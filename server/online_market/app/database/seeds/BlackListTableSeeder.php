<?php

class BlackListTableSeeder extends Seeder {

	public function run()
	{
		BlackList::create([
			'user_id'=> '1',
			'black_id'=> '2',
			'kind'=> '1',
		]);
		BlackList::create([
			'user_id'=> '3',
			'black_id'=> '2',
			'kind'=> '1',
		]);
	}

}