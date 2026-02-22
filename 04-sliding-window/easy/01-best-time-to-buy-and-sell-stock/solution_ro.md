# Soluție Detaliată - Best Time to Buy and Sell Stock

## Ce Ne Cere Problema?

Problema ne cere să găsim profitul maxim pe care îl putem obține dintr-o tranzacție de cumpărare și vânzare de acțiuni. Avem un array de prețuri, unde fiecare element reprezintă prețul acțiunii într-o anumită zi. Putem cumpăra o dată și vinde o dată, și vrem să găsim profitul maxim posibil.

De exemplu, dacă avem prețurile `[7, 1, 5, 3, 6, 4]`, cel mai bun moment este să cumpărăm la ziua 2 (prețul 1) și să vindem la ziua 5 (prețul 6), obținând un profit de 6 - 1 = 5.

## Ce Este Profitul?

Profitul este diferența dintre prețul de vânzare și prețul de cumpărare. De exemplu, dacă cumpărăm la prețul 10 și vindem la prețul 15, profitul este 15 - 10 = 5.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Parcurgem array-ul o singură dată
2. Pentru fiecare zi, ținem minte prețul minim văzut până acum (cel mai bun moment de cumpărare)
3. Calculăm profitul dacă am vinde în ziua curentă (prețul curent - prețul minim)
4. Actualizăm profitul maxim dacă profitul curent este mai mare
5. Actualizăm prețul minim dacă prețul curent este mai mic

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int maxProfit(int[] prices) {
```

Această linie definește funcția `maxProfit`. Funcția primește:
- `prices` - array-ul de prețuri, unde `prices[i]` este prețul în ziua `i`

Funcția returnează `int` - profitul maxim posibil.

```java
    if (prices == null || prices.length < 2) {
        return 0;
    }
```

Această verificare tratează cazurile speciale. Să explicăm:

- Dacă array-ul este null sau are mai puțin de 2 elemente, nu putem face o tranzacție (avem nevoie de cel puțin 2 zile - una pentru cumpărare și una pentru vânzare)
- În acest caz, returnăm 0 (nu putem obține profit)

```java
    int minPrice = prices[0];
    int maxProfit = 0;
```

Aceste linii inițializează variabilele. Să explicăm:

- `minPrice = prices[0]` - prețul minim văzut până acum, inițializat cu prețul primei zile
- `maxProfit = 0` - profitul maxim obținut până acum, inițializat cu 0 (nu am făcut încă nicio tranzacție profitabilă)

```java
    for (int i = 1; i < prices.length; i++) {
```

Această buclă parcurge array-ul de la a doua zi (indicele 1) până la sfârșit. De ce începem de la 1? Pentru că prima zi (indicele 0) este folosită doar pentru inițializarea `minPrice`. Nu putem vinde în prima zi (nu am cumpărat încă).

```java
        minPrice = Math.min(minPrice, prices[i]);
```

Această linie actualizează prețul minim. Să explicăm:

- `Math.min(minPrice, prices[i])` compară prețul minim văzut până acum cu prețul curent și returnează cel mai mic
- `minPrice = ...` actualizează prețul minim dacă prețul curent este mai mic

De ce facem asta? Pentru că vrem să cumpărăm la cel mai mic preț posibil. Dacă prețul curent este mai mic decât prețul minim văzut până acum, actualizăm prețul minim.

De exemplu, dacă `minPrice = 5` și `prices[i] = 3`, atunci `minPrice` devine 3 (am găsit un preț mai bun de cumpărare).

```java
        int profit = prices[i] - minPrice;
```

Această linie calculează profitul dacă am vinde în ziua curentă. Să explicăm:

- `prices[i]` este prețul de vânzare în ziua curentă
- `minPrice` este prețul minim de cumpărare văzut până acum (cel mai bun moment de cumpărare)
- `profit = prices[i] - minPrice` calculează profitul dacă am cumpăra la `minPrice` și am vinde la `prices[i]`

De exemplu, dacă `minPrice = 1` și `prices[i] = 6`, atunci `profit = 6 - 1 = 5`.

```java
        maxProfit = Math.max(maxProfit, profit);
```

Această linie actualizează profitul maxim. Să explicăm:

- `Math.max(maxProfit, profit)` compară profitul maxim obținut până acum cu profitul curent și returnează cel mai mare
- `maxProfit = ...` actualizează profitul maxim dacă profitul curent este mai mare

De ce facem asta? Pentru că vrem să ținem minte cel mai mare profit pe care l-am putut obține până acum.

De exemplu, dacă `maxProfit = 3` și `profit = 5`, atunci `maxProfit` devine 5 (am găsit o tranzacție mai profitabilă).

```java
    return maxProfit;
```

Această linie returnează profitul maxim obținut după ce am parcurs toate zilele.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `prices = [7, 1, 5, 3, 6, 4]`:

**Inițializare:**
- `minPrice = 7` (prețul primei zile)
- `maxProfit = 0`

**Iterația 1 (i = 1, prices[1] = 1):**
- `minPrice = min(7, 1) = 1` (actualizăm prețul minim)
- `profit = 1 - 1 = 0` (dacă am vinde acum, profitul ar fi 0)
- `maxProfit = max(0, 0) = 0`

**Iterația 2 (i = 2, prices[2] = 5):**
- `minPrice = min(1, 5) = 1` (prețul minim rămâne 1)
- `profit = 5 - 1 = 4` (dacă am cumpăra la 1 și am vinde la 5, profitul ar fi 4)
- `maxProfit = max(0, 4) = 4` (actualizăm profitul maxim)

**Iterația 3 (i = 3, prices[3] = 3):**
- `minPrice = min(1, 3) = 1` (prețul minim rămâne 1)
- `profit = 3 - 1 = 2` (profitul ar fi 2)
- `maxProfit = max(4, 2) = 4` (profitul maxim rămâne 4)

**Iterația 4 (i = 4, prices[4] = 6):**
- `minPrice = min(1, 6) = 1` (prețul minim rămâne 1)
- `profit = 6 - 1 = 5` (profitul ar fi 5)
- `maxProfit = max(4, 5) = 5` (actualizăm profitul maxim)

**Iterația 5 (i = 5, prices[5] = 4):**
- `minPrice = min(1, 4) = 1` (prețul minim rămâne 1)
- `profit = 4 - 1 = 3` (profitul ar fi 3)
- `maxProfit = max(5, 3) = 5` (profitul maxim rămâne 5)

**După toate iterațiile:**
- `maxProfit = 5`
- Returnăm 5

**Rezultat:** 5 - profitul maxim este 5 (cumpărăm la ziua 2 cu prețul 1 și vindem la ziua 5 cu prețul 6).

## Alt Exemplu - Prețuri Descrescătoare

Să urmărim pentru `prices = [7, 6, 4, 3, 1]`:

**Inițializare:**
- `minPrice = 7`, `maxProfit = 0`

**Iterația 1 (i = 1, prices[1] = 6):**
- `minPrice = min(7, 6) = 6`
- `profit = 6 - 6 = 0`
- `maxProfit = 0`

**Iterația 2 (i = 2, prices[2] = 4):**
- `minPrice = min(6, 4) = 4`
- `profit = 4 - 4 = 0`
- `maxProfit = 0`

**Iterația 3 (i = 3, prices[3] = 3):**
- `minPrice = min(4, 3) = 3`
- `profit = 3 - 3 = 0`
- `maxProfit = 0`

**Iterația 4 (i = 4, prices[4] = 1):**
- `minPrice = min(3, 1) = 1`
- `profit = 1 - 1 = 0`
- `maxProfit = 0`

**Rezultat:** 0 - nu există nicio tranzacție profitabilă (prețurile scad continuu).

## De Ce Este Această Soluție Eficientă?

1. **Parcurgem array-ul o singură dată**: Nu comparăm fiecare zi cu toate celelalte zile, ci doar ținem minte prețul minim și calculăm profitul pentru fiecare zi.

2. **O(n) timp**: Parcurgem array-ul o singură dată, făcând O(1) operații pentru fiecare element.

3. **O(1) spațiu**: Folosim doar câteva variabile, nu creăm structuri de date suplimentare.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de zile. Parcurgem array-ul o singură dată.

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile, nu creăm structuri de date suplimentare.

## Concluzie

Această soluție este elegantă și eficientă. Prin păstrarea prețului minim văzut și calcularea profitului pentru fiecare zi, găsim profitul maxim într-o singură parcurgere a array-ului.
