# Soluție Detaliată - Valid Parentheses

## Ce Ne Cere Problema?

Problema ne cere să verificăm dacă un string (șir de caractere) format din paranteze este valid. Un string de paranteze este valid dacă:
1. Fiecare paranteză deschisă are o paranteză închisă corespunzătoare
2. Parantezele sunt închise în ordinea corectă
3. Fiecare tip de paranteză se închide cu același tip

De exemplu:
- `"()"` - valid (paranteză rotundă deschisă și închisă)
- `"()[]{}"` - valid (toate parantezele sunt corecte)
- `"(]"` - invalid (paranteză rotundă deschisă, dar paranteză pătrată închisă)
- `"([)]"` - invalid (parantezele nu sunt închise în ordinea corectă)

## Ce Este un Stack?

Un stack (stivă) este o structură de date care funcționează ca o stivă de farfurii - ultimul element adăugat este primul element scos (LIFO - Last In, First Out). Avem două operații principale:
- **Push** - adaugă un element în vârful stivei
- **Pop** - scoate elementul din vârful stivei

## De Ce Folosim un Stack?

Când întâlnim o paranteză deschisă, nu știm imediat dacă este validă - trebuie să așteptăm să vedem dacă apare paranteza închisă corespunzătoare mai târziu. Stack-ul ne permite să ținem minte parantezele deschise în ordinea în care le-am văzut, astfel încât să le putem verifica când întâlnim paranteze închise.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Folosim un stack pentru a ține minte parantezele deschise
2. Parcurgem string-ul caracter cu caracter
3. Când întâlnim o paranteză deschisă, o adăugăm în stack
4. Când întâlnim o paranteză închisă, verificăm dacă se potrivește cu ultima paranteză deschisă din stack
5. Dacă se potrivesc, scoatem paranteza deschisă din stack
6. Dacă nu se potrivesc sau stack-ul este gol, string-ul este invalid
7. La final, stack-ul trebuie să fie gol (toate parantezele au fost închise)

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public boolean isValid(String s) {
```

Această linie definește funcția `isValid`. Funcția primește:
- `s` - string-ul de paranteze de verificat

Funcția returnează `boolean` - `true` dacă string-ul este valid, `false` dacă nu este.

```java
    if (s == null || s.length() == 0) {
        return true;
    }
```

Această verificare tratează cazul când string-ul este null sau gol. Un string gol este considerat valid (nu există paranteze de verificat).

```java
    Stack<Character> stack = new Stack<>();
```

Această linie creează un stack gol pentru a stoca parantezele deschise. Să explicăm:

- `Stack<Character>` este un stack care conține caractere
- `new Stack<>()` creează un stack nou și gol

Stack-ul va stoca parantezele deschise în ordinea în care le-am văzut.

```java
    Map<Character, Character> map = new HashMap<>();
    map.put(')', '(');
    map.put('}', '{');
    map.put(']', '[');
```

Aceste linii creează un map (hartă) care asociază fiecare paranteză închisă cu paranteza deschisă corespunzătoare. Să explicăm:

- `Map<Character, Character>` este un map unde cheia și valoarea sunt caractere
- `map.put(')', '(')` asociază paranteza închisă ')' cu paranteza deschisă '('
- Similar pentru '}' cu '{' și ']' cu '['

De ce facem asta? Pentru a verifica rapid dacă o paranteză închisă se potrivește cu ultima paranteză deschisă din stack.

```java
    for (char c : s.toCharArray()) {
```

Această buclă parcurge fiecare caracter din string. Să explicăm:

- `s.toCharArray()` convertește string-ul într-un array de caractere
- `for (char c : ...)` parcurge fiecare caracter din array
- `c` este variabila care stochează caracterul curent

```java
        if (c == '(' || c == '{' || c == '[') {
            stack.push(c);
        }
```

Această parte tratează parantezele deschise. Să explicăm:

- `c == '(' || c == '{' || c == '['` verifică dacă caracterul curent este o paranteză deschisă
- `||` înseamnă "sau" - dacă caracterul este oricare dintre aceste trei, condiția este adevărată
- `stack.push(c)` adaugă paranteza deschisă în stack

De ce adăugăm parantezele deschise în stack? Pentru că trebuie să le ținem minte până când găsim paranteza închisă corespunzătoare.

```java
        else {
            if (stack.isEmpty() || stack.pop() != map.get(c)) {
                return false;
            }
        }
```

Această parte tratează parantezele închise. Să explicăm pas cu pas:

- `else` înseamnă că caracterul curent este o paranteză închisă (nu este deschisă)
- `stack.isEmpty()` verifică dacă stack-ul este gol
- `stack.pop()` scoate și returnează ultima paranteză deschisă din stack
- `map.get(c)` obține paranteza deschisă corespunzătoare parantezei închise `c`
- `stack.pop() != map.get(c)` compară ultima paranteză deschisă cu paranteza deschisă corespunzătoare

De ce verificăm `stack.isEmpty()`? Pentru că dacă stack-ul este gol, înseamnă că nu avem o paranteză deschisă pentru paranteza închisă curentă, deci string-ul este invalid.

De ce comparăm `stack.pop()` cu `map.get(c)`? Pentru că vrem să verificăm dacă ultima paranteză deschisă se potrivește cu paranteza închisă curentă. De exemplu, dacă ultima paranteză deschisă este '(' și paranteza închisă este ')', ele se potrivesc.

Dacă `stack.isEmpty()` este `true` sau `stack.pop() != map.get(c)` este `true`, string-ul este invalid, deci returnăm `false`.

```java
    return stack.isEmpty();
```

Această linie verifică dacă stack-ul este gol la final. Să explicăm:

- Dacă stack-ul este gol, înseamnă că toate parantezele deschise au fost închise corect, deci string-ul este valid
- Dacă stack-ul nu este gol, înseamnă că există paranteze deschise care nu au fost închise, deci string-ul este invalid

De exemplu, dacă avem `"("`, adăugăm '(' în stack, dar nu găsim niciodată ')', deci stack-ul rămâne cu '(' și returnăm `false`.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `s = "()[]{}"`:

**Inițializare:**
- `stack = []` (stack gol)
- `map = {')': '(', '}': '{', ']': '['}`

**Iterația 1 (c = '('):**
- `'(' == '(' || ...` → `true`
- `stack.push('(')` → `stack = ['(']`

**Iterația 2 (c = ')'):**
- `'(' == '(' || ...` → `false`, deci `else`
- `stack.isEmpty()`? `false` (stack-ul nu este gol)
- `stack.pop()` → returnează '(', `stack = []`
- `map.get(')')` → returnează '('
- `'(' != '('` → `false`, deci nu returnăm `false`
- Continuăm

**Iterația 3 (c = '['):**
- `'[' == '(' || ...` → `true`
- `stack.push('[')` → `stack = ['[']`

**Iterația 4 (c = ']'):**
- `'[' == '(' || ...` → `false`, deci `else`
- `stack.isEmpty()`? `false`
- `stack.pop()` → returnează '[', `stack = []`
- `map.get(']')` → returnează '['
- `'[' != '['` → `false`, continuăm

**Iterația 5 (c = '{'):**
- `'{' == '(' || ...` → `true`
- `stack.push('{')` → `stack = ['{']`

**Iterația 6 (c = '}'):**
- `'{' == '(' || ...` → `false`, deci `else`
- `stack.isEmpty()`? `false`
- `stack.pop()` → returnează '{', `stack = []`
- `map.get('}')` → returnează '{'
- `'{' != '{'` → `false`, continuăm

**După buclă:**
- `stack = []` (stack gol)
- `stack.isEmpty()` → `true`
- Returnăm `true`

**Rezultat:** `true` - string-ul este valid.

## Alt Exemplu - Invalid

Să urmărim pentru `s = "([)]"`:

**Inițializare:**
- `stack = []`

**Iterația 1 (c = '('):**
- `stack.push('(')` → `stack = ['(']`

**Iterația 2 (c = '['):**
- `stack.push('[')` → `stack = ['(', '[']`

**Iterația 3 (c = ')'):**
- `else` (nu este paranteză deschisă)
- `stack.isEmpty()`? `false`
- `stack.pop()` → returnează '[', `stack = ['(']`
- `map.get(')')` → returnează '('
- `'[' != '('` → `true`!
- Returnăm `false`

**Rezultat:** `false` - string-ul este invalid (paranteza pătrată deschisă nu se potrivește cu paranteza rotundă închisă).

## De Ce Este Această Soluție Eficientă?

1. **Parcurgem string-ul o singură dată**: Verificăm fiecare caracter o singură dată.

2. **Stack-ul gestionează ordinea**: Stack-ul ne permite să verificăm automat dacă parantezele sunt închise în ordinea corectă (ultima deschisă trebuie să fie prima închisă).

3. **O(n) timp și spațiu**: Parcurgem string-ul o dată și folosim un stack care poate conține cel mult n/2 elemente (dacă toate parantezele sunt deschise).

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este lungimea string-ului. Parcurgem string-ul o singură dată.

- **Complexitatea spațiului**: O(n) - în cel mai rău caz, toate parantezele sunt deschise, deci stack-ul va conține n/2 elemente.

## Concluzie

Această soluție este elegantă și eficientă. Folosim un stack pentru a ține minte parantezele deschise și le verificăm când întâlnim paranteze închise, asigurându-ne că se potrivesc și sunt închise în ordinea corectă.
