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
		$sessionId = Common::checkSessionId($input);
		$blacklist = BlackList::where('user_id', $input['user_id'])->lists('black_id');
		$list = User::whereIn('id', $blacklist)->select(['avatar', 'username', 'id'])->get();
		$data = ['blacklist'=>$list] + Common::getHeader();
		return Common::returnData(200, SUCCESS, $input['user_id'], $sessionId, $data);
	}

	/**
	 * Remove the specified resource from storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function destroy()
	{
		$input = Input::all();
		$sessionId = Common::checkSessionId($input);
		BlackList::find($input['black_id'])->destroy();
		return Common::returnData(200, DELETE_SUCCESS, $input['user_id'], $sessionId, $data);
	}

}
