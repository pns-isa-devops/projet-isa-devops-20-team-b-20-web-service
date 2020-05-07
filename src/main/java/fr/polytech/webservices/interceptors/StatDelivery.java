package fr.polytech.webservices.interceptors;

import java.io.Serializable;
import java.util.GregorianCalendar;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import fr.polytech.entities.Delivery;
import fr.polytech.warehouse.components.DeliveryModifier;
import fr.polytech.warehouse.exception.UnknownDeliveryException;
import statistics.StatisticsCreator;

public class StatDelivery implements Serializable {

    private static final long serialVersionUID = -5785967766941270647L;

    @EJB
    private StatisticsCreator statisticsCreator;

    @EJB
    private DeliveryModifier deliveryModifier;

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        Delivery delivery = deliveryModifier.findDelivery((String) ctx.getParameters()[0]);
        statisticsCreator.addOccupancy(delivery.getDrone().getDroneId(), new GregorianCalendar(), 0.25);
        return ctx.proceed();
    }
}
