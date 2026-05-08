# Solucion Problema #2: Tienda Virtual
13 minutos
## Prompt usado

Revisa el Problema #2 del README y corrige el codigo incompleto en `util` para implementar un sistema de pagos con multiples metodos, creacion de familias de objetos relacionados, notificacion automatica a inventario/facturacion/notificaciones, pruebas y documentacion de errores.

## Patrones identificados

Los dos patrones adecuados son:

- Abstract Factory: cada fabrica crea una familia compatible de objetos: `PaymentMethod` y su `ValidatePayment`.
- Observer: `ECIPayment` mantiene observadores y los notifica cuando un pago termina exitoso o fallido.

## Clases e interfaces corregidas o agregadas

- `PaymentFactory`: ahora crea el metodo de pago y su validador.
- `CreditCardFactory`, `PaypalFactory`, `CryptoFactory`: ahora son fabricas reales.
- `CreditCardPayment`, `PaypalPayment`, `CryptoPayment`: representan la ejecucion concreta del pago.
- `CreditCardValidator`, `PaypalValidator`, `CryptoValidator`: encapsulan las reglas de validacion.
- `InventoryPaymentObserver`, `FacturationPaymentObserver`, `NotificationPaymentObserver`: permiten que cada modulo reaccione al evento sin acoplarse al core.
- `ECIPayment`: procesa pagos usando abstracciones y notifica a sus observadores.

## Errores encontrados

- Faltaba `PaymentFactory`, por eso `ECIPayment` no compilaba.
- `PaymentEventObserver` importaba `javax.management.Notification` en vez de la clase `Notification` del proyecto.
- Las clases llamadas `CreditCardFactory`, `PaypalFactory` y `CryptoFactory` no eran fabricas: extendian `PaymentMethod`.
- `PaymentMethod` recibia `customerID`, pero el constructor guardaba mal el parametro.
- La validacion estaba mezclada con la ejecucion del pago, lo cual rompia la idea de crear familias pago + validador.
- El core no demostraba claramente la notificacion a multiples modulos independientes.

## Validacion del diagrama/contexto

El contexto del README es suficiente para identificar Abstract Factory y Observer. Aun asi, el codigo inicial no reflejaba bien el diagrama esperado porque no separaba fabrica, metodo de pago y validador. La solucion conserva el paquete `util`, pero aclara esas responsabilidades en clases separadas.

## Evidencia de aceptacion

Se agregaron pruebas en `src/test/java/eci/edu/byteProgramming/ejercicio/paper/util/ECIPaymentAcceptanceTest.java`.

Las pruebas validan:

- Pago exitoso con tarjeta: descuenta inventario, genera factura y envia correo de confirmacion.
- Pago fallido con PayPal: no descuenta stock, no genera factura y envia notificacion de fallo.
- Extensibilidad del Observer: se agrega un `AuditObserver` sin modificar `ECIPayment`.

Ejecucion validada con:

```text
.\mvnw.cmd test
Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```
