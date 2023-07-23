# Hashing
Implementación de una tabla hash utilizando direccionamiento abierto
con doble hashing como estrategia para resolver colisiones. 

La tabla hash se compone de objetos de la clase Celda, los cuales almacenan la clave, el valor y el estado de cada elemento.
Se configuran tanto la capacidad de la tabla como el umbral máximo del factor de carga.

La función de hash utilizada en este enfoque combina dos funciones: H1(clave) y H2(clave,
colisiones). La primera función, H1(clave), se calcula como la clave módulo N, donde N es la
capacidad de la tabla. La segunda función, H2(clave, colisiones), se obtiene multiplicando el número
de colisiones por (7 - (clave mod 7)). Esta combinación de funciones permite dispersar los elementos
de manera eficiente en la tabla hash.

Antes de insertar un dato, se realiza una verificación para evitar exceder el umbral de carga. En caso
de que se supere el umbral, se procede a redimensionar la tabla: duplicar el tamaño de la tabla original, buscar el siguiente número primo por encima de la
nueva capacidad, recorrer la tabla original e insertar los datos en la nueva tabla ampliada, insertar el
dato que provocó el redimensionamiento y finalmente reemplazar la tabla original por la nueva tabla
ampliada.
