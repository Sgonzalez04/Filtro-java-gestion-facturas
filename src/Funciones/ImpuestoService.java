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
import Contructor.Impuesto;

public class ImpuestoService {
    private static final String URL_BASE_DE_DATOS = "jdbc:sqlite:invoice_max_pro.db";

    // Función para obtener impuestos por cliente
    public List<Impuesto> obtenerImpuestosPorCliente(int clienteID) {
        List<Impuesto> impuestos = new ArrayList<>();
        String query = "SELECT * FROM impuestos WHERE cliente_id = ?";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, clienteID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Obtener el cliente asociado mediante su servicio
                    Cliente cliente = obtenerClientePorId(rs.getInt("cliente_id"));
                    // Obtener la factura asociada mediante su servicio
                    Factura factura = obtenerFacturaPorId(rs.getInt("factura_id"));

                    Impuesto impuesto = new Impuesto(
                            rs.getInt("impuesto_id"),
                            cliente,
                            factura,
                            rs.getString("numero_factura"),
                            rs.getDate("fecha_venta"),
                            rs.getDouble("valor_total_factura"),
                            rs.getDouble("impuesto_pagado")
                    );
                    impuestos.add(impuesto);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener impuestos por cliente: " + e.getMessage());
        }
        return impuestos;
    }

    // Función para obtener un cliente por su ID
    private Cliente obtenerClientePorId(int clienteID) {
        String query = "SELECT * FROM clientes WHERE cliente_id = ?";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, clienteID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("cliente_id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("documento"),
                            rs.getString("telefono"),
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

    // Función para obtener una factura por su ID
    private Factura obtenerFacturaPorId(int facturaID) {
        String query = "SELECT * FROM facturas WHERE factura_id = ?";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, facturaID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Factura factura = new Factura(
                            rs.getInt("factura_id"),
                            obtenerClientePorId(rs.getInt("cliente_id")),
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
