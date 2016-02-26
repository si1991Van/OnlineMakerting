@extends('admin.layout.default')
@if(Admin::isAdmin())
@section('title')
{{ $title='Thêm thành viên' }}
@stop

@section('content')

<div class="row margin-bottom">
  <div class="col-xs-12">
    <a href="{{ action('ManagerController@index') }}" class="btn btn-success">Danh sách thành viên</a>
  </div>
</div>

<div class="row">
	<div class="col-xs-12">
		<div class="box box-primary">
        <!-- form start -->
        {{ Form::open(array('action' => 'ManagerController@store')) }}
          <div class="box-body">
            <div class="form-group">
              <label for="username">Tên đăng nhập</label>
              <div class="row">
              	<div class="col-sm-6">
                	<input type="text" class="form-control" id="username" placeholder="Username" name="username">
                </div>
              </div>
            </div>
            <div class="form-group">
              <label for="password">Password</label>
              <div class="row">
              	<div class="col-sm-6">
              		<input type="password" class="form-control" id="password" placeholder="Password
              " name="password">
              	</div>
              </div>
            </div>
            <div class="form-group">
              <label for="email">Email</label>
              	<div class="row">
                	<div class="col-sm-6">
                		<input type="email" class="form-control" id="email" placeholder="Email" name="email">
            		</div>
      			</div>
            </div>
            <div class="form-group">
              <label for="email">Quyền hạn</label>
                <div class="row">
                  <div class="col-sm-6">
                    {{ Form::select('role_id', selectRoleId(), null, array('class' =>'form-control')) }}
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