package resource;

import jpautil.JpaUtil;
import model.Consultation;
import model.Doctor;
import model.Patient;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import repository.ConsultationRepository;
import repository.DoctorRepository;
import repository.PatientRepository;
import representation.ConsultationRepresentation;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ConsultationListResource extends ServerResource {

    @Get("json")
    public List<ConsultationRepresentation> getConsultations() {
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        List<Consultation> consultations = consultationRepository.findAll();
        em.close();
        return consultations.stream()
                .map(ConsultationRepresentation::new)
                .collect(toList());
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

}
