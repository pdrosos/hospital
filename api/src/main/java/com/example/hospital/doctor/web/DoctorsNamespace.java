package com.example.hospital.doctor.web;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(DoctorsNamespace.URI_DOCTORS)
public interface DoctorsNamespace {
    String URI_DOCTORS = "/doctors";
}
