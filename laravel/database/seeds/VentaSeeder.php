<?php

use Illuminate\Database\Seeder;

class VentaSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('ventas')->insert([
            'empleado_id' => 1,
            'producto_id' => 1,
            'importe' => 230.50,
            'ganancia' => 140.30,
            'fecha' => '2020/04/06',
        ]);
        
        DB::table('ventas')->insert([
            'empleado_id' => 1,
            'producto_id' => 2,
            'importe' => 130.50,
            'ganancia' => 30.30,
            'fecha' => '2020/04/06',
        ]);

        DB::table('ventas')->insert([
            'empleado_id' => 2,
            'producto_id' => 1,
            'importe' => 230.50,
            'ganancia' => 140.30,
            'fecha' => '2020/04/06',
        ]);

        DB::table('ventas')->insert([
            'empleado_id' => 3,
            'producto_id' => 6,
            'importe' => 530.50,
            'ganancia' => 340.30,
            'fecha' => '2020/04/06',
        ]);
    }
}
