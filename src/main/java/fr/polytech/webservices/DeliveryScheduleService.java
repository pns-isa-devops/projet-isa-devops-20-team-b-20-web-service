package fr.polytech.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;
import fr.polytech.entities.TimeState;
import fr.polytech.schedule.exception.DeliveryAlreadyScheduledException;
import fr.polytech.schedule.exception.DroneNotFoundException;
import fr.polytech.schedule.exception.NoFreeDroneAtThisTimeSlotException;
import fr.polytech.schedule.exception.OutsideOfDeliveryHoursException;
import fr.polytech.schedule.exception.TimeslotUnvailableException;
import fr.polytech.schedule.exception.ZeroDronesInWarehouseException;
import fr.polytech.warehouse.exception.UnknownDeliveryException;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/delivery-schedule")
public interface DeliveryScheduleService {

        /**
         * @param date       formated like "HH:mm"
         * @param deliveryId
         * @throws UnknownDeliveryException
         * @throws OutOfWorkingHourTimeSlotException
         * @throws DroneNotFoundException
         * @throws TimeslotUnvailableException
         * @throws OutsideOfDeliveryHoursException
         * @throws DeliveryAlreadyScheduledException
         * @throws Exception
         */
        @WebMethod
        void scheduleDelivery(@WebParam(name = "date") String date, @WebParam(name = "delivery_id") String deliveryId)
                        throws UnknownDeliveryException, DroneNotFoundException, NoFreeDroneAtThisTimeSlotException,
                        ZeroDronesInWarehouseException, OutsideOfDeliveryHoursException, TimeslotUnvailableException, DeliveryAlreadyScheduledException;

        @WebMethod
        List<TimeState> getCurrentPlanning(@WebParam(name = "droneid") String droneID)
                        throws DroneNotFoundException, ZeroDronesInWarehouseException;
}
