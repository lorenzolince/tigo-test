package com.tigo.test.steps.bdd.mock;

import com.tigo.test.config.dto.EmployeeDto;
import com.tigo.test.service.EmployeeService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author loren
 */
@TestConfiguration
public class EmployeeServiceMockConfig {

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeServiceImpl(); // Usamos la clase real, no el mock
    }

    static class EmployeeServiceImpl implements EmployeeService {
        private final List<EmployeeDto> data = new ArrayList<>();

        @Override
        public List<EmployeeDto> getAllEmployees() {
            return new ArrayList<>(data); // copia para evitar efectos laterales
        }

        @Override
        public boolean save(EmployeeDto dto) {
            data.removeIf(e -> e.getId() == dto.getId());
            data.add(dto);
            return true;
        }

        @Override
        public boolean deleteById(int id) {
            return data.removeIf(e -> e.getId() == id);
        }
    }
}
