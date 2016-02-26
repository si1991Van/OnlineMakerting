<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title> @yield('title') </title>
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<!-- Bootstrap 3.3.5 -->
	
	{{HTML::style('adminlte/bootstrap/css/bootstrap.min.css') }}
	<!-- Font Awesome -->
	{{HTML::style('adminlte/dist/css/font-awesome.min.css') }}
	<!-- Ionicons -->
	{{HTML::style('adminlte/dist/css/ionicons.min.css') }}
	<!-- Theme style -->
	{{HTML::style('adminlte/dist/css/AdminLTE.min.css') }}
	{{HTML::style('adminlte/dist/css/admin.css') }}
	<!-- AdminLTE Skins. Choose a skin from the css/skins
			 folder instead of downloading all of them to reduce the load. -->
	{{HTML::style('adminlte/dist/css/skins/_all-skins.min.css') }}
	<!-- Date Picker -->
	{{-- {{HTML::style('adminlte/plugins/datepicker/datepicker3.css') }} --}}
	<!-- Daterange picker -->
	{{-- {{HTML::style('adminlte/plugins/daterangepicker/daterangepicker-bs3.css') }} --}}
	<!-- bootstrap wysihtml5 - text editor -->
	{{-- {{HTML::style('adminlte/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css') }} --}}
	<!-- Date Time Picker -->
	{{HTML::style('adminlte/plugins/datetimepicker/bootstrap-datetimepicker.min.css') }}
	
	{{ HTML::style('adminlte/plugins/jQueryUI/jquery-ui.css') }}
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->

	<!-- jQuery 2.1.4 -->
	{{ HTML::script('adminlte/plugins/jQuery/jQuery-2.1.4.min.js') }}
	<!-- jQuery UI 1.11.4 -->
	{{ HTML::script('adminlte/plugins/jQueryUI/jquery-ui.min.js') }}
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->

	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>
	<!-- Bootstrap 3.3.5 -->
	{{ HTML::script('adminlte/bootstrap/js/bootstrap.min.js') }}
	<!-- daterangepicker -->
	{{-- {{ HTML::script('adminlte/plugins/daterangepicker/moment.min.js') }} --}}
	{{-- {{ HTML::script('adminlte/plugins/daterangepicker/daterangepicker.js') }} --}}
	<!-- datepicker -->
	{{-- {{ HTML::script('adminlte/plugins/datepicker/bootstrap-datepicker.js') }} --}}
	<!-- Bootstrap WYSIHTML5 -->
	{{-- {{ HTML::script('adminlte/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js') }} --}}
	<!-- datetimepicker -->
	{{ HTML::script('adminlte/plugins/datetimepicker/bootstrap-datetimepicker.min.js') }}
	<!-- FastClick -->
	{{ HTML::script('adminlte/plugins/fastclick/fastclick.js') }}
	<!-- AdminLTE App -->
	{{ HTML::script('adminlte/dist/js/app.min.js') }}

	<script>
	  $(function () {
	    $('#start_date').datetimepicker({
	    	format: "yyyy-mm-dd hh:ii:00",
			todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
	    });
	    $('#end_date').datetimepicker({
	    	format: "yyyy-mm-dd hh:ii:00",
			todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
	    });
	    $('#start_update_date').datetimepicker({
	    	format: "yyyy-mm-dd hh:ii:00",
			todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
	    });
	    $('#end_update_date').datetimepicker({
	    	format: "yyyy-mm-dd hh:ii:00",
			todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
	    });
	
	    $('#datepickerStartdate').datepicker({
	    	dateFormat: 'yy-mm-dd',
			});
	    $('#datepickerEnddate').datepicker({
	    	dateFormat: 'yy-mm-dd',
			});
	  });
	</script>

</head>