package Funciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Contructor.Cliente;
import Contructor.Factura;

public class FacturaService {
    private static final String URL_BASE_DE_DATOS = "jdbc:sqlite:invoice_max_pro.db";

    public void crearFactura(Factura factura) {
        String query = "INSERT INTO facturas (cliente_id, fecha_venta, valor_total, impuesto_pagado) VALUES (?, ?, ?, ?)";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, factura.getCliente().getClienteID());
            pstmt.setDate(2, new java.sql.Date(factura.getFechaVenta().getTime()));
            pstmt.setDouble(3, factura.getValorTotal());
            pstmt.setDouble(4, factura.getImpuestoPagado());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear factura: " + e.getMessage());
        }
    }

    public List<Factura> obtenerFacturas() {
        List<Factura> facturas = new ArrayList<>();
        String query = "SELECT * FROM facturas";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Factura factura = new Factura(
                        rs.getInt("factura_id"),
                        obtenerClientePorId(rs.getInt("cliente_id")),
                        rs.getDate("fecha_venta"),
                        rs.getDouble("valor_total"),
                        rs.getDouble("impuesto_pagado")
                );
                facturas.add(factura);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener facturas: " + e.getMessage());
        }
        return facturas;
    }

    public void actualizarFactura(Factura factura) {
        String query = "UPDATE facturas SET cliente_id = ?, fecha_venta = ?, valor_total = ?, impuesto_pagado = ? WHERE factura_id = ?";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, factura.getCliente().getClienteID());
            pstmt.setDate(2, new java.sql.Date(factura.getFechaVenta().getTime()));
            pstmt.setDouble(3, factura.getValorTotal());
            pstmt.setDouble(4, factura.getImpuestoPagado());
            pstmt.setInt(5, factura.getFacturaID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar factura: " + e.getMessage());
        }
    }

    public void eliminarFactura(int facturaID) {
        String query = "DELETE FROM facturas WHERE factura_id = ?";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, facturaID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar factura: " + e.getMessage());
        }
    }

    private Cliente obtenerClientePorId(int clienteId) {
        String query = "SELECT * FROM clientes WHERE cliente_id = ?";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, clienteId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("cliente_id"),
                            rs.getString("nombre_completo"),
                            rs.getString("apellido"),
                            rs.getString("numero_documento"),
                            rs.getString("numero_telefono"),
                            rs.getString("direccion")
                    );
                    return cliente;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener cliente por ID: " + e.getMessage());
        }
        return null;
    }
    public Factura obtenerFacturaPorId(int facturaID) {
        String query = "SELECT * FROM facturas WHERE factura_id = ?";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, facturaID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = obtenerClientePorId(rs.getInt("cliente_id"));
                    Factura factura = new Factura(
                            rs.getInt("factura_id"),
                            cliente,
                            rs.getDate("fecha_venta"),
                            rs.getDouble("valor_total"),
                            rs.getDouble("impuesto_pagado")
                    );
                    return factura;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener factura por ID: " + e.getMessage());
        }
        return null;
    }
}
