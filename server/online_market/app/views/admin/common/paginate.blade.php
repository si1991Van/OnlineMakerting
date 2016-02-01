<div class="row">
	<div class="col-xs-12">
		<ul class="pagination">
		<!-- phan trang -->
		{{ $input->appends(Request::except('page'))->links() }}
		</ul>
	</div>
</div>
