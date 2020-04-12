package fr.polytech.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/drone-maintenance")
public interface DroneMaintenanceService {

    /**
     * Adds a drone to the database.
     */
    @WebMethod
    void addDrone();

}
