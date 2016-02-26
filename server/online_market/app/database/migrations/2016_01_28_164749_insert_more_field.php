<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class InsertMoreField extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::table('favorites', function(Blueprint $table) {
            $table->integer('type_favorite')->after('id')->nullable();
        });
        Schema::table('users', function(Blueprint $table) {
            $table->string('address', 256)->after('id')->nullable();
            $table->string('avatar', 256)->after('id')->nullable();
            $table->string('code_email', 256)->after('id')->nullable();
            $table->string('code_phone', 256)->after('id')->nullable();
            $table->string('fullname', 256)->after('id')->nullable();
        });
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		//
	}

}
