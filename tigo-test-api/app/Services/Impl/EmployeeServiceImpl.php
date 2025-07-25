<?php

namespace App\Services\Impl;

use App\Services\EmployeeService;
use App\Daos\EmployeeDao;
use App\Dtos\EmployeeDto;
use App\Models\EmployeeModel;

class EmployeeServiceImpl implements EmployeeService
{
    protected EmployeeDao $dao;

    public function __construct()
    {
        $this->dao = new EmployeeDao();
    }
     /**
     * @return EmployeeModel[]
     */
   public function getEmployees(): array
   {
    $models = $this->dao->getAll();

    return array_map(function (EmployeeModel $employee) {
        return new EmployeeDto(
            $employee->id,
            $employee->name,
            $employee->role
        );
    }, $models);
   }

}