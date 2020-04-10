function validate() {

	var ok = true;
	var valid = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

	var passwordField = document.getElementById("password");
	var emailField = document.getElementById("email");

	if (!emailField.value.match(valid)) {
		alert("Email is invalid!");
		ok = false;
	} else {
		ok = true;
	}

	return ok;

}