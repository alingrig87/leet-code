# Soluție Detaliată - Sort Array by Increasing Frequency

## Ce Ne Cere Problema?

Problema ne cere să sortăm un array după frecvența elementelor (crescătoare). Dacă două elemente au aceeași frecvență, le sortăm descrescător după valoare.

De exemplu, dacă avem `nums = [1, 1, 2, 2, 2, 3]`:
- Frecvențe: 1 apare de 2 ori, 2 apare de 3 ori, 3 apare de 1 dată
- Sortare după frecvență: 3 (frecvență 1), 1 (frecvență 2), 2 (frecvență 3)
- Dacă frecvențele sunt egale, sortăm descrescător: de exemplu, dacă avem `[2, 3, 1, 3, 2]`, atunci 1 (frecvență 1), 2 și 2 (frecvență 2), 3 și 3 (frecvență 2) - dar 2 > 3, deci 2 vine înaintea lui 3

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Calculăm frecvența fiecărui element folosind un HashMap
2. Sortăm array-ul folosind un comparator personalizat care:
   - Compară mai întâi după frecvență (crescător)
   - Dacă frecvențele sunt egale, compară după valoare (descrescător)

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int[] frequencySort(int[] nums) {
```

Această linie definește funcția `frequencySort`. Funcția primește:
- `nums` - array-ul de sortat

Funcția returnează `int[]` - array-ul sortat după frecvență.

```java
    Map<Integer, Integer> freq = new HashMap<>();
```

Această linie creează un HashMap pentru a stoca frecvența fiecărui element. Cheia este elementul, iar valoarea este frecvența sa.

```java
    for (int num : nums) {
        freq.put(num, freq.getOrDefault(num, 0) + 1);
    }
```

Această buclă calculează frecvența fiecărui element. Să explicăm:

- `freq.getOrDefault(num, 0)` obține frecvența existentă sau 0 dacă elementul nu există
- `+ 1` incrementează frecvența
- `freq.put(num, ...)` actualizează frecvența elementului

De exemplu, dacă `nums = [1, 1, 2]`:
- `num = 1`: `freq.put(1, 0 + 1) = 1`
- `num = 1`: `freq.put(1, 1 + 1) = 2`
- `num = 2`: `freq.put(2, 0 + 1) = 1`
- `freq = {1: 2, 2: 1}`

```java
    Integer[] numsInteger = Arrays.stream(nums).boxed().toArray(Integer[]::new);
```

Această linie convertește array-ul de `int` într-un array de `Integer`. Este necesar pentru că `Arrays.sort()` cu comparator personalizat necesită obiecte, nu primitive.

```java
    Arrays.sort(numsInteger, (a, b) -> {
```

Această linie sortează array-ul folosind un comparator personalizat. `(a, b) -> { ... }` este o expresie lambda care definește cum să comparăm două elemente.

```java
        int freqA = freq.get(a);
        int freqB = freq.get(b);
```

Aceste linii obțin frecvențele elementelor. `freqA` este frecvența elementului `a`, iar `freqB` este frecvența elementului `b`.

```java
        if (freqA != freqB) {
            return freqA - freqB;
        }
```

Această condiție compară după frecvență. Să explicăm:

- `freqA != freqB` verifică dacă frecvențele diferă
- Dacă da, `freqA - freqB` returnează diferența (pozitivă dacă `freqA > freqB`, negativă dacă `freqA < freqB`)
- Rezultatul pozitiv înseamnă că `a` vine după `b` (frecvență mai mare)
- Rezultatul negativ înseamnă că `a` vine înaintea lui `b` (frecvență mai mică)

De exemplu, dacă `freqA = 2` și `freqB = 3`:
- `2 - 3 = -1` (negativ), deci `a` vine înaintea lui `b` (frecvență mai mică vine primul)

```java
        return b - a;
```

Această linie compară după valoare când frecvențele sunt egale. Să explicăm:

- `b - a` returnează diferența valorilor (descrescător)
- Rezultatul pozitiv înseamnă că `a` vine după `b` (valoare mai mică)
- Rezultatul negativ înseamnă că `a` vine înaintea lui `b` (valoare mai mare)

De exemplu, dacă `a = 2` și `b = 3` (ambele cu frecvență 2):
- `3 - 2 = 1` (pozitiv), deci `a` (2) vine după `b` (3)
- Dar vrem descrescător, deci 2 vine înaintea lui 3 - corect!

```java
    return Arrays.stream(numsInteger).mapToInt(i -> i).toArray();
```

Această linie convertește array-ul de `Integer` înapoi într-un array de `int` și îl returnează.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `nums = [1, 1, 2, 2, 2, 3]`:

**Pasul 1: Calculăm frecvențele**
- `freq = {1: 2, 2: 3, 3: 1}`

**Pasul 2: Sortăm**
- Comparăm 1 și 1: `freqA = 2`, `freqB = 2`, `2 != 2`? `false`, comparăm după valoare: `1 - 1 = 0` (egal)
- Comparăm 1 și 2: `freqA = 2`, `freqB = 3`, `2 != 3`? `true`, `2 - 3 = -1` (negativ), deci 1 vine înaintea lui 2
- Comparăm 1 și 3: `freqA = 2`, `freqB = 1`, `2 != 1`? `true`, `2 - 1 = 1` (pozitiv), deci 1 vine după 3
- Comparăm 2 și 3: `freqA = 3`, `freqB = 1`, `3 != 1`? `true`, `3 - 1 = 2` (pozitiv), deci 2 vine după 3
- Rezultat: `[3, 1, 1, 2, 2, 2]`

**Rezultat:** `[3, 1, 1, 2, 2, 2]` - sortat după frecvență crescătoare (3 are frecvență 1, 1 are frecvență 2, 2 are frecvență 3).

## De Ce Este Această Soluție Eficientă?

1. **O(n log n) timp**: Calculăm frecvențele în O(n), iar sortarea este O(n log n).

2. **O(n) spațiu**: HashMap-ul și array-ul convertit ocupă O(n) spațiu.

3. **Comparator personalizat**: Permite sortarea după mai multe criterii (frecvență, apoi valoare).

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n log n) - unde n este numărul de elemente. Calculăm frecvențele în O(n), iar sortarea este O(n log n).

- **Complexitatea spațiului**: O(n) - HashMap-ul și array-ul convertit ocupă O(n) spațiu.

## Concluzie

Această soluție este simplă și eficientă. Calculăm frecvențele folosind un HashMap și sortăm array-ul folosind un comparator personalizat care compară mai întâi după frecvență (crescător) și apoi după valoare (descrescător) pentru elementele cu frecvență egală.
