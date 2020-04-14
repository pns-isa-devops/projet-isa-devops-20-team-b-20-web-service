package fr.polytech.webservices;

import fr.polytech.entities.Delivery;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/drone-maintenance")
public interface DroneMaintenanceService {

    /**
     * Set the drone with the id drone_id in charge mode
     */
    @WebMethod
    void chargeDrone(@WebParam(name = "drone_id") String droneId) throws Exception;


}
