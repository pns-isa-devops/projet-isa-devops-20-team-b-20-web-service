package fr.polytech.webservices;

import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import fr.polytech.entities.Delivery;
import fr.polytech.entities.TimeState;
import fr.polytech.schedule.components.DeliveryScheduler;
import fr.polytech.schedule.exception.DeliveryAlreadyScheduledException;
import fr.polytech.schedule.exception.DroneNotFoundException;
import fr.polytech.schedule.exception.NoFreeDroneAtThisTimeSlotException;
import fr.polytech.schedule.exception.OutsideOfDeliveryHoursException;
import fr.polytech.schedule.exception.TimeslotUnvailableException;
import fr.polytech.schedule.exception.ZeroDronesInWarehouseException;
import fr.polytech.warehouse.components.DeliveryModifier;
import fr.polytech.warehouse.exception.UnknownDeliveryException;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/delivery-schedule")
@Stateless(name = "DeliveryScheduleWS")
public class DeliveryScheduleServiceImpl implements DeliveryScheduleService {

    @EJB(name = "stateless-schedule")
    private DeliveryScheduler deliveryScheduler;

    @EJB
    private DeliveryModifier deliveryModifier;

    @Override
    public void scheduleDelivery(String date, String deliveryId) throws DroneNotFoundException,
            UnknownDeliveryException, NoFreeDroneAtThisTimeSlotException, ZeroDronesInWarehouseException,
            OutsideOfDeliveryHoursException, TimeslotUnvailableException, DeliveryAlreadyScheduledException {
        Delivery delivery = deliveryModifier.findDelivery(deliveryId);
        String[] time = date.split(":");
        GregorianCalendar c = new GregorianCalendar();
        c.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
        c.set(GregorianCalendar.MINUTE, Integer.parseInt(time[1]));
        deliveryScheduler.scheduleDelivery(c, delivery);
    }

    @Override
    public List<TimeState> getCurrentPlanning(String droneID)
            throws DroneNotFoundException, ZeroDronesInWarehouseException {
        List<TimeState> states = deliveryScheduler.getCurrentPlanning(droneID);
        System.out.println(states);
        return states;
    }
}
