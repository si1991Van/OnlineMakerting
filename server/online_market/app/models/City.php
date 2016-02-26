<?php

use Illuminate\Auth\UserInterface;
use Illuminate\Auth\Reminders\RemindableInterface;
use Illuminate\Database\Eloquent\SoftDeletingTrait;

class City extends Eloquent
{
	use SoftDeletingTrait;
    protected $table = 'cities';
    protected $fillable = ['name'];
    protected $dates = ['deleted_at'];

}
