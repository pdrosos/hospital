package com.example.hospital.doctor.service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.hospital.common.error.EntityNotFoundException;
import com.example.hospital.doctor.entity.Doctor;
import com.example.hospital.doctor.entity.DoctorExaminationSchedule;
import com.example.hospital.doctor.mapper.DoctorMapper;
import com.example.hospital.doctor.model.DoctorExaminationScheduleCreateDto;
import com.example.hospital.doctor.model.DoctorExaminationScheduleDto;
import com.example.hospital.doctor.model.DoctorExaminationScheduleUpdateDto;
import com.example.hospital.doctor.repository.DoctorExaminationScheduleRepository;
import com.example.hospital.doctor.repository.DoctorRepository;

@Service
public class DoctorExaminationScheduleServiceImpl implements DoctorExaminationScheduleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);

    private final DoctorRepository doctorRepository;
    private final DoctorExaminationScheduleRepository doctorExaminationScheduleRepository;

    public DoctorExaminationScheduleServiceImpl(
            DoctorRepository doctorRepository,
            DoctorExaminationScheduleRepository doctorExaminationScheduleRepository
    ) {
        this.doctorRepository = doctorRepository;
        this.doctorExaminationScheduleRepository = doctorExaminationScheduleRepository;
    }

    @Override
    public List<DoctorExaminationScheduleDto> getDoctorExaminationScheduleList(Long doctorId) {
        Objects.requireNonNull(doctorId);

        Doctor doctor = checkDoctorExists(doctorId);

        return DoctorMapper.INSTANCE.doctorExaminationScheduleToDoctorExaminationScheduleDtoList(
                doctor.getExaminationSchedule()
        );
    }

    @Override
    public Optional<DoctorExaminationScheduleDto> getDoctorExaminationSchedule(Long doctorId, Long id) {
        Objects.requireNonNull(doctorId);
        Objects.requireNonNull(id);

        Doctor doctor = checkDoctorExists(doctorId);

        return doctor.getExaminationSchedule().
                stream().
                filter(s -> s.getId().equals(id) && s.getDoctor().getId().equals(doctorId)).
                findAny().
                map(DoctorMapper.INSTANCE::doctorExaminationScheduleToDoctorExaminationScheduleDto);

//        return doctorExaminationScheduleRepository.findOneByDoctorIdAndId(doctorId, id).
//                map(DoctorMapper.INSTANCE::doctorExaminationScheduleToDoctorExaminationScheduleDto);
    }

    @Override
    public DoctorExaminationScheduleDto createDoctorExaminationSchedule(
            Long doctorId,
            DoctorExaminationScheduleCreateDto doctorExaminationScheduleCreateDto
    ) {
        Objects.requireNonNull(doctorId);
        Objects.requireNonNull(doctorExaminationScheduleCreateDto);

        LOGGER.debug("Creating doctor examination schedule");

        Doctor doctor = checkDoctorExists(doctorId);

        DoctorExaminationSchedule doctorExaminationSchedule =
                DoctorMapper.INSTANCE.DoctorExaminationScheduleCreateDtoToDoctorExaminationSchedule(
                        doctorExaminationScheduleCreateDto
                );

        // doctor.addExaminationSchedule(doctorExaminationSchedule);
        doctorExaminationSchedule.setDoctor(doctor);

        // doctorRepository.save(doctor);
        doctorExaminationSchedule = doctorExaminationScheduleRepository.save(doctorExaminationSchedule);

        return DoctorMapper.INSTANCE.doctorExaminationScheduleToDoctorExaminationScheduleDto(doctorExaminationSchedule);
    }

    @Override
    public DoctorExaminationScheduleDto updateDoctorExaminationSchedule(
            Long doctorId,
            Long id,
            DoctorExaminationScheduleUpdateDto doctorExaminationScheduleUpdateDto
    ) {
        Objects.requireNonNull(doctorId);
        Objects.requireNonNull(id);
        Objects.requireNonNull(doctorExaminationScheduleUpdateDto);

        LOGGER.debug("Updating doctor examination schedule");

        Doctor doctor = checkDoctorExists(doctorId);

        DoctorExaminationSchedule doctorExaminationSchedule = getDoctorExaminationSchedule(doctor, id);

        DoctorMapper.INSTANCE.updateDoctorExaminationScheduleFromDoctorExaminationScheduleUpdateDto(
                doctorExaminationScheduleUpdateDto,
                doctorExaminationSchedule
        );

        // doctorRepository.save(doctor);
        doctorExaminationSchedule = doctorExaminationScheduleRepository.save(doctorExaminationSchedule);

        return DoctorMapper.INSTANCE.doctorExaminationScheduleToDoctorExaminationScheduleDto(doctorExaminationSchedule);
    }

    @Override
    public DoctorExaminationScheduleDto deleteDoctorExaminationSchedule(Long doctorId, Long id) {
        Objects.requireNonNull(doctorId);
        Objects.requireNonNull(id);

        LOGGER.debug("Deleting doctor examination schedule");

        checkDoctorExists(doctorId);

        DoctorExaminationSchedule doctorExaminationSchedule = doctorExaminationScheduleRepository
                .findOneByDoctorIdAndId(doctorId, id).
                orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Examination schedule with id {0} for doctor with id {1} not found", id, doctorId)
                ));

        doctorExaminationScheduleRepository.delete(doctorExaminationSchedule);

        return DoctorMapper.INSTANCE.doctorExaminationScheduleToDoctorExaminationScheduleDto(doctorExaminationSchedule);
    }

    private Doctor checkDoctorExists(Long doctorId) throws EntityNotFoundException {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if (optionalDoctor.isEmpty()) {
            throw new EntityNotFoundException(doctorId);
        }

        return optionalDoctor.get();
    }

    private DoctorExaminationSchedule getDoctorExaminationSchedule(Long doctorId, Long id) {
        DoctorExaminationSchedule doctorExaminationSchedule = doctorExaminationScheduleRepository
                .findOneByDoctorIdAndId(doctorId, id).
                        orElseThrow(() -> new EntityNotFoundException(
                                MessageFormat.format("Examination schedule with id {0} for doctor with id {1} not found", id, doctorId)
                        ));

        return doctorExaminationSchedule;
    }
}
