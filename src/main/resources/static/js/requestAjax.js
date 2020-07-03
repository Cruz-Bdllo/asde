var btn = document.querySelectorAll("#btn-view-representant");
var btnUpdates = document.querySelectorAll("#btn-update-representant");

var modalRepre = document.getElementById("table-data-repre").children;
var cellRepresentant = [];

for(const t of modalRepre){
    let cell = t.children;
    cellRepresentant.push(cell[1]);
}


// console.log(btn);
const URL_BASE = "http://localhost:8080/api/representant/getById/";

btn.forEach(b => {
    b.addEventListener("click", () => {
        let idRepre = b.parentElement.parentElement.getAttribute('id');
        req = new XMLHttpRequest();
        req.open("GET", URL_BASE+idRepre, true);
        req.onreadystatechange = function() {
            if (this.readyState == 4 && this.status==200) {
                let representant = JSON.parse(this.response);
                cellRepresentant[0].innerHTML=(representant.rfc=='')?'Sin RFC': representant.rfc;
                cellRepresentant[1].innerHTML=representant.name+" "+representant.firstName+" "+representant.lastName;
                cellRepresentant[2].innerHTML=representant.email;
                cellRepresentant[3].innerHTML=representant.phone;
            }
        }
        req.send();
       // console.log(idRepre);
   })
});

/*  PRESENTANTES
------------------------------------------------------ */
var formRepre = document.getElementById("form-update-representant");
btnUpdates.forEach(btn => {
   btn.addEventListener("click", () => {
       let idRepre = btn.parentElement.parentElement.getAttribute("id");
       ajax = new XMLHttpRequest();
       ajax.open("GET", URL_BASE+idRepre, true);
       ajax.onreadystatechange = function() {
           if (this.readyState == 4 && this.status == 200) {
               let repre = JSON.parse(this.responseText);
               document.getElementById("idrepre").value = repre.idRepresentant;
               document.getElementById("rfc-repre").value = repre.rfc;
               document.getElementById("nombre-repre").value = repre.name;
               document.getElementById("apeP-repre").value = repre.firstName;
               document.getElementById("apeM-repre").value = repre.lastName;
               document.getElementById("email-repre").value = repre.email;
               document.getElementById("phone-repre").value = repre.phone;
           }
       }
       ajax.send();
   });
});



$("#form-update-representant").submit((e) => {
    e.preventDefault();
    const dataSend = {
        idRepresentant: $("#idrepre").val(),
        rfc : $("#rfc-repre").val(),
        name : $("#nombre-repre").val(),
        firstName : $("#apeP-repre").val(),
        lastName : $("#apeM-repre").val(),
        email : $("#email-repre").val(),
        phone : $("#phone-repre").val()
    }
    $.ajax({
        url: "http://localhost:8080/api/representant/updateRepresentant",
        data: dataSend,
        method: "PUT",
        dataType: "text"

    })
        .done(function(data){
            $("#update-representant").modal("hide");
            swal({
                title: "Actualizando",
                text: "Se ha actualizando correctamente",
                icon: "success",
            }).then(() => {

                window.location.replace(window.location.pathname);
            })
        })
        .fail(function(jqXHR, textStatus, errorThrown){

            let errors = JSON.parse(jqXHR.responseText);
            console.log(errors);
            $("#error-name").text((errors[1] !== "") ? errors[1] : null).show();
            $("#error-firstName").text((errors[3] !== "") ? errors[3] : null).show();
            $("#error-lastName").text((errors[0] !== "") ? errors[0] : null).show();
            $("#error-email").text((errors[2] !== "") ? errors[2] : null).show();
            // $("#error-name").show();
        })
});


btnDeleteRepre = document.querySelectorAll("#btn-delete-repre");
btnDeleteRepre.forEach(btn => {
    btn.addEventListener("click", (e) => {
        e.preventDefault();
        const id = btn.parentElement.parentElement.getAttribute("id");



        swal({
            title: "Confirmación",
            text: "Desea eliminar al representante?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    window.location.href="http://localhost:8080/representantes/eliminar/"+id;
                } else {

                }
            });
    });
});




