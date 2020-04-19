/**
 * 
 */

function validateReview() {
	var isOk = true;
	
	var firstStar = document.getElementById("oneStar"); 
	var secondStar = document.getElementById("twoStar"); 
	var thirdStar = document.getElementById("threeStar");
	var fourthStar = document.getElementById("fourStar");
	var fifthStar = document.getElementById("fiveStar");
	
	var reviewInputContent = document.getElementById("reviewInputContent");
	
	if (!firstStar.checked && !secondStar.checked && !thirdStar.checked && !fourStar.checked && !fifthStar.checked) {
		isOk = false;
		alert("Please select a rating from 1 to 5.");
	}
	
	if (reviewInputContent.value == "") {
		isOk = false;
		alert("Please type in a review.");
	} 
	
	return isOk;
}