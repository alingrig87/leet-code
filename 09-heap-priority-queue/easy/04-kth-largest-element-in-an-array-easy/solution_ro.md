# Soluție Detaliată - Kth Largest Element in an Array

## Ce Ne Cere Problema?

Problema ne cere să găsim al k-lea cel mai mare element dintr-un array nesortat. De exemplu, dacă avem `nums = [3, 2, 1, 5, 6, 4]` și `k = 2`, al 2-lea cel mai mare element este 5 (elementele sortate descrescător: 6, 5, 4, 3, 2, 1).

## De Ce Folosim un Min Heap de Dimensiune k?

Dacă menținem un min heap de dimensiune k, elementul din vârf (cel mai mic din heap) este exact al k-lea cel mai mare element din array. De exemplu, dacă `k = 2` și heap-ul conține `[5, 6]`, elementul din vârf (5) este al 2-lea cel mai mare.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Menținem un min heap de dimensiune maximă k
2. Pentru fiecare element din array:
   - Dacă heap-ul are mai puțin de k elemente, îl adăugăm
   - Dacă heap-ul are k elemente și noul element este mai mare decât minimul, eliminăm minimul și adăugăm noul element
3. Al k-lea cel mai mare element este elementul din vârful heap-ului

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int findKthLargest(int[] nums, int k) {
```

Această linie definește funcția `findKthLargest`. Funcția primește:
- `nums` - array-ul nesortat
- `k` - valoarea k (al k-lea cel mai mare element)

Funcția returnează `int` - al k-lea cel mai mare element.

```java
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
```

Această linie creează un min heap gol. Min heap-ul va menține cei k cei mai mari elemente.

```java
    for (int num : nums) {
```

Această buclă parcurge fiecare element din array. `num` este variabila care stochează elementul curent.

```java
        if (minHeap.size() < k) {
            minHeap.offer(num);
        }
```

Această condiție adaugă elementul dacă heap-ul nu este plin. Să explicăm:

- `minHeap.size() < k` verifică dacă heap-ul are mai puțin de k elemente
- Dacă da, `minHeap.offer(num)` adaugă elementul în heap

De exemplu, dacă `k = 2` și heap-ul are `[3]` (1 element), adăugăm noul element direct.

```java
        else if (num > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(num);
        }
```

Această condiție înlocuiește minimul dacă noul element este mai mare. Să explicăm:

- `num > minHeap.peek()` verifică dacă noul element este mai mare decât minimul din heap
- Dacă da, `minHeap.poll()` elimină minimul (cel mai mic element)
- `minHeap.offer(num)` adaugă noul element

De exemplu, dacă `k = 2`, heap-ul este `[3, 4]` (minimul este 3) și adăugăm 5:
- `5 > 3`? `true`
- Eliminăm 3, adăugăm 5
- Heap devine `[4, 5]` (minimul este 4, care este al 2-lea cel mai mare)

```java
    return minHeap.peek();
```

Această linie returnează al k-lea cel mai mare element, care este elementul din vârful heap-ului (minimul din cei k cei mai mari).

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `nums = [3, 2, 1, 5, 6, 4]` și `k = 2`:

**Inițializare:**
- `minHeap = []`

**Iterația 1 (num = 3):**
- `minHeap.size() = 0 < 2`? `true`
- `minHeap.offer(3)` → `minHeap = [3]`

**Iterația 2 (num = 2):**
- `minHeap.size() = 1 < 2`? `true`
- `minHeap.offer(2)` → `minHeap = [2, 3]`

**Iterația 3 (num = 1):**
- `minHeap.size() = 2 < 2`? `false`
- `1 > 2`? `false`, nu adăugăm
- `minHeap = [2, 3]`

**Iterația 4 (num = 5):**
- `minHeap.size() = 2 < 2`? `false`
- `5 > 2`? `true`
- `minHeap.poll()` → eliminăm 2, `minHeap = [3]`
- `minHeap.offer(5)` → `minHeap = [3, 5]`

**Iterația 5 (num = 6):**
- `minHeap.size() = 2 < 2`? `false`
- `6 > 3`? `true`
- `minHeap.poll()` → eliminăm 3, `minHeap = [5]`
- `minHeap.offer(6)` → `minHeap = [5, 6]`

**Iterația 6 (num = 4):**
- `minHeap.size() = 2 < 2`? `false`
- `4 > 5`? `false`, nu adăugăm
- `minHeap = [5, 6]`

**După toate iterațiile:**
- `minHeap = [5, 6]`
- Returnăm `minHeap.peek() = 5`

**Rezultat:** `5` - al 2-lea cel mai mare element este 5.

## De Ce Este Această Soluție Eficientă?

1. **O(n log k) timp**: Unde n este numărul de elemente. Fiecare operație pe heap este O(log k).

2. **O(k) spațiu**: Heap-ul conține cel mult k elemente.

3. **Eficient pentru k mic**: Dacă k este mic comparativ cu n, această soluție este foarte eficientă.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n log k) - unde n este numărul de elemente și k este valoarea k. Fiecare operație pe heap este O(log k).

- **Complexitatea spațiului**: O(k) - heap-ul conține cel mult k elemente.

## Concluzie

Această soluție este elegantă și eficientă. Folosim un min heap de dimensiune k pentru a menține cei k cei mai mari elemente, astfel încât elementul din vârf să fie întotdeauna al k-lea cel mai mare element, permițând găsirea rapidă a acestuia.
