<template id="retro">
  <v-container fluid class="pa-0 fill-height login-wrapper" style="background-color: #121212 !important;">
    <v-row no-gutters class="fill-height">
      
      <v-col cols="12" md="7" class="d-none d-md-block">
        <v-img
          src="images/NIKE.jpg"
          cover
          class="fill-height"
          alt="Nike Sneakers Background"
        >
          <div class="fill-height image-overlay"></div>
        </v-img>
      </v-col>

      <v-col cols="12" md="5" class="d-flex align-center justify-center pa-4 pa-sm-8 pa-md-12">
        
        <v-card flat class="rounded-xl pa-8 pa-sm-10 w-100 login-card" style="border: 1px solid #E0E0E0; max-width: 480px;">
          
          <v-card-title class="text-h5 font-weight-bold text-wrap pa-0 mb-3" style="line-height: 1.2;">
            Te damos la bienvenida a  SneakerShop
          </v-card-title>
          
          <v-card-subtitle class="pa-0 text-body-2 text-grey-darken-1 mb-8">
            Indica tu email para registrarte o iniciar sesión
          </v-card-subtitle>
          
          <v-form @submit.prevent="handleContinue">
            <v-text-field
              v-model="email"
              label="Dirección de email *"
              variant="outlined"
              color="black"
              base-color="#BDBDBD"
              class="rounded-lg mb-6 email-input"
              :rules="[rules.required, rules.email]"
              hide-details="auto"
              type="email"
              density="comfortable"
              required
            ></v-text-field>
            
            <v-btn
              block
              class="text-none text-subtitle-1 rounded-pill"
              color="black"
              theme="dark"
              elevation="0"
              size="large"
              height="48"
              :loading="loading"
              :disabled="!email"
              @click="handleContinue"
            >
              Continuar
            </v-btn>
          </v-form>

          <div class="d-flex align-center my-10 social-divider-container">
            <v-divider class="opacity-100"></v-divider>
            <div class="mx-2 d-flex align-center justify-center social-circle">
              <div class="inner-circle" style="width: 8px; height: 8px; border-radius: 50%; border: 1px solid #BDBDBD;"></div>
            </div>
            <v-divider class="opacity-100"></v-divider>
          </div>

          <v-row justify="center" class="social-btns-row" no-gutters>
            <v-btn variant="outlined" class="rounded-lg border-grey-lighten-2 mx-1 social-btn" icon @click="socialLogin('google')">
              <v-icon color="#EA4335">mdi-google</v-icon>
            </v-btn>
            
            <v-btn variant="outlined" class="rounded-lg border-grey-lighten-2 mx-1 social-btn" icon @click="socialLogin('facebook')">
              <v-icon color="#1877F2">mdi-facebook</v-icon>
            </v-btn>
            
            <v-btn variant="outlined" class="rounded-lg border-grey-lighten-2 mx-1 social-btn" icon @click="socialLogin('apple')">
              <v-icon color="black">mdi-apple</v-icon>
            </v-btn>
            
            <v-btn variant="outlined" class="rounded-lg border-grey-lighten-2 mx-1 social-btn" icon @click="socialLogin('twitter')">
              <v-icon color="#1DA1F2">mdi-twitter</v-icon>
            </v-btn>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
app.component("retro", {
  template: "#retro",
  data() {
    return {
      email: "",
      loading: false, // Controla el estado de carga del botón de Vuetify
      rules: {
        required: value => !!value || 'El email es obligatorio.',
        email: value => {
          const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
          return pattern.test(value) || 'Email no válido.'
        },
      },
    };
  },
  methods: {
    async handleContinue() {
      // 1. Validamos localmente antes de molestar al servidor
      const isEmailValid = this.rules.email(this.email) === true;
      
      if (this.email && isEmailValid) {
        this.loading = true;
        try {
          // 2. Petición al endpoint unificado en Javalin
          // Enviamos solo el email para que el backend sepa que es LOGIN (Escenario B)
          const response = await fetch('/api/auth/step1', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email: this.email })
          });

          // Verificamos si la respuesta es exitosa (200-299)
          if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || "Error en el servidor");
          }

          const result = await response.json();

          if (result.status === "success") {
            // 3. Persistencia para el paso 2 (Verificación)
            // Guardamos el email en el navegador para usarlo en el fetch de /verify
            localStorage.setItem('userEmail', this.email);
            
            console.log("Código 2FA generado. Redirigiendo...");
            
            // 4. Salto a la pantalla de ingreso de código
            window.location.href = "/";
          } else {
            alert("Atención: " + result.message);
          }
        } catch (error) {
          console.error("Error en la autenticación:", error);
          alert("Hubo un problema al procesar el email. Verifica que el servidor esté corriendo en el puerto 7000.");
        } finally {
          this.loading = false;
        }
      } else {
        alert("Por favor, ingresa una dirección de correo válida para continuar.");
      }
    },
    
    socialLogin(platform) {
      console.log("Iniciando sesión con plataforma externa:", platform);
      // Implementación futura para la UTTT (OAuth2)
    }
  }
});
</script>
