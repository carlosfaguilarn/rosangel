<?php

namespace App\Http\Controllers;

use App\Empleado;
use App\Rol;
use App\Venta;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Barryvdh\DomPDF\Facade as PDF;

class EmpleadoController extends Controller
{
    public $query_comisiones = "
        select empleados.id as 'empleado_id', empleados.nombre, empleados.apellidos, empleados.comision,
            format(sum(ventas.importe), 2) as 'total_vendido', 
            format(sum(ventas.ganancia), 2) as 'ganancias_generadas',
            COUNT(*) as 'productos_vendidos',
            format(sum(ventas.importe) * (empleados.comision/100), 2) as 'total_pagar'
        from ventas
            LEFT JOIN empleados on empleados.id = ventas.empleado_id
        GROUP BY empleados.id
    ";
    
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {  
        $empleados = DB::table("empleados")
            ->leftJoin('rols', 'rols.id', '=', 'empleados.rol_id')
            ->select('empleados.*', 'rols.descripcion')
            ->get();

        return view('empleados.index', compact('empleados'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function agregar()
    {
        $roles = Rol::all();
        return view('empleados.agregar', compact('roles'));
    }

    /**
     * Comisiones de los empleados
     *
     * @return \Illuminate\Http\Response
     */
    public function comisiones()
    {
        $comisiones = DB::select($this->query_comisiones);
        return view('empleados.comisiones', compact('comisiones'));
    }

    /**
     * Almacenar un empleado recién creado en almacenamiento.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $empleado = new Empleado();
        $empleado->nombre = $request->input('nombre');
        $empleado->apellidos = $request->input('apellidos');
        $empleado->direccion = $request->input('direccion');
        $empleado->telefono = $request->input('telefono');
        $empleado->comision = $request->input('comision');
        $empleado->rol_id = $request->input('rol_id');
        $empleado->save();

        return redirect()
                ->action('EmpleadoController@index')
                ->with(['message'=>'¡El empleado se guardó correctamente!']);
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function previewPDF()
    {
        $comisiones = DB::select($this->query_comisiones);
        return redirect()
            ->action('EmpleadoController@printPDF');
        //return view('empleados.previewPDF', compact('comisiones'));
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function printPDF()
    {
        $comisiones = DB::select($this->query_comisiones);
        $pdf = PDF::loadView('empleados.printPDF', compact('comisiones')); 
        return $pdf->download('comision-list.pdf');
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
