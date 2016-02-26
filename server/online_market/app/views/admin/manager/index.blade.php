@extends('admin.layout.default')
@if(Admin::isAdmin())
@section('title')
{{ $title='Quản lý thành viên quản trị' }}
@stop

@section('content')

	<!-- inclue Search form -->
	@include('admin.manager.search')

	<div class="row margin-bottom">
		<div class="col-xs-12">
			<a href="{{ action('ManagerController@create') }}" class="btn btn-primary">Thêm thành viên</a>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
		  <div class="box">
			<div class="box-header">
			  <h3 class="box-title">Danh sách thành viên</h3>
			</div>
			<!-- /.box-header -->
			<div class="box-body table-responsive no-padding">
			  <table class="table table-hover">
				<tr>
				  <th>ID</th>
				  <th>Username</th>
				  <th>Quyền hạn</th>
				  <th style="width:200px;">Action</th>
				</tr>
				@foreach($data as $key => $value)
				<tr>
				  <td>{{ $value->id }}</td>
				  <td>{{ $value->username }}</td>
				  <td>{{ getRole($value->role_id) }}</td>
				  <td>
					{{-- <a href="#" class="btn btn-success">Xem</a> --}}
					<a href="{{action('ManagerController@changePassword', $value->id) }}" class="btn btn-primary">Change Pass</a>
					<a href="{{ action('ManagerController@edit', $value->id) }}" class="btn btn-primary">Sửa</a>
					{{ Form::open(array('method'=>'DELETE', 'action' => array('ManagerController@destroy', $value->id), 'style' => 'display: inline-block;')) }}
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
