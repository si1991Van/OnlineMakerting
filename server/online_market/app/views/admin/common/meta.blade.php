{{-- meta input for edit form --}}
@if(isset($inputSeo) && isset($pathToImageSeo))
<div class="form-group">
	<label for="metaname"><u>Thẻ meta</u></label>
	<div class="box-body">
		<div class="form-group">
			<label for="status_seo">Trạng thái</label>
			{{ Form::select('status_seo', [INACTIVE => 'Chưa kích hoạt', ACTIVE => 'Kích hoạt'], $inputSeo->status_seo, array('class' => 'form-control')) }}
		</div>
		<div class="form-group">
			<label for="title_site">Thẻ title</label>
			{{ Form::text('title_site', $inputSeo->title_site, textParentCategory('Thẻ title', true)) }}
		</div>
		<div class="form-group">
			<label for="description_site">Thẻ Descript site</label>
			{{ Form::textarea('description_site', $inputSeo->description_site, textParentCategory('Thẻ Descript site', true)) }}
		</div>
		<div class="form-group">
			<label for="keyword_site">Thẻ Keyword</label>
			{{ Form::text('keyword_site', $inputSeo->keyword_site, textParentCategory('Thẻ Keyword', true)) }}
		</div>
		<div class="form-group">
			<label for="title_fb">Thẻ title facebook</label>
			{{ Form::text('title_fb', $inputSeo->title_fb, textParentCategory('Thẻ facebook', true)) }}
		</div>
		<div class="form-group">
			<label for="description_fb">Thẻ descript facebook</label>
			{{ Form::textarea('description_fb', $inputSeo->description_fb, textParentCategory('Thẻ descript facebook', true)) }}
		</div>
		<div class="form-group">
			<label for="image_url_fb">Upload ảnh</label>
			{{ Form::file('image_url_fb') }}
			@if(!empty($inputSeo->image_url_fb))
			<img class="image_fb" src="{{ url($pathToImageSeo . $inputSeo->image_url_fb) }}" />
			@endif
		</div>
		<div class="form-group">
			<label for="image_url_fb">{{ $inputSeo->image_url_fb }}</label>
		</div>
	</div>
</div>
@else
{{-- meta input for create form --}}
<div class="form-group">
	<label for="metaname"><u>Thẻ meta</u></label>
	<div class="box-body">
		<div class="form-group">
			<label for="status_seo">Trạng thái</label>
			{{ Form::select('status_seo', [0 => 'Chưa kích hoạt', 1 => 'Kích hoạt'], null, array('class' => 'form-control')) }}
		</div>
		<div class="form-group">
			<label for="title_site">Thẻ title</label>
			{{ Form::text('title_site','',textParentCategory('Thẻ title')) }}
		</div>
		<div class="form-group">
			<label for="description_site">Thẻ Descript site</label>
			{{ Form::textarea('description_site', null , textParentCategory('Thẻ Descript site')) }}
		</div>
		<div class="form-group">
			<label for="keyword_site">Thẻ Keyword</label>
			{{ Form::text('keyword_site', null , textParentCategory('Thẻ Keyword')) }}
		</div>
		<div class="form-group">
			<label for="title_fb">Thẻ title facebook</label>
			{{ Form::text('title_fb', null , textParentCategory('Thẻ facebook')) }}
		</div>
		<div class="form-group">
			<label for="description_fb">Thẻ descript facebook</label>
			{{ Form::textarea('description_fb', null , textParentCategory('Thẻ descript facebook')) }}
		</div>
		<div class="form-group">
			<label for="image_url_fb">Upload ảnh</label>
			{{ Form::file('image_url_fb') }}
		</div>
	</div>
</div>
@endif