<?php

class PriceTableSeeder extends Seeder {

	public function run()
	{
		Price::create([
			'min'=> '0',
			'max'=> '1000000',
		]);
		Price::create([
			'min'=> '1000000',
			'max'=> '10000000',
		]);
		Price::create([
			'min'=> '10000000',
			'max'=> '100000000',
		]);
		Price::create([
			'min'=> '100000000',
		]);
	}

}