@extends('app')
@section('title', 'Administrador')
@section('seccion', 'Pedidos')
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
                        <th>Producto</th> 
                        <th>Cliente</th>  
                        <th>Empleado</th>  
                        <th>Dirección</th>  
                        <th>Teléfono</th>  
                        <th>Observaciones</th>  
                    </tr>
                </thead>
                <tbody>  
                    @foreach($pedidos as $pedido)
                        <tr>
                            <td>{{$pedido->id}}</td>
                            <td>{{$pedido->producto}}</td>
                            <td>{{$pedido->cliente}}</td>
                            <td>{{$pedido->empleado}}</td>
                            <td>{{$pedido->direccion}}</td>
                            <td>{{$pedido->telefono}}</td>
                            <td>{{$pedido->observaciones}}</td>
                        </tr>
                    @endforeach
                </tbody> 
            </table>
        </div> 
    </div>
@endsection