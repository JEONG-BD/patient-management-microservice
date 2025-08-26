package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(PatientMapper::toDTO)
                .toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO dto){
        if (patientRepository.existsByEmail(dto.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email already exists "
            + dto.getEmail());
        }

        Patient newPatient = patientRepository.save(PatientMapper.toModel(dto));
        return PatientMapper.toDTO(newPatient);
    }
    
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO dto){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID : " + id.toString()));

        if (patientRepository.existsByEmailAndIdNot(dto.getEmail(), id)){
            throw new EmailAlreadyExistsException("A patient with this email already exists "
                    + dto.getEmail());
        }

        patient.setName(dto.getName());
        patient.setRegisteredDate(LocalDate.parse(dto.getRegisteredDate()));
        patient.setEmail(dto.getEmail());
        patient.setAddress(dto.getAddress());
        patient.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);

        return PatientMapper.toDTO(updatedPatient);
    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}
