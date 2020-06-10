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

    let image = document.querySelector("#file").files[0];
    let sendImage = null;
    if(image != null){
        sendImage = btoa(image);
    }

/*    console.log(sendImage);
    document.querySelector("#ratings").appendChild(image);

    var pic = new Image();
    pic.src = 'data:image/png;base64,'+sendImage;
    document.querySelector("#ratings").appendChild(pic);

    var src = "data:image/jpeg;base64,";
    src += sendImage;
    var newImage = document.createElement('img');
    newImage.src = src;
    newImage.width = newImage.height = "80";
    document.querySelector("#ratings").appendChild(newImage);*/

    if(selectedMarker== null) return;

    fetch('rateme/rating/'+ givenStars + '/' + comment+ '/' + sendImage+ '/' + loggedInUser+ '/' + JSON.stringify(selectedMarker._latlng),   {
            method: 'post'
         })
         .then( response => {
                //console.log(response);
           } )
        .catch( error => console.error('Error:', error));
}