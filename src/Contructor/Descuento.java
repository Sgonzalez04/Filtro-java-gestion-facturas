package Contructor;

public class Descuento {
    private int descuentoID;
    private String tipoDescuento;
    private double valorDescuento;
    private int facturaID;

    public Descuento(int descuentoID, String tipoDescuento, double valorDescuento, int facturaID) {
        this.descuentoID = descuentoID;
        this.tipoDescuento = tipoDescuento;
        this.valorDescuento = valorDescuento;
        this.facturaID = facturaID;
    }

    public int getDescuentoID() {
        return descuentoID;
    }

    public void setDescuentoID(int descuentoID) {
        this.descuentoID = descuentoID;
    }

    public String getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(String tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public double getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(double valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public int getFacturaID() {
        return facturaID;
    }

    public void setFacturaID(int facturaID) {
        this.facturaID = facturaID;
    }
}
