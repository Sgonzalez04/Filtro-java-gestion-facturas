package Funciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Contructor.Descuento;

public class DescuentoService {
    private static final String URL_BASE_DE_DATOS = "jdbc:sqlite:invoice_max_pro.db";

    public void crearDescuento(Descuento descuento) {
        String query = "INSERT INTO descuentos (tipo_descuento, valor_descuento, factura_id) VALUES (?, ?, ?)";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, descuento.getTipoDescuento());
            pstmt.setDouble(2, descuento.getValorDescuento());
            pstmt.setInt(3, descuento.getFacturaID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear descuento: " + e.getMessage());
        }
    }

    public List<Descuento> obtenerDescuentosActivos() {
        List<Descuento> descuentos = new ArrayList<>();
        String query = "SELECT * FROM descuentos";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Descuento descuento = new Descuento(
                        rs.getInt("descuento_id"),
                        rs.getString("tipo_descuento"),
                        rs.getDouble("valor_descuento"),
                        rs.getInt("factura_id")
                );
                descuentos.add(descuento);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener descuentos activos: " + e.getMessage());
        }
        return descuentos;
    }

    public void actualizarDescuento(Descuento descuento) {
        String query = "UPDATE descuentos SET tipo_descuento = ?, valor_descuento = ?, factura_id = ? WHERE descuento_id = ?";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, descuento.getTipoDescuento());
            pstmt.setDouble(2, descuento.getValorDescuento());
            pstmt.setInt(3, descuento.getFacturaID());
            pstmt.setInt(4, descuento.getDescuentoID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar descuento: " + e.getMessage());
        }
    }

    public void eliminarDescuento(int descuentoID) {
        String query = "DELETE FROM descuentos WHERE descuento_id = ?";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, descuentoID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar descuento: " + e.getMessage());
        }
    }

    public Descuento obtenerDescuentoPorId(int descuentoID) {
        String query = "SELECT * FROM descuentos WHERE descuento_id = ?";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, descuentoID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Descuento descuento = new Descuento(
                            rs.getInt("descuento_id"),
                            rs.getString("tipo_descuento"),
                            rs.getDouble("valor_descuento"),
                            rs.getInt("factura_id")
                    );
                    return descuento;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener descuento por ID: " + e.getMessage());
        }
        return null;
    }
    
}
