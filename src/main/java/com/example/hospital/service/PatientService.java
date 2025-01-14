package com.example.hospital.service;

import com.example.hospital.entities.Doctor;
import com.example.hospital.entities.Patient;
import com.example.hospital.repositories.DoctorRepository;
import com.example.hospital.repositories.PatientRepository;
import com.example.hospital.requests.PatientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(int id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.orElse(null);
    }

    public Patient addPatient(PatientRequest patientRequest) {
        Optional<Doctor> doctor = doctorRepository.findById(patientRequest.getDoctorId());
        if (doctor.isEmpty()) {
            return null;  // Return null if doctor does not exist
        }
        Patient patient = new Patient();
        patient.setName(patientRequest.getName());
        patient.setDisease(patientRequest.getDisease());
        patient.setAge(patientRequest.getAge());
        patient.setDoctor(doctor.get());
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(int id) {
        patientRepository.deleteById(id);
    }
}
