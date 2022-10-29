//javascript.js
//set map options
var myLatLng = { lat: 10.8230, lng: 106.6296 };
var mapOptions = {
    center: myLatLng,
    zoom: 8,
    mapTypeId: google.maps.MapTypeId.ROADMAP

};


//create map
var map = new google.maps.Map(document.getElementById('googleMap'), mapOptions);

//create a DirectionsService object to use the route method and get a result for our request
var directionsService = new google.maps.DirectionsService();

//create a DirectionsRenderer object which we will use to display the route
var directionsDisplay = new google.maps.DirectionsRenderer();

//bind the DirectionsRenderer to the map
directionsDisplay.setMap(map);



//define calcRoute function
function calcRoute() {
    //create request
    var i = navigator.geolocation.getCurrentPosition((e) => {
      
        var currPosition = e.coords.latitude + " " + e.coords.longitude

        var request = {
            // origin: document.getElementById("from").value,
            origin: currPosition,
            destination: document.getElementById("to").value,
            travelMode: google.maps.TravelMode.DRIVING, //WALKING, BYCYCLING, TRANSIT
            unitSystem: google.maps.UnitSystem.IMPERIAL
        }
    
        //pass the request to the route method
        directionsService.route(request, function (result, status) {
            if (status == google.maps.DirectionsStatus.OK) {
    
                //Get distance and time
                const output = document.querySelector('#output');
                output.innerHTML = "<div class='alert-info'>" + document.getElementById("from").value + ".<br />Địa chỉ người nhận: " + document.getElementById("to").value + ".<br /> Khoảng cách: <i class='fas fa-road'></i> : " + result.routes[0].legs[0].distance.text + ".<br />Thời gian vận chuyển: <i class='fas fa-hourglass-start'></i> : " + result.routes[0].legs[0].duration.text + ".</div>";
    
                //display route
                directionsDisplay.setDirections(result);
            } else {
                //delete route from map
                directionsDisplay.setDirections({ routes: [] });
                //center map in London
                map.setCenter(myLatLng);
    
                //show error message
                output.innerHTML = "<div class='alert-danger'><i class='fas fa-exclamation-triangle'></i> Không thể xem được khoảng cách.</div>";
            }
        });
    });

   
   

}

// var x = document.getElementById("demo");

// function getLocation() {
//     if (navigator.geolocation) {
//         navigator.geolocation.getCurrentPosition(showPosition);
//     } else {
//         x.innerHTML = "Geolocation is not supported by this browser.";
//     }
// }
// function showPosition(position) {
//     x.innerHTML = position.coords.latitude +
//         "&nbsp;" + position.coords.longitude;
// }
//create autocomplete objects for all inputs
var options = {
    types: ['(cities)']
}

var input1 = document.getElementById("from");
var autocomplete1 = new google.maps.places.Autocomplete(input1, options);

var input2 = document.getElementById("to");
var autocomplete2 = new google.maps.places.Autocomplete(input2, options);
