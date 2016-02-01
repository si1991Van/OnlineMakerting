<?php

class LoginController extends ApiController {

	public function postLogin()
	{
		$input = Input::all();
		return $this->checkLogin($input);
	}

	public function getLogin()
	{
		return Common::returnData(200, SUCCESS);
	}

}
