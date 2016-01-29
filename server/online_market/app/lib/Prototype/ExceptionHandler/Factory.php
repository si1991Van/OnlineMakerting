<?php
namespace Prototype\ExceptionHandler;

class Factory {
    public static function createHanlder(){
        if (preg_match('/\/api\//',\Request::url())){
            return new ApiExceptionHandler();
        }
        return new WebExceptionHandler();
    }
}