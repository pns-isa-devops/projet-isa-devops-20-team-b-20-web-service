package fr.polytech.webservices;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import fr.polytech.entities.Invoice;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/invoice")
public interface InvoiceService {


    @WebMethod
    List<Invoice> getInvoices();
}