<?php
class CommonFilter {
    public static function createFilter(){
        if (preg_match('/\/api\//',\Request::url()))
        {
            return new ApiFilters();
        }
        // return new WebFilters();
        dd(11);
    }
}