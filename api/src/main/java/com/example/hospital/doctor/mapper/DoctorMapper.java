package com.example.hospital.doctor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.example.hospital.doctor.entity.Doctor;
import com.example.hospital.doctor.model.DoctorDto;
import com.example.hospital.doctor.model.DoctorUpdateDto;

@Mapper
public interface DoctorMapper {

    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    void updateDoctorFromDoctorUpdateDto(DoctorUpdateDto dto, @MappingTarget Doctor entity);

    DoctorDto doctorToDoctorDto(Doctor entity);
}