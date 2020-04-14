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
    boolean chargeDrone(@WebParam(name = "drone_id") String droneId) throws Exception;

    /**
     * Set the drone with the id drone_id in review mode
     */
    @WebMethod
    boolean reviewDrone(@WebParam(name = "drone_id") String droneId) throws Exception;

    /**
     * Set the drone with the id drone_id available
     */
    @WebMethod
    boolean setAvailableDrone(@WebParam(name = "drone_id") String droneId) throws Exception;


}
