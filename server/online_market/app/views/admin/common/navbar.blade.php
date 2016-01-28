<header class="main-header">
  <!-- Logo -->
  <a href="#" class="logo">
    <!-- mini logo for sidebar mini 50x50 pixels -->
    <span class="logo-mini">Online</span>
    <!-- logo for regular state and mobile devices -->
    <span class="logo-lg">Online</span>
  </a>
  <!-- Header Navbar: style can be found in header.less -->
  <nav class="navbar navbar-static-top" role="navigation">
    <!-- Sidebar toggle button-->
    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
      <span class="sr-only">Menu</span>
    </a>

    <div class="navbar-custom-menu">
      <ul class="nav navbar-nav">

        <li class="user">
          <a href="#"><i class="fa fa-user"></i>{{ Auth::admin()->get()->username }}</a>
        </li>

        <li class="user">
        	<a href="{{ action('ManagerController@edit', Auth::admin()->get()->id) }}"><i class="fa fa-user"></i>Tài khoản</a>
        </li>

        <li class="user">
        	<a href="{{ action('AdminController@logout') }}"><i class="fa fa-power-off"></i>Đăng xuất</a>
        </li>

      </ul>
    </div>
  </nav>
</header>