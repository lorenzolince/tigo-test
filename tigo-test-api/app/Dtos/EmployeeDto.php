<?php

namespace App\Dtos;

class EmployeeDto
{
    public function __construct(
        public int $id,
        public string $name,
        public string $role
    ) {}
}