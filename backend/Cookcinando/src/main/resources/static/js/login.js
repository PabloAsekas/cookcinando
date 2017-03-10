$(document).ready(function() {
	
	var rad = document.registro.type;
	$('#type').val("individual");
	for(var i = 0; i< rad.length; i++) {
		rad[i].onclick = function() {
			$('#type').val(this.value);
			var tipo = $('#type').val();
  		};
  	}
});

function validateEmail(email) {
	
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(email);
}

function validate() {
	
	var email = $("#email").val();
	if(validateEmail(email)) {
		$("#email").css("color", "green");
	}
	else {
		$("#email").css("color", "red");
	}
	return false;
	
}
