<?php

use Illuminate\Auth\UserInterface;
use Illuminate\Auth\Reminders\RemindableInterface;
use Illuminate\Database\Eloquent\SoftDeletingTrait;

class Price extends Eloquent
{
	use SoftDeletingTrait;
    protected $table = 'prices';
    protected $fillable = ['min', 'max'];
    protected $dates = ['deleted_at'];

}
