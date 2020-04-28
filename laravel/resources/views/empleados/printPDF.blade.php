<!-- Estilos -->
<style>
    .clearfix:after {
  content: "";
  display: table;
  clear: both;
}

.pdf-body {
    position: relative;
    width: auto;
    height: 29.7cm;
    margin: 10px 25px;
    padding: 20px;
    color: #001028;
    background: #FFFFFF;
    font-family: Arial, sans-serif;
    font-size: 12px;
    font-family: Arial;
}

header {
  padding: 10px 0;
  margin-bottom: 30px;
}

.logotipo{
  display: inline-flex;
}
.logo {
  width: 30px;
  height: 20px;
  text-align: left;
}

.pdf-title {
  color: #5D6975;
  font-size: 1.4em;
  line-height: 1.4em;
  font-weight: normal;
}

#project {
  float: left;
}

#project span {
  color: #5D6975;
  text-align: right;
  width: 52px;
  margin-right: 10px;
  display: inline-block;
  font-size: 0.8em;
}

#company {
  float: right;
  text-align: right;
}

#project div,
#company div {
  white-space: nowrap;
}

.pdf-table {
  width: 100%;
  border-collapse: collapse;
  border-spacing: 0;
  margin-bottom: 20px;
}

.pdf-table tr:nth-child(2n-1) td {
  background: #F5F5F5;
}

.pdf-table th,
.pdf-table td {
  text-align: center;
}

.pdf-table  th {
  padding: 5px 20px;
  color: #5D6975;
  border-bottom: 1px solid #C1CED9;
  white-space: nowrap;
  font-weight: normal;
}

.pdf-table .service,
.pdf-table .desc {
  text-align: left;
}

.pdf-table td {
  text-align: right;
}

.pdf-table td.service,
.pdf-table td.desc {
  vertical-align: top;
}

.pdf-table td.unit,
.pdf-table td.qty,
.pdf-table td.total {
  font-size: 1.2em;
}

.pdf-table td.grand {
  border-top: 1px solid #5D6975;;
}

#notices .notice {
  color: #5D6975;
  font-size: 1.2em;
}

.pdf-footer {
  color: #5D6975;
  width: 100%;
  height: 30px;
  position: absolute;
  bottom: 0;
  border-top: 1px solid #C1CED9;
  padding: 8px 0;
  text-align: center;
}

</style>
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
<a class="pdf-a"> Prueba </a>
