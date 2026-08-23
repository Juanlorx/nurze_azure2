function iniciarCarrusel(selector){

    const imagenes =
        document.querySelectorAll(selector + " img");

    let indice = 0;

    setInterval(() => {

        imagenes[indice].classList.remove("active");

        indice++;

        if(indice >= imagenes.length){
            indice = 0;
        }

        imagenes[indice].classList.add("active");

    }, 3000);
}

iniciarCarrusel(".pacientes");
iniciarCarrusel(".medicamentos");

function actualizarFecha() {

    const fecha = new Date();

    document.getElementById("fechaHora").innerHTML =
        fecha.toLocaleDateString() +
        " | " +
        fecha.toLocaleTimeString();
}

setInterval(actualizarFecha,1000);