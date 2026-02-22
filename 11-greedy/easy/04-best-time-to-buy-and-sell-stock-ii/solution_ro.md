# Soluție Detaliată - Best Time to Buy and Sell Stock II

## Ce Ne Cere Problema?

Problema ne cere să găsim profitul maxim pe care îl putem obține din tranzacții nelimitate de cumpărare și vânzare de acțiuni. Putem cumpăra și vinde de oricâte ori vrem, dar trebuie să vindem înainte de a cumpăra din nou.

De exemplu, dacă avem `prices = [7, 1, 5, 3, 6, 4]`:
- Putem cumpăra la 1, vinde la 5 (profit 4)
- Putem cumpăra la 3, vinde la 6 (profit 3)
- Profit total: 4 + 3 = 7

## De Ce Putem Suma Toate Diferențele Pozitive?

Deoarece putem face tranzacții nelimitate, putem "captura" fiecare creștere de preț. Dacă prețurile cresc de la a la d: [a, b, c, d] unde a < b < c < d:
- Strategia 1: Cumpărăm la a, vindem la d → Profit = d - a
- Strategia 2: Cumpărăm la a, vindem la b, cumpărăm la b, vindem la c, cumpărăm la c, vindem la d → Profit = (b-a) + (c-b) + (d-c) = d - a (același!)

Prin urmare, putem simplu să sumăm toate diferențele pozitive zilnice.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Parcurgem array-ul de prețuri
2. Pentru fiecare zi, calculăm diferența față de ziua anterioară
3. Dacă diferența este pozitivă (prețul a crescut), o adăugăm la profit
4. Suma tuturor diferențelor pozitive este profitul maxim

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int maxProfit(int[] prices) {
```

Această linie definește funcția `maxProfit`. Funcția primește:
- `prices` - array-ul cu prețurile acțiunilor pentru fiecare zi

Funcția returnează `int` - profitul maxim obținut.

```java
    if (prices == null || prices.length <= 1) {
        return 0;
    }
```

Această verificare tratează cazurile speciale. Dacă nu există prețuri sau există cel mult un preț, nu putem face tranzacții, deci profitul este 0.

```java
    int maxProfit = 0;
```

Această linie inițializează profitul maxim la 0.

```java
    for (int i = 1; i < prices.length; i++) {
```

Această buclă parcurge prețurile începând de la a doua zi (indicele 1). Comparăm fiecare zi cu ziua anterioară pentru a găsi creșterile de preț.

```java
        int priceDifference = prices[i] - prices[i - 1];
```

Această linie calculează diferența de preț între ziua curentă și ziua anterioară. Să explicăm:

- `prices[i]` este prețul zilei curente
- `prices[i - 1]` este prețul zilei anterioare
- `priceDifference` este diferența (pozitivă dacă prețul a crescut, negativă dacă a scăzut)

De exemplu, dacă `prices[i] = 5` și `prices[i - 1] = 3`:
- `priceDifference = 5 - 3 = 2` (prețul a crescut cu 2)

```java
        if (priceDifference > 0) {
            maxProfit += priceDifference;
        }
```

Această condiție adaugă diferența la profit dacă este pozitivă. Să explicăm:

- `priceDifference > 0` verifică dacă prețul a crescut (diferența este pozitivă)
- Dacă da, `maxProfit += priceDifference` adaugă diferența la profit

De ce adăugăm doar diferențele pozitive? Pentru că dacă prețul scade, nu facem tranzacție (nu cumpărăm când prețul scade). Dacă prețul crește, putem câștiga profit cumpărând înainte de creștere și vânzând după creștere.

De exemplu, dacă `priceDifference = 2`:
- `2 > 0`? `true`
- `maxProfit += 2` → profitul crește cu 2

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `prices = [7, 1, 5, 3, 6, 4]`:

**Inițializare:**
- `maxProfit = 0`

**Iterația 1 (i = 1):**
- `priceDifference = 1 - 7 = -6`
- `-6 > 0`? `false`, nu adăugăm
- `maxProfit = 0`

**Iterația 2 (i = 2):**
- `priceDifference = 5 - 1 = 4`
- `4 > 0`? `true`
- `maxProfit += 4` → `maxProfit = 4`
- (Cumpărăm la 1, vindem la 5, profit 4)

**Iterația 3 (i = 3):**
- `priceDifference = 3 - 5 = -2`
- `-2 > 0`? `false`, nu adăugăm
- `maxProfit = 4`

**Iterația 4 (i = 4):**
- `priceDifference = 6 - 3 = 3`
- `3 > 0`? `true`
- `maxProfit += 3` → `maxProfit = 7`
- (Cumpărăm la 3, vindem la 6, profit 3)

**Iterația 5 (i = 5):**
- `priceDifference = 4 - 6 = -2`
- `-2 > 0`? `false`, nu adăugăm
- `maxProfit = 7`

**Rezultat:** `7` - profitul maxim este 7 (4 + 3).

## Alt Exemplu - Toate Prețurile Cresc

Să urmărim pentru `prices = [1, 2, 3, 4, 5]`:

**Iterația 1:** `priceDifference = 1`, `maxProfit = 1`
**Iterația 2:** `priceDifference = 1`, `maxProfit = 2`
**Iterația 3:** `priceDifference = 1`, `maxProfit = 3`
**Iterația 4:** `priceDifference = 1`, `maxProfit = 4`

**Rezultat:** `4` - profitul maxim este 4 (suma tuturor creșterilor).

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Parcurgem array-ul o singură dată, făcând O(1) operații pentru fiecare zi.

2. **O(1) spațiu**: Folosim doar câteva variabile, nu creăm structuri de date suplimentare.

3. **Greedy optim**: Strategia de a captura fiecare creștere de preț este optimă când putem face tranzacții nelimitate.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de zile. Parcurgem array-ul o singură dată.

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile, nu creăm structuri de date suplimentare.

## Concluzie

Această soluție este simplă și eficientă. Deoarece putem face tranzacții nelimitate, putem captura fiecare creștere de preț prin sumarea tuturor diferențelor pozitive zilnice, obținând astfel profitul maxim într-o singură parcurgere și fără spațiu suplimentar.
