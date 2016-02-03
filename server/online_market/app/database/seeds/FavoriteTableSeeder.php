<?php

class FavoriteTableSeeder extends Seeder {

	public function run()
	{
		Favorite::create([
			'type_favorite'=> '1',
			'model_id'=> '1',
			'model_name'=> 'Product',
			'follow_id' => '2'
		]);
		Favorite::create([
			'type_favorite'=> '1',
			'model_id'=> '1',
			'model_name'=> 'Product',
			'follow_id' => '3'
		]);
		Favorite::create([
			'type_favorite'=> '1',
			'model_id'=> '2',
			'model_name'=> 'Product',
			'follow_id' => '1'
		]);

	}

}