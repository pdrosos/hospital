package com.example.hospital.patient.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.hospital.patient.entity.Patient;
import com.example.hospital.patient.model.PatientDto;

@Mapper
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    Patient patientDtoToPatient(PatientDto dto);

    PatientDto patientToPatientDto(Patient entity);
}