package fr.polytech.webservices;

import fr.polytech.dronepark.components.DroneReviewer;
import fr.polytech.entities.Drone;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/drone-maintenance")
@Stateless(name = "DroneMaintenanceWS")
public class DroneMaintenanceServiceImpl implements DroneMaintenanceService {

    @EJB
    private DroneReviewer droneReviewer;

    @Override
    public void addDrone() {
        droneReviewer.addDrone();


    }

}
