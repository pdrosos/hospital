package com.example.hospital.medicalspecialty.mapper;

import com.example.hospital.medicalspecialty.model.MedicalSpecialtyCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.example.hospital.medicalspecialty.entity.MedicalSpecialty;
import com.example.hospital.medicalspecialty.model.MedicalSpecialtyDto;
import com.example.hospital.medicalspecialty.model.MedicalSpecialtyUpdateDto;

@Mapper
public interface MedicalSpecialtyMapper {
    MedicalSpecialtyMapper INSTANCE = Mappers.getMapper(MedicalSpecialtyMapper.class);

    MedicalSpecialty medicalSpecialtyCreateDtoToMedicalSpecialty(MedicalSpecialtyCreateDto dto);

    void updateMedicalSpecialtyFromMedicalSpecialtyUpdateDto(MedicalSpecialtyUpdateDto dto, @MappingTarget MedicalSpecialty entity);

    MedicalSpecialtyDto medicalSpecialtyToMedicalSpecialtyDto(MedicalSpecialty entity);
}