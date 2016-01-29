<?php
namespace Prototype\Filters;

class Factory {
    public static function createFilter(){
        if (preg_match('/\/api\//',\Request::url())){
            return new ApiFilters();
        }
        return new WebFilters();
    }
}