# Soluție Detaliată - Meeting Rooms

## Ce Ne Cere Problema?

Problema ne cere să verificăm dacă o persoană poate participa la toate întâlnirile (meetings) dintr-o listă. O persoană poate participa la toate întâlnirile doar dacă nu se suprapun (nu există două întâlniri care să se întâmple în același timp).

De exemplu, dacă avem `intervals = [[0, 30], [5, 10], [15, 20]]`:
- Întâlnirea [5, 10] se suprapune cu [0, 30] (5 < 30), deci nu poate participa la toate
- Rezultat: `false`

## Ce Este un Overlap (Suprapunere)?

Două intervale se suprapun dacă se întâmplă în același timp. De exemplu, [0, 30] și [5, 10] se suprapun pentru că 5 < 30 (începutul celui de-al doilea interval este înainte de sfârșitul primului).

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Sortăm intervalele după timpul de început
2. Verificăm dacă fiecare interval se suprapune cu intervalul anterior
3. Dacă găsim o suprapunere, returnăm `false`
4. Dacă nu găsim suprapuneri, returnăm `true`

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public boolean canAttendMeetings(int[][] intervals) {
```

Această linie definește funcția `canAttendMeetings`. Funcția primește:
- `intervals` - array-ul de intervale, unde fiecare interval este `[start, end]`

Funcția returnează `boolean` - `true` dacă poate participa la toate întâlnirile, `false` dacă nu poate.

```java
    if (intervals == null || intervals.length <= 1) {
        return true;
    }
```

Această verificare tratează cazurile speciale. Dacă nu există intervale sau există cel mult un interval, nu există suprapuneri, deci returnăm `true`.

```java
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
```

Această linie sortează intervalele după timpul de început. Să explicăm:

- `Arrays.sort(intervals, ...)` sortează array-ul de intervale
- `(a, b) -> a[0] - b[0]` este un comparator care compară după primul element (timpul de început)
- `a[0] - b[0]` returnează diferența (pozitivă dacă `a[0] > b[0]`, negativă dacă `a[0] < b[0]`)

De exemplu, dacă `intervals = [[5, 10], [0, 30], [15, 20]]`:
- După sortare: `[[0, 30], [5, 10], [15, 20]]`

```java
    for (int i = 1; i < intervals.length; i++) {
```

Această buclă parcurge intervalele începând de la al doilea (indicele 1). Comparăm fiecare interval cu intervalul anterior.

```java
        if (intervals[i][0] < intervals[i - 1][1]) {
            return false;
        }
```

Această condiție verifică dacă există o suprapunere. Să explicăm:

- `intervals[i][0]` este timpul de început al intervalului curent
- `intervals[i - 1][1]` este timpul de sfârșit al intervalului anterior
- `intervals[i][0] < intervals[i - 1][1]` verifică dacă intervalul curent începe înainte ca intervalul anterior să se termine
- Dacă da, există o suprapunere, deci returnăm `false`

De exemplu, dacă `intervals[i - 1] = [0, 30]` și `intervals[i] = [5, 10]`:
- `5 < 30`? `true`!
- Există suprapunere, returnăm `false`

```java
    return true;
```

Această linie se execută doar dacă nu am găsit nicio suprapunere. În acest caz, persoana poate participa la toate întâlnirile, deci returnăm `true`.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `intervals = [[0, 30], [5, 10], [15, 20]]`:

**Pasul 1: Sortăm**
- Intervalele sunt deja sortate: `[[0, 30], [5, 10], [15, 20]]`

**Iterația 1 (i = 1):**
- `intervals[1] = [5, 10]`, `intervals[0] = [0, 30]`
- `5 < 30`? `true`!
- Returnăm `false`

**Rezultat:** `false` - există suprapunere, deci nu poate participa la toate întâlnirile.

## Alt Exemplu - Nu Există Suprapunere

Să urmărim pentru `intervals = [[7, 10], [2, 4]]`:

**Pasul 1: Sortăm**
- `[[2, 4], [7, 10]]`

**Iterația 1 (i = 1):**
- `intervals[1] = [7, 10]`, `intervals[0] = [2, 4]`
- `7 < 4`? `false`, continuăm

**După buclă:**
- Nu am găsit suprapuneri
- Returnăm `true`

**Rezultat:** `true` - nu există suprapunere, deci poate participa la toate întâlnirile.

## De Ce Este Această Soluție Eficientă?

1. **O(n log n) timp**: Sortarea este O(n log n), iar verificarea este O(n).

2. **O(1) spațiu**: Folosim doar câteva variabile, nu creăm structuri de date suplimentare (excludând sortarea care modifică array-ul original).

3. **Early termination**: Dacă găsim o suprapunere, returnăm imediat `false`, fără să continuăm verificarea.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n log n) - unde n este numărul de intervale. Sortarea este O(n log n), iar verificarea este O(n).

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile (excludând sortarea care modifică array-ul original).

## Concluzie

Această soluție este simplă și eficientă. Sortăm intervalele după timpul de început și verificăm dacă fiecare interval se suprapune cu intervalul anterior, returnând `false` imediat când găsim o suprapunere, verificând astfel dacă o persoană poate participa la toate întâlnirile.
