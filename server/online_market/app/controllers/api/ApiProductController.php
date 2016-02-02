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

	public function saved()
	{
		$input = Input::all();
		$userId = Product::find($input['product_id'])->user_id;
		Favorite::create([
							'model_name' => 'Product',
							'model_id' => $input['product_id'],
							'user_id' => $userId,
							'follow_id' => $input['user_id'],
							'type_favorite' => TYPE_FAVORITE_SAVE
						]);
		return Common::returnData(200, SUCCESS, $input['user_id'], $input['session_id']);
	}

	public function listStatus()
	{
		$input = Input::all();
		return CommonProduct::returnProduct(array('user_id'=>$input['user_id'], 'status'=>$input['status']));
	}

	public function listHidden()
	{
		return CommonProduct::returnProductDeleted();
	}

	public function listProductUser()
	{
		$input = Input::all();
		return CommonProduct::returnProduct(array('user_id'=>$input['user_id'], 'status'=>1));
	}

}
