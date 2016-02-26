@extends('admin.layout.default')
@if(Admin::isAdmin())
@section('title')
{{ $title='Quản lý Product' }}
@stop

@section('content')

	<div class="row margin-bottom">
		<div class="col-xs-12">
			{{-- <a href="" class="btn btn-primary">Thêm</a> --}}
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
		  <div class="box">
			<div class="box-header">
			  <h3 class="box-title">Danh sách</h3>
			</div>
			<!-- /.box-header -->
			<div class="box-body table-responsive no-padding">
			  <table class="table table-hover">
				<tr>
				  <th>ID</th>
				  <th>Name</th>
				  <th>Price</th>
				  <th>Type</th>
				  <th>User</th>
				  <th>Category</th>
				  <th>City</th>
				  <th>Status</th>
				  <th style="width:200px;">Action</th>
				</tr>
				@foreach($data as $key => $value)
				<tr>
				  <td>{{ $value->id }}</td>
				  <td>{{ $value->name }}</td>
				  <td>{{ $value->price }}</td>
				  <td>{{ getProductType($value->type_id) }}</td>
				  <td>{{ User::find($value->user_id)->username }}</td>
				  <td>{{ Category::find($value->category_id)->name }}</td>
				  <td>{{ City::find($value->city_id)->name }}</td>
				  <td>{{ getProductStatus($value->status) }}</td>
				  <td>
				  	@if($value->status == ACTIVE)
						<a href="{{ action('ProductController@check', $value->id) }}" class="btn btn-primary">Bỏ duyệt</a>
					@else
						<a href="{{ action('ProductController@check', $value->id) }}" class="btn btn-primary">Duyệt</a>
					@endif
					{{ Form::open(array('method'=>'DELETE', 'action' => array('ProductController@destroy', $value->id), 'style' => 'display: inline-block;')) }}
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
