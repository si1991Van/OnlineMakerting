<?php 
class ApiController extends BaseController {

    public function __construct() {
    	//
    }

    public function checkLogin($input)
    {
    	if (Auth::user()->attempt(Input::only('email', 'password'))){
            $userId = Auth::user()->get()->id;
            $sessionId = Common::getSessionId(Input::all(), $userId);
            return Common::returnData(200, 'Success', $userId, $sessionId);
		}
        throw new Prototype\Exceptions\UserLoginException();
    }

}
