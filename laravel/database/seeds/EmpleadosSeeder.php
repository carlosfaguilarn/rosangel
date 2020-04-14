<?php

use Illuminate\Database\Seeder;

class EmpleadosSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('empleados')->insert([
            'nombre' => 'Maria', 
            'apellidos' => 'Cebreros', 
            'direccion' => 'Laureles 1487, Alamos dos',
            'telefono' => '6682323243', 
            'comision' => '10',
            'rol_id' => 1
        ]); 

        DB::table('empleados')->insert([
            'nombre' => 'Carlos', 
            'apellidos' => 'Aguilar', 
            'direccion' => 'América 2497, Las Cerezas',
            'telefono' => '6682229752', 
            'comision' => '10',
            'rol_id' => 2
        ]); 

        DB::table('empleados')->insert([
            'nombre' => 'Angel', 
            'apellidos' => 'Cebreros', 
            'direccion' => 'Batamote 1323, Los angeles',
            'telefono' => '6682323232', 
            'comision' => '30',
            'rol_id' => 3
        ]); 
    }
}