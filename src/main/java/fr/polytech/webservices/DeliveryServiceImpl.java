package fr.polytech.webservices;

import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import fr.polytech.dronepark.exception.DroneNotAvailableException;
import fr.polytech.dronepark.exception.ExternalDroneApiException;
import fr.polytech.entities.Delivery;
import fr.polytech.schedule.components.DeliveryOrganizer;
import fr.polytech.schedule.exception.DroneNotFoundException;
import fr.polytech.schedule.exception.ZeroDronesInWarehouseException;
import fr.polytech.shipment.components.DeliveryInitializer;
import fr.polytech.shipment.exception.NoDroneAttachOnDeliveryException;
import fr.polytech.shipment.exception.NoTimeSlotAttachOnDeliveryException;
import fr.polytech.warehouse.components.DeliveryModifier;
import fr.polytech.warehouse.exception.ExternalCarrierApiException;
import fr.polytech.warehouse.exception.UnknownDeliveryException;
import fr.polytech.warehouse.exception.UnknownParcelException;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/delivery")
@Stateless(name = "DeliveryWS")
public class DeliveryServiceImpl implements DeliveryService {

    @EJB(name = "stateless-delivery")
    private DeliveryInitializer deliveryInitializer;

    @EJB(name = "stateless-schedule")
    private DeliveryOrganizer deliveryOrganizer;

    @EJB
    private DeliveryModifier deliveryModifier;

    /**
     * Gets the delivery corresponding to deliveryId from the deliveryModifier
     * component and start the shipment with it.
     *
     * @throws NoDroneAttachOnDeliveryException
     * @throws ExternalDroneApiException
     * @throws UnknownDeliveryException
     * @throws NoTimeSlotAttachOnDeliveryException
     * @throws DroneNotAvailableException
     */
    @Override
    public Delivery startDelivery(String deliveryId) throws NoDroneAttachOnDeliveryException, ExternalDroneApiException,
            UnknownDeliveryException, NoTimeSlotAttachOnDeliveryException, DroneNotAvailableException {
        Delivery deliveryFromWarehouse = deliveryModifier.findDelivery(deliveryId);
        return deliveryInitializer.initializeDelivery(deliveryFromWarehouse);
    }

    @Override
    public Delivery getNextDelivery(String date) throws DroneNotFoundException, ZeroDronesInWarehouseException {
        String[] time = date.split(":");
        GregorianCalendar c = new GregorianCalendar();
        c.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
        c.set(GregorianCalendar.MINUTE, Integer.parseInt(time[1]));
        Delivery d = deliveryOrganizer.getNextDelivery(c);
        d.getDrone();
        return d;
    }

    @Override
    public List<Delivery> checkForNewParcels() throws ExternalCarrierApiException, UnknownParcelException {
        return deliveryModifier.checkForNewParcels();
    }

}
