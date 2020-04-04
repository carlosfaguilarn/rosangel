@extends('app')
@section('title', 'Administrador')
@section('seccion', 'Clientes')
@section('subseccion', 'Listado')
@section('content')
    <div class="card">
        <div class="card-header">
            <h3 class="card-title">Listado de todos los pedidos</h3>
        </div> 
        <div class="card-body">
            <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>ID</th> 
                    <th>Nombre</th> 
                    <th>Dirección</th> 
                    <th>Teléfono</th> 
                    <th>Revisión</th> 
                    <th>Facebook</th> 
                </tr>
                </thead>
                <tbody>    
                </tbody> 
            </table>
        </div> 
    </div>
@endsection