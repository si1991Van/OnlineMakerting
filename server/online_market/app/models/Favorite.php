<?php

class Favorite extends Eloquent
{
    protected $table = 'favorites';
    protected $fillable = ['model_name', 'model_id', 'follow_id', 'type_favorite'];

}