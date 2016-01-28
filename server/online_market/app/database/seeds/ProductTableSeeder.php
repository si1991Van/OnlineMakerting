<?php

class ProductTableSeeder extends Seeder {

	public function run()
	{
		Product::create([
			'user_id'=> '1',
			'category_id'=> '1',
			'type_id' => '1',
			'price_id' => '1',
			'price' => '100000',
			'name' => 'San pham 1',
			'description' => 'Description san pham 1',
			'avatar' => 'avatar1.jpg',
			'city_id' => '1',
			'lat' => '21.00296184',
			'long' => '105.85202157',
			'position' => '1',
			'status' => '1',
		]);
		Product::create([
			'user_id'=> '2',
			'category_id'=> '1',
			'type_id' => '1',
			'price_id' => '2',
			'price' => '2000000',
			'name' => 'San pham 2',
			'description' => 'Description san pham 2',
			'avatar' => 'avatar2.jpg',
			'city_id' => '1',
			'lat' => '21.00296184',
			'long' => '105.85202157',
			'position' => '1',
			'status' => '1',
		]);
		Product::create([
			'user_id'=> '1',
			'category_id'=> '1',
			'type_id' => '2',
			'price_id' => '1',
			'price' => '100000',
			'name' => 'San pham 3',
			'description' => 'Description san pham 3',
			'avatar' => 'avatar3.jpg',
			'city_id' => '1',
			'lat' => '21.00296184',
			'long' => '105.85202157',
			'position' => '1',
			'status' => '1',
		]);

	}

}