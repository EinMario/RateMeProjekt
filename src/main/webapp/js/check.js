var nameOk = false;
var emailOk = false;
var lnameOk = false;
var plzOK = false;
var passwordOk = false;

function checkIfAllOk()
{
	let allOk = nameOk && emailOk && lnameOk && plzOK && passwordOk;
	if( allOk )
	{
		document.querySelector("#regist").disabled=false;
	}
	else
    {
		document.querySelector("#regist").disabled=true;
    }
}
function checkNameOnFocus()
{
	document.querySelector("#errorName").innerHTML = "";
}

function checkFNameOnBlur()
{
	if( document.querySelector("#firstname").value.length == 0 )
		return;

	if( nameOk == false )
	{
	   document.querySelector("#errorName").innerHTML = "Format des Namens ist falsch";
	}

}

function checkLNameOnBlur()
{
	if( document.querySelector("#lastname").value.length == 0 )
		return;

	if( lnameOk == false )
	{
	   document.querySelector("#errorName").innerHTML = "Format des Namens ist falsch";
	}
}

function checkName()
{
	let lnameInput = document.querySelector("#firstname").value;
	let fnameInput = document.querySelector("#lastname").value;
	if( fnameInput.length === 0 || lnameInput.length === 0)
	   return;

	let nameRegEx = /^[A-ZÄÖÜ][a-zäöüß]+(-[A-ZÄÖÜ][a-zäöüß]+)*$/;
	if( nameRegEx.test( lnameInput ) == false || nameRegEx.test( fnameInput ) == false)
	{
		nameOk = false;
		lnameOk = false;
	}
	else
	{
		nameOk = true;
		lnameOk = true;
	}
	checkIfAllOk();
}

// Email
function checkEmailOnFocus()
{
	document.querySelector("#errorEmail").innerHTML = "";
}

function checkEmailOnBlur()
{
	if( document.querySelector("#email").value.length == 0 )
		return;

	if( emailOk == false )
	{
	  document.querySelector("#errorEmail").innerHTML = "Format der E-Mail-Adresse ist falsch";
	}
}

function checkEmail()
{
	emailOk = false;

	let emailInput = document.querySelector("#email").value;
	if( emailInput.length === 0 )
		return;

	let emailRegEx = /^\w{4}\d{4,}@stud\.(hs-kl|fh-kl)\.de$/;
	if( emailRegEx.test( emailInput ) == false )
	{
		emailOk = false;
	}
	else
	{
		emailOk = true;
	}

	checkIfAllOk();
}

//Email End

//Zip
function checkZipOnFocus()
{
	document.querySelector("#errorZip").innerHTML = "";
}

function checkZipOnBlur()
{
	if( document.querySelector("#zip").value.length == 0 )
		return;

	if( plzOK == false )
	{
	  document.querySelector("#errorZip").innerHTML = "Eine Postleitzahl hat 5 Stellen und sollte als Korrekt empfunden werden";
	}
}

function checkZip()
{
	plzOK = false;

    let value = document.querySelector('#zip').value;


    if(value.length != 5 )
        return;

    plzOK = true;
    var length = -1;

    var result =fetch("rateme/zip/"+value)
     .then(response => response.json())
     .then(data => length = data.length)
     .then(data => length = data)
      .catch( error => console.log(error));

    console.log(length);
    console.log(result.typeof);

    if(length >= 1){
    console.log(True)}

	checkIfAllOk();
}

//Zip end


// Passwort
function checkZipOnFocus()
{
	document.querySelector("#errorZip").innerHTML = "";
}

function checkZipOnBlur()
{
	if( document.querySelector("#zip").value.length == 0 )
		return;

	if( passwordOk == false )
	{
	  document.querySelector("#errorZip").innerHTML = "Eine Postleitzahl hat 5 Stellen und sollte als Korrekt empfunden werden";
	}
}

function checkPassword(){
    let password = document.querySelector("#password").value;

    let length = password.length;
    let regExHasNumber = /\d/;
    let regExHasSpecialSign = /[!§$&?]/;
    let regExHasBothCases = /(?=[a-z]+[A-Z]+|[A-Z]+[a-z]+)[a-zA-Z]/;

    let minLength = (length >= 5);
    let greaterLength = (length >= 7);
    let number = regExHasNumber.test(password);
    let specialSign = regExHasSpecialSign.test(password);
    let bothCases = regExHasBothCases.test(password);

    passwordOk = false;
    document.querySelector("#outputPW").innerHTML = "Unsicheres Passwort";

    if(minLength && greaterLength && number && specialSign && bothCases)
    {
        document.querySelector("#outputPW").innerHTML = "Sehr sicheres Passwor";
        passwordOk = true;
    }
    else if( minLength && greaterLength && number && specialSign)
    {
        document.querySelector("#outputPW").innerHTML = "Sicher Passwort";
        passwordOk = true;
    }
    else if( minLength && greaterLength )
    {
        document.querySelector("#outputPW").innerHTML = "Mittel sicheres Passwort";
        passwordOk = true;
    }
    else if( minLength )
    {
        document.querySelector("#outputPW").innerHTML = "Akzeptables Passwort";
        passwordOk = true;
    }


    let outputSize = 0;

    if (minLength)
        outputSize += 4;
    if (number)
        outputSize += 4;
    if (specialSign)
        outputSize += 4;

    let c = document.querySelector("#pwdCanvas");
    let ctx = c.getContext("2d");

    ctx.fillStyle = "red";
    ctx.fillRect(0, 0, 80, 10);

    let grd = ctx.createLinearGradient(0, 0, outputSize * 20, 0);
    grd.addColorStop(0, "green");
    grd.addColorStop(1, "red");

    ctx.fillStyle = grd;
    ctx.fillRect(0, 0, 80, 10);

    checkIfAllOk;
}
