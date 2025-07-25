/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tigo.test.service;

import com.tigo.test.config.dto.EmployeeDto;
import com.tigo.test.model.Employee;
import com.tigo.test.repository.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author loren
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return repository.findAll().stream()
                .map(e -> new EmployeeDto(e.getId(), e.getName(), e.getRole()))
                .toList();

    }
    @Transactional
    @Override
    public boolean save(EmployeeDto dto) {
        try {
            Employee entity = new Employee();
            entity.setId(dto.getId());
            entity.setName(dto.getName()); 
            entity.setRole(dto.getRole());
            repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Transactional
    @Override
    public boolean deleteById(int id) {
        if (!repository.existsById(id)) {
            return false;
        }
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
