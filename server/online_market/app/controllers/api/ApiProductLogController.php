<?php

class ApiProductLogController extends ApiController {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index()
	{
		$input = Input::all();
		$sessionId = Common::checkSessionId($input);
		if (!$sessionId) {
			throw new Prototype\Exceptions\UserSessionErrorException();
		}
		$favorites = CommonFavorite::getFavorite([
													'model_name' => 'Product',
													'follow_id' => $input['user_id'],
													'type_favorite' => TYPE_FAVORITE_SAVE
												]);
		$product = Product::whereIn('id', $favorites)->select(listFieldProduct())->get();
		$data = array_merge(['product'=>$product], Common::getHeader());
		return Common::returnData(200, SUCCESS, $input['user_id'], $sessionId, $data);
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
		$sessionId = Common::checkSessionId($input);
		if (!$sessionId) {
			throw new Prototype\Exceptions\UserSessionErrorException();
		}
		Favorite::where('model_name', 'Product')
				->where('model_id', $id)
				->where('follow_id', $input['user_id'])
				->where('type_favorite', TYPE_FAVORITE_SAVE)
				->delete();
		return Common::returnData(200, DELETE_SUCCESS, $input['user_id'], $sessionId);
	}

}
