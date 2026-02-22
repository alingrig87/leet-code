# Soluție Detaliată - Max Consecutive Ones

## Ce Ne Cere Problema?

Problema ne cere să găsim numărul maxim de 1-uri consecutive într-un array binar (care conține doar 0 și 1). De exemplu, dacă avem `[1, 1, 0, 1, 1, 1]`, numărul maxim de 1-uri consecutive este 3 (ultimele trei 1-uri).

## Ce Înseamnă "Consecutive"?

"Consecutive" înseamnă "unul după altul, fără întrerupere". De exemplu, în `[1, 1, 0, 1, 1, 1]`:
- Primele două 1-uri sunt consecutive: `[1, 1]` (2 consecutive)
- Apoi apare un 0, care întrerupe secvența
- Ultimele trei 1-uri sunt consecutive: `[1, 1, 1]` (3 consecutive)
- Maximul este 3

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Parcurgem array-ul o singură dată
2. Pentru fiecare element, dacă este 1, incrementăm un counter
3. Dacă elementul este 0, resetăm counter-ul la 0
4. Ținem minte valoarea maximă a counter-ului
5. Returnăm valoarea maximă

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int findMaxConsecutiveOnes(int[] nums) {
```

Această linie definește funcția `findMaxConsecutiveOnes`. Funcția primește:
- `nums` - array-ul binar (conține doar 0 și 1)

Funcția returnează `int` - numărul maxim de 1-uri consecutive.

```java
    int count = 0;
    int maxCount = 0;
```

Aceste linii inițializează counter-urile. Să explicăm:

- `count = 0` - counter-ul curent care numără 1-urile consecutive din secvența curentă
- `maxCount = 0` - valoarea maximă a counter-ului văzută până acum

`count` va număra câte 1-uri consecutive am văzut în secvența curentă, iar `maxCount` va ține minte cel mai mare număr de 1-uri consecutive văzut până acum.

```java
    for (int num : nums) {
```

Această buclă parcurge fiecare element din array. `num` este variabila care stochează elementul curent.

```java
        if (num == 1) {
            count++;
            maxCount = Math.max(maxCount, count);
        }
```

Această parte tratează cazul când elementul este 1. Să explicăm:

- `num == 1` verifică dacă elementul curent este 1
- `count++` mărește counter-ul cu 1 (am găsit încă un 1 consecutiv)
- `maxCount = Math.max(maxCount, count)` actualizează valoarea maximă dacă counter-ul curent este mai mare

De exemplu, dacă `count = 2` și `maxCount = 1`, după `count++` avem `count = 3`, iar `maxCount` devine `max(1, 3) = 3`.

```java
        else {
            count = 0;
        }
```

Această parte tratează cazul când elementul este 0. Să explicăm:

- `else` înseamnă că elementul curent este 0 (nu este 1)
- `count = 0` resetează counter-ul la 0

De ce resetăm counter-ul? Pentru că când întâlnim un 0, secvența de 1-uri consecutive se întrerupe, deci trebuie să începem să numărăm de la 0 pentru următoarea secvență de 1-uri.

De exemplu, dacă avem `[1, 1, 0, ...]`:
- După primele două 1-uri: `count = 2`
- Când întâlnim 0: `count = 0` (resetăm pentru următoarea secvență)

```java
    return maxCount;
```

Această linie returnează numărul maxim de 1-uri consecutive găsite în array.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `nums = [1, 1, 0, 1, 1, 1]`:

**Inițializare:**
- `count = 0`, `maxCount = 0`

**Iterația 1 (num = 1):**
- `num == 1`? `true`
- `count++` → `count = 1`
- `maxCount = max(0, 1) = 1`

**Iterația 2 (num = 1):**
- `num == 1`? `true`
- `count++` → `count = 2`
- `maxCount = max(1, 2) = 2`

**Iterația 3 (num = 0):**
- `num == 1`? `false`
- `count = 0` (resetăm counter-ul)

**Iterația 4 (num = 1):**
- `num == 1`? `true`
- `count++` → `count = 1`
- `maxCount = max(2, 1) = 2`

**Iterația 5 (num = 1):**
- `num == 1`? `true`
- `count++` → `count = 2`
- `maxCount = max(2, 2) = 2`

**Iterația 6 (num = 1):**
- `num == 1`? `true`
- `count++` → `count = 3`
- `maxCount = max(2, 3) = 3`

**După toate iterațiile:**
- `maxCount = 3`
- Returnăm `3`

**Rezultat:** `3` - numărul maxim de 1-uri consecutive este 3.

## Alt Exemplu - Toate Sunt 1

Să urmărim pentru `nums = [1, 1, 1, 1]`:

**Inițializare:**
- `count = 0`, `maxCount = 0`

**Iterația 1:** `count = 1`, `maxCount = 1`
**Iterația 2:** `count = 2`, `maxCount = 2`
**Iterația 3:** `count = 3`, `maxCount = 3`
**Iterația 4:** `count = 4`, `maxCount = 4`

**Rezultat:** `4` - toate elementele sunt 1, deci numărul maxim este 4.

## Alt Exemplu - Toate Sunt 0

Să urmărim pentru `nums = [0, 0, 0, 0]`:

**Inițializare:**
- `count = 0`, `maxCount = 0`

**Toate iterațiile:** `count` rămâne 0 (nu întâlnim niciun 1)

**Rezultat:** `0` - nu există 1-uri, deci numărul maxim este 0.

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Parcurgem array-ul o singură dată, făcând O(1) operații pentru fiecare element.

2. **O(1) spațiu**: Folosim doar două variabile (counter-uri), nu creăm structuri de date suplimentare.

3. **Simplă și directă**: Nu avem nevoie de structuri complexe, doar de două counter-uri.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de elemente. Parcurgem array-ul o singură dată.

- **Complexitatea spațiului**: O(1) - folosim doar două variabile, nu creăm structuri de date suplimentare.

## Concluzie

Această soluție este simplă și eficientă. Folosim un counter pentru a număra 1-urile consecutive și îl resetăm când întâlnim un 0, ținând minte valoarea maximă văzută.
