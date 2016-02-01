<?php
namespace Prototype\ExceptionHandler;

class ApiExceptionHandler {

    private function makeJsonResponse($code, $msg) {
        return \Response::json([
                "code" => $code,
                "message" => $msg,
        ]);
    }
    public function handleUserLoginException(){
        return $this->makeJsonResponse(401, "Username or email is wrong");
    }

}