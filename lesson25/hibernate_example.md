@Entity - сущность
@Table(name="table_name") - позволяет задать имя таблицы
@ID
@GeneratedValue - автоматическая генерация первичного ключа (стратегии: AUTO, IDENTITY, SEQUENCE, TABLE, UUID)
@Basic - позволяет указать nullable и  fetch стратегию
@Column(nullable = false, length = 2000) - позволяет задать имя колонки в БД, размер поля, nullable, updatable, insertable
@Enumerated - позволяет указать как мапить enum значения: число или строка
@Transient - предотвращяет маппинг поля

@Embeddable, @EmbeddableID - позволяет задать составной первичный ключ

@OrderBy - позволяет задать порядок

@NamedQueries - позволяет задать именованные запросы

@SecondaryTable - распределение данных в сущности по нескольким таблицам:
```Java
@Entity(name="Address")
@SecondaryTables({
	@SecondaryTable(name = "City"),
	@SecondaryTable(name = "Country")
})
public class Address {
	@Id
	private Long id;
	private String street;
	@Column(table = "city")
	private String city;
	@Column(table = "country")
	private String country;
}
```
Коллекции базовых типов
```Java

//List
@Entity
public class Book implements Serializable {
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "Tag")
	@Column(name = "Value")
	private List<String> tags = new ArrayList<String>();
}

//Map
@Entity
public class CD implements Serializable {
	@ElementCollection()
	@CollectionTable(name = "track")
	@MapKeyColumn(name = "position")
	@Column(name = "title")
	private Map<String, String> tracks = new HashMap<>();
}

```