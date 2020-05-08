package fr.polytech.webservices.interceptors;

import java.io.Serializable;
import java.util.GregorianCalendar;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import statistics.StatisticsCreator;

public class StatMaintenance implements Serializable {

    private static final long serialVersionUID = -5785967766941270647L;

    @EJB
    private StatisticsCreator statisticsCreator;

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        String droneId = (String) ctx.getParameters()[0];
        statisticsCreator.addOccupancy(droneId, new GregorianCalendar(), 3.0);
        return ctx.proceed();
    }
}
