package fr.polytech.webservices;


import fr.polytech.dronepark.components.DroneReviewer;
import fr.polytech.dronepark.exception.DroneNotFoundException;
import fr.polytech.dronepark.exception.InvalidDroneIDException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/drone-maintenance")
@Stateless(name = "DroneMaintenanceWS")
public class DroneMaintenanceServiceImpl implements DroneMaintenanceService {

    @EJB
    private DroneReviewer droneReviewer;

    @Override
    public void addDrone(String droneId) throws InvalidDroneIDException {
        this.droneReviewer.addDrone(droneId);
    }

    @Override
    public void chargeDrone(String droneId) throws DroneNotFoundException{

        droneReviewer.setDroneInCharge(droneId);
    }

    @Override
    public void reviewDrone(String droneId) throws DroneNotFoundException {

        droneReviewer.putDroneInRevision(droneId);
    }

    @Override
    public void setAvailableDrone(String droneId) throws DroneNotFoundException {
        droneReviewer.setDroneAvailable(droneId);

    }
}
