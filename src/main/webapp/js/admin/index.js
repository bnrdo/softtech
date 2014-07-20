function logout(){
	$('#formLogout').submit();	
}

function editUserAccount(email){
	$('#emailToEditInput').val(email);	
	$('#formEditUser').submit();	
}

$(document).ready(function () {
	$('#logoutLink').click(logout);
	$('.emailAccountLink').click(function(){
		editUserAccount($(this).text());
	});
	$('#userAccountRolesDiv ul li').each(function(){
		$('#cb_'+$.trim($(this).text())).prop('checked', true);
	});
	var currentStatus = $('#userAccountCurrentStatus').text();
	$('#rb_'+currentStatus).prop('checked', true);
});