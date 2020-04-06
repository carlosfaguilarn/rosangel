@extends('app')
@section('title', 'Administrador')
@section('seccion', 'Empleados')
@section('subseccion', 'Listado')
@section('content') 
    @if(session()->has('message'))
        <script>  
            alertSuccess("{{session('message')}}"); 
        </script> 
    @endif
    <div class="card">
        <div class="card-header">
            <h3 class="card-title">Listado de todos los empleados</h3>
        </div> 
        <div class="card-body">
            <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>ID</th> 
                    <th>Nombre</th> 
                    <th>Apellidos</th> 
                    <th>Dirección</th> 
                    <th>Teléfono</th>    
                    <th>Rol</th>    
                </tr>
                </thead>
                <tbody>   
                    @foreach($empleados as $empleado)
                        <tr>
                            <td>{{$empleado->id}}</td>
                            <td>{{$empleado->nombre}}</td>
                            <td>{{$empleado->apellidos}}</td>
                            <td>{{$empleado->direccion}}</td>
                            <td>{{$empleado->telefono}}</td>
                            <td>{{$empleado->descripcion}}</td>
                        </tr>
                    @endforeach
                </tbody> 
            </table> 
        </div>  
    </div> 
@endsection 

@section('scripts') 
    <script>
        $(function () { 
            $("#example1").DataTable();
            $('#example2').DataTable({
                "paging": true,
                "lengthChange": false,
                "searching": false,
                "ordering": true,
                "info": true,
                "autoWidth": false,
            });
        });
    </script>  
@endsection