function login(){
    document.querySelector("#errorLogin").innerHTML ="";

    let username = document.querySelector("#userNameLogin").value;
    let password = document.querySelector("#passwordLogin").value;

    let text = username + ":" + password +";";



    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        console.log(xhttp.readyState);

        if (xhttp.status == XMLHttpRequest.DONE && xhttp.readyState == 4) {

            alert("Done");
            document.querySelector("#beforeLogin").style.visibility = "hidden";
            document.querySelector("#beforeLogin").style.visibility = "visible";
            document.querySelector("#loggedName").innerHTML = username;
        }else{
            document.querySelector("#errorLogin").innerHTML += "ERROR: Anmeldeinformationen sind falsch <br/>";
        }
    }
    xhttp.open("POST", "rateme/user/login/"+text, true);
    xhttp.send();
}

function login1(){
    document.querySelector("#errorLogin").innerHTML ="";

    let username = document.querySelector("#userNameLogin").value;
    let password = document.querySelector("#passwordLogin").value;

    let text = username + ":" + password +";";




    fetch('rateme/user/login/',   {
        method: 'post',
        body: text
      })
      .then( response => {
            document.querySelector("#username").value = "";
            document.querySelector("#password").value = "";
            if( !response.ok )
            {
                document.querySelector("#errorLogin").innerHTML = "Benutzerdaten sind fehlerhaft!";
                throw Error(response.statusText);
            }
            else
            {
                alert("Ja")
            }
        } )
     .catch( error => console.error('Error:', error));
}


function logout()
{
	fetch('rateme/user/login',   {
	    method: 'delete'
	  })
	  .then( response => {
		  if( response.ok )
	      {
			 // changeVisibility();
			 alert("Logout")
		  }
	  } )
	  .catch( error => console.error('Error:', error) );
}
