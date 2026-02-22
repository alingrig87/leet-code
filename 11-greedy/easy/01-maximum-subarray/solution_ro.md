# Soluție Detaliată - Maximum Subarray

## Ce Ne Cere Problema?

Problema ne cere să găsim subarray-ul continuu (subșir continuu) cu suma maximă dintr-un array care poate conține numere negative.

De exemplu, dacă avem `nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]`, subarray-ul cu suma maximă este `[4, -1, 2, 1]` cu suma 6.

## Ce Este Algoritmul lui Kadane?

Algoritmul lui Kadane este un algoritm de programare dinamică care rezolvă problema subarray-ului maxim într-o singură parcurgere. Ideea principală este că la fiecare poziție, decidem dacă să începem un subarray nou sau să extindem subarray-ul anterior.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Menținem două variabile: `currentSum` (suma subarray-ului care se termină la poziția curentă) și `maxSum` (suma maximă văzută până acum)
2. Pentru fiecare element, decidem dacă să începem un subarray nou sau să extindem subarray-ul anterior
3. Dacă `currentSum` devine negativ, este mai bine să începem un subarray nou (pentru că un număr negativ nu poate ajuta)
4. Actualizăm `maxSum` dacă `currentSum` este mai mare

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int maxSubArray(int[] nums) {
```

Această linie definește funcția `maxSubArray`. Funcția primește:
- `nums` - array-ul de numere (poate conține negative)

Funcția returnează `int` - suma maximă a unui subarray continuu.

```java
    if (nums == null || nums.length == 0) {
        return 0;
    }
```

Această verificare tratează cazul când array-ul este null sau gol. Dacă array-ul este gol, suma maximă este 0.

```java
    if (nums.length == 1) {
        return nums[0];
    }
```

Această verificare tratează cazul când array-ul are un singur element. În acest caz, suma maximă este acel element.

```java
    int currentSum = nums[0];
    int maxSum = nums[0];
```

Aceste linii inițializează variabilele. Să explicăm:

- `currentSum` este suma subarray-ului care se termină la poziția curentă (inițializat cu primul element)
- `maxSum` este suma maximă văzută până acum (inițializat cu primul element)

```java
    for (int i = 1; i < nums.length; i++) {
```

Această buclă parcurge restul elementelor începând de la al doilea (indicele 1).

```java
        currentSum = Math.max(nums[i], currentSum + nums[i]);
```

Această linie decide dacă să începem un subarray nou sau să extindem subarray-ul anterior. Să explicăm:

- `nums[i]` este opțiunea de a începe un subarray nou de la poziția curentă
- `currentSum + nums[i]` este opțiunea de a extinde subarray-ul anterior
- `Math.max(...)` alege opțiunea care dă suma mai mare

De exemplu, dacă `currentSum = -2` și `nums[i] = 1`:
- `Math.max(1, -2 + 1) = Math.max(1, -1) = 1`
- Alegem să începem un subarray nou (1 este mai mare decât -1)

De ce funcționează? Dacă `currentSum` este negativ, atunci `currentSum + nums[i] < nums[i]` (pentru că adăugăm un număr negativ), deci este mai bine să începem un subarray nou.

```java
        maxSum = Math.max(maxSum, currentSum);
```

Această linie actualizează suma maximă. Dacă `currentSum` este mai mare decât `maxSum`, actualizăm `maxSum`.

```java
    return maxSum;
```

Această linie returnează suma maximă găsită.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]`:

**Inițializare:**
- `currentSum = -2`, `maxSum = -2`

**Iterația 1 (i = 1, nums[1] = 1):**
- `currentSum = max(1, -2 + 1) = max(1, -1) = 1`
- `maxSum = max(-2, 1) = 1`
- Subarray curent: `[1]`

**Iterația 2 (i = 2, nums[2] = -3):**
- `currentSum = max(-3, 1 + (-3)) = max(-3, -2) = -2`
- `maxSum = max(1, -2) = 1`
- Subarray curent: `[1, -3]` (suma -2, dar maxSum rămâne 1)

**Iterația 3 (i = 3, nums[3] = 4):**
- `currentSum = max(4, -2 + 4) = max(4, 2) = 4`
- `maxSum = max(1, 4) = 4`
- Subarray curent: `[4]` (am început un subarray nou)

**Iterația 4 (i = 4, nums[4] = -1):**
- `currentSum = max(-1, 4 + (-1)) = max(-1, 3) = 3`
- `maxSum = max(4, 3) = 4`
- Subarray curent: `[4, -1]`

**Iterația 5 (i = 5, nums[5] = 2):**
- `currentSum = max(2, 3 + 2) = max(2, 5) = 5`
- `maxSum = max(4, 5) = 5`
- Subarray curent: `[4, -1, 2]`

**Iterația 6 (i = 6, nums[6] = 1):**
- `currentSum = max(1, 5 + 1) = max(1, 6) = 6`
- `maxSum = max(5, 6) = 6`
- Subarray curent: `[4, -1, 2, 1]`

**Iterația 7 (i = 7, nums[7] = -5):**
- `currentSum = max(-5, 6 + (-5)) = max(-5, 1) = 1`
- `maxSum = max(6, 1) = 6`
- Subarray curent: `[4, -1, 2, 1, -5]`

**Iterația 8 (i = 8, nums[8] = 4):**
- `currentSum = max(4, 1 + 4) = max(4, 5) = 5`
- `maxSum = max(6, 5) = 6`
- Subarray curent: `[4, -1, 2, 1, -5, 4]`

**Rezultat:** `6` - suma maximă este 6 (subarray-ul `[4, -1, 2, 1]`).

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Parcurgem array-ul o singură dată, făcând O(1) operații pentru fiecare element.

2. **O(1) spațiu**: Folosim doar două variabile, nu creăm structuri de date suplimentare.

3. **Algoritm optim**: Algoritmul lui Kadane este optim pentru această problemă.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de elemente. Parcurgem array-ul o singură dată.

- **Complexitatea spațiului**: O(1) - folosim doar două variabile, nu creăm structuri de date suplimentare.

## Concluzie

Această soluție este elegantă și eficientă. Folosim algoritmul lui Kadane pentru a găsi subarray-ul cu suma maximă, decidând la fiecare poziție dacă să începem un subarray nou sau să extindem subarray-ul anterior, obținând astfel soluția într-o singură parcurgere și fără spațiu suplimentar.
