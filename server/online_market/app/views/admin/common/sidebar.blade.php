<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">Menu</li>
			<li><a href="{{ action('ProductController@index') }}"><i class="fa fa-laptop"></i> <span>Products</span></a></li>
			@if(Admin::isAdmin())
			<li><a href="{{ action('CategoryController@index') }}"><i class="fa fa-list"></i> <span>Category</span></a></li>
			<li><a href="{{ action('PriceController@index') }}"><i class="fa fa-dollar"></i> <span>Prices</span></a></li>
			<li><a href="#"><i class="fa fa-user"></i> <span>Users</span></a></li>
			<li><a href="{{ action('ManagerController@index') }}"><i class="fa fa-users"></i> <span>Admins</span></a></li>
			@endif
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>