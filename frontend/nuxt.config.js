export default {
  // Global page headers: https://go.nuxtjs.dev/config-head
  router: {
    extendRoutes(routes, resolve) {
      routes.push({
        name: 'custom-home',
        path: '/',
        component: resolve(__dirname, 'pages/index.vue')
      });
      routes.push({
        name: 'detail-page',
        path: '/detail',
        component: resolve(__dirname, 'pages/scheduler.vue')
      });
      routes.push({
        name: 'edit-page',
        path: '/edit',
        component: resolve(__dirname, 'pages/edit.vue')
      });
    }
  },
  head: {
    title: 'scheduler-frontend',
    htmlAttrs: {
      lang: 'en'
    },
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '' },
      { name: 'format-detection', content: 'telephone=no' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: [
  ],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [
  ],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: [
    // https://go.nuxtjs.dev/typescript
    '@nuxt/typescript-build',
    // https://go.nuxtjs.dev/tailwindcss
    '@nuxtjs/tailwindcss',
  ],

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: ['@nuxtjs/tailwindcss'],

  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {
  }
}
