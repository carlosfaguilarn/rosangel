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
        DB::table('roles')->insert(['descripcion' => 'Administrador']);
        DB::table('roles')->insert(['descripcion' => 'Supervisor']);
        DB::table('roles')->insert(['descripcion' => 'Vendedor']);
    }
}
