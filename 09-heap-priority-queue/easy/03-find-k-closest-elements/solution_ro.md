# Soluție Detaliată - Find K Closest Elements

## Ce Ne Cere Problema?

Problema ne cere să găsim k elemente cele mai apropiate de o valoare dată (x) dintr-un array sortat. Elementele trebuie returnate în ordine sortată.

De exemplu, dacă avem `arr = [1, 2, 3, 4, 5]`, `k = 4` și `x = 3`, elementele cele mai apropiate sunt `[1, 2, 3, 4]` (sau `[2, 3, 4, 5]` - ambele sunt la distanță 1 de x).

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Folosim binary search pentru a găsi poziția de început a ferestrei de k elemente
2. Comparăm distanța de la x la elementul de la începutul ferestrei cu distanța de la x la elementul de la sfârșitul ferestrei
3. Mutăm fereastra către direcția unde distanța este mai mică
4. Returnăm k elemente începând de la poziția găsită

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public List<Integer> findClosestElements(int[] arr, int k, int x) {
```

Această linie definește funcția `findClosestElements`. Funcția primește:
- `arr` - array-ul sortat
- `k` - numărul de elemente de returnat
- `x` - valoarea de referință

Funcția returnează `List<Integer>` - lista cu k elemente cele mai apropiate de x.

```java
    int left = 0;
    int right = arr.length - k;
```

Aceste linii inițializează intervalul de căutare. Să explicăm:

- `left = 0` este poziția minimă de început a ferestrei
- `right = arr.length - k` este poziția maximă de început a ferestrei (pentru ca fereastra să nu depășească array-ul)

De exemplu, dacă `arr.length = 5` și `k = 3`, atunci `right = 2` (fereastra poate începe la pozițiile 0, 1 sau 2).

```java
    while (left < right) {
```

Această buclă continuă cât timp mai avem un interval valid de căutare.

```java
        int mid = left + (right - left) / 2;
```

Această linie calculează poziția de mijloc. Folosim formula `left + (right - left) / 2` pentru a evita overflow.

```java
        if (x - arr[mid] > arr[mid + k] - x) {
            left = mid + 1;
        } else {
            right = mid;
        }
```

Această condiție compară distanțele și ajustează fereastra. Să explicăm:

- `x - arr[mid]` este distanța de la x la elementul de la începutul ferestrei
- `arr[mid + k] - x` este distanța de la x la elementul de la sfârșitul ferestrei
- `x - arr[mid] > arr[mid + k] - x` verifică dacă distanța la sfârșit este mai mică
- Dacă da, mutăm fereastra la dreapta (`left = mid + 1`)
- Dacă nu, mutăm fereastra la stânga sau o păstrăm (`right = mid`)

De exemplu, dacă `arr = [1, 2, 3, 4, 5]`, `k = 3`, `x = 3` și `mid = 1`:
- Fereastra: `[2, 3, 4]` (de la poziția 1)
- `x - arr[1] = 3 - 2 = 1`
- `arr[4] - x = 5 - 3 = 2`
- `1 > 2`? `false`, deci `right = 1`

```java
    List<Integer> result = new ArrayList<>();
    for (int i = left; i < left + k; i++) {
        result.add(arr[i]);
    }
    return result;
```

Aceste linii construiesc rezultatul. Să explicăm:

- `List<Integer> result` creează lista rezultatelor
- `for (int i = left; i < left + k; i++)` parcurge k elemente începând de la poziția `left`
- `result.add(arr[i])` adaugă fiecare element în rezultat
- `return result` returnează lista cu k elemente cele mai apropiate

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `arr = [1, 2, 3, 4, 5]`, `k = 4` și `x = 3`:

**Inițializare:**
- `left = 0`, `right = 1` (5 - 4 = 1)

**Iterația 1:**
- `mid = 0 + (1 - 0) / 2 = 0`
- Fereastra: `[1, 2, 3, 4]` (de la poziția 0)
- `x - arr[0] = 3 - 1 = 2`
- `arr[4] - x = 5 - 3 = 2`
- `2 > 2`? `false`, deci `right = 0`
- Interval: `left = 0`, `right = 0`

**După buclă:**
- `left = 0`
- Returnăm `[1, 2, 3, 4]`

**Rezultat:** `[1, 2, 3, 4]` - k elemente cele mai apropiate de x = 3.

## De Ce Este Această Soluție Eficientă?

1. **O(log n + k) timp**: Binary search este O(log n), iar construirea rezultatului este O(k).

2. **O(1) spațiu**: Folosim doar câteva variabile (excludând lista rezultatului).

3. **Binary search optim**: Găsim rapid poziția optimă de început a ferestrei.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(log n + k) - unde n este numărul de elemente. Binary search este O(log n), iar construirea rezultatului este O(k).

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile (excludând lista rezultatului care este returnată).

## Concluzie

Această soluție este elegantă și eficientă. Folosim binary search pentru a găsi rapid poziția optimă de început a ferestrei de k elemente, comparând distanțele de la x la capetele ferestrei, obținând astfel k elemente cele mai apropiate într-o complexitate de O(log n + k).
