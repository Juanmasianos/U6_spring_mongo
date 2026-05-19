# Desarrollo e Implementación los modelos del carrito

## Separación de rutas y colecciones:
Creé los controllers "/v1/cart" y "/v2/cart" que delegan a servicios distintos. En la MongoDB, esto se almacena en dos colecciones separadas: "cartsOne" y "cartsTwo".

## V1 Documentos Embebidos 
   Al añadir un producto en la V1, el backend busca el producto en la base de datos y clona sus datos dentro del propio array del carrito. Al hacer el checkout, se ahorra el buscar  para saber el importe total..

### Flujo de ejecucion

   **1. Controlador(CartVersionOneController)**

   Recibe las peticiones HTTP (como /api/v1/cart/add).

   Delega la lógica de negocio a CartServiceOne y OrderService.

   **2. Capa de Servicio (CartServiceOneImpl)**

   Añadir Producto: Al iniciar addProduct, el servicio realiza una consulta única a la colección products con findById. Extrae los campos name y price del producto y los mete directamente dentro de la lista de ítems de CartVersionOne.

   Persistencia: Guarda completo el carrito en la colección cartsOne.

   **3. Proceso de Checkout**

   Lectura Eficiente: Recupera el carrito de la colección cartsOne. Al contener ya los precios embebidos, el cálculo del importe total se realiza inmediatamente sin hacer mas consultas.

   Validación de Stock: Realiza consultas individuales a la colección products únicamente para verificar que el stock disponible sea suficiente antes de crear el pedido.

## V2 Almacenar solo los IDs
   En la V2, el carrito esta hecho para que sea muy ligero, guardando solo el ID del producto y la cantidad. Esto hizo que que la lógica del servicio de checkout sea más compleja, yendo producto por producto haciendo consultas a la colección "products" para saber cuánto cuestan las cosas y porder calcular el total.

### Flujo de Ejecución 

   **1. Capa de Controlador (CartVersionTwoController)**

   Recibe las peticiones HTTP en la ruta /api/v2 (/api/v2/cart/add por ejemplo).


   **2. Capa de Servicio (CartServiceTwoImpl)**

   Añadir Producto: Al ejecutar addProduct, el servicio realiza una comprobación rápida de existencia (existsById) en la colección de productos. Si el producto es válido, inserta el id dentro del array del carrito y actualiza la cantidad.

   Persistencia: Guarda el documento en la colección cartsTwo.

   **3. Proceso de Checkout**

   Lookup Manual en Capa de Aplicación: Al procesar el pago, el servicio recupera el carrito e itera sobre cada ítem.

   Por cada producto único en el carrito, se ejecuta una consulta a la base de datos. Esto hace que se obtenga el precio vigente de mercado y calcular el total real de la orden.