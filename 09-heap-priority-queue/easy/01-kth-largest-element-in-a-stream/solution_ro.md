# Soluție Detaliată - Kth Largest Element in a Stream

## Ce Ne Cere Problema?

Problema ne cere să proiectăm o clasă care poate găsi al k-lea cel mai mare element dintr-un stream (flux) de numere. Trebuie să putem adăuga numere în stream și să obținem rapid al k-lea cel mai mare element.

De exemplu, dacă `k = 3` și stream-ul este `[4, 5, 8, 2]`, al 3-lea cel mai mare element este 4 (elementele sortate descrescător: 8, 5, 4, 2).

## Ce Este un Min Heap?

Un min heap este o structură de date unde elementul minim este întotdeauna în vârf. În Java, `PriorityQueue` este un min heap implicit.

## De Ce Folosim un Min Heap de Dimensiune k?

Dacă menținem un min heap de dimensiune k, elementul din vârf (cel mai mic din heap) este exact al k-lea cel mai mare element din stream. De exemplu, dacă `k = 3` și heap-ul conține `[4, 5, 8]`, elementul din vârf (4) este al 3-lea cel mai mare.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Menținem un min heap de dimensiune maximă k
2. Când adăugăm un element:
   - Dacă heap-ul are mai puțin de k elemente, îl adăugăm
   - Dacă heap-ul are k elemente și noul element este mai mare decât minimul, eliminăm minimul și adăugăm noul element
3. Al k-lea cel mai mare element este întotdeauna elementul din vârful heap-ului

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
class KthLargest {
    private PriorityQueue<Integer> minHeap;
    private int k;
```

Aceste linii definesc clasa și variabilele de instanță. Să explicăm:

- `PriorityQueue<Integer> minHeap` este min heap-ul care va menține cei k cei mai mari elemente
- `int k` este valoarea k (al k-lea cel mai mare element)

```java
    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();
        
        for (int num : nums) {
            add(num);
        }
    }
```

Acesta este constructorul clasei. Să explicăm:

- `this.k = k` stochează valoarea k
- `this.minHeap = new PriorityQueue<>()` creează un min heap gol
- `for (int num : nums)` parcurge elementele inițiale
- `add(num)` adaugă fiecare element folosind metoda `add` (care va menține heap-ul de dimensiune k)

```java
    public int add(int val) {
```

Această linie definește metoda `add` care adaugă un element în stream și returnează al k-lea cel mai mare element.

```java
        if (minHeap.size() < k) {
            minHeap.offer(val);
        }
```

Această condiție adaugă elementul dacă heap-ul nu este plin. Să explicăm:

- `minHeap.size() < k` verifică dacă heap-ul are mai puțin de k elemente
- Dacă da, `minHeap.offer(val)` adaugă elementul în heap

De exemplu, dacă `k = 3` și heap-ul are `[4, 5]` (2 elemente), adăugăm noul element direct.

```java
        else if (val > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(val);
        }
```

Această condiție înlocuiește minimul dacă noul element este mai mare. Să explicăm:

- `val > minHeap.peek()` verifică dacă noul element este mai mare decât minimul din heap
- Dacă da, `minHeap.poll()` elimină minimul (cel mai mic element)
- `minHeap.offer(val)` adaugă noul element

De exemplu, dacă `k = 3`, heap-ul este `[4, 5, 8]` (minimul este 4) și adăugăm 9:
- `9 > 4`? `true`
- Eliminăm 4, adăugăm 9
- Heap devine `[5, 8, 9]` (minimul este 5, care este al 3-lea cel mai mare)

```java
        return minHeap.peek();
```

Această linie returnează al k-lea cel mai mare element, care este elementul din vârful heap-ului (minimul din cei k cei mai mari).

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `k = 3` și `nums = [4, 5, 8, 2]`:

**Inițializare:**
- `k = 3`
- `minHeap = []`

**add(4):**
- `minHeap.size() = 0 < 3`? `true`
- `minHeap.offer(4)` → `minHeap = [4]`
- Returnăm `4`

**add(5):**
- `minHeap.size() = 1 < 3`? `true`
- `minHeap.offer(5)` → `minHeap = [4, 5]`
- Returnăm `4`

**add(8):**
- `minHeap.size() = 2 < 3`? `true`
- `minHeap.offer(8)` → `minHeap = [4, 5, 8]`
- Returnăm `4`

**add(2):**
- `minHeap.size() = 3 < 3`? `false`
- `2 > 4`? `false`, nu adăugăm
- Returnăm `4`

**add(3):**
- `minHeap.size() = 3 < 3`? `false`
- `3 > 4`? `false`, nu adăugăm
- Returnăm `4`

**add(5):**
- `minHeap.size() = 3 < 3`? `false`
- `5 > 4`? `true`
- `minHeap.poll()` → eliminăm 4, `minHeap = [5, 5, 8]`
- `minHeap.offer(5)` → `minHeap = [5, 5, 8]`
- Returnăm `5`

## De Ce Este Această Soluție Eficientă?

1. **O(log k) per adăugare**: Adăugarea și eliminarea dintr-un heap sunt O(log k).

2. **O(k) spațiu**: Heap-ul conține cel mult k elemente.

3. **O(1) pentru găsirea k-lea element**: Elementul din vârf este întotdeauna al k-lea cel mai mare.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n log k) - unde n este numărul de adăugări. Fiecare adăugare este O(log k).

- **Complexitatea spațiului**: O(k) - heap-ul conține cel mult k elemente.

## Concluzie

Această soluție este elegantă și eficientă. Folosim un min heap de dimensiune k pentru a menține cei k cei mai mari elemente, astfel încât elementul din vârf să fie întotdeauna al k-lea cel mai mare element, permițând găsirea rapidă a acestuia.
