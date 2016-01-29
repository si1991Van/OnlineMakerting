@extends('admin.layout.default')

@section('title')
{{ $title='Sửa' }}
@stop

@section('content')

@if(Admin::isAdmin())
<div class="row margin-bottom">
	<div class="col-xs-12">
		<a href="{{ action('CategoryController@index') }}" class="btn btn-success">Danh sách</a>
		<a href="{{ action('CategoryController@create') }}" class="btn btn-primary">Thêm</a>
	</div>
</div>
@endif

<div class="row">
	<div class="col-xs-12">
		<div class="box box-primary">
			<!-- form start -->
			{{ Form::open(array('action' => array('CategoryController@update', $data->id), 'method' => 'PUT')) }}
				<div class="box-body">
					<div class="form-group">
						<label for="name">Name</label>
						<div class="row">
							<div class="col-sm-6">
								<input type="text" class="form-control" id="name" placeholder="Name" name="name" value="{{ $data->name }}">
							</div>
						</div>
					</div>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<input type="submit" class="btn btn-primary" value="Lưu lại" />
				</div>
			{{ Form::close() }}
		</div>
		<!-- /.box -->
	</div>
</div>

@stop
