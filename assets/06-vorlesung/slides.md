class: title-slide  

# Modul- Fortgeschrittene Programmierkonzepte
### Bachelor Informatik

## 06- Reflection und Annotations
### Prof. Dr. Marcel Tilly
Fakultät für Informatik, Cloud Computing

---

# Reflection and Annotations

.center[![reflection](/assets/reflection.gif)]

- `java.lang.Class<T>`: your ticket to reflection
- Messing with Objects
- Basic Java beans (a simple plugin architecture)
- Annotations

---

# Defining Annotations

```java
public @interface MyMarker {}
```

```java
public @interface MyValue {
    String value();
}
```

```java
public @interface Fixed {
    String author() ;
    String date() ;
    String bugsFixed() default "" ;
}
```

```java
@MyMarker
@MyValue("Hansdampf")
@Fixed(author="riko493", date="2017-11-15")
void method() { ... }
```

```java
@MyMarker
@SomeValue("meh")
void method() { ... }
```

---

# Method Annotations

```java
class K {
    @Override
    public boolean equals(Object o) {
        // ...
    }
    @Deprecated
    public void useSomethingElseNow() {
        // ...
    }
    @SuppressWarnings("unchecked")
    public void nastyCasts() {

    }
}
```

---

# Type (Attribute) Annotations

`@NonNull`: The compiler can determine cases where a code path might receive a null value, without ever having to debug a `NullPointerException`.

`@ReadOnly`: The compiler will flag any attempt to change the object.

`@Regex`: Provides compile-time verification that a `String` intended to be used as a regular expression is a properly formatted regular expression.

`@Tainted` and `@Untainted`: Identity types of data that should not be used together, such as remote user input being used in system commands, or sensitive information in log streams.

```java
abstract void method(@NonNull String value, @Regex re);
```

---

# Annotations: JUnit5

```java
class MyTest {
    BufferedReader reader;

    @BeforeAll
    void setUp() {
        reader = new BufferedReader();  // ...
    }

    @Test
    void testSomeClass() {
        // ...
    }
}
```

---

# Annotations: Gson by Google

```java
class Klass {
    private int value1 = 1;
    private String value2 = "abc";
    @SerializedName("odd-name") private String oddName = "1337";
    private transient int value3 = 3;  // will be excluded
    Klass() {
        // default constructor (required)
    }
}

// Serialization
Klass obj = new Klass();
Gson gson = new Gson();
String json = gson.toJson(obj);  
// ==> json is {"value1":1,"value2":"abc","odd-name": "1337"}

// Deserialization
Klass obj2 = gson.fromJson(json, Klass.class);
// ==> obj2 is just like obj
```

---

# Annotations: Butterknife

```java
class ExampleActivity extends Activity {
    @BindView(R.id.title) TextView title;
    @BindView(R.id.subtitle) TextView subtitle;
    @BindView(R.id.footer) TextView footer;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity);
        ButterKnife.bind(this);
        // TODO Use fields...
    }
}
```

---

# Annotations: Retrofit

```java
public interface GitHubService {
  @GET("users/{user}/repos")
  Call<List<Repo>> listRepos(@Path("user") String user);
}

Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .build();

GitHubService service = retrofit.create(GitHubService.class);

Call<List<Repo>> repos = service.listRepos("octocat");
```