package com.sol.domain.backgroundTaskForView.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.backgroundTaskForView.entity.BackgroundTaskForViewEntity;
import com.sol.domain.backgroundTaskForView.port.filters.BackgroundTaskForViewFilters;

public interface BackgroundTaskForViewRepository extends CRUDRepository<String, BackgroundTaskForViewEntity, BackgroundTaskForViewFilters> {
}
