# Soluție Detaliată - Implement strStr()

## Ce Ne Cere Problema?

Problema ne cere să găsim prima apariție a unui string (`needle`) într-un alt string (`haystack`). Dacă `needle` nu apare în `haystack`, returnăm -1.

De exemplu, dacă avem `haystack = "hello"` și `needle = "ll"`, returnăm 2 (poziția unde începe "ll" în "hello").

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Verificăm cazul când `needle` este gol (returnăm 0)
2. Parcurgem `haystack` cu o fereastră glisantă de lungime `needle.length()`
3. Pentru fiecare poziție, verificăm dacă subșirul corespunzător este egal cu `needle`
4. Dacă găsim o potrivire, returnăm poziția
5. Dacă nu găsim, returnăm -1

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int strStr(String haystack, String needle) {
```

Această linie definește funcția `strStr`. Funcția primește:
- `haystack` - string-ul în care căutăm
- `needle` - string-ul pe care îl căutăm

Funcția returnează `int` - poziția primei apariții a lui `needle` în `haystack`, sau -1 dacă nu există.

```java
    if (needle.isEmpty()) {
        return 0;
    }
```

Această verificare tratează cazul când `needle` este gol. Dacă `needle` este gol, se consideră că apare la poziția 0 (la începutul oricărui string).

```java
    int n = haystack.length();
    int m = needle.length();
```

Aceste linii stochează lungimile string-urilor pentru a evita apeluri repetate la `length()`.

```java
    for (int i = 0; i <= n - m; i++) {
```

Această buclă parcurge toate pozițiile posibile de început ale lui `needle` în `haystack`. Să explicăm:

- `i` este poziția de început a ferestrei glisante
- `i <= n - m` asigură că fereastra nu depășește lungimea lui `haystack`
- De exemplu, dacă `n = 5` și `m = 2`, atunci `i` poate fi 0, 1, 2, 3 (fereastra de lungime 2 poate începe la aceste poziții)

```java
        if (haystack.substring(i, i + m).equals(needle)) {
            return i;
        }
```

Această condiție verifică dacă subșirul de la poziția `i` de lungime `m` este egal cu `needle`. Să explicăm:

- `haystack.substring(i, i + m)` extrage subșirul de la poziția `i` până la `i + m` (exclusiv)
- `.equals(needle)` compară subșirul cu `needle`
- Dacă sunt egale, am găsit prima apariție, deci returnăm poziția `i`

De exemplu, dacă `haystack = "hello"`, `needle = "ll"` și `i = 2`:
- `haystack.substring(2, 4)` → `"ll"`
- `"ll".equals("ll")`? `true`!
- Returnăm `2`

```java
    return -1;
```

Această linie se execută doar dacă nu am găsit nicio potrivire. În acest caz, returnăm -1.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `haystack = "hello"` și `needle = "ll"`:

**Inițializare:**
- `n = 5`, `m = 2`

**Iterația 1 (i = 0):**
- `haystack.substring(0, 2)` → `"he"`
- `"he".equals("ll")`? `false`, continuăm

**Iterația 2 (i = 1):**
- `haystack.substring(1, 3)` → `"el"`
- `"el".equals("ll")`? `false`, continuăm

**Iterația 3 (i = 2):**
- `haystack.substring(2, 4)` → `"ll"`
- `"ll".equals("ll")`? `true`!
- Returnăm `2`

**Rezultat:** `2` - prima apariție a lui "ll" este la poziția 2.

## Alt Exemplu - Nu Găsim

Să urmărim pentru `haystack = "hello"` și `needle = "aa"`:

**Iterația 1 (i = 0):** `"he" != "aa"`, continuăm
**Iterația 2 (i = 1):** `"el" != "aa"`, continuăm
**Iterația 3 (i = 2):** `"ll" != "aa"`, continuăm
**Iterația 4 (i = 3):** `"lo" != "aa"`, continuăm

**După toate iterațiile:**
- Nu am găsit nicio potrivire
- Returnăm `-1`

**Rezultat:** `-1` - "aa" nu apare în "hello".

## De Ce Este Această Soluție Eficientă?

1. **Simplă și directă**: Folosim o abordare naivă care este ușor de înțeles.

2. **O(n * m) timp**: Unde n este lungimea lui `haystack` și m este lungimea lui `needle`. În cel mai rău caz, verificăm fiecare poziție și comparăm subșirul complet.

3. **O(1) spațiu**: Folosim doar câteva variabile, nu creăm structuri de date suplimentare.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n * m) - unde n este lungimea lui `haystack` și m este lungimea lui `needle`. În cel mai rău caz, verificăm fiecare poziție și comparăm subșirul complet.

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile, nu creăm structuri de date suplimentare (excludând subșirul temporar creat de `substring()`).

## Concluzie

Această soluție este simplă și directă. Folosim o fereastră glisantă pentru a verifica fiecare poziție posibilă de început a lui `needle` în `haystack`, returnând prima poziție unde găsim o potrivire sau -1 dacă nu găsim.
