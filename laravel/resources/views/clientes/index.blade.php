@extends('app')
@section('title', 'Administrador')
@section('seccion', 'Clientes')
@section('subseccion', 'Listado')
@section('content')
    @if(session()->has('message'))
        <script>  
            alertSuccess('¡El cliente se guardó correctamente!'); 
        </script> 
    @endif
    <div class="card">
        <div class="card-header">
            <h3 class="card-title">Listado de todos los clientes</h3>
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
                    @foreach($clientes as $cliente)
                        <tr>
                            <td>{{$cliente->id}}</td>
                            <td>{{$cliente->nombre}}</td>
                            <td>{{$cliente->direccion}}</td>
                            <td>{{$cliente->telefono}}</td>
                            <td>{{$cliente->revision}}</td>
                            <td>{{$cliente->facebook}}</td>
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