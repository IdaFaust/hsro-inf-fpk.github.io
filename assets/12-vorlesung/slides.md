class: title-slide  

# Modul- Fortgeschrittene Programmierkonzepte
### Bachelor Informatik

## 12- Functional Porgramming
### Prof. Dr. Marcel Tilly
Fakultät für Informatik, Cloud Computing

---

# Functional Programming

## Immutable Objects

## Functions as First-Class Citizens

---

# Immutable Objects

If objects cannot be changed after their creation, parallelization becomes much easier.

`java.lang.String`
- no methods to change instance
- always returns _new_ instance

`final` modifier for attributes and variable, sort of:
- only prevents overwriting of primitive type or reference
- object may still be mutated

.skip[
> No mutation means no `for`/`while`!

]
---

# Functions as First-Class Citizens

```java
@FunctionalInterface
interface Function<A, B> {
	B apply(A obj);
}
```

```java
Function<Integer, Integer> square1 = new Function<Integer, Integer>() {
	@Override
	public Integer apply(Integer i) {
		return i * i;
	}
}
```

Or shorter as lambda expression `(arglist) -> { block; }`

```java
Function<Integer, Integer> square2 = (Integer i) -> { return i * i };
```

Or even shorter, for single instructions

```java
Function<Integer, Integer> square3 = i -> i * i;
```

---

# Why Functional Programming?

## Immutability simplifies parallelization

## Separation of Concerns

Functions as first-class citizens help to separate the iteration logic from the actual business logic.


---

# Example

Say you want to

- retrieve all students from a database,
- filter out those who took _Programmieren 3_,
- load their transcript of records from another database
- print all class names


---

# Iterative Solution

```java
for (Student s : getStudents()) {
	if (s.getClasses().contains("Programmieren 3")) {
		ToR tor = db.getToR(s.getMatrikel());
		for (Record r : tor) {
			System.out.println(r.getName());
		}
	}
}
```

---

# A Simple Immutable List

`head` stores the data, `tail` links to the next element.

The end of the list is explicitly modeled.

```java
class List<T> {
	final T head;
	final List<T> tail;

	private List(T el, List<T> tail) {
		this.head = el;
		this.tail = tail;
	}

	boolean isEmpty() {
		return head == null;
	}

	// ...
}
```

---

# Some Helper Functions

Some factory functions for convenience:

```java
class List<T> {
	// ...

	static <T> List<T> empty() {
		return new List<T>(null, null);
	}

	static <T> List<T> list(T elem, List<T> xs) {
		return new List<>(elem, xs);
	}
	
	static <T> List<T> list(T... elements) {
		if (elements.length == 0)
			return empty();
		int i = elements.length - 1;
		List<T> xs = list(elements[i], empty());
		while (--i >= 0)
			xs = list(elements[i], xs);
		return xs;
	}
}
```

---

# Recursive Sort Algorithms

---

# Recursive Sort Algorithms

## Insertion Sort

```java
static <T extends Comparable<T>> List<T> isort(List<T> xs) {
	if (xs.isEmpty()) return xs;
	else return insert(xs.head, isort(xs.tail));
}

private static <T extends Comparable<T>> List<T> insert(T x, List<T> xs) {
	if (xs.isEmpty()) return list(x, empty());
	else {
		if (x.compareTo(xs.head) < 0) return list(x, xs);
		else return list(xs.head, insert(x, xs.tail));
	}
}
```

---

# Recursive Sort Algorithms

## Merge Sort

```java
static <T extends Comparable<T>> List<T> msort(List<T> xs) {
	if (xs.isEmpty()) return xs;            // no element at all
	else if (xs.tail.isEmpty()) return xs;  // only single element
	else {
		int n = length(xs);
		List<T> a = take(xs, n/2);
		List<T> b = drop(xs, n/2);

		return merge(msort(a), msort(b));
	}
}

private static <T extends Comparable<T>> List<T> merge(List<T> xs, List<T> ys) {
	if (xs.isEmpty()) return ys;
	else if (ys.isEmpty()) return xs;
	else {
		if (xs.head.compareTo(ys.head) < 0)
			return list(xs.head, merge(xs.tail, ys));
		else
			return list(ys.head, merge(xs, ys.tail));
	}
}
```

---

# Anonymous Classes, Lambda, References

```java
static <A> void forEach(List<A> xs, Consumer<A> c) {
	if (xs.isEmpty()) return;
	else {
		c.accept(xs.head);
		forEach(xs.tail, c);
	}
}
```

And here's a `Consumer` that prints elements to `System.out`:

```java
List<Integer> xs = list(1, 2, 3, 4);
forEach(xs, new Consumer<Integer>() {
	@Override
	public void accept(Integer i) {
		System.out.println(i);
	}
});

// or shorter with lambda
forEach(xs, i -> System.out.println(i));

// or even shorter with method references
forEach(xs, System.out::println);
```

---

# Example

### Iterative Solution (see earlier slide)

```java
for (Student s : Database.getStudents()) {
	if (s.getClasses().contains("Programmieren 3")) {
		Transcript tr = Database.getToR(s.getMatrikel());
		for (Record r : tr)
			System.out.println(r);
	}
}
```

---

# Example

### Functional Solution

```java
Database.getStudents().stream()
	.filter(s -> s.getClasses().contains("Programmieren 3"))
	.map(Student::getMatrikel)
	.map(Database::getToR)
	.flatMap(t -> t.records.stream())  // stream of lists to single list
	.forEach(System.out::println);
```

---

.skip.center[
![wow](/assets/jawdrop.gif)
]
