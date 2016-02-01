<?php
class Common {

	public static function returnData($code, $message, $data = null)
	{
		return Response::json([
				'code' => $code,
				'message' => $message,
				'data' => $data,
			]);
	}
	public static function checkAuthencation($input)
	{
		$check = Auth::user()->attempt($input);
		return $check;
	}
}