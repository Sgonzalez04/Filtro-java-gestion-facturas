package Funciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Contructor.DetalleFactura;

public class DetalleFacturaService {
    private static final String URL_BASE_DE_DATOS = "jdbc:sqlite:invoice_max_pro.db";

    public void crearDetalleFactura(DetalleFactura detalleFactura) {
        String query = "INSERT INTO detalles_factura (factura_id, producto_id, cantidad) VALUES (?, ?, ?)";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, detalleFactura.getFactura().getFacturaID());
            pstmt.setInt(2, detalleFactura.getProducto().getProductoID());
            pstmt.setInt(3, detalleFactura.getCantidad());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear detalle de factura: " + e.getMessage());
        }
    }

    public List<DetalleFactura> obtenerDetallesFacturaPorID(int facturaID) {
        List<DetalleFactura> detallesFactura = new ArrayList<>();
        String query = "SELECT * FROM detalles_factura WHERE factura_id = ?";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, facturaID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    DetalleFactura detalleFactura = new DetalleFactura(
                            rs.getInt("detalle_id"),
                            null, // No se necesita la instancia de Factura ya que solo buscamos detalles de factura por su ID
                            null, // No se necesita la instancia de Producto ya que solo buscamos detalles de factura por su ID
                            rs.getInt("cantidad")
                    );
                    detallesFactura.add(detalleFactura);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener detalles de factura: " + e.getMessage());
        }
        return detallesFactura;
    }
    

    public void actualizarDetalleFactura(DetalleFactura detalleFactura) {
        String query = "UPDATE detalles_factura SET factura_id = ?, producto_id = ?, cantidad = ? WHERE detalle_id = ?";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, detalleFactura.getFactura().getFacturaID());
            pstmt.setInt(2, detalleFactura.getProducto().getProductoID());
            pstmt.setInt(3, detalleFactura.getCantidad());
            pstmt.setInt(4, detalleFactura.getDetalleID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar detalle de factura: " + e.getMessage());
        }
    }

    public void eliminarDetalleFactura(int detalleID) {
        String query = "DELETE FROM detalles_factura WHERE detalle_id = ?";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, detalleID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar detalle de factura: " + e.getMessage());
        }
    }
}
