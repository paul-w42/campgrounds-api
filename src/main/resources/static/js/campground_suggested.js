/*
 * When clicked do one of two things, depending on what is currently showing.
 * 1) hide the currently displayed campgrounds and display suggested campgrounds, or
 * 2) hide the suggested campgrounds, and display the confirmed campgrounds
 */
async function viewSuggestedCGs() {
    console.log("viewSuggestedCGs");

    // viewSuggestedCGsLink = document.querySelector("#viewSuggestedAnchor");
    let adminTable = document.querySelector("#container");
    let suggestedTable = document.querySelector("#suggestedContainer");
    let viewSuggestedCGsLink = document.querySelector("#viewSuggestedAnchor");

    // if currently display Suggested Campgrounds, display confirmed Campgrounds
    if (viewSuggestedCGsLink.textContent === "View Campgrounds") {
        adminTable.style.display = "table";
        suggestedTable.style.display = "none";
        viewSuggestedCGsLink.textContent = "View Suggested CGs";

    } else {
        adminTable.style.display = "none";
        suggestedTable.style.display = "table";
        viewSuggestedCGsLink.textContent = "View Campgrounds";

        await fetchSuggestedCampgrounds();
    }
}


async function fetchSuggestedCampgrounds()     // async required for await keyword below
{
    let uri = "../api/v1/campgrounds/suggested";
    let config = {  // http headers
        method: "get"
    }

    let response = await fetch(uri, config);

    // convert the body of the response to JSON format
    let json = await response.json();

     addItemsSuggestedTable(json);

    //console.log("fetchSuggestedCampgrounds() complete");
}

// Add Records to table
function addItemsSuggestedTable(suggestedArray)
{
    let section = document.querySelector("#suggestedContainer");

    // remove all rows (if exist) from prior build-out
    let rows = section.getElementsByTagName("tr");
    for (let i = rows.length - 1; i > 0; i--) {
        section.deleteRow(i);
    }

    // loop over array, add one item at a time
    for (let i = 0; i < suggestedArray.length; i++)
    {
        let suggested = suggestedArray[i];
        addSuggestedRow(suggested, section);
    }
}

/*
 * builds row/columns to display tabular data
 * spanX1 is normal view/display of data
 * spanX2 is edit view/display for data
 */
function addSuggestedRow(campground, section)
{
    // create HTML elements
    let tr = document.createElement("tr")

    // IDs
    let td1 = document.createElement("td");     // sc1 = suggested column 1, was td1
    td1.id = `${campground.id}`;        // todo: necessary?

    // Campground Name
    let td2 = document.createElement("td");
    td2.id = "suggestedCGName";

    // Num Campsites
    let td3 = document.createElement("td");

    // Tent Campsites
    let td4 = document.createElement("td");

    // Latitude
    let td5 = document.createElement("td");

    // Longitude
    let td6 = document.createElement("td");

    // URL & Anchor
    let td7 = document.createElement("td");
    let a7= document.createElement("a");

    // user
    let td8 = document.createElement("td");

    // email
    let td9 = document.createElement("td");

    // date
    let td10 = document.createElement("td");

    let td11 = document.createElement("td");
    let a11Remove = document.createElement("a");

    // assign unique id to this row, attribute name is 'data-id'
    tr.dataset.id = campground.id;

    // connect elements to child / insertBefore(), appendChild(), ...
    tr.appendChild(td1);    // id
    tr.appendChild(td2);    // name
    tr.appendChild(td3);    // total campsites
    tr.appendChild(td4)     // tent only sites
    tr.appendChild(td5);    // lat
    tr.appendChild(td6);    // long
    tr.appendChild(td7);    // url
    tr.appendChild(td8);    // UserName
    tr.appendChild(td9);    // UserEmail
    tr.appendChild(td10);   // Date Suggested
    tr.appendChild(td11);   // remove command

    // add text or HTML attributes
    td1.textContent = campground.id;

    // name
    td2.textContent = campground.name;

    // campsites
    td3.textContent = campground.totalCampsites;

    // tent sites
    td4.textContent = campground.tentOnlySites;

    // latitude
    td5.textContent = campground.latitude.toFixed(4);

    // longitude
    td6.textContent = campground.longitude.toFixed(4);

    // build anchor for campground url
    if (campground.url !== null) {
        a7.setAttribute('href', campground.url);
        a7.setAttribute('target', "blank");
        a7.textContent = "[url]";
    }
    td7.appendChild(a7);

    // username
    td8.textContent = campground.userName;

    // email
    td9.textContent = campground.userEmail;

    // date
    let dateSt = campground.dateSuggested;
    let date = new Date(dateSt);
    td10.textContent = (date.getFullYear() + "-" + date.getMonth() + "-" + date.getDate());

    a11Remove.setAttribute('href', '#');
    a11Remove.textContent = "Remove";
    //a11Remove.addEventListener("click", showDeleteModal);
    a11Remove.addEventListener("click", (event) => {
        showDeleteModal(event, false, a11Remove);
    });

    td11.appendChild(a11Remove);

    section.appendChild(tr);
}
