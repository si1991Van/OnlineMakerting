<script src="{{ asset('admins/ckeditor/ckeditor.js')}}"></script>
<script>
    CKEDITOR.replace( 'editor1',
    {
        entities_latin: false,
        entities_greek: false,
        filebrowserBrowseUrl : '/admins/ckeditor/ckfinder/ckfinder.html',
        filebrowserImageBrowseUrl : '/admins/ckeditor/ckfinder/ckfinder.html?type=Images',
        filebrowserFlashBrowseUrl : '/admins/ckeditor/ckfinder/ckfinder.html?type=Flash',
        filebrowserUploadUrl : '/admins/ckeditor/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Files',
        filebrowserImageUploadUrl : '/admins/ckeditor/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Images',
        filebrowserFlashUploadUrl : '/admins/ckeditor/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Flash'
        }
    );
</script>