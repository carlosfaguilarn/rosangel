@extends('app')
@section('title', 'Administrador')
@section('seccion', 'Inventario')
@section('subseccion', 'Listado')
@section('content')
    <div class="card">
        <div class="card-header">
            <h3 class="card-title">Listado de todos los productos</h3>
        </div>
        <div class="card-body">
            <table id="example1" class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Descripción</th>
                        <th>Marca</th>
                        <th>Existencia</th>
                    </tr>
                </thead>
                <tbody>
                    @foreach($productos as $producto)
                        <tr>
                            <td>{{$producto->id}}</td>
                            <td>{{$producto->descripcion}}</td>
                            <td>{{$producto->marca}}</td>
                            <td>{{$producto->existencia}}</td>
                        </tr>
                    @endforeach
                </tbody>
            </table>
        </div>
    </div>
@endsection
