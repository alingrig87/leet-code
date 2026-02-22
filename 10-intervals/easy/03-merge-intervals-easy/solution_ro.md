# Soluție Detaliată - Merge Intervals

## Ce Ne Cere Problema?

Problema ne cere să combinăm (merge) toate intervalele suprapuse dintr-un array de intervale. După combinare, ar trebui să rămână doar intervale care nu se suprapun.

De exemplu, dacă avem `intervals = [[1, 3], [2, 6], [8, 10], [15, 18]]`:
- [1, 3] și [2, 6] se suprapun, deci le combinăm în [1, 6]
- [8, 10] și [15, 18] nu se suprapun, deci le păstrăm
- Rezultat: `[[1, 6], [8, 10], [15, 18]]`

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Sortăm intervalele după timpul de început
2. Parcurgem intervalele și verificăm dacă fiecare interval se suprapune cu ultimul interval combinat
3. Dacă se suprapun, le combinăm actualizând sfârșitul ultimului interval
4. Dacă nu se suprapun, adăugăm noul interval ca un interval nou

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int[][] merge(int[][] intervals) {
```

Această linie definește funcția `merge`. Funcția primește:
- `intervals` - array-ul de intervale

Funcția returnează `int[][]` - array-ul cu intervalele combinate.

```java
    if (intervals == null || intervals.length <= 1) {
        return intervals;
    }
```

Această verificare tratează cazurile speciale. Dacă nu există intervale sau există cel mult un interval, nu există ce combina, deci returnăm array-ul original.

```java
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
```

Această linie sortează intervalele după timpul de început. Sortăm pentru a putea verifica eficient suprapunerile comparând fiecare interval cu intervalul anterior.

```java
    List<int[]> merged = new ArrayList<>();
    merged.add(intervals[0]);
```

Aceste linii inițializează lista cu intervalele combinate. Adăugăm primul interval direct, deoarece este primul interval sortat.

```java
    for (int i = 1; i < intervals.length; i++) {
```

Această buclă parcurge restul intervalelor începând de la al doilea (indicele 1).

```java
        int[] last = merged.get(merged.size() - 1);
        int[] current = intervals[i];
```

Aceste linii obțin ultimul interval combinat și intervalul curent. `last` este ultimul interval din lista combinată, iar `current` este intervalul curent de procesat.

```java
        if (current[0] <= last[1]) {
            last[1] = Math.max(last[1], current[1]);
        }
```

Această condiție combină intervalele dacă se suprapun. Să explicăm:

- `current[0] <= last[1]` verifică dacă intervalul curent începe înainte sau la momentul când ultimul interval se termină (suprapunere)
- Dacă da, `last[1] = Math.max(last[1], current[1])` actualizează sfârșitul ultimului interval la maximul dintre sfârșitul ultimului interval și sfârșitul intervalului curent

De exemplu, dacă `last = [1, 3]` și `current = [2, 6]`:
- `2 <= 3`? `true`, există suprapunere
- `last[1] = max(3, 6) = 6`
- `last` devine `[1, 6]`

```java
        else {
            merged.add(current);
        }
```

Această parte adaugă intervalul curent dacă nu se suprapune. Dacă intervalul curent nu se suprapune cu ultimul interval combinat, îl adăugăm ca un interval nou.

```java
    return merged.toArray(new int[merged.size()][]);
```

Această linie convertește lista în array și o returnează.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `intervals = [[1, 3], [2, 6], [8, 10], [15, 18]]`:

**Pasul 1: Sortăm**
- Intervalele sunt deja sortate: `[[1, 3], [2, 6], [8, 10], [15, 18]]`

**Inițializare:**
- `merged = [[1, 3]]`

**Iterația 1 (i = 1, current = [2, 6]):**
- `last = [1, 3]`
- `2 <= 3`? `true`, există suprapunere
- `last[1] = max(3, 6) = 6`
- `merged = [[1, 6]]`

**Iterația 2 (i = 2, current = [8, 10]):**
- `last = [1, 6]`
- `8 <= 6`? `false`, nu există suprapunere
- `merged.add([8, 10])`
- `merged = [[1, 6], [8, 10]]`

**Iterația 3 (i = 3, current = [15, 18]):**
- `last = [8, 10]`
- `15 <= 10`? `false`, nu există suprapunere
- `merged.add([15, 18])`
- `merged = [[1, 6], [8, 10], [15, 18]]`

**Rezultat:** `[[1, 6], [8, 10], [15, 18]]` - intervalele suprapuse au fost combinate.

## De Ce Este Această Soluție Eficientă?

1. **O(n log n) timp**: Sortarea este O(n log n), iar combinarea este O(n).

2. **O(n) spațiu**: Lista combinată poate conține cel mult n intervale.

3. **Simplă și directă**: Sortăm o dată, apoi combinăm eficient.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n log n) - unde n este numărul de intervale. Sortarea este O(n log n), iar combinarea este O(n).

- **Complexitatea spațiului**: O(n) - lista combinată poate conține cel mult n intervale.

## Concluzie

Această soluție este simplă și eficientă. Sortăm intervalele după timpul de început și combinăm intervalele suprapuse actualizând sfârșitul ultimului interval, obținând astfel toate intervalele combinate într-o singură parcurgere.
