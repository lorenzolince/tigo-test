/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tigo.test.config.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author loren
 */
@Data
@AllArgsConstructor
public class EmployeeDto {

    private int id;
    private String name;
    private String role;

}
