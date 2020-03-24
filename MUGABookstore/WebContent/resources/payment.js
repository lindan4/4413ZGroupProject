/**
 * 
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

