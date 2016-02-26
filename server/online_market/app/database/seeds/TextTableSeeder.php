<?php

class TextTableSeeder extends Seeder {

	public function run()
	{
		Text::create([
				'title' => 'Khuyến mãi',
				'description'=> 'Giới thiệu khuyến mãi',
			]);
		Text::create([
				'title' => 'Hướng dẫn',
				'description'=> 'Giới thiệu hướng dẫn',
			]);
		Text::create([
				'title' => 'Liên hệ',
				'description'=> 'Giới thiệu liên hệ',
			]);

	}

}