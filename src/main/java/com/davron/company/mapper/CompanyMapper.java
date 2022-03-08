package com.davron.company.mapper;

import com.davron.company.dto.CompanyDto;
import com.davron.company.entity.Company;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CompanyMapper extends EntityMapper<CompanyDto, Company> {
}