package fr.polytech.webservices;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import fr.polytech.entities.Delivery;
import fr.polytech.schedule.components.DeliveryOrganizer;
import fr.polytech.shipment.components.DeliveryInitializer;
import fr.polytech.warehouse.components.DeliveryModifier;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/delivery")
@Stateless(name = "DeliveryWS")
public class DeliveryServiceImpl implements DeliveryService {

    @EJB(name = "stateless-delivery")
    private DeliveryInitializer deliveryInitializer;

    @EJB(name = "stateless-schedule")
    private DeliveryOrganizer deliveryOrganizer;

    @EJB
    private DeliveryModifier deliveryModifier;

    @Override
    /**
     * Get the delivery corresponding to deliveryId from the deliveryModifier
     * component and start the shipment with it
     */
    public void startDelivery(String deliveryId) throws Exception {
        Delivery deliveryFromWharehouse = deliveryModifier.findDelivery(deliveryId);

        // If the delivery doesn't have a drone associated there is a problem
        if (deliveryFromWharehouse.getDrone() == null) {
            throw new Exception("There is no drone on this delivery");
        }
        deliveryInitializer.initializeDelivery(deliveryFromWharehouse);
    }

    @Override
    public Delivery getNextDelivery() throws Exception {
        return deliveryOrganizer.getNextDelivery();
    }

    @Override
    public void checkForNewParcels() {
        deliveryModifier.checkForNewParcels();
    }
    
}
