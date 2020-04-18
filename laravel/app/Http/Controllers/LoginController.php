<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use JWTAuth;
use Tymon\JWTAuth\Exceptions\JWTException;
use App\Http\Requests\LoginRequest;

class LoginController extends Controller
{
    public function login(Request $request) {
        $input = $request->only('email', 'password');
        $jwt_token = null;
        try{
            if($jwt_token = JWTAuth::attempt($input)){
                return response()->json([
                    'status' => 'ok',
                    'token' => $jwt_token,
                ]);
            }
            return response()->json([
                'status' => 'invalid_credentials',
                'message' => 'Usuario o contraseña incorrecta',
                'data' => $input
            ],401);
        } catch (JWTException $exception) {
            return response()->json([
                'status' => 'error_inesperado',
                'message' => 'Ha ocurrido un error inesperado',
                'error' => $exception
            ],401);
        }
    }
}
