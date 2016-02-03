<?php
class CommonProduct {

	public static function countProduct($input = array())
	{
		$result = self::getProduct($input);
		return count($result);
	}

	public static function getProduct($input = array())
	{
		$result = Product::where(function ($query) use ($input){
			if (!empty($input['user_id'])) {
				$query = $query->where('user_id', $input['user_id']);
			}
			if (!empty($input['category_id'])) {
				$query = $query->where('category_id', $input['category_id']);
			}
			if (!empty($input['type_id'])) {
				$query = $query->where('type_id', $input['type_id']);
			}
			if (!empty($input['price_id'])) {
				$query = $query->where('price_id', $input['price_id']);
			}
			if (!empty($input['city_id'])) {
				$query = $query->where('city_id', $input['city_id']);
			}
			if (!empty($input['position'])) {
				$query = $query->where('position', $input['position']);
			}
			if (!empty($input['status'])) {
				$query = $query->where('status', $input['status']);
			}
			if (!empty($input['name'])) {
				$query = $query->where('name', 'like', '%'.$input['name'].'%');
			}
			if (!empty($input['start_date'])) {
				$query = $query->where('start_time', '>=', $input['start_date']);
			}
			if (!empty($input['end_date'])) {
				$query = $query->where('start_time', '<=', $input['end_date']);
			}
			//lat long

<<<<<<< HEAD
		})->lists('id', 'name', 'avatar', 'price', 'price_id', 'category_id', 'user_id', 'type_id', 'city_id', 'start_time', 'status', 'position', 'created_at');
=======
		})->select(listFieldProduct())->orderBy('position', 'asc')->get();
>>>>>>> fbcafdf7245adf47b3a509178fe26dffcce035a1
		return $result;
	}

	public static function getProductDeleted($input = array())
	{
		$result = Product::onlyTrashed()
			->where('user_id', $input['user_id'])
<<<<<<< HEAD
			->lists('id', 'name', 'avatar', 'price', 'price_id', 'category_id', 'user_id', 'type_id', 'city_id', 'start_time', 'status', 'position', 'created_at');
=======
			->select(listFieldProduct())->orderBy('position', 'asc')->get();
>>>>>>> fbcafdf7245adf47b3a509178fe26dffcce035a1
		return $result;
	}

	public static function countProductDeleted($input = array())
	{
		$result = self::getProductDeleted($input);
		return count($result);
	}

<<<<<<< HEAD
}
=======
	public static function returnProduct($options = array())
	{
		$input = Input::all();
		$product = CommonProduct::getProduct($options);
		$array = Common::getHeader();
		if($array) {
			$data = array_merge(['product'=>$product], Common::getHeader());
		}
		return Common::returnData(200, SUCCESS, $input['user_id'], $input['session_id'], $data);
	}

	public static function returnProductDeleted()
	{
		$input = Input::all();
		$product = CommonProduct::getProductDeleted($input);
		$data = array_merge(['product'=>$product], Common::getHeader());
		return Common::returnData(200, SUCCESS, $input['user_id'], $input['session_id'], $data);
	}

}
>>>>>>> fbcafdf7245adf47b3a509178fe26dffcce035a1
