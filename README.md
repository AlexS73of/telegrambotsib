# TelegramDipBot

Это Telegram-бот, созданный на базе Java Spring Boot для автоматизации внутренних процессов компании «СИБИНТЕК-СОФТ». Он предоставляет меню с инструкциями, возможностью загрузки PDF-документов и справочной информацией для новых сотрудников.

## 🛠️ Используемые технологии

- Java 24+
- Spring Boot
- Spring Data JPA
- MySQL
- TelegramBots API
- Gradle

## 📁 Структура проекта

- `bot` — основной Telegram-бот
- `handler` — логика обработки сообщений и колбэков
- `keyboard` — генерация inline и reply-клавиатур
- `model` — сущности JPA (`BotUser`, `FileEntity`)
- `repository` — доступ к БД
- `config` — конфигурация Telegram-бота
