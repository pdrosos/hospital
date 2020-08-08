package com.example.hospital.medicalspecialty.service;

import java.util.List;
import java.util.Optional;

import com.example.hospital.medicalspecialty.model.MedicalSpecialtyCreateDto;
import com.example.hospital.medicalspecialty.model.MedicalSpecialtyDto;
import com.example.hospital.medicalspecialty.model.MedicalSpecialtyUpdateDto;

public interface MedicalSpecialtyService {

    List<MedicalSpecialtyDto> findAll();

    Optional<MedicalSpecialtyDto> findById(long id);

    MedicalSpecialtyDto create(MedicalSpecialtyCreateDto medicalSpecialtyCreateDto);

    MedicalSpecialtyDto update(Long id, MedicalSpecialtyUpdateDto medicalSpecialtyUpdateDto);

    MedicalSpecialtyDto delete(Long id);
}
