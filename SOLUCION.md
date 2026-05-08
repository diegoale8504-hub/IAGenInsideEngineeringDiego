# Solucion Problema #1: El Videoclub de Don Mario
12 minutos
## Prompt usado

EN el readme de este repositorio tengo dos problemas vamos a hacer uno primero:Problema #1: "El Videoclub de Don Mario"                                                          
Contexto limitado: solo enunciado                                                                                                                                                
Sin documentación adicional: aparte de su conocimiento previo                                                                                                                    
Desafío: formular buenos prompts sin información visual o técnica previa Este es el enfoque del problema 1Problema #1: El Videoclub de Don                                       
Duración: Máximo 15 minutos                                                                                                                                                      
Don Mario acaba de abrir un videoclub moderno donde los clientes pueden alquilar peliculas fisicas o digitales. El problema es que su sistema anterior era un caos: todos los    
precios se calculaban igual sin importar el tipo de pelicula o membresia del cliente, y no habia forma de saber que peliculas estaban disponibles en tiempo real.

Tu Mision                                                                                                                                                                        
Ayuda a Don Mario creando un sistema de alquiler que permita:

Registrar peliculas (fisicas o digitales) con su disponibilidad.                                                                                                                 
Que el cliente elija X peliculas para alquilar.                                                                                                                                  
Calcular el precio total segun su tipo de membresia:                                                                                                                             
Basica: precio normal.                                                                                                                                                           
Premium: 20% de descuento.                                                                                                                                                       
Mostrar al finalizar un recibo con las peliculas, precio por unidad y total.                                                                                                     
Peliculas Disponibles                                                                                                                                                            
[Fisica] Interestellar - $8.000 - Disponible                                                                                                                                     
[Fisica] El Padrino - $7.000 - No disponible                                                                                                                                     
[Digital] Inception - $5.000 - Disponible                                                                                                                                        
[Digital] Matrix - $6.000 - Disponible                                                                                                                                           
Caso de Ejemplo                                                                                                                                                                  
Membresia del cliente: Premium                                                                                                                                                   
Seleccione peliculas (numeros separados por coma): 1,3

--- RECIBO DE ALQUILER ---                                                                                                                                                       
Cliente: Premium                                                                                                                                                                 
Peliculas:                                                                                                                                                                       
- Interestellar (Fisica) - $8.000                                                                                                                                               
- Inception (Digital) - $5.000                                                                                                                                                  
Subtotal: $13.000                                                                                                                                                                
Descuento (20%): $2.600                                                                                                                                                          
Total a pagar: $10.400
   --------------------------                                                                                                                                                       
¡Disfrute su pelicula!                                                                                                                                                           
Objetivos del Ejercicio                                                                                                                                                          
Identificar cual o cuales patrones de diseno utilizar.                                                                                                                           
Explicar que principios de SOLID se aplican.                                                                                                                                     
Aplicar polimorfismo y encapsulamiento.                                                                                                                                          
Colocar evidencia de la ejecucion del ejercicio (ejecucion por consola; no es necesario hacer front). lo debes de crear en la carpeta                                            
src/main/java/eci.edu.bytePrograming.ejercicio.paper.ejercicio1 debes de seguir muy bien todo el contexto de como es el problema y de como deben de ser los outputs
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
