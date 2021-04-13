package com.sol.domain.solUser.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.solUser.entity.Credential;
import com.sol.domain.solUser.entity.Setting;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.port.SolUserIdGenerator;
import com.sol.domain.solUser.port.SolUserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Создание сущности
 */
public class CreateSolUserUseCase extends AbstractCreateUseCase<SolUserEntity, SolUserIdGenerator, SolUserRepository, CreateSolUserUseCase.InputValues> {


    public CreateSolUserUseCase(SolUserRepository repository, SolUserIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<SolUserEntity> execute(InputValues inputValues) {

        validate(inputValues);

        SolUserEntity solUserEntity = new SolUserEntity(idGenerator.generate());
       
        // Происходит заполнение всех полей 
        solUserEntity.setEmail(inputValues.email);
        solUserEntity.setUsername(inputValues.username);
        solUserEntity.setFirstName(inputValues.firstName);
        solUserEntity.setLastName(inputValues.lastName);
        solUserEntity.setUserPicId(inputValues.userPicId);
        solUserEntity.setSetting(inputValues.setting);
        solUserEntity.setCredentials(inputValues.credentials);

        solUserEntity = repository.save(solUserEntity);

        return SingletonEntityOutputValues.of(solUserEntity);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        // перечисление полей необходимых для создания сущности
        protected String email;
        protected String username;
        protected String firstName;
        protected String lastName;
        protected String userPicId;
        protected Setting setting;
        protected List<Credential> credentials;

    }

    /**
     * Валидация входящий данных
     * @param inputValues
     */
    private void validate(InputValues inputValues) {

    }

}
