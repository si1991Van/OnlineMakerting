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
		$session = Common::checkSessionId($input);
		if (!$session) {
			throw new Prototype\Exceptions\UserSessionErrorException();
		}
		$blacklist = BlackList::where('user_id', $input['user_id'])->lists('black_id');
		$list = User::whereIn('id', $blacklist)->select(['avatar', 'username', 'id'])->get();
		$data = ['blacklist'=>$list] + Common::getHeader();
		return Common::returnData(200, SUCCESS, $input['user_id'], $input['session_id'], $data);
	}

	/**
	 * Remove the specified resource from storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function destroy($id)
	{
		$input = Input::all();
	}

}
