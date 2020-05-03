package fr.polytech.webservices.interceptors;

import fr.polytech.entities.Delivery;
import statistics.StatisticsCollector;
import statistics.StatisticsCreator;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.GregorianCalendar;

public class StatDelivery implements Serializable {

    private static final long serialVersionUID = -5785967766941270647L;

    @EJB private StatisticsCreator statisticsCreator;

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        Delivery delivery = (Delivery) ctx.getParameters()[0];
        statisticsCreator.addOccupancy(delivery.getDrone().getDroneId(),new GregorianCalendar(),0.25);
        return ctx.proceed();
    }
}
