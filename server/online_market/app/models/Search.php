<?php

<<<<<<< HEAD
use Illuminate\Auth\UserInterface;
use Illuminate\Auth\Reminders\RemindableInterface;
use Illuminate\Database\Eloquent\SoftDeletingTrait;

class Search extends Eloquent
{
	use SoftDeletingTrait;
    protected $table = 'searchs';
    protected $fillable = ['user_id', 'category_id', 'type_id', 'price_id', 'name', 'lat', 'long', 'start_date'];
    protected $dates = ['deleted_at'];
=======
class Search extends Eloquent
{
    protected $table = 'searchs';
    protected $fillable = ['user_id', 'category_id', 'type_id', 'price_id', 'name', 'lat', 'long', 'start_date'];
>>>>>>> fbcafdf7245adf47b3a509178fe26dffcce035a1

}
