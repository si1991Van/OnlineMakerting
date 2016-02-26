<?php

class ApiCategoryController extends ApiController {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index($id)
	{
		return CommonProduct::returnProduct(array('category_id'=>$id, 'status'=>1));
	}

}
