# Fortgeschrittene Programmierkonzepte (aka Programmieren 3)


_Pflichtmodul im [Bachelorstudiengang Informatik](https://www.th-rosenheim.de/technik/informatik-mathematik/informatik-bachelor/) an der [Hochschule Rosenheim](https://www.th-rosenheim.de)._

## Literatur

- Bloch: [Effective Java](https://www.amazon.de/Effective-Java-2nd-Programming-Language/dp/0321356683/)
- Oaks: [Java Performance](https://www.amazon.de/Java-Performance-The-Definitive-Guide/dp/1449358454/)
- Gamma _et al._: [Design Patterns](https://www.amazon.de/Patterns-Elements-Reusable-Object-Oriented-Software/dp/0201633612/)
- Subramaniam: [Functional Programming in Java](https://www.amazon.de/Functional-Programming-Java-Harnessing-Expressions/dp/1937785467/)
- Siedersleben: [Moderne Softwarearchitektur](https://www.amazon.de/Moderne-Software-Architektur-Umsichtig-planen-robust/dp/3898642925/)

### ausserdem hilfreich

- Peter's [UML/PlantUML guide](./plantuml-guide)


## Vorlesungen

- **Einführung und Organisatorisches ([Slides](/01s-intro/), [Übungen](https://github.com/hsro-inf-fpk/01-tools/))**
	
	Nach einer kurzen Einführung werden wir uns an Hand einiger Beispiele die notwendigen Tools im Java Umfeld ansehen, damit wir für das Semester das Rüstzeug eines Softwareingenieur zur Hand haben: Git, IntelliJ, [Google](https://www.google.com), [SO](https://www.stackoverflow.com) und [Java docs](http://docs.oracle.com/javase/8/docs/). Fehlt noch was?.

- **Klassen und Interfaces (als Wiederholung!) ([Slides](/02s-classes-interfaces/), [Skript](/02ln-classes-interfaces/), [Übungen](https://github.com/hsro-inf-fpk/02-classes-interfaces/))**
	
	In dieser Session sehen wir uns verschiedene Typen von Klassen an: inner, anonymous, local, und static Klassen. Wann wird was benutzt und welche Sichtbarkeit haben sie?
	Ausserdem: `@FunctionalInterface` und Lambda-Expressions.

- **Vererbung (Wiederholung!) ([Slides](/03s-inheritance/), [Skript](/03ln-inheritance/), [Übungen](https://github.com/hsro-inf-fpk/03-inheritance))**
	
	Eine weitere Session, die sich mit Klassen beschäftigt. Allerdings mehr aus Sicht der Veerbung (ein wichtiges Konzept der OOP!). Hierzu sehen wir uns final und abstract Classes an, sowie virtuelle Funktionen und defaults.
	Mit einem ersten Design-Pattern (Decorator) sollte das Thema dann abgerundet sein.
	
- **Mixins (Teil 1) und Generics (Teil 1) ([Slides](/04s-generics-1/), [Skript](/04ln-generics-1/), [Übungen](https://github.com/hsro-inf-fpk/04-generics))**
	
	Nach einem kurzen Ausflug zu Mixins, stürzen wir uns natürlich voller Freude in das Thema "Generics". Wie werden diese in Datastrukturen und Algorithmen angewendet.

- **Mixins (Teil 2) und Generics (Teil 2) ([Slides](/05s-generics-2/), [Skript](/05ln-generics-2/), [Übungen](https://github.com/hsro-inf-fpk/05-generic-bounds))**
	
	Mixins zum zweiten und ein paar Sonderfälle bei Generics (Bounds und Wildcards) bestimmen diese Session.

- **Reflection und Annotations ([Slides](/06s-reflection-annotations/), [Skript](/06ln-reflection-annotations/), [Übungen](https://github.com/hsro-inf-fpk/06-annotations-reflection))**
	
	Mit dem Reflection API und Annotationen werden wir uns in dieser Session beschäftigen. Das gelingt am Besten an Hand von einigen Beispielen: Unit Tests mit ([JUnit5](http://junit.org/junit5/)), Serialization mit ([gson](https://github.com/google/gson)) und Networking mit ([retrofit](https://github.com/square/retrofit)).

- **Design Pattern (Teil 1) ([Slides](/07s-iterator-composite-observer/), [Skript](/07ln-iterator-composite-observer/), Übungen: [JavaFX (recommended)](https://github.com/hsro-inf-fpk/07-composite-observer-jfx) or [Android (advanced)](https://github.com/hsro-inf-fpk/07-composite-observer))**

	Fun with Pattern! In dieser Session starten wir endlich mit Design-Pattern im Software Engeneering: Composite-, Iterator- und Observer-Pattern (ach ja, und wie kann man damit ein UI implementieren auf Android). Was ist eigentlich MVC/MVVC?

- **Design Pattern, pt. 2 ([Slides](/08s-singleton-factory-strategy-command/), [Skript](/08ln-singleton-factory-strategy-command/), Übungen: [JavaFX](https://github.com/hsro-inf-fpk/08-singleton-factory-strategy-jfx) or [Android](https://github.com/hsro-inf-fpk/08-singleton-factory-strategy-android))**

	Mehr Pattern: Singleton-, Factory-, Strategy- und Command-Pattern.

- **Design Pattern, pt. 3 ([Slides](/09s-proxy-adapter-flyweight/), [Skript](/09ln-proxy-adapter-flyweight/), Übungen: [JavaFX](https://github.com/hsro-inf-fpk/09-adapter-flyweight-jfx), [Android](https://github.com/hsro-inf-fpk/09-adapter-flyweight-android))**
	
	Last but not least! Zum Abschluß der Pattern-Reihe: Proxy-, Adapter- und Flyweight-Pattern.

- **Parallel Processing (Teil 1) ([Slides](/10s-threads/), [Skript](/10ln-threads/), Übungen: [JavaFX](https://github.com/hsro-inf-fpk/10-threads-jfx) or [Android](https://github.com/hsro-inf-fpk/10-threads-android))**

	Parallele Ausführung von Programcode ist ein wichtiges Konzept. In dieser Session steigen wir ein in Threads und parallele Verarbeitung.

- **Parallel Processing (Teil 2) ([Slides](/11s-futures/), [Skript](/11ln-futures/), Übungen: [Terminal](https://github.com/hsro-inf-fpk/11-futures-cli) or [Android](https://github.com/hsro-inf-fpk/11-futures-android))**
	
	`Thread`s are clunky--- erweiterte Konzepte, wie `Future` oder `Promises` werden in dieser Session behandelt.

- **Einführung in die funktionale Programmierung ([Slides](/12s-fp1/), [Skript](/12ln-fp1/), [Übungen](https://github.com/hsro-inf-fpk/12-functional-cli))**
	
	Nachdem wir uns nun fast 3 Semester mit der imperativen und objektorientierten Programmierung beschäftigt haben, verlassen wir nun unsere Komfortzone und schauen uns Konzepte der funktionalen Programmierung an.
	Natürlich haben wir zunächst ein wenig Spaß mit der Theorie bevor wir uns so tollen Dingen wie `filter`, `map` und `forEach` zu wenden.

- **Functional programming in Java ([Slides](/13s-fp2/), [Skript](/13ln-fp2/), [Übungen](https://github.com/hsro-inf-fpk/13-map-reduce-collect))**

	Eine weitere Session zu funktionaler Programmierung. Was ist besonders und wo sind die Grenzen? Warum ist es gerade gar so populär?
	Ein nette Besonderheit, wie funktionale Programmierung in OO-Sprachen integriert wird, sind natürlich Klassen und Interfaces. Das schauen wir uns an Hand von ´streams´ und wie `reduce` und `collect` darauf angewendet wird.

- **Zusammenfassung und Klausurvorbereitung (Jan 24)**

	Diese Session nutzen wir, um das Semester noch mal grob zu überfliegen und das als Klausurvorbereitung zu nutzen. Sicher eine gute Chance Fragen loszuwerden.
