<?php
class CommonSetting {

	public static function getSettingMenu()
	{
		$input = Input::all();
		if(!empty($input['session_id'])) {
	    	$setting = self::getSettingMenu2($input);
	    } else {
	    	$setting = self::getSettingMenu1($input);
	    }
	    return $setting;
	}

	public static function getSettingMenu1($input)
	{
		$setting = array(
		    [
		     'name' => REGISTER,
		     'link' => url('api/register'),
		     'method' => 'GET',
		     'quantity' => null
		    ],
		    [
		     'name' => LOGIN,
		     'link' => url('api/login'),
		     'method' => 'GET',
		     'quantity' => null
		    ],
	    );
	    return $setting;
	}

	public static function getSettingMenu2($input)
	{
		$setting = array(
			[
		     'name' => PRODUCT_STATUS_1,
		     'link' => url('api/product_status/1'),
		     'method' => 'POST',
		     'quantity' => CommonProduct::countProduct(array('user_id'=>$input['user_id'], 'status'=>1))
		    ],
		    [
		     'name' => PRODUCT_STATUS_2,
		     'link' => url('api/product_status/2'),
		     'method' => 'POST',
		     'quantity' => CommonProduct::countProduct(array('user_id'=>$input['user_id'], 'status'=>2))
		    ],
		    [
		     'name' => PRODUCT_STATUS_3,
		     'link' => url('api/product_status/3'),
		     'method' => 'POST',
		     'quantity' => CommonProduct::countProduct(array('user_id'=>$input['user_id'], 'status'=>3))
		    ],
		    [
		     'name' => PRODUCT_STATUS_4,
		     'link' => url('api/product_hidden'),
		     'method' => 'POST',
		     'quantity' => CommonProduct::countProductDeleted(array('user_id'=>$input['user_id']))
		    ],
		    [
		     'name' => PRODUCT_LOG,
		     'link' => url('api/product_log'),
		     'method' => 'POST',
		     'quantity' => CommonFavorite::countFavorite(array('model_name'=>'Product', 'follow_id'=>$input['user_id']))
		    ],
		    [
		     'name' => SEARCH_LOG,
		     'link' => url('api/search_log'),
		     'method' => 'POST',
		     'quantity' => CommonSearch::countSearch(array('user_id'=>$input['user_id']))
		    ],
		    [
		     'name' => LOGOUT,
		     'link' => url('api/logout'),
		     'method' => 'POST',
		     'quantity' => null
		    ],
	    );
	    return $setting;
	}

}