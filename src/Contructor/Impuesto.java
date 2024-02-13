package Contructor;

import java.util.Date;

public class Impuesto {
    private int impuestoID;
    private Cliente cliente;
    private Factura factura;
    private String numeroFactura;
    private Date fechaVenta;
    private double valorTotalFactura;
    private double impuestoPagado;

    public Impuesto(int impuestoID, Cliente cliente, Factura factura, String numeroFactura, Date fechaVenta, double valorTotalFactura, double impuestoPagado) {
        this.impuestoID = impuestoID;
        this.cliente = cliente;
        this.factura = factura;
        this.numeroFactura = numeroFactura;
        this.fechaVenta = fechaVenta;
        this.valorTotalFactura = valorTotalFactura;
        this.impuestoPagado = impuestoPagado;
    }

    public int getImpuestoID() {
        return impuestoID;
    }

    public void setImpuestoID(int impuestoID) {
        this.impuestoID = impuestoID;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getValorTotalFactura() {
        return valorTotalFactura;
    }

    public void setValorTotalFactura(double valorTotalFactura) {
        this.valorTotalFactura = valorTotalFactura;
    }

    public double getImpuestoPagado() {
        return impuestoPagado;
    }

    public void setImpuestoPagado(double impuestoPagado) {
        this.impuestoPagado = impuestoPagado;
    }


}
