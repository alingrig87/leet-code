# Soluție Detaliată - Last Stone Weight

## Ce Ne Cere Problema?

Problema ne cere să simulăm un joc unde avem o colecție de pietre cu greutăți diferite. La fiecare rundă, luăm cele două pietre cele mai grele și le "spargem" împreună. Dacă greutățile sunt egale, ambele pietre sunt distruse. Dacă greutățile diferă, piatra mai ușoară este distrusă, iar piatra mai grea rămâne cu greutatea diferenței. Trebuie să găsim greutatea ultimei pietre rămase (sau 0 dacă nu mai rămâne nicio piatră).

De exemplu, dacă avem `stones = [2, 7, 4, 1, 8, 1]`:
- Runda 1: 8 și 7 → diferența = 1, rămâne piatra de 1
- Runda 2: 4 și 2 → diferența = 2, rămâne piatra de 2
- Runda 3: 2 și 1 → diferența = 1, rămâne piatra de 1
- Runda 4: 1 și 1 → ambele distruse
- Rezultat: 0

## De Ce Folosim un Max Heap?

Un max heap ne permite să obținem rapid cele două pietre cele mai grele (sunt în vârful heap-ului). În Java, putem simula un max heap folosind un `PriorityQueue` cu un comparator invers.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Adăugăm toate pietrele într-un max heap
2. Cât timp heap-ul are cel puțin 2 elemente:
   - Extragem cele două pietre cele mai grele
   - Calculăm diferența
   - Dacă diferența > 0, adăugăm diferența înapoi în heap
3. Returnăm greutatea ultimei pietre (sau 0 dacă heap-ul este gol)

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int lastStoneWeight(int[] stones) {
```

Această linie definește funcția `lastStoneWeight`. Funcția primește:
- `stones` - array-ul cu greutățile pietrelor

Funcția returnează `int` - greutatea ultimei pietre rămase (sau 0).

```java
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
```

Această linie creează un max heap. Să explicăm:

- `PriorityQueue<Integer>` este un heap
- `(a, b) -> b - a` este un comparator care inversează ordinea (b - a în loc de a - b)
- Astfel, elementele mai mari sunt în vârf (max heap)

De exemplu, dacă adăugăm `[2, 7, 4]`, heap-ul va fi `[7, 2, 4]` (7 este în vârf).

```java
    for (int stone : stones) {
        maxHeap.offer(stone);
    }
```

Această buclă adaugă toate pietrele în heap. `maxHeap.offer(stone)` adaugă piatra în heap.

```java
    while (maxHeap.size() > 1) {
```

Această buclă continuă cât timp mai avem cel puțin 2 pietre. Când rămâne doar o piatră (sau niciuna), bucla se termină.

```java
        int first = maxHeap.poll();
        int second = maxHeap.poll();
```

Aceste linii extrag cele două pietre cele mai grele. Să explicăm:

- `maxHeap.poll()` extrage și elimină elementul din vârf (cea mai grea piatră)
- `first` este prima piatră (cea mai grea)
- `second` este a doua piatră (a doua cea mai grea)

De exemplu, dacă heap-ul este `[8, 7, 4, 2, 1, 1]`:
- `first = 8` (cea mai grea)
- `second = 7` (a doua cea mai grea)
- Heap devine `[4, 2, 1, 1]`

```java
        int difference = first - second;
```

Această linie calculează diferența dintre cele două pietre. Dacă `first = 8` și `second = 7`, atunci `difference = 1`.

```java
        if (difference > 0) {
            maxHeap.offer(difference);
        }
```

Această condiție adaugă diferența înapoi în heap dacă este pozitivă. Să explicăm:

- `difference > 0` verifică dacă diferența este pozitivă (pietrele nu au fost distruse complet)
- Dacă da, `maxHeap.offer(difference)` adaugă diferența înapoi în heap

De exemplu, dacă `difference = 1`, adăugăm 1 înapoi în heap.

```java
    return maxHeap.isEmpty() ? 0 : maxHeap.poll();
```

Această linie returnează greutatea ultimei pietre. Să explicăm:

- `maxHeap.isEmpty()` verifică dacă heap-ul este gol
- Dacă da, returnăm 0 (nu mai rămâne nicio piatră)
- Dacă nu, `maxHeap.poll()` extrage și returnează ultima piatră rămasă

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `stones = [2, 7, 4, 1, 8, 1]`:

**Inițializare:**
- `maxHeap = [8, 7, 4, 2, 1, 1]` (max heap)

**Runda 1:**
- `first = 8`, `second = 7`
- `difference = 8 - 7 = 1`
- `difference > 0`? `true`, adăugăm 1
- `maxHeap = [4, 2, 1, 1, 1]`

**Runda 2:**
- `first = 4`, `second = 2`
- `difference = 4 - 2 = 2`
- `difference > 0`? `true`, adăugăm 2
- `maxHeap = [2, 1, 1, 1]`

**Runda 3:**
- `first = 2`, `second = 1`
- `difference = 2 - 1 = 1`
- `difference > 0`? `true`, adăugăm 1
- `maxHeap = [1, 1, 1]`

**Runda 4:**
- `first = 1`, `second = 1`
- `difference = 1 - 1 = 0`
- `difference > 0`? `false`, nu adăugăm nimic
- `maxHeap = [1]`

**După buclă:**
- `maxHeap.size() = 1`, bucla se termină
- `maxHeap.isEmpty()`? `false`
- Returnăm `maxHeap.poll() = 1`

**Rezultat:** `1` - ultima piatră rămasă are greutatea 1.

## De Ce Este Această Soluție Eficientă?

1. **O(n log n) timp**: Unde n este numărul de pietre. Construim heap-ul în O(n log n) și fiecare operație de spargere este O(log n).

2. **O(n) spațiu**: Heap-ul poate conține cel mult n elemente.

3. **Max heap eficient**: Obținem rapid cele două pietre cele mai grele.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n log n) - unde n este numărul de pietre. Construim heap-ul și efectuăm operații de spargere.

- **Complexitatea spațiului**: O(n) - heap-ul poate conține cel mult n elemente.

## Concluzie

Această soluție este elegantă și eficientă. Folosim un max heap pentru a obține rapid cele două pietre cele mai grele, simulând jocul până când rămâne cel mult o piatră, returnând greutatea acesteia sau 0 dacă nu mai rămâne nicio piatră.
