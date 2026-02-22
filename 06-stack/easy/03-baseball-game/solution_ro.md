# Soluție Detaliată - Baseball Game

## Ce Ne Cere Problema?

Problema ne cere să calculăm scorul unui joc de baseball bazat pe o listă de operații. Operațiile sunt:
- Un număr întreg: adaugă acest număr la scor
- "C": anulează (invalidate) ultimul scor (șterge-l)
- "D": dublează ultimul scor (adaugă 2 * ultimul scor)
- "+": adaugă suma ultimelor două scoruri

De exemplu, pentru `["5", "2", "C", "D", "+"]`:
- "5" → scoruri: [5]
- "2" → scoruri: [5, 2]
- "C" → anulează 2, scoruri: [5]
- "D" → dublează 5, scoruri: [5, 10]
- "+" → adaugă 5+10=15, scoruri: [5, 10, 15]
- Scor total: 5 + 10 + 15 = 30

## De Ce Folosim un Stack?

Un stack este perfect pentru această problemă pentru că:
- Trebuie să accesăm ultimul element (pentru "C" și "D")
- Trebuie să accesăm ultimele două elemente (pentru "+")
- Stack-ul ne permite să accesăm rapid elementele de la vârf

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Folosim un stack pentru a stoca scorurile
2. Pentru fiecare operație:
   - Dacă este un număr, îl adăugăm în stack
   - Dacă este "C", scoatem ultimul element din stack
   - Dacă este "D", dublăm ultimul element și îl adăugăm
   - Dacă este "+", adunăm ultimele două elemente și adăugăm suma
3. La final, sumăm toate elementele din stack

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int calPoints(String[] ops) {
```

Această linie definește funcția `calPoints`. Funcția primește:
- `ops` - array-ul de operații (string-uri)

Funcția returnează `int` - scorul total.

```java
    Stack<Integer> stack = new Stack<>();
```

Această linie creează un stack gol pentru a stoca scorurile. Stack-ul va păstra toate scorurile valide în ordinea în care au fost adăugate.

```java
    for (String op : ops) {
```

Această buclă parcurge fiecare operație din array. `op` este variabila care stochează operația curentă.

```java
        if (op.equals("C")) {
            stack.pop();
        }
```

Această parte tratează operația "C" (anulează). Să explicăm:

- `op.equals("C")` verifică dacă operația curentă este "C"
- `stack.pop()` scoate ultimul element din stack (anulează ultimul scor)

De exemplu, dacă stack-ul este `[5, 2]`, după `stack.pop()`, stack-ul devine `[5]`.

```java
        else if (op.equals("D")) {
            stack.push(2 * stack.peek());
        }
```

Această parte tratează operația "D" (dublează). Să explicăm:

- `op.equals("D")` verifică dacă operația curentă este "D"
- `stack.peek()` returnează ultimul element din stack fără să îl scoată
- `2 * stack.peek()` calculează dublul ultimului scor
- `stack.push(...)` adaugă dublul în stack

De exemplu, dacă stack-ul este `[5]`:
- `stack.peek()` → 5
- `2 * 5 = 10`
- `stack.push(10)` → stack devine `[5, 10]`

```java
        else if (op.equals("+")) {
            int top = stack.pop();
            int sum = top + stack.peek();
            stack.push(top);
            stack.push(sum);
        }
```

Această parte tratează operația "+" (adună ultimele două). Să explicăm pas cu pas:

- `op.equals("+")` verifică dacă operația curentă este "+"
- `int top = stack.pop()` scoate ultimul element și îl salvează
- `int sum = top + stack.peek()` calculează suma ultimului element (care tocmai l-am scos) și penultimului element (care este acum în vârful stack-ului)
- `stack.push(top)` pune înapoi ultimul element (pentru că l-am scos doar temporar)
- `stack.push(sum)` adaugă suma în stack

De exemplu, dacă stack-ul este `[5, 2]`:
- `top = stack.pop()` → `top = 2`, stack devine `[5]`
- `sum = 2 + 5 = 7`
- `stack.push(2)` → stack devine `[5, 2]`
- `stack.push(7)` → stack devine `[5, 2, 7]`

De ce punem înapoi `top`? Pentru că vrem să păstrăm toate scorurile anterioare, iar suma este un scor nou care se adaugă.

```java
        else {
            stack.push(Integer.parseInt(op));
        }
```

Această parte tratează cazul când operația este un număr. Să explicăm:

- `else` înseamnă că operația nu este "C", "D" sau "+", deci este un număr
- `Integer.parseInt(op)` convertește string-ul la număr întreg
- `stack.push(...)` adaugă numărul în stack

De exemplu, dacă `op = "5"`, atunci `Integer.parseInt("5") = 5`, și `stack.push(5)` adaugă 5 în stack.

```java
    int sum = 0;
    while (!stack.isEmpty()) {
        sum += stack.pop();
    }
    return sum;
```

Aceste linii calculează scorul total. Să explicăm:

- `int sum = 0` inițializează suma la 0
- `while (!stack.isEmpty())` continuă cât timp stack-ul nu este gol
- `sum += stack.pop()` adaugă fiecare element din stack la sumă (și îl scoate din stack)
- `return sum` returnează scorul total

De exemplu, dacă stack-ul este `[5, 10, 15]`:
- Iterația 1: `sum = 0 + 15 = 15`
- Iterația 2: `sum = 15 + 10 = 25`
- Iterația 3: `sum = 25 + 5 = 30`
- Returnăm 30

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `ops = ["5", "2", "C", "D", "+"]`:

**Inițializare:**
- `stack = []`

**Iterația 1 (op = "5"):**
- `op.equals("C")`? `false`
- `op.equals("D")`? `false`
- `op.equals("+")`? `false`
- `else`: `stack.push(5)` → `stack = [5]`

**Iterația 2 (op = "2"):**
- `else`: `stack.push(2)` → `stack = [5, 2]`

**Iterația 3 (op = "C"):**
- `op.equals("C")`? `true`
- `stack.pop()` → scoate 2, `stack = [5]`

**Iterația 4 (op = "D"):**
- `op.equals("D")`? `true`
- `stack.peek()` → 5
- `stack.push(2 * 5)` → `stack.push(10)` → `stack = [5, 10]`

**Iterația 5 (op = "+"):**
- `op.equals("+")`? `true`
- `top = stack.pop()` → `top = 10`, `stack = [5]`
- `sum = 10 + 5 = 15`
- `stack.push(10)` → `stack = [5, 10]`
- `stack.push(15)` → `stack = [5, 10, 15]`

**După toate iterațiile:**
- `stack = [5, 10, 15]`
- Sumăm: `5 + 10 + 15 = 30`
- Returnăm `30`

**Rezultat:** `30` - scorul total este 30.

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Parcurgem array-ul de operații o singură dată, făcând O(1) operații pentru fiecare operație.

2. **O(n) spațiu**: Stack-ul poate conține cel mult n elemente (dacă toate operațiile sunt numere).

3. **Simplă și directă**: Folosim un stack pentru a gestiona scorurile, ceea ce este perfect pentru accesarea ultimelor elemente.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de operații. Parcurgem array-ul o singură dată.

- **Complexitatea spațiului**: O(n) - stack-ul poate conține cel mult n elemente.

## Concluzie

Această soluție este simplă și eficientă. Folosim un stack pentru a gestiona scorurile, procesând fiecare operație și calculând scorul total la final.
