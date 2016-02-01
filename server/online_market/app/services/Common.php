<?php
class Common {

	public static function returnData($code, $message, $userId = '', $sessionId = '', $data = null)
	{
		return Response::json([
				'code' => $code,
				'message' => $message,
				'user_id' => $userId,
				'session_id' => $sessionId,
				'data' => $data,
			]);
	}

	public static function getSessionId($input, $userId)
	{
		$device = Device::where('device_id', $input['device_id'])
						->where('user_id', $userId)
						->first();
		if($device) {
			if(!isset($input['session_id'])) {
				$sessionId = $device->session_id;
				if(!($sessionId)) {
					$sessionId = generateRandomString();
	            	Device::find($device->id)->update(['session_id' => $sessionId]);
				}
	        }
	        else {
	            if($device->session_id == $input['session_id']) {
	                $sessionId = $input['session_id'];
	            } else {
	                throw new Prototype\Exceptions\UserSessionErrorException();
	            }
	        }
		} else {
			$sessionId = generateRandomString();
			Device::create(['device_id'=>$input['device_id'], 'user_id'=>$userId, 'session_id'=>$sessionId]);
		}
        return $sessionId;
	}

	public static function getListArray($modelName, $selectField)
	{
		$obj = $modelName::all($selectField);
		$data = array();
		foreach ($obj as $key => $value) {
			$data[$key] = $value->toArray();
		}
		return $data;
	}

}
