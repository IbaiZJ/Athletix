// Función principal para validar el formulario
function validarFormulario() {
    const campos = document.querySelectorAll('input, select, textarea');
    let todosValidos = true;

    campos.forEach(campo => {
        if (!validarCampo(campo)) {
            todosValidos = false;
            mostrarError(campo, obtenerMensajeError(campo));
        } else {
            limpiarError(campo);
        }
    });

    return todosValidos;
}

// Función mejorada para mostrar errores
function mostrarError(campo, mensaje) {
    // Limpiar errores previos primero
    limpiarError(campo);
    
    // Aplicar estilos al campo con error
    campo.classList.add('campo-error');
    campo.style.borderColor = '#ff4444';
    campo.style.boxShadow = '0 0 0 1px #ff4444';
    
    // Crear contenedor de error
    const errorContainer = document.createElement('div');
    errorContainer.className = 'error-mensaje-container';
    
    // Crear icono de error
    const errorIcon = document.createElement('span');
    errorIcon.className = 'error-icon';
    errorIcon.textContent = '📍';
    
    // Crear texto de error
    const errorText = document.createElement('span');
    errorText.className = 'error-text';
    errorText.textContent = mensaje;
    
    // Ensamblar componentes
    errorContainer.appendChild(errorIcon);
    errorContainer.appendChild(errorText);
    
    // Insertar después del campo
    campo.parentNode.insertBefore(errorContainer, campo.nextSibling);
}

// Función para limpiar errores
function limpiarError(campo) {
    // Remover estilos del campo
    campo.classList.remove('campo-error');
    campo.style.borderColor = '';
    campo.style.boxShadow = '';
    
    // Eliminar mensaje de error si existe
    const errorContainer = campo.nextElementSibling;
    if (errorContainer && errorContainer.classList.contains('error-mensaje-container')) {
        campo.parentNode.removeChild(errorContainer);
    }
}

// Función para validar campos individuales
function validarCampo(campo) {
    const valor = campo.value.trim();
    const tipo = campo.type;
    const requerido = campo.required;
    const patron = campo.pattern;
    const minLength = campo.minLength || -1;
    const maxLength = campo.maxLength || Infinity;

    // Validar campo requerido
    if (requerido && valor === '') {
        return false;
    }

    // Validación especial para username (verificar disponibilidad)
    if ((campo.name === 'username' || campo.id === 'username') && valor !== '') {
        return validarUsernameDisponible(valor);
    }

    // Validar según el tipo de campo
    switch (tipo) {
        case 'email':
            return validarEmail(valor);
        case 'password':
            return validarPassword(valor);
        case 'tel':
            return validarTelefono(valor);
        case 'number':
            return !isNaN(valor) && (!campo.min || valor >= campo.min) && (!campo.max || valor <= campo.max);
        case 'checkbox':
            return !requerido || campo.checked;
        case 'radio':
            if (requerido) {
                const nombre = campo.name;
                const seleccionado = document.querySelector(`input[name="${nombre}"]:checked`);
                return seleccionado !== null;
            }
            return true;
        case 'select-one':
            return !requerido || valor !== '';
        default:
            if (patron) {
                const regex = new RegExp(patron);
                return regex.test(valor);
            }
            return valor.length >= minLength && valor.length <= maxLength;
    }
}

// Funciones de validación específicas
function validarEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}

function validarPassword(password) {
    // Mínimo 8 caracteres, al menos una letra y un número
    const re = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
    return re.test(password);
}

function validarTelefono(telefono) {
    // Formato internacional básico
    const re = /^\+?[\d\s-]{10,}$/;
    return re.test(telefono);
}

function validarUsernameDisponible(username) {
    // Aquí deberías implementar una llamada AJAX para verificar en el servidor
    // Esto es solo un ejemplo simulado
    const usernamesOcupados = ['admin', 'usuario', 'test'];
    return !usernamesOcupados.includes(username.toLowerCase());
}

// Función para obtener mensajes de error personalizados
function obtenerMensajeError(campo) {
    const valor = campo.value.trim();
    const tipo = campo.type;
    const requerido = campo.required;

    // Validación especial para username
    if ((campo.name === 'username' || campo.id === 'username') && valor !== '') {
        return 'El nombre de usuario ya está en uso';
    }

    if (requerido && valor === '') {
        return 'Este campo es obligatorio.';
    }

    switch (tipo) {
        case 'email':
            return 'Por favor, introduce un correo electrónico válido.';
        case 'password':
            return 'La contraseña debe tener al menos 8 caracteres, incluyendo letras y números.';
        case 'tel':
            return 'Por favor, introduce un número de teléfono válido.';
        case 'number':
            if (campo.min && valor < campo.min) {
                return `El valor mínimo permitido es ${campo.min}.`;
            }
            if (campo.max && valor > campo.max) {
                return `El valor máximo permitido es ${campo.max}.`;
            }
            return 'Por favor, introduce un número válido.';
        case 'checkbox':
            return 'Debes aceptar este campo para continuar.';
        case 'radio':
            return 'Debes seleccionar una opción.';
        case 'select-one':
            return 'Debes seleccionar una opción de la lista.';
        default:
            if (campo.pattern) {
                return 'El formato del campo no es válido.';
            }
            if (campo.minLength && valor.length < campo.minLength) {
                return `El campo debe tener al menos ${campo.minLength} caracteres.`;
            }
            if (campo.maxLength && valor.length > campo.maxLength) {
                return `El campo no puede tener más de ${campo.maxLength} caracteres.`;
            }
            return 'El valor introducido no es válido.';
    }
}

// CSS recomendado (añade esto a tu hoja de estilos)
/*
.error-mensaje-container {
    display: flex;
    align-items: center;
    gap: 6px;
    margin-top: 4px;
    color: #ff4444;
    font-size: 0.85em;
    padding: 4px 8px;
    background-color: #fff8f8;
    border-radius: 4px;
    width: fit-content;
}

.error-icon {
    font-size: 1.1em;
}

.campo-error {
    border-color: #ff4444 !important;
    box-shadow: 0 0 0 1px #ff4444 !important;
}
*/

// Cómo usar:
document.querySelector('form').addEventListener('submit', function(e) {
    if (!validarFormulario()) {
        e.preventDefault();
        // Opcional: desplazarse al primer error
        const primerError = document.querySelector('.campo-error');
        if (primerError) {
            primerError.scrollIntoView({ behavior: 'smooth', block: 'center' });
        }
    }
});