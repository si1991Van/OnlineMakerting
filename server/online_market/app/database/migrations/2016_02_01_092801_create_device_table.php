<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateDeviceTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('devices', function(Blueprint $table) {
            $table->increments('id');
            $table->string('device_id', 256)->nullable();
            $table->integer('user_id')->nullable();
            $table->string('session_id', 256)->nullable();
            $table->timestamps();
        });
        Schema::table('users', function($table) {
		    $table->dropColumn('session_id');
		});
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
        Schema::drop('devices');
	}

}
