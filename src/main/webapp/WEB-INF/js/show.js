function toTopOfPage() {
    if (window.scrollY > 0) {
        window.scrollTo(0, window.scrollY - 20);
        setTimeout("toTopOfPage()", 10);
    }
}