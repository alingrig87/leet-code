# Soluție Detaliată - Remove Outermost Parentheses

## Ce Ne Cere Problema?

Problema ne cere să eliminăm parantezele exterioare (outermost) din fiecare "primitive string" dintr-un string format din mai multe primitive strings concatenate. Un primitive string este un string de paranteze care este "balanced" (echilibrat) și nu poate fi împărțit în subșiruri balanced mai mici.

De exemplu, dacă avem `"(()())(())"`:
- Primitive strings: `"(()())"` și `"(())"`
- După eliminarea parantezelor exterioare: `"()()"` și `"()"`
- Rezultat: `"()()()"`

## Ce Este "Depth" (Adâncimea)?

Depth (adâncimea) este numărul de paranteze deschise care nu au fost închise încă. De exemplu, în `"(()())"`:
- La început: depth = 0
- După prima '(': depth = 1
- După a doua '(': depth = 2
- După prima ')': depth = 1
- După a treia '(': depth = 2
- După a doua ')': depth = 1
- După a treia ')': depth = 0

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Folosim un counter pentru a ține minte adâncimea (depth)
2. Când întâlnim '(', incrementăm depth
3. Când întâlnim ')', decrementăm depth
4. Adăugăm parantezele doar dacă depth > 0 (nu sunt paranteze exterioare)

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public String removeOuterParentheses(String s) {
```

Această linie definește funcția `removeOuterParentheses`. Funcția primește:
- `s` - string-ul de procesat

Funcția returnează `String` - string-ul fără paranteze exterioare.

```java
    StringBuilder result = new StringBuilder();
    int depth = 0;
```

Aceste linii inițializează variabilele. Să explicăm:

- `StringBuilder result` va construi string-ul rezultat
- `depth = 0` inițializează adâncimea la 0

```java
    for (char c : s.toCharArray()) {
```

Această buclă parcurge fiecare caracter din string. `c` este variabila care stochează caracterul curent.

```java
        if (c == '(') {
```

Această condiție verifică dacă caracterul curent este o paranteză deschisă.

```java
            if (depth > 0) {
                result.append(c);
            }
            depth++;
```

Aceste linii tratează paranteza deschisă. Să explicăm:

- `depth > 0` verifică dacă adâncimea este mai mare decât 0
- Dacă da, înseamnă că nu suntem la nivelul exterior (outermost), deci adăugăm paranteza
- `depth++` incrementează adâncimea (am deschis o paranteză)

De exemplu, dacă `depth = 0` și întâlnim '(', aceasta este o paranteză exterioară, deci nu o adăugăm. Dacă `depth = 1` și întâlnim '(', aceasta nu este exterioară, deci o adăugăm.

```java
        } else {
            depth--;
            if (depth > 0) {
                result.append(c);
            }
        }
```

Această parte tratează paranteza închisă. Să explicăm:

- `depth--` decrementează adâncimea (am închis o paranteză)
- `depth > 0` verifică dacă adâncimea este mai mare decât 0
- Dacă da, înseamnă că nu suntem la nivelul exterior, deci adăugăm paranteza

De ce decrementăm înainte de a verifica? Pentru că vrem să verificăm adâncimea după ce am închis paranteza. Dacă `depth = 1` și întâlnim ')', după decrementare `depth = 0`, deci nu adăugăm paranteza (este exterioară).

```java
    return result.toString();
```

Această linie returnează string-ul rezultat construit cu StringBuilder.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `s = "(()())(())"`:

**Inițializare:**
- `result = ""`, `depth = 0`

**Iterația 1 (c = '('):**
- `depth = 0`, nu adăugăm (este exterioară)
- `depth++` → `depth = 1`

**Iterația 2 (c = '('):**
- `depth = 1 > 0`, adăugăm '('
- `result = "("`
- `depth++` → `depth = 2`

**Iterația 3 (c = ')'):**
- `depth--` → `depth = 1`
- `depth = 1 > 0`, adăugăm ')'
- `result = "()"`

**Iterația 4 (c = '('):**
- `depth = 1 > 0`, adăugăm '('
- `result = "()("`
- `depth++` → `depth = 2`

**Iterația 5 (c = ')'):**
- `depth--` → `depth = 1`
- `depth = 1 > 0`, adăugăm ')'
- `result = "()()"`

**Iterația 6 (c = ')'):**
- `depth--` → `depth = 0`
- `depth = 0`, nu adăugăm (este exterioară)

**Iterația 7 (c = '('):**
- `depth = 0`, nu adăugăm (este exterioară)
- `depth++` → `depth = 1`

**Iterația 8 (c = '('):**
- `depth = 1 > 0`, adăugăm '('
- `result = "()()("`
- `depth++` → `depth = 2`

**Iterația 9 (c = ')'):**
- `depth--` → `depth = 1`
- `depth = 1 > 0`, adăugăm ')'
- `result = "()()()"`

**Iterația 10 (c = ')'):**
- `depth--` → `depth = 0`
- `depth = 0`, nu adăugăm (este exterioară)

**Rezultat:** `"()()()"` - parantezele exterioare au fost eliminate.

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Parcurgem string-ul o singură dată, făcând O(1) operații pentru fiecare caracter.

2. **O(1) spațiu**: Folosim doar câteva variabile (excludând string-ul rezultat).

3. **Simplă și directă**: Folosim un counter pentru adâncime, eliminând parantezele exterioare automat.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este lungimea string-ului. Parcurgem string-ul o singură dată.

- **Complexitatea spațiului**: O(n) - string-ul rezultat poate avea lungimea n (excludând parantezele exterioare).

## Concluzie

Această soluție este simplă și eficientă. Folosim un counter pentru adâncime pentru a identifica parantezele exterioare (când depth = 0 sau devine 0), eliminându-le automat și păstrând doar parantezele interioare.
