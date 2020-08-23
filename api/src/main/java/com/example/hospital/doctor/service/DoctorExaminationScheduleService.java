package com.example.hospital.doctor.service;

import java.util.List;
import java.util.Optional;

import com.example.hospital.doctor.model.DoctorExaminationScheduleCreateDto;
import com.example.hospital.doctor.model.DoctorExaminationScheduleDto;
import com.example.hospital.doctor.model.DoctorExaminationScheduleUpdateDto;

public interface DoctorExaminationScheduleService {

    List<DoctorExaminationScheduleDto> getDoctorExaminationScheduleList(Long doctorId);

    Optional<DoctorExaminationScheduleDto> getDoctorExaminationSchedule(Long doctorId, Long id);

    DoctorExaminationScheduleDto createDoctorExaminationSchedule(
            Long doctorId,
            DoctorExaminationScheduleCreateDto doctorExaminationScheduleCreateDto
    );

    DoctorExaminationScheduleDto updateDoctorExaminationSchedule(
            Long doctorId,
            Long id,
            DoctorExaminationScheduleUpdateDto doctorExaminationScheduleUpdateDto
    );

    DoctorExaminationScheduleDto deleteDoctorExaminationSchedule(Long doctorId, Long id);
}
