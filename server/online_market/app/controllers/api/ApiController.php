<?php 
class ApiController extends BaseController {

    public function __construct() {
    	//
    }

    public function checkLogin($input)
    {
        // dd(Auth::user()->attempt($input));
    	if (Auth::user()->attempt($input)){
            return Common::returnData(200, 'Success');
		}
        throw new Prototype\Exceptions\UserLoginException();

    }

}

