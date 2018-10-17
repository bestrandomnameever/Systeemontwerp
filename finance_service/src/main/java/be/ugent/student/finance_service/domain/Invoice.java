package be.ugent.student.finance_service.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document
public class Invoice {
    @Id
    private String invoiceId;
    private String patientId;
    private String hospitalStayId;
    private Date dateCreated;
    private Status status;
    private List<ListItem> items;

    public Invoice() {
        //this.invoiceId = UUID.randomUUID().toString();
        this.dateCreated = Date.from(Instant.now());
        this.status = Status.Open;
        items = new ArrayList<>();
    }

    public Invoice(String patientId, String hospitalStayId) {
        this();
        this.patientId = patientId;
        this.hospitalStayId = hospitalStayId;
    }

    public void addListItem(ListItem item) {
        items.add(item);
    }

    public void addListItem(String name, double cost) {
        items.add(new ListItem(name,cost));
    }

    public Invoice close() {
        this.status = Status.Closed;
        return this;
    }

    public Invoice pay() {
        this.status = Status.Paid;
        return this;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getHospitalStayId() {
        return hospitalStayId;
    }

    public void setHospitalStayId(String hospitalStayId) {
        this.hospitalStayId = hospitalStayId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<ListItem> getItems() {
        return items;
    }

    public void setItems(List<ListItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("\n(%s)(%s)(%s)\n", this.invoiceId, this.patientId, this.hospitalStayId));
        builder.append("____________\n");
        for (ListItem item : items) {
            builder.append(item.toString());
            builder.append('\n');
        }
        builder.append("____________\n");
        builder.append(String.format("%s    %s", dateCreated.toString(), status.toString()));
        return builder.toString();
    }
}

