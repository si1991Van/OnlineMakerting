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
<<<<<<< HEAD
	//check login
=======
	//check login, logout
>>>>>>> fbcafdf7245adf47b3a509178fe26dffcce035a1
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
	Route::post('/category/{id}', 'ApiCategoryController@index');
	//detail product
	Route::post('/product/{id}', 'ApiProductController@index');
	Route::post('/product_saved/{id}', 'ApiProductController@saved');
	//list product saved
	Route::post('/product_log', 'ApiProductLogController@index');
	Route::post('/product_log/{id}/delete', 'ApiProductLogController@destroy');
	//upload product
	Route::get('/post_product', 'ApiPostController@index');
	Route::post('/post_product', 'ApiPostController@post');
	//list favorite user products
	Route::post('/favorite', 'ApiFavoriteController@index');
	Route::post('/favorite/{id}/delete', 'ApiFavoriteController@destroy');
	//search products
	Route::get('/search/{status}', 'ApiSearchController@index');
	Route::get('/search_action', 'ApiSearchController@action');
	Route::post('/search_saved', 'ApiSearchController@saved');
	//search log
	Route::post('/search_log', 'ApiSearchLogController@index');
	Route::post('/search_log/{search_id}/delete', 'ApiSearchLogController@destroy');
	//account profile
	Route::get('/profile', 'ApiProfileController@index');
	Route::post('/profile', 'ApiProfileController@post');
	//user profile
	Route::post('/account/{id}', 'ApiProfileController@account');
	//block user
	Route::post('/block/{id}', 'ApiProfileController@block');
	//list products user
	Route::post('/product_user/{id}', 'ApiProductController@listProductUser');
	//black list user
	Route::post('/blacklist', 'ApiBlackListController@index');
	Route::post('/blacklist/{black_id}/delete', 'ApiBlackListController@destroy');
	//verify account
	Route::post('/verify_account', 'ApiVerifyAccountController@index');
	//forgot password
	Route::post('/forgot_password', 'ApiForgotPasswordController@index');
	//list message
	Route::post('/message', 'ApiMessageController@index');
	Route::post('/message/{message_id}/show', 'ApiMessageController@show');
	Route::post('/message/{message_id}/delete', 'ApiMessageController@destroy');
	//chat offline
	Route::get('/product/message', 'ApiChatController@index');
	Route::post('/product/{id}/message', 'ApiChatController@post');
	//send message
<<<<<<< HEAD
	Route::post('/report', 'ApiReportController@post');
=======
	Route::post('/product/{id}/report', 'ApiReportController@post');
>>>>>>> fbcafdf7245adf47b3a509178fe26dffcce035a1
	//list products status
	Route::post('/product_status/{status}', 'ApiProductController@listStatus');
	//list products delete
	Route::post('/product_hidden', 'ApiProductController@listHidden');
<<<<<<< HEAD
=======
	//text
	Route::post('/text/{id}', 'ApiTextController@index');
>>>>>>> fbcafdf7245adf47b3a509178fe26dffcce035a1

});
