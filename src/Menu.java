import java.util.List;
import java.util.Scanner;

import Contructor.Cliente;
import Contructor.Descuento;
import Contructor.Factura;
import Contructor.Impuesto;
import Contructor.Producto;
import Funciones.ClienteService;
import Funciones.DescuentoService;
import Funciones.FacturaService;
import Funciones.ImpuestoService;
import Funciones.ProductoService;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Clientes");
            System.out.println("2. Productos");
            System.out.println("3. Facturas");
            System.out.println("4. Impuestos");
            System.out.println("5. Descuentos");
            System.out.println("0. Salir");
            System.out.print("Ingrese su opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    menuClientes(scanner);
                    break;
                case 2:
                    menuProductos(scanner);
                    break;
                case 3:
                    menuFacturas(scanner);
                    break;
                case 4:
                    menuImpuestos(scanner);
                    break;
                case 5:
                    menuDescuentos(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    public static void menuClientes(Scanner scanner) {
        int opcion;
        ClienteService clienteService = new ClienteService(); // Instancia del servicio de clientes
    
        do {
            System.out.println("=== Menú de Clientes ===");
            System.out.println("1. Crear Cliente");
            System.out.println("2. Leer Cliente por ID");
            System.out.println("3. Actualizar Cliente");
            System.out.println("4. Eliminar Cliente");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Ingrese su opción: ");
    
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada
    
            switch (opcion) {
                case 1:
                    // Lógica para crear un cliente
                    Cliente nuevoCliente = solicitarDatosCliente(scanner);
                    clienteService.crearCliente(nuevoCliente);
                    System.out.println("Cliente creado correctamente.");
                    break;
                case 2:
                    // Lógica para leer cliente por ID
                    System.out.print("Ingrese el ID del cliente a buscar: ");
                    int clienteID = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer de entrada
                    Cliente cliente = clienteService.obtenerClientePorID(clienteID);
                    if (cliente != null) {
                        System.out.println(cliente);
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;
                case 3:
                    // Lógica para actualizar un cliente
                    System.out.print("Ingrese el ID del cliente a actualizar: ");
                    int clienteIDActualizar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer de entrada
                    Cliente clienteActualizar = clienteService.obtenerClientePorID(clienteIDActualizar);
                    if (clienteActualizar != null) {
                        Cliente clienteActualizado = solicitarDatosCliente(scanner);
                        clienteActualizado.setClienteID(clienteIDActualizar);
                        clienteService.actualizarCliente(clienteActualizado);
                        System.out.println("Cliente actualizado correctamente.");
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;
                case 4:
                    // Lógica para eliminar un cliente
                    System.out.print("Ingrese el ID del cliente a eliminar: ");
                    int clienteIDEliminar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer de entrada
                    clienteService.eliminarCliente(clienteIDEliminar);
                    System.out.println("Cliente eliminado correctamente.");
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 0);
    }
    
    public static Cliente solicitarDatosCliente(Scanner scanner) {
        System.out.print("Ingrese el nombre completo del cliente: ");
        String nombreCompleto = scanner.nextLine();
        System.out.print("Ingrese el apellido del cliente: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese el número de documento del cliente: ");
        String numeroDocumento = scanner.nextLine();
        System.out.print("Ingrese el número de teléfono del cliente: ");
        String numeroTelefono = scanner.nextLine();
        System.out.print("Ingrese la dirección del cliente: ");
        String direccion = scanner.nextLine();
    
        return new Cliente(0, nombreCompleto, apellido, numeroDocumento, numeroTelefono, direccion);
    }


    public static void menuProductos(Scanner scanner) {
    int opcion;
    ProductoService productoService = new ProductoService(); // Instancia del servicio de productos

    do {
        System.out.println("=== Menú de Productos ===");
        System.out.println("1. Crear Producto");
        System.out.println("2. Leer Productos");
        System.out.println("3. Actualizar Producto");
        System.out.println("4. Eliminar Producto");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Ingrese su opción: ");

        opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        switch (opcion) {
            case 1:
                // Lógica para crear un producto
                Producto nuevoProducto = solicitarDatosProducto(scanner);
                productoService.crearProducto(nuevoProducto);
                System.out.println("Producto creado correctamente.");
                break;
            case 2:
                // Lógica para leer productos
                List<Producto> productos = productoService.obtenerTodosLosProductos();
                System.out.println("=== Listado de Productos ===");
                for (Producto producto : productos) {
                    System.out.println(producto);
                }
                break;
            case 3:
                // Lógica para actualizar un producto
                System.out.print("Ingrese el ID del producto a actualizar: ");
                int productoIDActualizar = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer de entrada
                Producto productoActualizar = productoService.obtenerProductoPorId(productoIDActualizar);
                if (productoActualizar != null) {
                    Producto productoActualizado = solicitarDatosProducto(scanner);
                    productoActualizado.setProductoID(productoIDActualizar);
                    productoService.actualizarProducto(productoActualizado);
                    System.out.println("Producto actualizado correctamente.");
                } else {
                    System.out.println("Producto no encontrado.");
                }
                break;
            case 4:
                // Lógica para eliminar un producto
                System.out.print("Ingrese el ID del producto a eliminar: ");
                int productoIDEliminar = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer de entrada
                productoService.eliminarProducto(productoIDEliminar);
                System.out.println("Producto eliminado correctamente.");
                break;
            case 0:
                System.out.println("Volviendo al Menú Principal...");
                break;
            default:
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
        }
    } while (opcion != 0);
    }

    public static Producto solicitarDatosProducto(Scanner scanner) {
        System.out.print("Ingrese el ID del producto: ");
        int productoID = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el precio unitario del producto: ");
        double precioUnitario = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer de entrada
        return new Producto(productoID, nombre, precioUnitario);
    }



    public static void menuFacturas(Scanner scanner) {
    int opcion;
    FacturaService facturaService = new FacturaService(); // Instancia del servicio de facturas

    do {
        System.out.println("=== Menú de Facturas ===");
        System.out.println("1. Crear Factura");
        System.out.println("2. Leer Facturas");
        System.out.println("3. Actualizar Factura");
        System.out.println("4. Eliminar Factura");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Ingrese su opción: ");

        opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        switch (opcion) {
            case 1:
                // Lógica para crear una factura
                Factura nuevaFactura = solicitarDatosFactura(scanner);
                facturaService.crearFactura(nuevaFactura);
                System.out.println("Factura creada correctamente.");
                break;
            case 2:
                // Lógica para leer facturas
                List<Factura> facturas = facturaService.obtenerFacturas();
                System.out.println("=== Listado de Facturas ===");
                for (Factura factura : facturas) {
                    System.out.println(factura);
                }
                break;
            case 3:
                // Lógica para actualizar una factura
                System.out.print("Ingrese el ID de la factura a actualizar: ");
                int facturaIDActualizar = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer de entrada
                Factura facturaActualizar = facturaService.obtenerFacturaPorId(facturaIDActualizar);
                if (facturaActualizar != null) {
                    Factura facturaActualizada = solicitarDatosFactura(scanner);
                    facturaActualizada.setFacturaID(facturaIDActualizar);
                    facturaService.actualizarFactura(facturaActualizada);
                    System.out.println("Factura actualizada correctamente.");
                } else {
                    System.out.println("Factura no encontrada.");
                }
                break;
            case 4:
                // Lógica para eliminar una factura
                System.out.print("Ingrese el ID de la factura a eliminar: ");
                int facturaIDEliminar = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer de entrada
                facturaService.eliminarFactura(facturaIDEliminar);
                System.out.println("Factura eliminada correctamente.");
                break;
            case 0:
                System.out.println("Volviendo al Menú Principal...");
                break;
            default:
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
        }
    } while (opcion != 0);
    }

    public static Factura solicitarDatosFactura(Scanner scanner) {
        System.out.print("Ingrese el ID del cliente: ");
        int clienteID = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada
        System.out.print("Ingrese la fecha de venta (YYYY-MM-DD): ");
        String fechaVentaStr = scanner.nextLine();
        java.util.Date fechaVenta = java.sql.Date.valueOf(fechaVentaStr);
        System.out.print("Ingrese el valor total de la factura: ");
        double valorTotal = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer de entrada
        System.out.print("Ingrese el impuesto pagado: ");
        double impuestoPagado = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer de entrada

        Cliente cliente = new Cliente(clienteID, fechaVentaStr, fechaVentaStr, fechaVentaStr, fechaVentaStr, fechaVentaStr);
        cliente.setClienteID(clienteID);

        return new Factura(0, cliente, fechaVenta, valorTotal, impuestoPagado);
    }


    public static void menuImpuestos(Scanner scanner) {
    int opcion;
    ImpuestoService impuestoService = new ImpuestoService(); // Instancia del servicio de impuestos

    do {
        System.out.println("=== Menú de Impuestos ===");
        System.out.println("1. Ver Impuestos por Cliente");
        System.out.println("2. Volver al Menú Principal");
        System.out.print("Ingrese su opción: ");

        opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        switch (opcion) {
            case 1:
                // Lógica para ver impuestos por cliente
                System.out.print("Ingrese el ID del cliente para ver sus impuestos: ");
                int clienteID = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer de entrada
                List<Impuesto> impuestos = impuestoService.obtenerImpuestosPorCliente(clienteID);
                System.out.println("=== Impuestos para el Cliente con ID " + clienteID + " ===");
                for (Impuesto impuesto : impuestos) {
                    System.out.println(impuesto);
                }
                break;
            case 2:
                System.out.println("Volviendo al Menú Principal...");
                break;
            default:
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
        }
    } while (opcion != 2);
}


    public static void menuDescuentos(Scanner scanner) {
        int opcion;
        DescuentoService descuentoService = new DescuentoService(); // Instancia del servicio de descuentos

        do {
            System.out.println("=== Menú de Descuentos ===");
            System.out.println("1. Crear Descuento");
            System.out.println("2. Leer Descuentos Activos");
            System.out.println("3. Actualizar Descuento");
            System.out.println("4. Eliminar Descuento");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Ingrese su opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    // Lógica para crear un descuento
                    Descuento nuevoDescuento = solicitarDatosDescuento(scanner);
                    descuentoService.crearDescuento(nuevoDescuento);
                    System.out.println("Descuento creado correctamente.");
                    break;
                case 2:
                    // Lógica para leer descuentos activos
                    List<Descuento> descuentos = descuentoService.obtenerDescuentosActivos();
                    System.out.println("=== Descuentos Activos ===");
                    for (Descuento descuento : descuentos) {
                        System.out.println(descuento);
                    }
                    break;
                case 3:
                    // Lógica para actualizar un descuento
                    System.out.print("Ingrese el ID del descuento a actualizar: ");
                    int descuentoIDActualizar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer de entrada
                    Descuento descuentoActualizar = descuentoService.obtenerDescuentoPorId(descuentoIDActualizar);
                    if (descuentoActualizar != null) {
                        Descuento descuentoActualizado = solicitarDatosDescuento(scanner);
                        descuentoActualizado.setDescuentoID(descuentoIDActualizar);
                        descuentoService.actualizarDescuento(descuentoActualizado);
                        System.out.println("Descuento actualizado correctamente.");
                    } else {
                        System.out.println("Descuento no encontrado.");
                    }
                    break;
                case 4:
                    // Lógica para eliminar un descuento
                    System.out.print("Ingrese el ID del descuento a eliminar: ");
                    int descuentoIDEliminar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer de entrada
                    descuentoService.eliminarDescuento(descuentoIDEliminar);
                    System.out.println("Descuento eliminado correctamente.");
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 0);
    }

    public static Descuento solicitarDatosDescuento(Scanner scanner) {
        System.out.print("Ingrese el tipo de descuento: ");
        String tipoDescuento = scanner.nextLine();
        System.out.print("Ingrese el valor del descuento: ");
        double valorDescuento = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer de entrada
        System.out.print("Ingrese el ID de la factura asociada: ");
        int facturaID = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada
        return new Descuento(0, tipoDescuento, valorDescuento, facturaID);
    }
}
