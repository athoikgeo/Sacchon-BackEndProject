package router;

import org.restlet.Application;
import org.restlet.routing.Router;
import resource.*;

public class CustomRouter {

    private Application application;

    public CustomRouter(Application application) {
        this.application = application;
    }

    public Router publicResources() {
        Router router = new Router();
        router.attach("/ping", PingServerResource.class); //get

        /**
         * The registration (GET, POST)
         */
        router.attach("/register/doctor", DoctorRegisterResource.class);
        router.attach("/register/patient", PatientRegisterResource.class);

        /**
         * Can ge used only by the reporter. (GET, POST)
         */
        router.attach("/doctor", DoctorListResource.class);
        router.attach("/patient", PatientListResource.class);
        router.attach("/reporter", ReporterListResource.class);

        /**
         * Can ge used by the reporter and the user (doctor, patient and reporter respectively). (GET, PUT, DELETE)
         */
        router.attach("/doctor/{doctorId}", DoctorResource.class);
        router.attach("/patient/{patientId}", PatientResource.class);
        router.attach("/reporter/{reporterId}", ReporterResource.class);

        /**
         * Measurements (GET, POST)
         */
        router.attach("/measurement", MeasurementListResource.class);

        /**
         * Measurements (GET, PUT, DELETE)
         */
        router.attach("/measurement/{measurementId}", MeasurementResource.class);//get, put, delete (patient)

        /**
         * Measurement (GET)
         */
        router.attach("/patient/{patientId}/measurement", PatientMeasurementsResource.class);//get,  (reporter, doctor,patient)

        /**
         * ConsultationS (GET, POST)
         */
        router.attach("/consultation", ConsultationListResource.class); //get, post (reporter)

        /**
         * ConsultationS (GET, PUT, DELETE)
         */
        router.attach("/consultation/{consultationId}", ConsultationResource.class);//get, put, delete  (patient)

        /**
         * Consultation (GET)
         */
        router.attach("/patient/{patientId}/consultation", PatientConsultationsResource.class);//get (patient,doctor)

        return router;
    }


    public Router protectedResources() {
        Router router = new Router();

        return router;
    }
}