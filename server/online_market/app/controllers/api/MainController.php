<?php

class MainController extends ApiController {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index()
	{
		$category = Common::getListArray('Category', ['id', 'name']);
		$product = Common::getListArray('Product', ['id', 'name', 'avatar', 'price', 'price_id', 'category_id', 'user_id', 'type_id', 'city_id', 'start_time', 'status', 'position', 'created_at']);
		
		return Common::returnData(200, 'Success', '', '', ['category' => $category, 'product'=>$product]);
	}

}
