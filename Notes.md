Всё что нужно для клиента хранится в папке resources/static
Там - стили, картинки, javascript.
По умолчанию spring boot ориентирован на работу именно с папкой static

### Стили
Есть набор зависимостей, которые собрались в папке webjars.
Плюс есть свои стили в static.

В base.html необходимо прописать пути для сприптов и библиотек.
Как подключенных, так и своих.

    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <!--подключение css файлов из папки static-->
    <link rel="stylesheet" href="/css/style.css"/>

    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    
для css путь нужен относительно /resources/static
для библиотек и скриптов идёт от /webjars

Пути до скриптов, стилей, картинок для спринга всегда идут относительные от /resources/static

### Валидация

Все аннотации, касающиеся валидации собраны в пакете javax.validation.constraints
Все проверки указываются в качестве аннотаций в полях сущности
Чтобы проверки действительно были осуществлены, нужно в контроллере в методе создания сущности добавить к аргументу 
аннотацию @Valid.
Она указывает, что валидацию нужно осуществить.
В случае проблем, в BindingResult метод hasErrors() вернёт true

### Безопасность

При подключении security в entity обязательно должны появиться две сущности:
- User
- Role
соответствующие таблицы в БД **обязательно** нужно переименовать (эти имена зарезервированы в БД)
имена сущностей могут быть любыми.

Сущность, которая реализует пользователя **обязательно** должна реализовывать интерфейс UserDetails
(и соответственно, реализовывать ряд его методов: главный - возвращать список ролей пользователя)

Сущность, которая реализует роль **обязательно** должна реализовывать интерфейс GrantedAuthority
(и соответственно, реализовывать ряд его методов: главный - возвращать имя роли)

Для каждой сущности создаются свои репозитории

### Сервисы

Сервисы - это прослойка между контроллерами и репозиториями, которая занимается обработкой данных

Все сервисы должны быть отмечены аннотацией @Service
Это нужно чтобы можно было привязать сервис к контроллеру.

Конкретно сервис юзеров должен реализовывать UserDetailService.
плюс обязательны ссылки на UserRepository; RoleRepository; шифровальщик (для шифрования / дешифровки пароля)
На текущий момент единственный допустимый шифровальщик - BCryptPasswordEncoder.

Все пользователи обязаны быть уникальными. Мы должны осуществить некоторый критерий уникальности.
Перед тем как сохранять пользователя в базу обязательно нужно проверить, есть там такой пользователь уже или нет.
Метод findByUsername() используется для поиска по уникальному критерию.
Если такого пользователя нет, то далее должна быть описана логика назначения ролей пользователю.

### WebSecurityConfig

Аннотация @Configuration означает, что нужно создать и сохранить объект данного класса.
И что это объект-конфигуратор.

Аннотация @EnableWebSecuritу означает, что данный класс настраивает права доступа пользователей к элементам проекта.

Обязательно наличие шифровальщика BCryptPasswordEncoder
Именно в WebSecurityConfig указано создание объекта этого класса

Если метод, который возвращает какой-то объект, отмечен аннотацией @Bean - это означает, что этот объект должен быть 
сохранён в спринге так же как и всё остальное. 

Всё что отмечено @Configuration инстанцируется в первую очередь.

Главный метод в WebSecurityConfig: configure(HttpSecurity httpSecurity)
В нём настраивается какие пользователи могут обращаться к каким страницам. А к каким нет.

### HTML формы

[index.html](src/main/resources/templates/index.html)
