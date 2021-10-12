package com.sol.client.slot.v1;

import com.rcore.domain.commons.usecase.UseCaseExecutor;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.rest.api.commons.response.SearchApiResponse;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.spring.security.CredentialPrincipal;
import com.sol.client.slot.v1.mappers.SlotResponseMapper;
import com.sol.client.slot.v1.request.CreateSlotRequest;
import com.sol.client.slot.v1.request.SearchSlotRequest;
import com.sol.client.slot.v1.request.UpdateSlotRequest;
import com.sol.client.slot.v1.response.SlotResponse;
import com.sol.domain.slot.config.SlotConfig;
import com.sol.domain.slot.entity.SlotEntity;
import com.sol.domain.slot.exceptions.HasNoAccessToSlotException;
import com.sol.domain.slot.usecases.DeleteSlotUseCase;
import com.sol.domain.solUser.config.SolUserConfig;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.usecases.MeUseCase;
import com.sol.domain.task.config.TaskConfig;
import com.sol.domain.task.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component("SlotController")
public class SlotController implements SlotResource {
    private final UseCaseExecutor useCaseExecutor;
    private final SlotConfig solConfig;
    private final TaskConfig taskConfig;
    private final SolUserConfig solUserConfig;
    private final SlotResponseMapper mapper = new SlotResponseMapper();


    @Override
    public SuccessApiResponse<SlotResponse> create(CreateSlotRequest request, CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig.meUseCase().execute(MeUseCase.InputValues.builder().credentialId(credentialPrincipal.getId()).build()).getEntity();

        TaskEntity taskEntity = useCaseExecutor.execute(
                taskConfig.findTaskByIdUseCase(),
                IdInputValues.of(request.getTaskId()),
                output -> output.getEntity().get());

        return useCaseExecutor.execute(
                solConfig.createSlotUseCase(),
                request.toInputValues(solUserEntity.getId(), taskEntity),
                output -> SuccessApiResponse.of(mapper.map(output.getEntity()))
        );
    }

    @Override
    public SuccessApiResponse<SlotResponse> get(String id, CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig.meUseCase().execute(
                MeUseCase.InputValues.builder().credentialId(credentialPrincipal.getId()).build()).getEntity();

        SlotResponse slotResponse = useCaseExecutor.execute(
                solConfig.findSlotByIdUseCase(),
                IdInputValues.of(id),
                output -> mapper.map(output.getEntity().get())
        );

        if (slotResponse.getOwnerId().equals(solUserEntity.getId()) == false) {
            throw new HasNoAccessToSlotException();
        }

        return SuccessApiResponse.of(slotResponse);
    }

    @Override
    public SuccessApiResponse<SlotResponse> put(String id, UpdateSlotRequest request, CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig.meUseCase().execute(MeUseCase.InputValues.builder().credentialId(credentialPrincipal.getId()).build()).getEntity();

        return useCaseExecutor.execute(
                solConfig.updateSlotUseCase(),
                request.toInputValues(solUserEntity),
                output -> SuccessApiResponse.of(mapper.map(output.getEntity()))
        );
    }

    @Override
    public SuccessApiResponse<String> delete(String id, CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig.meUseCase().execute(MeUseCase.InputValues.builder().credentialId(credentialPrincipal.getId()).build()).getEntity();

        return useCaseExecutor.execute(
                solConfig.deleteSlotUseCase(),
                DeleteSlotUseCase.InputValues.of(id, solUserEntity.getId()),
                output -> SuccessApiResponse.of(HttpStatus.OK.toString())
        );
    }

    @Override
    public SuccessApiResponse<SearchApiResponse<SlotResponse>> find(SearchSlotRequest request, CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig.meUseCase().execute(MeUseCase.InputValues.builder().credentialId(credentialPrincipal.getId()).build()).getEntity();
        List<SlotResponse> response = new ArrayList<>();

        if (request.getTaskId() != null) {
            response = useCaseExecutor.execute(
                    solConfig.findByTaskSlotUseCase(),
                    request.toTaskInputValues(solUserEntity),
                    output ->
                            output
                                    .getEntity()
                                    .stream()
                                    .map(mapper::map)
                                    .collect(Collectors.toList())
            );
        } else {
            response = useCaseExecutor.execute(
                    solConfig.findByDateSlotUseCase(),
                    request.toDateInputValues(solUserEntity),
                    output ->
                            output
                                    .getEntity()
                                    .stream()
                                    .map(mapper::map)
                                    .collect(Collectors.toList())
            );
        }

        SuccessApiResponse<SearchApiResponse<SlotResponse>> result = SuccessApiResponse.of(
                SearchApiResponse.withItemsAndCount(response, Long.valueOf(response.size()))
        );
        return result;
    }
}
