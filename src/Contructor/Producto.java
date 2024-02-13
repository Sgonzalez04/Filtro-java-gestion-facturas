package Contructor;

public class Producto {
    private int productoID;
    private String nombre;
    private double precioUnitario;

    public Producto(int productoID, String nombre, double precioUnitario) {
        this.productoID = productoID;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
    }

    public int getProductoID() {
        return productoID;
    }

    public void setProductoID(int productoID) {
        this.productoID = productoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

}
