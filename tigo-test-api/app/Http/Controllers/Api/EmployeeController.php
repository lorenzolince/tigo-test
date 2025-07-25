<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Services\EmployeeService;

class EmployeeController extends Controller
{
    protected EmployeeService $service;

      public function __construct(EmployeeService $service)
    {
        $this->service = $service;
    }

    public function index()
    {
         $dtos = $this->service->getEmployees(); // Aquí retornás DTOs si ya tenés ese paso
        return response()->json($dtos);
    }
}