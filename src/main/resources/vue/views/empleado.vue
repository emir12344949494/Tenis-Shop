<template id="empleado">
    <v-app class="bg-grey-darken-4">

        <!-- APP BAR -->
        <v-app-bar color="grey-darken-4" flat>
            <v-btn icon class="d-lg-none" @click.stop="drawer = !drawer">
                <v-icon>mdi-menu</v-icon>
            </v-btn>

            <h2 class="text-white font-weight-bold ml-4 d-flex align-center">
                Sneaker-Shop-Emplados&nbsp;&nbsp;<v-icon>mdi-tennis"></v-icon>
            </h2>

            <v-spacer></v-spacer>

            <v-btn icon color="white" @click="logout">
                <v-icon>mdi-logout</v-icon>
            </v-btn>
        </v-app-bar>

        <!-- DRAWER -->
        <v-navigation-drawer v-model="drawer" app temporary color="grey-darken-4">
            <v-list nav>
                <v-list-item v-for="mnu in menu" :key="mnu.text" :prepend-icon="mnu.icon" :title="mnu.text" link
                    :href="mnu.href" />
            </v-list>

            <v-divider class="my-4" />

            <v-list nav>
                <v-list-item prepend-icon="mdi-home" title="Inicio" @click="selection = 'dashboard'; drawer = false" />
            </v-list>

            <v-list nav>
                <v-list-item prepend-icon="mdi-account-group" title="Jugadores"
                    @click="selection = 'players'; drawer = false" />
            </v-list>

            <v-list nav>
                <v-list-item prepend-icon="mdi-trophy" title="Torneos"
                    @click="selection = 'tournaments'; drawer = false" />
            </v-list>

            <v-list nav>
                <v-list-item prepend-icon="mdi-chart-line" title="Ranking"
                    @click="selection = 'ranking'; drawer = false" />
            </v-list>
        </v-navigation-drawer>

        <!-- CONTENIDO -->
        <v-main class="pa-6">
            <v-container>
                <br>
                <!-- TITULO -->
                <h1 class="text-white font-weight-bold mb-2">Panel de Tenis – Empleados</h1>
                <p class="text-grey-lighten-1 mb-6">
                    Control total del sistema, jugadores, torneos, rankings y más.
                </p>

                <!-- SLIDER PRINCIPAL -->
                <v-carousel cycle height="350" hide-delimiters>
                    <v-carousel-item v-for="(img, i) in slider" :key="i">
                        <v-img :src="img" cover></v-img>
                    </v-carousel-item>
                </v-carousel>

                <br />

                <!-- TABS -->
                <v-tabs v-model="selection" color="green-accent-4" class="mb-6" grow>
                    <v-tab value="dashboard">Tenis en Stock</v-tab>
                    <v-tab value="players">Ventas por Mes</v-tab>
                    <v-tab value="tournaments">Ofertas</v-tab>
                    <v-tab value="ranking">Categorias</v-tab>
                </v-tabs>

                <!-- CONTENIDO DINÁMICO -->
                <v-window v-model="selection">

                    <!-- DASHBOARD -->
                    <v-window-item value="dashboard">
                        <v-card flat color="grey-darken-3" class="pa-6 text-white rounded-xl">

                            <h3 class="text-h5 font-weight-bold mb-2 text-center">Registro de Producto</h3>
                            <p class="text-grey-lighten-1 mb-8 text-center">Ingrese los datos para actualizar el
                                inventario</p>

                            <v-card class="pa-4 mb-6 bg-grey-darken-4 rounded-lg" flat>
                                <v-row dense align="center">
                                    <v-col cols="12" md="6">
                                        <v-text-field v-model="newSale.desc" label="Descripción del producto"
                                            variant="solo-filled" flat density="comfortable" bg-color="grey-darken-3"
                                            hide-details prepend-inner-icon="mdi-tag"></v-text-field>
                                    </v-col>

                                    <v-col cols="8" md="4">
                                        <v-text-field v-model.number="newSale.qty" label="Cantidad" type="number"
                                            variant="solo-filled" flat density="comfortable" bg-color="grey-darken-3"
                                            hide-details prepend-inner-icon="mdi-numeric"></v-text-field>
                                    </v-col>

                                    <v-col cols="4" md="2">
                                        <v-btn color="blue-accent-2" block height="48" elevation="2" @click="addSale"
                                            :disabled="!newSale.desc || !newSale.qty">
                                            <v-icon start>mdi-plus</v-icon>
                                            Agregar
                                        </v-btn>
                                    </v-col>
                                </v-row>
                            </v-card>

                            <v-card class="bg-grey-darken-4 rounded-lg mb-6" flat>
                                <v-card-title class="text-subtitle-1 text-blue-accent-2 pa-4">
                                    Control de Inventario Dinámico
                                </v-card-title>

                                <v-data-table :headers="headers" :items="salesList" theme="dark" class="bg-transparent"
                                    no-data-text="No hay registros disponibles">
                                    <template v-slot:item.qty="{ item }">
                                        <v-chip color="green-accent-3" size="small" variant="flat"
                                            class="font-weight-bold">
                                            {{ item.qty }} unidades
                                        </v-chip>
                                    </template>

                                    <template v-slot:item.actions="{ index }">
                                        <v-btn icon="mdi-delete" variant="text" color="red-accent-2" size="small"
                                            @click="removeSale(index)"></v-btn>
                                    </template>
                                </v-data-table>
                            </v-card>
                        </v-card>
                    </v-window-item>

                    <!-- PLAYERS -->
                    <v-window-item value="players">
                        <v-card flat color="grey-darken-3" class="pa-6 text-white">

                            <h3 class="text-h5 font-weight-bold text-center">Ventas que se generaron este mes</h3>
                            <p class="text-grey-lighten-1 text-center mb-6">
                                Grafica de ventas
                            </p>

                            <v-list class="bg-grey-darken-4 rounded-lg">
                                <v-list-item v-for="p in players" :key="p.id">
                                    <v-list-item-avatar size="50">
                                        <v-img :src="p.photo"></v-img>
                                    </v-list-item-avatar>

                                    <v-list-item-title>{{ p.name }}</v-list-item-title>
                                    <v-list-item-subtitle>{{ p.country }}</v-list-item-subtitle>
                                </v-list-item>
                            </v-list>

                        </v-card>
                        <br>
                        <footer class="footer">
                            <div class="footer-container">
                                <!-- Logo -->
                                <div class="footer-logo">
                                    <h2>SneakerShop</h2>
                                </div>
                                <!-- Enlaces -->
                                <ul class="footer-links">
                                    <li>
                                        <RouterLink to="/">Inicio</RouterLink>
                                    </li>
                                    <li>
                                        <RouterLink to="/coleccion">Colección</RouterLink>
                                    </li>
                                    <li>
                                        <RouterLink to="/contacto">Contacto</RouterLink>
                                    </li>
                                </ul>

                                <!-- Redes sociales -->
                                <div class="footer-social">
                                    <a href="#"><i class="fab fa-instagram"></i></a>
                                    <a href="#"><i class="fab fa-tiktok"></i></a>
                                    <a href="#"><i class="fab fa-twitter"></i></a>
                                </div>
                            </div>

                            <div class="footer-bottom">
                                <p>© 2025 SneakerShop. Todos los derechos reservados.</p>
                            </div>
                        </footer>
                    </v-window-item>

                    <!-- TOURNAMENTS -->
                    <v-window-item value="tournaments">
                        <v-card flat color="grey-darken-3" class="pa-6 text-white">

                            <h3 class="text-h5 font-weight-bold text-center">Ofertas</h3>
                            <p class="text-grey-lighten-1 text-center mb-6">Ofertas que se publicaran los proximos
                                meses
                            </p>
                            <center><v-slide-group show-arrows>
                                    <v-slide-group-item v-for="t in tournaments" :key="t.id">
                                        <v-card width="300" class="ma-3 pa-4 bg-grey-darken-4" hover
                                            @click="addOffer(t)" style="cursor: pointer;">
                                            <v-img :src="t.image" height="160" class="mb-3 rounded-lg" cover />

                                            <div class="d-flex justify-space-between align-center">
                                                <div>
                                                    <h3 class="text-h6 mb-1">{{ t.name }}</h3>
                                                    <p class="text-caption text-blue-accent-2 font-weight-bold">
                                                        {{ t.country }}
                                                    </p>
                                                </div>
                                                <v-btn icon="mdi-plus-circle" variant="text"
                                                    color="blue-accent-2"></v-btn>
                                            </div>
                                        </v-card>
                                    </v-slide-group-item>
                                </v-slide-group></center>
                                <br>
                        </v-card>
                        <footer class="footer">
                            <div class="footer-container">
                                <!-- Logo -->
                                <div class="footer-logo">
                                    <h2>SneakerShop</h2>
                                </div>
                                <!-- Enlaces -->
                                <ul class="footer-links">
                                    <li>
                                        <RouterLink to="/">Inicio</RouterLink>
                                    </li>
                                    <li>
                                        <RouterLink to="/coleccion">Colección</RouterLink>
                                    </li>
                                    <li>
                                        <RouterLink to="/contacto">Contacto</RouterLink>
                                    </li>
                                </ul>

                                <!-- Redes sociales -->
                                <div class="footer-social">
                                    <a href="#"><i class="fab fa-instagram"></i></a>
                                    <a href="#"><i class="fab fa-tiktok"></i></a>
                                    <a href="#"><i class="fab fa-twitter"></i></a>
                                </div>
                            </div>
                            <br>

                            <div class="footer-bottom">
                                <p>© 2025 SneakerShop. Todos los derechos reservados.</p>
                            </div>
                        </footer>
                    </v-window-item>

                    <!-- RANKING -->
                    <v-window-item value="ranking">
                        <v-card flat color="grey-darken-3" class="pa-6 text-white">

                            <h3 class="text-h5 font-weight-bold text-center">Categorias en Tienda Digital </h3>
                            <p class="text-grey-lighten-1 text-center mb-6">Crear categoria</p>

                            <ol style="font-size: 1.2rem;">
                                <li v-for="r in ranking" :key="r.id">
                                    <strong>{{ r.position }}. {{ r.name }}</strong> — {{ r.country }}
                                </li>
                            </ol>

                        </v-card>
                    </v-window-item>

                    <!-- PAYMENTS -->
                    <v-window-item value="payments">
                        <v-card flat color="grey-darken-4" class="pa-6 text-white">

                            <h3 class="text-h5 text-center">Pagos y Transacciones</h3>
                            <p class="text-grey-lighten-1 text-center mb-6">
                                Historial de operaciones del sistema
                            </p>

                            <v-card v-for="t in payments" :key="t.id" class="pa-4 mb-4 bg-grey-darken-3">
                                <h3 class="text-h6">Transacción #{{ t.id }}</h3>
                                <p><strong>Jugador:</strong> {{ t.player }}</p>
                                <p><strong>Monto:</strong> {{ t.amount }}</p>
                                <p><strong>Fecha:</strong> {{ t.date }}</p>
                            </v-card>

                        </v-card>
                    </v-window-item>
                    <br>
                    <!-- El encabezado de la pagina -->
                    <footer class="footer">
                        <div class="footer-container">
                            <!-- Logo -->
                            <div class="footer-logo">
                                <h2>SneakerShop</h2>
                            </div>
                            <!-- Enlaces -->
                            <ul class="footer-links">
                                <li>
                                    <RouterLink to="/">Inicio</RouterLink>
                                </li>
                                <li>
                                    <RouterLink to="/coleccion">Colección</RouterLink>
                                </li>
                                <li>
                                    <RouterLink to="/contacto">Contacto</RouterLink>
                                </li>
                            </ul>

                            <!-- Redes sociales -->
                            <div class="footer-social">
                                <a href="#"><i class="fab fa-instagram"></i></a>
                                <a href="#"><i class="fab fa-tiktok"></i></a>
                                <a href="#"><i class="fab fa-twitter"></i></a>
                            </div>
                        </div>

                        <div class="footer-bottom">
                            <p>© 2025 SneakerShop. Todos los derechos reservados.</p>
                        </div>
                    </footer>
                </v-window>

            </v-container>
        </v-main>

    </v-app>
</template>

<script>
app.component("empleado", {
    template: "#empleado",

    data() {
        return {
            drawer: false,
            selection: "dashboard",

            // 1. Campos vinculados al formulario (v-model)
            newSale: {
                descripsion: '',
                unidades: null
            },

            // 2. Lista dinámica (La tabla se genera a partir de aquí)
            salesList: [
            ],

            menu: [
                { text: "Dashboard-Ventas", icon: "mdi-view-dashboard", href: "#dashboard" },
                { text: "Inventario", icon: "mdi-account-group", href: "#players" },
                { text: "Clientes", icon: "mdi-trophy", href: "#tournaments" },
                { text: "Gráfica", icon: "mdi-chart-line", href: "#ranking" },
            ],

            slider: [
                "images/NB.jpeg",
                "images/add.jpeg",
                "images/jr.jpeg",
            ],

            players: [
                { id: 1, name: "Grafica de Ventas por mes y el nuemro de ventas que no ha ahido", country: "20-2-26", photo: "images/Grafica.png" },
            ],

            tournaments: [
                { id: 1, name: "Agregar Oferta ", country: "Agregar", image: "images/aire.jpg" },
                { id: 2, name: "Información para publicar", country: "Generar Links", image: "images/jnike.png" },
                { id: 3, name: "Menbrecia", country: "Fuente", image: "images/sumer.jpg" },
            ],

            payments: [
                { id: 1, player: "Carlos Alcaraz", amount: "$250", date: "2025-02-10" },
                { id: 2, player: "Novak Djokovic", amount: "$300", date: "2025-02-09" },
                { id: 3, player: "Jannik Sinner", amount: "$180", date: "2025-02-05" },
            ],
        };
    },

    methods: {
        // Método principal para añadir a la tabla
        addSale() {
            // Verificamos que desc no sea solo espacios y qty sea un número válido
            if (this.newSale.desc && this.newSale.desc.trim() !== '' && this.newSale.qty > 0) {

                // Añadimos el objeto a salesList
                this.salesList.push({
                    desc: this.newSale.desc,
                    qty: parseInt(this.newSale.qty)
                });

                // Reset de los inputs
                this.newSale.desc = '';
                this.newSale.qty = null;

                console.log("Producto guardado correctamente en salesList");
            } else {
                console.warn("Faltan datos para agregar el producto");
            }
        },

        // Eliminar producto por su índice
        removeSale(index) {
            this.salesList.splice(index, 1);
        },

        logout() {
            window.location.href = "/login";
        }
    }
});
</script>

<style>
/* GENERAL */
body {
    background-color: #121212;
    color: #fff;
    font-family: 'Roboto', sans-serif;
}

/* APP BAR */
.v-app-bar {
    background-color: #212121 !important;
}

.v-app-bar h2 {
    display: flex;
    align-items: center;
}

.v-app-bar v-icon {
    margin-left: 5px;
}

/* DRAWER */
.v-navigation-drawer {
    background-color: #212121 !important;
    color: #fff;
}

.v-navigation-drawer .v-list-item {
    color: #fff;
}

.v-navigation-drawer .v-list-item:hover {
    background-color: #424242;
}

/* SLIDER */
.v-carousel .v-carousel-item {
    border-radius: 12px;
    overflow: hidden;
}

.v-carousel .v-img {
    object-fit: cover;
}

/* TABS */
.v-tabs {
    background-color: #1e1e1e !important;
}

.v-tab {
    color: #fff !important;
}

.v-tab--active {
    color: #69f0ae !important;
    font-weight: bold;
}

/* CARDS */
.v-card {
    border-radius: 12px;
}

.v-card.bg-grey-darken-4 {
    background-color: #1c1c1c !important;
}

.v-card.bg-grey-darken-3 {
    background-color: #2c2c2c !important;
}

.v-card.text-white {
    color: #fff !important;
}

/* LIST ITEMS */
.v-list-item {
    border-radius: 8px;
    margin-bottom: 4px;
    transition: background-color 0.3s;
}

.v-list-item:hover {
    background-color: #424242;
}

/* SLIDE GROUP */
.v-slide-group-item .v-card {
    transition: transform 0.3s;
}

.v-slide-group-item .v-card:hover {
    transform: scale(1.05);
}

/* RANKING */
ol {
    padding-left: 1.5rem;
}

ol li {
    margin-bottom: 0.6rem;
    color: #cfcfcf;
}

/* PAYMENTS */
.v-card.pa-4 {
    background-color: #2c2c2c !important;
}

/* TEXT STYLES */
.text-white {
    color: #fff !important;
}

.text-grey-lighten-1 {
    color: #bdbdbd !important;
}

/* RESPONSIVE */
@media (max-width: 960px) {
    .v-app-bar h2 {
        font-size: 1.2rem;
    }

    .v-card {
        font-size: 0.9rem;
    }
}

/* stylos de footer*/
.footer {
    background-color: #0f0f0f;
    color: #f2f2f2;
    padding: 40px 20px 20px;
    font-family: 'Poppins', sans-serif;
}

.footer-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 20px;
}

.footer-logo h2 {
    font-size: 1.6rem;
    color: #fff;
    letter-spacing: 1.5px;
}

.footer-links {
    list-style: none;
    display: flex;
    gap: 25px;
    padding: 0;
    margin: 0;
}

.footer-links a {
    color: #aaa;
    text-decoration: none;
    font-size: 0.95rem;
    transition: color 0.3s ease;
}

.footer-links a:hover {
    color: #ff4747;
}

.footer-social {
    display: flex;
    gap: 15px;
}

.footer-social a {
    color: #fff;
    font-size: 1.2rem;
    transition: color 0.3s ease;
}

.footer-social a:hover {
    color: #ff4747;
}

.footer-bottom {
    border-top: 1px solid #333;
    margin-top: 25px;
    padding-top: 15px;
    text-align: center;
    font-size: 0.85rem;
    color: #777
}

/* Estilos generales para el Dashboard de Empleado */

/* 1. Personalización de las Tablas */
.v-table {
    background-color: transparent !important;
}

.v-table thead {
    background-color: rgba(0, 0, 0, 0.2);
    /* Fondo sutil para el encabezado */
}

.v-table th {
    font-weight: 700 !important;
    text-transform: uppercase;
    letter-spacing: 1px;
    font-size: 0.75rem !important;
    border-bottom: 2px solid #424242 !important;
}

/* 2. Efecto Hover en las filas (se iluminan al pasar el mouse) */
.v-table tbody tr {
    transition: background-color 0.3s ease;
}

.v-table tbody tr:hover {
    background-color: rgba(255, 255, 255, 0.05) !important;
}

/* 3. Estilo para los campos de texto (Inputs) */
.v-text-field .v-field {
    border-radius: 12px !important;
    transition: transform 0.2s ease;
}

.v-text-field .v-field--focused {
    transform: translateY(-2px);
    /* Pequeño levante al escribir */
}

/* 4. Botones con efectos de pulsación */
.v-btn {
    text-transform: none !important;
    /* Quita las mayúsculas forzadas */
    font-weight: 600;
    letter-spacing: 0.5px;
    transition: all 0.3s ease !important;
}

.v-btn:hover {
    filter: brightness(1.2);
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.3);
}

.v-btn:active {
    transform: scale(0.95);
}

/* 5. Tarjetas (Cards) con bordes suaves */
.rounded-xl {
    border: 1px solid rgba(255, 255, 255, 0.1);
    /* Un borde muy fino para dar relieve */
}

/* 6. Barra de desplazamiento (Scrollbar) personalizada */
::-webkit-scrollbar {
    width: 8px;
}

::-webkit-scrollbar-track {
    background: #212121;
}

::-webkit-scrollbar-thumb {
    background: #424242;
    border-radius: 10px;
}

::-webkit-scrollbar-thumb:hover {
    background: #616161;
}

/* 7. Animación para cuando aparece un producto nuevo en la tabla */
@keyframes fadeInRow {
    from {
        opacity: 0;
        transform: translateY(10px);
    }

    to {
        opacity: 1;
        transform: translateY(0);
    }
}

.v-table tbody tr {
    animation: fadeInRow 0.5s ease forwards;
}



/* --- Estilos para la Tabla de Productos --- */
.v-table {
    background-color: transparent !important;
}

/* Encabezados de la tabla con estilo moderno */
.v-table thead th {
    font-weight: 800 !important;
    text-transform: uppercase;
    letter-spacing: 1px;
    font-size: 0.8rem !important;
    color: #111111 !important;
    /* Color blue-accent-2 */
    border-bottom: 2px solid #424242 !important;
}

/* Efecto hover en las filas: se iluminan al pasar el mouse */
.v-table tbody tr {
    transition: background-color 0.2s ease;
}

.v-table tbody tr:hover {
    background-color: rgba(255, 255, 255, 0.05) !important;
}

/* Estilo para las celdas */
.v-table td {
    border-bottom: 1px solid #424242 !important;
    padding: 12px 16px !important;
}

/* --- Estilos para los Inputs (Campos de Texto) --- */
.v-text-field .v-field {
    border-radius: 12px !important;
    transition: all 0.3s ease !important;
}

/* Efecto de elevación cuando el usuario hace clic en el input */
.v-text-field .v-field--focused {
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.5) !important;
}

/* --- Estilos para Botones y Chips --- */
.v-btn {
    text-transform: none !important;
    /* Quita las mayúsculas automáticas */
    font-weight: bold;
    border-radius: 8px !important;
}

/* Animación sutil al presionar el botón Agregar */
.v-btn:active {
    transform: scale(0.95);
}

.v-chip {
    font-weight: 700 !important;
}

/* --- Scrollbar personalizada para que combine con el tema oscuro --- */
::-webkit-scrollbar {
    width: 8px;
}

::-webkit-scrollbar-track {
    background: #212121;
}

::-webkit-scrollbar-thumb {
    background: #424242;
    border-radius: 10px;
}

::-webkit-scrollbar-thumb:hover {
    background: #616161;
}

/* --- Animación de entrada para nuevas filas --- */
@keyframes slideIn {
    from {
        opacity: 0;
        transform: translateX(-10px);
    }

    to {
        opacity: 1;
        transform: translateX(0);
    }
}

.v-table tbody tr {
    animation: slideIn 0.4s ease-out forwards;
}
</style>