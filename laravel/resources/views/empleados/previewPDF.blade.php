@extends('app')
@section('title', 'Administrador')
@section('seccion', '')
@section('subseccion', 'PDF')
@section('content')
  <div class="col-12">
      <div class="callout callout-info">
        <h5><i class="fas fa-info"></i> Nota:</h5>
        Esta página es una vista previa, si la información es correcta da click al botón Imprimir PDF.
      </div>
      
      @include('empleados.printPDF')

      <div class="row no-print">
        <div class="col-12">
            <div style="width: 100%; margin-top: 30px; text-align: right"> 
                <a href="{{url('empleados/printPDF')}}" class="btn btn-default pull-right" style="margin-right: 5px;">
                    <i class="fas fa-print"></i>&nbsp;&nbsp; Imprimir
                </a>
            </div>  
        </div>
    </div> 
  </div>  
@endsection