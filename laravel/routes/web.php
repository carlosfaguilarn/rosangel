<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/



Route::get('/laravel', function () {
    return view('welcome');
});

Route::get('/', function () {
    return redirect('/inicio');
});
Route::resource('/inicio', 'DashboardController');

Route::get('/clientes', 'ClienteController@index');
Route::get('/clientes/agregar', 'ClienteController@agregar');
Route::post('/clientes/save', 'ClienteController@store');

Route::get('/empleados', 'EmpleadoController@index');
Route::get('/empleados/agregar', 'EmpleadoController@agregar');
Route::get('/empleados/comisiones', 'EmpleadoController@comisiones');
Route::post('/empleados/save', 'EmpleadoController@store');

Route::get('/pedidos', 'PedidoController@index');
Route::get('/inventario', 'InventarioController@index');
Route::get('/inventario/agregar', 'InventarioController@agregar');