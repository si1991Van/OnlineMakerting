<?php
class CommonSearch {

	public static function countSearch($input = array())
	{
		$result = self::getSearch($input);
		return count($result);
	}

	public static function getSearch($input = array())
	{
		$result = Search::where(function ($query) use ($input){
<<<<<<< HEAD
			if (!empty($input['deleted'])) {
				$query = $query->whereNotNull('deleted_at');
			}
=======
>>>>>>> fbcafdf7245adf47b3a509178fe26dffcce035a1
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
			if (!empty($input['name'])) {
				$query = $query->where('name', 'like', '%'.$input['name'].'%');
			}
			if (!empty($input['start_date'])) {
				$query = $query->where('start_date', '>=', $input['start_date']);
			}
			if (!empty($input['end_date'])) {
				$query = $query->where('start_date', '<=', $input['end_date']);
			}
			//lat long

<<<<<<< HEAD
		})->lists('id', 'name', 'price_id', 'category_id', 'user_id', 'type_id', 'start_date', 'lat', 'long', 'created_at');
=======
		})->select(listFieldSearch())->get();
>>>>>>> fbcafdf7245adf47b3a509178fe26dffcce035a1
		return $result;
	}

}