package com.davron.company.controller;

import com.davron.company.dto.AddressDto;
import com.davron.company.entity.Address;
import com.davron.company.mapper.AddressMapper;
import com.davron.company.service.AddressService;

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

@RequestMapping("/api/address")
@RestController

public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated AddressDto addressDto) {
        addressService.save(addressDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> findById(@PathVariable("id") Long id) {
        AddressDto address = addressService.findById(id);
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        addressService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<AddressDto>> pageQuery(AddressDto addressDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<AddressDto> addressPage = addressService.findByCondition(addressDto, pageable);
        return ResponseEntity.ok(addressPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated AddressDto addressDto, @PathVariable("id") Long id) {
        addressService.update(addressDto, id);
        return ResponseEntity.ok().build();
    }
}