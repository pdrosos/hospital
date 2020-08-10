package com.example.hospital.patient.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.hospital.common.error.EntityNotFoundException;
import com.example.hospital.patient.entity.Patient;
import com.example.hospital.patient.mapper.PatientMapper;
import com.example.hospital.patient.model.PatientDto;
import com.example.hospital.patient.model.PatientUpdateDto;
import com.example.hospital.patient.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);

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

    public PatientDto update(
            Long id,
            PatientUpdateDto patientUpdateDto
    ) throws EntityNotFoundException {
        Objects.requireNonNull(id);
        Objects.requireNonNull(patientUpdateDto);

        LOGGER.debug("Updating patient");

        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isEmpty()) {
            throw new EntityNotFoundException(id);
        }

        Patient patient = optionalPatient.get();

        PatientMapper.INSTANCE.updatePatientFromPatientUpdateDto(patientUpdateDto, patient);
        patient = patientRepository.save(patient);

        return PatientMapper.INSTANCE.patientToPatientDto(patient);
    }

}
