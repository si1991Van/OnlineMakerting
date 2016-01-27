<?php

use Illuminate\Auth\UserInterface;
use Illuminate\Auth\Reminders\RemindableInterface;
use Illuminate\Database\Eloquent\SoftDeletingTrait;

class ApiMessage extends Eloquent
{
	use SoftDeletingTrait;
    protected $table = 'messages';
    protected $fillable = ['sent_id', 'receiver_id', 'message', 'status'];
    protected $dates = ['deleted_at'];

}