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
		$device = Device::where('device_id', $input['device_id'])
						->where('user_id', $input['user_id'])
						->first();
		if($device) {
			if($device->session_id == $input['session_id']) {
                Device::find($device->id)->update(['session_id' => null]);
                return Common::returnData(200, SUCCESS, $input['user_id'], '');
            } else {
            	throw new Prototype\Exceptions\UserSessionErrorException();
            }
		}
		throw new Prototype\Exceptions\DeviceErrorException();
	}

}
