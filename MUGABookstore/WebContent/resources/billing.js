/**
 * Shipping Address Validation
 */

function toggleBilling() {
	var checkbox = document.getElementById("infoAddDiff");
	var billDiv = document.getElementById("billingAddressInfo");
	if (checkbox.checked) {
		billDiv.style.display = "inline";
	}
	else {
		billDiv.style.display = "none";
	}
}

function validate(userHide) {
	let ok = true;

	/*
	 * Shipping info
	 */
	const sBA = document.getElementById("shippingAddress").value;
	const sCity = document.getElementById("shippingCity").value;
	const sState = document.getElementById("shippingState/Province").value;
	const sCountry = document.getElementById("shippingCountry").value;
	const sPostal = document.getElementById("shippingPostal").value;
	const sPhone = document.getElementById("shippingPhoneNo").value;

	/*
	 * Card info
	 */
	const cardholder = document.getElementById("nameOnCard").value;
	const cvv = document.getElementById("cardCVV").value;
	const card = document.getElementById("ccNum").value;

	/*
	 * Billing info
	 */

	const billingCity = document.getElementById("billingCity").value;
	const billingAddress = document.getElementById("billingAddress").value;
	const billingProvince = document.getElementById("billingState/province").value;
	const billingCountry = document.getElementById("billingCountry").value;
	const billingPostal = document.getElementById("billingPostal/zipcode").value;
	const billingPhone = document.getElementById("billingPhoneNo").value;

	const validEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	const validPhoneNo = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;

	/*
	 * Validate firstname & lastname null when user logged in
	 *
	 */

	const firstname = document.getElementById("orderFirstName")
	const lastname = document.getElementById("orderLastName")
	if (firstname && firstname.value === "") {
		document.getElementById("nameError").style.display = "inline";
		document.getElementById("nameError").style.color = "red";
		ok = false;
	} else {
		document.getElementById("nameError").style.display = "none";

	}

	if (lastname && lastname.value === "") {
		document.getElementById("lastError").style.display = "inline";
		document.getElementById("lastError").style.color = "red";
		ok = false;
	} else {
		document.getElementById("lastError").style.display = "none";

	}

	/*
	 * Shipping info
	 */
	if (sBA === "") {
		document.getElementById("shippingAddressError").style.display = "inline";
		document.getElementById("shippingAddressError").style.color = "red";
		ok = false;
	} else {
		document.getElementById("shippingAddressError").style.display = "none";

	}

	if (sCity === "") {
		document.getElementById("shippingCityError").style.display = "inline";
		document.getElementById("shippingCityError").style.color = "red";
		ok = false;
	} else {
		document.getElementById("shippingCityError").style.display = "none";

	}

	if (sCity === "") {
		document.getElementById("shippingStateError").style.display = "inline";
		document.getElementById("shippingStateError").style.color = "red";
		ok = false;
	} else {
		document.getElementById("shippingStateError").style.display = "none";

	}
	if (sCountry === "") {
		document.getElementById("shippingCountryError").style.display = "inline";
		document.getElementById("shippingCountryError").style.color = "red";
		ok = false;
	} else {
		document.getElementById("shippingCountryError").style.display = "none";

	}
	if (sPostal.length > 7 || sPostal.length === 0) {
		document.getElementById("shippingPostalError").style.display = "inline";
		document.getElementById("shippingPostalError").style.color = "red";
		ok = false;
	} else {
		document.getElementById("shippingPostalError").style.display = "none";

	}
	if (!sPhone.match(validPhoneNo)) {
		document.getElementById("shippingPhoneError").style.display = "inline";
		document.getElementById("shippingPhoneError").style.color = "red";
		ok = false;
	} else {
		document.getElementById("shippingPhoneError").style.display = "none";
	}

	/*
	 * Billing info
	 */

	const billingChecked = document.getElementById("infoAddDiff");

	if (!billingChecked.checked) {
	} else {

		if (billingCity === "") {
			document.getElementById("billingCityError").style.display = "inline";
			document.getElementById("billingCityError").style.color = "red";
			ok = false;
		} else {
			document.getElementById("billingCityError").style.display = "none";

		}

		if (billingPostal > 7 || billingPostal.length === 0) {
			document.getElementById("billingPostalError").style.display = "inline";
			document.getElementById("billingPostalError").style.color = "red";
			ok = false;
		} else {
			document.getElementById("billingPostalError").style.display = "none";

		}
		if (billingAddress === "") {
			document.getElementById("billingAddressError").style.display = "inline";
			document.getElementById("billingAddressError").style.color = "red";
			ok = false;
		} else {
			document.getElementById("billingAddressError").style.display = "none";

		}
		if (billingCountry === "") {
			document.getElementById("billingCountryError").style.display = "inline";
			document.getElementById("billingCountryError").style.color = "red";
			ok = false;
		} else {
			document.getElementById("billingCountryError").style.display = "none";

		}
		if (billingProvince === "") {
			document.getElementById("billingProvinceError").style.display = "inline";
			document.getElementById("billingProvinceError").style.color = "red";
			ok = false;
		} else {
			document.getElementById("billingProvinceError").style.display = "none";

		}
		if (!billingPhone.match(validPhoneNo)) {
			document.getElementById("billingPhoneError").style.display = "inline";
			document.getElementById("billingPhoneError").style.color = "red";
			ok = false;
		} else {
			document.getElementById("billingPhoneError").style.display = "none";

		}
	}

	/*
	 * Validate Email & Password, null when user logged in
	 */

	const password = document.getElementById("password");
	const email = document.getElementById("email");
	if (!password) {
		
		if ((email && password) == null) {
		} else {
	
			if (email.value === " " || (!email.value.match(validEmail))) {
				document.getElementById("emailError").style.display = "inline";
				document.getElementById("emailError").style.color = "red";
				ok = false;
			}
			if (password.value.length <= 6 || password.value === "") {
				document.getElementById("passwordError").style.display = "inline";
				document.getElementById("passwordError").style.color = "red";
				ok = false;
			} else {
				document.getElementById("passwordError").style.display = "none";
				document.getElementById("emailError").style.display = "none";
			}
		}
	}
	

	/*
	 * May use Stripe
	 */

	if (cardholder === "") {
		document.getElementById("cardholderError").style.display = "inline";
		document.getElementById("cardholderError").style.color = "red";
		ok = false;
	} else {
		document.getElementById("cardholderError").style.display = "none";
	}

	if (card.length !== 16 || isNaN(card)) {
		document.getElementById("ccError").style.display = "inline";
		document.getElementById("ccError").style.color = "red";
		ok = false;
	} else {
		document.getElementById("ccError").style.display = "none";
	}

	if ((cvv.length !== 3) || isNaN(cvv)) {
		document.getElementById("cvvError").style.display = "inline";
		document.getElementById("cvvError").style.color = "red";
		ok = false;
	} else {
		document.getElementById("cvvError").style.display = "none";

	}

	return ok;
}
