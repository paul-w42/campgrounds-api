/*
* A large portion of this .js file was taken from the in-class project for jokes-api.
* There are two versions of the heavy-lift functions below, one set for
* each calling page.  The reason is that each page requires a different set
* of HTML components, and I did not want to litter this with if-statements.
*
* @author Paul Woods
*/

let campgroundList;         // list of cgs from server
let markersList = [];       // list of map markers
let map;                    // google map
let splitView = false;
let dataTableDiv;
let dataTableTable;
let mapContainer;
let splitViewDiv;
let splitViewAnchor;
let suggestCgAnchor;
const MARKER_TIMEOUT = 3000;
let mapInit = false;

// wait until page loads
window.onload = async function () {

    // TODO: Utilize dynamic library import, explained here:
    // https://developers.google.com/maps/documentation/javascript/load-maps-js-api

    // if prior pass had an issue/error, run again
    if (!mapInit) {
        initMap();
    }

    await fetchCampgrounds();
    jsonToMapMarkers();

    splitViewDiv = document.getElementById("splitView");
    splitViewAnchor = document.getElementById("splitViewAnchor");

    dataTableDiv = document.getElementById("dataTable");
    dataTableTable = document.getElementById("cgTable");
    mapContainer = document.getElementById("map_container");

    suggestCgAnchor = document.getElementById("suggestCgLink");
    suggestCgAnchor.addEventListener('click', showCgSuggestDialog);

    splitViewDiv.addEventListener("click", displaySplitView);

    // Nov '23
    // example w/ function call - https://developers.google.com/maps/documentation/javascript/events
    map.addListener("bounds_changed", getListInView);

};

// async function checkLoaded() {
//     if (!pageLoaded) {
//         console.log("page not loaded, waiting 50ms");
//         setTimeout(checkLoaded, 50); // Pause for 5 seconds (5000 milliseconds)
//     }
// }

/*
 * called from init() function, after google maps have been loaded
 */
// async function instantiateFields() {
//
//     await checkLoaded();
//
//     // call fetchCampgrounds
//     await fetchCampgrounds();
//     jsonToMapMarkers();
//
//     splitViewDiv = document.getElementById("splitView");
//     splitViewAnchor = document.getElementById("splitViewAnchor");
//
//     dataTableDiv = document.getElementById("dataTable");
//     dataTableTable = document.getElementById("cgTable");
//     mapContainer = document.getElementById("map_container");
//
//     suggestCgAnchor = document.getElementById("suggestCgLink");
//     suggestCgAnchor.addEventListener('click', showCgSuggestDialog);
//
//     splitViewDiv.addEventListener("click", displaySplitView);
//
//     // Nov '23
//     // listener example w/ addListener function - https://developers.google.com/maps/documentation/javascript/events
//     map.addListener("bounds_changed", getListInView);
// }

function showCgSuggestDialog() {

    let dialog = document.querySelector("#suggestCgDialog");
    let suggestButton = document.querySelector("#suggestCg");
    let cancelButton = document.querySelector("#cancelSuggest");

    dialog.showModal();

    suggestButton.addEventListener("click", addSuggestedCampground);

    // let campName;
    // if (campground) {
    //     campName = document.getElementById(`f${id}2`).value;
    // } else {
    //     campName = document.getElementById("suggestedCGName").textContent;
    // }
    cancelButton.addEventListener("click", () => {
        dialog.close();
    });
}

async function addSuggestedCampground(event)
{
    event.preventDefault();
    // retrieve values from Form
    let cgName = document.querySelector("input#name").value;
    let url = document.querySelector("input#url").value;
    let latitude = document.querySelector("input#lat").value;
    let longitude = document.querySelector("input#lon").value;
    let tentSites = document.querySelector("input#tentOnly").value;
    let totalSites = document.querySelector("input#totalSites").value;
    let userName = document.querySelector("input#username").value;
    let email = document.querySelector("input#email").value;

    // Build JS Object w/ form data
    let suggCampObj = {
        name: `${cgName}`,
        url: `${url}`,
        latitude: `${latitude}`,
        longitude: `${longitude}`,
        tentOnlySites: `${tentSites}`,
        totalCampsites: `${totalSites}`,
        userName: `${userName}`,
        userEmail: `${email}`
    }

    // Build Request including body
    let uri = "../api/v1/campgrounds/suggested";
    let config = {  // http headers
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(suggCampObj),
    }

    // perform POST, await response
    let response = await fetch(uri, config);

    // convert the body of the response to JSON format
    let json = await response.json();

    // close dialog
    let dialog = document.querySelector("#suggestCgDialog");
    dialog.close();

    // display thank-you message
    let thankDialog = document.querySelector("#thank");
    let thankButton = document.querySelector("#closeThank");
    thankButton.addEventListener("click", () => {
        thankDialog.close();
    })
    thankDialog.showModal();
}

/*
 * Shrink map size to 1/2 width, display table
 */
function displaySplitView() {
    if (splitView === false) {
        splitView = true;
        splitViewAnchor.textContent = "Display Map Only"
        mapContainer.style.width = "50%";
        dataTableDiv.style.display = "block";
        console.log("displaying data table");
        setTimeout(getListInView, 35);
    }
    else {
        splitView = false;
        splitViewAnchor.textContent = "Display Split View"
        dataTableDiv.style.display = "none";
        mapContainer.style.width = "100%";
        console.log("hiding data table");
    }
}

/*
 * remove rows from data table prior to appending new data
 */
function removeTableRows() {
    const headerRow = dataTableDiv.querySelector('tr:first-child');    // get header row
    const rows = dataTableDiv.querySelectorAll('tr:not(:first-child)');    // get all rows except header

    for (const row of rows) {
        row.remove();   // remove each row
    }
}

/*
 * builds list of displayed campgrounds to show in table
 */
function getListInView() {

    removeTableRows();

    // console.log("inside getListInView()");
    const newList = [];
    const bounds = map.getBounds();

    for (let i = 0; i < campgroundList.length; i++) {
        let campground = campgroundList[i];
        if (bounds.contains(new google.maps.LatLng(campground.latitude, campground.longitude))) {
            newList.push(campground);
        }
    }

    // dataTable
    for (let i = 0; i < newList.length; i++) {
        // let tr = document.createElement("tr");
        let tr = dataTableTable.insertRow();
        // assign unique id to this row, attribute name is 'data-id'
        tr.dataset.id = newList[i].id;

        let td1 = document.createElement("td");
        let td2 = document.createElement("td");
        let td3 = document.createElement("td");
        let a1 = document.createElement("a");
        let a2 = document.createElement("a");

        // build & assign anchor to t1 - click event
        a1.text = newList[i].name;
        a1.setAttribute('href', '#');
        a1.style.color = "darkgreen";
        a1.style.fontWeight = "bolder";
        a1.addEventListener('click', handleCgClick);
        a1.addEventListener('mouseover', handleCgHover);
        a1.addEventListener('mouseout', handleCgHover);
        td1.appendChild(a1);

        // build & assign anchor to t2 - url
        a2.textContent = newList[i].url.substring(8,26);
        a2.setAttribute('href', newList[i].url);
        a2.setAttribute('target', 'blank_' + i);
        td2.appendChild(a2);

        if (newList[i].totalCampsites === 0) {
            td3.textContent = "-";
        } else {
            td3.textContent = newList[i].totalCampsites;
        }

        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        dataTableTable.appendChild(tr);
    }
}

/*
 * event handler for mouseover and mouseout events - changes color of icon while mouse is hovering
 * over campground in the table.
 */
function handleCgHover() {
    let row = this.closest('tr');
    let id = row.dataset.id;

    // search through markersList, find cg w/ id
    for (let i = 0; i < markersList.length; i++) {
        let markerObject = markersList[i];
        if (markerObject.id == id) { // does not work w/ ===, diff types
            // Convert to mouse-over & mouse-out handler
            if (markerObject.type === 'tent') {
                if (markerObject.marker.icon.url === "./images/tent_50_alt.png") {
                    markerObject.marker.setIcon({url: "./images/tent_50.png", scaledSize: new google.maps.Size(35,35)});
                } else {
                    markerObject.marker.setIcon({url: "./images/tent_50_alt.png", scaledSize: new google.maps.Size(35,35)});
                }
            } else {
                if (markerObject.marker.icon.url === "./images/camper_50_alt.png") {
                    markerObject.marker.setIcon({url: "./images/camper_50.png", scaledSize: new google.maps.Size(35,35)});
                } else {
                    markerObject.marker.setIcon({url: "./images/camper_50_alt.png", scaledSize: new google.maps.Size(35,35)});
                }
            }
        }
    }
}

/*
 * event handler for clicks on campground name inside table
 */
function handleCgClick() {
    let row = this.closest('tr');
    let id = row.dataset.id;

    // search through markersList, find cg w/ id
    for (let i = 0; i < markersList.length; i++) {
        let markerObject = markersList[i];
        if (markerObject.id == id) { // does not work w/ ===, diff types
            markerObject.marker.setAnimation(google.maps.Animation.BOUNCE);
            // WORKS FOR MOUSE-OVER W/ TIMEOUT
            if (markerObject.type === 'tent') {
                markerObject.marker.setIcon({url: "./images/tent_50_alt.png", scaledSize: new google.maps.Size(35,35)});
            } else {
                markerObject.marker.setIcon({url: "./images/camper_50_alt.png", scaledSize: new google.maps.Size(35,35)});
            }

            // reset to original after timeout
            setTimeout(() => {
                markerObject.marker.setAnimation(null);
                if (markerObject.type === 'tent') {
                    markerObject.marker.setIcon({url: "./images/tent_50.png", scaledSize: new google.maps.Size(35,35)});
                } else {
                    markerObject.marker.setIcon({url: "./images/camper_50.png", scaledSize: new google.maps.Size(35,35)});
                }
            }, MARKER_TIMEOUT);
        }
    }
}

/*
 * Converts (high-level) json list of campgrounds into markers on maps
 */
function jsonToMapMarkers() {

    // loop over array, add one item at a time
    for (let i = 0; i < campgroundList.length; i++)
    {
        let campground = campgroundList[i];
        let type;

        if ((campground.tentOnlySites === campground.totalCampsites) && campground.totalCampsites > 0) {
            type = "tent";
        } else {
            type = "all";
        }

        displayMarker(type, campground.latitude, campground.longitude, campground.name, campground.id);
    }
}


/*
 * A callback function used by Google Maps
 * https://developers.google.com/maps/documentation/javascript/custom-markers
 */
async function initMap() {
    if (!mapInit) {
        const centerWash = {lat: 47.38664, lng: -120.52386};
        // const rvPosition = {lat: 47.4, lng: -120.65};

        const { Map } = await google.maps.importLibrary("maps");

        map = new Map(document.getElementById("map_container"), {
            center: centerWash,
            zoom: 8,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        });

        mapInit = true;
    }
}

/*
 * creates a display marker everytime called for passed in campground
 */
function displayMarker(type, lat, lon, name, cgId) {
    // let cgId = 42;
    const position = {lat: lat, lng: lon};
    let icon;
    let title;

    if (type === 'tent') {
        icon = {
            url: "./images/tent_50.png",
            size: new google.maps.Size(40,40),
            origin: new google.maps.Point(0,0),
            anchor: new google.maps.Point(20,20),
            scaledSize: new google.maps.Size(35,35)
        };
        title = name;
    }
    else {
        icon = {
            url: "./images/camper_50.png",
            size: new google.maps.Size(40,40),
            origin: new google.maps.Point(0,0),
            anchor: new google.maps.Point(20,20),
            scaledSize: new google.maps.Size(35,35)
        };
        title = name;
    }

    let marker = new google.maps.Marker({
        map: map,
        position: position,
        title: title,
        icon: icon,
    });

    let markerObject = {id: cgId, type: type, marker: marker};

    markersList.push(markerObject);
}

/*
 * Fetches campgrounds, stores in a list of Campground objects
 */
async function fetchCampgrounds()     // async required for await keyword below
{
    let uri = "../api/v1/campgrounds";
    let config = {  // http headers
        method: "get"
    }

    // load campgrounds from database
    let response = await fetch(uri, config);

    // convert the body of the response to JSON format
    campgroundList = await response.json();

    // console.log("fetchCampgrounds() complete");
}

