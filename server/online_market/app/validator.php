<?php
Validator::extend('unique_delete', function($attribute, $value, $parameters)
{
	if (Admin::where('username', $value)->first()) {
		return false;
	}
	return true;
});

