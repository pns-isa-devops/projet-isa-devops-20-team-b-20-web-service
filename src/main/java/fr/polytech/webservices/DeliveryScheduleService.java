package fr.polytech.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;
import fr.polytech.entities.TimeState;

import fr.polytech.schedule.exception.DroneNotFoundException;
<<<<<<< HEAD
import fr.polytech.schedule.exception.NoFreeDroneAtThisTimeSlotException;
import fr.polytech.schedule.exception.OutOfWorkingHourTimeSlotException;
import fr.polytech.schedule.exception.ZeroDronesInWarehouseException;
=======
import fr.polytech.schedule.exception.OutsideOfDeliveryHoursException;
import fr.polytech.schedule.exception.TimeslotUnvailableException;
>>>>>>> add custom exception pns-isa-devops/projet-isa-devops-20-team-b-20#151
import fr.polytech.warehouse.exception.UnknownDeliveryException;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/delivery-schedule")
public interface DeliveryScheduleService {

    /**
     * @param date       formated like "HH:mm"
     * @param deliveryId
     * @throws UnknownDeliveryException
     * @throws OutOfWorkingHourTimeSlotException
     * @throws DroneNotFoundException
     * @throws OutsideOfDeliveryHoursException
     * @throws Exception
     */
    @WebMethod
    void scheduleDelivery(@WebParam(name = "date") String date, @WebParam(name = "delivery_id") String deliveryId)
            throws UnknownDeliveryException, DroneNotFoundException, OutOfWorkingHourTimeSlotException, NoFreeDroneAtThisTimeSlotException, ZeroDronesInWarehouseException;

    @WebMethod
    List<TimeState> getCurrentPlanning(@WebParam(name = "droneid") String droneID) throws DroneNotFoundException, ZeroDronesInWarehouseException;
}
