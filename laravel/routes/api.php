<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

Route::post('/pedidos/crear', 'PedidoController@store');
Route::get('/pedidos', 'PedidoController@pedidos');
Route::get('/productos', 'InventarioController@productos');
Route::get('/clientes', 'ClienteController@clientes');

Route::post('/login', 'LoginController@login');
