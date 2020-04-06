<?php

namespace App\Http\Controllers;

use App\Empleado;
use App\Rol;
use App\Venta;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class EmpleadoController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {  
        $empleados = DB::table("empleados")
            ->leftJoin('roles', 'roles.id', '=', 'empleados.rol_id')
            ->select('empleados.*', 'roles.descripcion')
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
        $roles = DB::table('roles')->get();
        return view('empleados.agregar', compact('roles'));
    }

    /**
     * Comisiones de los empleados
     *
     * @return \Illuminate\Http\Response
     */
    public function comisiones()
    {
        $comisiones = DB::select("
            select empleados.id, empleados.nombre, empleados.apellidos, empleados.comision,
                format(sum(ventas.importe), 2) as 'total_vendido', 
                format(sum(ventas.ganancia), 2) as 'ganancias_generadas',
                COUNT(*) as 'productos_vendidos',
                format(sum(ventas.importe) * (empleados.comision/100), 2) as 'total_pagar'
            from ventas
                LEFT JOIN empleados on empleados.id = ventas.empleado_id
            GROUP BY empleados.id
        ");

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
