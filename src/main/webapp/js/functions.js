// Global Variable for the map

let mymap;
let redIcon;
let blueIcon;
//Application state
var selectedMarker;

window.onload = function()
{
	mymap = L.map('mapid').setView([ 49.250723, 7.377122 ], 13);

	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?'
	                +'access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpe'
	                +'jY4NXVycTA2emYycXBndHRqcmZ3N3gifQ'
	                +'.rJcFIG214AriISLbB6B5aw',
	   {  maxZoom     : 18,
	      attribution : 'Map data &copy; '
	                     + '<a href="https://www.openstreetmap.org/">OpenStreetMap</a>'
	                     + ' contributors, '
	                     + '<a href="https://creativecommons.org/licenses/by-sa/2.0/">'
	                     + 'CC-BY-SA</a>, '
	                     + 'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',	
	      id         : 'mapbox.streets' 
	   }).addTo(mymap);

	redIcon = new L.Icon({
            iconUrl: './icon/marker-icon-red.png',
            shadowUrl: './icon/marker-shadow.png',
            iconSize : [ 25, 41 ],
            iconAnchor : [ 12, 41 ],
            popupAnchor : [ 1, -34 ],
            shadowSize : [ 41, 41 ]
        });

    blueIcon = new L.Icon({
            iconUrl: './icon/marker-icon-blue.png',
            shadowUrl: './icon/marker-shadow.png',
            iconSize : [ 25, 41 ],
            iconAnchor : [ 12, 41 ],
            popupAnchor : [ 1, -34 ],
            shadowSize : [ 41, 41 ]
        });
    showPoisOnMap();
} 


function showRegistration() {
	document.querySelector("#registration").style.display = "block";
}

function hideRegistration() {
	document.querySelector("#registration").style.display = "none";
}

function showTable() {
	document.querySelector("#table").style.display = "block";
}

function hideTable() {
	document.querySelector("#table").style.display = "none";
}

function decode_utf8(s) {
  return decodeURIComponent(escape(s));
}

function showPoisOnMap()
{
    fetch("rateme/poi")
    .then(response => response.json())
    .then(data => data.forEach( poi => {
        let callback = poiSelectionCallback(poi);
        L.marker([poi.lat, poi.lon], {icon: blueIcon})
        .addTo(mymap) .on('click', callback);
      } )
    ) .catch( error => console.log(error) );
}

function poiSelectionCallback(poi)
{

    return function(event)
    {

        document.querySelector("#side").innerHTML = "<h3 id=\"name\">Hier sind die Infos zur ausgewählten Kneipe</h3>"+
                    "<h4><a href=\"javascript:showTable()\">Infos</a></h4>"+
                    " <br/> <table id=\"table\" style=\"display : none\">";

        if (selectedMarker != null) { selectedMarker.setIcon(blueIcon);}
        selectedMarker = event.target;
        selectedMarker.setIcon(redIcon);


        poi.poiTags.forEach( item => {
            if(item.tag == "name"){
                document.querySelector("#name").innerHTML = item.value;
            }

            document.querySelector("#table").innerHTML += "<tr> <td>" + item.tag + "</td> <td id=\"info\">  " + item.value + "</td> </tr>";
          } );
        document.querySelector("#side").innerHTML += " </table>";
        document.querySelector("#table").innerHTML += "<button id=\"Abbrechen\" onclick=\"hideTable()\">Schließen</button>";
    }
}


