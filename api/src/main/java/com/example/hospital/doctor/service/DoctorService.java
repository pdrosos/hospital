package com.example.hospital.doctor.service;

import java.util.List;
import java.util.Optional;

import com.example.hospital.doctor.model.DoctorDto;
import com.example.hospital.doctor.model.DoctorUpdateDto;

public interface DoctorService {

    List<DoctorDto> findAll();

    Optional<DoctorDto> findById(Long id);

    DoctorDto update(Long id, DoctorUpdateDto doctorUpdateDto);

}
