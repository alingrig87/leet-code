# Soluție Detaliată - Next Greater Element I

## Ce Ne Cere Problema?

Problema ne cere să găsim pentru fiecare element din `nums1` următorul element mai mare din `nums2`. Dacă nu există un element mai mare, returnăm -1.

De exemplu, dacă avem `nums1 = [4, 1, 2]` și `nums2 = [1, 3, 4, 2]`:
- Pentru 4 din nums1, următorul element mai mare în nums2 este -1 (nu există)
- Pentru 1 din nums1, următorul element mai mare în nums2 este 3
- Pentru 2 din nums1, următorul element mai mare în nums2 este -1 (nu există)

Rezultat: `[-1, 3, -1]`.

## Ce Este un Monotonic Stack?

Un monotonic stack este un stack care menține elementele într-o anumită ordine (crescătoare sau descrescătoare). În cazul nostru, folosim un stack descrescător - elementele mai mici sunt eliminate când întâlnim un element mai mare.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Parcurgem `nums2` de la dreapta la stânga
2. Pentru fiecare element, eliminăm din stack toate elementele mai mici sau egale
3. Următorul element mai mare este elementul din vârful stack-ului (sau -1 dacă stack-ul este gol)
4. Adăugăm elementul curent în stack
5. La final, căutăm rezultatele pentru elementele din `nums1` într-un map

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int[] nextGreaterElement(int[] nums1, int[] nums2) {
```

Această linie definește funcția `nextGreaterElement`. Funcția primește:
- `nums1` - array-ul pentru care căutăm următorul element mai mare
- `nums2` - array-ul în care căutăm

Funcția returnează `int[]` - array-ul cu următorul element mai mare pentru fiecare element din `nums1`.

```java
    Map<Integer, Integer> map = new HashMap<>();
    Stack<Integer> stack = new Stack<>();
```

Aceste linii creează structurile de date. Să explicăm:

- `map` va stoca mapping-ul între fiecare element din `nums2` și următorul său element mai mare
- `stack` va menține elementele în ordine descrescătoare

```java
    for (int i = nums2.length - 1; i >= 0; i--) {
```

Această buclă parcurge `nums2` de la dreapta la stânga. Parcurgem de la sfârșit pentru că vrem să știm care este următorul element mai mare care apare după elementul curent.

```java
        while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
            stack.pop();
        }
```

Această buclă elimină elementele mai mici sau egale din stack. Să explicăm:

- `!stack.isEmpty()` verifică dacă stack-ul nu este gol
- `stack.peek() <= nums2[i]` verifică dacă elementul din vârful stack-ului este mai mic sau egal cu elementul curent
- Dacă da, `stack.pop()` elimină elementul (pentru că nu poate fi următorul element mai mare pentru elementele anterioare)

De exemplu, dacă `nums2[i] = 4` și stack-ul conține `[2, 3]`:
- `stack.peek() = 3 <= 4`? `true`, eliminăm 3
- `stack.peek() = 2 <= 4`? `true`, eliminăm 2
- Stack-ul devine gol

```java
        map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
```

Această linie stochează următorul element mai mare. Să explicăm:

- `stack.isEmpty() ? -1 : stack.peek()` verifică dacă stack-ul este gol
- Dacă da, următorul element mai mare este -1 (nu există)
- Dacă nu, următorul element mai mare este elementul din vârful stack-ului
- `map.put(nums2[i], ...)` stochează mapping-ul pentru elementul curent

```java
        stack.push(nums2[i]);
```

Această linie adaugă elementul curent în stack. Elementul curent poate fi următorul element mai mare pentru elementele anterioare.

```java
    int[] result = new int[nums1.length];
    for (int i = 0; i < nums1.length; i++) {
        result[i] = map.get(nums1[i]);
    }
    return result;
```

Aceste linii construiesc rezultatul. Pentru fiecare element din `nums1`, căutăm următorul element mai mare în map și îl adăugăm în rezultat.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `nums1 = [4, 1, 2]` și `nums2 = [1, 3, 4, 2]`:

**Parcurgere nums2 de la dreapta la stânga:**

**Iterația 1 (i = 3, nums2[3] = 2):**
- Stack: `[]`
- `stack.isEmpty()`? `true`, deci `map.put(2, -1)`
- `stack.push(2)` → Stack: `[2]`

**Iterația 2 (i = 2, nums2[2] = 4):**
- Stack: `[2]`
- `stack.peek() = 2 <= 4`? `true`, eliminăm 2
- Stack: `[]`
- `stack.isEmpty()`? `true`, deci `map.put(4, -1)`
- `stack.push(4)` → Stack: `[4]`

**Iterația 3 (i = 1, nums2[1] = 3):**
- Stack: `[4]`
- `stack.peek() = 4 <= 3`? `false`, nu eliminăm
- `map.put(3, 4)`
- `stack.push(3)` → Stack: `[4, 3]`

**Iterația 4 (i = 0, nums2[0] = 1):**
- Stack: `[4, 3]`
- `stack.peek() = 3 <= 1`? `false`, nu eliminăm
- `map.put(1, 3)`
- `stack.push(1)` → Stack: `[4, 3, 1]`

**Map final:** `{2: -1, 4: -1, 3: 4, 1: 3}`

**Căutare pentru nums1:**
- `result[0] = map.get(4) = -1`
- `result[1] = map.get(1) = 3`
- `result[2] = map.get(2) = -1`

**Rezultat:** `[-1, 3, -1]`.

## De Ce Este Această Soluție Eficientă?

1. **O(n + m) timp**: Parcurgem `nums2` o dată (O(n)) și `nums1` o dată (O(m)).

2. **O(n) spațiu**: Map-ul și stack-ul pot conține cel mult n elemente.

3. **Monotonic stack**: Stack-ul menține elementele în ordine descrescătoare, permițând găsirea rapidă a următorului element mai mare.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n + m) - unde n este lungimea lui `nums2` și m este lungimea lui `nums1`.

- **Complexitatea spațiului**: O(n) - map-ul și stack-ul pot conține cel mult n elemente.

## Concluzie

Această soluție este elegantă și eficientă. Folosim un monotonic stack pentru a găsi rapid următorul element mai mare pentru fiecare element din `nums2`, apoi căutăm rezultatele pentru elementele din `nums1` într-un map, obținând astfel soluția într-o singură parcurgere.
