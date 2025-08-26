package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getPatients(){
        List<PatientResponseDTO> patientsDto = patientService.getPatients();
        return ResponseEntity.ok().body(patientsDto);
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatiend(@Valid @RequestBody PatientRequestDTO dto){
        PatientResponseDTO patientDto = patientService.createPatient(dto);
        return ResponseEntity.ok().body(patientDto);
    }


}
