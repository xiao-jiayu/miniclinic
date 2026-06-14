# MiniClinic 社區診所掛號系統

一個以 Spring Boot 實作的社區診所掛號系統，支援醫師登入、病患掛號、掛號狀態管理等功能。

## 線上 Demo

https://miniclinic-xiao-jiayu.onrender.com
*(💡 提示：等一下部署成功後，可以再回來把這個網址換成 Render 給你的真實網址喔！)*

## 技術棧

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Thymeleaf
- SQLite（本地開發） / PostgreSQL（雲端正式環境）
- BCrypt（密碼雜湊）

## 功能清單

- 醫師登入 / 登出
- 醫師個人 Dashboard（今日掛號清單）
- 病患資料管理（CRUD）
- 線上掛號功能
- **看診完成功能**（點擊按鈕可將狀態更新為 COMPLETED 並自動重整）
- 掛號狀態變更（booked / completed / cancelled）
- RESTful API

## 本機執行

```bash
./mvnw spring-boot:run
```

開啟瀏覽器訪問 http://localhost:8080

預設醫師帳密：
- D001 / pass1234
- D002 / pass1234

## 作者

2026 年 Java 程式設計課程期末專案

## 聲明

所有病患資料均為虛構，僅供教學使用。