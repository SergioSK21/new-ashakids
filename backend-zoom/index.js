const express = require("express");
const axios = require("axios");
require("dotenv").config();
const cors = require('cors'); // Para habilitar CORS

const app = express();
app.use(express.json());
app.use(express.static('public'));
app.use(cors());  // Habilitar CORS

// Obtener access_token automáticamente desde Zoom
async function getAccessToken() {
    try {
        const response = await axios.post("https://zoom.us/oauth/token", null, {
            params: {
                grant_type: "account_credentials",
                account_id: process.env.ZOOM_ACCOUNT_ID
            },
            auth: {
                username: process.env.ZOOM_CLIENT_ID,
                password: process.env.ZOOM_CLIENT_SECRET
            }
        });

        return response.data.access_token;
    } catch (err) {
        console.error("Error al obtener el access_token:", err.response?.data || err);
        throw new Error('No se pudo obtener el token de acceso.');
    }
}

// Crear reunión Zoom
app.post("/crear-reunion", async (req, res) => {
    try {
        const token = await getAccessToken();

        const { topic, start_time, duration, agenda } = req.body;

        const response = await axios.post(
            "https://api.zoom.us/v2/users/me/meetings",
            {
                topic: topic || "Sesión de Terapia ASHAKids",  // Si no se pasa un tema, usa el default
                type: 2, // reunión programada
                start_time: req.body.start_time,
                duration: duration,
                timezone: "America/Lima",
                settings: {
                    join_before_host: true
                }
            },
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json"
                }
            }
        );

        const link = response.data.join_url;
        res.json({ mensaje: "Reunión creada", linkZoom: link });

        const mysql = require('mysql2/promise'); // Instala con npm si no lo tienes

        const db = await mysql.createPool({
            host: 'localhost',
            user: 'root',
            password: 'Mantequillademani1%',
            database: 'PaginaPrueba'
        });

        await db.query(
            'INSERT INTO reuniones (tema, fecha, hora, duracion, terapeuta, link_zoom) VALUES (?, ?, ?, ?, ?, ?)',
            [
                topic,
                start_time.split('T')[0],        // fecha
                start_time.split('T')[1],        // hora
                duration,
                agenda.replace('Cita con ', ''), // terapeuta
                link
            ]
        );
        


    } catch (err) {
        console.error("Error al crear reunión:", err.response?.data || err);
        res.status(500).json({ mensaje: "No se pudo crear la reunión.", error: err.message });
    }
});

app.listen(3000, () => {
    console.log("Servidor en http://localhost:3000");
});
