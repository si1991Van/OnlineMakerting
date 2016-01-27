<?php

use Illuminate\Auth\UserInterface;
use Illuminate\Auth\Reminders\RemindableInterface;
use Illuminate\Database\Eloquent\SoftDeletingTrait;

class BlackList extends Eloquent
{
	use SoftDeletingTrait;
    protected $table = 'blacklists';
    protected $fillable = ['user_id', 'black_id', 'kind'];
    protected $dates = ['deleted_at'];

}	