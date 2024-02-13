package Funciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Contructor.Producto;

public class ProductoService {
    private static final String URL_BASE_DE_DATOS = "jdbc:sqlite:invoice_max_pro.db";

    // Función para obtener todos los productos
    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM productos";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("producto_id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio_unitario")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todos los productos: " + e.getMessage());
        }
        return productos;
    }

    // Función para obtener un producto por su ID
    public Producto obtenerProductoPorId(int productoID) {
        String query = "SELECT * FROM productos WHERE producto_id = ?";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, productoID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Producto producto = new Producto(
                            rs.getInt("producto_id"),
                            rs.getString("nombre"),
                            rs.getDouble("precio_unitario")
                    );
                    return producto;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener producto por ID: " + e.getMessage());
        }
        return null;
    }

    // Función para crear un nuevo producto
    public void crearProducto(Producto producto) {
        String query = "INSERT INTO productos (nombre, precio_unitario) VALUES (?, ?)";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, producto.getNombre());
            pstmt.setDouble(2, producto.getPrecioUnitario());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear producto: " + e.getMessage());
        }
    }

    // Función para actualizar un producto existente
    public void actualizarProducto(Producto producto) {
        String query = "UPDATE productos SET nombre = ?, precio_unitario = ? WHERE producto_id = ?";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, producto.getNombre());
            pstmt.setDouble(2, producto.getPrecioUnitario());
            pstmt.setInt(3, producto.getProductoID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    // Función para eliminar un producto existente
    public void eliminarProducto(int productoID) {
        String query = "DELETE FROM productos WHERE producto_id = ?";
        try (Connection conexion = DriverManager.getConnection(URL_BASE_DE_DATOS);
             PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, productoID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }
}
