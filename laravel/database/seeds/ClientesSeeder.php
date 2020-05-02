<?php

use Illuminate\Database\Seeder;

class ClientesSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('clientes')->insert([
            'nombre' => 'Maria Cebreros',
            'direccion' => 'Alamos dos',
            'telefono' => '6682121212',
            'revision' => '10/10/2019',
            'facebook' => 'Maria E. Cebreros'
        ]);

        DB::table('clientes')->insert([
            'nombre' => 'Carlos Aguilar',
            'direccion' => 'Las Cerezas',
            'telefono' => '6682229752',
            'revision' => '11/12/2019',
            'facebook' => 'Carlos Aguilar'
        ]);
    }
}
