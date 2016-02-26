<?php

use Illuminate\Auth\UserInterface;
use Illuminate\Auth\Reminders\RemindableInterface;
use Illuminate\Database\Eloquent\SoftDeletingTrait;

class ProductImage extends Eloquent
{
	use SoftDeletingTrait;
    protected $table = 'product_images';
    protected $fillable = ['product_id', 'image_url'];
    protected $dates = ['deleted_at'];

}
