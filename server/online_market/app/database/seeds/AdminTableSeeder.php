<?php

class AdminTableSeeder extends Seeder {

	public function run()
	{
		Admin::create([
				'email'=>'trantunghn196@gmail.com',
				'password'=>Hash::make('123456'),
				'username'=> 'tung1984',
				'status'=> '1',
				'role_id'=> '1',
			]);
	}

}