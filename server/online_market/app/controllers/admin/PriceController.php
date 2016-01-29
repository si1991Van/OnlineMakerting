<?php

class PriceController extends AdminController {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index()
	{
		$data = Price::orderBy('id', 'asc')->paginate(PAGINATE);
		return View::make('admin.price.index')->with(compact('data'));
	}

	/**
	 * Show the form for creating a new resource.
	 *
	 * @return Response
	 */
	public function create()
	{
		return View::make('admin.price.create');
	}


	/**
	 * Store a newly created resource in storage.
	 *
	 * @return Response
	 */
	public function store()
	{
		$rules = array(
			'min' => 'required',
            'max' => 'required',
		);
		$input = Input::except('_token');
		$validator = Validator::make($input,$rules);
		if($validator->fails()) {
			return Redirect::action('PriceController@create')
	            ->withErrors($validator);
        } else {
        	$id = Price::create($input)->id;
    		return Redirect::action('PriceController@index');
        }
	}


	/**
	 * Display the specified resource.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function show($id)
	{
		//
	}


	/**
	 * Show the form for editing the specified resource.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function edit($id)
	{
		$data = Price::find($id);
        return View::make('admin.price.edit', array('data'=>$data));
	}


	/**
	 * Update the specified resource in storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function update($id)
	{
		$rules = array(
			'min' => 'required',
            'max' => 'required',
		);
        $input = Input::except('_token');
		$validator = Validator::make($input,$rules);
		if($validator->fails()) {
			return Redirect::action('PriceController@edit', $id)
	            ->withErrors($validator);
        } else {
        	CommonNormal::update($id, $input);
    		return Redirect::action('PriceController@index');
        }
	}


	/**
	 * Remove the specified resource from storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function destroy($id)
	{
		CommonNormal::delete($id);
        return Redirect::action('PriceController@index');
	}


}
