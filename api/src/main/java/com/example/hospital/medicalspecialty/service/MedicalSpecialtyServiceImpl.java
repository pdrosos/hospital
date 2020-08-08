package com.example.hospital.medicalspecialty.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.hospital.common.error.EntityNotFoundException;
import com.example.hospital.medicalspecialty.model.MedicalSpecialtyCreateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.hospital.medicalspecialty.entity.MedicalSpecialty;
import com.example.hospital.medicalspecialty.mapper.MedicalSpecialtyMapper;
import com.example.hospital.medicalspecialty.model.MedicalSpecialtyDto;
import com.example.hospital.medicalspecialty.model.MedicalSpecialtyUpdateDto;
import com.example.hospital.medicalspecialty.repository.MedicalSpecialtyRepository;

@Service
public class MedicalSpecialtyServiceImpl implements MedicalSpecialtyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalSpecialtyService.class);

    private final MedicalSpecialtyRepository medicalSpecialtyRepository;

    public MedicalSpecialtyServiceImpl(MedicalSpecialtyRepository medicalSpecialtyRepository) {
        this.medicalSpecialtyRepository = medicalSpecialtyRepository;
    }

    @Override
    public List<MedicalSpecialtyDto> findAll() {
        // todo pageable
        return medicalSpecialtyRepository.findAll().
                stream().
                map(MedicalSpecialtyMapper.INSTANCE::medicalSpecialtyToMedicalSpecialtyDto).
                collect(Collectors.toList());
    }

    @Override
    public Optional<MedicalSpecialtyDto> findById(long id) {
        Optional<MedicalSpecialty> medicalSpecialty = medicalSpecialtyRepository.findById(id);

        return medicalSpecialty.map(MedicalSpecialtyMapper.INSTANCE::medicalSpecialtyToMedicalSpecialtyDto);
    }

    public MedicalSpecialtyDto create(MedicalSpecialtyCreateDto medicalSpecialtyCreateDto) {
        Objects.requireNonNull(medicalSpecialtyCreateDto);

        LOGGER.debug("Creating medical specialty");

        MedicalSpecialty medicalSpecialty =
                MedicalSpecialtyMapper.INSTANCE.medicalSpecialtyCreateDtoToMedicalSpecialty(medicalSpecialtyCreateDto);

        medicalSpecialty = medicalSpecialtyRepository.save(medicalSpecialty);

        return MedicalSpecialtyMapper.INSTANCE.medicalSpecialtyToMedicalSpecialtyDto(medicalSpecialty);
    }

    public MedicalSpecialtyDto update(
            Long id,
            MedicalSpecialtyUpdateDto medicalSpecialtyUpdateDto
    ) throws EntityNotFoundException {
        Objects.requireNonNull(id);
        Objects.requireNonNull(medicalSpecialtyUpdateDto);

        LOGGER.debug("Updating medical specialty");

        Optional<MedicalSpecialty> optionalMedicalSpecialty = medicalSpecialtyRepository.findById(id);

        if (optionalMedicalSpecialty.isEmpty()) {
            throw new EntityNotFoundException(id);
        }

        MedicalSpecialty medicalSpecialty = optionalMedicalSpecialty.get();

        MedicalSpecialtyMapper.INSTANCE.updateMedicalSpecialtyFromMedicalSpecialtyUpdateDto(medicalSpecialtyUpdateDto, medicalSpecialty);
        medicalSpecialty = medicalSpecialtyRepository.save(medicalSpecialty);

        return MedicalSpecialtyMapper.INSTANCE.medicalSpecialtyToMedicalSpecialtyDto(medicalSpecialty);
    }

    public MedicalSpecialtyDto delete(Long id)  {
        Objects.requireNonNull(id);

        LOGGER.debug("Deleting medical specialty");

        Optional<MedicalSpecialty> optionalMedicalSpecialty = medicalSpecialtyRepository.findById(id);

        if (optionalMedicalSpecialty.isEmpty()) {
            throw new EntityNotFoundException(id);
        }

        medicalSpecialtyRepository.deleteById(id);

        MedicalSpecialty medicalSpecialty = optionalMedicalSpecialty.get();

        return MedicalSpecialtyMapper.INSTANCE.medicalSpecialtyToMedicalSpecialtyDto(medicalSpecialty);
    }
}
