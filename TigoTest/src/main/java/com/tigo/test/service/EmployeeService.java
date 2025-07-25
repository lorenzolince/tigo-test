/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tigo.test.service;

import com.tigo.test.config.dto.EmployeeDto;
import java.util.List;

/**
 *
 * @author loren
 */
public interface EmployeeService {

    List<EmployeeDto> getAllEmployees();

    boolean save(EmployeeDto dto);

    boolean deleteById(int id);


}
