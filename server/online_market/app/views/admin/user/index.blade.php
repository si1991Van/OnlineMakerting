@extends('admin.layout.default')

@section('title')
{{ $title='Quản lý User' }}
@stop

@section('content')
@include('admin.user.search')
<!-- inclue Search form 

-->
<div class="row">
	<div class="col-xs-12">
	  <div class="box">
		<div class="box-header">
		  <h3 class="box-title">Danh sách User</h3>
		</div>
		<!-- /.box-header -->
		<div class="box-body table-responsive no-padding">
		  <table class="table table-hover">
			<tr>
			  <th>ID</th>
			  <th>Tài khoản</th>
			  <th>Loại tài khoản</th>
			  <th>email</th>
			  <th>Tên</th>
			  <th>Ip</th>
			  <th>Device</th>
			  <th>Đăng nhập cuối</th>
			  <th>Trạng thái</th>
			  <th>Action</th>
			</tr>
			@foreach($inputUser as $value)
			<tr>
			  <td>{{ $value->id }}</td>
			  <td>{{ UserManager::getUsername($value->id)['user_name'] }}</td>
			  <td>{{ UserManager::getUsername($value->id)['type_user'] }}</td>
			  <td>{{ $value->email }}</td>
			  <td>{{ $value->fullname }}</td>
			  <td>{{ $value->ip }}</td>
			  <td>{{ getNameDevice($value->device) }}</td>
			  <td>{{ $value->updated_at }}</td>
			  <td>{{ UserManager::getStatus($value->status) }}</td>
			  <td>
			    @if(Admin::isAdmin() || Admin::isEditor())
				  	@if($value->status == ACTIVE )
					<a href="{{action('UserController@edit', $value->id) }}" class="btn btn-danger">Hủy</a>
					@else
					<a href="{{action('UserController@edit', $value->id) }}" class="btn btn-primary">Kích hoạt</a>
					@endif
					@if(UserManager::getUsername($value->id)['type_user'] == TYPESYSTEM)
						<a href="{{action('UserController@changePassword', $value->id) }}" class="btn btn-primary">Đổi mật khẩu</a>
					@endif
		<!-- 		{{ Form::open(array('method'=>'DELETE', 'action' => array('UserController@destroy', $value->id), 'style' => 'display: inline-block;')) }}
				<button class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa?');">Xóa</button>
				{{ Form::close() }} -->
				@endif
			  </td>
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
		<ul class="pagination">
		<!-- phan trang -->
		{{ $inputUser->appends(Request::except('page'))->links() }}
		</ul>
	</div>
</div>

@stop

