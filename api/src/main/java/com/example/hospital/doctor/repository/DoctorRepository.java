package com.example.hospital.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospital.doctor.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
