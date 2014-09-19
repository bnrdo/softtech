$(document).ready(function () {
	$('#logoutLink').click(function() {
		$('#formLogout').submit();
	});
	$('#submitReset').click(function(){
		if($('#password').val() !== $('#confirmPassword').val()){
			$('#resetErrorDiv').fadeIn(1000).delay(2000).fadeOut("slow");
			return false;
		}
	});	
	$('.btn-file :file').on('fileselect', function(event, numFiles, label) {    
		var input = $(this).parents('.input-group').find(':text'),
            log = numFiles > 1 ? numFiles + ' files selected' : label;
        
        if( input.length ) {
            input.val(log);
        } else {
            if( log ) alert(log);
        }        
    });
});
