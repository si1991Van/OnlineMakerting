<?php

class ProductImageTableSeeder extends Seeder {

	public function run()
	{
		ProductImage::create([
			'product_id'=> '1',
			'image_url'=> 'avatar1.jpg',
		]);
		ProductImage::create([
			'product_id'=> '1',
			'image_url'=> 'avatar1-1.jpg',
		]);
		ProductImage::create([
			'product_id'=> '1',
			'image_url'=> 'avatar1-2.jpg',
		]);
		ProductImage::create([
			'product_id'=> '2',
			'image_url'=> 'avatar2.jpg',
		]);
		ProductImage::create([
			'product_id'=> '3',
			'image_url'=> 'avatar3.jpg',
		]);

	}

}