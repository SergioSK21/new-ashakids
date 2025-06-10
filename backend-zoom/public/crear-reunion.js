document.getElementById('form-agendar').addEventListener('submit', async function (e) {
    e.preventDefault();

    const tema = document.getElementById('tema').value.trim();
    const fecha = document.getElementById('fecha').value;
    const hora = document.getElementById('hora').value;
    const duracion = document.getElementById('duracion').value;
    const nombre = document.getElementById('nombre').value.trim();

    if (!tema || !fecha || !hora || !duracion || !nombre) {
        alert('Por favor, completa todos los campos.');
        return;
    }

    try {
        const fechaISO = `${fecha}T${hora}:00`; // SIN toISOString

        const response = await fetch('http://localhost:3000/crear-reunion', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                topic: tema,
                start_time: fechaISO,
                duration: parseInt(duracion),
                agenda: `Cita con ${nombre}`
            })
        });

        const data = await response.json();
        console.log("Respuesta del servidor:", data);

        if (response.ok) {
            const modal = new bootstrap.Modal(document.getElementById('modalReunion'));
            const joinUrl = data.linkZoom || data.join_url; // <-- depende de cómo responda tu servidor
            document.getElementById('linkReunion').href = joinUrl;
            modal.show();
            this.reset();
        } else {
            alert(`❌ Error al crear reunión: ${data.error || 'verifica el servidor'}`);
        }

    } catch (err) {
        console.error(err);
        alert('❌ No se pudo conectar al servidor.');
    }
});
