package fr.polytech.webservices;

import java.util.List;

import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import fr.polytech.dronepark.exception.DroneNotAvailableException;
import fr.polytech.dronepark.exception.ExternalDroneApiException;
import fr.polytech.entities.Delivery;
import fr.polytech.schedule.exception.DroneNotFoundException;
import fr.polytech.schedule.exception.ZeroDronesInWarehouseException;
import fr.polytech.shipment.exception.NoDroneAttachOnDeliveryException;
import fr.polytech.shipment.exception.NoTimeSlotAttachOnDeliveryException;
import fr.polytech.warehouse.exception.ExternalCarrierApiException;
import fr.polytech.warehouse.exception.UnknownDeliveryException;
import fr.polytech.warehouse.exception.UnknownParcelException;
import fr.polytech.webservices.interceptors.StatDelivery;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/delivery")
public interface DeliveryService {

        @WebMethod
        @Interceptors({ StatDelivery.class })
        Delivery startDelivery(@WebParam(name = "delivery_id") String deliveryId)
                        throws NoDroneAttachOnDeliveryException, ExternalDroneApiException, UnknownDeliveryException,
                        NoTimeSlotAttachOnDeliveryException, DroneNotAvailableException;

        /**
         * Shows the closest delivery to process, according to the planning, start from
         * the given date
         *
         * @return Delivery
         * @throws DroneNotFoundException
         * @throws ZeroDronesInWarehouseException
         * @throws Exception
         */
        @WebMethod
        Delivery getNextDelivery(@WebParam(name = "date") String date)
                        throws DroneNotFoundException, ZeroDronesInWarehouseException;

        @WebMethod
        List<Delivery> checkForNewParcels() throws ExternalCarrierApiException, UnknownParcelException;

}
