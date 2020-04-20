function decreaseQuantity(qtId) {
	var count = document.getElementById(qtId).value;
	if (count > 0) {
		count = parseInt(count) - 1;
		document.getElementById(qtId).value = count;
	}
	
	
	
}


function increaseQuantity(qtId) {
	var count = document.getElementById(qtId).value;
	if (count >= 0) {
		count = parseInt(count) + 1;
		document.getElementById(qtId).value = count;
	}
	
	
}