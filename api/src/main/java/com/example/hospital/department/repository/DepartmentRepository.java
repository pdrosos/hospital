package com.example.hospital.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hospital.department.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
