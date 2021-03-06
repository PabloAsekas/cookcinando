$(document).ready(function() {
	
	$('#registry-error').addClass('hidden');
	
	var rad = document.registro.type;
	$('#type').val("individual");
	for(var i = 0; i< rad.length; i++) {
		rad[i].onclick = function() {
			$('#type').val(this.value);
			var tipo = $('#type').val();
  		};
  	}
	
	//$('#registry-success').addClass('hidden');
});

$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});

//REGISTRY

function validateNick(){
	
	var expresion = /^@{1}\w+/g;
	var nick = $('#nick').val();
	
	if(expresion.test(nick)){
		$("#nick-input").removeClass("has-error");
		$("#nick-input").addClass("has-success");
		return true;
	}
	else {
		$("#nick-input").removeClass("has-success");
		$("#nick-input").addClass("has-error");
		return false;
	}
}

function validateEmail() {
	
	var expresion = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var email = $('#email').val();
	
	if(expresion.test(email)){
		$("#email-input").removeClass("has-error");
		$("#email-input").addClass("has-success");
		return true;
	}
	else {
		$("#email-input").removeClass("has-success");
		$("#email-input").addClass("has-error");
		return false;
	}
}

function validatePassword() {
	
	var expresion = /[0-9a-zA-Z]+$/;
	var password = $('#password').val();
	
	if(expresion.test(password) && password.length >= 4){
		$("#password-input").removeClass("has-error");
		$("#password-input").addClass("has-success");
		return true;
	}
	else {
		$("#password-input").removeClass("has-success");
		$("#password-input").addClass("has-error");
		return false;
	}
}

function comparePasswords() {
	
	var password = $('#password').val();
	var repeatedPassword = $('#repeated-password').val();
	
	if(repeatedPassword !== "" && password === repeatedPassword){
		$("#repeated-password-input").removeClass("has-error");
		$("#repeated-password-input").addClass("has-success");
		return true;
	}
	else {
		$("#repeated-password-input").removeClass("has-success");
		$("#repeated-password-input").addClass("has-error");
		return false;
	}
}

function validate() {
	
	var nick = $('#nick').val();
	var email = $('#email').val();
	var password = $('#password').val();
	var repeatedPassword = $('#repeated-password').val();
	
	if((nick !== "") && (email !== "") && (password !== "") && (repeatedPassword !== "")){
		if(validateNick() && validateEmail() && validatePassword() && comparePasswords()){
			//$('#registry-success').removeClass('hidden');
			return true;
		}
		else {
			$('#registry-error').removeClass('hidden');
			return false;
		}
	}	
	else {
		validateNick() ? '' : $("#nick-input").addClass("has-error");
		validateEmail() ? '' : $("#email-input").addClass("has-error");
		validatePassword() ? '' : $("#password-input").addClass("has-error");
		comparePasswords() ? '' : $("#repeated-password-input").addClass("has-error");
		$('#registry-error').removeClass('hidden');
		return false;
	}
}
