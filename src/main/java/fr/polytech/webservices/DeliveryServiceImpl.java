package fr.polytech.webservices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import fr.polytech.entities.Delivery;
import fr.polytech.schedule.components.DeliveryOrganizer;
import fr.polytech.schedule.exception.DroneNotFoundException;
import fr.polytech.shipment.components.DeliveryInitializer;
import fr.polytech.warehouse.components.DeliveryModifier;
import fr.polytech.warehouse.exception.ExternalCarrierApiException;
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
     */
    @Override
    public void startDelivery(String deliveryId) throws Exception {
        Delivery deliveryFromWarehouse = deliveryModifier.findDelivery(deliveryId);

        // If the delivery doesn't have a drone associated there is a problem
        if (deliveryFromWarehouse.getDrone() == null) {
            throw new Exception("There is no drone on this delivery");
        }
        deliveryInitializer.initializeDelivery(deliveryFromWarehouse);
    }

    @Override
    public Delivery getNextDelivery() throws DroneNotFoundException {
        return deliveryOrganizer.getNextDelivery();
    }

    @Override
    public List<Delivery> checkForNewParcels() throws ExternalCarrierApiException, UnknownParcelException {
        return deliveryModifier.checkForNewParcels();
    }

}
