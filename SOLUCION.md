# Solucion Problema #1: El Videoclub de Don Mario

## Prompt usado

Resuelve el Problema #1 del README: crear un sistema de alquiler para peliculas fisicas y digitales, con disponibilidad, membresia basica o premium, recibo por consola, patrones de diseno, principios SOLID y pruebas de aceptacion.

## Como se resolvio

La solucion quedo en `src/main/java/eci/edu/byteProgramming/ejercicio/paper/Ejercicio_1`.

Se modelo una clase abstracta `Movie` con dos implementaciones: `PhysicalMovie` y `DigitalMovie`. Asi se aplica polimorfismo para que el recibo pueda tratar todas las peliculas como `Movie`, pero cada una responda su propio tipo.

Para las membresias se uso el patron Strategy mediante `MembershipStrategy`, `BasicMembership` y `PremiumMembership`. Tome esta decision porque el descuento cambia segun el tipo de cliente y puede crecer sin modificar el servicio de alquiler.

`RentalService` contiene el inventario y procesa la seleccion del cliente. Solo alquila peliculas disponibles e ignora selecciones invalidas para evitar que una entrada incorrecta rompa el flujo de consola.

`RentalReceipt` encapsula el resultado del alquiler: peliculas, subtotal, descuento y total. `ReceiptFormatter` se encarga de convertir ese resultado en el texto del recibo solicitado.

## Patrones y principios aplicados

- Strategy: separa el calculo del descuento segun la membresia.
- Polimorfismo: `PhysicalMovie` y `DigitalMovie` son tratadas como `Movie`.
- Encapsulamiento: los datos de peliculas y recibos se exponen mediante metodos controlados.
- Open/Closed: se pueden agregar nuevas membresias sin modificar `RentalService`.
- Single Responsibility: el servicio calcula el alquiler y el formatter imprime el recibo.
- Dependency Inversion: `RentalService` depende de la abstraccion `MembershipStrategy`, no de una membresia concreta.

## Evidencia de aceptacion

Se agregaron pruebas en `src/test/java/eci/edu/byteProgramming/ejercicio/paper/Ejercicio_1/RentalServiceAcceptanceTest.java`.

Las pruebas validan:

- Cliente Premium seleccionando `1,3`: subtotal `$13.000`, descuento `$2.600` y total `$10.400`.
- Cliente Basica pagando precio completo.
- Peliculas no disponibles, como `El Padrino`, no se incluyen en el recibo.
- Selecciones invalidas se ignoran sin afectar el resultado.
