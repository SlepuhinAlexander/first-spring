### К понедельнику (8 июня 2020)
1. Клонируете репозиторий
2. Выполняете maven -> reimport, чтобы подгрузить все необходимые зависимости
3. В файле [application.properties](src/main/resources/application.properties) устанавливаете свои настройки:
   1. ПОРТ, НАЗВАНИЕ БД (строка 2) spring.datasource.url=jdbc:postgresql://localhost:5432/db_lessons
   2. ИМЯ ПОЛЬЗОВАТЕЛЯ (строка 3) spring.datasource.username=jjd
   3. ПАРОЛЬ (строка 4) spring.datasource.password=jjd
4. Запускаете проект. Если порт 8080 занят, нужно раскомментировать строчку 16 в [application.properties](src/main/resources/application.properties) 
и вместо 'ваш_порт' указать другой порт
5. В адресной строке браузера пишите  http://localhost:8080/ (или тот порт, который указали)
6. В браузере увидите ошибку Whitelabel Error Page
7. В базе данных должна будет создаться табличка tst_entity (проверьте через PGAdmin)