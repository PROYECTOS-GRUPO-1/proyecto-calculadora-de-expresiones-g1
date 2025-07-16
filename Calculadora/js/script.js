// FUNCIONES DEFINIDAS EN EL ÁMBITO GLOBAL
function agregar(valor) {
    const pantalla = document.getElementById('pantalla');
    pantalla.value += valor;
}

function limpiar() {
    const pantalla = document.getElementById('pantalla');
    const historial = document.getElementById('historial');
    pantalla.value = '';
    historial.textContent = '';
}

function borrar() {
    const pantalla = document.getElementById('pantalla');
    pantalla.value = pantalla.value.slice(0, -1);
}

function calcular() {
    const pantalla = document.getElementById('pantalla');
    const historial = document.getElementById('historial');
    try {
        if (/[a-zA-Z]/.test(pantalla.value)) {
            throw new Error("Expresión inválida");
        }

        const expresion = pantalla.value.replace(/×/g, '*');
        const resultado = eval(expresion);

        if (isNaN(resultado) || !isFinite(resultado)) {
            throw new Error("Error matemático");
        }

        historial.textContent = `${pantalla.value} =`;
        pantalla.value = resultado;
    } catch (error) {
        pantalla.value = "Error";
        setTimeout(() => limpiar(), 1000);
    }
}

// UNA VEZ CARGADO EL DOCUMENTO, SE ASIGNAN EVENTOS ADICIONALES SI ES NECESARIO
document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('button').forEach(btn => {
        if (!btn.onclick) {
            const value = btn.textContent;
            if (value && value.trim() !== '') {
                btn.onclick = function() {
                    agregar(value);
                    this.blur(); // Remover foco después de clic
                };
            }
        }
    });
});
