package com.davron.company.service;

import com.davron.company.dto.DepartmentDto;
import com.davron.company.entity.Department;
import com.davron.company.exception.CustomException;
import com.davron.company.mapper.DepartmentMapper;
import com.davron.company.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class DepartmentService {
    private final DepartmentRepository repository;
    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentRepository repository, DepartmentMapper departmentMapper) {
        this.repository = repository;
        this.departmentMapper = departmentMapper;
    }

    public DepartmentDto save(DepartmentDto departmentDto) {
        Department entity = departmentMapper.toEntity(departmentDto);
        return departmentMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public DepartmentDto findById(Long id) {
        return departmentMapper.toDto(repository.findById(id).orElseThrow(()-> new CustomException("not found")));
    }

    public Page<DepartmentDto> findByCondition(DepartmentDto departmentDto, Pageable pageable) {
        Page<Department> entityPage = repository.findAll(pageable);
        List<Department> entities = entityPage.getContent();
        return new PageImpl<>(departmentMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public DepartmentDto update(DepartmentDto departmentDto, Long id) {
        DepartmentDto data = findById(id);
        Department entity = departmentMapper.toEntity(departmentDto);
        return save(departmentMapper.toDto(entity));
    }
}