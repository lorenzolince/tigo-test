<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\EmployeePageController;
use App\Http\Controllers\EmployeeApiPageController;
use App\Http\Controllers\EmployeeCrudController;

Route::get('/', function () {
    return view('welcome');
});
Route::get('/employees', [EmployeePageController::class, 'index']);


Route::get('/employees-api', [EmployeeApiPageController::class, 'index']);

Route::get('/employees-crud', [EmployeeCrudController::class, 'index']);

Route::post('/employees-crud/create', [EmployeeCrudController::class, 'store']);

Route::post('/employees-crud/{id}', [EmployeeCrudController::class, 'destroy']);

