<?php
use Carbon\Carbon;
class CommonNormal
{
	public static function delete($id)
	{
		$name = self::commonName();
		$name::find($id)->delete();
	}

	public static function update($id, $input, $modelName = NULL)
	{
		$name = self::commonName();
		if($modelName) {
			$name = $modelName;
		}
		$name::find($id)->update($input);
	}

	public static function create($input, $name = NULL)
	{
		$name = self::commonName($name);
		$id = $name::create($input)->id;
		return $id;
	}

	public static function commonName($name = NULL)
	{
		if ($name == NULL) {
			$name = Request::segment(2);
		}
		if ($name == '') {
			return 'AdminNew';
		}
		if($name =='news'){
			return 'AdminNew';
		}
		if($name =='newstype'){
			return 'TypeNew';
		}
		if ($name == 'manager') {
			return 'Admin';
		}
		if ($name == 'introduce') {
			return 'Introduce';
		}
		if ($name == 'bottomtext') {
			return 'BottomText';
		}
		if ($name == 'contact') {
			return 'Contact';
		}
		if ($name == 'slider') {
			return 'AdminSlide';
		}
		if ($name == 'type_about_us') {
			return 'TypeAboutUs';
		}
		if ($name == 'about_us_company') {
			return 'AboutUs';
		}

	}
}