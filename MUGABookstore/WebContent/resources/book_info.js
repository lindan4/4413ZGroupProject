/**
 * 
 */

function validateReview() {
	let isOk = true;
	
	const firstStar = document.getElementById("oneStar");
	const secondStar = document.getElementById("twoStar");
	const thirdStar = document.getElementById("threeStar");
	const fourthStar = document.getElementById("fourStar");
	const fifthStar = document.getElementById("fiveStar");
	
	const reviewInputContent = document.getElementById("reviewInputContent");
	
	if (!firstStar.checked && !secondStar.checked && !thirdStar.checked && !fourthStar.checked && !fifthStar.checked) {
		isOk = false;
		alert("Please select a rating from 1 to 5.");
	}
	
	if (reviewInputContent.value === "") {
		isOk = false;
		alert("Please type in a review.");
	} 
	
	return isOk;
}