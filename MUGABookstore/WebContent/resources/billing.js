/**
 * Shipping Address Validation
 */

function validate() {
	var ok = true;

	var firstname = document.getElementById("orderFirstName").value;
	var lastname = document.getElementById("orderLastName").value;
	var sBA = document.getElementById("shippingAddress").value;
	var sCity = document.getElementById("shippingCity").value;
	var sState = document.getElementById("shippingState/Province").value;
	var sCountry = document.getElementById("shippingCountry").value;
	var sPostal = document.getElementById("shippingPostal").value;
	var sPhone = document.getElementById("shippingPhoneNo").value;
	var email = document.getElementById("email").value;
	var password = document.getElementById("password").value;
	var cardholder = document.getElementById("nameOnCard").value;
	var cvv = document.getElementById("cardCVV").value;

	var billingCity = document.getElementById("billingCity").value;
	var billingAddress = document.getElementById("billingAddress").value;
	var billingProvince = document.getElementById("billingState/province").value;
	var billingCountry = document.getElementById("billingCountry").value;
	var billingPostal = document.getElementById("billingPostal/zipcode").value;

	var validEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var validPhoneNo = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
	
	if (firstname == "") {
		document.getElementById("nameError").style.display = "inline";
		document.getElementById("nameError").style.color = "red";

	} else {
		document.getElementById("nameError").style.display = "none";

	}

	if (lastname == "") {
		document.getElementById("passwordError").style.display = "inline";
		document.getElementById("passwordError").style.color = "red";
		var ok = false;
	} else {
		document.getElementById("passwordError").style.display = "none";

	}

	if (sBA == "") {
		document.getElementById("shippingAddressError").style.display = "inline";
		document.getElementById("shippingAddressError").style.color = "red";
		var ok = false;
	} else {
		document.getElementById("shippingAddressError").style.display = "none";

	}

	if (sCity == "") {
		document.getElementById("shippingCityError").style.display = "inline";
		document.getElementById("shippingCityError").style.color = "red";
		var ok = false;
	} else {
		document.getElementById("shippingCityError").style.display = "none";

	}

	if (sCity == "") {
		document.getElementById("shippingStateError").style.display = "inline";
		document.getElementById("shippingStateError").style.color = "red";
		var ok = false;
	} else {
		document.getElementById("shippingStateError").style.display = "none";

	}
	if (sCountry == "") {
		document.getElementById("shippingCountryError").style.display = "inline";
		document.getElementById("shippingCountryError").style.color = "red";
		var ok = false;
	} else {
		document.getElementById("shippingCountryError").style.display = "none";

	}
	if (sPostal.length > 7 || isNaN(sPostal) || sPostal.length == 0) {
		document.getElementById("shippingPostalError").style.display = "inline";
		document.getElementById("shippingPostalError").style.color = "red";
		var ok = false;
	} else {
		document.getElementById("shippingPostalError").style.display = "none";

	}
	if (!sPhone.match(validPhoneNo)) {
		document.getElementById("shippingPhoneError").style.display = "inline";
		document.getElementById("shippingPhoneError").style.color = "red";
		var ok = false;
	} else {
		document.getElementById("shippingPhoneError").style.display = "none";
	}

	if (billingCity == "") {
		document.getElementById("billingCityError").style.display = "inline";
		document.getElementById("billingCityError").style.color = "red";
		var ok = false;
	} else {
		document.getElementById("billingCityError").style.display = "none";

	}

	if (billingAddress == "") {
		document.getElementById("billingAddressError").style.display = "inline";
		document.getElementById("billingAddressError").style.color = "red";
		var ok = false;
	} else {
		document.getElementById("billingAddressError").style.display = "none";

	}
	
	
	if (email == " " || (!email.match(validEmail))) {
		document.getElementById("emailError").style.display = "inline";
		document.getElementById("emailError").style.color = "red";
		var ok = false;
	} else {
		document.getElementById("emailError").style.display = "none";
	}

	if (password.length <= 6 || password == "") {
		document.getElementById("passwordError").style.display = "inline";
		document.getElementById("passwordError").style.color = "red";
		var ok = false;
	} else {
		document.getElementById("passwordError").style.display = "none";
	}

	/*
	 * May use Stripe
	 */

	if (cardholder == "") {
		document.getElementById("cardholderError").style.display = "inline";
		document.getElementById("cardholderError").style.color = "red";
		var ok = false;
	} else {
		document.getElementById("cardholderError").style.display = "none";
	}

	if ((cvv.length != 3) || isNaN(cvv)) {
		document.getElementById("cvvError").style.display = "inline";
		document.getElementById("cvvError").style.color = "red";
		var ok = false;
	} else {
		document.getElementById("cvvError").style.display = "none";

	}

	return ok;

}
