# Soluție Detaliată - Can Place Flowers

## Ce Ne Cere Problema?

Problema ne cere să verificăm dacă putem planta n flori într-un pat de flori fără ca două flori să fie adiacente (una lângă alta). Patul de flori este reprezentat ca un array unde 0 înseamnă loc gol și 1 înseamnă loc ocupat.

De exemplu, dacă avem `flowerbed = [1, 0, 0, 0, 1]` și `n = 1`:
- Putem planta o floare la poziția 2 (între cele două flori existente)
- Rezultat: `true`

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Parcurgem patul de flori
2. Pentru fiecare loc gol, verificăm dacă vecinii (stânga și dreapta) sunt goi
3. Dacă da, plantăm floarea acolo și incrementăm contorul
4. Dacă am plantat n flori, returnăm `true`
5. Dacă nu am plantat n flori după ce am parcurs tot patul, returnăm `false`

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public boolean canPlaceFlowers(int[] flowerbed, int n) {
```

Această linie definește funcția `canPlaceFlowers`. Funcția primește:
- `flowerbed` - array-ul reprezentând patul de flori (0 = gol, 1 = ocupat)
- `n` - numărul de flori de plantat

Funcția returnează `boolean` - `true` dacă putem planta n flori, `false` dacă nu putem.

```java
    if (n == 0) {
        return true;
    }
```

Această verificare tratează cazul când nu trebuie să plantăm flori. Dacă `n = 0`, nu avem flori de plantat, deci returnăm `true`.

```java
    if (flowerbed == null || flowerbed.length == 0) {
        return false;
    }
```

Această verificare tratează cazul când patul este null sau gol. Dacă patul este gol și `n > 0`, nu putem planta flori, deci returnăm `false`.

```java
    int count = 0;
```

Această linie inițializează contorul pentru numărul de flori plantate.

```java
    for (int i = 0; i < flowerbed.length; i++) {
```

Această buclă parcurge fiecare poziție din patul de flori. `i` este indicele poziției curente.

```java
        if (flowerbed[i] == 0) {
```

Această condiție verifică dacă locul curent este gol. Dacă locul este deja ocupat (1), nu putem planta acolo, deci trecem la următoarea poziție.

```java
            boolean leftEmpty = (i == 0) || (flowerbed[i - 1] == 0);
            boolean rightEmpty = (i == flowerbed.length - 1) || (flowerbed[i + 1] == 0);
```

Aceste linii verifică dacă vecinii sunt goi. Să explicăm:

- `leftEmpty` verifică dacă vecinul din stânga este gol:
  - `i == 0` verifică dacă suntem la începutul array-ului (nu avem vecin stâng)
  - `flowerbed[i - 1] == 0` verifică dacă vecinul din stânga este gol
- `rightEmpty` verifică dacă vecinul din dreapta este gol:
  - `i == flowerbed.length - 1` verifică dacă suntem la sfârșitul array-ului (nu avem vecin drept)
  - `flowerbed[i + 1] == 0` verifică dacă vecinul din dreapta este gol

De exemplu, dacă `i = 2` și `flowerbed = [1, 0, 0, 0, 1]`:
- `leftEmpty = (2 == 0) || (flowerbed[1] == 0) = false || true = true`
- `rightEmpty = (2 == 4) || (flowerbed[3] == 0) = false || true = true`

```java
            if (leftEmpty && rightEmpty) {
                flowerbed[i] = 1;
                count++;
                if (count >= n) {
                    return true;
                }
            }
```

Această condiție plantează floarea dacă vecinii sunt goi. Să explicăm:

- `leftEmpty && rightEmpty` verifică dacă ambii vecini sunt goi
- Dacă da, `flowerbed[i] = 1` plantează floarea la poziția curentă
- `count++` incrementează contorul de flori plantate
- `if (count >= n)` verifică dacă am plantat suficiente flori
- Dacă da, `return true` (am reușit să plantăm n flori)

De exemplu, dacă `flowerbed[i] = 0`, `leftEmpty = true`, `rightEmpty = true` și `n = 1`:
- Plantăm floarea: `flowerbed[i] = 1`
- `count = 1`
- `1 >= 1`? `true`, returnăm `true`

```java
    return count >= n;
```

Această linie returnează rezultatul după ce am parcurs tot patul. Dacă am plantat cel puțin n flori, returnăm `true`, altfel `false`.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `flowerbed = [1, 0, 0, 0, 1]` și `n = 1`:

**Inițializare:**
- `count = 0`

**Iterația 1 (i = 0):**
- `flowerbed[0] = 1` (ocupat), continuăm

**Iterația 2 (i = 1):**
- `flowerbed[1] = 0` (gol)
- `leftEmpty = (1 == 0) || (flowerbed[0] == 0) = false || false = false`
- `rightEmpty = (1 == 4) || (flowerbed[2] == 0) = false || true = true`
- `leftEmpty && rightEmpty`? `false`, nu plantăm

**Iterația 3 (i = 2):**
- `flowerbed[2] = 0` (gol)
- `leftEmpty = (2 == 0) || (flowerbed[1] == 0) = false || true = true`
- `rightEmpty = (2 == 4) || (flowerbed[3] == 0) = false || true = true`
- `leftEmpty && rightEmpty`? `true`!
- `flowerbed[2] = 1`, `count = 1`
- `1 >= 1`? `true`, returnăm `true`

**Rezultat:** `true` - putem planta o floare la poziția 2.

## Alt Exemplu - Nu Putem Planta

Să urmărim pentru `flowerbed = [1, 0, 0, 0, 1]` și `n = 2`:

**Iterația 1:** `flowerbed[0] = 1`, continuăm
**Iterația 2:** `leftEmpty = false`, nu plantăm
**Iterația 3:** Plantăm la poziția 2, `count = 1`
**Iterația 4:** `flowerbed[3] = 0`, dar `leftEmpty = (flowerbed[2] == 0)`? `false` (am plantat la 2), nu plantăm
**Iterația 5:** `flowerbed[4] = 1`, continuăm

**După toate iterațiile:**
- `count = 1 < 2`
- Returnăm `false`

**Rezultat:** `false` - nu putem planta 2 flori.

## De Ce Este Această Soluția Eficientă?

1. **O(n) timp**: Parcurgem array-ul o singură dată, făcând O(1) operații pentru fiecare poziție.

2. **O(1) spațiu**: Folosim doar câteva variabile, nu creăm structuri de date suplimentare.

3. **Early termination**: Dacă am plantat n flori, returnăm imediat `true`, fără să continuăm parcurgerea.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este lungimea array-ului. Parcurgem array-ul o singură dată.

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile, nu creăm structuri de date suplimentare.

## Concluzie

Această soluție este simplă și eficientă. Parcurgem patul de flori și plantăm flori în locurile goale care au vecinii goi, incrementând contorul și returnând `true` imediat când am plantat n flori, verificând astfel dacă putem planta n flori fără ca două flori să fie adiacente.
