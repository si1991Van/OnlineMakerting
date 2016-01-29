<?php

class LoginController extends ApiController {

	public function getLogin()
	{
		$input = Input::all();
		return $this->checkLogin($input);
	}

}
