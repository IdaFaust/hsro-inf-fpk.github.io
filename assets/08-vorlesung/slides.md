class: title-slide  

# Modul- Fortgeschrittene Programmierkonzepte
### Bachelor Informatik

## 08- Design Patterns, pt. 2
### Prof. Dr. Marcel Tilly
Fakultät für Informatik, Cloud Computing

---

# Singleton

Structure to enforce the use of a _unique_ instance.

![dp-singleton](/assets/dp-singleton.svg)

---

# Strategy

---

# Strategy

![kara-explore](/assets/kara-explore.png)

---

# Strategy

```java
public class Kara extends JavaKaraProgram {
	public static void main(String[] args) throws Exception {
		Kara k = new Kara();
		k.run("src/main/resources/world2.world");
	}

	@Override
	public void myMainProgram() {
		kara.move();        // one step forward
		kara.turnLeft();    // you guessed it...
		kara.turnRight();
		kara.treeFront();   // tree ahead?
		kara.putLeaf();     // take a clover leaf
		kara.removeLeav();  // remove a clover leaf		
	}
}
```

How place leafs on every field?

---

# Strategy

Mechanism to provide different implementations to achieve the same outcome.

![dp-strategy](/assets/dp-strategy.svg)

---

# Factory

---

# Factory

Composite pattern:

```json
{
	"key": "value",
	"nested": {
		"key": "value"
	}
}
```

```xml
<element>
	<key>value</key>
	<element>
		<key>value</key>
	</element>
</element>
```

---

# Factory

```java
interface Component {
	String toString();
}
interface Composite extends Component {
	void add(Component c);
}
interface Leaf extends Component {
}
```

```java
JsonComposite root = new JsonComposite("root");
root.add(new JsonLeaf("key", "value"));

Composite nested = new JsonComposite("nested");
nested.add(new JsonLeaf("key", "value"));
root.add(nested);

System.out.println(root);
// "root": {"key": "value", "nested": {"key": "value"}}
```

---

# Factory

Structure to enforce the use of abstract factories and products, by hiding the actual instantiation of the concrete factory and products.

![dp-factory](/assets/dp-abstract-factory.svg)

---

# Command

---

# Command

.w40[
<img class="float-left" src="/assets/dp-command.svg">
]

Mechanism to organize, execute and undo operations on certain objects.
