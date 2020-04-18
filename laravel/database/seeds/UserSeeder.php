<?php

use Illuminate\Database\Seeder;

class UserSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('users')->insert(['name' => 'carlos', 'email' => 'carlos@gmail.com', 'password' => '123']);
        DB::table('users')->insert(['name' => 'maria', 'email' => 'maria@gmail.com', 'password' => '321']);
    }
}
