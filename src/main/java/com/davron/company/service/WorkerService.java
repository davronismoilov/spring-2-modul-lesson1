package com.davron.company.service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.davron.company.dto.WorkerDto;
import com.davron.company.entity.Worker;
import com.davron.company.exception.CustomException;
import com.davron.company.mapper.WorkerMapper;
import com.davron.company.repository.WorkerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class WorkerService {
    private final WorkerRepository repository;
    private final WorkerMapper workerMapper;

    public WorkerService(WorkerRepository repository, WorkerMapper workerMapper) {
        this.repository = repository;
        this.workerMapper = workerMapper;
    }

    public WorkerDto save(WorkerDto workerDto) {
        Worker entity = workerMapper.toEntity(workerDto);
        return workerMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public WorkerDto findById(Long id) {
        return workerMapper.toDto(repository.findById(id).orElseThrow(()-> new CustomException("not found")));
    }

    public Page<WorkerDto> findByCondition(WorkerDto workerDto, Pageable pageable) {
        Page<Worker> entityPage = repository.findAll(pageable);
        List<Worker> entities = entityPage.getContent();
        return new PageImpl<>(workerMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public WorkerDto update(WorkerDto workerDto, Long id) {
        WorkerDto data = findById(id);
        Worker entity = workerMapper.toEntity(workerDto);
        return save(workerMapper.toDto(entity));
    }
}