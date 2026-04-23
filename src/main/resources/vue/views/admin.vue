<template  id="admin">
  <v-app class="bg-grey-darken-4">

    <!-- APP BAR -->
    <v-app-bar color="grey-darken-4" flat>
      <v-btn icon class="d-lg-none" @click.stop="drawer = !drawer">
        <v-icon>mdi-menu</v-icon>
      </v-btn>

      <h2 class="text-white font-weight-bold ml-4 d-flex align-center">
        Sneaker-Shop-Admin&nbsp;&nbsp;<v-icon>mdi-tennis"></v-icon>
      </h2>

      <v-spacer></v-spacer>

      <v-btn icon color="white" @click="logout">
        <v-icon>mdi-logout</v-icon>
      </v-btn>
    </v-app-bar>

    <!-- DRAWER -->
    <v-navigation-drawer v-model="drawer" app temporary color="grey-darken-4">
      <v-list nav>
        <v-list-item
          v-for="mnu in menu"
          :key="mnu.text"
          :prepend-icon="mnu.icon"
          :title="mnu.text"
          link
          :href="mnu.href"
        />
      </v-list>

      <v-divider class="my-4" />

      <v-list nav>
        <v-list-item prepend-icon="mdi-home" title="Inicio" @click="selection = 'dashboard'; drawer = false" />
      </v-list>

      <v-list nav>
        <v-list-item prepend-icon="mdi-account-group" title="Jugadores" @click="selection = 'players'; drawer = false" />
      </v-list>

      <v-list nav>
        <v-list-item prepend-icon="mdi-trophy" title="Torneos" @click="selection = 'tournaments'; drawer = false" />
      </v-list>

      <v-list nav>
        <v-list-item prepend-icon="mdi-chart-line" title="Ranking" @click="selection = 'ranking'; drawer = false" />
      </v-list>
    </v-navigation-drawer>

    <!-- CONTENIDO -->
    <v-main class="pa-6">
      <v-container>
         <br>
        <!-- TITULO -->
        <h1 class="text-white font-weight-bold mb-2">Panel de Tenis – Administrador</h1>
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
          <v-tab value="dashboard">Dashboard-Ventas</v-tab>
          <v-tab value="players">Sneakers-pulbicar</v-tab>
          <v-tab value="tournaments">Multimedios</v-tab>
          <v-tab value="ranking">Clientes</v-tab>
          <v-tab value="payments">Pagos</v-tab>
        </v-tabs>

        <!-- CONTENIDO DINÁMICO -->
        <v-window v-model="selection">

          <!-- DASHBOARD -->
          <v-window-item value="dashboard">
            <v-card flat color="grey-darken-3" class="pa-6 text-white">

              <h3 class="text-h5 font-weight-bold mb-2 text-center">Dashboard General</h3>
              <p class="text-grey-lighten-1 mb-8 text-center">
                Resumen rápido del sistema
              </p>

              <v-row>
                <v-col cols="12" md="4">
                  <v-card class="pa-4 bg-grey-darken-4 text-white">
                    <h3 class="text-h6">Tenis por mes</h3>
                    <p class="text-h4 font-weight-bold">124</p>
                  </v-card>
                </v-col>

                <v-col cols="12" md="4">
                  <v-card class="pa-4 bg-grey-darken-4 text-white">
                    <h3 class="text-h6">Edicion de imagen</h3>
                    <p class="text-h4 font-weight-bold">6</p>
                  </v-card>
                </v-col>

                <v-col cols="12" md="4">
                  <v-card class="pa-4 bg-grey-darken-4 text-white">
                    <h3 class="text-h6">Editar codigo</h3>
                    <p class="text-h4 font-weight-bold">Sí</p>
                  </v-card>
                </v-col>
              </v-row>

            </v-card>
          </v-window-item>

          <!-- PLAYERS -->
          <v-window-item value="players">
            <v-card flat color="grey-darken-3" class="pa-6 text-white">

              <h3 class="text-h5 font-weight-bold text-center">Sneakers-pulbicar</h3>
              <p class="text-grey-lighten-1 text-center mb-6">
                Lista de los tenis que se van a publicar 
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

              <h3 class="text-h5 font-weight-bold text-center">Multimedios</h3>
              <p class="text-grey-lighten-1 text-center mb-6">Multimedios para la pagina y herramientas</p>

              <v-slide-group show-arrows>
                <v-slide-group-item v-for="t in tournaments" :key="t.id">
                  <v-card width="300" class="ma-3 pa-4 bg-grey-darken-4">
                    <v-img :src="t.image" height="160" class="mb-3 rounded-lg" />

                    <h3 class="text-h6">{{ t.name }}</h3>
                    <p class="text-grey-lighten-1">{{ t.surface }} – {{ t.country }}</p>
                  </v-card>
                </v-slide-group-item>
              </v-slide-group>
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

                <div class="footer-bottom">
                  <p>© 2025 SneakerShop. Todos los derechos reservados.</p>
                </div>
              </footer>
          </v-window-item>

          <!-- RANKING -->
          <v-window-item value="ranking">
            <v-card flat color="grey-darken-3" class="pa-6 text-white">

              <h3 class="text-h5 font-weight-bold text-center">Clientes</h3>
              <p class="text-grey-lighten-1 text-center mb-6">Clientes Destacados</p>

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

              <v-card
                v-for="t in payments"
                :key="t.id"
                class="pa-4 mb-4 bg-grey-darken-3"
              >
                <h3 class="text-h6">Transacción #{{ t.id }}</h3>
                <p><strong>Jugador:</strong> {{ t.player }}</p>
                <p><strong>Monto:</strong> {{ t.amount }}</p>
                <p><strong>Fecha:</strong> {{ t.date }}</p>
              </v-card>

            </v-card>
          </v-window-item>
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
        </v-window>

      </v-container>
    </v-main>

  </v-app>
</template>


<script>
app.component("admin", {
  template: "#admin",

  data() {
    return {
      drawer: false,
      selection: "dashboard",

      menu: [
        { text: "Dashboard-Ventas", icon: "mdi-view-dashboard", href: "#dashboard" },
        { text: "Invetario", icon: "mdi-account-group", href: "#players" },
        { text: "Clientes", icon: "mdi-trophy", href: "#tournaments" },
        { text: "", icon: "mdi-chart-line", href: "#ranking" },
      ],

      slider: [
        "images/NB.jpeg",
        "images/add.jpeg",
        "images/jr.jpeg",
      ],

      players: [
        {
          id: 1,
          name: "Nike colro verde",
          country: "20-2-26",
          photo: "images/verde.jpg"
        },
        {
          id: 2,
          name: "Jordan Street",
          country: "21-1-26",
          photo: "images/lanm.jpg"
        },
        {
          id: 3,
          name: "Nike Sb-dunk Chrome",
          country: "21-3-26",
          photo: "images/laz.jpg"
        },
      ],

      tournaments: [
        {
          id: 1,
          name: "Agregar Imagenes ",
          country: "Agregar",
          image: "images/aire.jpg"
        },
        {
          id: 2,
          name: "informacion para publicar",
          country: "Generar Links",
          image: "images/jnike.png"
        },
        {
          id: 3,
          name: "Editar-codigo",
          country: "Fuente",
          image: "images/html.jpg"
        },
      ],

      ranking: [
        { id: 1, position: 1, name: "Novak Djokovic", country: "Serbia" },
        { id: 2, position: 2, name: "Carlos Alcaraz", country: "España" },
        { id: 3, position: 3, name: "Jannik Sinner", country: "Italia" },
        { id: 4, position: 4, name: "Daniil Medvedev", country: "Rusia" },
        { id: 5, position: 5, name: "Alexander Zverev", country: "Alemania" },
      ],

      payments: [
        { id: 1, player: "Carlos Alcaraz", amount: "$250", date: "2025-02-10" },
        { id: 2, player: "Novak Djokovic", amount: "$300", date: "2025-02-09" },
        { id: 3, player: "Jannik Sinner", amount: "$180", date: "2025-02-05" },
      ],
    };
  },

  methods: {
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
</style>