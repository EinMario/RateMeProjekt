var base64Image = null;
function rate() {
    let star5 = document.querySelector("#star5");
    let star4 = document.querySelector("#star4");
    let star3 = document.querySelector("#star3");
    let star2 = document.querySelector("#star2");
    let star1 = document.querySelector("#star1");

    let givenStars = 0;

    if(star5.checked) givenStars = 5;
    if(star4.checked) givenStars = 4;
    if(star3.checked) givenStars = 3;
    if(star2.checked) givenStars = 2;
    if(star1.checked) givenStars = 1;

    if(givenStars == 0) return;

    let comment = document.querySelector("#ratingText").value;


    if(selectedMarker== null) return;

    console.log(base64Image);
    let data = {    user : loggedInUser,
                    txt : comment,
                    stars : givenStars,
                    pic : base64Image
    }



   fetch('rateme/rating/'+JSON.stringify(selectedMarker._latlng), {
                method: 'post',
                headers: {'Content-Type': 'application/json'},
                body:JSON.stringify(data)
                }).then( response => {
                                   if(response.ok){
                                        fetchUserRatings(loggedInUser);
                                        getRatingsForPoi(JSON.stringify(selectedMarker._latlng));
                                        document.querySelector("#star5").checked =false;
                                        document.querySelector("#star4").checked =false;
                                        document.querySelector("#star3").checked =false;
                                        document.querySelector("#star2").checked =false;
                                        document.querySelector("#star1").checked =false;
                                        document.querySelector("#ratingText").value="";
                                        document.querySelector("#output") = null;
                                    }
                              } )
               .catch( error => console.error('Error:', error));
}

var loadFile = function(event) {
	var image = document.getElementById('output');
	image.src = URL.createObjectURL(event.target.files[0]);
	var can = document.getElementById('canvas');
    var ctx = can.getContext("2d");
    ctx.drawImage(image, 10, 10);


	toDataURL(image.src, function(dataUrl) {
      base64Image = dataUrl;
    });
};
function toDataURL(url, callback) {
  var xhr = new XMLHttpRequest();
  xhr.onload = function() {
    var reader = new FileReader();
    reader.onloadend = function() {
      callback(reader.result);
    }
    reader.readAsDataURL(xhr.response);
  };
  xhr.open('GET', url);
  xhr.responseType = 'blob';
  xhr.send();
}

function getRatingsForPoi(pos){
        document.querySelector("#ratings").innerHTML ="";
        fetch("rateme/rating/poi/"+ pos)
        .then(response => response.json())
        .then(ratings => ratings.forEach( rating => {
            if(rating != null){
                if(rating.imagePath && rating.imagePath !=="null"){
                    var newImage = document.createElement('img');
                    newImage.src = rating.imagePath;
                    newImage.width = newImage.height = "80";
                    document.querySelector("#ratings").innerHTML +="<div style=\"display:block; border: 3px solid #2c3d03; background: #ba9d00;max-width: 100%;\">" + rating.creatorName +" gab "+ rating.grade+" Sterne und schrieb: <br><p>" +newImage.outerHTML+ rating.txt + "</p> Datum:"+ rating.create +"</div> <br>";
                }else{
                    document.querySelector("#ratings").innerHTML +="<div style=\"display:block; border: 3px solid #2c3d03; background: #ba9d00;max-width: 100%;\">" + rating.creatorName +" gab "+ rating.grade+" Sterne und schrieb: <br><p>"+rating.txt + "</p> Datum:"+ rating.create +"</div> <br>";
                }
            }
          } )
        ) .catch( error => console.log(error) );
}

