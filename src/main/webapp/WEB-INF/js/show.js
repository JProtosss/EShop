var mySidebar = document.getElementById("mySidebar");


function w3_open() {
    if (mySidebar.style.display === 'block') {
        mySidebar.style.display = 'none';
    } else {
        mySidebar.style.display = 'block';
    }
}

function w3_close() {
    mySidebar.style.display = "none";
}