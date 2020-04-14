<?php

use Illuminate\Database\Seeder;

class RolesSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('rols')->insert(['descripcion' => 'Administrador']);
        DB::table('rols')->insert(['descripcion' => 'Supervisor']);
        DB::table('rols')->insert(['descripcion' => 'Vendedor']);
    }
}
