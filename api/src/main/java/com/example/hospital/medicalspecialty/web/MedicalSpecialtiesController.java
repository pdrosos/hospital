package com.example.hospital.medicalspecialty.web;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.hospital.common.error.EntityNotFoundException;
import com.example.hospital.common.web.Response;
import com.example.hospital.medicalspecialty.model.MedicalSpecialtyCreateDto;
import com.example.hospital.medicalspecialty.model.MedicalSpecialtyDto;
import com.example.hospital.medicalspecialty.model.MedicalSpecialtyUpdateDto;
import com.example.hospital.medicalspecialty.service.MedicalSpecialtyService;

@RestController
@RequestMapping("/medical-specialties")
public class MedicalSpecialtiesController {

    private final MedicalSpecialtyService medicalSpecialtyService;

    public MedicalSpecialtiesController(MedicalSpecialtyService medicalSpecialtyService) {
        this.medicalSpecialtyService = medicalSpecialtyService;
    }

    @GetMapping
    public List<MedicalSpecialtyDto> getMedicalSpecialties() {
        return medicalSpecialtyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends Object> getMedicalSpecialty(@PathVariable Long id) {
        Optional<MedicalSpecialtyDto> medicalSpecialtyDto = medicalSpecialtyService.findById(id);

        return Response.of(medicalSpecialtyDto, id);
    }

    @PostMapping
    public ResponseEntity<? extends Object> createMedicalSpecialty(
            @Valid @RequestBody MedicalSpecialtyCreateDto medicalSpecialtyCreateDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return Response.unprocessableEntity(bindingResult.getFieldErrors());
        }

        MedicalSpecialtyDto medicalSpecialtyDto = medicalSpecialtyService.create(medicalSpecialtyCreateDto);

        return Response.created(medicalSpecialtyDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<? extends Object> updateMedicalSpecialty(
            @PathVariable Long id,
            @Valid @RequestBody MedicalSpecialtyUpdateDto medicalSpecialtyUpdateDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return Response.unprocessableEntity(bindingResult.getFieldErrors());
        }

        try {
            MedicalSpecialtyDto medicalSpecialtyDto = medicalSpecialtyService.update(id, medicalSpecialtyUpdateDto);

            return ResponseEntity.ok(medicalSpecialtyDto);
        } catch (EntityNotFoundException ex) {
            return Response.notFound(id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<? extends Object> deleteMedicalSpecialty(@PathVariable Long id) {
        try {
            MedicalSpecialtyDto medicalSpecialtyDto = medicalSpecialtyService.delete(id);

            return ResponseEntity.ok(medicalSpecialtyDto);
        } catch (EntityNotFoundException ex) {
            return Response.notFound(id);
        }
    }

}
