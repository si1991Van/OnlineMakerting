<?php

class RegisterController extends ApiController {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index()
	{
		return Common::returnData(200, SUCCESS);
	}


	/**
	 * Store a newly created resource in storage.
	 *
	 * @return Response
	 */
	public function store()
	{
		$rules = array(
            'email'      => 'required|email|unique:users',
            'password'   => 'required',
        );
        $input = Input::all();
        $validator = Validator::make($input, $rules);
        if ($validator->fails()) {
            throw new Prototype\Exceptions\UserRegisterException();
        } else {
        	$input['password'] = Hash::make($input['password']);
			$userId = User::create($input)->id;
			$sessionId = Common::getSessionId($input, $userId);
			return Common::returnData(200, SUCCESS, $userId, $sessionId);
        }
	}

}
