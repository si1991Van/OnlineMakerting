<?php

use Illuminate\Auth\UserInterface;
use Illuminate\Auth\Reminders\RemindableInterface;
use Illuminate\Database\Eloquent\SoftDeletingTrait;

class Favorite extends Eloquent
{
	use SoftDeletingTrait;
    protected $table = 'favorites';
    protected $fillable = ['user_id', 'model_name', 'model_id', 'follow_id'];
    protected $dates = ['deleted_at'];

}