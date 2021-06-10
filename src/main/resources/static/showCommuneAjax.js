$(document).ready(function() {//kald først jQuery-funktioner,når siden er klar
    $.ajax({
        url: "/commune",
        type: "GET",
        contentType: "application/JSON",
        success: function (communeData) {
            $.each(communeData, function (index, commune) { //iterer over collection i data
                let totalInfection = 0;
                let averageInfection = 0;
                let html = `
                        
                     <h3>Commune</h3>
                    <div id="communeId-${commune.id}">
                        ${commune.name} ${commune.communeCode} 
                     </div> 
                     </br>
                     <h5>Perishes</h5>
                     
                `
                $.each(commune.parishSet, function (index, parish){
                  html+=  `
                  <div>
                   ${parish.name} ${parish.infectionPressure}
                   
                  </div>
                    `
                      totalInfection += parish.infectionPressure;
                })
                averageInfection = totalInfection / commune.parishSet.length;
                html += `</br>
                <p>Total infection pressure ${totalInfection.toFixed(2)}</p>
                
                <p>Average infection pressure ${averageInfection.toFixed(2)}</p>
                <hr>
                `

                $("#communes").append(html)

            })
            $("#status").html("Svar fra server OK");
        },
    });
});