# Soluție Detaliată - Non-overlapping Intervals

## Ce Ne Cere Problema?

Problema ne cere să găsim numărul minim de intervale care trebuie eliminate pentru ca restul intervalelor să nu se mai suprapună. De exemplu, dacă avem `intervals = [[1, 2], [2, 3], [3, 4], [1, 3]]`:
- Putem elimina [1, 3] pentru ca restul să nu se suprapună
- Rezultat: 1 (un interval eliminat)

## Ce Este Algoritmul Greedy "Earliest Finish Time"?

Algoritmul greedy "Earliest Finish Time" (cel mai devreme timp de terminare) este o strategie optimă pentru probleme de programare a intervalelor. Strategia este să păstrăm întotdeauna intervalul care se termină cel mai devreme, deoarece acesta lasă cel mai mult timp disponibil pentru intervalele următoare.

## De Ce Sortăm După End Time?

Sortăm după end time pentru a putea aplica strategia greedy. Dacă sortăm după end time, putem procesa intervalele în ordinea în care se termină și să păstrăm întotdeauna intervalul care se termină cel mai devreme.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Sortăm intervalele după timpul de sfârșit (end time)
2. Păstrăm primul interval (se termină cel mai devreme)
3. Pentru fiecare interval următor:
   - Dacă nu se suprapune cu ultimul interval păstrat, îl păstrăm
   - Dacă se suprapune, îl eliminăm (preferăm intervalul care se termină mai devreme, care este deja păstrat)

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int eraseOverlapIntervals(int[][] intervals) {
```

Această linie definește funcția `eraseOverlapIntervals`. Funcția primește:
- `intervals` - array-ul de intervale

Funcția returnează `int` - numărul minim de intervale de eliminat.

```java
    if (intervals == null || intervals.length <= 1) {
        return 0;
    }
```

Această verificare tratează cazurile speciale. Dacă nu există intervale sau există cel mult un interval, nu există suprapuneri, deci nu trebuie să eliminăm nimic.

```java
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
```

Această linie sortează intervalele după timpul de sfârșit. Să explicăm:

- `Integer.compare(a[1], b[1])` compară end time-urile intervalelor
- Sortăm crescător după end time (intervalele care se termină mai devreme vin primul)

De exemplu, dacă `intervals = [[1, 3], [2, 4], [1, 2]]`:
- După sortare: `[[1, 2], [1, 3], [2, 4]]` (sortate după end time)

```java
    int lastEnd = intervals[0][1];
    int removeCount = 0;
```

Aceste linii inițializează variabilele. Să explicăm:

- `lastEnd` stochează end time-ul ultimului interval păstrat (inițializat cu primul interval, pe care îl păstrăm întotdeauna)
- `removeCount` numără câte intervale eliminăm

```java
    for (int i = 1; i < intervals.length; i++) {
```

Această buclă parcurge restul intervalelor începând de la al doilea (indicele 1).

```java
        int currentStart = intervals[i][0];
        int currentEnd = intervals[i][1];
```

Aceste linii extrag start time-ul și end time-ul intervalului curent.

```java
        if (currentStart < lastEnd) {
            removeCount++;
        }
```

Această condiție verifică dacă intervalul curent se suprapune cu ultimul interval păstrat. Să explicăm:

- `currentStart < lastEnd` verifică dacă intervalul curent începe înainte ca ultimul interval păstrat să se termine (suprapunere)
- Dacă da, `removeCount++` incrementează contorul (eliminăm intervalul curent)

De exemplu, dacă `lastEnd = 2` și `currentStart = 1`:
- `1 < 2`? `true`, există suprapunere
- Eliminăm intervalul curent (preferăm intervalul care se termină mai devreme, care este deja păstrat)

```java
        else {
            lastEnd = currentEnd;
        }
```

Această parte actualizează `lastEnd` dacă intervalul curent nu se suprapune. Să explicăm:

- `else` înseamnă că intervalul curent nu se suprapune cu ultimul interval păstrat
- `lastEnd = currentEnd` actualizează end time-ul la end time-ul intervalului curent (l-am păstrat)

```java
    return removeCount;
```

Această linie returnează numărul de intervale eliminate.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `intervals = [[1, 2], [2, 3], [3, 4], [1, 3]]`:

**Pasul 1: Sortăm după end time**
- `[[1, 2], [2, 3], [1, 3], [3, 4]]` (sortate după end time: 2, 3, 3, 4)

**Inițializare:**
- `lastEnd = 2` (end time-ul primului interval [1, 2])
- `removeCount = 0`

**Iterația 1 (i = 1, current = [2, 3]):**
- `currentStart = 2`, `currentEnd = 3`
- `2 < 2`? `false`, nu există suprapunere
- `lastEnd = 3`
- `removeCount = 0`

**Iterația 2 (i = 2, current = [1, 3]):**
- `currentStart = 1`, `currentEnd = 3`
- `1 < 3`? `true`, există suprapunere
- `removeCount++` → `removeCount = 1`
- `lastEnd` rămâne 3

**Iterația 3 (i = 3, current = [3, 4]):**
- `currentStart = 3`, `currentEnd = 4`
- `3 < 3`? `false`, nu există suprapunere
- `lastEnd = 4`
- `removeCount = 1`

**Rezultat:** `1` - trebuie să eliminăm un interval ([1, 3]).

## De Ce Este Această Soluție Eficientă?

1. **O(n log n) timp**: Sortarea este O(n log n), iar parcurgerea este O(n).

2. **O(1) spațiu**: Folosim doar câteva variabile, nu creăm structuri de date suplimentare.

3. **Greedy optim**: Strategia "earliest finish time" este optimă pentru această problemă.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n log n) - unde n este numărul de intervale. Sortarea este O(n log n), iar parcurgerea este O(n).

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile (excludând sortarea care modifică array-ul original).

## Concluzie

Această soluție este elegantă și eficientă. Folosim algoritmul greedy "earliest finish time" pentru a găsi numărul minim de intervale de eliminat, sortând intervalele după end time și păstrând întotdeauna intervalul care se termină cel mai devreme, obținând astfel soluția optimă.
