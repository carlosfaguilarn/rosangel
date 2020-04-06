<!DOCTYPE html>
    <html lang="en">
    <head>
    <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="icon" type="image/png" href="{!! asset('img/tienda.jpg') !!}" />
        <title>Rosangel Store - @yield('title')</title>
        <link rel="stylesheet" href="{!! asset('css/adminlte.min.css') !!}">
        <link rel="stylesheet" href="{!! asset('css/store.css') !!}"> 
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="{!! asset('plugins/fontawesome-free/css/all.min.css') !!}">
        <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700">
        <link rel="stylesheet" href="{!! asset('plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css') !!}">
        <link rel="stylesheet" href="{!! asset('plugins/icheck-bootstrap/icheck-bootstrap.min.css') !!}">
        <link rel="stylesheet" href="{!! asset('plugins/jqvmap/jqvmap.min.css') !!}">
        <link rel="stylesheet" href="{!! asset('plugins/overlayScrollbars/css/OverlayScrollbars.min.css') !!}">
        <link rel="stylesheet" href="{!! asset('plugins/daterangepicker/daterangepicker.css') !!}">
        <link rel="stylesheet" href="{!! asset('plugins/summernote/summernote-bs4.css') !!}">
        <link rel="stylesheet" href="{!! asset('plugins/datatables-bs4/css/dataTables.bootstrap4.css') !!}">
        <link rel="stylesheet" href="{!! asset('toastr/toastr.css') !!}"> 
        <!-- Scripts -->
        <script src="{!! asset('plugins/jquery/jquery.min.js') !!}"></script>
        <script src="{!! asset('plugins/jquery-ui/jquery-ui.min.js') !!}"></script>
        <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
        <script> 
            $.widget.bridge('uibutton', $.ui.button)
        </script>
        <script src="{!! asset('plugins/bootstrap/js/bootstrap.bundle.min.js') !!}"></script>
        <script src="{!! asset('plugins/datatables/jquery.dataTables.js') !!}"></script>
        <script src="{!! asset('plugins/datatables-bs4/js/dataTables.bootstrap4.js') !!}"></script>
        <script src="{!! asset('plugins/chart.js/Chart.min.js') !!}"></script>
        <script src="{!! asset('plugins/sparklines/sparkline.js') !!}"></script>
        <script src="{!! asset('plugins/jqvmap/jquery.vmap.min.js') !!}"></script>
        <script src="{!! asset('plugins/jqvmap/maps/jquery.vmap.usa.js') !!}"></script>
        <script src="{!! asset('plugins/jquery-knob/jquery.knob.min.js') !!}"></script>
        <script src="{!! asset('plugins/moment/moment.min.js') !!}"></script>
        <script src="{!! asset('plugins/daterangepicker/daterangepicker.js') !!}"></script>
        <script src="{!! asset('plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js') !!}"></script>
        <script src="{!! asset('plugins/summernote/summernote-bs4.min.js') !!}"></script>
        <script src="{!! asset('plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js') !!}"></script>
        <script src="{!! asset('js/adminlte.js') !!}"></script>
        <script src="{!! asset('js/pages/dashboard.js') !!}"></script>
        <script src="{!! asset('js/demo.js') !!}"></script>
        <script src="{!! asset('js/store.js') !!}"></script>
        <!-- Select2 -->
        <script src="{!! asset('plugins/select2/js/select2.full.min.js') !!}"></script>
        <script src="{!! asset('toastr/toastr.js') !!}"></script> 
        @yield('scripts') 
    </head>
    <body class="hold-transition sidebar-mini layout-fixed">
        <div class="wrapper">
            <!-- MENÚ LATERAL IZQUIERDO -->
            <!-- Navbar -->
            <nav class="main-header navbar navbar-expand navbar-white navbar-light">
                <!-- Left navbar links -->
                <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" data-widget="pushmenu" href="#"><i class="fas fa-bars"></i></a>
                </li>
                <li class="nav-item d-none d-sm-inline-block">
                    <a href="./" class="nav-link">Inicio</a>
                </li>
                <li class="nav-item d-none d-sm-inline-block">
                    <a href="#" class="nav-link">@yield('seccion')</a>
                </li>
                </ul>

                <!-- SEARCH FORM -->
                <form class="form-inline ml-3">
                <div class="input-group input-group-sm">
                    <input class="form-control form-control-navbar" type="search" placeholder="Search" aria-label="Search">
                    <div class="input-group-append">
                    <button class="btn btn-navbar" type="submit">
                        <i class="fas fa-search"></i>
                    </button>
                    </div>
                </div>
                </form>

                <!-- Right navbar links -->
                <ul class="navbar-nav ml-auto">
                <!-- Messages Dropdown Menu -->
                <li class="nav-item dropdown">
                    <a class="nav-link" data-toggle="dropdown" href="#">
                    <i class="far fa-comments"></i>
                    <span class="badge badge-danger navbar-badge">3</span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                    <a href="#" class="dropdown-item">
                        <!-- Message Start -->
                        <div class="media">
                        <img src="dist/img/user1-128x128.jpg" alt="User Avatar" class="img-size-50 mr-3 img-circle">
                        <div class="media-body">
                            <h3 class="dropdown-item-title">
                            Brad Diesel
                            <span class="float-right text-sm text-danger"><i class="fas fa-star"></i></span>
                            </h3>
                            <p class="text-sm">Call me whenever you can...</p>
                            <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
                        </div>
                        </div>
                        <!-- Message End -->
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">
                        <!-- Message Start -->
                        <div class="media">
                        <img src="dist/img/user8-128x128.jpg" alt="User Avatar" class="img-size-50 img-circle mr-3">
                        <div class="media-body">
                            <h3 class="dropdown-item-title">
                            John Pierce
                            <span class="float-right text-sm text-muted"><i class="fas fa-star"></i></span>
                            </h3>
                            <p class="text-sm">I got your message bro</p>
                            <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
                        </div>
                        </div>
                        <!-- Message End -->
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">
                        <!-- Message Start -->
                        <div class="media">
                        <img src="dist/img/user3-128x128.jpg" alt="User Avatar" class="img-size-50 img-circle mr-3">
                        <div class="media-body">
                            <h3 class="dropdown-item-title">
                            Nora Silvester
                            <span class="float-right text-sm text-warning"><i class="fas fa-star"></i></span>
                            </h3>
                            <p class="text-sm">The subject goes here</p>
                            <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
                        </div>
                        </div>
                        <!-- Message End -->
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item dropdown-footer">See All Messages</a>
                    </div>
                </li>
                <!-- Notifications Dropdown Menu -->
                <li class="nav-item dropdown">
                    <a class="nav-link" data-toggle="dropdown" href="#">
                    <i class="far fa-bell"></i>
                    <span class="badge badge-warning navbar-badge">15</span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                    <span class="dropdown-item dropdown-header">15 Notifications</span>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">
                        <i class="fas fa-envelope mr-2"></i> 4 new messages
                        <span class="float-right text-muted text-sm">3 mins</span>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">
                        <i class="fas fa-users mr-2"></i> 8 friend requests
                        <span class="float-right text-muted text-sm">12 hours</span>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">
                        <i class="fas fa-file mr-2"></i> 3 new reports
                        <span class="float-right text-muted text-sm">2 days</span>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item dropdown-footer">See All Notifications</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-widget="control-sidebar" data-slide="true" href="#">
                    <i class="fas fa-th-large"></i>
                    </a>
                </li>
                </ul>
            </nav>
            <!-- /.navbar -->
            <!-- Main Sidebar Container -->
            <aside class="main-sidebar sidebar-dark-primary elevation-4">
                <!-- Brand Logo -->
                <a href="index3.html" class="brand-link"> 
                    <span class="brand-text font-weight-light">Rosangel Store</span>
                </a>

                <!-- Sidebar -->
                <div class="sidebar">
                <!-- Sidebar user panel (optional) -->
                <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                    <div class="image">
                    <img src="{!! asset('img/avatar3.png') !!}" class="img-circle elevation-2" alt="User Image">
                    </div>
                    <div class="info">
                    <a href="#" class="d-block">María Cebreros</a>
                    </div>
                </div>

                <!-- Sidebar Menu -->
                <nav class="mt-2">
                    <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                    <!-- Add icons to the links using the .nav-icon class
                        with font-awesome or any other icon font library -->
                    <!-- OPCION 1 DEL MENÚ-->
                    <li class="nav-item @if(request()->is('/inicio')) menu-open @endif">
                        <a href="{{ url('/') }}" class="nav-link">
                            <i class="nav-icon fas fa-chart-bar"></i>
                            <p>
                                Inicio 
                            </p>
                        </a>                     
                    </li>  
                    <!-- OPCION 2 DEL MENÚ-->
                    <li class="nav-item has-treeview @if(request()->is('clientes') || request()->is('clientes/agregar')) menu-open @endif">
                        <a class="nav-link">
                            <i class="nav-icon fas fa-users"></i>
                            <p>
                                Clientes
                                <i class="right fas fa-angle-left"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a href="{{ url('/clientes') }}" class="nav-link @if(request()->is('clientes')) link-active @endif">
                                    <i class="fas fa-list-ol nav-icon" style="font-size: 10px;"></i>
                                    <p>Listado</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="{{ url('/clientes/agregar') }}" class="nav-link @if(request()->is('clientes/agregar')) link-active @endif">
                                    <i class="fas fa-plus nav-icon" style="font-size: 10px;"></i>
                                    <p>Agregar cliente</p>
                                </a>
                            </li> 
                        </ul>
                    </li> 
                    <!-- OPCION 3 DEL MENÚ-->
                    <li class="nav-item has-treeview @if(request()->is('empleados') || request()->is('empleados/agregar') || request()->is('empleados/comisiones')) menu-open @endif">
                        <a class="nav-link">
                            <i class="nav-icon fas fa-user-tie"></i>
                            <p>
                                Empleados
                                <i class="right fas fa-angle-left"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="{{ url('empleados') }}" class="nav-link @if(request()->is('empleados')) link-active @endif">
                            <i class="fas fa-list-ol nav-icon" style="font-size: 10px"></i>
                            <p>Listado</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="{{ url('empleados/agregar') }}" class="nav-link @if(request()->is('empleados/agregar')) link-active @endif">
                            <i class="fas fa-plus nav-icon" style="font-size: 10px"></i>
                            <p>Agregar empleado</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="{{ url('empleados/comisiones') }}" class="nav-link @if(request()->is('empleados/comisiones')) link-active @endif">
                            <i class="fas fa-dollar-sign nav-icon" style="font-size: 10px"></i>
                            <p>Comisiones</p>
                            </a>
                        </li>
                        </ul>
                    </li>
                    <!-- OPCION 4 DEL MENÚ-->
                    <li class="nav-item has-treeview @if(request()->is('inventario') || request()->is('inventario/agregar')) menu-open @endif">
                        <a class="nav-link">
                        <i class="nav-icon fas fa-clipboard-list"></i>
                        <p>
                            Inventario
                            <i class="right fas fa-angle-left"></i>
                        </p>
                        </a>
                        <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="{{ url('inventario') }}" class="nav-link @if(request()->is('inventario')) link-active @endif">
                                <i class="fas fa-list-ol nav-icon" style="font-size: 10px;"></i>
                                <p>Listado</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="{{ url('inventario/agregar') }}" class="nav-link @if(request()->is('inventario/agregar')) link-active @endif">
                            <i class="fas fa-plus nav-icon" style="font-size: 10px;"></i>
                            <p>Agregar producto</p>
                            </a>
                        </li> 
                        </ul>
                    </li>
                    
                    <!-- OPCION 5 DEL MENÚ-->
                    <li class="nav-item @if(request()->is('pedidos')) menu-open @endif">
                        <a href="{{ url('pedidos') }}" class="nav-link">
                            <i class="nav-icon fas fa-cart-plus"></i>
                            <p>
                                Pedidos 
                            </p>
                        </a> 
                    </li> 
                </nav>
                <!-- /.sidebar-menu -->
                </div>
                <!-- /.sidebar -->
            </aside>
            <!-- CONTENIDO -->
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <div class="container-fluid">
                    <div class="row mb-2">
                        <div class="col-sm-6">
                            <h1>@yield('seccion')</h1>
                        </div>
                        <div class="col-sm-6">
                            <ol class="breadcrumb float-sm-right">
                                <li class="breadcrumb-item"><a href="#">@yield('seccion')</a></li>
                                <li class="breadcrumb-item">@yield('subseccion')</li>
                            </ol>
                        </div>
                    </div>
                </div><!-- /.container-fluid -->
            </section>
            <!-- Main content -->
            <section class="content">
                <div class="row">
                    <div class="col-12"> 
                        @yield('content')
                    </div>
                </div>
            </section>
        </div>  
    </body>
</html>