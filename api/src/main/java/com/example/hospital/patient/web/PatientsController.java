package com.example.hospital.patient.web;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.hospital.common.error.EntityNotFoundException;
import com.example.hospital.common.web.Response;
import com.example.hospital.patient.service.PatientService;
import com.example.hospital.patient.model.PatientDto;
import com.example.hospital.patient.model.PatientUpdateDto;

import javax.validation.Valid;

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

    @PutMapping("/{id}")
    public ResponseEntity<? extends Object> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody PatientUpdateDto patientUpdateDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return Response.unprocessableEntity(bindingResult.getFieldErrors());
        }

        try {
            PatientDto medicalSpecialtyDto = patientService.update(id, patientUpdateDto);

            return ResponseEntity.ok(medicalSpecialtyDto);
        } catch (EntityNotFoundException ex) {
            return Response.notFound(id);
        }
    }
}
