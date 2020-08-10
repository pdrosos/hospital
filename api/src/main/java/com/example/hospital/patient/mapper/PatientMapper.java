package com.example.hospital.patient.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.example.hospital.patient.entity.Patient;
import com.example.hospital.patient.model.PatientDto;
import com.example.hospital.patient.model.PatientUpdateDto;

@Mapper
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    void updatePatientFromPatientUpdateDto(PatientUpdateDto dto, @MappingTarget Patient entity);

    PatientDto patientToPatientDto(Patient entity);
}