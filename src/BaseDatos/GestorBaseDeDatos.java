package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBaseDeDatos {
    private static final String URL_BASE_DE_DATOS = "jdbc:sqlite:invoice_max_pro.db";

    public static void inicializarBaseDeDatos() {
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS)) {
            // Crear la tabla de clientes
            String crearTablaClientesSQL = "CREATE TABLE IF NOT EXISTS clientes ("
                + "cliente_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombre_completo TEXT NOT NULL,"
                + "apellido TEXT NOT NULL,"
                + "numero_documento TEXT NOT NULL,"
                + "numero_telefono TEXT,"
                + "direccion TEXT NOT NULL"
                + ")";
            try (Statement stmt = conexion.createStatement()) {
                stmt.executeUpdate(crearTablaClientesSQL);
            }

            // Crear la tabla de facturas
            String crearTablaFacturasSQL = "CREATE TABLE IF NOT EXISTS facturas ("
                + "factura_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "cliente_id INTEGER NOT NULL,"
                + "fecha_venta TEXT NOT NULL,"
                + "valor_total DOUBLE NOT NULL,"
                + "impuesto_pagado DOUBLE NOT NULL,"
                + "FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id)"
                + ")";
            try (Statement stmt = conexion.createStatement()) {
                stmt.executeUpdate(crearTablaFacturasSQL);
            }

            // Crear la tabla de impuestos
            String crearTablaImpuestosSQL = "CREATE TABLE IF NOT EXISTS impuestos ("
                + "impuesto_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "factura_id INTEGER NOT NULL,"
                + "numero_factura TEXT NOT NULL,"
                + "fecha_venta TEXT NOT NULL,"
                + "valor_total_factura DOUBLE NOT NULL,"
                + "impuesto_pagado DOUBLE NOT NULL,"
                + "FOREIGN KEY (factura_id) REFERENCES facturas(factura_id)"
                + ")";
            try (Statement stmt = conexion.createStatement()) {
                stmt.executeUpdate(crearTablaImpuestosSQL);
            }

            // Crear la tabla de descuentos
            String crearTablaDescuentosSQL = "CREATE TABLE IF NOT EXISTS descuentos ("
                + "descuento_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "tipo_descuento TEXT NOT NULL,"
                + "valor DOUBLE NOT NULL,"
                + "descripcion TEXT,"
                + "activo INTEGER NOT NULL"
                + ")";
            try (Statement stmt = conexion.createStatement()) {
                stmt.executeUpdate(crearTablaDescuentosSQL);
            }

            // Otros ajustes y creaciones de tablas si es necesario
        } catch (SQLException e) {
            System.out.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL_BASE_DE_DATOS);
    }
}
