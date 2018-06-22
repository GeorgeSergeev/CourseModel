# CourseModel
Джемалетдинов Арсен. Задание получил 19 июня, начал 20.
Для генерации каркаса использовал Jhipster, ибо решил сделать и back и front-end, не тратя время на настройку рабочего окружения (хотя если потребуется, настрою)
Стек: Spring Boot+AngularJS.
Из критериев для дополнительных баллов.

HTML+CSS (по моему 40% времени у меня заняла адаптивная верстка)
AngularJS (каплю логики перенес на JS, но это не из за лени: ) ). + полноценное использование директив, фильтров, роутинга. + code style и структура папок от John Papa (крутой мужик), в своем блоге он рассказывает почему такой code style намного лучше общепринятого.
Spring Security (есть две роли, с правами доступа Admin и User). * на самом деле есть еще одна - AnonymusUser, но считайте это обычным гостем.
MySQL (4 сущности + две промежуточные, хранящие в себе ключи от других сущностей, например Student и Courses )
CRUD REST services (best practices от Jhipster'a, с собственным swagger'ом и тестами). Когда будете делать review, может показаться что вся логика написанная в REST контролерах, но нет. Стримы и лямбды вполне позволили мне не создавать кучу методов в слое сервисов. Когда нужно, я просто обращался к DTO за данными с помощью Java 8 прямо из REST контроллера. (знаю что плохая практика, но если было бы больше времени, сделал бы лучше, у меня ведь тоже есть чувство прекрасного)
Hibernate (обычный hibernate со своими аннотациями)
С какими сложностями столкнулся? Всего с одной. Это не понимание архитектуры предлагаемой модели. Из за этого я не до конца разобрался с сущностью "прохождения курса", пытаясь понять откуда там должен появится List оценок, на основании которого и строятся последующие два метода. А так все сделал.

Если дело дойдет до тех. собеседования, можем вместе разобраться с этой моделью.
Пишите, звоните. dzhemaletdinov.a.i14@gmail.com
Очень хочу у вас работать, и развиваться как full-stack.

## Development
1) npm install
2) Система сборки gulp, поэтому - npm install -g gulp-cli
3) Создать базу данных - CourseModel 
В корне проекта есть дамп базы данных - CourseModel.sql
Для доступа к базе данных, необходимо перейти в src\main\resources\config\application-dev.yml и установить username && password в строках 36-37.
3) запуск -  ./mvnw
             gulp

