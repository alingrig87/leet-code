# Soluție Detaliată - Insert Interval

## Ce Ne Cere Problema?

Problema ne cere să inserăm un interval nou într-o listă de intervale sortate și non-suprapuse, combinându-l cu intervalele suprapuse dacă este necesar.

De exemplu, dacă avem `intervals = [[1, 3], [6, 9]]` și `newInterval = [2, 5]`:
- [2, 5] se suprapune cu [1, 3], deci le combinăm în [1, 5]
- [1, 5] nu se suprapune cu [6, 9], deci le păstrăm separate
- Rezultat: `[[1, 5], [6, 9]]`

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Faza 1: Adăugăm toate intervalele care se termină înainte ca noul interval să înceapă
2. Faza 2: Combinăm toate intervalele care se suprapun cu noul interval
3. Faza 3: Adăugăm noul interval (combinat) și restul intervalelor care nu se suprapun

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int[][] insert(int[][] intervals, int[] newInterval) {
```

Această linie definește funcția `insert`. Funcția primește:
- `intervals` - array-ul de intervale sortate și non-suprapuse
- `newInterval` - intervalul nou de inserat

Funcția returnează `int[][]` - array-ul cu intervalul nou inserat și combinat.

```java
    List<int[]> result = new ArrayList<>();
    int i = 0;
    int n = intervals.length;
```

Aceste linii inițializează variabilele. `result` va stoca rezultatul, `i` este pointer-ul pentru parcurgerea intervalelor, iar `n` este numărul de intervale.

```java
    while (i < n && intervals[i][1] < newInterval[0]) {
        result.add(intervals[i]);
        i++;
    }
```

Această buclă este Faza 1: adăugăm intervalele care se termină înainte ca noul interval să înceapă. Să explicăm:

- `intervals[i][1] < newInterval[0]` verifică dacă intervalul curent se termină înainte ca noul interval să înceapă (nu se suprapun)
- Dacă da, `result.add(intervals[i])` adaugă intervalul în rezultat (nu trebuie combinat)
- `i++` trece la următorul interval

De exemplu, dacă `intervals[i] = [1, 3]` și `newInterval = [5, 7]`:
- `3 < 5`? `true`, adăugăm [1, 3] în rezultat

```java
    while (i < n && intervals[i][0] <= newInterval[1]) {
        newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
        newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
        i++;
    }
```

Această buclă este Faza 2: combinăm intervalele care se suprapun cu noul interval. Să explicăm:

- `intervals[i][0] <= newInterval[1]` verifică dacă intervalul curent începe înainte sau la momentul când noul interval se termină (suprapunere)
- Dacă da, `newInterval[0] = Math.min(...)` actualizează începutul noului interval la minimul dintre începutul noului interval și începutul intervalului curent
- `newInterval[1] = Math.max(...)` actualizează sfârșitul noului interval la maximul dintre sfârșitul noului interval și sfârșitul intervalului curent
- `i++` trece la următorul interval

De exemplu, dacă `newInterval = [2, 5]` și `intervals[i] = [1, 3]`:
- `1 <= 5`? `true`, există suprapunere
- `newInterval[0] = min(2, 1) = 1`
- `newInterval[1] = max(5, 3) = 5`
- `newInterval` devine `[1, 5]`

```java
    result.add(newInterval);
```

Această linie adaugă noul interval (combinat) în rezultat după ce am combinat toate intervalele suprapuse.

```java
    while (i < n) {
        result.add(intervals[i]);
        i++;
    }
```

Această buclă este Faza 3: adăugăm restul intervalelor care nu se suprapun cu noul interval (care se termină după ce noul interval se termină).

```java
    return result.toArray(new int[result.size()][]);
```

Această linie convertește lista în array și o returnează.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `intervals = [[1, 3], [6, 9]]` și `newInterval = [2, 5]`:

**Inițializare:**
- `result = []`, `i = 0`, `n = 2`

**Faza 1:**
- `i = 0`, `intervals[0] = [1, 3]`
- `3 < 2`? `false`, nu adăugăm
- `i` rămâne 0

**Faza 2:**
- `i = 0`, `intervals[0] = [1, 3]`
- `1 <= 5`? `true`, există suprapunere
- `newInterval[0] = min(2, 1) = 1`
- `newInterval[1] = max(5, 3) = 5`
- `newInterval = [1, 5]`, `i = 1`
- `i = 1`, `intervals[1] = [6, 9]`
- `6 <= 5`? `false`, nu mai există suprapunere
- `result.add([1, 5])`

**Faza 3:**
- `i = 1`, `intervals[1] = [6, 9]`
- `result.add([6, 9])`
- `i = 2`, bucla se termină

**Rezultat:** `[[1, 5], [6, 9]]` - intervalul nou a fost inserat și combinat.

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Parcurgem array-ul o singură dată, făcând O(1) operații pentru fiecare interval.

2. **O(n) spațiu**: Lista rezultatelor poate conține cel mult n + 1 intervale.

3. **Trei faze clare**: Separăm logica în trei faze pentru claritate și eficiență.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de intervale. Parcurgem array-ul o singură dată.

- **Complexitatea spațiului**: O(n) - lista rezultatelor poate conține cel mult n + 1 intervale.

## Concluzie

Această soluție este elegantă și eficientă. Separăm logica în trei faze: adăugăm intervalele care nu se suprapun, combinăm intervalele care se suprapun cu noul interval, și adăugăm restul intervalelor, obținând astfel intervalul nou inserat și combinat într-o singură parcurgere.
