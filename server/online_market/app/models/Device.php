<?php

use Illuminate\Auth\UserInterface;
use Illuminate\Auth\Reminders\RemindableInterface;

class Device extends Eloquent
{
    protected $table = 'devices';
    protected $fillable = ['user_id', 'session_id', 'device_id'];

}