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

function toTopOfPage() {
    if (window.scrollY > 0) {
        window.scrollTo(0, window.scrollY - 20);
        setTimeout("toTopOfPage()", 10);
    }
}