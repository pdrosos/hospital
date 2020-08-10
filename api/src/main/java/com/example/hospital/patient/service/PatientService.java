package com.example.hospital.patient.service;

import java.util.List;
import java.util.Optional;

import com.example.hospital.patient.model.PatientDto;
import com.example.hospital.patient.model.PatientUpdateDto;

public interface PatientService {

    List<PatientDto> findAll();

    Optional<PatientDto> findById(long id);

    PatientDto update(Long id, PatientUpdateDto medicalSpecialtyUpdateDto);

}
