class: title-slide  

# Modul- Fortgeschrittene Programmierkonzepte
### Bachelor Informatik

## 02- Classes und Interfaces
### Prof. Dr. Marcel Tilly
Fakultät für Informatik, Cloud Computing

---

---

# Classes and Interfaces Revisited

- information hiding, packages and accessibility
- interfaces revisited
- static classes
- nested classes
- lambda and method references

---

# Method References

| Kind	| Example
|-------|--------
|Reference to a static method	| `ContainingClass::staticMethodName`
|Reference to an instance method of a particular object	| `containingObject::instanceMethodName`
| Reference to an instance method of an arbitrary object of a particular type	| `ContainingType::methodName`
| Reference to a constructor	| `ClassName::new`

