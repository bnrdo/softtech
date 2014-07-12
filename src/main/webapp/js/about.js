$(document).ready(function(){
	$("#btnSubmit").click(function(){
		if(validate()) return;
		$("#btnSubmit").button('loading');
		$("#formFeedback").submit();
	});
	
	$("#formFeedback").submit(function(event) {
		var name = $("#name").val();
		var phone = $("#phone").val();
		var email = $("#email").val();
		var concern = $("#concern").val();
		var requestJson = { "name" : name, "phone" : phone, "email" : email, "concern" : concern};
		
		$.ajax({
			url: $("#formFeedback").attr( "action"),
			data: JSON.stringify(requestJson),
			type: "POST",
			
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			
			success: function(concern) {
				console.log(concern);
				clear();
				$("#btnSubmit").button('reset');
				$("#confirmation").html('<div id="confirmation" class="alert alert-success alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button><span class="glyphicon glyphicon-ok-circle"></span> <strong>Success!</strong> Your message is sent to our team!</div>');
			},
			
			error: function() {
				$("#btnSubmit").button('reset');
				$("#confirmation").html('<div id="confirmation" class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button><span class="glyphicon glyphicon-remove-circle"></span> <strong>Failed!</strong> Something seems wrong. Let us get back to you on this.</div>');
			}
		});
		
		event.preventDefault();
	});
});

function validate() {
	var result = false;
	if($("#name").val() == "") {
		result = true;
		$("#formGroupName").addClass("has-error");
	} else {
		$("#formGroupName").removeClass("has-error");
	}
	
	if($("#phone").val() == "") {
		result = true;
		$("#formGroupPhone").addClass("has-error");
	} else {
		$("#formGroupPhone").removeClass("has-error");
	}
	
	if($("#email").val() == "") {
		result = true;
		$("#formGroupEmail").addClass("has-error");
	} else {
		$("#formGroupEmail").removeClass("has-error");
	}
	
	if($("#concern").val() == "") {
		result = true;
		$("#formGroupConcern").addClass("has-error");
	} else {
		$("#formGroupConcern").removeClass("has-error");
	}
	
	return result;
}

function clear() {
	$("#name").val("");
	$("#phone").val("");
	$("#email").val("");
	$("#concern").val("");
	
	$("#formGroupName").removeClass("has-error");
	$("#formGroupPhone").removeClass("has-error");
	$("#formGroupEmail").removeClass("has-error");
	$("#formGroupConcern").removeClass("has-error");
}