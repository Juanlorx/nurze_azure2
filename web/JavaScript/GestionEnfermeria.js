document.querySelectorAll('[data-filtro]').forEach(function (input) {
    input.addEventListener('input', function () {
        var tabla = document.getElementById(input.dataset.filtro);
        if (!tabla) return;
        var texto = input.value.toLocaleLowerCase();
        tabla.querySelectorAll('tbody tr').forEach(function (fila) {
            fila.style.display = fila.textContent.toLocaleLowerCase().includes(texto) ? '' : 'none';
        });
    });
});
