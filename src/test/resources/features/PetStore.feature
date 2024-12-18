
  Feature: Gestión de Órdenes en PetStore API

    Background:
      Given la url es "https://petstore.swagger.io/v2"

    @petstore
    Scenario Outline: Crear Orden
      When creo la orden de pedido mascota  con '<id>' con '<petId>' y '<cantidad>'
      Then valido el codigo de respuesa sea 200
      And valido el estado de la orden sea "placed"
      Examples:
        | id | petId | cantidad |
        | 50 | 1     | 10       |
        | 60 | 3     | 20       |


    @petstore
    Scenario Outline: Consulta de Orden por ID
      When consulto una orden con el '<orderId>'
      Then el código de respuesta es <responseCode>
      And el ID de la orden en la respuesta es "<orderId>"
      And el petID de la orden es "<petId>"
      Examples:
        | orderId | responseCode | petId |
        | 50      | 200          | 1     |
        | 60      | 200          | 3     |

    @petstore
    Scenario Outline: Consulta de una orden con un orderId inexistente
      When consulto una orden con el '<orderId>'
      Then el código de respuesta es <responseCode>
      And el mensaje de error en la respuesta es "Order not found"
      Examples:
        | orderId         | responseCode |
        | 500000000000000 | 404          |

