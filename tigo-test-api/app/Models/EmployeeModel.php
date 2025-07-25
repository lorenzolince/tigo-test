<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class EmployeeModel extends Model
{
    protected $table = 'employees'; // nombre exacto de tu tabla
    public $timestamps = false;     // si no usás created_at / updated_at

    protected $fillable = [
        'id', 'name', 'role'
    ];
}