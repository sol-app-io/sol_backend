package com.sol.domain.solUser.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.solUser.entity.Credential;
import com.sol.domain.solUser.entity.Setting;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.exceptions.SolUserNotFoundException;
import com.sol.domain.solUser.port.SolUserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdateSolUserUseCase extends UseCase<UpdateSolUserUseCase.InputValues, SingletonEntityOutputValues<SolUserEntity>> {

    private final SolUserRepository solUserRepository;

    @Override
    public SingletonEntityOutputValues<SolUserEntity> execute(InputValues inputValues) {

        SolUserEntity solUserEntity = solUserRepository
                .findById(inputValues.getId())
                .orElseThrow(SolUserNotFoundException::new);

        // Тут изменение данных
        solUserEntity.setEmail(inputValues.email);
        solUserEntity.setUsername(inputValues.username);
        solUserEntity.setFirstName(inputValues.firstName);
        solUserEntity.setLastName(inputValues.lastName);
        solUserEntity.setUserPicId(inputValues.userPicId);
        solUserEntity.setSetting(inputValues.setting);
        solUserEntity.setCredentials(inputValues.credentials);
        
        solUserEntity = solUserRepository.save(solUserEntity);

        return SingletonEntityOutputValues.of(solUserEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        //Сущность которую требуется обновить
        protected String id;

        //указываются данные которые необходимо изменить
        protected String email;
        protected String username;
        protected String firstName;
        protected String lastName;
        protected String userPicId;
        protected Setting setting;
        protected List<Credential> credentials;
    }

}
