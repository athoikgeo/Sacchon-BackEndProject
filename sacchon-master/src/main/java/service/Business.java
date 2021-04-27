package service;

import model.Consultation;
import model.Doctor;
import model.Measurement;
import model.Patient;
import repository.ConsultationRepository;
import repository.DoctorRepository;
import repository.MeasurementRepository;
import repository.PatientRepository;

import javax.persistence.EntityManager;
import java.util.Date;

public class Business {

    public static void createData(EntityManager em) {

        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        Measurement measurement = new Measurement();
        measurement.setCarbIn(12.5f);
        measurement.setGlucoseLevel(3.4f);
        measurement.setDate(new Date());

        System.out.println(measurement);

        System.out.println(measurement);
        DoctorRepository doctorRepository = new DoctorRepository(em);
        Doctor doctor = new Doctor();

        doctor.setFirstName("George");
        doctor.setLastName("j");
        doctor.setLastName("sedf");
        doctor.setLastName("Ask");
        doctor.setPassword("vive");
        System.out.println(doctor);
        doctorRepository.save(doctor);
        System.out.println(doctor);

        Patient patient = new Patient();
        PatientRepository patientRepository = new PatientRepository(em);
        patient.setPassword("fgdtfh");

        patient.setFirstName("Georgia");
        patient.setLastName("P");
        patient.setLastName("DFX");
        patient.setLastName("AFGsk");

        patient.setUsername("fret");
        patient.setPassword("ABED");
        measurement.setPatientId(patient);

        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        Consultation consultation = new Consultation();
        consultation.setDoctorId(doctor);
        consultation.setPatientId(patient);
        consultation.setDate(new Date());
        consultation.setDosage(4.5f);
        consultation.setMedication("Deon");
        consultationRepository.save(consultation);
        patientRepository.save(patient);
        measurementRepository.save(measurement);
    }
}
