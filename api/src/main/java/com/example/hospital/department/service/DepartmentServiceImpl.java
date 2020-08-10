package com.example.hospital.department.service;

import com.example.hospital.common.error.EntityNotFoundException;
import com.example.hospital.department.entity.Department;
import com.example.hospital.department.mapper.DepartmentMapper;
import com.example.hospital.department.model.DepartmentCreateDto;
import com.example.hospital.department.model.DepartmentDto;
import com.example.hospital.department.model.DepartmentUpdateDto;
import com.example.hospital.department.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<DepartmentDto> findAll() {
        // todo pageable
        return departmentRepository.findAll().
                stream().
                map(DepartmentMapper.INSTANCE::departmentToDepartmentDto).
                collect(Collectors.toList());
    }

    @Override
    public Optional<DepartmentDto> findById(long id) {
        Optional<Department> department = departmentRepository.findById(id);

        return department.map(DepartmentMapper.INSTANCE::departmentToDepartmentDto);
    }

    public DepartmentDto create(DepartmentCreateDto departmentCreateDto) {
        Objects.requireNonNull(departmentCreateDto);

        LOGGER.debug("Creating department");

        Department department =
                DepartmentMapper.INSTANCE.departmentCreateDtoToDepartment(departmentCreateDto);

        department = departmentRepository.save(department);

        return DepartmentMapper.INSTANCE.departmentToDepartmentDto(department);
    }

    public DepartmentDto update(
            Long id,
            DepartmentUpdateDto departmentUpdateDto
    ) throws EntityNotFoundException {
        Objects.requireNonNull(id);
        Objects.requireNonNull(departmentUpdateDto);

        LOGGER.debug("Updating department");

        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        if (optionalDepartment.isEmpty()) {
            throw new EntityNotFoundException(id);
        }

        Department department = optionalDepartment.get();

        DepartmentMapper.INSTANCE.updateDepartmentFromDepartmentUpdateDto(departmentUpdateDto, department);
        department = departmentRepository.save(department);

        return DepartmentMapper.INSTANCE.departmentToDepartmentDto(department);
    }

    public DepartmentDto delete(Long id)  {
        Objects.requireNonNull(id);

        LOGGER.debug("Deleting department");

        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        if (optionalDepartment.isEmpty()) {
            throw new EntityNotFoundException(id);
        }

        departmentRepository.deleteById(id);

        Department department = optionalDepartment.get();

        return DepartmentMapper.INSTANCE.departmentToDepartmentDto(department);
    }
}
