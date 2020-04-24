@extends('app')
@section('title', 'Administrador')
@section('seccion', 'Dashboard de Rosangel Store')
@section('subseccion', 'dashboard')
@section('content')
<div class="container-fluid">
    <!-- Small boxes (Stat box) -->

    <div class="row">
      <div class="col-lg-3 col-6">
        <!-- small box -->
        <div class="small-box bg-blanca">
          <div class="inner">
            <h3>{{$data['pedidos']}}</h3>
            <p>Nuevos Pedidos</p>
          </div>
          <div class="icon">
            <i class="ion ion-bag color-danger"></i>
          </div>
          <a href="#" class="small-box-footer bg-ft-danger">Más información <i class="fas fa-arrow-circle-right"></i></a>
        </div>
      </div>
      <!-- ./col -->
      <div class="col-lg-3 col-6">
        <!-- small box -->
        <div class="small-box bg-blanca">
          <div class="inner">
            <h3>${{$data['ventas']}}</h3>
            <p>Ventas</p>
          </div>
          <div class="icon">
            <i class="ion ion-stats-bars color-success"></i>
          </div>
          <a href="#" class="small-box-footer bg-ft-success">Más información <i class="fas fa-arrow-circle-right"></i></a>
        </div>
      </div>
      <div class="col-lg-3 col-6">
        <!-- small box -->
        <div class="small-box bg-blanca">
          <div class="inner">
            <h3>${{$data['ganancias']}}</h3>
            <p>Ganancias</p>
          </div>
          <div class="icon">
            <i class="ion ion-stats-bars color-danger"></i>
          </div>
          <a href="#" class="small-box-footer bg-ft-warning">Más información <i class="fas fa-arrow-circle-right"></i></a>
        </div>
      </div>
      <!-- ./col -->
      <div class="col-lg-3 col-6">
        <!-- small box -->
        <div class="small-box bg-blanca">
          <div class="inner">
            <h3>{{$data['clientes']}}</h3>
            <p>Clientes</p>
          </div>
          <div class="icon">
            <i class="ion ion-person-add color-info"></i>
          </div>
          <a href="{{url('clientes')}}" class="small-box-footer bg-ft-info">Más información <i class="fas fa-arrow-circle-right"></i></a>
        </div>
      </div>
      <!-- ./col
      <div class="col-lg-3 col-6">
        <!-- small box
        <div class="small-box bg-danger">
          <div class="inner">
            <h3></h3>
            <p>Productos vendidos</p>
          </div>
          <div class="icon">
            <i class="ion ion-pie-graph"></i>
          </div>
          <a href="#" class="small-box-footer">Más información <i class="fas fa-arrow-circle-right"></i></a>
        </div>
      </div>
       ./col -->
    </div>
    <!-- /.row -->

    <!-- GRÁFICAS -->
    <div class="row">
        <!-- Left col -->
        <section class="col-lg-7 connectedSortable">
          <!-- Custom tabs (Charts with tabs)-->
          <div class="card">
            <div class="card-header">
              <h3 class="card-title">
                <i class="fas fa-chart-pie mr-1"></i>
                Sales
              </h3>
              <div class="card-tools">
                <ul class="nav nav-pills ml-auto">
                  <li class="nav-item">
                    <a class="nav-link active" href="#revenue-chart" data-toggle="tab">Area</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#sales-chart" data-toggle="tab">Donut</a>
                  </li>
                </ul>
              </div>
            </div><!-- /.card-header -->
            <div class="card-body">
              <div class="tab-content p-0">
                <!-- Morris chart - Sales -->
                <div class="chart tab-pane active" id="revenue-chart"
                     style="position: relative; height: 300px;">
                    <canvas id="revenue-chart-canvas" height="300" style="height: 300px;"></canvas>
                 </div>
                <div class="chart tab-pane" id="sales-chart" style="position: relative; height: 300px;">
                  <canvas id="sales-chart-canvas" height="300" style="height: 300px;"></canvas>
                </div>
              </div>
            </div><!-- /.card-body -->
          </div>
          <!-- /.card -->


        </section>
        <!-- /.Left col -->
        <!-- right col (We are only adding the ID to make the widgets sortable)-->
        <section class="col-lg-5 connectedSortable">
          <!-- Map card -->
          <div class="card bg-gradient-primary">
            <div class="card-header border-0">
              <!-- card tools -->
              <div class="card-tools">
                <button type="button"
                        class="btn btn-primary btn-sm daterange"
                        data-toggle="tooltip"
                        title="Date range">
                  <i class="far fa-calendar-alt"></i>
                </button>
                <button type="button"
                        class="btn btn-primary btn-sm"
                        data-card-widget="collapse"
                        data-toggle="tooltip"
                        title="Collapse">
                  <i class="fas fa-minus"></i>
                </button>
              </div>
              <!-- /.card-tools -->
            </div>

            <!-- /.card-body-->
            <div class="card-footer bg-transparent">
              <div class="row">
                <div class="col-4 text-center">
                  <div id="sparkline-1"></div>
                </div>
                <!-- ./col -->
                <div class="col-4 text-center">
                  <div id="sparkline-2"></div>
                </div>
                <!-- ./col -->
                <div class="col-4 text-center">
                  <div id="sparkline-3"></div>
                </div>
                <!-- ./col -->
              </div>
              <!-- /.row -->
            </div>
          </div>
          <!-- /.card -->


          <!-- Calendar -->
          <div class="card bg-gradient-success">
            <div class="card-header border-0">

              <h3 class="card-title">
                <i class="far fa-calendar-alt"></i>
                Calendar
              </h3>
              <!-- tools card -->
              <div class="card-tools">
                <!-- button with a dropdown -->
                <div class="btn-group">
                  <button type="button" class="btn btn-success btn-sm dropdown-toggle" data-toggle="dropdown">
                    <i class="fas fa-bars"></i></button>
                  <div class="dropdown-menu float-right" role="menu">
                    <a href="#" class="dropdown-item">Add new event</a>
                    <a href="#" class="dropdown-item">Clear events</a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">View calendar</a>
                  </div>
                </div>
                <button type="button" class="btn btn-success btn-sm" data-card-widget="collapse">
                  <i class="fas fa-minus"></i>
                </button>
                <button type="button" class="btn btn-success btn-sm" data-card-widget="remove">
                  <i class="fas fa-times"></i>
                </button>
              </div>
              <!-- /. tools -->
            </div>
            <!-- /.card-header -->
            <div class="card-body pt-0">
              <!--The calendar -->
              <div id="calendar" style="width: 100%"></div>
            </div>
            <!-- /.card-body -->
          </div>
          <!-- /.card -->
        </section>
        <!-- right col -->
      </div>
@endsection
