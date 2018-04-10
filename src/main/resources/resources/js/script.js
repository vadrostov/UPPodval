jQuery(function($) {


	$("#text").summernote({
		
		lang:'ru-RU',
		height:300,
		minHeight:200,
		maxHeight:400,
		focus:true,
		placeholder:'Введите данные',
		toolbar:[
		//[groupname,[list buttons]]
			['insert',['picture','link','video','table']],
			['style',['bold','italic','underline']],
			['font', ['strikethrough', 'superscript', 'subscript']],
		    ['fontsize', ['fontsize','fontname']],
		    ['color', ['color']],
		    ['para', ['ul', 'ol', 'paragraph','style']],
		    ['height', ['height','codeview']]
		
		],
		fontNames:['Arial','Times New Roman','Verdana'],
		disableDragAndDrop:true
		
	});

	
	
	$('.btn').click(function(e) {
		e.preventDefault();
		
		//alert($("#text").summernote('code'));
		
		//$("#text").summernote('code','hello');
		//$("#text").summernote('editor.insertText','hello');
		//$("#text").summernote('reset');
		//$("#text").summernote('disable');///enable
		
		//$("#text").summernote('createRange');
		//$("#text").summernote('saveRange');
		
		$("#text").summernote('italic');
		
		
	});
	
	$('h1').click(function(e) {
		
		//alert($("#text").summernote('restoreRange'));
		
	});
	

});