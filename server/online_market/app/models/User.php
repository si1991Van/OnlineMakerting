<?php

use Illuminate\Auth\UserTrait;
use Illuminate\Auth\UserInterface;
use Illuminate\Auth\Reminders\RemindableTrait;
use Illuminate\Auth\Reminders\RemindableInterface;

class User extends Eloquent implements UserInterface, RemindableInterface {

	use UserTrait, RemindableTrait;

	/**
	 * The database table used by the model.
	 *
	 * @var string
	 */
	protected $table = 'users';

	/**
	 * The attributes excluded from the model's JSON form.
	 *
	 * @var array
	 */
	protected $hidden = array('password', 'remember_token');
	protected $fillable = array('email', 'password', 'username',
		'status', 'phone', 'lat', 'long', 'type', 'fullname', 'code_phone', 'code_email', 'avatar', 'address', 'session_id', 'facebook_id', 'google_id', 'notify');
    protected $dates = ['deleted_at'];

}
