<?php

use Illuminate\Auth\UserInterface;
use Illuminate\Auth\Reminders\RemindableInterface;
use Illuminate\Database\Eloquent\SoftDeletingTrait;

class Text extends Eloquent
{
	use SoftDeletingTrait;
    protected $table = 'texts';
    protected $fillable = ['title', 'description'];
    protected $dates = ['deleted_at'];

}
