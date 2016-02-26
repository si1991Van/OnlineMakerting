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

		$this->call('AdminTableSeeder');
		$this->call('CityTableSeeder');
		$this->call('UserTableSeeder');
		$this->call('CategoryTableSeeder');
		$this->call('PriceTableSeeder');
		$this->call('ProductTableSeeder');
		$this->call('ProductImageTableSeeder');
		$this->call('FavoriteTableSeeder');
		$this->call('ApiMessageTableSeeder');
		$this->call('BlackListTableSeeder');
		$this->call('TextTableSeeder');

	}

}
