/*
 * A large portion of this .js file was taken from the in-class project for jokes-api.
 * There are two versions of the heavy-lift functions below, one set for
 * each calling page.  The reason is that each page requires a different set
 * of HTML components, and I did not want to litter this with if-statements.
 *
 * @author Paul Woods
 */

// wait until page loads
window.onload = async function () {
    // call fetchCampgrounds
    await fetchCampgrounds();

    // add Event listener to AddCampgroundButton
    let addCampgroundButton = document.querySelector("#addCampgroundButton");
    addCampgroundButton.addEventListener("click", addCampground);

    // add Event listener to display/hide addCampgroundForm
    let addCampgroundLink = document.querySelector("#addCampgroundLink");
    addCampgroundLink.addEventListener("click", displayHideCampgroundForm);
    //
    let viewSuggestedCGsLink = document.querySelector("#viewSuggestedAnchor");
    viewSuggestedCGsLink.addEventListener("click", viewSuggestedCGs);

    // console.log("onload() complete");
};

function displayHideCampgroundForm()
{
    //console.log("displayAddCampground() called");
    // span.style.display
    let addCampgroundForm = document.querySelector("#form");
    if (addCampgroundForm.style.display === 'grid') {
        addCampgroundForm.style.display = 'none';
    } else {
        addCampgroundForm.style.display = 'grid';
    }
}

// Add a new Campground record to the database + list
async function addCampground(event)
{
    event.preventDefault();
    // retrieve values from Form
    let name = document.querySelector("input#name").value;
    let numCampsites = document.querySelector("input#campsites").value;
    let latitude = document.querySelector("input#lat").value;
    let longitude = document.querySelector("input#lon").value;
    let url = document.querySelector("input#url").value;

    // Build JS Object w/ form data
    let campObj = {
        name: `${name}`,
        url: `${url}`,
        latitude: `${latitude}`,
        longitude: `${longitude}`,
        tentOnlySites: `0`,
        totalCampsites: `${numCampsites}`
    }

    // Build Request including body
    let uri = "../api/v1/campgrounds";
    let config = {  // http headers
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(campObj),
    }

    // perform POST, await response
    let response = await fetch(uri, config);

    // convert the body of the response to JSON format
    let json = await response.json();

    // query table/container
    let section = document.querySelector("#container");
    addCampgroundRow(json, section);

    // hide form
    displayHideCampgroundForm();
    clearInputValues();
}

function clearInputValues()
{
    document.querySelector("input#name").value = "";
    document.querySelector("input#campsites").value = "";
    document.querySelector("input#lat").value = "";
    document.querySelector("input#lon").value = "";
    document.querySelector("input#url").value = "";
}

async function fetchCampgrounds()     // async required for await keyword below
{
    let uri = "../api/v1/campgrounds";
    let config = {  // http headers
        method: "get"
    }

    let response = await fetch(uri, config);

    // convert the body of the response to JSON format
    let json = await response.json();

    addItemsTable(json);

    // console.log("fetchCampgrounds() complete");
}

// Add Records to table
function addItemsTable(campgroundsArray)
{
    let section = document.querySelector("#container");

    // loop over array, add one item at a time
    for (let i = 0; i < campgroundsArray.length; i++)
    {
        let campground = campgroundsArray[i];
        addCampgroundRow(campground, section);
    }
}

// helper function for addCampgroundRow that appends two spans to td
function buildColumnChildren(child1, child2)
{
    let td = document.createElement("td");
    td.appendChild(child1);
    td.appendChild(child2);
    return td;
}

/*
 * builds row/columns to display tabular data
 * spanX1 is normal view/display of data
 * spanX2 is edit view/display for data
 */
function addCampgroundRow(campground, section)
{
    // create HTML elements
    let tr = document.createElement("tr")

    // IDs
    let td1 = document.createElement("td");

    // Campground Name
    let span21 = document.createElement("span");
    let span22 = document.createElement("span");
    span21.id = `d${campground.id}2`;
    span22.id = `e${campground.id}2`;
    span22.style.display = "none";
    let td2 = buildColumnChildren(span21, span22);

    // Num Campsites
    let span31 = document.createElement("span");
    let span32 = document.createElement("span");
    span31.id = `d${campground.id}3`;
    span32.id = `e${campground.id}3`;
    span32.style.display = "none";
    let td3 = buildColumnChildren(span31, span32);

    // Latitude
    let span41 = document.createElement("span");
    let span42 = document.createElement("span");
    span41.id = `d${campground.id}4`;
    span42.id = `e${campground.id}4`;
    span42.style.display = "none";
    let td4 = buildColumnChildren(span41, span42);

    // Longitude
    let span51 = document.createElement("span");
    let span52 = document.createElement("span");
    span51.id = `d${campground.id}5`;
    span52.id = `e${campground.id}5`;
    span52.style.display = "none";
    let td5 = buildColumnChildren(span51, span52);

    // URL
    let span61 = document.createElement("span");
    let span62 = document.createElement("span");
    let a6= document.createElement("a");
    span61.id = `d${campground.id}6`;   // d - data
    span62.id = `e${campground.id}6`;   // e - edit
    span62.style.display = "none";
    let td6 = buildColumnChildren(span61, span62);

    // Edit
    let span71 = document.createElement("span");
    let span72 = document.createElement("span");
    let a7Edit = document.createElement("a");
    let a7Save = document.createElement("a");
    let a8Cancel = document.createElement("a");
    span71.id = `d${campground.id}7`;
    span72.id = `e${campground.id}7`;
    span72.style.display = "none";
    let td7 = buildColumnChildren(span71, span72);

    // Delete
    let span81 = document.createElement("span");
    let span82 = document.createElement("span");
    let a8Delete = document.createElement("a");
    span81.id = `d${campground.id}8`;
    span82.id = `e${campground.id}8`;
    span82.style.display = "none";
    let td8 = buildColumnChildren(span81, span82);

    // assign unique id to this row, attribute name is 'data-id'
    tr.dataset.id = campground.id;

    // connect elements to child / insertBefore(), appendChild(), ...
    tr.appendChild(td1);    // id
    tr.appendChild(td2);    // name
    tr.appendChild(td3);    // total campsites
    tr.appendChild(td4);    // lat
    tr.appendChild(td5);    // long
    tr.appendChild(td6);    // url
    tr.appendChild(td7);    // edit
    tr.appendChild(td8);    // delete

    // add text or HTML attributes
    td1.textContent = campground.id;

    // name
    span21.textContent = campground.name;
    let edit22 = document.createElement("input");
    edit22.setAttribute("type", "text");
    edit22.setAttribute("name", "name");
    edit22.setAttribute("value", campground.name);
    edit22.id = `f${campground.id}2`;
    span22.appendChild(edit22);

    // campsites
    span31.textContent = campground.totalCampsites;
    let edit32 = document.createElement("input");
    edit32.setAttribute("type", "text");
    edit32.setAttribute("name", "totalCampsites");
    edit32.setAttribute("value", campground.totalCampsites);
    edit32.id = `f${campground.id}3`;
    edit32.style.width = "5em";
    span32.appendChild(edit32);

    // latitude
    span41.textContent = campground.latitude;
    let edit42 = document.createElement("input");
    edit42.setAttribute("type", "text");
    edit42.setAttribute("name", "latitude");
    edit42.setAttribute("value", campground.latitude);
    edit42.id = `f${campground.id}4`;
    edit42.style.width = "6em";
    span42.appendChild(edit42);

    // longitude
    span51.textContent = campground.longitude;
    let edit52 = document.createElement("input");
    edit52.setAttribute("type", "text");
    edit52.setAttribute("name", "longitude");
    edit52.setAttribute("value", campground.longitude);
    edit52.id = `f${campground.id}5`;
    edit52.style.width = "6em";
    span52.appendChild(edit52);

    // build anchor for campground url
    a6.setAttribute('href', campground.url);
    a6.setAttribute('target', "blank");
    a6.textContent = campground.url;
    span61.appendChild(a6);
    let edit62 = document.createElement("input");
    edit62.setAttribute("type", "url");
    edit62.setAttribute("name", "url");
    edit62.setAttribute("value", campground.url);
    edit62.id = `f${campground.id}6`;
    edit62.style.width = "25em";
    span62.appendChild(edit62);

    // build anchor for edit
    a7Edit.textContent = 'Edit';
    a7Edit.setAttribute('href', '#');
    a7Edit.addEventListener("click", showEditCampground);
    a7Save.text = 'Save';
    a7Save.setAttribute('href', '#');
    a7Save.addEventListener("click", saveEditCampground);
    a7Save.style.fontWeight = 'bold';

    span71.appendChild(a7Edit);
    span72.appendChild(a7Save);

    // build anchor for delete
    a8Delete.textContent = 'Delete';
    a8Delete.setAttribute('href', '#');
    // a8Delete.addEventListener("click", showDeleteModal);
    a8Delete.addEventListener("click", (event) => {
        showDeleteModal(event, true, a8Delete);
    });

    // build anchor for Cancel (edit op)
    a8Cancel.text = 'Cancel';
    a8Cancel.setAttribute('href', '#');
    a8Cancel.addEventListener("click", cancelEdit);
    a8Cancel.style.fontWeight = 'bold';

    span81.appendChild(a8Delete);
    span82.appendChild(a8Cancel);

    // add div to the section
    section.appendChild(tr);
}

/*
 * Called when user chooses to edit a Campground entry, hides normal row display,
 * shows edit fields
 * STATUS: DONE
 */
function showEditCampground(event) {
    event.preventDefault();
    let row = this.closest('tr');
    let id = row.dataset.id;
    //console.log('editCampgroundClick: ' + id);

    // Hide display-only portion of this record, Display edit portion
    for (let i = 2; i < 9; i++) {
        //console.log(`looking for span: "#d${i}${id}"`);
        let span = document.querySelector(`#d${id}${i}`);
        span.style.display = "none";
        span = document.querySelector(`#e${id}${i}`);
        span.style.display = "inline";
    }
}

/*
 * Called when the user chooses to save an edit.
 * STATUS: DONE
 */
async function saveEditCampground(event) {
    event.preventDefault();
    let row = this.closest('tr');
    let id = row.dataset.id;

    // f23 = f is form, 2 is id of 2nd item, 3 is 3rd field (i.e. campsites)
    let campObj = {
        id: id,
        name: document.getElementById(`f${id}2`).value,
        url: document.getElementById(`f${id}6`).value,
        latitude: document.getElementById(`f${id}4`).value,
        longitude: document.getElementById(`f${id}5`).value,
        tentOnlySites: 0,
        totalCampsites: document.getElementById(`f${id}3`).value
    }

    //console.log("campObj: " + campObj);

    // Build Request including body
    let uri = "../api/v1/campgrounds";
    let config = {  // http headers
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(campObj),
    }

    //console.log("config: " + config);

    // perform PUT, await response
    let response = await fetch(uri, config);

    // convert the body of the response to JSON format
    let json = await response.json();

    //console.log("Response from Server POST:");
    //console.log(json);

    // build anchor for URL column
    let url_anchor= document.createElement("a");
    url_anchor.setAttribute('href', json.url);
    url_anchor.setAttribute('target', '_blank');
    url_anchor.textContent = json.url;

    // remove previous anchor
    document.getElementById(`d${id}6`).removeChild(document.getElementById(`d${id}6`).lastElementChild);

    // Set new values:
    document.getElementById(`d${id}2`).textContent = json.name;
    document.getElementById(`d${id}6`).appendChild(url_anchor);
    document.getElementById(`d${id}4`).textContent = json.latitude;
    document.getElementById(`d${id}5`).textContent = json.longitude;
    document.getElementById(`d${id}3`).textContent = json.totalCampsites;

    // re-display data fields, hide form fields
    for (let i = 2; i < 9; i++) {
        let span = document.querySelector(`#d${id}${i}`);
        span.style.display = "inline";
        span = document.querySelector(`#e${id}${i}`);
        span.style.display = "none";
    }
}

/*
 * Called when user presses the 'Cancel' button during edit.  This hides the edit fields, an
 * re-displays the data only fields.
 * STATUS: DONE
 */
function cancelEdit(event) {
    event.preventDefault();
    let row = this.closest('tr');
    let id = row.dataset.id;

    // Display display-only portion of this record, Hide edit portion
    for (let i = 2; i < 9; i++) {
        let span = document.querySelector(`#d${id}${i}`);
        span.style.display = "inline";
        span = document.querySelector(`#e${id}${i}`);
        span.style.display = "none";
    }
}

/*
 * Displays the modal dialog to confirm delete when the delete link
 * is clicked.  It also builds the event handlers for both the
 * 'Delete' and 'Cancel' buttons.
 *
 * STATUS: WORKING
 */
function showDeleteModal(event, campground, element) {
    // console.log("this: " + JSON.stringify(this, null, 4));
    event.preventDefault();
    //let row = this.closest('tr');
    let row = element.closest('tr');
    let id = row.dataset.id;

    // if (campground) {
    //     console.log("campground id: " + id);
    // } else {
    //     console.log("suggestedCampground id: " + id);
    // }

    let dialog = document.querySelector("#delDialog");
    let deleteButton = document.querySelector("#confirmDelete");
    let cancelButton = document.querySelector("#cancelDelete");

    dialog.showModal();

    let campName;
    if (campground) {
        campName = document.getElementById(`f${id}2`).value;
    } else {
        campName = document.getElementById("suggestedCGName").textContent;
    }

    //console.log("Campground Name: " + campName);
    document.getElementById("delName").textContent = campName;
    //nameSpan.textContent = campName;

    deleteButton.addEventListener("click", async () => {
        // Build Request including body

        let campObj = {id: id};
        let uri;

        if (campground) {
            uri = "../api/v1/campgrounds";
        } else {
            uri = "../api/v1/campgrounds/suggested";
        }

        let config = {  // http headers
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(campObj),
        }

        // console.log("Config:");
        // console.log(config);

        // perform DELETE, await response
        let response = await fetch(uri, config);

        if (response.status === 200) {
            // console.log("DELETED!!!!");
            row.remove();
            dialog.close();
        // } else {
        //     console.log("NOT DELETED, HTTP Response " + response.status);
        }

    });
    cancelButton.addEventListener("click", () => {
        dialog.close();
    });

}


