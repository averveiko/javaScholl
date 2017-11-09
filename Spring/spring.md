# Конспект по Spring by AVerveyko

## Конфигурирование

### XML
```Java
ClassPathXMLApplicationContext("context.xml");
context.getBean();

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


## Аннотации
```Java
@Component
@Service
@Repository
@Bean

@Autowired

@PostConstruct //init-method

```
## Ссылки
* [Евгений Борисов — Spring-потрошитель, часть 1](https://www.youtube.com/watch?v=BmBr5diz8WA)

## Разное
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

