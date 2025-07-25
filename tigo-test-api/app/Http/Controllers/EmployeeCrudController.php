<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Http;
use App\Dtos\EmployeeDto;

class EmployeeCrudController extends Controller
{
    public function index()
    {
        $baseUrl = config('api.url_base_external');

        $response = Http::get($baseUrl . '/api/employees');

        if ($response->successful()) {
            $rawEmployees = $response->json();

            $employees = array_map(function ($e) {
                return new EmployeeDto($e['id'], $e['name'], $e['role']);
            }, $rawEmployees);

            return view('crud_emp.index', compact('employees'));
        }

        return view('crud_emp.index')->withErrors('No se pudo obtener la lista de empleados.');
    }

    public function store(Request $request)
    {
        $baseUrl = config('api.url_base_external');

        $payload = [
            'id' => $request->input('id'),
            'name' => $request->input('name'),
            'role' => $request->input('role'),
        ];

        $response = Http::post($baseUrl . '/api/employees/save', $payload);

        if ($response->successful() && $response->json() === true) {
            return redirect()->back()->with('success', 'Empleado agregado.');
        }

        return redirect()->back()->withErrors('No se pudo agregar el empleado.');
    }

    public function destroy($id)
    {
        $baseUrl = config('api.url_base_external');

        $response = Http::post($baseUrl . "/api/employees/delete/{$id}");

        if ($response->successful() && $response->json() === true) {
            return redirect()->back()->with('success', 'Empleado eliminado.');
        }

        return redirect()->back()->withErrors('No se pudo eliminar el empleado.');
    }
}