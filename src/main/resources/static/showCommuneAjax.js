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
                        
                     <h3 style="text-align: center">Commune</h3>
                    <div id="communeId-${commune.id}" class="parish_info">
                    <div>
                    <h6>Commune name</h6>
                    <p>  ${commune.name}</p>
                    </div>
                     <div>
                    <h6>Commune code</h6>
                    <p> ${commune.communeCode}  </p>
                    </div>
                      
                     </div> 
                     </br>
                     <h5 style="text-align: center">Perishes</h5>
                     
                `
                $.each(commune.parishSet, function (index, parish){
                  html+=  `
                  <div class="parish_info">
                    <div>
                    <h6>Parish name</h6>
                    <p>  ${parish.name} </p>
                    </div>
                    <div>
                    <h6>Infection pressure</h6>
                    <p>  ${parish.infectionPressure} </p>
                    </div>
                  
                   
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