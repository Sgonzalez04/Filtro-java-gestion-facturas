CREATE TABLE Clientes (
    ClienteID INT PRIMARY KEY,
    NombreCompleto VARCHAR(100),
    Apellido VARCHAR(100),
    NumeroDocumento VARCHAR(20),
    NumeroTelefono VARCHAR(20),
    Direccion VARCHAR(255)
);

CREATE TABLE Productos (
    ProductoID INT PRIMARY KEY,
    Nombre VARCHAR(100),
    PrecioUnitario DECIMAL(10, 2)
);

CREATE TABLE Facturas (
    FacturaID INT PRIMARY KEY,
    ClienteID INT,
    FechaVenta DATE,
    ValorTotal DECIMAL(10, 2),
    ImpuestoPagado DECIMAL(10, 2),
    FOREIGN KEY (ClienteID) REFERENCES Clientes(ClienteID)
);

CREATE TABLE Impuestos (
    ImpuestoID INT PRIMARY KEY,
    ClienteID INT,
    FacturaID INT,
    NumeroFactura VARCHAR(50),
    FechaVenta DATE,
    ValorTotalFactura DECIMAL(10, 2),
    ImpuestoPagado DECIMAL(10, 2),
    FOREIGN KEY (ClienteID) REFERENCES Clientes(ClienteID),
    FOREIGN KEY (FacturaID) REFERENCES Facturas(FacturaID)
);

CREATE TABLE Descuentos (
    DescuentoID INT PRIMARY KEY,
    TipoDescuento VARCHAR(20),
    ValorDescuento DECIMAL(10, 2),
    FacturaID INT,
    FOREIGN KEY (FacturaID) REFERENCES Facturas(FacturaID)
);

CREATE TABLE DetallesFactura (
    DetalleID INT PRIMARY KEY,
    FacturaID INT,
    ProductoID INT,
    Cantidad INT,
    FOREIGN KEY (FacturaID) REFERENCES Facturas(FacturaID),
    FOREIGN KEY (ProductoID) REFERENCES Productos(ProductoID)
);

-- Inserción de datos de clientes
INSERT INTO clientes (nombre, apellido, documento, telefono, direccion) VALUES
('Juan', 'Perez', '123456789', '555-1234', 'Calle Principal 123'),
('María', 'López', '987654321', '555-5678', 'Avenida Central 456');

-- Inserción de datos de productos
INSERT INTO productos (nombre, precio) VALUES
('Producto A', 50.00),
('Producto B', 75.00),
('Producto C', 100.00);

-- Inserción de datos de facturas
INSERT INTO facturas (cliente_id, fecha_venta, valor_total, impuesto_pagado) VALUES
(1, '2023-01-15', 150.00, 25.00),
(2, '2023-02-20', 200.00, 35.00);

-- Inserción de datos de impuestos
INSERT INTO impuestos (cliente_id, factura_id, numero_factura, fecha_venta, valor_total_factura, impuesto_pagado) VALUES
(1, 1, 'FAC-001', '2023-01-15', 150.00, 25.00),
(2, 2, 'FAC-002', '2023-02-20', 200.00, 35.00);

-- Inserción de datos de descuentos
INSERT INTO descuentos (tipo_descuento, valor_descuento, factura_id) VALUES
('Porcentaje', 10.00, 1),
('Monto Fijo', 20.00, 2);
