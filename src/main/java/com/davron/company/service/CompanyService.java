package com.davron.company.service;

import com.davron.company.dto.CompanyDto;
import com.davron.company.entity.Company;
import com.davron.company.exception.CustomException;
import com.davron.company.mapper.CompanyMapper;
import com.davron.company.repository.CompanyRepository;
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
public class CompanyService {
    private final CompanyRepository repository;
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyRepository repository, CompanyMapper companyMapper) {
        this.repository = repository;
        this.companyMapper = companyMapper;
    }

    public CompanyDto save(CompanyDto companyDto) {
        Company entity = companyMapper.toEntity(companyDto);
        return companyMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public CompanyDto findById(Long id) {
        return companyMapper.toDto(repository.findById(id).orElseThrow(()-> new CustomException("not found ")));
    }

    public Page<CompanyDto> findByCondition(CompanyDto companyDto, Pageable pageable) {
        Page<Company> entityPage = repository.findAll(pageable);
        List<Company> entities = entityPage.getContent();
        return new PageImpl<>(companyMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public CompanyDto update(CompanyDto companyDto, Long id) {
        CompanyDto data = findById(id);
        Company entity = companyMapper.toEntity(companyDto);
        return save(companyMapper.toDto(entity));
    }
}