<template id="login-page">
  <v-app>
    <v-main class="d-flex justify-center align-center" style="height:100vh;">
      <v-card color="grey-darken-3" class="pa-6 text-white" width="400">
        <v-card-title class="text-h6 font-weight-bold text-center">
          Iniciar Sesión
        </v-card-title>

        <v-card-text>
          <!-- Campo de usuario -->
          <div class="mb-4">
            <v-text-field v-model="loginForm.username" label="Usuario" prepend-icon="mdi-account" color="white"
              variant="outlined" />
          </div>
          <br>
          <!-- Campo de contraseña -->
          <div class="mb-4">
            <v-text-field v-model="loginForm.password" label="Contraseña" type="password" prepend-icon="mdi-lock"
              color="white" variant="outlined" />
          </div>

          <!-- Campo de rol -->
          <div>
            <v-select v-model="loginForm.role" :items="roles" label="Rol" prepend-icon="mdi-account-badge" color="white"
              variant="outlined" />
          </div>
        </v-card-text>

        <v-btn v-for="btn in delta" :key="btn.text" color="green-accent-4" rounded="pill" block class="mt-4"
          @click="verificarAcceso(btn)" :disabled="!puedeAcceder">
          {{ btn.text }}
        </v-btn>

        <br>

        <v-snackbar v-model="snackbar" timeout="3000" color="grey-darken-3">
          {{ loginMessage }}
          <template v-slot:actions>
            <v-btn variant="text" @click="snackbar = false">Cerrar</v-btn>
          </template>
        </v-snackbar>

        <br>

        <v-btn v-for="mnu in menu" :key="mnu.text" color="grey-lighten-1" variant="outlined" rounded="pill"
          :prepend-icon="mnu.icon" :href="mnu.href" block>
          REGRESO
        </v-btn>
      </v-card>
    </v-main>
  </v-app>
</template>

<script>
app.component("login-page", {
  template: "#login-page",

  data() {
    return {
      loginForm: {
        username: "",
        password: "",
        role: "",
      },

      // Definimos los arreglos que el HTML recorre con v-for
      delta: [
        { text: "INGRESAR" }
      ],

      menu: [
        { text: "REGRESO", icon: "mdi-arrow-left", href: "/" }
      ],

      roles: ["Administrador", "Empleado", "Cliente"],

      puedeAcceder: false,
      snackbar: false,
      loginMessage: "",
    };
  },

  watch: {
    loginForm: {
      handler() {
        // Habilita el botón si los campos no están vacíos
        this.puedeAcceder =
          this.loginForm.username.trim() !== "" &&
          this.loginForm.password.trim() !== "" &&
          this.loginForm.role !== "";
      },
      deep: true,
    },
  },

  methods: {
    // Ya no necesitas pasar 'btn' como parámetro porque la URL depende del ROL
    verificarAcceso() {
      const { username, password, role } = this.loginForm;

      // Base de datos simulada de usuarios
      const credenciales = {
        Administrador: { user: "admin", pass: "admin123", url: "/administrador" },
        Empleado: { user: "empleado", pass: "empleado123", url: "/Empleado" },
        Cliente: { user: "user", pass: "cliente123", url: "/cliente" }
      };

      const cuenta = credenciales[role];

      // Verificamos si las credenciales coinciden con el rol seleccionado
      if (cuenta && username === cuenta.user && password === cuenta.pass) {
        this.loginMessage = `¡Bienvenido ${role}! Accediendo...`;
        this.snackbar = true;

        // Redirección basada en la URL del objeto credenciales
        setTimeout(() => {
          window.location.href = cuenta.url;
        }, 1000);

      } else {
        this.loginMessage = "Error: Usuario, contraseña o rol incorrectos";
        this.snackbar = true;
      }
    },
  },
});
</script>


<style scoped>
.v-application {
  background-color: #121212 !important;
}

.v-card {
  border-radius: 15px !important;
}

.v-card-title {
  text-align: center;
}

.v-btn {
  background-color: #4caf50;
  border-radius: 4px;
  width: 120px;
}

.v-list-item {
  background-color: #4caf50;
  color: #cfcfcf;
  border-radius: 30px;
  margin: 6px 0;
  transition: all 0.3s ease;
}
</style>
