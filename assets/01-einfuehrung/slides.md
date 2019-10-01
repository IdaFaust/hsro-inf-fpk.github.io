class: title-slide  

# Modul- Fortgeschrittene Programmierkonzepte
### Bachelor Informatik

## 01-Einführung
### Prof. Dr. Marcel Tilly
Fakultät für Informatik, Cloud Computing

---

# Introduction

- Programmieren 3: mandatory class for CS majors
- materials
	- https://hsro-inf-fpk.github.io
	- Github organization: https://github.com/hsro-inf-fpk
	- ...in English! &#x263A;
	- slides, lecture notes, assignments (with Readmes)
- class: Wednesdays, 8a
- tutorials: 
	- tutor: ???
	- Wednesdays 11.45a/1.45p/3.30p
	- sign up [through community](https://www.fh-rosenheim.de/community/inf-community/lehrveranstaltungen/details/?tx_fhalumni_pi1%5Buid%5D=3134#registration)

Materialien auf Englisch, Vorlesung aber auf Deutsch.
Github orga ist closed/BIO--by invitation only

---

# Credits (Leistungsnachweis)

- written exam (90') at the end of the term
- you may bring one (1) book *with* ISBN number and *without* notes
- you need to sign up for the exam (OSC)
- theoretical and coding questions

---

# Review

## Programmieren 1
- imperative programming in C
- constants, variables, expressions, functions, I/O
- basic data structures (fields, arrays, lists)
- pointers &#x263A;

---

# Review

## Programmieren 2
- object-oriented programming (OOP) in Java
- classes and objects (and references! &#x263A;)
- interfaces and inheritance
- errors, exceptions and how to deal with them

---

# Agenda for Programmieren 3

See https://hsro-inf-fpk.github.io/ (Syllabus)


---

# Agenda today

0. _Inform:_<br>
	Your trusted advisors: [Google](https://google.com) -- [SO](https://stackoverflow.com) -- [Java Docs](https://docs.oracle.com/javase/8/docs/) -- [Google Translate](https://translate.google.com/)

1. _Memorize:_<br>
	The git version control system (https://git-scm.com/)

2. _Automate:_<br>
	The Gradle build tool (https://gradle.org/)

3. _Organize:_<br>
	The IntelliJ IDEA (https://www.jetbrains.com/idea/)

4. _(Optional) Collaborate_<br>
	Practice cross-repository pull requests and learn about _continuous integration_ (https://travis-ci.org/)

???

SO = stackoverflow

---

# Memorize: The git VCS

- [Git](https://git-scm.com) is the _de-facto_ state of the art [version control system](https://en.wikipedia.org/wiki/Version_control).
- Some of you might remember [CVS (concurrent versions system)](http://savannah.nongnu.org/projects/cvs) or [subversion](https://subversion.apache.org/), geeks might also know of [mercurial](https://www.mercurial-scm.org/).
- Generally speaking, you should always use a version control system (VCS) when working on code, so you can keep track of changes.
- Print and laminate: https://services.github.com/on-demand/downloads/github-git-cheat-sheet.pdf
- For the more visual: http://ndpsoftware.com/git-cheatsheet.html
- If you run into a mess (and you will): http://justinhileman.info/article/git-pretty/git-pretty.png

---

# Git and feature branches

https://www.atlassian.com/continuous-delivery/continuous-delivery-workflows-with-feature-branching-and-gitflow

![Basic CD Workflow](/assets/CDworkflows_basic2.png)

---

# Automate: The Gradle Build Tool

![GBT](/assets/gradlebuildtool.png)
.center[https://gradle.org]

- `gradle init --type java-application` to bootstrap a project
- `./gradlew build` to use the [Gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) to be independent of locally installed Gradle
- `apply plugin: 'eclipse'` and `./gradlew eclipse` to generate Eclipse project files
- `apply plugin: 'idea'` and `./gradlew idea` to generate IntelliJ files (note: these are file-based project descriptions, not the new directory based `.idea/*`)


---

# IntelliJ

.center[https://www.jetbrains.com/idea/]

---

# Travis CI

- Collaboration means splitting the work
- Teamwork means working together
- Use feature branches and automated tests (JUnit)
- Use automated build and test runner
