<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Venta extends Model
{
    protected $fillable = [
        'empleado_id', 'producto_id', 'importe', 'ganancia'
    ]; 
}
