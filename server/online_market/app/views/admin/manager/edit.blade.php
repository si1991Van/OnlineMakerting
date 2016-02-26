@extends('admin.layout.default')

@section('title')
{{ $title='Sửa thành viên' }}
@stop

@section('content')

@if(Admin::isAdmin())
<div class="row margin-bottom">
	<div class="col-xs-12">
		<a href="{{ action('ManagerController@index') }}" class="btn btn-success">Danh sách thành viên</a>
		<a href="{{ action('ManagerController@create') }}" class="btn btn-primary">Thêm thành viên</a>
	</div>
</div>
@endif

<div class="row">
	<div class="col-xs-12">
		<div class="box box-primary">
			<!-- form start -->
			{{ Form::open(array('action' => array('ManagerController@update', $data->id), 'method' => 'PUT')) }}
				<div class="box-body">
					<div class="form-group">
						<label for="username">Tên đăng nhập</label>
						<div class="row">
							<div class="col-sm-6">
								<input type="text" class="form-control" id="username" placeholder="Username" name="username" value="{{ $data->username }}">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="password">Password</label>
						<div class="row">
							<div class="col-sm-6">
								<input type="text" class="form-control" id="password" placeholder="Password" name="password">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="email">Email</label>
							<div class="row">
								<div class="col-sm-6">
									<input type="email" class="form-control" id="email" placeholder="Email" name="email" value="{{ $data->email }}" >
							</div>
						</div>
					</div>
					@if(Admin::isAdmin())
						<div class="form-group">
							<label for="email">Quyền hạn</label>
								<div class="row">
									<div class="col-sm-6">
										{{ Form::select('role_id', selectRoleId(), $data->role_id, array('class' =>'form-control')) }}
								</div>
							</div>
						</div>
					@endif
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
