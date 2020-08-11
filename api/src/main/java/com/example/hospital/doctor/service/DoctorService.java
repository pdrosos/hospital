package com.example.hospital.doctor.service;

import java.util.List;
import java.util.Optional;

import com.example.hospital.doctor.model.DoctorDto;
import com.example.hospital.doctor.model.DoctorExaminationScheduleDto;
import com.example.hospital.doctor.model.DoctorExaminationScheduleCreateDto;
import com.example.hospital.doctor.model.DoctorExaminationScheduleUpdateDto;
import com.example.hospital.doctor.model.DoctorUpdateDto;

public interface DoctorService {

    List<DoctorDto> findAll();

    Optional<DoctorDto> findById(Long id);

    List<DoctorExaminationScheduleDto> getExaminationScheduleList(Long doctorId);

    Optional<DoctorExaminationScheduleDto> getExaminationSchedule(Long doctorId, Long id);

    DoctorDto update(Long id, DoctorUpdateDto doctorUpdateDto);

    DoctorExaminationScheduleDto createExaminationSchedule(
            Long doctorId,
            DoctorExaminationScheduleCreateDto doctorExaminationScheduleCreateDto
    );

    DoctorExaminationScheduleDto updateExaminationSchedule(
            Long doctorId,
            Long id,
            DoctorExaminationScheduleUpdateDto doctorExaminationScheduleUpdateDto
    );

}
