Feature: Gestión de empleados

  Scenario: Crear un nuevo empleado
    Given tengo un empleado con nombre "Valeria" y rol "Frontend Lead"
    When guardo el empleado
    Then el resultado debe ser exitoso

  Scenario: Obtener todos los empleados
    When solicito la lista de empleados
    Then recibo una lista con al menos 1 empleado

  Scenario: Eliminar un empleado por ID
    Given existe un empleado con ID 99
    When elimino el empleado con ID 99
    Then el resultado debe ser exitoso
