package fr.polytech.webservices;


import fr.polytech.dronepark.components.DroneReviewer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/drone-maintenance")
@Stateless(name = "DroneMaintenanceWS")
public class DroneMaintenanceServiceImpl implements DroneMaintenanceService{

    @EJB
    private DroneReviewer droneReviewer;

    @Override
    public void chargeDrone(String droneId) throws Exception {

        if(droneReviewer.setDroneInCharge(droneId)){
            System.out.println("Set drone "+droneId+ " in charge ! ");
        }
        else{
            System.out.println("Drone "+droneId+ " doesn't exist ! ");
        }
    }
}
