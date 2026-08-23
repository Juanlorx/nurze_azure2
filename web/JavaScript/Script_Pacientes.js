function agregarALista() {

    let nombre = document.getElementById("nombre").value;
    let cedula = document.getElementById("cedula").value;
    let meds = document.getElementById("meds").value;
    let condicion = document.getElementById("condicion").value;

    if (nombre === "" || cedula === "" || meds === "" || condicion === "") {
        alert("Complete todos los campos");
        return;
    }

    let lista = document.getElementById("listaPacientes");

    let fila = document.createElement("div");
    fila.classList.add("fila-paciente");

    fila.innerHTML = `
        <span>
            <input type="checkbox">
        </span>

        <span>${nombre}</span>

        <span>${cedula}</span>

        <span>${meds}</span>

        <span>${condicion}</span>

        <span>
            <button class="btn-eliminar"
                    onclick="eliminarPaciente(this)">
                Eliminar
            </button>
        </span>
    `;

    lista.appendChild(fila);

    document.getElementById("nombre").value = "";
    document.getElementById("cedula").value = "";
    document.getElementById("meds").value = "";
    document.getElementById("condicion").value = "";
}

function eliminarPaciente(boton) {

    let fila = boton.parentElement.parentElement;
    fila.remove();

}