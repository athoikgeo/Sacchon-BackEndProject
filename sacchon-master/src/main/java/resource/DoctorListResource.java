package resource;

import jpautil.JpaUtil;
import model.Doctor;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import repository.DoctorRepository;
import representation.DoctorRepresentation;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DoctorListResource extends ServerResource {

    @Get("json")
    public List<DoctorRepresentation> getDoctors() {

        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);

        List<Doctor> doctors = doctorRepository.findAll();
        em.close();

        List<DoctorRepresentation> doctorRepresentationList =
                doctors.stream()
                        .map(DoctorRepresentation::new)
                        .collect(toList());

        return doctorRepresentationList;
    }

    @Post("json")
    public DoctorRepresentation add(DoctorRepresentation doctorRepresentationIn) {

        if (doctorRepresentationIn == null) return null;
        if (doctorRepresentationIn.getFirstName() == null) return null;
        if (doctorRepresentationIn.getLastName() == null) return null;
        if (doctorRepresentationIn.getPassword() == null) return null;
        if (doctorRepresentationIn.getUsername() == null) return null;
        if (doctorRepresentationIn.getRole() == null) return null;
        Doctor doctor = doctorRepresentationIn.createDoctor();
        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        doctorRepository.save(doctor);
        DoctorRepresentation p = new DoctorRepresentation(doctor);
        return doctorRepresentationIn;
    }
}
