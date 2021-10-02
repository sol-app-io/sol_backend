package com.sol.domain.slot.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.slot.entity.SlotEntity;
import com.sol.domain.slot.port.filters.SlotFilters;

import java.time.LocalDateTime;
import java.util.List;

public interface SlotRepository extends CRUDRepository<String, SlotEntity, SlotFilters> {
    public List<SlotEntity> findByTaskId(String taskId, String ownerId);
    public List<SlotEntity> findByTaskId(String taskId);
    public List<SlotEntity> findByTime(LocalDateTime start, LocalDateTime end, String ownerId);
}
