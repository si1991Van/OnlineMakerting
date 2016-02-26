<div class="margin-bottom">
	{{ Form::open(array('action' => 'ManagerController@search', 'method' => 'GET')) }}
		<div class="input-group" style="width: 150px; display:inline-block;">
			<label>Từ khóa</label>
		  	<input type="text" name="keyword" class="form-control" placeholder="Search" />
		</div>
		<div class="input-group" style="width: 150px; display:inline-block;">
			<label>Quyền hạn</label>
		  	{{ Form::select('role_id', selectRoleId(), null, array('class' =>'form-control')) }}
		</div>

		<div class="input-group" style="display: inline-block; vertical-align: bottom;">
			<input type="submit" value="Search" class="btn btn-primary" />
		</div>
	{{ Form::close() }}
</div>