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
		if(!isset($input['session_id'])) {
            $sessionId = generateRandomString();
            User::find($userId)->update(['session_id' => $sessionId]);
        }
        else {
            if(User::find($userId)->session_id == $input['session_id']) {
                $sessionId = $input['session_id'];
            } else {
                throw new Prototype\Exceptions\UserSessionErrorException();
            }
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
