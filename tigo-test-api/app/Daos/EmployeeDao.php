<?php

namespace App\Daos;

use App\Models\EmployeeModel;

class EmployeeDao
{
    /**
     * @return EmployeeModel[]
     */
    public function getAll(): array
    {
        // Si estás usando base de datos:
        // Retornás los modelos como array plano
        return EmployeeModel::all()->all(); // <-- Convierte Collection a array de objetos

        // Si estás en modo dummy, usás los quemados:
        // return [
        //     new EmployeeModel(['id' => 1, 'name' => 'Lorenzo González', 'role' => 'DevOps Architect']),
        //     new EmployeeModel(['id' => 2, 'name' => 'Valeria Torres', 'role' => 'Frontend Lead']),
        //     new EmployeeModel(['id' => 3, 'name' => 'Samuel Rivera', 'role' => 'QA Analyst']),
        // ];
    }
}