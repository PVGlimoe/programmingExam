$(document).ready(function() {//kald først jQuery-funktioner,når siden er klar

    console.log("inde");
    const queryString  = window.location.search;

    const urlParams = new URLSearchParams(queryString);

    const id = urlParams.get('id');

    $.ajax({
        url: "/parish/" + id,
        type: "GET",
        contentType: "application/JSON",
        success: function (parishData) {

            $("#parish_code").val(parishData.parishCode);
            $("#parish_name").val(parishData.name);
            $("#infection_pressure").val(parishData.infectionPressure);
            $("#shutdown_time").val(parishData.shutDownTime);
            $("#commune_id").val(parishData.commune);


        }
    });

    $("#create_parish").submit(function(e) {

        e.preventDefault(); // avoid to execute the actual submit of the form.

        let newParish= {parishCode: $('#parish_code').val(),
            name: $('#parish_name').val(),
            infectionPressure: $('#infection_pressure').val(),
            shutDownTime: $('#shutdown_time').val(),
            commune: parseInt($('#commune_id').val())
        };

        let json = JSON.stringify(newParish);

        $.ajax({
            type: "PUT",
            url: "/parish/" + id,
            data: json,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function() {
                location.href = "/"
            }
        });
    });
});