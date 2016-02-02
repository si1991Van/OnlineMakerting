<?php

class ApiCategoryController extends ApiController {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index()
	{
		$input = Input::all();
		return CommonProduct::returnProduct(array('category_id'=>$input['category_id'], 'status'=>1));
	}

}
