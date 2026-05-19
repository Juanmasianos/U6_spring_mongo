# Comparacion

| Estrategia | Ventajas | Desventajas | Rendimiento | Complejidad | Casos de Uso Ideal |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Embedded** (Documentos Embebidos) | • Lectura rapida.<br>• No requiere lookup.<br> | • Duplica datos.<br>• Tamaño del documento mayor.<br>• El precio y nombre no cambia dentro. | • Alto en lectura.<br>• Medio en escritura. | Baja, toda la información está en la misma coleccion. | E-commerce tradicionales donde el usuario necesita ver el precio exacto al que añadió el producto. |
| **IDs** (Referencias Manuales) | • Documentos muy ligeros.<br>• Consistencia total de los datos (precio/stock siempre al día).<br>• Evita datos duplicados. | • Requiere consultas extra para pintar el carrito o procesar el pago. | • Alto en escritura.<br>• Bajo/Medio en lectura (por la sobrecarga de consultas). | Media, requiere lógica en el backend para consultar las colecciones. | Aplicaciones donde los datos del producto (precio, stock) cambian constantemente. |
| **DBRef** (Referencias Formales) | • Vinculación formal y estandarizada entre colecciones. | • Mayor consumo de espacio que los IDs manuales.<br>• Peor rendimiento sobre las referencias manuales. | • Bajo/Medio (Similar a IDs, pero añade sobrecarga al parsear la estructura del DBRef). | Media-Alta, es driver-dependiente, añade metadatos como `$ref` y `$id`. | Sistemas complejos donde un ítem del carrito puede referenciar a colecciones totalmente distintas. |
| **Items separados** (Colección independiente) | • Gestiona ítems de forma aislada y flexible.<br> |• Requiere transacciones ACID para asegurar que el carrito y sus ítems estén sincronizados.<br>| Medio (Muchas operaciones de lectura/escritura por separado.) | Alta, hay que gestionar múltiples escrituras en colecciones distintas simultáneamente. | Carritos masivos o "Listas de deseos" gigantescas donde un usuario puede tener miles de artículos. |

---

El enfoque Embedded (V1) clona los datos del producto dentro del carrito, permitiendo lecturas mas rapidas. La estrategia de IDs (V2) crea documentos ligeros y consistencia en tiempo real al almacenar solo referencias, aunque se queda algo mas atras en el rendimiento en el checkout debido a tener que consultar cada producto.

Por otro lado(aunque en esta practica no los he utilizado), DBRef(V3) formaliza estas referencias añadiendo metadatos, pero se encuentra en desuso frente a los IDs manuales por que sobrecarga innecesariamente el sistema y finalmente, el modelado con Ítems separados(V4) rompe la filosofía NoSQL para ofrecer flexibilidad en carritos, requiriendo transacciones complejas para asegurar la integridad que los documentos embebidos resuelven.

