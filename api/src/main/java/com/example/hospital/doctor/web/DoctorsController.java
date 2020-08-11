package com.example.hospital.doctor.web;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import com.example.hospital.common.error.EntityNotFoundException;
import com.example.hospital.common.web.Response;
import com.example.hospital.doctor.model.DoctorDto;
import com.example.hospital.doctor.model.DoctorUpdateDto;
import com.example.hospital.doctor.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class DoctorsController implements DoctorsNamespace {

    private final DoctorService doctorService;

    public DoctorsController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<DoctorDto> getDoctors() {
        return doctorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends Object> getDoctor(@PathVariable Long id) {
        Optional<DoctorDto> doctorDto = doctorService.findById(id);

        return Response.of(doctorDto, id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<? extends Object> updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody DoctorUpdateDto doctorUpdateDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return Response.unprocessableEntity(bindingResult.getFieldErrors());
        }

        try {
            DoctorDto doctorDto = doctorService.update(id, doctorUpdateDto);

            return ResponseEntity.ok(doctorDto);
        } catch (EntityNotFoundException ex) {
            return Response.notFound(id);
        }
    }

}
