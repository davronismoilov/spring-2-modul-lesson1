package com.davron.company.mapper;

import com.davron.company.dto.DepartmentDto;
import com.davron.company.entity.Department;
import org.springframework.stereotype.Component;

@Component
public interface DepartmentMapper extends EntityMapper<DepartmentDto, Department> {
}