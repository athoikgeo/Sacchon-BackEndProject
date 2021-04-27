package resource;

import jpautil.JpaUtil;
import model.Doctor;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import repository.DoctorRepository;
import representation.DoctorRepresentation;

import javax.persistence.EntityManager;

public class DoctorResource extends ServerResource {

    private int doctorId;

    @Override
    protected void doInit() {
        doctorId = Integer.parseInt(getAttribute("doctorId"));
    }

    @Get("json")
    public DoctorRepresentation getDoctor() {
        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        Doctor doctor = doctorRepository.read(doctorId);
        DoctorRepresentation doctorRepresentation2 = new DoctorRepresentation(doctor);
        em.close();
        return doctorRepresentation2;
    }

    @Put("json")
    public DoctorRepresentation putDoctor(DoctorRepresentation doctorRepresentation) {
        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        Doctor doctor = doctorRepository.read(doctorId);
        doctor.setPassword(doctorRepresentation.getPassword());
        doctor.setUsername(doctorRepresentation.getUsername());
        doctor.setLastName(doctorRepresentation.getLastName());
        doctor.setFirstName(doctorRepresentation.getFirstName());
        doctorRepository.save(doctor);
        DoctorRepresentation doctorRepresentation2 = new DoctorRepresentation(doctor);
        em.close();
        return doctorRepresentation2;
    }

    @Delete("txt")
    public boolean deleteDoctor() {
        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        return doctorRepository.delete(doctorId);
    }

}
