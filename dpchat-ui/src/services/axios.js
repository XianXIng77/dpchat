import axios from 'axios'

// 创建axios实例，利用反向代理，不需要配置baseURL
const axiosInstance = axios.create({
  timeout: 10000, // 请求超时时间
  headers: {
    'Content-Type': 'application/json'
  }
})

// 响应拦截器
axiosInstance.interceptors.response.use(
  response => {
    return response.data
  },
  error => {
    console.error('API请求错误:', error)
    // 统一错误处理
    if (error.response) {
      // 服务器返回了错误状态码
      const { status, data } = error.response
      return Promise.reject({
        status,
        message: data.message || `请求失败: ${status}`
      })
    } else if (error.request) {
      // 请求已发送但没有收到响应
      return Promise.reject({ message: '网络错误，请检查连接' })
    } else {
      // 请求配置出错
      return Promise.reject({ message: error.message })
    }
  }
)

// 用户相关API
export const userAPI = {
  // 登录请求
  async login(username, password) {
    try {
      const response = await axiosInstance.post('/api/ai/login', {
        username,
        password
      })
      return response
    } catch (error) {
      console.error('登录失败:', error)
      throw error
    }
  }
}

export default axiosInstance