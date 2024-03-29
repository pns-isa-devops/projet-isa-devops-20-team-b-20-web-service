package fr.polytech.webservices;

import statistics.StatisticsCollector;
import statistics.exception.NoOccupancyOnThatDroneException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/analytics")
@Stateless(name = "AnalyticsWS")
public class AnalyticsServiceImpl implements AnalyticsService {
    @EJB
    private StatisticsCollector statisticsCollector;

    @Override
    public double getOccupancyRate(String droneId) throws NoOccupancyOnThatDroneException {
        return statisticsCollector.getOccupancyRate(droneId);
    }
}
