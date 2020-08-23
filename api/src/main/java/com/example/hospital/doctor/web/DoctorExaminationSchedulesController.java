package com.example.hospital.doctor.web;

import javax.validation.Valid;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import com.example.hospital.doctor.service.DoctorExaminationScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.hospital.common.error.EntityNotFoundException;
import com.example.hospital.common.web.Response;
import com.example.hospital.doctor.model.DoctorExaminationScheduleDto;
import com.example.hospital.doctor.model.DoctorExaminationScheduleCreateDto;
import com.example.hospital.doctor.model.DoctorExaminationScheduleUpdateDto;

@RestController
public class DoctorExaminationSchedulesController implements DoctorsNamespace {

    private final DoctorExaminationScheduleService doctorExaminationScheduleService;

    public DoctorExaminationSchedulesController(DoctorExaminationScheduleService doctorExaminationScheduleService) {
        this.doctorExaminationScheduleService = doctorExaminationScheduleService;
    }

    @GetMapping("/{doctorId}/examination-schedule")
    public List<DoctorExaminationScheduleDto> getDoctorExaminationScheduleList(@PathVariable Long doctorId) {
        return doctorExaminationScheduleService.getDoctorExaminationScheduleList(doctorId);
    }

    @GetMapping("/{doctorId}/examination-schedule/{id}")
    public ResponseEntity<? extends Object> getDoctorExaminationSchedule(@PathVariable Long doctorId, @PathVariable Long id) {
        try {
            Optional<DoctorExaminationScheduleDto> doctorExaminationScheduleDto =
                    doctorExaminationScheduleService.getDoctorExaminationSchedule(doctorId, id);

            return Response.of(
                    doctorExaminationScheduleDto,
                    MessageFormat.format("Examination schedule with id {0} for doctor id {1} not found", id, doctorId)
            );
        } catch (EntityNotFoundException ex) {
            return Response.notFound(doctorId);
        }
    }

    @PostMapping("/{doctorId}/examination-schedule")
    public ResponseEntity<? extends Object> createDoctorExaminationSchedule(
            @PathVariable Long doctorId,
            @Valid @RequestBody DoctorExaminationScheduleCreateDto doctorExaminationScheduleCreateDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return Response.unprocessableEntity(bindingResult.getFieldErrors());
        }

        try {
            DoctorExaminationScheduleDto doctorExaminationScheduleDto = doctorExaminationScheduleService.createDoctorExaminationSchedule(
                    doctorId,
                    doctorExaminationScheduleCreateDto
            );

            return Response.created(doctorExaminationScheduleDto);
        } catch (EntityNotFoundException ex) {
            return Response.notFound(doctorId);
        }
    }

    @PutMapping("/{doctorId}/examination-schedule/{id}")
    public ResponseEntity<? extends Object> updateDoctorExaminationSchedule(
            @PathVariable Long doctorId,
            @PathVariable Long id,
            @Valid @RequestBody DoctorExaminationScheduleUpdateDto doctorExaminationScheduleUpdateDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return Response.unprocessableEntity(bindingResult.getFieldErrors());
        }

        try {
            DoctorExaminationScheduleDto doctorExaminationScheduleDto = doctorExaminationScheduleService.updateDoctorExaminationSchedule(
                    doctorId,
                    id,
                    doctorExaminationScheduleUpdateDto
            );

            return ResponseEntity.ok(doctorExaminationScheduleDto);
        } catch (EntityNotFoundException ex) {
            return Response.notFound(id);
        }
    }

    @DeleteMapping("/{doctorId}/examination-schedule/{id}")
    public ResponseEntity<? extends Object> deleteMedicalSpecialty(@PathVariable Long doctorId, @PathVariable Long id) {
        try {
            DoctorExaminationScheduleDto doctorExaminationScheduleDto =
                    doctorExaminationScheduleService.deleteDoctorExaminationSchedule(doctorId, id);

            return ResponseEntity.ok(doctorExaminationScheduleDto);
        } catch (EntityNotFoundException ex) {
            return Response.notFound(id);
        }
    }
}
