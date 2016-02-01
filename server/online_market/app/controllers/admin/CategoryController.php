<?php

class CategoryController extends AdminController {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index()
	{
		$data = Category::orderBy('id', 'desc')->paginate(PAGINATE);
		return View::make('admin.category.index')->with(compact('data'));
	}

	/**
	 * Show the form for creating a new resource.
	 *
	 * @return Response
	 */
	public function create()
	{
		return View::make('admin.category.create');
	}


	/**
	 * Store a newly created resource in storage.
	 *
	 * @return Response
	 */
	public function store()
	{
		$rules = array(
			'name' => 'required',
		);
		$input = Input::except('_token');
		$validator = Validator::make($input,$rules);
		if($validator->fails()) {
			return Redirect::action('CategoryController@create')
	            ->withErrors($validator);
        } else {
        	$id = Category::create($input)->id;
    		return Redirect::action('CategoryController@index');
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
		$data = Category::find($id);
        return View::make('admin.category.edit', array('data'=>$data));
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
			'name' => 'required',
		);
        $input = Input::except('_token');
		$validator = Validator::make($input,$rules);
		if($validator->fails()) {
			return Redirect::action('CategoryController@edit', $id)
	            ->withErrors($validator);
        } else {
        	CommonNormal::update($id, $input);
    		return Redirect::action('CategoryController@index');
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
        return Redirect::action('CategoryController@index');
	}


}
