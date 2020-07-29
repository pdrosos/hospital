package com.example.hospital.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospital.patient.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
