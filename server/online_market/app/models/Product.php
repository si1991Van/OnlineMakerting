<?php

use Illuminate\Auth\UserInterface;
use Illuminate\Auth\Reminders\RemindableInterface;
use Illuminate\Database\Eloquent\SoftDeletingTrait;

class Product extends Eloquent
{
	use SoftDeletingTrait;
    protected $table = 'products';
    protected $fillable = ['user_id', 'category_id', 'type_id', 'price_id', 'price', 'name', 'description', 'avatar', 'city_id', 'lat', 'long', 'position', 'status', 'start_time'];
    protected $dates = ['deleted_at'];

}
