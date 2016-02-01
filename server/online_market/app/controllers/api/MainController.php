<?php

class MainController extends ApiController {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index()
	{
		$input = Input::all();
		$product = Common::getListArray('Product', ['id', 'name', 'avatar', 'price', 'price_id', 'category_id', 'user_id', 'type_id', 'city_id', 'start_time', 'status', 'position', 'created_at']);
		$data = ['product'=>$product] + Common::getHeader();
		return Common::returnData(200, SUCCESS, $input['user_id'], $input['session_id'], $data);
	}

}
