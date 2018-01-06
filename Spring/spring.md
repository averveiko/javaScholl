# Конспект по Spring by AVerveyko

## Конфигурирование

### XML
```Java
ApplicationContext context = ClassPathXMLApplicationContext("context.xml");
MyBean myBean = context.getBean("myBean", myBean.class);

```
``` XML
<!-- Включает постпроцессоры обрабатывающие аннотации -->
<context:annotation-config> 
<!-- Или можно сделать так -->
<context:component-scan base-package="com.mkyong.customer" />

<beans....>
 <bean class="com.inwhite.spring.compare.CoolDaoImpl" id="coolDao"/>

    <bean id ="coolService"  class="com.inwhite.spring.compare.CoolServiceImpl"
                             init-method="init" 
                             destroy-method="closeResources"
                             scope="prototype">
           <property name="dao" ref="coolDao"/>
    </bean>

</beans>
```

### Annotation
```XML
<context:annotation-config>
```



```Java
@Repository
public class CoolDaoImpl implements CoolDao {
    @Override
    public void doCRUD() {
        //some logic here
    }
}


@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CoolServiceImpl implements CoolService {
    @Autowired
    private CoolDao dao;

    @PostConstruct
    public void init() {
        //init logic here
    }

    @PreDestroy
    public void closeResources() {
        //close resources here
    }

    @Override
    public void doWork() {
        dao.doCRUD();       
    }
}
```

### Java
final ApplicationContext context = new AnnotationConfigApplicationContext(ConfigExample.class);
final MyBean bean = context.getBean(MyBean.class);

```Java
@Configuration
public class JavaConfig {
    @Bean
    public CoolDao dao(){
        return new CoolDaoImpl();
    }

    @Bean(initMethod = "init", destroyMethod = "closeResources")
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public CoolService coolService(){
        CoolServiceImpl service = new CoolServiceImpl();
        service.setDao(dao());
        return service;
    }
}
```

### Groovy
```Groovy
beans {
    coolDao(CoolDaoImpl)

    coolService(CoolService){bean->
        bean.scope = 'prototype'
        bean.initMethod = 'init'
        bean.destroyMethod = 'closeResources'
    }
}
```
## Режимы создания бинов
* singleton
* prototype
* request
* session
* globalSession
* websocket

## Аннотации
```Java
@Required //Внедряет обязательные зависимости по типу
@Autowired //Внедряет зависимости по типу
@Qualifier //Идентифицирует бины одинакового типа
@Resource //Внедряет зависимости по имени типа

@Component //Идентифицирует бин
@Service
@Repository
@Controller

@Scope //Определяет область действия бина
@Value //Внедряет дефолтные значения

@Configuration //Идентифицирует класс как фабрику бинов
@Bean //Идентифицирует метод создающий бин
@Scope //Определяет область действия бина
@PropertySource //Импортирование XML c конфигурацией
@ImportResource //Подключения файла ресурсов
@Import //Импортирование других классов с конфигурацией
@ComponentScan //Аналог <context:componetn-scan>
@Profile //Подключает компонент только в заданном профиле



@PostConstruct //init-method
@PreDestroy //destroy-method

```

## Spring AOP API
```Java
public static class DisplayTimeIntercepter implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            System.out.println("Before: " + invocation.getMethod().getName());
            return invocation.proceed();
        }finally {
            System.out.println("After: " + invocation.getMethod().getName());
        }
    }
}

//Использование
final MessageRenderer target = new MessageRendererImpl();
final ProxyFactory pf = new ProxyFactory();
pf.setTarget(target);
pf.addAdvice(new DisplayTimeIntercepter());

final MessageRenderer proxy = (MessageRenderer)pf.getProxy();
proxy.execute();
```

## Ссылки
* [Евгений Борисов — Spring-потрошитель, часть 1](https://www.youtube.com/watch?v=BmBr5diz8WA)

## Разное
Environment - описывает окружение в котором работает приложение. Содержит текущие активные профили, текущие св-ва полученные из разных источников (системные, св-ва из файлов, JNDI ...)
```Java
//Use property file
@Configuration
@PropertySource(
        value = {"classpath:db.properties"}
)
public class RecipeStoreConfig {
@Autowired
private Environment env;

env.getProperty("jdbc.url");


//Установить поле с помощью Spring ReflectionUtil
field.setAcccessible(true);
ReflectionUtil.setField(field, bean, value);
```

# H2
```XML
<!-- Добавляем инит скрипты -->
<property name="url" value="jdbc:h2:file:h2\db;INIT=
RUNSCRIPT FROM 'classpath:schema.sql'\;
RUNSCRIPT FROM 'classpath:test-data.sql'"/>
```

