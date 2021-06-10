function getParishes() {
    //Ajax request - this gets all the parishes
    $.ajax({
        url: "/parish",
        type: "GET",
        contentType: "application/JSON",
        success: function (parishData) {
            $.each(parishData, function (index, parishData) { //iterer over collection i data
                const html = `
                    <div id="parishId-${parishData.id}" class="parish_info">
                    <div>
                    <p>Parish Name</p>
                     <p>${parishData.name}</p>
                    </div>
                    <div>
                    <p>ParishCode</p>
                    <p>${parishData.parishCode}</p>
                    </div>
                         ${parishData.infectionPressure} ${parishData.shutDownTime}
                        <button onclick="parishDelete(${parishData.id})">
                            Delete
                         </button> 
                         <button onclick="location.href='/updateParish?id=${parishData.id}'">
                         Update
                         </button>
                         <input type="checkbox" id="checkBoxId" ${compareDate(parishData.shutDownTime) ? "checked" : ""}>
 
                     </div> 
                `
                $("#parishes").append(html)
            })
        },
    });
}


$("#create_parish").submit(function (e) {

    e.preventDefault(); // avoid to execute the actual submit of the form.

    debugger;
    let newParish = {
        parishCode: $('#parish_code').val(),
        name: $('#parish_name').val(),
        infectionPressure: $('#infection_pressure').val(),
        shutDownTime: $('#shutdown_time').val(),
        commune: parseInt($('#commune_id').val())
    };

    let json = JSON.stringify(newParish);

    $.ajax({
        type: "POST",
        url: "/parish",
        data: json,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function () {
            location.href = "/"
        }
    });
});


function parishDelete(id) {
    $.ajax({
        url: '/parish/' + id,
        type: 'DELETE',
        success: function () {
            $(`#parishId-${id}`).remove()
        }
    });
}

function compareDate(shutDownTime) {
    if (shutDownTime == null) {
        return false
    }
    return new Date() >= new Date(shutDownTime);
}



