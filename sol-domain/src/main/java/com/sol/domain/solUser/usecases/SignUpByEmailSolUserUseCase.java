package com.sol.domain.solUser.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.solUser.entity.Credential;
import com.sol.domain.solUser.entity.Setting;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.exceptions.SolUserExistException;
import com.sol.domain.solUser.port.SolUserIdGenerator;
import com.sol.domain.solUser.port.SolUserRepository;
import com.sol.domain.space.entity.SpaceEntity;
import com.sol.domain.space.usecases.CreateSpaceUseCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.foodtechlab.lib.auth.integration.core.credential.exception.CredentialNotFoundException;
import ru.foodtechlab.lib.auth.integration.core.credential.request.CreateCredentialRequest;
import ru.foodtechlab.lib.auth.integration.core.credential.response.CredentialResponse;
import ru.foodtechlab.lib.auth.integration.core.role.response.RoleResponse;

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
    private final ru.foodtechlab.lib.auth.integration.core.credential.CredentialServiceFacade credentialServiceFacade;
    private final ru.foodtechlab.lib.auth.integration.core.role.RoleServiceFacade roleServiceFacade;
    private final CreateSpaceUseCase createSpaceUseCase;

    private final static String SOL_USER_ROLE = "SOL_USER_ROLE";

    private ru.foodtechlab.lib.auth.integration.core.role.response.RoleResponse solRolePassword() {

        Optional<ru.foodtechlab.lib.auth.integration.core.role.response.RoleResponse> role = null;
        try{
            role = roleServiceFacade.findByName(SOL_USER_ROLE);
        }catch(Exception e){
        }

        if (role != null && role.isPresent()) return role.get();

        List<RoleResponse.AuthType> authTypes = new ArrayList<>();
        authTypes.add(RoleResponse.AuthType.PASSWORD);
        RoleResponse roleResponse = roleServiceFacade.create(ru.foodtechlab.lib.auth.integration.core.role.request.CreateRoleRequest.builder()
                .name(SOL_USER_ROLE)
                .accesses(new ArrayList<>())
                .hasBoundlessAccess(false)
                .availableAuthTypes(authTypes).build()
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
        }catch (CredentialNotFoundException e){
        }
        CredentialResponse credentialResponse = null;

        if (credentialResponseOptional != null && credentialResponseOptional.isPresent()) {
            throw new SolUserExistException();
        } else {
            List<CreateCredentialRequest.Role> roles = new ArrayList<>();
            RoleResponse roleResponse = solRolePassword();
            CreateCredentialRequest.Role role = CreateCredentialRequest.Role.builder()
                    .name(roleResponse.getName())
                    .roleId(roleResponse.getId())
                    .isBlocked(false)
                    .build();
            roles.add(role);

            credentialResponse = credentialServiceFacade.create(
                    new CreateCredentialRequest(
                            inputValues.email.toLowerCase(Locale.ROOT),
                            inputValues.password,
                            "",
                            inputValues.email,
                            roles,
                            CredentialResponse.Status.ACTIVE
                    )
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
