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
		$product = Common::getListArray('Product', ['id', 'name', 'avatar', 'price', 'created_at']);
		return Common::returnData(200, 'Success', '', '', ['category' => $category, 'product'=>$product]);
	}

}
