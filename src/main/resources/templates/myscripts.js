function activateSignupButton(){
    console.log(document.getElementById('termsCheckBox').checked);
    if(document.getElementById("termsCheckBox").checked){
                    console.log("Checkbox is checked.");
        }
        else{
            console.log("Checkbox is unchecked.");
        }
}

function validateLoginForm(){
    console.log('Validating login form')
    let loginForm = document.forms["loginForm"];
    let email = loginForm["email"].value;
    let emailRegx = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    let passwordRegx = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,}$/;
//    console.log(email);
//    console.log(email==="")
    if(email===""){
        document.getElementById("emailValidationMessage").innerHTML = "Email can not be empty";
        return false;
    }
    if(!emailRegx.test(email)){
        document.getElementById("emailValidationMessage").innerHTML = "Email is not valid";
        return false;
    }

    let password = loginForm["inputPasswordField"].value;
//    console.log("password is " + password)
    if(password.length<7){
        document.getElementById("passwordValidationMessage").innerHTML = "Password length must be greater than 7 characters"
        return false;
    }
    if(!passwordRegx.test(password)){
        document.getElementById("passwordValidationMessage").innerHTML = "Password length must contain special character and a number"
        return false;
    }

    if(!document.getElementById("termsCheckBox").checked){
        document.getElementById("termsAcceptMessage").innerHTML = "Please check this to continue"
        return false;
    }
    console.log('Successfully verified login form.');
    return true;
}