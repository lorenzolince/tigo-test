<?php

namespace App\Http\Controllers;

use App\Services\EmployeeService;

class EmployeePageController extends Controller
{
    protected EmployeeService $service;

    public function __construct(EmployeeService $service)
    {
        $this->service = $service;
    }

    public function index()
    {
        $employees = $this->service->getEmployees(); // Retorna DTOs directamente
        return view('employees.index', compact('employees'));
    }
}