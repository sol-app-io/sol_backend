package com.sol.domain.solUser.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.port.SolUserRepository;
import lombok.*;
import ru.foodtechlab.lib.auth.service.domain.authorization.exceptions.BadCredentialsException;

import java.util.Optional;


@RequiredArgsConstructor
public class MeUseCase extends UseCase<MeUseCase.InputValues, SingletonEntityOutputValues<SolUserEntity>> {

    private final SolUserRepository repository;

    @Override
    public SingletonEntityOutputValues<SolUserEntity> execute(MeUseCase.InputValues inputValues) {
        Optional<SolUserEntity> solUserEntityOptional = repository.findByCredential(inputValues.credentialId);
        if(solUserEntityOptional.isEmpty()){
            throw new BadCredentialsException();
        }

        SolUserEntity solUserEntity = solUserEntityOptional.get();
        return SingletonEntityOutputValues.of(solUserEntity);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        protected String credentialId;
    }

    /**
     * Валидация входящий данных
     *
     * @param inputValues
     */
    private void validate(SignUpByEmailSolUserUseCase.InputValues inputValues) {

    }

}