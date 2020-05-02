<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\User;
use App\Log;
use JWTAuth;
use Tymon\JWTAuth\Exceptions\JWTException;
use Tymon\JWTAuth\Exceptions\TokenExpiredException;
use Tymon\JWTAuth\Exceptions\TokenInvalidException;
use App\Http\Requests\LoginRequest;

class LoginController extends Controller{
    public $loginAfterSignUp = true;

    public function register(Request $request) {
        $user = new User();
        $user->name = $request->name;
        $user->email = $request->email;
        $user->password = bcrypt($request->password);
        $user->save();

        if ($this->loginAfterSignUp) {
            return $this->login($request);
        }

        return response()->json([
            'status' => 'ok',
            'data' => $user
        ], 200);
    }

    public function login(Request $request) {
        $input = $request->only('email', 'password');
        $jwt_token = null;
        if (!$jwt_token = JWTAuth::attempt($input)) {
            $message = 'Correo o contraseña no válidos';

            Log::create(['texto' => $message]);

            return response()->json([
                'status' => 'invalid_credentials',
                'message' => $message,
            ], 401);
        }

        Log::create(['texto' => "Login OK [$request->email]"]);

        $request->token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMC4xMC4xLjEyMDo4MDAwXC9hcGlcL2xvZ2luIiwiaWF0IjoxNTg3NDE3NDk5LCJleHAiOjE1ODc0MjEwOTksIm5iZiI6MTU4NzQxNzQ5OSwianRpIjoiS3lIS05WTVdnakFEZE1SbSIsInN1YiI6MSwicHJ2IjoiODdlMGFmMWVmOWZkMTU4MTJmZGVjOTcxNTNhMTRlMGIwNDc1NDZhYSJ9.pD6hsK5xUHMORh8VSRQ50yY-xgHWFffvQIMqzDqsQYQ";

        return response()->json([
            'status' => 'ok', 
            'token' => $jwt_token,
            'user' => auth()->user()
        ]);
    }

    public function getAuthenticatedUser(){
        try {
            if (!$user = JWTAuth::parseToken()->authenticate()) {
                return response()->json(['user_not_found'], 404);
            }
        } catch (TokenExpiredException $e) {
            return response()->json(['token_expired'], $e->getStatusCode());
        } catch (TokenInvalidException $e) {
            return response()->json(['token_invalid'], $e->getStatusCode());
        } catch (JWTException $e) {
            return response()->json(['token_absent'], $e->getStatusCode());
        }
        return $user;
    }

    public function logout(Request $request) {
        $this->validate($request, [
            'token' => 'required'
        ]);
        try {
            JWTAuth::invalidate($request->token);
            return response()->json([
                'status' => 'ok',
                'message' => 'Cierre de sesión exitoso.'
            ]);
        } catch (JWTException $exception) {
            return response()->json([
                'status' => 'unknown_error',
                'message' => 'Al usuario no se le pudo cerrar la sesión.'
            ], 500);
        }
    }

    public function getAuthUser(Request $request) {
        $this->validate($request, [
            'token' => 'required'
        ]);
        $user = JWTAuth::authenticate($request->token);
        return response()->json(['user' => $user]);
    }


    protected function jsonResponse($data, $code = 200){
        return response()->json(
            $data, $code,
            [
                'Content-Type' => 'application/json;charset=UTF8',
                'Charset' => 'utf-8'
            ],
            JSON_UNESCAPED_UNICODE);
    }
}
