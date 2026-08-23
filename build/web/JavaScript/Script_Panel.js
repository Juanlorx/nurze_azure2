<script>

function mostrarUsuarios(){

document.getElementById("contenidoModulo").innerHTML = `

<h2>Gestión de Usuarios</h2>

<button class="btn-agregar">
    Nuevo Usuario
</button>

<table>

<thead>

<tr>

<th>ID</th>
<th>Nombre</th>
<th>Correo</th>
<th>Rol</th>
<th>Acciones</th>

</tr>

</thead>

<tbody>

<tr>

<td>1</td>
<td>Juan Garcia</td>
<td>juan@gmail.com</td>
<td>Administrador</td>

<td>

<button class="editar">
Editar
</button>

<button class="eliminar">
Eliminar
</button>

</td>

</tr>
<script>

function mostrarUsuarios(){

    let contenido =
            document.getElementById("contenidoModulo");

    contenido.style.display = "block";
}

</script>
</body>

</table>

`;

}

</script>