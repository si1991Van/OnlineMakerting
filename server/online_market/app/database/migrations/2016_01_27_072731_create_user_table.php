<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateUserTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('users', function(Blueprint $table) {
            $table->increments('id');
            $table->string('email', 256)->nullable();
            $table->string('password', 256)->nullable();
            $table->string('username', 256)->nullable();
            $table->string('phone', 256)->nullable();
            $table->string('lat', 256)->nullable();
            $table->string('long', 256)->nullable();
            $table->integer('type')->nullable();
            $table->integer('status')->nullable();
            $table->string('remember_token', 256)->nullable();
            $table->softDeletes();
            $table->timestamps();
        });
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('users');
	}

}
