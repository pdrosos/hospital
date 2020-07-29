package com.example.hospital.patient.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.hospital.patient.entity.Patient;
import com.example.hospital.patient.mapper.PatientMapper;
import com.example.hospital.patient.model.PatientDto;
import com.example.hospital.patient.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<PatientDto> findAll() {
        // todo pageable
        return patientRepository.findAll().
                stream().
                map(PatientMapper.INSTANCE::patientToPatientDto).
                collect(Collectors.toList());
    }

    @Override
    public Optional<PatientDto> findById(long id) {
        Optional<Patient> patient = patientRepository.findById(id);

        return patient.map(PatientMapper.INSTANCE::patientToPatientDto);
    }
}
