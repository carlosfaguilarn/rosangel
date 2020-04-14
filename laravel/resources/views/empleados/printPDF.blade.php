<!-- Main content --> 
<link rel="stylesheet" href="{{public_path('css/pdf.css') }}">  
        

<div class="invoice p-3 mb-3">
<!-- title row -->
<div class="row">
    <div class="col-12"> 
    <h4 style="margin: 0px; padding: 0px">
        <img style="width: 30px; heigh: 20px;" src="{{public_path('img/R_blanca.png')}}" />
        Rosangel Store, Inc.
        <small class="float-right">Fecha: 2/10/2014</small>
    </h4>
    </div> 
    <!-- /.col -->
</div>
<!-- info row -->
<div class="row invoice-info">
    <div class="col-sm-4 invoice-col">
   
    </div>
</div>
<!-- /.row -->

<!-- Table row -->
<div class="row">
    <div class="col-12 table-responsive">
    <table class="table table-striped">
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
    <!-- /.col -->
</div>
<!-- /.row -->

<div class="row">
    <!-- accepted payments column -->
    <div class="col-6">
        
    </div>
    <!-- /.col -->
    <div class="col-6 float-right">
        <p class="lead">Resumen de pagos</p> 
        <div class="table-responsive">
            <table class="table">
            <tr>
                <th style="width:50%">Subtotal:</th>
                <td>$250.30</td>
            </tr>
            <tr>
                <th>Tax (9.3%)</th>
                <td>$10.34</td>
            </tr>
            <tr>
                <th>Shipping:</th>
                <td>$5.80</td>
            </tr>
            <tr>
                <th>Total:</th>
                <td>$265.24</td>
            </tr>
            </table>
        </div>
    </div>
    <!-- /.col -->
</div>
<!-- /.row --> 
<button class="btn btn-primary">
    Prueba
</button>