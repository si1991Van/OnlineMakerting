<div class="margin-bottom">
    {{ Form::open(array('action' => 'UserController@search', 'method' => 'GET')) }}
        <div class="input-group" style="width: 150px; display:inline-block;">
            <label>Tài khoản</label>
            <input type="text" name="user_name" class="form-control" placeholder="Tài khoản" />
        </div>
        <div class="input-group" style="width: 150px; display:inline-block;">
            <label>Từ ngày</label>
            <input type="text" name="start_date" class="form-control" id="datepickerStartdate" placeholder="Từ ngày" />
        </div>
        <div class="input-group" style="width: 150px; display:inline-block;">
            <label>Đến ngày</label>
            <input type="text" name="end_date" class="form-control" id="datepickerEnddate" placeholder="Đến ngày" />
        </div>
        <div class="input-group" style="width: 150px; display:inline-block;">
            <label>Từ ngày đăng nhập</label>
            <input type="text" name="from_update_at" class="form-control" id="start_update_date" placeholder="từ ngày đăng nhập" />
        </div>
        <div class="input-group" style="width: 150px; display:inline-block;">
            <label>Đến ngày đăng nhập</label>
            <input type="text" name="to_update_at" class="form-control" id="end_update_date" placeholder="Đến ngày đăng nhập" />
        </div>
        <div class="input-group" style="display: inline-block; vertical-align: bottom;">
            <input type="submit" value="Search" class="btn btn-primary" />
        </div>
    {{ Form::close() }}
</div>