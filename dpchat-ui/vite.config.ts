import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'
import { fileURLToPath } from 'node:url' // 添加缺失的导入

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },
  // 添加反向代理配置
  server: {
    port: 5000, // 前端开发服务器端口
    headers: {
      'Cross-Origin-Opener-Policy': 'same-origin',
      'Cross-Origin-Embedder-Policy': 'require-corp'
    },
    proxy: {
      // 将以/api开头的请求转发到后端服务器
      '/api': {
        target: 'http://localhost:9090', // 后端API运行在9090端口
        changeOrigin: true, // 允许跨域
        rewrite: (path) => path.replace(/^\/api/, '') // 重写路径，移除/api前缀
      },
      // 如果需要其他代理配置，可以在这里添加
      // 例如：
      // '/socket': {
      //   target: 'ws://localhost:9090',
      //   ws: true,
      //   changeOrigin: true
      // }
    }
  }
})
