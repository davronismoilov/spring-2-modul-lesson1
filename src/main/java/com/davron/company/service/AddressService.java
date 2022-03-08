package com.davron.company.service;

import com.davron.company.dto.AddressDto;
import com.davron.company.entity.Address;
import com.davron.company.exception.CustomException;
import com.davron.company.mapper.AddressMapper;
import com.davron.company.repository.AddressRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class AddressService {
    private final AddressRepository repository;

    @Autowired
     AddressMapper addressMapper;

    public AddressService(AddressRepository repository) {
        this.repository = repository;

    }

    public AddressDto save(AddressDto addressDto) {
        Address entity = addressMapper.toEntity(addressDto);
        return addressMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public AddressDto findById(Long id) {
        return addressMapper.toDto(repository.findById(id).orElseThrow(()-> new CustomException("not found")));
    }

    public Page<AddressDto> findByCondition(AddressDto addressDto, Pageable pageable) {
        Page<Address> entityPage = repository.findAll(pageable);
        List<Address> entities = entityPage.getContent();
        return new PageImpl<>(addressMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public AddressDto update(AddressDto addressDto, Long id) {
        AddressDto data = findById(id);
        Address entity = addressMapper.toEntity(addressDto);
        return save(addressMapper.toDto(entity));
    }
}