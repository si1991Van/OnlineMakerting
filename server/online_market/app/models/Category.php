<?php

use Illuminate\Auth\UserInterface;
use Illuminate\Auth\Reminders\RemindableInterface;
use Illuminate\Database\Eloquent\SoftDeletingTrait;

class Category extends Eloquent
{
	use SoftDeletingTrait;
    protected $table = 'categories';
    protected $fillable = ['name'];
    protected $dates = ['deleted_at'];

}
