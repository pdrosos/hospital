package com.example.hospital.department.mapper;

import com.example.hospital.department.entity.Department;
import com.example.hospital.department.model.DepartmentCreateDto;
import com.example.hospital.department.model.DepartmentDto;
import com.example.hospital.department.model.DepartmentUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    Department departmentCreateDtoToDepartment(DepartmentCreateDto dto);

    void updateDepartmentFromDepartmentUpdateDto(DepartmentUpdateDto dto, @MappingTarget Department entity);

    DepartmentDto departmentToDepartmentDto(Department entity);
}