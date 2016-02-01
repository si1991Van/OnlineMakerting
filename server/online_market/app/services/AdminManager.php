<?php
class AdminManager
{
	public static function searchUserOperation($input)
	{
		$users = Admin::where(function ($query) use ($input){
			if ($input['role_id']) {
				$query = $query->where('role_id', $input['role_id']);
			}
			if ($input['keyword']) {
				$query = $query->where('email', 'like', '%'.$input['keyword'].'%')
								->orWhere('username', 'like', '%'.$input['keyword'].'%');
			}
			// todo
			// if ($input['start_date'])
			// 	$query = $query->where('updated_at', '>=' ,$input['start_date']);
			// if ($input['end_date'])
			// 	$query = $query->where('updated_at', '<=' ,$input['end_date'].' 23:59:59');
		})->orderBy('id', 'desc')->paginate(PAGINATE);
		return $users;
	}

}
