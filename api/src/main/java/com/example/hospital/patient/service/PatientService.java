package com.example.hospital.patient.service;

import com.example.hospital.patient.model.PatientDto;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<PatientDto> findAll();

    Optional<PatientDto> findById(long id);
}
