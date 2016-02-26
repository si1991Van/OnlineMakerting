<?php

class ApiBlackListController extends ApiController {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index()
	{
		$input = Input::all();
<<<<<<< HEAD
		$session = Common::checkSessionId($input);
		if (!$session) {
=======
		$sessionId = Common::checkSessionId($input);
		if (!$sessionId) {
>>>>>>> fbcafdf7245adf47b3a509178fe26dffcce035a1
			throw new Prototype\Exceptions\UserSessionErrorException();
		}
		$blacklist = BlackList::where('user_id', $input['user_id'])->lists('black_id');
		$list = User::whereIn('id', $blacklist)->select(['avatar', 'username', 'id'])->get();
<<<<<<< HEAD
		$data = ['blacklist'=>$list] + Common::getHeader();
		return Common::returnData(200, SUCCESS, $input['user_id'], $input['session_id'], $data);
=======
		$data = array_merge(['blacklist'=>$list], Common::getHeader());
		return Common::returnData(200, SUCCESS, $input['user_id'], $sessionId, $data);
>>>>>>> fbcafdf7245adf47b3a509178fe26dffcce035a1
	}

	/**
	 * Remove the specified resource from storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function destroy($blackId)
	{
		$input = Input::all();
		$sessionId = Common::checkSessionId($input);
		if (!$sessionId) {
			throw new Prototype\Exceptions\UserSessionErrorException();
		}
		BlackList::where('user_id', $input['user_id'])
			->where('black_id', $blackId)
			->delete();
		return Common::returnData(200, DELETE_SUCCESS, $input['user_id'], $sessionId, $data);
	}

}
