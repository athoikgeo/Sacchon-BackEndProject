package resource;

import jpautil.JpaUtil;
import model.Consultation;
import model.Doctor;
import model.Patient;
import org.restlet.resource.*;
import repository.ConsultationRepository;
import repository.DoctorRepository;
import repository.PatientRepository;
import representation.ConsultationRepresentation;

import javax.persistence.EntityManager;

public class ConsultationResource extends ServerResource {

    private int consultationId;

    @Override
    protected void doInit() {
        consultationId = Integer.parseInt(getAttribute("consultationId"));
    }

    @Get("json")
    public ConsultationRepresentation getConsultation() {
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        Consultation consultation = consultationRepository.read(consultationId);
        ConsultationRepresentation consultationRepresentation2 = new ConsultationRepresentation(consultation);
        em.close();
        return consultationRepresentation2;
    }

    @Post("json")
    public ConsultationRepresentation add(ConsultationRepresentation consultationRepresentation) {
        if (consultationRepresentation == null) return null;
        if (consultationRepresentation.getDate() == null) return null;
        if (consultationRepresentation.getDoctorId() == 0) return null;
        if (consultationRepresentation.getPatientId() == 0) return null;

        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        PatientRepository patientRepository = new PatientRepository(em);
        Patient patient = patientRepository.read(consultationRepresentation.getPatientId());
        DoctorRepository doctorRepository = new DoctorRepository(em);
        Doctor doctor = doctorRepository.read(consultationRepresentation.getDoctorId());

        Consultation consultation = consultationRepresentation.createConsultation();
        consultation.setMedication(consultationRepresentation.getMedication());
        consultation.setDosage(consultationRepresentation.getDosage());
        consultation.setDate(consultationRepresentation.getDate());

        consultation.setDoctorId(doctor);
        consultation.setPatientId(patient);
        consultationRepository.save(consultation);

        ConsultationRepresentation consultationRepresentation2 = new ConsultationRepresentation(consultation);
        em.close();
        return consultationRepresentation2;

    }

    @Put("json")
    public ConsultationRepresentation putConsultation(ConsultationRepresentation consultationRepresentation) {
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);

        Consultation consultation = consultationRepresentation.createConsultation();
        consultation.setMedication(consultationRepresentation.getMedication());
        consultation.setDosage(consultationRepresentation.getDosage());
        consultation.setDate(consultationRepresentation.getDate());
        consultationRepository.save(consultation);

        ConsultationRepresentation consultationRepresentation2 = new ConsultationRepresentation(consultation);
        em.close();
        return consultationRepresentation2;
    }

    @Delete("txt")
    public boolean deleteConsultation() {
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consaltationRepository = new ConsultationRepository(em);
        return consaltationRepository.delete(consultationId);
    }
}
