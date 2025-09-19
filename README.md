# DPChat

<div align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5.5-green" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Vue%203-3.4.15-blue" alt="Vue 3">
  <img src="https://img.shields.io/badge/Spring%20AI-1.0.2-purple" alt="Spring AI">
  <img src="https://img.shields.io/badge/Ollama-API-yellow" alt="Ollama">
  <img src="https://img.shields.io/badge/DeepSeek-Model-orange" alt="DeepSeek Model">
</div>

## 📋 项目概述
DPChat是一个基于AI大语言模型的智能聊天应用平台，集成了Spring AI、Ollama和DeepSeek等先进技术，提供多模态交互体验。该项目分为前后端分离架构，后端采用Spring Boot构建RESTful API，前端使用Vue 3开发现代化Web界面，能够实现智能对话、文档理解、客户服务等多种场景的应用。

## 🚀 核心功能

- **✨ AI智能对话**：与AI模型进行自然语言交互，支持多轮对话和上下文理解
- **📄 PDF文档对话**：上传PDF文件并进行智能问答，深入理解文档内容
- **💬 客户服务系统**：提供专业的客户咨询记录和管理功能
- **🎮 趣味互动**：内置哄哄模拟器等趣味互动功能
- **🌓 深色/浅色主题**：支持一键切换界面主题，提供舒适的视觉体验
- **📱 响应式设计**：适配多种屏幕尺寸，提供良好的移动端体验

## 🛠️ 技术栈

### 后端 (dpchat-api)
- **框架**：Spring Boot 3.5.5
- **语言**：Java 21
- **AI集成**：Spring AI 1.0.2 + Ollama
- **数据库访问**：MyBatis-Plus 3.5.5
- **数据库**：MySQL
- **API文档**：Knife4j OpenAPI 3
- **工具库**：Lombok、Spring AOP

### 前端 (dpchat-ui)
- **框架**：Vue 3 + TypeScript
- **构建工具**：Vite 6.1.0
- **状态管理**：Pinia 3.0.1
- **路由**：Vue Router 4.2.5
- **UI组件**：Naive UI 2.41.0
- **图标**：Heroicons Vue
- **PDF处理**：@pdftron/webviewer 11.3.0
- **Markdown渲染**：marked、highlight.js
- **HTTP客户端**：axios

## 📁 项目结构

```text
dpchat/
├── dpchat-api/            # 后端Spring Boot项目
│   ├── src/main/java/     # 后端源代码
│   ├── src/main/resources/# 配置文件
│   └── pom.xml            # Maven依赖配置
├── dpchat-ui/             # 前端Vue 3项目
│   ├── src/               # 前端源代码
│   │   ├── components/    # Vue组件
│   │   ├── views/         # 页面视图
│   │   ├── services/      # API服务
│   │   ├── stores/        # Pinia状态管理
│   │   ├── router/        # 路由配置
│   │   └── assets/        # 静态资源
│   ├── public/            # 静态文件
│   └── package.json       # NPM依赖配置
└── README.md              # 项目说明文档
```
