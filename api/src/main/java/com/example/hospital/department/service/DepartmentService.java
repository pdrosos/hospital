package com.example.hospital.department.service;

import com.example.hospital.department.model.DepartmentCreateDto;
import com.example.hospital.department.model.DepartmentDto;
import com.example.hospital.department.model.DepartmentUpdateDto;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<DepartmentDto> findAll();

    Optional<DepartmentDto> findById(long id);

    DepartmentDto create(DepartmentCreateDto departmentCreateDto);

    DepartmentDto update(Long id, DepartmentUpdateDto departmentUpdateDto);

    DepartmentDto delete(Long id);
}
