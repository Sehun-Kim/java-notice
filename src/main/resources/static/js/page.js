$(".page-link").click(function(event) {
    if ($(event.target).is("a")) {
        var idx = new Number(event.target.name) - 1
        window.location.href="/?page=" + idx;
    }
});