<?php

class ApiProductController extends ApiController {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index()
	{
		//
	}

	public function saved($id)
	{
		$input = Input::all();
		Favorite::create([
							'model_name' => 'Product',
							'model_id' => $id,
							'follow_id' => $input['user_id'],
							'type_favorite' => TYPE_FAVORITE_SAVE
						]);
		return Common::returnData(200, SUCCESS, $input['user_id'], $input['session_id']);
	}

	public function listStatus($status)
	{
		$input = Input::all();
		return CommonProduct::returnProduct(array('user_id'=>$input['user_id'], 'status'=>$status));
	}

	public function listHidden()
	{
		return CommonProduct::returnProductDeleted();
	}

	public function listProductUser($id)
	{
		return CommonProduct::returnProduct(array('user_id'=>$id, 'status'=>1));
	}

}
