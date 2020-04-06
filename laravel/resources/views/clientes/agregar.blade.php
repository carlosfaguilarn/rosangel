@extends('app')
@section('title', 'Administrador')
@section('seccion', 'Clientes')
@section('subseccion', 'Agregar')
@section('content')
    <div class="card">
        <!--
        <div class="card-header">
            <h3 class="card-title">Agregar un nuevo cliente</h3>
        </div> 
        -->
        <div class="card-body">
            <form method="POST" action="{{ url('clientes/save') }}">
                @csrf
                <div class="row"> 
                    <div class="col-md-6">
                        <!-- Nombre -->
                        <div class="form-group">
                            <label>Nombre:</label>
                            <div class="input-group"> 
                                <input name="nombre" type="text" class="form-control">
                            </div> 
                        </div>  

                        <!-- Apellidos -->
                        <div class="form-group">
                            <label>Apellidos:</label>
                            <div class="input-group"> 
                                <input name="apellidos" type="text" class="form-control">
                            </div> 
                        </div>  

                        <!-- Cuenta Facebook -->
                        <div class="form-group">
                            <label>Cuenta Facebook:</label>
                            <div class="input-group"> 
                                <input name="facebook" type="text" class="form-control">
                            </div> 
                        </div>  
                    </div>

                    <div class="col-md-6">
                        <!-- Dirección -->
                        <div class="form-group">
                            <label>Dirección:</label>
                            <div class="input-group"> 
                                <input name="direccion" type="text" class="form-control">
                            </div> 
                        </div>  

                        <!-- phone mask -->
                        <div class="form-group">
                            <label>Teléfono:</label> 
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-phone"></i></span>
                                </div>
                                <input name="telefono" type="text" class="form-control" data-inputmask='"mask": "(999) 999-9999"' data-mask>
                            </div>
                        </div> 
    
                        <!-- Revisión -->
                        <div class="form-group">
                            <label>Revisión:</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="far fa-calendar-alt"></i>
                                    </span>
                                </div>
                                <input name="revision" type="text" class="form-control float-right">
                            </div>
                        </div> 
                    </div>
                    
                    <div style="width: 100%; margin-top: 30px; text-align: center"> 
                        <button type="submit" class="btn btn-primary pull-right" style="margin-right: 5px;">
                            <i class="fa fa-save"></i>&nbsp;&nbsp; Guardar
                        </button>
                    </div>  
                </div> 
            </form>
            <div style="width: 100%;"> 
                <button id="notificacion" onclick="
                    alertSuccess('¡El cliente se guardó correctamente!');
                ">
                    </i>&nbsp;&nbsp; Notificacion
                </button>
            </div> 
        </div> 
    </div>

    <!-- SCRIPTS -->
    <script>
        $(function () {
            //Initialize Select2 Elements
            $('.select2').select2()

            //Initialize Select2 Elements
            $('.select2bs4').select2({
            theme: 'bootstrap4'
            })

            //Datemask dd/mm/yyyy
            $('#datemask').inputmask('dd/mm/yyyy', { 'placeholder': 'dd/mm/yyyy' })
            //Datemask2 mm/dd/yyyy
            $('#datemask2').inputmask('mm/dd/yyyy', { 'placeholder': 'mm/dd/yyyy' })
            //Money Euro
            $('[data-mask]').inputmask()

            //Date range picker
            $('#reservation').daterangepicker()
            //Date range picker with time picker
            $('#reservationtime').daterangepicker({
            timePicker: true,
            timePickerIncrement: 30,
            locale: {
                format: 'MM/DD/YYYY hh:mm A'
            }
            })
            //Date range as a button
            $('#daterange-btn').daterangepicker({
                ranges   : {
                'Today'       : [moment(), moment()],
                'Yesterday'   : [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                'Last 7 Days' : [moment().subtract(6, 'days'), moment()],
                'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                'This Month'  : [moment().startOf('month'), moment().endOf('month')],
                'Last Month'  : [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                },
                startDate: moment().subtract(29, 'days'),
                endDate  : moment()
            },function (start, end) {
                $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
            });

            //Timepicker
            $('#timepicker').datetimepicker({
                format: 'LT'
            });
            
            //Bootstrap Duallistbox
            $('.duallistbox').bootstrapDualListbox()

            //Colorpicker
            $('.my-colorpicker1').colorpicker()
            //color picker with addon
            $('.my-colorpicker2').colorpicker()

            $('.my-colorpicker2').on('colorpickerChange', function(event) {
                $('.my-colorpicker2 .fa-square').css('color', event.color.toString());
            });

            $("input[data-bootstrap-switch]").each(function(){
                $(this).bootstrapSwitch('state', $(this).prop('checked'));
            });  
        }) 
    </script>
@endsection

@section('scripts')
    <!-- jQuery -->
    <script src="{!! asset('plugins/jquery/jquery.min.js') !!}"></script>
    <!-- Bootstrap 4 -->
    <script src="{!! asset('plugins/bootstrap/js/bootstrap.bundle.min.js') !!}"></script>
    <!-- Select2 -->
    <script src="{!! asset('plugins/select2/js/select2.full.min.js') !!}"></script>
    <!-- Bootstrap4 Duallistbox -->
    <script src="{!! asset('plugins/bootstrap4-duallistbox/jquery.bootstrap-duallistbox.min.js') !!}"></script>
    <!-- InputMask -->
    <script src="{!! asset('plugins/moment/moment.min.js') !!}"></script>
    <script src="{!! asset('plugins/inputmask/min/jquery.inputmask.bundle.min.js') !!}"></script>
    <!-- date-range-picker -->
    <script src="{!! asset('plugins/daterangepicker/daterangepicker.js') !!}"></script>
    <!-- bootstrap color picker -->
    <script src="{!! asset('plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js') !!}"></script>
    <!-- Tempusdominus Bootstrap 4 -->
    <script src="{!! asset('plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js') !!}"></script>
    <!-- Bootstrap Switch -->
    <script src="{!! asset('plugins/bootstrap-switch/js/bootstrap-switch.min.js') !!}"></script>
    <!-- AdminLTE App -->
    <script src="{!! asset('dist/js/adminlte.min.js') !!}"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="{!! asset('dist/js/demo.js') !!}"></script>
    <!-- Page script -->
    <script>
    $(function () {
        //Initialize Select2 Elements
        $('.select2').select2()
        //Initialize Select2 Elements
        $('.select2bs4').select2({
            theme: 'bootstrap4'
        }) 
        //Datemask dd/mm/yyyy
        $('#datemask').inputmask('dd/mm/yyyy', { 'placeholder': 'dd/mm/yyyy' })
        //Datemask2 mm/dd/yyyy
        $('#datemask2').inputmask('mm/dd/yyyy', { 'placeholder': 'mm/dd/yyyy' })
        //Money Euro
        $('[data-mask]').inputmask()

        //Date range picker
        $('#reservation').daterangepicker()
        //Date range picker with time picker
        $('#reservationtime').daterangepicker({
            timePicker: true,
            timePickerIncrement: 30,
            locale: {
                format: 'MM/DD/YYYY hh:mm A'
            }
        })
        //Date range as a button
        $('#daterange-btn').daterangepicker({
            ranges   : {
            'Today'       : [moment(), moment()],
            'Yesterday'   : [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
            'Last 7 Days' : [moment().subtract(6, 'days'), moment()],
            'Last 30 Days': [moment().subtract(29, 'days'), moment()],
            'This Month'  : [moment().startOf('month'), moment().endOf('month')],
            'Last Month'  : [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
            },
            startDate: moment().subtract(29, 'days'),
            endDate  : moment()
        },function (start, end) {
            $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
        })

        //Timepicker
        $('#timepicker').datetimepicker({
            format: 'LT'
        })
        
        //Bootstrap Duallistbox
        $('.duallistbox').bootstrapDualListbox()

        //Colorpicker
        $('.my-colorpicker1').colorpicker()
        //color picker with addon
        $('.my-colorpicker2').colorpicker()

        $('.my-colorpicker2').on('colorpickerChange', function(event) {
        $('.my-colorpicker2 .fa-square').css('color', event.color.toString());
        });

        $("input[data-bootstrap-switch]").each(function(){
            $(this).bootstrapSwitch('state', $(this).prop('checked'));
        });
    })
    </script>
@endsection