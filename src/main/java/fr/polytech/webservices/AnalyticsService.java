package fr.polytech.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import statistics.exception.NoOccupancyOnThatDroneException;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/analytics")
public interface AnalyticsService {

    /**
     * Returns the occupancy rate of the drone with droneId
     *
     * @param droneId
     * @throws NoOccupancyOnThatDroneException
     * @throws Exception
     */
    @WebMethod
    double getOccupancyRate(@WebParam(name = "drone_id") String droneId) throws NoOccupancyOnThatDroneException;
}
