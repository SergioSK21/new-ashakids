function mostrarContenido(seccion) {
  const contenedor = document.getElementById("contenido-dinamico");

async function crearReunionDesdeCard(reunionId) {
    try {
        // Hacer una solicitud GET a Spring para obtener los detalles de la reunión usando el ID
        const response = await fetch(`http://localhost:8080/api/reuniones/${reunionId}`);
        const reunion = await response.json();

        const datos = {
            topic: reunion.tema,
            start_time: `${reunion.fecha}T${reunion.hora}:00`,
            duration: reunion.duracion,
            agenda: `Cita con ${reunion.terapeuta}`
        };

        // Enviar la solicitud al backend Node.js para crear la reunión
        const zoomResponse = await fetch("http://localhost:3000/crear-reunion", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(datos)
        });

        const data = await zoomResponse.json();
        if (zoomResponse.ok) {
            alert("✅ Reunión creada. Link: " + data.linkZoom);
            // Aquí podrías mostrar el modal con el link o hacer algo más
        } else {
            alert("❌ Error al crear reunión");
        }
    } catch (err) {
        console.error(err);
        alert('❌ No se pudo crear la reunión');
    }
}
function cambiarSeccion(elemento) {
  const seccion = elemento.getAttribute("data-seccion");
  cargarContenido(seccion); // Esta es la función que ya tienes
}

  if (seccion === "agenda") {
    contenedor.innerHTML =
      `
      <h3>Próximas Citas</h3>

      <div class="cita-card">
        <div class="cita-img">
          <img src="img/zoom.jpg" alt="niñofeliz">
        </div>
        <div class="cita-content">
          <h4 class="titulo-cita">Reunión de Proyecto</h4>
          <div class="parrafo-cita">
            <div><strong>Modalidad:</strong> Virtual(Zoom)</div>
            <div><strong>Psicologo:</strong> Susana Horia</div>
            <div><strong>Ticket:</strong> #123456</div>
            <div><strong>Hora:</strong> 10:00 AM</div>
          </div>
        </div>

        <div class="cita-confirmacion">
          <button class="btn-reservar">Reservar</button>
          <button class="btn-cancelar">Cancelar</button>

        </div>
      </div>
        `;
        vincularBotonesReservar();

  } else if (seccion === "foros") {
    contenedor.innerHTML = "<h3>Foros</h3><p>Participa en los foros.</p>";
  } else if (seccion === "tips") {
    contenedor.innerHTML =
      "<h3>Tips</h3><p>¡Contenido alternativo como ayuda para los padres</p>";
  } else if (seccion === "blogs") {
    contenedor.innerHTML = "<h3>Contenido</h3><p>Explora contenido nuevo.</p>";
  }
}

/* Toggle menú hamburguesa */
document.addEventListener("DOMContentLoaded", () => {
  const toggleBtn = document.createElement("div");
  toggleBtn.className = "menu-toggle";
  toggleBtn.textContent = "☰";
  document.body.appendChild(toggleBtn);

  const sidebar = document.querySelector(".sidebar");

  toggleBtn.addEventListener("click", () => {
    sidebar.classList.toggle("active");
  });
});
<<<<<<< HEAD

=======
>>>>>>> dfebf74f83a65f85810d1f2da76db17a9064b88f
/*
function vincularBotonesReservar() {
  const botones = document.querySelectorAll(".btn-reservar");

  botones.forEach(btn => {
    btn.addEventListener("click", async () => {
      const terapeutaId = btn.getAttribute("data-id"); // Usar el ID del terapeuta como atributo en el botón

      try {
        // Hacer una solicitud al backend para obtener los datos del terapeuta
        const response = await fetch(`http://localhost:8080/api/terapeutas/${terapeutaId}`);
        const terapeuta = await response.json();

        // Actualizar los datos en el modal
        document.getElementById('terapeuta-nombre').textContent = terapeuta.nombre;
        document.getElementById('terapeuta-servicio').textContent = terapeuta.servicio;
        document.getElementById('terapeuta-fecha-hora').textContent = new Date(terapeuta.fecha_hora).toLocaleString();
        document.getElementById('contacto-nombre').textContent = "Nombre del contacto"; // Si tienes el dato, lo agregas aquí
        document.getElementById('contacto-correo').textContent = "Correo"; // Lo mismo aquí
        document.getElementById('monto').textContent = `S/ ${terapeuta.monto.toFixed(2)}`;

        // Mostrar el modal
        const citaModal = new bootstrap.Modal(document.getElementById('citaModal'));
        citaModal.show();

        // Acción cuando se haga clic en "Reservar"
        document.getElementById('reservar-btn').addEventListener("click", () => {
          // Aquí puedes agregar la lógica para confirmar la reserva o hacer más acciones.
          alert("Cita reservada!");
        });

      } catch (err) {
        console.error(err);
        alert("❌ Error al obtener los datos del terapeuta.");
      }
    });
  });
}
*/

function mostrarModalZoom(link) {
  const linkBtn = document.getElementById("linkReunion");
  linkBtn.href = link;

  const bsModal = new bootstrap.Modal(document.getElementById("modalReunion"));
  bsModal.show();
}


document.addEventListener("DOMContentLoaded", () => {
  vincularBotonesReservar();
});


