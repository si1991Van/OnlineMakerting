<?php

class MainController extends ApiController {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index()
	{
		return CommonProduct::returnProduct(array('status'=>1));
	}

}
