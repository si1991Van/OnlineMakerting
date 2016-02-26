<?php

class BlackList extends Eloquent
{
    protected $table = 'blacklists';
    protected $fillable = ['user_id', 'black_id', 'kind'];

}	