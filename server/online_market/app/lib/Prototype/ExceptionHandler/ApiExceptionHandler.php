<?php
namespace Prototype\ExceptionHandler;

class ApiExceptionHandler {

    private function makeJsonResponse($code, $msg, $data = null) {
        return \Response::json([
                "code" => $code,
                "message" => $msg,
                "data" => $data,
        ]);
    }

    public function handleUserLoginException(){
        return $this->makeJsonResponse(401, "Username or email is wrong");
    }

    public function handleUserSessionErrorException(){
        return $this->makeJsonResponse(402, "User session is wrong");
    }

    public function handleDeviceErrorException(){
        return $this->makeJsonResponse(403, "DeviceID is wrong");
    }

    public function handleUserRegisterException(){
        return $this->makeJsonResponse(404, "Email is wrong or registered");
    }

}
