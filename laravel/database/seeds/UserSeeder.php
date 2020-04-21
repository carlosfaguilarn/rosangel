<?php

use Illuminate\Database\Seeder;
use App\User;

class UserSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        User::create([
            'name' => 'Carlos Francisco Aguilar',
            'email' => 'carlos',
            'password' => '123',
            'photo' => 'carlos.png'
        ]);
        User::create([
            'name' => 'María Cebreros Miranda',
            'email' => 'maria',
            'password' => '321',
            'photo' => 'maria.png'
        ]);
    }
}
