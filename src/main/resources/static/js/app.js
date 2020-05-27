let typeProcedure = document.getElementById("type-procedure");

document.getElementById("signatures").style.display =
(typeProcedure.options[typeProcedure.selectedIndex].value==="ESTRUCTURAL")?"block": "none";

typeProcedure.addEventListener("change", () => {
    let state = typeProcedure.options[typeProcedure.selectedIndex].value;

    document.getElementById("signatures").style.display =
        (state==="ESTRUCTURAL") ? "block" : "none";
});

// Validate expiration date
let expiration = document.getElementById("expiration-permanent");

expiration.addEventListener("change", () => {

  console.log(expiration.checked ? "checked":"uncheked");
   document.getElementById("expiration-date").style.display =
       (expiration.checked) ? "none" : "block";
});
console.log(expiration)