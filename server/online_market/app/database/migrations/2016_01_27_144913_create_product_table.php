<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateProductTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('products', function(Blueprint $table) {
            $table->increments('id');
            $table->integer('user_id')->nullable();
            $table->integer('category_id')->nullable();
            $table->integer('type_id')->nullable();
            $table->integer('price_id')->nullable();
            $table->string('price', 256)->nullable();
            $table->string('name', 256)->nullable();
            $table->text('description')->nullable();
            $table->string('avatar', 256)->nullable();
            $table->integer('city_id')->nullable();
            $table->string('lat', 256)->nullable();
            $table->string('long', 256)->nullable();
            $table->integer('position')->nullable();
            $table->integer('status')->nullable();
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
        Schema::drop('products');
	}

}
