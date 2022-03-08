package com.davron.company.mapper;

import com.davron.company.dto.WorkerDto;
import com.davron.company.entity.Worker;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface WorkerMapper extends EntityMapper<WorkerDto, Worker> {
}