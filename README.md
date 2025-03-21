
## Содержание
1. [Как запустить проект локально]
2. [Как собрать и запустить проект с помощью Docker]
3. [Как использовать API]
4. [Как получить доступ к Swagger UI]

---

## Как запустить проект локально

### Требования
- Java 17
- Maven или Gradle
- PostgreSQL

### Шаги
1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/your-repo/task-management-system.git
   cd task-management-system

## Соберите проект:
   Для Gradle:
```bash
   ./gradlew build
```
## Запустите приложение:
```bash
java -jar target/tz-jwt-0.0.1-SNAPSHOT.jar
```
Приложение будет доступно по адресу:

```bash
http://localhost:8085
```


## Как собрать и запустить проект с помощью Docker

### Требования
- Docker
- Docker Compose

### Шаги

Клонируйте репозиторий:

```bash
git clone https://github.com/your-repo/task-management-system.git
```
```bash
cd task-management-system
```

### Соберите Docker-образ:

```bash
docker-compose build
```

### Запустите контейнеры:

```bash
docker-compose up
```
Приложение будет доступно по адресу:

```bash
http://localhost:8085
```

База данных PostgreSQL будет доступна на порту 5432.

## Как использовать API
Примеры запросов
Регистрация пользователя
```bash
POST /api/auth/register
Content-Type: application/json
{
  "email": "user@example.com",
  "password": "password123",
  "role": "USER"
}
```
Аутентификация пользователя
```bash
POST /api/auth/login
Content-Type: application/x-www-form-urlencoded
email=user@example.com&password=password123
```
Создание задачи
```bash
POST /api/tasks
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>
{
  "title": "Fix bug",
  "description": "Fix the bug in authentication",
  "status": "в процессе",
  "priority": "высокий",
  "authorId": 1
}
```
Получение задач с фильтрацией и пагинацией
```bash
GET /api/tasks?status=в процессе&priority=высокий&page=0&size=10
Authorization: Bearer <JWT_TOKEN>
```
Добавление комментария
```bash
POST /api/comments
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>
```
```bash
{
  "text": "This is a test comment",
  "taskId": 1,
  "authorId": 1
}
```
## Как получить доступ к Swagger UI
Запустите приложение (локально или через Docker).

Перейдите по адресу:

```bash
http://localhost:8085/swagger-ui.html
```
