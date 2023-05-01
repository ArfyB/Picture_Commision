$(function()
{
	function uploadImage(files) {
		console.log(files.length);
		console.log(files);
		/*
		for (var i = 0; i < files.length; i++) {
	    var file = files[i];
	    var formData = new FormData();
	    formData.append('file', file);
	    $.ajax({
	      url: '/php/upload', // 파일 업로드 처리를 수행하는 PHP 파일의 URL
	      type: 'POST',
	      data: formData,
	      processData: false,
	      contentType: false,
	      success: function(response) {
	        var url = response.url;
	        var img = $('<img>').attr('src', url);
	        $('#summernote').summernote('insertNode', img[0]);
	      },
	      error: function() {
	        console.log('Failed to upload image');
	      }
	    });
  		}
  		*/
	}
	
	$('#summernote').summernote({
	    placeholder: 'Hello stand alone ui',
	    tabsize: 2,
	    height: 120,
	    defaultImageWidth:50,
	    toolbar: [
	      ['style', ['style']],
	      ['font', ['bold', 'underline', 'clear']],
	      ['color', ['color']],
	      ['para', ['ul', 'ol', 'paragraph']],
	      ['table', ['table']],
	      ['insert', ['link', 'picture', 'video']],
	      ['view', ['fullscreen', 'codeview', 'help']]
	    ],
	    callbacks: {
      	onImageUpload: function(files) {
        uploadImage(files);
      }
    }
	  });
	  
	  
	  $('#a').on('click', function()
	  {
		var markupStr1 = $('#summernote').summernote('code');
		
		console.log('1'+markupStr1);
		
		/*
		var cexplaindata = $('#cexplaindata');
		var cexplainform = new FormData(cexplaindata);
		*/
		
		
		var cexplainform = new FormData($('#cexplaindata')[0]); // jQuery 객체에서 HTMLFormElement 객체로 변경
		
		$.ajax
		({
			type : 'post',
			url : '/cexplain/test',
			data : cexplainform,
			processData : false,
			contentType : false,
			cache : false,
			success : function(res)
			{
				if (!res.check)
				{
					alert(res.certify? '메일이 전송 되었습니다' : '메일 전송에 실패 하였습니다');	
				}
				else
				{
					EmailCheck.text('이미 존재하는 계정입니다');
				}
			},
			error : function(e)
			{
				console.log(e);
			}
		})
		
		
	})
})
      
      
/*   
$('#summernote').summernote();
$('#summernote').summernote({
  height: 300,                 // set editor height
  minHeight: null,             // set minimum height of editor
  maxHeight: null,             // set maximum height of editor
  focus: true                  // set focus to editable area after initializing summernote
});
*/