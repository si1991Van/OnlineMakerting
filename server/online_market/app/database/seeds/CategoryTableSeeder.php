<?php

class CategoryTableSeeder extends Seeder {

	public function run()
	{
		Category::create([
			'name'=> 'Oto',
		]);
		Category::create([
			'name'=> 'Xe máy',
		]);
		Category::create([
			'name'=> 'Xe đạp',
		]);
	}

}