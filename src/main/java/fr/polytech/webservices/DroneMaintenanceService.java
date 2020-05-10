package fr.polytech.webservices;

import fr.polytech.dronepark.exception.DroneCannotChangeStateException;
import fr.polytech.dronepark.exception.DroneNotFoundException;
import fr.polytech.dronepark.exception.InvalidDroneIDException;
import fr.polytech.entities.Drone;
import fr.polytech.webservices.interceptors.StatCharge;
import fr.polytech.webservices.interceptors.StatMaintenance;

import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/drone-maintenance")
public interface DroneMaintenanceService {

    /**
     * Adds a drone to the database.
     */
    @WebMethod
    void addDrone(@WebParam(name = "drone_id") String droneId) throws InvalidDroneIDException;

    /**
     * Gets all drones.
     * @return a list of drones.
     */
    @WebMethod
    List<Drone> getDrones();

    /**
     * Set the drone with the id drone_id in charge mode
     */
    @WebMethod
    @Interceptors({StatCharge.class})
    void chargeDrone(@WebParam(name = "drone_id") String droneId) throws DroneNotFoundException, DroneCannotChangeStateException;

    /**
     * Set the drone with the id drone_id in review mode
     */
    @WebMethod
    @Interceptors({StatMaintenance.class})
    void reviewDrone(@WebParam(name = "drone_id") String droneId) throws DroneNotFoundException, DroneCannotChangeStateException;

    /**
     * Set the drone with the id drone_id available
     */
    @WebMethod
    void setAvailableDrone(@WebParam(name = "drone_id") String droneId) throws DroneNotFoundException;


}
