@extends('app')
@section('title', 'Administrador')
@section('seccion', 'Empleados')
@section('subseccion', 'Comisiones')
@section('content')
    <div class="card">
        <div class="card-header">
            <h3 class="card-title float-right"> 
                <a href="{{url('empleados/export-excel')}}" class="btn btn-light" style="margin-right: 5px; border: 1px solid grey">
                    <i class="far fa-file-excel" style="color: #A35E8B"></i>&nbsp;&nbsp; Exportar Excel
                </a>
                <a href="{{url('empleados/previewPDF')}}" class="btn btn-light" style="margin-right: 5px; border: 1px solid grey">
                    <i class="far fa-file-pdf" style="color: #A35E8B"></i>&nbsp;&nbsp; Exportar PDF
                </a>
            </h3>
        </div> 
        <div class="card-body">
            <table id="example1" class="table table-bordered table-striped">
                <thead>
                    <tr> 
                        <th>ID</th>   
                        <th>Empleado</th>   
                        <th>Productos Vendidos</th> 
                        <th>Total Vendido</th>    
                        <th>Ganancias Generadas</th>    
                        <th>Porcentaje Comision</th>    
                        <th>Total a Pagar</th>    
                    </tr>
                </thead>
                <tbody>   
                    @foreach($comisiones as $comision)
                        <tr> 
                            <td class="left">{{$comision->empleado_id}}</td>
                            <td class="left">{{$comision->nombre}} {{$comision->apellidos}}</td>
                            <td class="center">{{$comision->productos_vendidos}}</td>
                            <td class="right">${{$comision->total_vendido}}</td>
                            <td class="right">${{$comision->ganancias_generadas}}</td>
                            <td class="center">{{$comision->comision}}%</td>
                            <td class="right">${{$comision->total_pagar}}</td>
                        </tr>
                    @endforeach
                </tbody> 
            </table> 
        </div>  
    </div> 
    <div style="width: 100%; margin-top: 30px; text-align: center"> 
        <a href="{{url('empleados/previewPDF')}}" class="btn btn-primary pull-right" style="margin-right: 5px;">
            <i class="fas fa-file-pdf"></i>&nbsp;&nbsp; Exportar PDF
        </a>
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