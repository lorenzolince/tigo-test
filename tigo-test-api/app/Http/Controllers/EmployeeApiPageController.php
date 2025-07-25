<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\Http;
use App\Dtos\EmployeeDto;

class EmployeeApiPageController extends Controller
{
public function index()
{
    $baseUrl = config('api.url_base');

    $response = Http::get($baseUrl . '/api/employees');

    if ($response->successful()) {
        $rawEmployees = $response->json();

        $employees = array_map(function ($e) {
            return new EmployeeDto($e['id'], $e['name'], $e['role']);
        }, $rawEmployees);

        return view('employees.index', compact('employees'));
    }

    return view('employees.index')->withErrors('No se pudo obtener la lista de empleados desde la API.');
}


}
