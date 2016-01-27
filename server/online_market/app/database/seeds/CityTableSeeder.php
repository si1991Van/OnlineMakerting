<?php

class CityTableSeeder extends Seeder {

	public function run()
	{
		City::create([
			'name'=> 'Hà nội',
		]);
		City::create([
			'name'=> 'Hồ Chí Minh',
		]);

	}

}