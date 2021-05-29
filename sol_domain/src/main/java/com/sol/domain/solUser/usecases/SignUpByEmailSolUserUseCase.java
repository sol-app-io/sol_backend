package com.sol.domain.solUser.usecases;

import com.rcore.domain.auth.authorization.config.AuthorizationConfig;
import com.rcore.domain.auth.credential.config.CredentialConfig;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.credential.port.PasswordCryptographer;
import com.rcore.domain.auth.credential.usecases.CreateCredentialUseCase;
import com.rcore.domain.auth.role.config.RoleConfig;
import com.rcore.domain.auth.role.entity.RoleEntity;
import com.rcore.domain.auth.role.usecases.CreateRoleUseCase;
import com.rcore.domain.auth.role.usecases.FindRoleByNameUseCase;
import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.rcore.domain.security.exceptions.AuthenticationException;
import com.sol.domain.solUser.entity.Credential;
import com.sol.domain.solUser.entity.Setting;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.port.SolUserIdGenerator;
import com.sol.domain.solUser.port.SolUserRepository;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Создание сущности
 */

@AllArgsConstructor
public class SignUpByEmailSolUserUseCase extends UseCase<SignUpByEmailSolUserUseCase.InputValues, SingletonEntityOutputValues<SolUserEntity>> {

    private final SolUserRepository repository;
    private final SolUserIdGenerator idGenerator;
    private final CredentialRepository credentialRepository;
    private final CreateCredentialUseCase createCredentialUseCase;
    private final RoleConfig roleConfig;
    private final PasswordCryptographer passwordCryptographer;

    private final static String ROLE_SOL_EMAIL = "SOL_ROLE_EMAIL";

    private RoleEntity solRolePassword() {
        Optional<RoleEntity> role = roleConfig.findRoleByNameUseCase().execute(FindRoleByNameUseCase.InputValues.of(ROLE_SOL_EMAIL)).getEntity();
        if (role.isPresent()) return role.get();

        List<RoleEntity.AuthType> authTypes = new ArrayList<>();
        authTypes.add(RoleEntity.AuthType.PASSWORD);
        RoleEntity roleEntity = roleConfig.createRoleUseCase().execute(
                CreateRoleUseCase.InputValues.builder()
                        .name(ROLE_SOL_EMAIL)
                        .accesses(new ArrayList<>())
                        .hasBoundlessAccess(false)
                        .availableAuthTypes(authTypes).build()).getEntity();

        return roleEntity;
    }

    @Override
    public SingletonEntityOutputValues<SolUserEntity> execute(InputValues inputValues) {

        validate(inputValues);

        Optional<CredentialEntity> credentialEntityOptional = this.credentialRepository.findByEmail(inputValues.email);
        CredentialEntity credentialEntity = null;

        if (credentialEntityOptional.isPresent()) {
            CredentialEntity ce = credentialEntityOptional.get();
            Optional<SolUserEntity> solOptional = this.repository.findByCredential(ce.getId());
            if (solOptional.isPresent()) {
                return SingletonEntityOutputValues.of(solOptional.get());
            }

            if (passwordCryptographer.validate(inputValues.password, credentialEntity.getPassword(), credentialEntity.getId()) == false) {
                throw new AuthenticationException();
            }

            credentialEntity = ce;
        } else {
            List<CreateCredentialUseCase.InputValues.Role> roles = new ArrayList<>();
            RoleEntity roleEntity = solRolePassword();
            CreateCredentialUseCase.InputValues.Role role = CreateCredentialUseCase.InputValues.Role.builder()
                    .name(roleEntity.getName())
                    .roleId(roleEntity.getId())
                    .isBlocked(false)
                    .build();
            roles.add(role);

            credentialEntity = createCredentialUseCase.execute(
                    CreateCredentialUseCase.InputValues.builder()
                            .username(inputValues.email)
                            .password(inputValues.password)
                            .email(inputValues.email)

                            .roles(roles)
                            .status(CredentialEntity.Status.ACTIVE)
                            .build()
            ).getEntity();
        }

        Optional<SolUserEntity> solUserEntityOptional = repository.findByCredential(credentialEntity.getId());
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
                                    .credentialId(credentialEntity.getId())
                                    .build()
                    );
            solUserEntity = repository.save(solUserEntity);
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
