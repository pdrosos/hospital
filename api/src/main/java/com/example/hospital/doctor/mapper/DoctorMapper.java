package com.example.hospital.doctor.mapper;

import java.util.List;
import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.example.hospital.doctor.entity.Doctor;
import com.example.hospital.doctor.entity.DoctorExaminationSchedule;
import com.example.hospital.doctor.model.DoctorDto;
import com.example.hospital.doctor.model.DoctorExaminationScheduleDto;
import com.example.hospital.doctor.model.DoctorUpdateDto;
import com.example.hospital.doctor.model.DoctorExaminationScheduleCreateDto;
import com.example.hospital.doctor.model.DoctorExaminationScheduleUpdateDto;

@Mapper
public interface DoctorMapper {

    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    DoctorDto doctorToDoctorDto(Doctor entity);

    List<DoctorExaminationScheduleDto> doctorExaminationScheduleToDoctorExaminationScheduleDtoList(
            Collection<DoctorExaminationSchedule> doctorExaminationSchedule
    );

    DoctorExaminationScheduleDto doctorExaminationScheduleToDoctorExaminationScheduleDto(
            DoctorExaminationSchedule doctorExaminationSchedule
    );

    DoctorExaminationSchedule DoctorExaminationScheduleCreateDtoToDoctorExaminationSchedule(
            DoctorExaminationScheduleCreateDto doctorExaminationScheduleCreateDto
    );

    void updateDoctorFromDoctorUpdateDto(DoctorUpdateDto dto, @MappingTarget Doctor entity);

    void updateDoctorExaminationScheduleFromDoctorExaminationScheduleUpdateDto(
            DoctorExaminationScheduleUpdateDto dto,
            @MappingTarget DoctorExaminationSchedule entity
    );
}