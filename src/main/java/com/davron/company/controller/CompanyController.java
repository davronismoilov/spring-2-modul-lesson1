package com.davron.company.controller;

import com.davron.company.dto.CompanyDto;
import com.davron.company.entity.Company;
import com.davron.company.mapper.CompanyMapper;
import com.davron.company.service.CompanyService;

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

@RequestMapping("/company")
@RestController


public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated CompanyDto companyDto) {
        companyService.save(companyDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> findById(@PathVariable("id") Long id) {
        CompanyDto company = companyService.findById(id);
        return ResponseEntity.ok(company);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        companyService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<CompanyDto>> pageQuery(CompanyDto companyDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CompanyDto> companyPage = companyService.findByCondition(companyDto, pageable);
        return ResponseEntity.ok(companyPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated CompanyDto companyDto, @PathVariable("id") Long id) {
        companyService.update(companyDto, id);
        return ResponseEntity.ok().build();
    }
}