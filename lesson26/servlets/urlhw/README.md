### Создать сервлет
Используемый архетип maven:
org.codehaus.mojo.archetypes:webapp-javaee7 (Archetype for a web application using Java EE 7.)

Вручную делается так:
* mvn archetype:generate
* filter по "webapp-javaee7"

После разработки деплоим war в WildFly: wildfly-11.0.0.Final\standalone\deployments

Разработка:
* Создаем класс, наследуемся от HttpServlet
* Регистрируем через аннотацию: @WebServlet(urlPatterns = "/hello")
* Переопределяем protected void service(HttpServletRequest req, HttpServletResponse resp)
* см. пример кода + [скринкасты](https://www.youtube.com/playlist?list=PLiJ76e8LBYQVOHd1G3vva9FGV66sdV3N1)