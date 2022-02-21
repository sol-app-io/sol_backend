package com.sol.client.base.schedules;

import com.rcore.domain.commons.usecase.UseCaseExecutor;
import com.sol.domain.backgroundTaskForView.config.BackgroundTaskForViewConfig;
import com.sol.domain.backgroundTaskForView.usecases.RunNextBackgroundTaskForViewUseCase;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SolSchedules {
    private final UseCaseExecutor useCaseExecutor;
    private final BackgroundTaskForViewConfig backgroundTaskForViewConfig;

    @Scheduled(fixedDelay = 1, initialDelay = 5 * 1000l)
    public void runNExtBackground() {
        useCaseExecutor.execute(
                backgroundTaskForViewConfig.runNextBackgroundTaskForViewUseCase(),
                RunNextBackgroundTaskForViewUseCase.InputValues.empty()
        );
    }
}
