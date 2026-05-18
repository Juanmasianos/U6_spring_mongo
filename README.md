## Desarrollo e Implementación del Proyecto

Para poder comparar ambos modelos en igualdad de condiciones, estructuramos el código de la siguiente manera:

1. **Separación de rutas y colecciones:** Creamos controladores específicos (`/api/v1/cart` y `/api/v2/cart`) que atacan a servicios totalmente distintos. En la base de datos MongoDB, esto se traduce en dos colecciones separadas: `carts_v1` y `carts_v2`.

2. **Lógica de la v1 (Datos duplicados por rendimiento):** 
   Al añadir un producto en la V1, el backend busca el producto en la base de datos y clona sus datos (nombre y precio) dentro del propio array del carrito. Al hacer el checkout, el proceso es rapidísimo porque el carrito es "autosuficiente" para saber el importe total, aunque tuvimos que programar una validación extra para comprobar que el stock en la colección de productos fuera correcto antes de cerrar el pedido.

3. **Lógica de la v2 (IDs para asegurar consistencia):**
   En la V2, el carrito se programó para que sea lo más ligero posible, guardando solo el ID del producto y la cantidad. Esto nos obligó a que la lógica del servicio de checkout sea más compleja: el código tiene que ir producto por producto haciendo consultas a la colección principal de `products` para enterarse de cuánto cuestan las cosas en ese preciso momento, calcular el total y restar el stock todo a la vez.