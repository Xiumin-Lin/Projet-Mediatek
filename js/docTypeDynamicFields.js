$(function(){
    //show optional fields depending on the type
    $("#docType").on("change", function() {
        var selected_class = $("option:selected", this).val();
        var subBox = $(".subBox");
        if(subBox.length) {
            subBox.each(function() {
                if($(this).hasClass("type_" + selected_class)) {
                    $(this).show(500); // is the display speed in ms
                    $("input", this).prop('disabled',false);
                }
                else {
                    $(this).hide(500); // is the hide speed in ms
                    $("input", this).prop('disabled',true);
                }
            });
        }
    })
})
