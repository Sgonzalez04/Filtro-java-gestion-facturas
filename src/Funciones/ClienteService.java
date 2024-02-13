package Funciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Contructor.Cliente;

public class ClienteService {
    private static final String URL_BASE_DE_DATOS = "jdbc:sqlite:invoice_max_pro.db";

    public void crearCliente(Cliente cliente) {
        String query = "INSERT INTO clientes (nombre_completo, apellido, numero_documento, numero_telefono, direccion) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, cliente.getNombreCompleto());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setString(3, cliente.getNumeroDocumento());
            pstmt.setString(4, cliente.getNumeroTelefono());
            pstmt.setString(5, cliente.getDireccion());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear cliente: " + e.getMessage());
        }
    }

    public Cliente obtenerClientePorID(int clienteID) {
        String query = "SELECT * FROM clientes WHERE cliente_id = ?";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, clienteID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("cliente_id"),
                            rs.getString("nombre_completo"),
                            rs.getString("apellido"),
                            rs.getString("numero_documento"),
                            rs.getString("numero_telefono"),
                            rs.getString("direccion")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener cliente por ID: " + e.getMessage());
        }
        return null;
    }

    public void actualizarCliente(Cliente cliente) {
        String query = "UPDATE clientes SET nombre_completo = ?, apellido = ?, numero_documento = ?, numero_telefono = ?, direccion = ? WHERE cliente_id = ?";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, cliente.getNombreCompleto());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setString(3, cliente.getNumeroDocumento());
            pstmt.setString(4, cliente.getNumeroTelefono());
            pstmt.setString(5, cliente.getDireccion());
            pstmt.setInt(6, cliente.getClienteID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    public void eliminarCliente(int clienteID) {
        String query = "DELETE FROM clientes WHERE cliente_id = ?";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, clienteID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
    }
}
