<?php namespace Prototype\Filters;

class ApiFilters {
    public function auth() {
        // $header_check = array();
        // $header_check[0] = \Request::header("os");
        // if($header_check[0] != 1 && $header_check[0] != 2)
        // {
        //     throw new \Prototype\Exceptions\ValidationHeaderException();
        // }
        // $login = \Login::where("session_id", $session)->first();
        // if (!$login || $login->expired_at < \Carbon\Carbon::now()) \Prototype\Common\ThrowCommonExceptions::throwUnAuthenticate();
    }
}
