@extends('admin.layout.default')
@if(Admin::isAdmin())
@section('title')
{{ $title='Thêm' }}
@stop

@section('content')

<div class="row margin-bottom">
	<div class="col-xs-12">
		<a href="{{ action('PriceController@index') }}" class="btn btn-success">Danh sách</a>
	</div>
</div>

<div class="row">
	<div class="col-xs-12">
		<div class="box box-primary">
			<!-- form start -->
			{{ Form::open(array('action' => 'PriceController@store')) }}
				<div class="box-body">
					<div class="form-group">
						<label for="username">Min</label>
						<div class="row">
							<div class="col-sm-6">
								<input type="text" class="form-control" id="min" placeholder="min" name="min">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="password">Max</label>
						<div class="row">
							<div class="col-sm-6">
								<input type="text" class="form-control" id="max" placeholder="max
						" name="max">
							</div>
						</div>
					</div>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<input type="submit" class="btn btn-primary" value="Lưu lại" />
					<input type="reset" class="btn btn-default" value="Nhập lại" />
				</div>
			{{ Form::close() }}
		</div>
		<!-- /.box -->
	</div>
</div>

@stop
@endif