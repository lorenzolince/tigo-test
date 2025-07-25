<?php

use Illuminate\Foundation\Application;
use Illuminate\Foundation\Configuration\Exceptions;
use Illuminate\Foundation\Configuration\Middleware;

return Application::configure(basePath: dirname(__DIR__))
    ->withRouting(
        web: __DIR__.'/../routes/web.php',
        api: __DIR__.'/../routes/api.php', // ✅ Añadido para cargar rutas API
        commands: __DIR__.'/../routes/console.php',
        health: '/up',
        apiPrefix: '/api' // ✅ Asegura que todas las rutas en api.php usen prefijo /api
    )
    ->withMiddleware(function (Middleware $middleware): void {
        // Puedes agregar middlewares personalizados aquí
    })
    ->withExceptions(function (Exceptions $exceptions): void {
        // Manejo de excepciones globales si lo necesitás
    })
    ->create();
