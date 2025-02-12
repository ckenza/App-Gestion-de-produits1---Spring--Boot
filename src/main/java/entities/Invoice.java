package entities;

import java.util.Date;

public class Invoice {


    private int idInvoice;
    private Date date;
    private Double total;


    public Invoice(){}
    public Invoice(int idInvoice, Date date, Double total){
        this.idInvoice = idInvoice;
        this.date = date;
        this.total = total;
    }


    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }


    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
