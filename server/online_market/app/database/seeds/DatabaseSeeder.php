<?php

class DatabaseSeeder extends Seeder {

	/**
	 * Run the database seeds.
	 *
	 * @return void
	 */
	public function run()
	{
		Eloquent::unguard();

		$this->call('UserTableSeeder');
		$this->call('AdminTableSeeder');
		$this->call('BlackListTableSeeder');
		$this->call('MessageTableSeeder');
		$this->call('FavoriteTableSeeder');
		$this->call('ProductTableSeeder');
		$this->call('ProductImageTableSeeder');
		$this->call('CityTableSeeder');
		$this->call('CategoryTableSeeder');
	}

}
