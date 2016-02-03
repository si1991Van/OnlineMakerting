<?php

class Device extends Eloquent
{
    protected $table = 'devices';
    protected $fillable = ['user_id', 'session_id', 'device_id'];

}