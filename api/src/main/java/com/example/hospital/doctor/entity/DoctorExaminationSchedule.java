package com.example.hospital.doctor.entity;

import com.example.hospital.common.entity.AuditableEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="doctor_examination_schedules")
public class DoctorExaminationSchedule extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @NotNull
    private Integer dayNumberOfWeek;

    @NotNull
    private Integer startHour;

    @NotNull
    private Integer endHour;

    @NotNull
    private Integer durationMinutes;

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Integer getDayNumberOfWeek() {
        return dayNumberOfWeek;
    }

    public void setDayNumberOfWeek(Integer dayNumberOfWeek) {
        this.dayNumberOfWeek = dayNumberOfWeek;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public Integer getEndHour() {
        return endHour;
    }

    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

}
