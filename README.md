## InvoiceMaxPro - Sistema de Gestión Integral
InvoiceMaxPro es un sistema de gestión integral diseñado para la administración de clientes, productos y facturas. Este proyecto se centra en propo
]rcionar herramientas robustas para la gestión de operaciones CRUD (Crear, Leer, Actualizar y Eliminar) relacionadas con clientes y productos, así como en la generación y almacenamiento de facturas.

Requerimientos Específicos
Módulo de Impuestos
El módulo de impuestos es fundamental para la generación de un listado anual que englobe todas las ventas realizadas. Este componente debe cumplir con los siguientes requisitos:

Presentar información detallada exigida por la Dirección de Impuestos y Aduanas Nacionales (DIAN), incluyendo nombre completo del cliente, apellido, número de documento, número de teléfono, dirección, número de factura, fecha de venta, valor total de la factura e impuesto pagado.
Realizar el cálculo del impuesto de IVA y recalcular el valor total a pagar con IVA, incorporando este valor como un campo adicional en la factura dentro de la base de datos.
Generar y enviar a la DIAN un archivo denominado "impuesto.json", que contenga la información mencionada anteriormente.
Asegurar que cada transacción realizada por un cliente figure de manera independiente en el archivo "impuesto.json".
Generar un único archivo para los impuestos que abarque el período fiscal correspondiente desde el 1 de enero hasta el 31 de diciembre del año anterior.
Registrar el valor del impuesto en una tabla, incluyendo el id, año, y porcentaje del impuesto, con la posibilidad de parametrizar este ajuste por año.
Módulo de Descuentos
El módulo de descuentos debe administrar distintas modalidades de descuentos, incluyendo porcentaje y monto fijo. Se establecen las siguientes condiciones:

Los descuentos activos se aplican al generar la factura, verificando la presencia de descuentos activos y aplicándolos todos a la vez.
Se permite la activación de múltiples descuentos simultáneamente.
Se establece un límite máximo del 100% de descuento para evitar acumulaciones excesivas que puedan generar deudas con los clientes.