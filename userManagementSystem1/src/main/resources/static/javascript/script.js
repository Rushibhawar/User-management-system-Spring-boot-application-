function validateUserForm() {
  var userFields = ["userFirstName", "userLastName", "userDateOfBirth", "userEmail", "userPassword","userMobileNumber"];
  var permanentAddressFields = ["userPermanentAddressStreet", "userPermanentAddressLine1", "userPermanentAddressLine2", "userPermanentAddressCity", "userPermanentAddressState", "userPermanentAddressPincode"];
  /*var correspondenceAddressFields = ["userCorrespondenceAddressStreet", "userCorrespondenceAddressLine1", "userCorrespondenceAddressLine2", "userCorrespondenceAddressCity", "userCorrespondenceAddressState", "userCorrespondenceAddressPincode"];*/

  var isFormValid = true;
  
  userFields.forEach(function(field) {
    if (!document.getElementById(field).value.trim()) {
		nextElementsDisplay(field);
      isFormValid = false;
    }
  });

  permanentAddressFields.forEach(function(field) {
    if (!document.getElementById(field).value.trim()) {
		nextElementsDisplay(field);
      isFormValid = false;
    }
  });

 /* correspondenceAddressFields.forEach(function(field) {
    if (!document.getElementById(field).value.trim()) {
		nextElementsDisplay(field);
      isFormValid = false;
    }
  });*/

  if (!isFormValid) {
    var errorMsg = document.getElementById("errorMsg");
    errorMsg.style.display = "block";
    document.documentElement.scrollTop = 0;
    
    // now set timeout for the error message appeared
    setTimeout(function(){
    	errorMsg.style.display = "none";
	}, 5000);
	
    return false;
  }

  return true;
}

//function to show error message
function nextElementsDisplay(field){
	var nextElement = document.getElementById(field);
	nextElement.nextElementSibling.style.display = "block";
	
	document.getElementById(field).addEventListener("focus", () => {
		nextElement.nextElementSibling.style.display = "none";
	});
	
}

//function to validate first and last name
function validateNameInput(input) {
	!(/[^a-zA-Z]/g.test(input.value)) ? input.nextElementSibling.style.display = "none" : input.nextElementSibling.style.display = "block";
}

//function to validate email
function validateEmail(input) {
  	const emailRegex = /^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}(\.[a-zA-Z]{2,6})?)$/;
	emailRegex.test(input.value) ? (input.nextElementSibling.style.display = "none") : input.nextElementSibling.style.display = "block"
}

//function to validate password
function validatePassword(input) {
	(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/.test(input.value)) ? input.nextElementSibling.style.display = "none" : input.nextElementSibling.style.display = "block";
}

//function to validate mobile number
function validateMobileNumber(input) {
	(/^\d{10}$/.test(input.value)) ? input.nextElementSibling.style.display = "none" : input.nextElementSibling.style.display = "block";
}

//function to validate address street
function validateStreet(input) {
	(/^[a-zA-Z0-9\s#,'-/:]+$/i.test(input.value)) ? input.nextElementSibling.style.display = "none" : input.nextElementSibling.style.display = "block";
}

//function to validate address line
function validateLine(input) {
	(/^(?=.*[a-zA-Z0-9\s#])[\sa-zA-Z0-9.#,'-/:]+$/i.test(input.value)) ? input.nextElementSibling.style.display = "none" : input.nextElementSibling.style.display = "block";
}

//function to validate city and state
function validateCityState(input) {
	(/^[A-Z\s][a-z\s]*$/.test(input.value)) ? input.nextElementSibling.style.display = "none" : input.nextElementSibling.style.display = "block";
}

//function to validate pincode
function validatePincode(input) {
	(/^\d{6}$/.test(input.value)) ? input.nextElementSibling.style.display = "none" : input.nextElementSibling.style.display = "block";
}

//function to send the page size and view user list
function updateSelectedOptionIntoATag() {
    var selectedSize = document.getElementById("sizeDropdown").value;
    var url = "/home?selectedSize=" + selectedSize;
    
    //for debugging
    console.log("url : "+url + "and sleected Option : "+selectedSize)
    
    var link = document.getElementById("viewListAsPerSizeLink");
    
    //now set the href of achor tag with id viewListAsPerSizeLink with the above link
    link.href = url;
    
    // this click() function clicks on the hidden link to send the size in the URL to the server
    link.click();
}


// function to show and hide password
function showPassword(){
	var showPasswordCheckbox = document.getElementById("showPasswordCheckbox");
    var userPassword = document.getElementById("userPassword");

    showPasswordCheckbox.checked ? userPassword.type = 'text' : userPassword.type = 'password';
}


/*function correspondenceSAddressameAsPermanent(){
	var permanentAddressFieldsIds = ["userPermanentAddressStreet", "userPermanentAddressLine1", "userPermanentAddressLine2", "userPermanentAddressCity", "userPermanentAddressState", "userPermanentAddressPincode"];
  	var correspondenceAddressFieldsIds = ["userCorrespondenceAddressStreet", "userCorrespondenceAddressLine1", "userCorrespondenceAddressLine2", "userCorrespondenceAddressCity", "userCorrespondenceAddressState", "userCorrespondenceAddressPincode"];

	var correspondeceSameAsPermanentCheckbox = document.getElementById("correspondeceSameAsPermanentCheckbox");

    if(correspondeceSameAsPermanentCheckbox.checked){
        permanentAddressFieldsIds.forEach(function(feild,index){
            document.getElementById(correspondenceAddressFieldsIds[index]).value = document.getElementById(feild).value; 
        });
    }
    else{
        permanentAddressFieldsIds.forEach(function(feild,index){
			document.getElementById(correspondenceAddressFieldsIds[index]).value = "";
			console.log("remove for each after");            
        });
    }
}*/
function correspondenceSAddressameAsPermanent(){
	var permanentAddressFieldsIds = document.querySelectorAll("#userPermanentAddressStreet, #userPermanentAddressLine1, #userPermanentAddressLine2, #userPermanentAddressCity, #userPermanentAddressState, #userPermanentAddressPincode");
    
  	var correspondenceAddressFieldsIds = document.querySelectorAll("#userCorrespondenceAddressStreet, #userCorrespondenceAddressLine1, #userCorrespondenceAddressLine2, #userCorrespondenceAddressCity, #userCorrespondenceAddressState, #userCorrespondenceAddressPincode");

	var correspondeceSameAsPermanentCheckbox = document.getElementById("correspondeceSameAsPermanentCheckbox");

    if(correspondeceSameAsPermanentCheckbox.checked){

        permanentAddressFieldsIds.forEach(function(field,index){
            correspondenceAddressFieldsIds[index].value = field.value;
        });
    }
    else{
        permanentAddressFieldsIds.forEach(function(field,index){
            correspondenceAddressFieldsIds[index].value = "";
			console.log("remove for each after");            
        });
    }
}




