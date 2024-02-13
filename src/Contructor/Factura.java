package Contructor;

import java.util.Date;
import java.util.List;

public class Factura {
    private int facturaID;
    private Cliente cliente;
    private Date fechaVenta;
    private double valorTotal;
    private double impuestoPagado;
    private Impuesto impuesto;
    private List<Descuento> descuentos;

    public Factura(int facturaID, Cliente cliente, Date fechaVenta, double valorTotal, double impuestoPagado) {
        this.facturaID = facturaID;
        this.cliente = cliente;
        this.fechaVenta = fechaVenta;
        this.valorTotal = valorTotal;
        this.impuestoPagado = impuestoPagado;
    }

    public int getFacturaID() {
        return facturaID;
    }

    public void setFacturaID(int facturaID) {
        this.facturaID = facturaID;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getImpuestoPagado() {
        return impuestoPagado;
    }

    public void setImpuestoPagado(double impuestoPagado) {
        this.impuestoPagado = impuestoPagado;
    }

    public Impuesto getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }

    public List<Descuento> getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(List<Descuento> descuentos) {
        this.descuentos = descuentos;
    }

    
}
