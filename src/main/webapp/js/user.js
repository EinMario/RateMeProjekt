
let loggedIn = false;
function changeLoginStatus(name){
    if(loggedIn){
        document.querySelector("#loginStatus").innerHTML = '<span style="white-space: nowrap"> <div id="loggedName" class="label">'+ name + '</div> </span>' + '<span style="float: right; white-space: nowrap"><button type="button"  onclick="logout()">Logout</button> </span>';
    }else{
        document.querySelector("#loginStatus").innerHTML = '<span style="white-space: nowrap"> Benutzer: <input id="userNameLogin" type="text" name="userName" required /></span> <span style="white-space: nowrap"> Password: <input id="passwordLogin" type="password" name="password" required /></span> <button type="button" style="width: 100px;" onclick="login()">Anmelden</button>'+
                                                            '<span style="float: right; white-space: nowrap"> <a href="javascript:showRegistration()"> Als neuer Benutzer registrieren</a></span>';
    }
}

function login(){

    let username = document.querySelector("#userNameLogin").value;
    let password = document.querySelector("#passwordLogin").value;

    let usernamePassword = username + ":" + password +";";

    fetch('rateme/user/login/'+usernamePassword,   {
        method: 'post',
        body: text
      })
      .then( response => {

            if( !response.ok )
            {
                document.querySelector("#passwordLogin").value = "";
                alert("Benutzerdaten sind fehlerhaft!");
                throw Error(response.statusText);
            }
            else
            {
                document.querySelector("#userNameLogin").value = "";
                document.querySelector("#passwordLogin").value = "";
                loggedIn = true;
                changeLoginStatus(username);
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
			 loggedIn = false;
             changeLoginStatus("");
		  }
	  } )
	  .catch( error => console.error('Error:', error) );
}
