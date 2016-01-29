<?php

class LogoutController extends ApiController {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function logout()
	{
		$input = Input::all();
		if(User::find($input['user_id'])->session_id == $input['session_id']) {
			User::find($input['user_id'])->update(['session_id'=>null]);
			return Common::returnData(200, 'Success', $input['user_id'], '');
        } else {
            throw new Prototype\Exceptions\UserSessionErrorException();
        }
	}

}
