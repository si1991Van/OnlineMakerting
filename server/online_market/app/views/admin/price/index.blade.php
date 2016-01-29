@extends('admin.layout.default')
@if(Admin::isAdmin())
@section('title')
{{ $title='Quản lý price' }}
@stop

@section('content')

	<div class="row margin-bottom">
		<div class="col-xs-12">
			<a href="{{ action('PriceController@create') }}" class="btn btn-primary">Thêm</a>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
		  <div class="box">
			<div class="box-header">
			  <h3 class="box-title">Danh sách price</h3>
			</div>
			<!-- /.box-header -->
			<div class="box-body table-responsive no-padding">
			  <table class="table table-hover">
				<tr>
				  <th>ID</th>
				  <th>Min</th>
				  <th>Max</th>
				  <th style="width:200px;">Action</th>
				</tr>
				@foreach($data as $key => $value)
				<tr>
				  <td>{{ $value->id }}</td>
				  <td>{{ $value->min }}</td>
				  <td>{{ $value->max }}</td>
				  <td>
					<a href="{{ action('PriceController@edit', $value->id) }}" class="btn btn-primary">Sửa</a>
					{{ Form::open(array('method'=>'DELETE', 'action' => array('PriceController@destroy', $value->id), 'style' => 'display: inline-block;')) }}
					<button class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa?');">Xóa</button>
					{{ Form::close() }}

				  </td>
				</tr>
				@endforeach
			  </table>
			</div>
			<!-- /.box-body -->
		  </div>
		  <!-- /.box -->
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
			{{ $data->appends(Request::except('page'))->links() }}
		</div>
	</div>

@stop
@endif
