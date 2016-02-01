@if (count($errors->all()) > 0)
<div class="alert alert-danger alert-block">
	<button type="button" class="close" data-dismiss="alert">&times;</button>
	{{ HTML::ul($errors->all()) }}
</div>
@endif

@if ($message = Session::get('success'))
<div class="alert alert-success alert-block">
    <i class="fa fa-check"></i>
	<button type="button" class="close" data-dismiss="alert">&times;</button>
    @if(is_array($message))
    @foreach ($message as $m)
    {{ $m }}
    @endforeach
    @else
    {{ $message }}
    @endif
</div>
@endif

@if ($message = Session::get('error'))
<div class="alert alert-danger alert-dismissable">
    <i class="fa fa-ban"></i>
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
    @if(is_array($message))
    @foreach ($message as $m)
    {{ $m }}
    @endforeach
    @else
    {{ $message }}
    @endif
</div>
@endif

@if ($message = Session::get('warning'))
<div class="alert alert-warning alert-block">
    <i class="fa fa-warning"></i>
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    @if(is_array($message))
    @foreach ($message as $m)
    {{ $m }}
    @endforeach
    @else
    {{ $message }}
    @endif
</div>
@endif

@if ($message = Session::get('notice'))
<div class="alert alert-warning alert-block">
    <i class="fa fa-warning"></i>
	<button type="button" class="close" data-dismiss="alert">&times;</button>
    @if(is_array($message))
    @foreach ($message as $m)
    {{ $m }}
    @endforeach
    @else
    {{ $message }}
    @endif
</div>
@endif

@if ($message = Session::get('info'))
<div class="alert alert-info alert-block">
    <i class="fa fa-info"></i>
	<button type="button" class="close" data-dismiss="alert">&times;</button>
    @if(is_array($message))
    @foreach ($message as $m)
    {{ $m }}
    @endforeach
    @else
    {{ $message }}
    @endif
</div>
@endif

@if (Session::has('message'))
    <div class="alert alert-success">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong><center>{{ Session::get('message') }}</center></strong>
    </div>
@endif
