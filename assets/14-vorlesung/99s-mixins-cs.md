class: title-slide  

# Modul- Fortgeschrittene Programmierkonzepte
### Bachelor Informatik

## 14- Mixins in C#
### Prof. Dr. Marcel Tilly
Fakultät für Informatik, Cloud Computing

---

# Wiederholung: Decorator Pattern

Klassen um Funktionalität erweitern ("dekorieren").

<div>
	<div style="float: left; width: 45%; height: 100%">
	    <img alt="decorator pattern" src="../assets/dp-decorator.svg" style="width: 100%">
	</div>
	<div style="width: 55%; float: left">
		<ul>
			<li>Gemeinsame Basisklasse bzw. Interface</li>
			<li>Aufrufe an <span class="remark-code remark-code-line">delegate</span> weiterleiten</li>
			<li>Methoden hinzufügen oder überladen</li>
			<li>Vorallem für Kaskadierung geeignet</li>
		</ul>
	</div>
</div>

<div style="clear: both"></div>

```java
FileInputStream fis = new FileInputStream("/objects.gz");
BufferedInputStream bis = new BufferedInputStream(fis);
GzipInputStream gis = new GzipInputStream(bis);
ObjectInputStream ois = new ObjectInputStream(gis);
SomeObject someObject = (SomeObject) ois.readObject();
```

---

# Wiederholung: Vererbung und Interfaces

Beispiel:

- Wir können einfache Nachrichten (`Message`) verschicken
- `EscalatedMessage` hat entsprechend Großbuchstaben (_schreien_).
- `UnicodeMessage` um Smileys ausgeben zu können.

.center[
<img src="../assets/mixin-diamond-1.svg">
]

#### Was nun, wenn eine `UnicodeMessage` auch "eskalierbar" sein soll?


---

# Wiederholung: Vererbung und Interfaces

Um die Typhierarchie beizubehalten wird nun ein Interface `IEscalatable` eingeführt, so dass für mehrere Klassen `is-a IEscalatable` gelten kann:

.center[
<img src="../assets/mixin-diamond-2.svg">
]

#### Nachteil: Code muss doppelt implementiert werden!

---

# Mixins

In der OOP sind _Mixins_ Klassen, welche 

- in andere Klassen _eingebunden_ werden können, ohne aber die Basisklasse zu sein
- nur in Kombination mit den Zielklassen sinnvoll sind

In C# können Mixins (weitgehend) durch _extension methods_ realisiert werden.


#### Ziel

- **Eine** Implementierung für **alle** passenden Klassen
- `has-a` bzw. `can-do` anstatt `is-a`

#### Verwandte Themen

- _Aspect Oriented Programming (AOP):_ Funktionalitäten als _Aspekte_
- _Dependency Inversion_

---

<h1>Mixins in C&num;</h1>

Realisierung durch Erweiterungsmethoden (_extension methods_).

```csharp
public class Message {
	public string Text { get; set; }
}
public `static class` Mixins {
	`public static` string Escalated(`this Message self`) {
  		return self.Text.ToUpper();
	}
}
```

- Statische Methode in statischer Klasse (i.d.R. `public`)
- Erstes Argument vom Zieltyp mit Modifier `this`
- Compiler "sammelt" alle Erweiterungsmethoden

```csharp
Message m = new Message();
m.Text = "Hello, world";

Console.WriteLine(`m.Escalated()`);        // "HELLO, WORLD"
Console.WriteLine(`Mixins.Escalated(m)`);  // äquivalent
```

---

# Zustandsbehaftete Mixins

Die aktuelle Implementierung ist recht übersichtlich -- und zustandslos.

Was ist, wenn man nun "immer weiter" eskalieren möchte, mit immer mehr Ausrufezeichen?

```
Hallo, Welt
HALLO, WELT
HALLO, WELT!
HALLO, WELT!!
HALLO, WELT!!!
```

---

# Zustandsbehaftete Mixins

Verwende `ConditionalWeakTable` als Zustandsspeicher.

Für jeden Aufrufer wird der Zustand gespeichert.

```csharp
public class Message {
	public string Text { get; set; }
}

public static class Mixins {
	private static readonly ConditionalWeakTable<Message, object> state_ =
		new ConditionalWeakTable<Message, object>();
	
	public static string Escalate(this Message self) {
		object n;

		// gibts schon einen Zustand für self?
		if (!state_.TryGetValue(self, out n))
			n = 0;

		string message = self.Text.ToUpper() + new String('!', (int) n);
		
		// neuen Zustand für self abspeichern
		state_.AddOrUpdate(self, 1 + (int) n);
		return message;
	}
}
```

---

# Zustandsbehaftete Mixins

Verwende `ConditionalWeakTable` als Zustandsspeicher.

Für jeden Aufrufer wird der Zustand gespeichert.

```csharp
class Program {
	static void Main(string[] args) {
		Message m1 = new Message();
        Message m2 = new Message();
        
        m1.Text = "Mach hin";
        m2.Text = "Schneller";

        Console.WriteLine(m1.Text);         // "Mach hin"
        Console.WriteLine(m1.Escalated());  // "MACH HIN"
        Console.WriteLine(m1.Escalated());  // "MACH HIN!"
        
        Console.WriteLine(m2.Text);         // "Schneller"
        Console.WriteLine(m2.Escalated());  // "SCHNELLER"
        Console.WriteLine(m2.Escalated());  // "SCHNELLER!"
	}
}
```

---

# Sichtbarkeiten

Da Erweiterungsmethoden nur syntaktischer Zucker sind, gelten die Sichtbarkeiten wie gehabt:

```csharp
public class Message {
	public string Text { get; set; }
	private int id;
}

public static class Mixins {
	public static void Debug(this Message self) {
		self.Text;  // OK
		self.id;    // `Compilerfehler: not visible!`
	}
}
```

**Daher:** Es ist sinnvoll Erweiterungsmethoden gegen Interfaces zu implementieren, damit

- alle benötigten Attribute eingesehen werden können
- das Mixin flexibel über das Interface "angeheftet" werden kann


---

# Binden und Überschreiben

Erweiterungsmethoden _binden wenn nötig_ aber _überschreiben **nicht**_.

```csharp
class Message {
	public string Text { get; set; }
	public string Escalated() {
		Text.ToLower();
	}
}

static class Mixins {
	public static string Escalated(this Message self) {
		self.Text.ToUpper();
	}
}
```

```csharp
Message m = new Message();
m.Text = "Hello, world";

Console.WriteLine(m.Escalated());
Console.WriteLine(Mixins.Escalated(m));
```

#### Ausgabe?

---

# Binden und Überschreiben

Erweiterungsmethoden _binden wenn nötig_ aber _überschreiben **nicht**_.

```csharp
class Message {
	public string Text { get; set; }
	public string Escalated() {
		Text.ToLower();
	}
}

static class Mixins {
	public static string Escalated(this Message self) {
		self.Text.ToUpper();
	}
}
```

```csharp
Message m = new Message();
m.Text = "Hello, world";

Console.WriteLine(m.Escalated());
Console.WriteLine(Mixins.Escalated(m));
```

```
hello, world
HELLO, WORLD
```

---

# Anheften von Mixins

Implementiere Mixin für Interface (Dependency Inversion!)...

```csharp
public interface IEscalatable {
	string Text { get; }
}

public static class EscalatableMixin {
	public static string Escalated(this `IEscalatable` self) {
		return self.Text.ToUpper();
	}
}
```

...und hefte es später an Klassen:

```csharp
public class EscalatedMessage : Message, IEscalatable {}

public class EscalatableUnicodeMessage : UnicodeMessage, IEscalatable {}
```


---

# Zusammenfassung

- _"Favor composition over inheritance"_
- _Mixins_ erweitern _bestehende_ Klassen _ohne_ eine verwandschaftliche Beziehung herzustellen
- Für C# gilt:
	- Mixins können zu einem gewissen Grad mit _extension methods_ realisiert werden.
	- Die Einbindung einer `ConditionalWeakTable` erlaubt es instanzspezifische Zustandsinformation zu unterhalten.
	- Extension methods binden, aber überschreiben nicht; es gelten die spezifizierten Sichtbarkeiten
- Vergleiche auch: 
	- Traits in Scala
	- Mehrfachvererbung in C++ 
	- Defaultmethoden in Java (ohne Syntaxzucker)

---

# Weiterführende Informationen

#### Dokumentation

- <https://docs.microsoft.com/en-us/dotnet/csharp/programming-guide/classes-and-structs/extension-methods>

#### Literatur

- Craig, Iain (2007). Object-Oriented Programming Languages: Interpretation. Springer.
- Gamma, Erich; Helm, Richard; Johnson, Ralph; Vlissides, John (1994). Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley.

#### Frameworks/Toolkits:

- https://www.postsharp.net/
- https://github.com/re-motion/Remix


---

# Vorsicht mit autom. Typumwandlung (1)

```csharp
class Vorsicht {
	public void Obacht(object o)
		=> Console.WriteLine("Vorsicht.Obacht: {0}", o.GetType());
}
static class VorsichtMixin {
	public static void Obacht(this Vorsicht self, int i)
		=> Console.writeLine("VorsichtMixin.Obacht: {0}", i);


	static void Main(string[] args) {
		Vorsicht v = new Vorsicht();
		v.Obacht(3);
		// "Vorsicht.Obacht: System.Int32"
		
		v.Obacht("Hans");
		// "Vorsicht.Obacht: System.String"
	}
}
```

**Vorsicht:** Der Typ `object` trifft hier auf alle Argumente zu!


---

# Vorsicht mit autom. Typumwandlung (2)

```csharp
class Vorsicht {
	public void Obacht(`int i`)
		=> Console.WriteLine("Vorsicht.Obacht: {0}", i);
}
static class VorsichtMixin {
	public static void Obacht(this Vorsicht self, `object o`)
		=> Console.writeLine("VorsichtMixin.Obacht: {0}", o.GetType());


	static void Main(string[] args) {
		Vorsicht v = new Vorsicht();
		v.Obacht(3);
		// `Vorsicht.Obacht: System.Int32`

		v.Obacht("Hans");
		// `VorsichtMixin.Obacht: System.String`
	}
}
```
