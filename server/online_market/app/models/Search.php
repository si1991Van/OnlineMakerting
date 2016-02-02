<?php

class Search extends Eloquent
{
    protected $table = 'searchs';
    protected $fillable = ['user_id', 'category_id', 'type_id', 'price_id', 'name', 'lat', 'long', 'start_date'];

}
