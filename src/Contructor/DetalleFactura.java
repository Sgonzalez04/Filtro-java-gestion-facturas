package Contructor;

public class DetalleFactura {
    private int detalleID;
    private Factura factura;
    private Producto producto;
    private int cantidad;

    public DetalleFactura(int detalleID, Factura factura, Producto producto, int cantidad) {
        this.detalleID = detalleID;
        this.factura = factura;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public int getDetalleID() {
        return detalleID;
    }

    public void setDetalleID(int detalleID) {
        this.detalleID = detalleID;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


}
