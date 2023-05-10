	function readImg(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
		$('#preview').attr('src', e.target.result);
		console.log(e.target.result);
		};
		reader.readAsDataURL(input.files[0]);
		} 
		else
		{
		$('#preview').attr('src', "");
		}
	};


$(function()
{
	function uploadImage(files) {
		
		var formData = new FormData();
		
		for (var i = 0; i < files.length; i++) {
	    var file = files[i];
	    formData.append('files', file);	// summernote에 추가한 이미지의 파라미터 이름을 설정
	    }
	    
	    $.ajax({
	      url: '/img/upload',
	      enctype : 'multipart/form-data',
	      type: 'post',
	      data: formData,
	      processData: false,
	      contentType: false,
	      cache: false,
	      timeout : 600000,
	      success: function(response) {
		if(response.added)
		{
	        var list = response.list;
	        for(var i = 0; i < list.length; i++)
	        {
				var img = $('<img>').attr('src', '/pics/'+list[i]);
	        	$('#Commision_Contents').summernote('insertNode', img[0]);		
			}
	    }
	    else
	    {
			alert('error');
		}
	      },
	      error: function(e) {
			console.log(e);
	        console.log('Failed to upload image');
	      }
	    });
	};
	
	function deleteImage(target) {
		
		var imageUrl = $(target).attr("src");
		console.log(imageUrl);
		
	    $.ajax({
	      url: '/img/delete',
	      type: 'post',
	      data: { imageUrl : imageUrl },
	      cache: false,
	      timeout : 600000,
	      success: function(response) {
	      },
	      error: function(e) {
		alert('실패');
	      }
	    });
	};
	
	$('#Commision_Contents').summernote({
	    placeholder: '자신의 커미션에 대한 자세한 설명 및 이미지를 첨부',
	    tabsize: 2,
	    height: 500,
	    defaultImageWidth:50,
	    toolbar: [
	      ['style', ['style', 'fontsize']],
	      ['font', ['bold', 'underline', 'italic', 'clear']],
	      ['color', ['color']],
	      ['para', ['ul', 'ol', 'paragraph']],
	      ['table', ['table']],
	      ['insert', ['link', 'picture', 'hr']],
	      ['view', ['help']]
	    ],
	    callbacks: {
      	onImageUpload: function(files) {
        uploadImage(files);
		},
		
      	onMediaDelete: function(target) {
		deleteImage(target);
		}
    }
	  });
	  
	  $('#Commision_Introduce').summernote({
	    placeholder: '커미션 or 본인의 간단한 설명',
	    tabsize: 2,
	    height: 200,
	    width:500,
	    toolbar: [
	      
	    ]
	  });
	  
	  
	  $('#upload').on('click', function()
	  {
		var title = $('#Title').val();
		var Contents = $('#Commision_Contents').summernote('code');
		var Introduce = $('#Commision_Introduce').summernote('code');
		
		var data = $('#cexplaindata')[0];
		
		var formData = new FormData(data);
		
		
		var tag = $('#Tags').val();
		var tags = tag.split('#');
		for(var i = 0; i<tags.length; i++)
		{
			tags[i] = tags[i].trim();
		}
		tags.splice(0, 1);
		
		
		formData.set('Tags', tags);		
		formData.delete('files');
		formData.set('Introduce', Introduce);
		formData.set('Contents', Contents);
		
		
		for (let [key, value] of formData.entries()) {
 		console.log(key + ': ' + value);
		}
		
		console.log('0');
		
		$.ajax
		({
			type : 'POST',
			url : '/cexplain/upload',
			data : formData,
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false,
			cache : false,
			success : function(res)
			{
				console.log(res.added?'a':'b');
			},
			error : function(e)
			{
				console.log(e);
			}
		})
	})
	
})