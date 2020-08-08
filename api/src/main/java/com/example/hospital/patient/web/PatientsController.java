package com.example.hospital.patient.web;

import java.util.List;
import java.util.Optional;

import com.example.hospital.common.web.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital.patient.service.PatientService;
import com.example.hospital.patient.model.PatientDto;

@RestController
@RequestMapping("/patients")
public class PatientsController {

    private final PatientService patientService;

    public PatientsController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<PatientDto> getPatients() {
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends Object> getPatient(@PathVariable Long id) {
        Optional<PatientDto> patientDto = patientService.findById(id);

        return Response.of(patientDto, id);
    }

}
