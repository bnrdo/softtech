function logout(){
	$('#formLogout').submit();	
}

$(document).ready(function () {
	$('#logoutLink').click(logout);
});