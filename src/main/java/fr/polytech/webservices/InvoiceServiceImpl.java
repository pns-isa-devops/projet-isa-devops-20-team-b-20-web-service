package fr.polytech.webservices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import fr.polytech.entities.Invoice;
import fr.polytech.invoice.components.InvoiceManager;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/invoice")
@Stateless(name = "InvoiceWS")
public class InvoiceServiceImpl implements InvoiceService {
    @EJB
    private InvoiceManager invoiceManager;

    @Override
    public List<Invoice> getInvoices() {
        return invoiceManager.getInvoices();
    }
}