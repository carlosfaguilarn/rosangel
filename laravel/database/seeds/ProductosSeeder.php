<?php

use Illuminate\Database\Seeder;

class ProductosSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('productos')->insert([
            'descripcion' => 'Licuadora',
            'marca' => 'Oster',
            'existencia' => 8
        ]);
        DB::table('productos')->insert([
            'descripcion' => 'Cafetera',
            'marca' => 'Oster',
            'existencia' => 6
        ]);
        DB::table('productos')->insert([
            'descripcion' => 'Colchón',
            'marca' => 'Spring air',
            'existencia' => 9
        ]);
        DB::table('productos')->insert([
            'descripcion' => 'Microondas',
            'marca' => 'LG',
            'existencia' => 3
        ]);
    }
}
