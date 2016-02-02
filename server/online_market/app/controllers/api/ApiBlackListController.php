<?php

class ApiBlackListController extends ApiController {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index($userId)
	{
		$input = Input::all();
		$sessionId = Common::checkSessionId($input);
		$blacklist = BlackList::where('user_id', $userId)->lists('black_id');
		$list = User::whereIn('id', $blacklist)->select(['avatar', 'username', 'id'])->get();
		$data = ['blacklist' => $list] + Common::getHeader();
		return Common::returnData(200, SUCCESS, $userId, $sessionId, $data);
	}

	/**
	 * Remove the specified resource from storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function destroy($userId, $blackId)
	{
		$input = Input::all();
		$sessionId = Common::checkSessionId($input);
		BlackList::where('user_id', $userId)
			->where('black_id', $blackId)
			->delete();
		return Common::returnData(200, DELETE_SUCCESS, $userId, $sessionId, $data);
	}

}
