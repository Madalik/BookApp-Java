import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  server: {
    proxy: {
      '/api/': {
        target: 'http://127.0.0.1:8080',
        secure: false,
        changeOrigin:false,
        rewrite:(path) => path.replace(/^\/api/, '') 
      }
    }
  },
  plugins: [react()],
  
})
