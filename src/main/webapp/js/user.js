
var loggedIn = false;
var loggedInUser;
/*
window.onload = function()
{
    var loginState= sessionStorage.getItem('login');
    if(loginState){
        document.querySelector("#ownRatings").innerHTML = '<h3> Meine Bewertungen: </h3> <table>';
        document.querySelector("#loginStatus").innerHTML = '<span style="white-space: nowrap"> '+ name + ' </span>' + '<span style="float: right; white-space: nowrap"><button type="button"  onclick="logout()">Logout</button> </span>';
        document.querySelector("#createRating").innerHTML = ' <h3>Bewertung abgeben:</h3>   <table> <tr> <td>Bewertung: <td><fieldset class="rating" id="rating"> <legend> </legend>    <input type="radio" id="star5" name="rating" value="5" /><label for="star5" title="Rocks!">5 stars</label> <input type="radio" id="star4" name="rating" value="4" /><label for="star4" title="Pretty good">4 stars</label> <input type="radio" id="star3" name="rating" value="3" /><label for="star3" title="Meh">3 stars</label> <input type="radio" id="star2" name="rating" value="2" /><label for="star2" title="Kinda bad">2 stars</label> <input type="radio" id="star1" name="rating" value="1" /><label for="star1" title="Sucks big time">1 star</label></fieldset></td></tr></table>Kommentar:<textarea id="ratingText" name="ratingTxt" style="width: 280px;" required></textarea><p />Bild hochladen: <br /> <input id="file" type="file" name="file" /><p /><button type="button" onclick="javascript:rate()">Absenden</button>';
    }else if(!loginState){
        document.querySelector("#loginStatus").innerHTML = '<span style="white-space: nowrap"> Benutzer: <input id="userNameLogin" type="text" name="userName" required /></span> <span style="white-space: nowrap"> Password: <input id="passwordLogin" type="password" name="password" required /></span> <button type="button" style="width: 100px;" onclick="login()">Anmelden</button>'+
                                                            '<span style="float: right; white-space: nowrap"> <a href="javascript:showRegistration()"> Als neuer Benutzer registrieren</a></span>';
        document.querySelector("#createRating").innerHTML = '';
        document.querySelector("#ownRatings").innerHTML = '';
    }else{
    }
}
*/


function changeLoginStatus(name){
    if(loggedIn){
        document.querySelector("#ownRatings").innerHTML = '<h3> Meine Bewertungen: </h3> <table id="ownRatingsTable" style="width : 100%">';
        document.querySelector("#loginStatus").innerHTML = '<span style="white-space: nowrap"> '+ name + ' </span>' + '<span style="float: right; white-space: nowrap"><button type="button"  onclick="logout()">Logout</button> </span>';
        document.querySelector("#createRating").innerHTML = ' <h3>Bewertung abgeben:</h3>   <table> <tr> <td>Bewertung: <td><fieldset class="rating" id="rating"> <legend> </legend>    <input type="radio" id="star5" name="rating" value="5" /><label for="star5" title="Rocks!">5 stars</label> <input type="radio" id="star4" name="rating" value="4" /><label for="star4" title="Pretty good">4 stars</label> <input type="radio" id="star3" name="rating" value="3" /><label for="star3" title="Meh">3 stars</label> <input type="radio" id="star2" name="rating" value="2" /><label for="star2" title="Kinda bad">2 stars</label> <input type="radio" id="star1" name="rating" value="1" /><label for="star1" title="Sucks big time">1 star</label></fieldset></td></tr></table>Kommentar:<textarea id="ratingText" name="ratingTxt" style="width: 280px;" required></textarea><p />Bild hochladen: <br /> <input id="file" type="file" accept="image/*" name="file" onchange="loadFile(event)"/><img id="output" width="100" /><p /><button type="button" onclick="javascript:rate()">Absenden</button>';
    }else{
        document.querySelector("#loginStatus").innerHTML = '<span style="white-space: nowrap"> Benutzer: <input id="userNameLogin" type="text" name="userName" required /></span> <span style="white-space: nowrap"> Password: <input id="passwordLogin" type="password" name="password" required /></span> <button type="button" style="width: 100px;" onclick="login()">Anmelden</button>'+
                                                            '<span style="float: right; white-space: nowrap"> <a href="javascript:showRegistration()"> Als neuer Benutzer registrieren</a></span>';
        document.querySelector("#createRating").innerHTML = '';
        document.querySelector("#ownRatings").innerHTML = '';
    }
}





function login(){

    let username = document.querySelector("#userNameLogin").value;
    let password = document.querySelector("#passwordLogin").value;
        let text = username + ":" + password +";";


    fetch('rateme/user/login/'+text,   {
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
                loggedInUser = username;
                loggedIn = true;

                changeLoginStatus(username);

                fetchUserRatings(loggedInUser);
                document.querySelector("#ownRatings").innerHTML += '</table>'
            }
        } )
     .catch( error => console.error('Error:', error));
}


function fetchUserRatings(user){
    fetch('rateme/rating/'+user,{
        method: 'get'
    }).then(response => response.json())
    .then( data => data.forEach( rating => {
            document.querySelector("#ownRatingsTable").innerHTML += '<tr> <td>'+ rating.osmName + '</td>' + '<td>'+ rating.create + '</td>'+ '<td>'+ rating.txt + '</td>' + '<td>' +rating.grade +'/5 <fieldset class="rating" disabled><legend></legend><input type="radio" value="5" disabled /><label  title="'+rating.ratingType+'"></label>'  + '</td> </tr>'

        })
    ).catch( error => console.error('Error:', error));

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
			 loggedInUser = null;
             changeLoginStatus("");
		  }
	  } )
	  .catch( error => console.error('Error:', error) );
}
