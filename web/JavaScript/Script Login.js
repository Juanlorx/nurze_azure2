 const form = document.getElementById('loginForm');
        const errorMsg = document.getElementById('errorMsg');

        form.addEventListener('submit', function(e) {
            e.preventDefault();

            const email = document.getElementById('email').value.trim();
            const rethus = document.getElementById('rethus').value.trim();
            const password = document.getElementById('password').value.trim();

            if (email === '' || rethus === '' || password === '') {
                errorMsg.style.display = 'block';
                return;
            }

            errorMsg.style.display = 'none';
            alert('Inicio de sesión exitoso (simulación)');
        });