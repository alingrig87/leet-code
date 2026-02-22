# Soluție Detaliată - Can Attend Meetings

## Ce Ne Cere Problema?

Problema ne cere să verificăm dacă o persoană poate participa la toate întâlnirile (meetings) dintr-o listă. Aceasta este aceeași problemă ca "Meeting Rooms" - verificăm dacă nu există suprapuneri între intervale.

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

Această linie sortează intervalele după timpul de început. Sortăm pentru a putea verifica eficient suprapunerile comparând fiecare interval cu intervalul anterior.

```java
    for (int i = 1; i < intervals.length; i++) {
        if (intervals[i][0] < intervals[i - 1][1]) {
            return false;
        }
    }
```

Această buclă verifică suprapunerile. Să explicăm:

- `for (int i = 1; i < intervals.length; i++)` parcurge intervalele începând de la al doilea
- `intervals[i][0] < intervals[i - 1][1]` verifică dacă intervalul curent începe înainte ca intervalul anterior să se termine
- Dacă da, există suprapunere, deci returnăm `false`

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

## De Ce Este Această Soluție Eficientă?

1. **O(n log n) timp**: Sortarea este O(n log n), iar verificarea este O(n).

2. **O(1) spațiu**: Folosim doar câteva variabile.

3. **Early termination**: Dacă găsim o suprapunere, returnăm imediat `false`.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n log n) - unde n este numărul de intervale.

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile.

## Concluzie

Această soluție este simplă și eficientă. Sortăm intervalele după timpul de început și verificăm dacă fiecare interval se suprapune cu intervalul anterior, returnând `false` imediat când găsim o suprapunere.
