package com.davron.company.mapper;

import com.davron.company.dto.AddressDto;
import com.davron.company.entity.Address;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AddressMapper extends EntityMapper<AddressDto, Address> {
}