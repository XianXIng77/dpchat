<template>
  <div class="login-container" :class="{ 'dark': isDark }">
    <div class="login-form-wrapper">
      <div class="login-header">
        <h1 class="logo-text">Heima AI Hub</h1>
        <p class="subtitle">欢迎回来，请登录您的账户</p>
      </div>
      
      <form @submit.prevent="handleLogin" class="login-form">
        <div class="form-group">
          <label for="username">用户名/邮箱</label>
          <input
            type="text"
            id="username"
            v-model="form.username"
            @input="validateField('username')"
            placeholder="请输入用户名或邮箱"
            :class="{ 'error': errors.username }"
          />
          <span v-if="errors.username" class="error-message">{{ errors.username }}</span>
        </div>

        <div class="form-group">
          <div class="label-wrapper">
            <label for="password">密码</label>
            <a href="#" class="forgot-password" @click.prevent="showForgotPassword">忘记密码？</a>
          </div>
          <input
            :type="showPassword ? 'text' : 'password'"
            id="password"
            v-model="form.password"
            @input="validateField('password')"
            placeholder="请输入密码"
            :class="{ 'error': errors.password }"
          />
          <button type="button" class="toggle-password" @click="togglePasswordVisibility">
            {{ showPassword ? '隐藏' : '显示' }}
          </button>
          <span v-if="errors.password" class="error-message">{{ errors.password }}</span>
        </div>

        <div class="form-options">
          <label class="remember-me">
            <input type="checkbox" v-model="form.rememberMe" />
            <span>记住我</span>
          </label>
        </div>

        <button type="submit" class="login-button" :disabled="isSubmitting">
          {{ isSubmitting ? '登录中...' : '登录' }}
        </button>

        <div class="divider">
          <span>或</span>
        </div>

        <div class="social-login">
          <button type="button" class="social-button google">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M22.54 6.42a2.78 2.78 0 0 0-1.94-2C18.88 4 12 4 12 4s-6.88 0-8.6.46a2.78 2.78 0 0 0-1.94 2A29 29 0 0 0 1 11.75a29 29 0 0 0 .46 5.33A2.78 2.78 0 0 0 3.4 19c1.72.46 8.6.46 8.6.46s6.88 0 8.6-.46a2.78 2.78 0 0 0 1.94-2 29 29 0 0 0 .46-5.25 29 29 0 0 0-.46-5.33z" fill="#4285F4"/>
              <path d="M12 15.5a3.5 3.5 0 1 1 0-7 3.5 3.5 0 0 1 0 7z" fill="#34A853"/>
              <path d="M5.5 11.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z" fill="#FBBC05"/>
              <path d="M12 19.5c3.56 0 6.5-2.94 6.5-6.5s-2.94-6.5-6.5-6.5a6.51 6.51 0 0 0-6.5 6.5c0 1.61.59 3.11 1.56 4.39l-.96.96a2.78 2.78 0 0 0 1.94 2A29 29 0 0 0 12 19.5z" fill="#EA4335"/>
            </svg>
            谷歌登录
          </button>
          
          <button type="button" class="social-button github">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M12 0C5.373 0 0 5.373 0 12c0 5.302 3.438 9.8 8.207 11.387.599.111.793-.261.793-.577v-2.234c-3.338.726-4.033-1.416-4.033-1.416-.546-1.387-1.333-1.756-1.333-1.756-1.089-.745.083-.729.083-.729 1.205.084 1.839 1.237 1.839 1.237 1.07 1.834 2.807 1.304 3.492.997.107-.775.418-1.305.762-1.604-2.665-.305-5.467-1.334-5.467-5.931 0-1.311.469-2.381 1.236-3.221-.124-.303-.535-1.524.117-3.176 0 0 1.008-.322 3.301 1.23.957-.266 1.983-.399 3.003-.404 1.02.005 2.047.138 3.006.404 2.291-1.552 3.297-1.23 3.297-1.23.653 1.653.242 2.874.118 3.176.77.84 1.235 1.911 1.235 3.221 0 4.609-2.807 5.624-5.479 5.921.43.372.823 1.102.823 2.222v3.293c0 .319.192.694.801.576C20.566 21.797 24 17.3 24 12c0-6.627-5.373-12-12-12z" fill="currentColor"/>
            </svg>
            GitHub 登录
          </button>
        </div>

        <div class="register-link">
          还没有账户？<a href="#" @click.prevent="goToRegister">立即注册</a>
        </div>
      </form>
    </div>

    <!-- 忘记密码弹窗 -->
    <div v-if="showForgotModal" class="modal-overlay" @click.self="closeForgotModal">
      <div class="modal-content">
        <div class="modal-header">
          <h3>找回密码</h3>
          <button class="close-button" @click="closeForgotModal">&times;</button>
        </div>
        <div class="modal-body">
          <p>请输入您的邮箱地址，我们将发送重置密码的链接给您</p>
          <input
            type="email"
            v-model="forgotEmail"
            placeholder="请输入您的邮箱"
            class="forgot-email-input"
          />
          <button @click="sendResetLink" class="send-link-button">
            发送重置链接
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed } from 'vue'
import { useDark } from '@vueuse/core'
import { useRouter } from 'vue-router'
import { userAPI } from '../services/axios.js'
import { useMessage } from 'naive-ui'

const router = useRouter()
const isDark = useDark()

// 表单数据
const form = reactive({
  username: '123456',
  password: '123456',
  rememberMe: false
})

// 表单错误
const errors = reactive({
  username: '',
  password: ''
})

// 状态变量
const showPassword = ref(false)
const isSubmitting = ref(false)
const showForgotModal = ref(false)
const forgotEmail = ref('')

// 表单验证
const validateField = (field: string) => {
  if (field === 'username') {
    if (!form.username.trim()) {
      errors.username = '请输入用户名或邮箱'
    } else if (!isValidEmail(form.username) && form.username.length < 3) {
      errors.username = '用户名至少需要3个字符'
    } else {
      errors.username = ''
    }
  } else if (field === 'password') {
    if (!form.password) {
      errors.password = '请输入密码'
    } else if (form.password.length < 6) {
      errors.password = '密码至少需要6个字符'
    } else {
      errors.password = ''
    }
  }
}

// 邮箱格式验证
const isValidEmail = (email: string) => {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  return emailRegex.test(email)
}

// 验证整个表单
const validateForm = (): boolean => {
  validateField('username')
  validateField('password')
  
  return !errors.username && !errors.password
}

// 处理登录
const handleLogin = async () => {
  if (!validateForm()) {
    return
  }

  isSubmitting.value = true
  try {
    // 使用axios发送登录请求
    const response = await userAPI.login(form.username, form.password)
    
    // 登录成功后处理响应
    // 通常这里会存储token和用户信息
    // if (response.token) {
    //   // 存储token到localStorage
    //   localStorage.setItem('token', response.token)
    //   // 如果需要存储用户信息
    //   if (response.userInfo) {
    //     localStorage.setItem('userInfo', JSON.stringify(response.userInfo))
    //   }
      
    //   // 登录成功后跳转到首页
    //   router.push('/')
    // } else {
    //   throw new Error('登录失败，未收到token')
    // }
    // 登录成功后处理响应
    // 通常这里会存储token和用户信息
    if (response.code === 1) {
      message.success(response.msg|| '登录成功')
      // 延迟跳转，让用户看到成功提示
      setTimeout(() => {
        router.push('/')
      }, 1000)
      
    } else {
      message.error(response.msg || '登录失败')
    }
  } catch (error) {
    console.error('登录失败:', error)
    // 添加错误提示
    alert(error.message || '登录失败，请稍后重试')
  } finally {
    isSubmitting.value = false
  }
}

// 切换密码可见性
const togglePasswordVisibility = () => {
  showPassword.value = !showPassword.value
}

// 显示忘记密码弹窗
const showForgotPassword = () => {
  showForgotModal.value = true
}

// 关闭忘记密码弹窗
const closeForgotModal = () => {
  showForgotModal.value = false
  forgotEmail.value = ''
}

// 发送重置密码链接
const sendResetLink = () => {
  if (!isValidEmail(forgotEmail.value)) {
    alert('请输入有效的邮箱地址')
    return
  }
  
  // 这里是模拟发送重置链接的请求
  // 在实际项目中，应该替换为真实的API调用
  console.log('发送重置链接到:', forgotEmail.value)
  alert('重置密码链接已发送，请查收邮箱')
  closeForgotModal()
}

// 跳转到注册页面
const goToRegister = () => {
  // 如果有注册页面，可以跳转到注册页面
  alert('注册功能即将上线')
}
</script>

<style scoped lang="scss">
.login-container {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: var(--bg-color);
  transition: background-color 0.3s;
  padding: 1rem;
}

.login-form-wrapper {
  background: rgba(255, 255, 255, 0.9);
  backdrop-filter: blur(10px);
  border-radius: 1rem;
  padding: 2.5rem;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
  max-width: 450px;
  width: 100%;
  transition: all 0.3s;

  .login-header {
    text-align: center;
    margin-bottom: 2rem;

    .logo-text {
      font-size: 2rem;
      font-weight: bold;
      margin-bottom: 0.5rem;
      background: linear-gradient(45deg, #007CF0, #00DFD8);
      -webkit-background-clip: text;
      -webkit-text-fill-color: transparent;
    }

    .subtitle {
      color: #666;
      font-size: 1.1rem;
    }
  }

  .login-form {
    width: 100%;

    .form-group {
      margin-bottom: 1.5rem;
      position: relative;

      label {
        display: block;
        margin-bottom: 0.5rem;
        font-weight: 500;
        color: var(--text-color);
      }

      .label-wrapper {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 0.5rem;

        .forgot-password {
          color: #007CF0;
          text-decoration: none;
          font-size: 0.9rem;
          transition: color 0.3s;

          &:hover {
            color: #0056b3;
          }
        }
      }

      input {
        width: 100%;
        padding: 0.75rem 1rem;
        border: 2px solid #e0e0e0;
        border-radius: 0.5rem;
        font-size: 1rem;
        transition: border-color 0.3s;
        background: rgba(255, 255, 255, 0.7);

        &:focus {
          outline: none;
          border-color: #007CF0;
        }

        &.error {
          border-color: #ff4d4f;
        }
      }

      .toggle-password {
        position: absolute;
        right: 10px;
        top: 70%;
        background: none;
        border: none;
        color: #666;
        cursor: pointer;
        font-size: 0.875rem;
        transition: color 0.3s;

        &:hover {
          color: #333;
        }
      }

      .error-message {
        color: #ff4d4f;
        font-size: 0.875rem;
        margin-top: 0.25rem;
        display: block;
      }
    }

    .form-options {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 1.5rem;

      .remember-me {
        display: flex;
        align-items: center;
        cursor: pointer;

        input[type="checkbox"] {
          margin-right: 0.5rem;
          accent-color: #007CF0;
        }

        span {
          font-size: 0.9rem;
          color: var(--text-color);
        }
      }
    }

    .login-button {
      width: 100%;
      padding: 0.75rem;
      background: linear-gradient(45deg, #007CF0, #00DFD8);
      color: white;
      border: none;
      border-radius: 0.5rem;
      font-size: 1rem;
      font-weight: 500;
      cursor: pointer;
      transition: all 0.3s;

      &:hover:not(:disabled) {
        transform: translateY(-2px);
        box-shadow: 0 5px 15px rgba(0, 124, 240, 0.3);
      }

      &:disabled {
        opacity: 0.7;
        cursor: not-allowed;
        transform: none;
        box-shadow: none;
      }
    }

    .divider {
      display: flex;
      align-items: center;
      margin: 2rem 0;

      &::before,
      &::after {
        content: '';
        flex: 1;
        height: 1px;
        background: #e0e0e0;
      }

      span {
        padding: 0 1rem;
        color: #666;
        font-size: 0.9rem;
      }
    }

    .social-login {
      display: flex;
      gap: 1rem;
      margin-bottom: 1.5rem;

      .social-button {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 0.5rem;
        padding: 0.75rem;
        border: 1px solid #e0e0e0;
        border-radius: 0.5rem;
        background: white;
        cursor: pointer;
        transition: all 0.3s;
        font-size: 0.9rem;

        &:hover {
          transform: translateY(-2px);
          box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        &.google {
          color: #4285F4;

          svg {
            fill: #4285F4;
          }
        }

        &.github {
          color: #333;

          &:hover {
            background: #333;
            color: white;
          }
        }
      }
    }

    .register-link {
      text-align: center;
      font-size: 0.9rem;
      color: var(--text-color);

      a {
        color: #007CF0;
        text-decoration: none;
        transition: color 0.3s;

        &:hover {
          color: #0056b3;
        }
      }
    }
  }
}

/* 暗色模式样式 */
.dark .login-form-wrapper {
  background: rgba(30, 30, 30, 0.9);
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);

  .login-header {
    .subtitle {
      color: #999;
    }
  }

  .login-form {
    .form-group {
      label {
        color: var(--text-color);
      }

      input {
        background: rgba(40, 40, 40, 0.7);
        color: var(--text-color);
        border-color: rgba(255, 255, 255, 0.1);

        &:focus {
          border-color: #007CF0;
        }

        &::placeholder {
          color: #666;
        }
      }

      .toggle-password {
        color: #999;

        &:hover {
          color: #fff;
        }
      }
    }

    .form-options {
      .remember-me {
        span {
          color: var(--text-color);
        }
      }
    }

    .divider {
      &::before,
      &::after {
        background: rgba(255, 255, 255, 0.1);
      }

      span {
        color: #999;
      }
    }

    .social-login {
      .social-button {
        background: rgba(40, 40, 40, 0.7);
        border-color: rgba(255, 255, 255, 0.1);
        color: var(--text-color);

        &.github:hover {
          background: #333;
          color: white;
        }
      }
    }

    .register-link {
      color: var(--text-color);
    }
  }
}

/* 弹窗样式 */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  border-radius: 0.5rem;
  padding: 1.5rem;
  width: 90%;
  max-width: 400px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);

  .modal-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 1rem;

    h3 {
      margin: 0;
      color: var(--text-color);
    }

    .close-button {
      background: none;
      border: none;
      font-size: 1.5rem;
      cursor: pointer;
      color: #666;
      padding: 0;
      width: 30px;
      height: 30px;
      display: flex;
      align-items: center;
      justify-content: center;
      border-radius: 50%;
      transition: all 0.3s;

      &:hover {
        background: #f0f0f0;
        color: #333;
      }
    }
  }

  .modal-body {
    p {
      margin-bottom: 1rem;
      color: #666;
    }

    .forgot-email-input {
      width: 100%;
      padding: 0.75rem;
      border: 1px solid #e0e0e0;
      border-radius: 0.5rem;
      margin-bottom: 1rem;
      font-size: 1rem;

      &:focus {
        outline: none;
        border-color: #007CF0;
      }
    }

    .send-link-button {
      width: 100%;
      padding: 0.75rem;
      background: #007CF0;
      color: white;
      border: none;
      border-radius: 0.5rem;
      font-size: 1rem;
      cursor: pointer;
      transition: background-color 0.3s;

      &:hover {
        background: #0056b3;
      }
    }
  }
}

/* 暗色模式弹窗样式 */
.dark .modal-content {
  background: #2a2a2a;

  .modal-header {
    h3 {
      color: var(--text-color);
    }

    .close-button {
      color: #999;

      &:hover {
        background: #3a3a3a;
        color: #fff;
      }
    }
  }

  .modal-body {
    p {
      color: #999;
    }

    .forgot-email-input {
      background: #3a3a3a;
      border-color: #4a4a4a;
      color: var(--text-color);

      &::placeholder {
        color: #666;
      }
    }
  }
}

/* 响应式设计 */
@media (max-width: 768px) {
  .login-form-wrapper {
    padding: 2rem 1.5rem;
  }

  .social-login {
    flex-direction: column;
  }

  .login-header {
    .logo-text {
      font-size: 1.75rem;
    }
  }
}
</style>