package com.example.hospital.doctor.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.hospital.common.error.EntityNotFoundException;
import com.example.hospital.doctor.entity.Doctor;
import com.example.hospital.doctor.mapper.DoctorMapper;
import com.example.hospital.doctor.model.DoctorDto;
import com.example.hospital.doctor.model.DoctorUpdateDto;
import com.example.hospital.doctor.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<DoctorDto> findAll() {
        // todo pageable
        return doctorRepository.findAll().
                stream().
                map(DoctorMapper.INSTANCE::doctorToDoctorDto).
                collect(Collectors.toList());
    }

    @Override
    public Optional<DoctorDto> findById(long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);

        return doctor.map(DoctorMapper.INSTANCE::doctorToDoctorDto);
    }

    public DoctorDto update(
            Long id,
            DoctorUpdateDto doctorUpdateDto
    ) throws EntityNotFoundException {
        Objects.requireNonNull(id);
        Objects.requireNonNull(doctorUpdateDto);

        LOGGER.debug("Updating doctor");

        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);

        if (optionalDoctor.isEmpty()) {
            throw new EntityNotFoundException(id);
        }

        Doctor doctor = optionalDoctor.get();

        DoctorMapper.INSTANCE.updateDoctorFromDoctorUpdateDto(doctorUpdateDto, doctor);
        doctor = doctorRepository.save(doctor);

        return DoctorMapper.INSTANCE.doctorToDoctorDto(doctor);
    }

}
