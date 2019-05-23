function selectArgInput() {
    var argInput = document.getElementById("arg");
    selectInput(argInput);
}
function selectBaseInput() {
    var baseInput = document.getElementById("base");
    selectInput(baseInput);
}
function selectInput(field) {
    field.style.border = "thin inset red";
    field.onfocus = function () {
        field.style.border = "thin inset #00000044";
    }
}