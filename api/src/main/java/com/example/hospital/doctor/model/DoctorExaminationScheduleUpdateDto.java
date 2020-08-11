package com.example.hospital.doctor.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.example.hospital.common.validator.FirstNumberLessThanSecondNumber;

@FirstNumberLessThanSecondNumber(
        first = "startHour",
        second = "endHour",
        message = "Start hour must be before end hour"
)
public class DoctorExaminationScheduleUpdateDto {

    @Min(value = 1, message = "Day number of week cannot be less than 1")
    @Max(value = 7, message = "Day number of week cannot be greater than 7")
    private Integer dayNumberOfWeek;

    @Min(value = 0, message = "Start hour cannot be less than 0")
    @Max(value = 23, message = "Start hour cannot be greater than 23")
    private Integer startHour;

    @Min(value = 0, message = "End hour cannot be less than 0")
    @Max(value = 23, message = "End hour cannot be greater than 23")
    private Integer endHour;

    @Min(value = 20, message = "Duration minutes cannot be less than 20")
    @Max(value = 60, message = "Duration minutes cannot be greater than 60")
    private Integer durationMinutes;

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
