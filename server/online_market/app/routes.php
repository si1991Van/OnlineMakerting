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
	//check login, logout -> finish
	Route::get('/login', 'LoginController@getLogin');
	Route::post('/login', 'LoginController@postLogin');
	Route::post('/logout', 'LogoutController@logout');
	Route::get('/register', 'RegisterController@index');
	Route::post('/register', 'RegisterController@store');
	//login by facebook or google->finish
	Route::post('/login_social', 'LoginController@loginSocial');

	//home->finish
	Route::get('/', 'MainController@index');

	//setting
	Route::get('/setting', 'ApiSettingController@index');

	//list category
	Route::get('/category', 'ApiCategoryController@index');

	//list products following category->finish
	Route::post('/category/{id}', 'ApiCategoryController@index');

	//detail product
	Route::post('/product/{id}', 'ApiProductController@index');
	Route::post('/product_saved/{id}', 'ApiProductController@saved');

	//list product saved->finish
	Route::post('/product_log', 'ApiProductLogController@index');
	Route::post('/product_log/{id}/delete', 'ApiProductLogController@destroy');

	//upload product
	Route::get('/post_product', 'ApiPostController@index');
	Route::post('/post_product', 'ApiPostController@post');

	//list favorite user products->finish
	Route::post('/favorite', 'ApiFavoriteController@index');
	Route::post('/favorite/{id}/delete', 'ApiFavoriteController@destroy');

	//search products
	Route::get('/search/{status}', 'ApiSearchController@index');
	Route::get('/search_action', 'ApiSearchController@action');
	Route::post('/search_saved', 'ApiSearchController@saved');

	//search log
	Route::post('/search_log', 'ApiSearchLogController@index');
	Route::post('/search_log/{search_id}/delete', 'ApiSearchLogController@destroy');

	//user profile->TODO
	Route::get('/profile', 'ApiProfileController@index');
	Route::post('/profile', 'ApiProfileController@post');
	//verify user->later
	Route::post('/verify_phone', 'ApiVerifyAccountController@index');

	//list products user->finish
	Route::post('/product_user/{id}', 'ApiProductController@listProductUser');

	//black list user->finish
	Route::post('/blacklist', 'ApiBlackListController@index');
	Route::post('/blacklist/{black_id}/delete', 'ApiBlackListController@destroy');

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
	Route::post('/product/{id}/report', 'ApiReportController@post');

	//list products status->finish
	Route::post('/product_status/{status}', 'ApiProductController@listStatus');

	//list products hidden->finish
	Route::post('/product_hidden', 'ApiProductController@listHidden');

	//list product of account:TODO
	Route::post('/account/{id}', 'ApiProfileController@account');
	//block account:TODO
	Route::post('/block/{id}', 'ApiProfileController@block');

	//text hardcode: policy, introduce, contact
	Route::post('/text/{id}', 'ApiTextController@index');

});
