# Soluție Detaliată - Minimum Size Subarray Sum

## Ce Ne Cere Problema?

Problema ne cere să găsim lungimea minimă a unui subarray (subșir continuu) a cărui sumă este mai mare sau egală cu o valoare dată (target). Dacă nu există un astfel de subarray, returnăm 0.

De exemplu, dacă avem `nums = [2, 3, 1, 2, 4, 3]` și `target = 7`, trebuie să găsim subarray-ul de lungime minimă cu suma >= 7. În acest caz, subarray-ul `[4, 3]` are suma 7 și lungimea 2, care este minimul.

## Ce Este Sliding Window Variabil?

Sliding Window variabil este o tehnică unde dimensiunea ferestrei se schimbă în funcție de condiții. În cazul nostru:
- Expandăm fereastra (mărim) când suma este mai mică decât target
- Contractăm fereastra (micșorăm) când suma este >= target, încercând să găsim o fereastră mai mică

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Folosim doi pointeri - `left` (stânga) și `right` (dreapta) care definesc fereastra
2. Expandăm fereastra adăugând elemente de la dreapta până când suma >= target
3. Contractăm fereastra eliminând elemente de la stânga, încercând să găsim o fereastră mai mică
4. Ținem minte lungimea minimă a ferestrei care satisface condiția

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int minSubArrayLen(int target, int[] nums) {
```

Această linie definește funcția `minSubArrayLen`. Funcția primește:
- `target` - suma minimă dorită
- `nums` - array-ul de numere

Funcția returnează `int` - lungimea minimă a subarray-ului, sau 0 dacă nu există.

```java
    int left = 0;
    int sum = 0;
    int minLen = Integer.MAX_VALUE;
```

Aceste linii inițializează variabilele. Să explicăm:

- `left = 0` - pointer-ul de la stânga care definește începutul ferestrei
- `sum = 0` - suma elementelor din fereastra curentă
- `minLen = Integer.MAX_VALUE` - lungimea minimă găsită, inițializată cu valoarea maximă posibilă

Folosim `Integer.MAX_VALUE` pentru a putea actualiza `minLen` cu orice lungime găsită (care va fi întotdeauna mai mică decât MAX_VALUE).

```java
    for (int right = 0; right < nums.length; right++) {
```

Această buclă expandează fereastra. `right` este pointer-ul de la dreapta care definește sfârșitul ferestrei.

```java
        sum += nums[right];
```

Această linie adaugă elementul curent la sumă. Când expandăm fereastra la dreapta, adăugăm noul element la sumă.

```java
        while (sum >= target) {
```

Această buclă contractează fereastra. Să explicăm:

- `sum >= target` verifică dacă suma ferestrei curente este >= target
- Dacă da, încercăm să contractăm fereastra (să o micșorăm) pentru a găsi o fereastră mai mică care încă satisface condiția

De ce folosim `while` și nu `if`? Pentru că după ce contractăm fereastra o dată, suma ar putea să fie încă >= target, deci putem continua să contractăm pentru a găsi o fereastră și mai mică.

```java
            minLen = Math.min(minLen, right - left + 1);
```

Această linie actualizează lungimea minimă. Să explicăm:

- `right - left + 1` calculează lungimea ferestrei curente
- `Math.min(minLen, ...)` compară lungimea minimă găsită până acum cu lungimea curentă și păstrează cea mai mică
- `minLen = ...` actualizează lungimea minimă

De exemplu, dacă `minLen = 5` și `right - left + 1 = 3`, atunci `minLen` devine 3.

```java
            sum -= nums[left];
            left++;
```

Aceste linii contractează fereastra. Să explicăm:

- `sum -= nums[left]` elimină elementul de la stânga din sumă
- `left++` mută pointer-ul de la stânga cu o poziție la dreapta, micșorând astfel fereastra

De exemplu, dacă fereastra este `[2, 3, 1, 2]` (suma = 8) și `target = 7`:
- `sum -= nums[0]` → `sum = 8 - 2 = 6`
- `left++` → `left = 1`
- Noua fereastră: `[3, 1, 2]` (suma = 6)
- `6 >= 7`? `false`, bucla se termină

```java
    return minLen == Integer.MAX_VALUE ? 0 : minLen;
```

Această linie returnează rezultatul. Să explicăm:

- `minLen == Integer.MAX_VALUE` verifică dacă nu am găsit niciun subarray valid
- Dacă da, returnăm 0 (nu există subarray cu suma >= target)
- Dacă nu, returnăm `minLen` (lungimea minimă găsită)

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `nums = [2, 3, 1, 2, 4, 3]` și `target = 7`:

**Inițializare:**
- `left = 0`, `sum = 0`, `minLen = MAX_VALUE`

**Iterația 1 (right = 0, nums[0] = 2):**
- `sum += 2` → `sum = 2`
- `2 >= 7`? `false`, nu intrăm în while
- Fereastra: `[2]`, suma = 2

**Iterația 2 (right = 1, nums[1] = 3):**
- `sum += 3` → `sum = 5`
- `5 >= 7`? `false`, nu intrăm în while
- Fereastra: `[2, 3]`, suma = 5

**Iterația 3 (right = 2, nums[2] = 1):**
- `sum += 1` → `sum = 6`
- `6 >= 7`? `false`, nu intrăm în while
- Fereastra: `[2, 3, 1]`, suma = 6

**Iterația 4 (right = 3, nums[3] = 2):**
- `sum += 2` → `sum = 8`
- `8 >= 7`? `true`, intrăm în while
- `minLen = min(MAX_VALUE, 4) = 4`
- `sum -= 2` → `sum = 6`, `left = 1`
- `6 >= 7`? `false`, ieșim din while
- Fereastra: `[3, 1, 2]`, suma = 6

**Iterația 5 (right = 4, nums[4] = 4):**
- `sum += 4` → `sum = 10`
- `10 >= 7`? `true`, intrăm în while
- `minLen = min(4, 4) = 4`
- `sum -= 3` → `sum = 7`, `left = 2`
- `7 >= 7`? `true`, continuăm
- `minLen = min(4, 3) = 3`
- `sum -= 1` → `sum = 6`, `left = 3`
- `6 >= 7`? `false`, ieșim din while
- Fereastra: `[2, 4]`, suma = 6

**Iterația 6 (right = 5, nums[5] = 3):**
- `sum += 3` → `sum = 9`
- `9 >= 7`? `true`, intrăm în while
- `minLen = min(3, 3) = 3`
- `sum -= 2` → `sum = 7`, `left = 4`
- `7 >= 7`? `true`, continuăm
- `minLen = min(3, 2) = 2`
- `sum -= 4` → `sum = 3`, `left = 5`
- `3 >= 7`? `false`, ieșim din while
- Fereastra: `[3]`, suma = 3

**După toate iterațiile:**
- `minLen = 2`
- Returnăm `2`

**Rezultat:** `2` - lungimea minimă este 2 (subarray-ul `[4, 3]`).

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Fiecare element este adăugat și eliminat cel mult o dată, deci avem O(n) operații totale.

2. **O(1) spațiu**: Folosim doar câteva variabile, nu creăm structuri de date suplimentare.

3. **Sliding window optim**: Expandăm și contractăm fereastra eficient, găsind soluția într-o singură parcurgere.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de elemente. Fiecare element este procesat cel mult de două ori (adăugat o dată, eliminat o dată).

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile, nu creăm structuri de date suplimentare.

## Concluzie

Această soluție este elegantă și eficientă. Folosim sliding window variabil pentru a găsi subarray-ul de lungime minimă cu suma >= target, expandând și contractând fereastra eficient într-o singură parcurgere.
