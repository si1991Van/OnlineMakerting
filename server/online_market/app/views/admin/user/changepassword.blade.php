@extends('admin.layout.default')

@section('title')
{{ $title='Đổi mật khẩu' }}
@stop

@section('content')

<div class="row margin-bottom">
	<div class="col-xs-12">
		<a href="{{ action('UserController@index') }} " class="btn btn-success">Danh sách user</a>
	</div>
</div>

<div class="row">
	<div class="col-xs-12">
		<div class="box box-primary">
			<!-- form start -->
			{{ Form::open(array('action' => array('UserController@update', $inputUser->id), 'method' => 'PUT')) }}
			<div class="box-body">
				<div class="form-group">
					<label for="name">Tên User</label>
					<div class="row">
						<div class="col-sm-6">	       
							<input name="user_name" type="text" class="form-control" value="{{ $inputUser->user_name }}" readonly="true">          	           	        	
						</div>
					</div>
				</div>
			<div class="box-body">
				<div class="form-group">
					<label for="password">Mật khẩu mới</label>
					<div class="row">
						<div class="col-sm-6">	       
							<input name="password" type="password" class="form-control" placehoder="Nhập mật khẩu mới!">          	
						   <!-- {{ Form::password('password', null , textParentCategory('Tên thể loại tin')) }} -->
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="repassword">Nhập lại mật khẩu mới</label>
					<div class="row">
						<div class="col-sm-6">	       
						<input name="repassword" type="password" class="form-control" placehoder="Nhập mật khẩu mới!">          	           	
						   <!-- {{ Form::password('repassword', null , textParentCategory('Tên thể loại tin')) }} -->
						</div>
					</div>
				</div>
			  <!-- /.box-body -->

			  <div class="box-footer">
				{{ Form::submit('Lưu lại', array('class' => 'btn btn-primary')) }}
			  </div>
			{{ Form::close() }}
		  </div>
		  <!-- /.box -->
	</div>
</div>
@stop
