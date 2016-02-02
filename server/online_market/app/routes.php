<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the Closure to execute when that URI is requested.
|
*/

Route::group(['prefix' => 'admin'], function () {

	Route::get('/login', array('uses' => 'AdminController@login', 'as' => 'admin.login'));
	Route::post('/login', array('uses' => 'AdminController@doLogin'));
	Route::get('/logout', array('uses' => 'AdminController@logout', 'as' => 'admin.logout'));
	Route::resource('/', 'AdminController');

	Route::get('/manager/changepassword/{id}', array('uses' => 'ManagerController@changePassword', 'as' => 'admin.manager.chanpassword'));
	Route::post('/manager/updatePassword/{id}', array('uses' => 'ManagerController@updatePassword'));
	Route::get('/manager/search', array('uses' => 'ManagerController@search', 'as' => 'admin.manager.search'));
	Route::resource('/manager', 'ManagerController');

	Route::resource('/price', 'PriceController');
	Route::resource('/category', 'CategoryController');
	Route::get('/product/check/{id}', 'ProductController@check');
	Route::resource('/product', 'ProductController');

});

Route::group(['prefix' => 'api'], function () {
	//check login, logout
	Route::get('/login', 'LoginController@getLogin');
	Route::post('/login', 'LoginController@postLogin');
	Route::post('/logout', 'LogoutController@logout');
	Route::get('/register', 'RegisterController@index');
	Route::post('/register', 'RegisterController@store');
	//login by facebook or google
	Route::post('/login_social', 'LoginController@loginSocial');

	//home
	Route::get('/', 'MainController@index');
	//list products
	Route::post('/category', 'ApiCategoryController@index');
	//detail product
	Route::post('/product', 'ApiProductController@index');
	Route::post('/product_saved', 'ApiProductController@saved');
	//list product saved
	Route::post('/product_log', 'ApiProductLogController@index');
	Route::post('/product_log/delete', 'ApiProductLogController@index');
	//upload product
	Route::get('/post_product', 'ApiPostController@index');
	Route::post('/post_product', 'ApiPostController@post');
	//list favorite user products
	Route::post('/favorite', 'ApiFavoriteController@index');
	Route::post('/favorite/delete', 'ApiFavoriteController@destroy');
	//search products
	Route::get('/search', 'ApiSearchController@index');
	Route::get('/search_action', 'ApiSearchController@action');
	Route::post('/search_saved', 'ApiSearchController@saved');
	//search log
	Route::post('/search_log', 'ApiSearchLogController@index');
	Route::post('/search_log/delete', 'ApiSearchLogController@destroy');
	//account profile
	Route::get('/profile', 'ApiProfileController@index');
	Route::post('/profile', 'ApiProfileController@post');
	//user profile
	Route::post('/account', 'ApiProfileController@account');
	//block user
	Route::post('/block', 'ApiProfileController@block');
	//list products user
	Route::post('/product_user', 'ApiProductController@listProductUser');
	//black list user
	Route::post('/blacklist', 'ApiBlackListController@index');
	Route::post('/blacklist/delete', 'ApiBlackListController@destroy');
	//verify account
	Route::post('/verify_account', 'ApiVerifyAccountController@index');
	//forgot password
	Route::post('/forgot_password', 'ApiForgotPasswordController@index');
	//list message
	Route::post('/message', 'ApiMessageController@index');
	Route::post('/message/show', 'ApiMessageController@show');
	Route::post('/message/delete', 'ApiMessageController@destroy');
	//chat offline
	Route::get('/product/message', 'ApiChatController@index');
	Route::post('/product/message', 'ApiChatController@post');
	//send message
	Route::post('/product/report', 'ApiReportController@post');
	//list products status
	Route::post('/product_status', 'ApiProductController@listStatus');
	//list products delete
	Route::post('/product_hidden', 'ApiProductController@listHidden');

});
