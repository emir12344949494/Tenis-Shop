<template id="homepage">
  <v-app class="bg-grey-darken-4">
    <!-- App Bar -->
    <v-app-bar color="grey-darken-4" flat>
      <!-- Botón para abrir/cerrar el menú lateral -->
      <v-btn icon class="d-lg-none d-xl-flex" @click.stop="drawer = !drawer">
        <v-icon>mdi-menu</v-icon>
      </v-btn>

      <!-- Título de la app -->
      <h2 class="text-white font-weight-bold ml-4 d-flex align-center">
        SneakerShop&nbsp;&nbsp;<v-icon>mdi-shoe-sneaker</v-icon>
      </h2>
      <v-spacer></v-spacer>
    </v-app-bar>
    <!-- Drawer lateral -->
    <v-navigation-drawer v-model="drawer" app temporary color="grey-darken-4">
      <v-list nav>
        <v-list-item v-for="mnu in menu" :prepend-icon="mnu.icon" :title="mnu.text" :href="mnu.href" link></v-list-item>
      </v-list>

      <v-list nav>
        <v-list-item prepend-icon="mdi-home" title="Inicio" @click="selection = 'venta'; drawer = false" />
      </v-list>
      <v-list nav>
        <v-list-item prepend-icon="mdi-information" title="Clientes" @click="selection = 'balances'; drawer = false" />
      </v-list>
      
    </v-navigation-drawer>
    <!-- Contenido principal -->
    <v-main class="pa-6">
      <v-container>
        <br />
        <h1 class="text-white font-weight-bold mb-2">Ventas de sneakers</h1>
        <p class="text-grey-lighten-1 mb-6">
          Una excelente manera de generar toda la motivación que necesitas para ponerte en forma
        </p>
        <!-- Slider de imágenes -->
        <v-carousel cycle height="500" hide-delimiters class="mt-8">
          <v-carousel-item v-for="(img, i) in sliderImages" :key="i">
            <v-img :src="img" class="fill-height"></v-img>
          </v-carousel-item>
        </v-carousel>
        <br></br>
        <!-- Tablas -->
        <v-tabs v-model="selection" color="deep-purple-accent-4" class="mb-6" grow>
          <v-tab value="venta">Productos</v-tab>
          <v-tab value="nuevos">Sneakers a la venta</v-tab>
          <v-tab value="reventa">Reventa</v-tab>
          <v-tab value="balances">Clientes</v-tab>
          <v-tab value="transacciones">Carrito</v-tab>
        </v-tabs>

        <!-- venta,  shoes  -->
        <v-window v-model="selection">
          <v-window-item value="venta">
            <v-card flat color="grey-darken-3" class="pa-6 text-white">
              <h3 class="text-h5 font-weight-bold mb-2 text-center">Nuevos Productos</h3>
              <p class="text-grey-lighten-1 mb-8 text-center">
                Los nuevos tenis que están en el mercado
              </p>

              <!-- Carrusel tipo tienda -->
              <v-slide-group show-arrows class="pa-4">
                <v-slide-group-item v-for="(item, index) in shoes" :key="index">
                  <v-card color="grey-darken-4" width="340" class="ma-3 pa-3 rounded-lg hover-scale">
                    <v-img :src="item.image" height="220" cover class="rounded-lg mb-3"></v-img>

                    <v-card-title class="text-white text-h6 font-weight-bold">
                      {{ item.marque }}
                    </v-card-title>

                    <v-card-subtitle class="text-grey-lighten-1">
                      {{ item.seller }} — {{ item.location }}
                    </v-card-subtitle>

                    <v-card-text class="text-white">
                      <div><strong>Precio:</strong> {{ item.price }}</div>
                      <div><strong>Publicado:</strong> {{ item.date }}</div>
                    </v-card-text>

                    <v-card-actions>
                      <v-btn color="green-accent-4" variant="tonal" text @click="comprar(item)">
                        <v-icon start>mdi-cart</v-icon> Comprar
                      </v-btn>
                      <v-spacer></v-spacer>
                    </v-card-actions>
                  </v-card>
                </v-slide-group-item>
              </v-slide-group>
              <v-spacer></v-spacer>
              <v-space></v-space>
              <br>
              <p class="text-grey-lighten-1 mb-8 text-center">
                LAS NOTICIAS DEL MOMENTO
              </p>
              <br>
              <v-slide-group show-arrows class="pa-4">
                <v-slide-group-item v-for="(item, index) in banda" :key="index">
                  <v-card color="grey-darken-4" width="390" height="580" class="ma-3 pa-3 rounded-lg hover-scale">
                    <v-img :src="item.image" height="440" cover class="rounded-lg mb-3"></v-img>

                    <v-card-title class="text-white text-h6 font-weight-bold">
                      {{ item.marque }}
                    </v-card-title>
                    <v-card-actions>
                      <v-btn color="green-accent-4" variant="tonal" text @click="info(item)">
                        <v-icon start>open-in-new</v-icon> Info
                      </v-btn>
                      <v-spacer></v-spacer>
                    </v-card-actions>
                  </v-card>
                </v-slide-group-item>
              </v-slide-group>
              <p class="text-grey-lighten-1 mb-8 text-center">
                Prepara tu carrito
              </p>
              <v-slide-group show-arrows class="pa-4">
                <v-slide-group-item v-for="(item, index) in delta" :key="index">
                  <v-card color="grey-darken-4" width="340" class="ma-3 pa-3 rounded-lg hover-scale">
                    <v-img :src="item.image" height="220" cover class="rounded-lg mb-3"></v-img>

                    <v-card-title class="text-white text-h6 font-weight-bold">
                      {{ item.marque }}
                    </v-card-title>

                    <v-card-subtitle class="text-grey-lighten-1">
                      {{ item.seller }} — {{ item.location }}
                    </v-card-subtitle>

                    <v-card-text class="text-white">
                      <div><strong>Precio:</strong> {{ item.price }}</div>
                      <div><strong>Publicado:</strong> {{ item.date }}</div>
                    </v-card-text>

                    <v-card-actions>
                      <v-btn color="green-accent-4" variant="tonal" text @click="comprar(item)">
                        <v-icon start>mdi-cart</v-icon> Comprar
                      </v-btn>
                      <v-spacer></v-spacer>
                    </v-card-actions>
                  </v-card>
                </v-slide-group-item>
              </v-slide-group>

              <v-slide-group show-arrows class="pa-4">
                <v-slide-group-item v-for="(item, index) in perro" :key="index">
                  <v-card color="grey-darken-4" width="340" class="ma-3 pa-3 rounded-lg hover-scale">
                    <v-img :src="item.image" height="220" cover class="rounded-lg mb-3"></v-img>

                    <v-card-title class="text-white text-h6 font-weight-bold">
                      {{ item.marque }}
                    </v-card-title>

                    <v-card-subtitle class="text-grey-lighten-1">
                      {{ item.seller }} — {{ item.location }}
                    </v-card-subtitle>

                    <v-card-text class="text-white">
                      <div><strong>Precio:</strong> {{ item.price }}</div>
                      <div><strong>Publicado:</strong> {{ item.date }}</div>
                    </v-card-text>

                    <v-card-actions>
                      <v-btn color="green-accent-4" variant="tonal" text @click="comprar(item)">
                        <v-icon start>mdi-cart</v-icon> Comprar
                      </v-btn>
                      <v-spacer></v-spacer>
                    </v-card-actions>
                  </v-card>
                </v-slide-group-item>
              </v-slide-group>
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
              <v-snackbar v-model="snackbar" timeout="2500" color="green-accent-4" rounded="pill">
                ✅ Has comprado: {{ productoComprado }}
              </v-snackbar>

            </v-card>
          </v-window-item>


          <v-window-item value="nuevos">
            <v-card flat color="grey-darken-3" class="pa-6 text-white">
              <h3 class="text-h5 font-weight-bold mb-2 text-center">Nuevos Productos</h3>
              <p class="text-grey-lighten-1 mb-8 text-center">
                Los nuevos tenis que están en el mercado
              </p>

              <!-- Carrusel tipo tienda -->
              <v-slide-group show-arrows class="pa-4">
                <v-slide-group-item v-for="(item, index) in suner" :key="index">
                  <v-card color="grey-darken-4" width="340" class="ma-3 pa-3 rounded-lg hover-scale">
                    <v-img :src="item.image" height="220" cover class="rounded-lg mb-3"></v-img>

                    <v-card-title class="text-white text-h6 font-weight-bold">
                      {{ item.marque }}
                    </v-card-title>

                    <v-card-subtitle class="text-grey-lighten-1">
                      {{ item.seller }} — {{ item.location }}
                    </v-card-subtitle>

                    <v-card-text class="text-white">
                      <div><strong>Precio:</strong> {{ item.price }}</div>
                      <div><strong>Publicado:</strong> {{ item.date }}</div>
                    </v-card-text>

                    <v-card-actions>
                      <v-btn color="green-accent-4" variant="tonal" text @click="comprar(item)">
                        <v-icon start>mdi-cart</v-icon> Comprar
                      </v-btn>
                      <v-spacer></v-spacer>
                    </v-card-actions>
                  </v-card>
                </v-slide-group-item>
              </v-slide-group>
              <br></br>
              <v-slide-group show-arrows class="pa-4">
                <v-slide-group-item v-for="(item, index) in perro" :key="index">
                  <v-card color="grey-darken-4" width="340" class="ma-3 pa-3 rounded-lg hover-scale">
                    <v-img :src="item.image" height="220" cover class="rounded-lg mb-3"></v-img>

                    <v-card-title class="text-white text-h6 font-weight-bold">
                      {{ item.marque }}
                    </v-card-title>

                    <v-card-subtitle class="text-grey-lighten-1">
                      {{ item.seller }} — {{ item.location }}
                    </v-card-subtitle>

                    <v-card-text class="text-white">
                      <div><strong>Precio:</strong> {{ item.price }}</div>
                      <div><strong>Publicado:</strong> {{ item.date }}</div>
                    </v-card-text>

                    <v-card-actions>
                      <v-btn color="green-accent-4" variant="tonal" text @click="comprar(item)">
                        <v-icon start>mdi-cart</v-icon> Comprar
                      </v-btn>
                      <v-spacer></v-spacer>
                    </v-card-actions>
                  </v-card>
                </v-slide-group-item>
                <br></br>
              </v-slide-group>

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

              <!-- Snackbar de confirmación -->
              <v-snackbar v-model="snackbar" timeout="2500" color="green-accent-4" rounded="pill">
                ✅ Has comprado: {{ productoComprado }}
              </v-snackbar>
            </v-card>
          </v-window-item>

          <!-- resales -->

          <v-window-item value="reventa">
            <v-card flat color="grey-darken-3" class="pa-6 text-white">
              <h3 class="text-h5 font-weight-bold mb-2 text-center">Productos en reventa </h3>
              <p class="text-grey-lighten-1 mb-8 text-center">
                Los nuevos tenis que están en el mercado por reventa de los clientes
              </p>

              <!-- Carrusel tipo tienda -->
              <v-slide-group show-arrows class="pa-4">
                <v-slide-group-item v-for="(item, index) in resales" :key="index">
                  <v-card color="grey-darken-4" width="340" class="ma-3 pa-3 rounded-lg hover-scale">
                    <v-img :src="item.image" height="220" cover class="rounded-lg mb-3"></v-img>

                    <v-card-title class="text-white text-h6 font-weight-bold">
                      {{ item.marque }}
                    </v-card-title>

                    <v-card-subtitle class="text-grey-lighten-1">
                      {{ item.seller }} — {{ item.location }}
                    </v-card-subtitle>

                    <v-card-text class="text-white">
                      <div><strong>Precio:</strong> {{ item.price }}</div>
                      <div><strong>Publicado:</strong> {{ item.date }}</div>
                    </v-card-text>

                    <v-card-actions>
                      <v-btn color="green-accent-4" variant="tonal" text @click="comprar(item)">
                        <v-icon start>mdi-cart</v-icon> Comprar
                      </v-btn>
                      <v-spacer></v-spacer>
                    </v-card-actions>
                  </v-card>
                </v-slide-group-item>
              </v-slide-group>

              <!-- Snackbar de confirmación -->
              <v-snackbar v-model="snackbar" timeout="2500" color="green-accent-4" rounded="pill">
                ✅ Has comprado: {{ productoComprado }}
              </v-snackbar>
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
            </v-card>
          </v-window-item>

          <!-- TAB BALANCES-->
          <v-window-item value="balances">
            <v-card flat color="grey-darken-3" class="pa-6 text-white">
              <h3 class="text-h5 font-weight-bold mb-4 text-center">Clientes</h3>
              <p class="text-grey-lighten-1 text-center mb-6">Nuestros principales clientes</p>
              <v-list lines="two" class="bg-grey-darken-4 rounded-lg">
                <v-list-item v-for="(client, index) in clients" :key="index" class="py-3">
                  <v-list-item-avatar size="48">
                    <v-img :src="client.photo" cover></v-img>
                  </v-list-item-avatar>
                  <v-list-item-content>
                    <v-list-item-title class="text-white font-weight-bold">
                      {{ client.name }}
                    </v-list-item-title>
                    <v-list-item-subtitle class="text-grey-lighten-1">
                      {{ client.feedback }}
                    </v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
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
            </v-card>
          </v-window-item>

          <!-- TAB TRANSACCIONES  -->

          <v-window-item value="transacciones">
            <v-card flat color="grey-darken-4" class="pa-8 text-white rounded-xl elevation-3 d-print-none">
              <h2 class="text-h5 mb-2 text-center">Historial de Pagos</h2>
              <p class="text-subtitle-1 text-center mb-6 text-grey-lighten-1">
                Consulta los tickets generados y descarga tus reportes detallados.
              </p>
              <v-divider class="mb-8 opacity-20"></v-divider>

              <v-row v-if="tickets.length">
                <v-col cols="12" md="6" v-for="(ticket, index) in tickets" :key="index">
                  <v-card class="pa-5 bg-grey-darken-3 rounded-lg border-s-xl border-blue-accent-2" flat>
                    <div class="d-flex justify-space-between align-start mb-4">
                      <div>
                        <span class="text-overline text-blue-accent-2">Folio de Venta</span>
                        <h3 class="text-h6">#{{ ticket.id || (index + 1001) }}</h3>
                      </div>
                      <v-chip size="small" color="grey-lighten-1" variant="outlined">
                        <v-icon start size="14">mdi-calendar</v-icon>
                        {{ ticket.fecha }}
                      </v-chip>
                    </div>

                    <div class="mb-4">
                      <div class="d-flex justify-space-between mb-1">
                        <span class="text-grey">Cliente:</span>
                        <span class="font-weight-bold">{{ ticket.cliente }}</span>
                      </div>
                      <div class="d-flex justify-space-between mb-1">
                        <span class="text-grey">Producto:</span>
                        <span>{{ ticket.producto }}</span>
                      </div>
                      <div class="d-flex justify-space-between">
                        <span class="text-grey">Método:</span>
                        <span class="text-capitalize">{{ ticket.tipo }}</span>
                      </div>
                    </div>

                    <v-divider class="mb-3 opacity-10"></v-divider>

                    <div class="d-flex justify-space-between align-center">
                      <span class="text-h6">Monto Total:</span>
                      <span class="text-h5 font-weight-black text-green-accent-3">
                        ${{ ticket.precio }}
                      </span>
                    </div>
                  </v-card>
                </v-col>
              </v-row>

              <div v-else class="text-center py-12 border-dashed border-grey-darken-2 rounded-xl">
                <v-icon size="80" color="grey-darken-2" class="mb-4">mdi-ticket-off-outline</v-icon>
                <h3 class="text-h6 text-grey-darken-1">No hay tickets generados aún</h3>
              </div>

              <div class="d-flex justify-center mt-8">
                <v-btn color="red-accent-3" prepend-icon="mdi-file-pdf-box" variant="flat" size="large"
                  @click="downloadPDF" :disabled="tickets.length === 0" class="px-8 rounded-pill">
                  Descargar Ticket de venta (PDF)
                </v-btn>
              </div>
            </v-card>


            <div id="print-area-final" class="printable-content">
              <div class="ticket-wrapper">
                <div class="text-center mb-6">
                  <h1 class="ticket-title">HIGHTECHNOLOGY SNEAKERS</h1>
                  <p class="ticket-subtitle">Ticket de Venta</p>
                  <div class="divider-black"></div>
                </div>

                <table class="ticket-table">
                  <thead>
                    <tr>
                      <th>Producto / Marca</th>
                      <th class="text-center">Fecha</th>
                      <th class="text-right">Precio</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="(t, index) in tickets" :key="index">
                      <td>{{ t.marque }}</td>
                      <td class="text-center">{{ t.date }}</td>
                      <td class="text-right font-bold">{{ t.price }}</td>
                    </tr>
                  </tbody>
                </table>
                <div class="total-section">
                  <div class="total-box">
                    <span>TOTAL A PAGAR</span>
                    <h2>${{ calcularTotal() }}</h2>
                  </div>
                </div>
                <div class="notes-section">
                  <p><strong>Nota Informativa:</strong></p>
                  <p>Este documento sirve como resumen administrativo de las ventas realizadas. Los precios incluyen
                    IVA. Gracias por su confianza en Hightechnology.</p>
                  <p class="mt-4 text-center"><strong>VUELVA PRONTO</strong></p>
                </div>
              </div>
            </div>

          </v-window-item>
        </v-window>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
app.component("homepage", {
  template: "#homepage",

  data() {
    return {
      selection: "venta",
      drawer: null,
      snackbar: false,
      productoComprado: "",
      tickets: [],
      sliderImages: [
        "images/zy.jpeg",
        "images/nk.jpeg",
        "images/NB.jpeg",
        "images/add.jpeg"
      ],

      menu: [
        { text: 'home', href: '/' },
        { icon: 'mdi-account', text: 'login-user', href: 'login' },
        { text: 'retro', text: 'registro de cuenta', href: 'registro' },
      ],

      // --- Datos de ejemplo ---
      clients: [
        { name: "Carlos Méndez", feedback: "Excelente servicio y entregas puntuales.", photo: "img/user1.jpg" },
        { name: "Rocio Guzman", feedback: "La calidad del producto es excepcional.", photo: "img/user2.jpg" },
        { name: "Luis Ortega", feedback: "Atención al cliente rápida y confiable.", photo: "img/user3.jpg" },
        { name: "Alejandro Montolla", feedback: "El producto es de calidad.", photo: "img/user3.jpg" },
        { name: "Mariel Caballero", feedback: "Los modelos son muy bonitos.", photo: "img/user3.jpg" },
        { name: "Laura García", feedback: "Atención al cliente rápida y confiable.", photo: "img/user3.jpg" },
        { name: "Maria Dolores", feedback: "Atención al cliente rápida y confiable.", photo: "img/user3.jpg" },
      ],

      // --- Productos principales (Nike) ---
      shoes: [
        { image: "images/1.jpg", price: "$465", date: "02.12.2021, 3:34 AM", marque: "Nike SB Dunk" },
        { image: "images/2.jpg", price: "$714", date: "02.12.2021, 4:12 AM", marque: "Air Force 1" },
        { image: "images/3.jpg", price: "$982", date: "02.12.2021, 6:24 AM", marque: "Nike Zoom" },
        { image: "images/4.jpg", price: "$340", date: "02.12.2021, 7:51 AM", marque: "Nike Air-Max MN" },
        { image: "images/6.jpg", price: "$124", date: "02.12.2021, 7:51 AM", marque: "Nike Blazer" },
        { image: "images/8.jpg", price: "$94", date: "02.12.2021, 7:51 AM", marque: "Nike Cortez" },
        { image: "images/9.jpg", price: "$410", date: "02.12.2021, 7:51 AM", marque: "Nike P-6000" },
        { image: "images/12.jpg", price: "$410", date: "02.12.2021, 7:51 AM", marque: "Nike LD-1000" },
      ],

      // --- Productos Suner (Adidas/Yeezy) ---
      suner: [
        { image: "images/6.png", price: "$630", marque: "Yeezy 350", date: "03.17.2025" },
        { image: "images/01.png", price: "$560", marque: "Adidas Zamba-OG", date: "05.10.2025" },
        { image: "images/03.png", price: "$630", marque: "Yeezy 700 V2", date: "03.17.2025" },
        { image: "images/02.png", price: "$420", marque: "Adidas Adizero EVO", date: "04.28.2025" },
        { image: "images/8.png", price: "$420", marque: "Adidas Response CL", date: "04.28.2025" },
        { image: "images/13.png", price: "$420", marque: "Adidas Zamba-blue", date: "04.28.2025" },
        { image: "images/9.png", price: "$420", marque: "Yeezy 750 v2", date: "04.28.2025" },
      ],

      // --- Productos Resales ---
      resales: [
        { image: "images/15.jpg", price: "$630", marque: "Jordan 1 mid", seller: "Artix Shop", location: "Colombia", date: "03.17.2025" },
        { image: "images/20.jpg", price: "$560", marque: "Adidas superstart", seller: "Juan Perez", location: "México", date: "05.10.2025" },
        { image: "images/17.jpg", price: "$630", marque: "Air Force 1", seller: "Sellsneahsers Shop", location: "Colombia", date: "03.17.2025" },
        { image: "images/21.jpg", price: "$420", marque: "Adidas Zamba-green", seller: "SneakerMX", location: "España", date: "04.28.2025" },
        { image: "images/18.jpg", price: "$630", marque: "Jordan 1 low", seller: "Re-Sell Shop", location: "Colombia", date: "03.17.2025" },
        { image: "images/23.jpg", price: "$630", marque: "Adidas forum", seller: "Re-Sell Shop", location: "Colombia", date: "03.17.2025" },
      ],

      banda: [
        { image: "images/jnike.png", marque: "Streetwear" },
        { image: "images/todos.jpg", marque: "Forums nike" },
        { image: "images/street.jpg", marque: "The news" },
        { image: "images/patta.jpg", marque: "Colaboracion patta" },
        { image: "images/rojos.jpg", marque: "Only AIR" },
        { image: "images/sumer.jpg", marque: "Runer" }, ,
      ],

      delta: [
        { image: "images/gh.jpg", price: "$350", marque: "Jordan 1 higt-purple", seller: "Shoes Shop", location: "Argentina", date: "03.17.2025" },
        { image: "images/sb.jpg", price: "$120", marque: "Nike sb-dunk", seller: "Delta-shoes", location: "México-MTY", date: "05.11.2025" },
        { image: "images/air.jpg", price: "$730", marque: "Air max centinel", seller: "Sell Shop", location: "USA-Texas", date: "03.7.2025" },
        { image: "images/gtx.jpg", price: "$420", marque: "Nike Air Force 1 GORE-TEX", seller: "SneakerMX", location: "España", date: "09.8.2025" },
        { image: "images/og.jpg", price: "$210", marque: "Nike Off Noir and Gum ", seller: "Re-Sell Shop", location: "Chile", date: "01.1.2025" },
        { image: "images/verde.jpg", price: "$130", marque: "Nike Fir-phantom", seller: "Class Shop", location: "Brazil", date: "04.5.2025" },
      ],

      perro: [
        { image: "images/740.jpg", price: "$320", marque: "Adidas Yeezy 350 V2 ", seller: "MTY-shop", location: "USA-california", date: "03.7.2025" },
        { image: "images/700.jpg", price: "$720", marque: "Adidas Yeezy 700", seller: "CRShoes", location: "Colombia", date: "05.12.2025" },
        { image: "images/500.jpg", price: "$800", marque: "Adidas Yeezy 500", seller: "Sell Shop", location: "USA-Texas", date: "02.2.2025" },
        { image: "images/adoi.jpg", price: "$520", marque: "Adidas Yeezy Boost 350 V2", seller: "Acorruña.ES", location: "España", date: "09.3.2025" },
        { image: "images/gw.jpg", price: "$910", marque: "Adidas Yeezy 700 V3 ", seller: "CH-tenis", location: "Chile", date: "01.9.2025" },
        { image: "images/350.jpg", price: "$1030", marque: "Adidas Yeezy 700 “Wash Orange” ", seller: "Class Shop", location: "Mexico", date: "04.5.2025" },
      ],
    };
  },

  methods: {
    // Realizar una compra, generar ticket y GUARDAR en MongoDB Atlas
    async comprar(item) {
      const nuevoTicket = {
        id: this.tickets.length + 1,
        cliente: "Cliente " + (this.tickets.length + 1),
        fecha: new Date().toLocaleString(),
        producto: item.marque,
        precio: item.price,
        tipo: this.selection,
      };

      try {
        // Enviamos el ticket al endpoint de Javalin que configuramos
        const response = await fetch('/api/auth/step1', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            email: "usuario@uttt.edu.mx", // Puedes dinamizar esto con el login
            ticket: nuevoTicket
          })
        });

        const result = await response.json();

        if (result.status === "success") {
          // Si el servidor confirma el guardado en Atlas, actualizamos la UI
          this.tickets.push(nuevoTicket);
          this.productoComprado = `${item.marque} por ${item.price}`;
          this.snackbar = true;
          console.log("Sincronizado con Atlas:", result.message);
        } else {
          alert("Error al guardar en la base de datos");
        }
      } catch (error) {
        console.error("Error de conexión:", error);
        alert("No se pudo conectar con el servidor Javalin. Revisa la terminal.");
      }
    },

    imprimirTicket() {
      console.log("Preparando vista de impresión...");
      window.print();
    },

    downloadPDF() {
      if (this.tickets.length === 0) return alert("No hay datos");
      window.dispatchEvent(new Event('resize'));
      setTimeout(() => {
        window.print();
      }, 250);
    },

    calcularTotal() {
      return this.tickets.reduce((sum, t) => {
        const val = parseFloat(String(t.price || t.precio).replace(/[$,]/g, '')) || 0;
        return sum + val;
      }, 0).toFixed(2);
    },

    addSale() {
      if (this.newSale.desc && this.newSale.desc.trim() !== '' && this.newSale.qty > 0) {
        this.salesList.push({
          desc: this.newSale.desc,
          qty: parseInt(this.newSale.qty)
        });
        this.newSale.desc = '';
        this.newSale.qty = null;
      }
    },

    addOffer(offer) {
      this.salesList.push({
        desc: offer.name,
        qty: 1
      });
    },

    removeSale(index) {
      this.salesList.splice(index, 1);
    },

    logout() {
      window.location.href = "/login";
    }
  },
});
</script>

<style>
.v-application {
  background-color: #121212 !important;
}

.v-card {
  border-radius: 15px !important;
}

.v-toolbar-title {
  font-weight: bold;
}

.d-none d-sm-flex {
  width: 800px;
}

.tickets-container {
  max-height: 400px;
  overflow-y: auto;
  scrollbar-width: thin;
  scrollbar-color: #4caf50 #2c2c2c;
}

.tickets-container::-webkit-scrollbar {
  width: 8px;
}

.tickets-container::-webkit-scrollbar-thumb {
  background-color: #4caf50;
  border-radius: 4px;
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


/* Ocultar en la web normal para no ensuciar la interfaz */
#print-area {
  display: none;
}


/* Ocultar en la pantalla normal */
#print-area-final {
  display: none;
}

@media print {

  /* 1. OCULTAR TODO LO QUE NO SEA EL TICKET */
  html,
  body,
  .v-application,
  .v-main,
  .v-window,
  .v-card {
    visibility: hidden !important;
    height: auto !important;
    overflow: visible !important;
    background: white !important;
  }

  /* 2. MOSTRAR EL TICKET Y POSICIONARLO ARRIBA */
  #print-area-final,
  #print-area-final * {
    visibility: visible !important;
  }

  #print-area-final {
    display: block !important;
    position: absolute !important;
    left: 0 !important;
    top: 0 !important;
    width: 100% !important;
    background: white !important;
    color: black !important;
    z-index: 99999 !important;
  }

  /* Estilos de tabla manuales (sin depender de Vuetify) */
  .ticket-table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
    color: black !important;
  }

  .ticket-table th,
  .ticket-table td {
    border: 1px solid black !important;
    padding: 8px;
    text-align: left;
  }

  .ticket-title {
    font-size: 24pt;
    font-weight: bold;
  }

  .divider-black {
    border-bottom: 2px solid black;
    margin: 10px 0;
  }

  .total-box {
    border: 2px solid black;
    padding: 10px;
    margin-top: 20px;
    text-align: right;
  }

  /* Eliminar encabezados y pies de página del navegador (URL, Fecha) */
  @page {
    margin: 1cm;
  }
}
</style>
