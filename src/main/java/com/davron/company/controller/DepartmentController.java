package com.davron.company.controller;

import com.davron.company.dto.DepartmentDto;
import com.davron.company.entity.Department;
import com.davron.company.mapper.DepartmentMapper;
import com.davron.company.service.DepartmentService;
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

@RequestMapping("/department")
@RestController

public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated DepartmentDto departmentDto) {
        departmentService.save(departmentDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> findById(@PathVariable("id") Long id) {
        DepartmentDto department = departmentService.findById(id);
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        departmentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<DepartmentDto>> pageQuery(DepartmentDto departmentDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<DepartmentDto> departmentPage = departmentService.findByCondition(departmentDto, pageable);
        return ResponseEntity.ok(departmentPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated DepartmentDto departmentDto, @PathVariable("id") Long id) {
        departmentService.update(departmentDto, id);
        return ResponseEntity.ok().build();
    }
}