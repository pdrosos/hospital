package com.example.hospital.doctor.service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.hospital.doctor.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.hospital.common.error.EntityNotFoundException;
import com.example.hospital.department.entity.Department;
import com.example.hospital.department.repository.DepartmentRepository;
import com.example.hospital.medicalspecialty.entity.MedicalSpecialty;
import com.example.hospital.medicalspecialty.repository.MedicalSpecialtyRepository;
import com.example.hospital.doctor.entity.Doctor;
import com.example.hospital.doctor.entity.DoctorExaminationSchedule;
import com.example.hospital.doctor.mapper.DoctorMapper;
import com.example.hospital.doctor.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;
    private final MedicalSpecialtyRepository medicalSpecialtyRepository;

    public DoctorServiceImpl(
            DoctorRepository doctorRepository,
            MedicalSpecialtyRepository medicalSpecialtyRepository,
            DepartmentRepository departmentRepository
    ) {
        this.doctorRepository = doctorRepository;
        this.departmentRepository = departmentRepository;
        this.medicalSpecialtyRepository = medicalSpecialtyRepository;
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
    public Optional<DoctorDto> findById(Long id) {
        Objects.requireNonNull(id);

        Optional<Doctor> doctor = doctorRepository.findById(id);

        return doctor.map(DoctorMapper.INSTANCE::doctorToDoctorDto);
    }

    @Override
    public List<DoctorExaminationScheduleDto> getExaminationScheduleList(Long doctorId) {
        Objects.requireNonNull(doctorId);

        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if (optionalDoctor.isEmpty()) {
            throw new EntityNotFoundException(doctorId);
        }

        Doctor doctor = optionalDoctor.get();

        return DoctorMapper.INSTANCE.doctorExaminationScheduleToDoctorExaminationScheduleDtoList(
                doctor.getExaminationSchedule()
        );
    }

    @Override
    public Optional<DoctorExaminationScheduleDto> getExaminationSchedule(Long doctorId, Long id) {
        Objects.requireNonNull(doctorId);
        Objects.requireNonNull(id);

        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if (optionalDoctor.isEmpty()) {
            throw new EntityNotFoundException(doctorId);
        }

        Doctor doctor = optionalDoctor.get();

        return doctor.getExaminationSchedule().
                stream().
                filter(s -> s.getId().equals(id) && s.getDoctor().getId().equals(doctorId)).
                findAny().
                map(DoctorMapper.INSTANCE::doctorExaminationScheduleToDoctorExaminationScheduleDto);
    }

    @Override
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

        Optional<Department> optionalDepartment = departmentRepository.findById(doctorUpdateDto.getDepartmentId());
        if (optionalDepartment.isEmpty()) {
            throw new EntityNotFoundException(
                    MessageFormat.format("Department with id {0} not found", doctorUpdateDto.getDepartmentId())
            );
        }

        Optional<MedicalSpecialty> optionalMedicalSpecialty = medicalSpecialtyRepository.findById(doctorUpdateDto.getSpecialtyId());
        if (optionalMedicalSpecialty.isEmpty()) {
            throw new EntityNotFoundException(MessageFormat.format("Medical specialty with id {0} not found", doctorUpdateDto.getSpecialtyId()));
        }

        Doctor doctor = optionalDoctor.get();
        Department department = optionalDepartment.get();
        MedicalSpecialty medicalSpecialty = optionalMedicalSpecialty.get();

        DoctorMapper.INSTANCE.updateDoctorFromDoctorUpdateDto(doctorUpdateDto, doctor);
        doctor.setDepartment(department);
        doctor.setSpecialty(medicalSpecialty);

        doctor = doctorRepository.save(doctor);

        return DoctorMapper.INSTANCE.doctorToDoctorDto(doctor);
    }

    @Override
    public DoctorExaminationScheduleDto createExaminationSchedule(
            Long doctorId,
            DoctorExaminationScheduleCreateDto doctorExaminationScheduleCreateDto
    ) {
        Objects.requireNonNull(doctorId);
        Objects.requireNonNull(doctorExaminationScheduleCreateDto);

        LOGGER.debug("Creating doctor examination schedule");

        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if (optionalDoctor.isEmpty()) {
            throw new EntityNotFoundException(doctorId);
        }

        Doctor doctor = optionalDoctor.get();

        DoctorExaminationSchedule doctorExaminationSchedule =
                DoctorMapper.INSTANCE.DoctorExaminationScheduleCreateDtoToDoctorExaminationSchedule(
                        doctorExaminationScheduleCreateDto
                );

        doctorExaminationSchedule.setDoctor(doctor);
        doctor.addExaminationSchedule(doctorExaminationSchedule);

        doctorRepository.save(doctor);

        return DoctorMapper.INSTANCE.doctorExaminationScheduleToDoctorExaminationScheduleDto(doctorExaminationSchedule);
    }

    @Override
    public DoctorExaminationScheduleDto updateExaminationSchedule(
            Long doctorId,
            Long id,
            DoctorExaminationScheduleUpdateDto doctorExaminationScheduleUpdateDto
    ) {
        Objects.requireNonNull(doctorId);
        Objects.requireNonNull(id);
        Objects.requireNonNull(doctorExaminationScheduleUpdateDto);

        LOGGER.debug("Updating doctor examination schedule");

        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if (optionalDoctor.isEmpty()) {
            throw new EntityNotFoundException(doctorId);
        }

        Doctor doctor = optionalDoctor.get();

        Optional<DoctorExaminationSchedule> optionalDoctorExaminationSchedule = doctor.getExaminationSchedule().
                stream().
                filter(s -> s.getId().equals(id) && s.getDoctor().getId().equals(doctorId)).
                findAny();

        if (optionalDoctorExaminationSchedule.isEmpty()) {
            throw new EntityNotFoundException(
                    MessageFormat.format("Examination schedule with id {0} for doctor id {1} not found", id, doctorId)
            );
        }

        DoctorExaminationSchedule doctorExaminationSchedule = optionalDoctorExaminationSchedule.get();

        DoctorMapper.INSTANCE.updateDoctorExaminationScheduleFromDoctorExaminationScheduleUpdateDto(
                doctorExaminationScheduleUpdateDto,
                doctorExaminationSchedule
        );

        doctorRepository.save(doctor);

        return DoctorMapper.INSTANCE.doctorExaminationScheduleToDoctorExaminationScheduleDto(doctorExaminationSchedule);
    }
}
