package fr.polytech.webservices;

import fr.polytech.dronepark.exception.ExternalDroneApiException;
import fr.polytech.entities.Delivery;
import fr.polytech.schedule.exception.DroneNotFoundException;
import fr.polytech.shipment.exception.NoDroneAttachOnDeliveryException;
import fr.polytech.shipment.exception.NoTimeSlotAttachOnDeliveryException;
import fr.polytech.warehouse.exception.ExternalCarrierApiException;
import fr.polytech.warehouse.exception.UnknownDeliveryException;
import fr.polytech.warehouse.exception.UnknownParcelException;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/delivery")
public interface DeliveryService {

        @WebMethod
        void startDelivery(@WebParam(name = "delivery_id") String deliveryId) throws NoDroneAttachOnDeliveryException,
                        ExternalDroneApiException, UnknownDeliveryException, NoTimeSlotAttachOnDeliveryException;

        /**
         * Shows the closest delivery to process, according to the planning
         *
         * @return Delivery
         * @throws DroneNotFoundException
         * @throws Exception
         */
        @WebMethod
        Delivery getNextDelivery() throws DroneNotFoundException;

        @WebMethod
        List<Delivery> checkForNewParcels() throws ExternalCarrierApiException, UnknownParcelException;

}
