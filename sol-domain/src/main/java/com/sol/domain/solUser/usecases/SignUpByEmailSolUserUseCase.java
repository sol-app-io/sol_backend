package com.sol.domain.solUser.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.solUser.entity.Credential;
import com.sol.domain.solUser.entity.Setting;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.port.SolUserIdGenerator;
import com.sol.domain.solUser.port.SolUserRepository;
import com.sol.domain.space.entity.SpaceEntity;
import com.sol.domain.space.usecases.CreateSpaceUseCase;
import com.sol.domain.viewUser.usecases.InitAllViewsUseCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.foodtechlab.lib.auth.integration.core.credential.CredentialServiceFacade;
import ru.foodtechlab.lib.auth.integration.core.credential.exception.CredentialNotFoundException;
import ru.foodtechlab.lib.auth.integration.core.role.RoleServiceFacade;
import ru.foodtechlab.lib.auth.service.facade.credential.dto.requests.CreateCredentialRequest;
import ru.foodtechlab.lib.auth.service.facade.credential.dto.responses.CredentialResponse;
import ru.foodtechlab.lib.auth.service.facade.role.dto.requests.CreateRoleRequest;
import ru.foodtechlab.lib.auth.service.facade.role.dto.responses.RoleResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Создание сущности
 */

@AllArgsConstructor
public class SignUpByEmailSolUserUseCase extends UseCase<SignUpByEmailSolUserUseCase.InputValues, SingletonEntityOutputValues<SolUserEntity>> {

    private final SolUserRepository repository;
    private final SolUserIdGenerator idGenerator;
    private final CredentialServiceFacade credentialServiceFacade;
    private final RoleServiceFacade roleServiceFacade;
    private final CreateSpaceUseCase createSpaceUseCase;
    private final InitAllViewsUseCase initAllViewsUseCase;

    private final static String SOL_USER_ROLE = "SOL_USER_ROLE";

    private RoleResponse solRolePassword() {

        Optional<RoleResponse> role = null;
        try {
            role = roleServiceFacade.findByCode(SOL_USER_ROLE);
        } catch (Exception e) {
        }

        if (role != null && role.isPresent()) return role.get();

        RoleResponse roleResponse = roleServiceFacade.create(CreateRoleRequest.builder()
                .name(SOL_USER_ROLE)
                .code(SOL_USER_ROLE)
                .accessIds(new ArrayList<>())
                .isRegistrationAllowed(false)
                .build()
        );

        return roleResponse;
    }

    @Override
    public SingletonEntityOutputValues<SolUserEntity> execute(InputValues inputValues) {
        validate(inputValues);
        Optional<CredentialResponse> credentialResponseOptional = null;

        try {
            credentialResponseOptional = credentialServiceFacade
                    .findByName(
                            inputValues.email.toLowerCase(Locale.ROOT)
                    );
        } catch (CredentialNotFoundException e) {
            e.printStackTrace();
        }
        CredentialResponse credentialResponse = null;

        if (credentialResponseOptional != null && credentialResponseOptional.isPresent()) {
            credentialResponse = credentialResponseOptional.get();
            // TODO delete or add auth
            //throw new SolUserExistException();
        } else {
            List<CreateCredentialRequest.Role> roles = new ArrayList<>();
            RoleResponse roleResponse = solRolePassword();
            CreateCredentialRequest.Role role = CreateCredentialRequest.Role.builder()
                    .code(roleResponse.getCode())
                    .roleId(roleResponse.getId())
                    .isBlocked(false)
                    .build();
            roles.add(role);

            credentialResponse = credentialServiceFacade.create(
                    CreateCredentialRequest.builder()
                            .email(new CredentialResponse.Email(
                                    inputValues.email.toLowerCase(Locale.ROOT),
                                    false
                            ))
                            .username(inputValues.email.toLowerCase(Locale.ROOT))
                            .password(inputValues.password)
                            .roles(roles)
                            .isBlocked(false)
                            .confirmationCodeDestinationType(CreateCredentialRequest.ConfirmationCodeDestinationType.EMAIL)
                            .personalConfirmationCode("4242")
                            .confirmationCodeType(CreateCredentialRequest.Type.ONE_TIME)
                            .build()
            );
        }

        Optional<SolUserEntity> solUserEntityOptional = repository.findByCredential(credentialResponse.getId());
        SolUserEntity solUserEntity = null;

        if (solUserEntityOptional.isPresent()) {
            solUserEntity = solUserEntityOptional.get();
        } else {
            solUserEntity = new SolUserEntity(idGenerator.generate());

            solUserEntity.setEmail(inputValues.email);
            solUserEntity.setUsername(inputValues.email);
            solUserEntity.setFirstName("");
            solUserEntity.setLastName("");
            solUserEntity.setUserPicId(null);
            solUserEntity.setSetting(new Setting());
            solUserEntity.getCredentials()
                    .add(
                            Credential.builder()
                                    .type(Credential.Type.EMAIL)
                                    .credentialId(credentialResponse.getId())
                                    .build()
                    );
            solUserEntity = repository.save(solUserEntity);

            createSpaceUseCase.execute(CreateSpaceUseCase.InputValues.builder()
                    .title("Inbox")
                    .ownerId(solUserEntity.getId())
                    .icon(Icon.of("\uD83D\uDCE5"))
                    .type(SpaceEntity.Type.INBOX)
                    .build()
            );
        }

        initAllViewsUseCase.execute(InitAllViewsUseCase.InputValues.of(solUserEntity));

        return SingletonEntityOutputValues.of(solUserEntity);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        // перечисление полей необходимых для создания сущности
        protected String email;
        protected String password;
    }

    /**
     * Валидация входящий данных
     *
     * @param inputValues
     */
    private void validate(InputValues inputValues) {

    }

}
