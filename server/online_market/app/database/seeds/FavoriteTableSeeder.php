<?php

class FavoriteTableSeeder extends Seeder {

	public function run()
	{
		Favorite::create([
			'model_id'=> '1',
			'model_name'=> 'Product',
			'user_id' => '1',
			'follow_id' => '2'
		]);
		Favorite::create([
			'model_id'=> '1',
			'model_name'=> 'Product',
			'user_id' => '1',
			'follow_id' => '3'
		]);
		Favorite::create([
			'model_id'=> '2',
			'model_name'=> 'Product',
			'user_id' => '2',
			'follow_id' => '1'
		]);
		
	}

}