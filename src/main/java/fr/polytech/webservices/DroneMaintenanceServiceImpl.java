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
    public boolean chargeDrone(String droneId) throws Exception {

        if(droneReviewer.setDroneInCharge(droneId)){
            System.out.println("Set drone "+droneId+ " in charge ! ");
            return true;
        }
        else{
            System.out.println("Drone "+droneId+ " doesn't exist ! ");
            return false;
        }
    }

    @Override
    public boolean reviewDrone(String droneId) throws Exception {

        if(droneReviewer.putDroneInRevision(droneId)){
            System.out.println("Set drone "+droneId+ " in review  ! ");
            return true;
        }
        else{
            System.out.println("Drone "+droneId+ " doesn't exist ! ");
            return false;
        }
    }
}
