package fr.polytech.webservices;

import fr.polytech.entities.Delivery;
import fr.polytech.webservices.interceptors.StatDelivery;
import fr.polytech.webservices.interceptors.StatMaintenance;

import java.util.List;

import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/delivery")
public interface DeliveryService {

        @WebMethod
        @Interceptors({StatDelivery.class})
        void startDelivery(@WebParam(name = "delivery_id") String deliveryId) throws Exception; // TODO never throw blank exception like that...

        /**
         * Shows the closest delivery to process, according to the planning
         * @return Delivery
         * @throws Exception
         */
        @WebMethod
        Delivery getNextDelivery();

        @WebMethod
        List<Delivery> checkForNewParcels() throws Exception ;

}
