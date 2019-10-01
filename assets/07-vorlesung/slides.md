class: title-slide  

# Modul- Fortgeschrittene Programmierkonzepte
### Bachelor Informatik

## 01- Design Pattern, pt. 1
### Prof. Dr. Marcel Tilly
Fakultät für Informatik, Cloud Computing

---

# Recommended Reading

<img src="/assets/design-pattern-amazon.jpg" alt="GOF" style="float: left; width: 30%; margin-right: 30px">

## [Design Patterns](https://www.amazon.de/Patterns-Elements-Reusable-Object-Oriented-Software/dp/0201633612/)
by Gamma/Helm/Johnson/Vlissides (_Gang of Four_).

---

# Design Patterns

Patterns that emerged for solving frequent problems

Shared vocabulary for developers
- common ground for talking about architecture
- less talking, more doing

Patterns are based on principles of object-oriented programming.
- interfaces, inheritance
- composition, delegation and encapsulation

Toolset for a clear software architecture.

---

# Class Diagrams

.skip[
![uml-class-relations](/assets/classdiagram.svg)
]

**Composition**: real-world whole-part relation

**Aggregation**: "catalog" containment, can exist independently

---

# Sequence Diagrams

.skip.center[
![uml-sequence-diagram](/assets/uml-sequence-diagram.svg)
]

---

# Iterator

```java
SimpleList<Integer> list = SimpleList<>(3, 1, 3, 3, 7);
```

.container[
.column[

```java
int i = 0;
for ( ; i < list.size(); ) {
	System.out.println(list.get(i));
	i++;
}
```
]

.column[

```java
int i = 0;
while (i < list.size()) {
	System.out.println(list.get(i));
	i++;
}
```
]
]

.container[

```java
Iterator<Integer> it = list.???;

while (it.hasNext()) {
	Integer v = it.next();
}
```
]

How does an iterator look like?

---

# Iterator

<img alt="iterator-uml" class="float-left w40" src="/assets/dp-iterator.svg">

```java
class SimpleList<T> implements BasicList<T> {
	// ...
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Element it = root;
			@Override
			public boolean hasNext() {
				return it == null;
			}

			@Override
			public T next() {
				T value = it.value;
				it = it.next;
				return value;
			}
		};
	}
}
```

---

# Composite

.skip.center[
![fashion shopping](/assets/dp-composite-ex.svg)
]

---

# Composite

.skip.center[
![uml-composite](/assets/dp-composite.svg)
]

---

# Composite Examples

- file systems: identifier, directory, file, link
- JUnit:
	+ component: _test_
	+ composite: _test suite_ comprised of multiple tests
	+ leaf: individual test case
- HTML documents:
	+ component: _element_
	+ composite: containers (`div`, `p`, etc.)
	+ leaf: _text nodes_
- GUI libraries (such as Android)
	+ component: `android.view.View`
	+ composite: `android.view.ViewGroup`
	+ leaf: individual widgets, e.g. `Button`

---

# Observer

.skip.center[
![uml-observer-seq](/assets/dp-observer-seq.svg)
]

---

# Observer

.skip.center[
![uml-observer](/assets/dp-observer.svg)
]

---

# Android

.container[
.column.w40[
![Android OS](/assets/android-robot.svg)
]

.column.w40[
![Android design](/assets/android-design.png)
]
]

.container[
_Don't panic: this is neither a GUI nor a mobile development class!_
]

---

# Model-View-Controller

.container[
.column[
![MVC](/assets/mvc.svg)
]

.column.w60[
**Model**: 
- current data and state of the app
- Java program

**View**: 
- visualization of data and state
- Android widget library

**Controller**: 
- business logic (by you)
- user input (provided by Android OS)
]
]

.container[
_Sometimes you will see Model-View-Viewcontroller (MVVC) or Model-View-Viewmodel (MVVM), adding an intermediate layer._
]

---

# Model

Data structures, entity types, auxiliary types.

Core algorithms to load, store, organize and transform data.*

Typically implemented in (pure) Java.**

Examples:
- `Joke` class to store jokes from ICNDB
- networking code to retrieve jokes from ICNDB
- internal cache to store jokes

.skip[
*strictly speaking, _model_ only refers to data; that's why some talk of MVVM or MVVC
]

.skip[
**you can also use meta languages or reference native libraries
]

---

# View

What you _see_ on when you open the app.

Text views, buttons, lists, images, etc.

Typically implemented using a certain XML format, which is then "inflated" by a loader program.

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:layout_width="fill_parent"
              android:layout_height="fill_parent"
              android:orientation="vertical" >
    <TextView android:id="@+id/text"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:text="I am a TextView" />
    <Button android:id="@+id/button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="I am a Button" />
</LinearLayout>
```

---

# Controller

Manipulate the model using user or system input.

User input: button clicks, swipe-for-refresh, etc.

System signals: power or network configuration changes, interrupts

Typically implemented in Java, by triggering certain logic on a certain event.

---

# Android: Basic Building Blocks (1)

- see the [base project](https://github.com/hsro-inf-prg3/07-composite-observer) for this weeks assignment
- an _app_ consists of at least one _activity_ or _service_
	+ services run in the background (not covered here)
	+ activities run in the foreground, and require a main view

- no more `public static void main(String... args)`, but main activity
	+ configured in `AndroidManifest.xml`, typically `MainActivity`

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // this will inflate the layout from res/layout/activity_main.xml
        setContentView(R.layout.activity_main);
        
        // add your code to be run at startup here
    }
}
```

---

# Basic Building Blocks (2)

Every activity needs a layout.

`R.layout.activity_main` points to `res/layout/activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="de.fhro.inf.prg3.a07.MainActivity">
    <TextView
    	android:id="@+id/myTextView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</android.support.constraint.ConstraintLayout>
```

---

# Basic Components

<https://developer.android.com/guide/topics/ui/controls.html>

![ui-controls](/assets/ui-controls.png)

- `TextField` (single and multiline)
- `TextInput`
- `Button`
- `CheckBox` and `RadioButton`
- `ListView`

---

# Referencing Components on the Screen

You can get a handle on the components rendered on the screen.
- set the `android:id` field in the XML layout
- inside the activity code, use the `findViewById()` function with that id

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // see android:id="@+id/myTextView"
        TextView tv = (TextView) findViewById(R.id.myTextView);
    }
}
```

---

# Wiring Components and User Input

<https://developer.android.com/guide/topics/ui/ui-events.html>

Components can react to certain user input, for example
- _click_, using the `setOnClickListener()`
- _long click_, using the `setOnLongClickListener()`
- and a few others

```java
Button button = (Button) findViewById(R.id.myButton);
button.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View v) {
		// do something
	}
});
```

---

# A Word on Logging

`System.out` etc. don't work (no terminal, no service!)

Use a _toast_ instead:

```java
Context context = getApplicationContext();
CharSequence text = "Hello toast!";
int duration = Toast.LENGTH_SHORT;

Toast toast = Toast.makeText(context, text, duration);
toast.show();
```

Or use system logging services (rendered to logcat):

```java
import import java.util.logging.Logger;
// ...
Logger logger = Logger.getLogger(OpenMensaAPITests.class.getName());
logger.info("Hello, world!");
```

---

# Some Peculiarities

- unless you actively terminate apps, they won't terminate (until the OS decides to kill them)
- when you launch an app, you actually launch an activity (the app may already be running)
- when cycling activities, they may actually be recreated
- rotation events cause activities to be recreated
- apps (sic!) have separate threads for GUI, services and logic
	+ you can't run IO (networking, files) on the GUI thread
	+ you can run services without an open activity (think Dropbox!)
- getting from one activity to another, you need to [understand the intent mechanism](https://developer.android.com/guide/components/intents-filters.html)
