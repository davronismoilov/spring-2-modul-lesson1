package com.davron.company.controller;

import com.davron.company.dto.WorkerDto;
import com.davron.company.entity.Worker;
import com.davron.company.mapper.WorkerMapper;
import com.davron.company.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/worker")
@RestController

public class WorkerController {
    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated WorkerDto workerDto) {
        workerService.save(workerDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkerDto> findById(@PathVariable("id") Long id) {
        WorkerDto worker = workerService.findById(id);
        return ResponseEntity.ok(worker);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        workerService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<WorkerDto>> pageQuery(WorkerDto workerDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<WorkerDto> workerPage = workerService.findByCondition(workerDto, pageable);
        return ResponseEntity.ok(workerPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated WorkerDto workerDto, @PathVariable("id") Long id) {
        workerService.update(workerDto, id);
        return ResponseEntity.ok().build();
    }
}