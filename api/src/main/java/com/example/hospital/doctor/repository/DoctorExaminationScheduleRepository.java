package com.example.hospital.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospital.doctor.entity.DoctorExaminationSchedule;

import java.util.Optional;

@Repository
public interface DoctorExaminationScheduleRepository extends JpaRepository<DoctorExaminationSchedule, Long> {
    Optional<DoctorExaminationSchedule> findOneByDoctorIdAndId(Long doctorId, Long id);
}
