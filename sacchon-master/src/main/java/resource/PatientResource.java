package resource;

import jpautil.JpaUtil;
import model.Patient;
import org.restlet.resource.*;
import repository.PatientRepository;
import representation.PatientRepresentation;

import javax.persistence.EntityManager;

public class PatientResource extends ServerResource {

    private int patientId;

    @Override
    protected void doInit() {
        patientId = Integer.parseInt(getAttribute("patientId"));
    }

    @Get("json")
    public PatientRepresentation getPatient() {
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        Patient patient = patientRepository.read(patientId);
        PatientRepresentation patientRepresentation2 = new PatientRepresentation(patient);
        em.close();
        return patientRepresentation2;
    }

    @Post("json")
    public PatientRepresentation add(PatientRepresentation patientRepresentation) {
        if (patientRepresentation == null) return null;
        if (patientRepresentation.getFirstName() == null) return null;
        if (patientRepresentation.getLastName() == null) return null;
        if (patientRepresentation.getPassword() == null) return null;
        if (patientRepresentation.getUsername() == null) return null;


        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);

        Patient patient = patientRepresentation.createPatient();
        patient.setPassword(patientRepresentation.getPassword());
        patient.setUsername(patientRepresentation.getUsername());
        patient.setLastName(patientRepresentation.getLastName());
        patient.setFirstName(patientRepresentation.getFirstName());
        patientRepository.save(patient);

        PatientRepresentation patientRepresentation2 = new PatientRepresentation(patient);
        em.close();
        return patientRepresentation2;
    }

    @Put("json")
    public PatientRepresentation putPatient(PatientRepresentation patientRepresentation) {
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        Patient patient = patientRepository.read(patientId);
        patient.setPassword(patientRepresentation.getPassword());
        patient.setUsername(patientRepresentation.getUsername());
        patient.setLastName(patientRepresentation.getLastName());
        patient.setFirstName(patientRepresentation.getFirstName());
        patientRepository.save(patient);
        PatientRepresentation patientRepresentation2 = new PatientRepresentation(patient);
        em.close();
        return patientRepresentation2;
    }

    @Delete("txt")
    public boolean deletePatient() {
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        return patientRepository.delete(patientId);
    }

}
