package com.tigo.test.steps.bdd;

import com.tigo.test.steps.bdd.mock.EmployeeServiceMockConfig;
import com.tigo.test.config.dto.EmployeeDto;
import com.tigo.test.service.EmployeeService;
import io.cucumber.java.en.*;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.springframework.test.context.TestPropertySource;

/**
 * Clase de pasos BDD para pruebas de empleado
 *
 * @author lorenzolince
 */
@CucumberContextConfiguration
@ContextConfiguration(classes = EmployeeServiceMockConfig.class)
@TestPropertySource("classpath:application.properties")

public class EmployeeSteps {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeSteps.class);

    @Autowired
    private EmployeeService employeeService;

    private EmployeeDto employee;
    private boolean result;
    private List<EmployeeDto> employeeList;

    @Given("tengo un empleado con nombre {string} y rol {string}")
    public void tengo_un_empleado(String name, String role) {
        employee = new EmployeeDto(0, name, role);
        logger.info("? Preparado empleado → Nombre: {}, Rol: {}", name, role);
    }

    @When("guardo el empleado")
    public void guardo_el_empleado() {
        result = employeeService.save(employee);
        logger.info("💾 Guardado empleado → Resultado: {}", result);
    }

    @Then("el resultado debe ser exitoso")
    public void resultado_exitoso() {
        logger.info("✅ Verificando que el resultado fue exitoso");
        Assertions.assertTrue(result);
    }

    @When("solicito la lista de empleados")
    public void solicito_lista() {
        employeeList = employeeService.getAllEmployees();
        logger.info("📋 Solicitud de lista de empleados → Cantidad recibida: {}", employeeList.size());
    }

    @Then("recibo una lista con al menos 1 empleado")
    public void lista_con_empleados() {
        if (employeeList != null) {
            for (EmployeeDto emp : employeeList) {
                logger.debug("👤 Empleado → ID: {}, Nombre: {}, Rol: {}", emp.getId(), emp.getName(), emp.getRole());
            }
        }
        Assertions.assertFalse(employeeList.isEmpty());
    }

    @Given("existe un empleado con ID {int}")
    public void existe_empleado_con_id(int id) {
        employee = new EmployeeDto(id, "Mock Name", "Mock Role");
        boolean saved = employeeService.save(employee);
        logger.info("🧾 Creado mock empleado con ID {} → Guardado: {}", id, saved);
    }

    @When("elimino el empleado con ID {int}")
    public void elimino_empleado(int id) {
        result = employeeService.deleteById(id);
        logger.info("❌ Eliminado empleado con ID {} → Resultado: {}", id, result);
    }
}
