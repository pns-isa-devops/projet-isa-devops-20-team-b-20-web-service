package fr.polytech.webservices;


import fr.polytech.dronepark.components.DroneReviewer;

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

    @Override
    public boolean chargeDrone(String droneId) {

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
    public boolean reviewDrone(String droneId) {

        if(droneReviewer.putDroneInRevision(droneId)){
            System.out.println("Set drone "+droneId+ " in review  ! ");
            return true;
        }
        else{
            System.out.println("Drone "+droneId+ " doesn't exist ! ");
            return false;
        }
    }

    @Override
    public boolean setAvailableDrone(String droneId) {

        if(droneReviewer.setDroneAvailable(droneId)){
            System.out.println("Set drone "+droneId+ " available  ! ");
            return true;
        }
        else{
            System.out.println("Drone "+droneId+ " doesn't exist ! ");
            return false;
        }
    }
}
