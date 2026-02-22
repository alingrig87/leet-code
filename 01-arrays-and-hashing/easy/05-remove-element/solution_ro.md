# Soluție Detaliată - Remove Element

## Ce Ne Cere Problema?

Problema ne cere să eliminăm toate aparițiile unei anumite valori dintr-un array, dar să facem asta direct în array-ul dat, fără să creăm un array nou. Trebuie să returnăm numărul de elemente care nu sunt egale cu valoarea de eliminat.

De exemplu, dacă avem array-ul `[3, 2, 2, 3]` și valoarea de eliminat este `3`, după eliminare ar trebui să avem `[2, 2]` în primele 2 poziții, și să returnăm 2 (numărul de elemente care nu sunt 3).

## Ce Înseamnă "In-Place"?

"In-place" înseamnă că modificăm array-ul dat direct, fără să creăm un array nou. Este ca și cum ai reorganiza obiectele dintr-o cutie direct în aceeași cutie, fără să le muți într-o altă cutie.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Folosim un pointer (writeIndex) care ține minte unde urmează să punem următorul element valid
2. Parcurgem array-ul cu un alt pointer (readIndex)
3. Pentru fiecare element, verificăm dacă este diferit de valoarea de eliminat
4. Dacă este diferit, îl copiem la poziția writeIndex și mutăm writeIndex înainte
5. Dacă este egal cu valoarea de eliminat, îl sărim (nu îl copiem)

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int removeElement(int[] nums, int val) {
```

Această linie definește funcția `removeElement`. Funcția primește:
- `nums` - array-ul din care eliminăm elementele
- `val` - valoarea pe care o eliminăm

Funcția returnează `int` - numărul de elemente care nu sunt egale cu `val`.

```java
    if (nums == null || nums.length == 0) {
        return 0;
    }
```

Această verificare se asigură că array-ul există și nu este gol. Dacă array-ul este null sau gol, nu există elemente, deci returnăm 0.

```java
    int writeIndex = 0;
```

Această linie inițializează writeIndex la 0. WriteIndex este pointer-ul care indică poziția unde urmează să punem următorul element valid (care nu este egal cu `val`).

Gândiți-vă la writeIndex ca la un arhivar care pune doar documentele valide într-un dosar, începând de la începutul dosarului.

```java
    for (int readIndex = 0; readIndex < nums.length; readIndex++) {
```

Această buclă parcurge array-ul de la început până la sfârșit. Să explicăm:

- `int readIndex = 0` inițializează readIndex la 0 - acesta este pointer-ul care parcurge array-ul
- `readIndex < nums.length` este condiția - bucla continuă cât timp readIndex este în limitele array-ului
- `readIndex++` mărește readIndex cu 1 după fiecare iterație

ReadIndex este ca un inspector care verifică fiecare element din array.

```java
        if (nums[readIndex] != val) {
```

Această condiție verifică dacă elementul curent este diferit de valoarea pe care o eliminăm. Să explicăm:

- `nums[readIndex]` este elementul de la poziția curentă (unde este readIndex)
- `val` este valoarea pe care o eliminăm
- `!=` înseamnă "diferit de" sau "nu este egal cu"

Dacă elementul este diferit de `val`, înseamnă că este un element valid pe care vrem să îl păstrăm.

```java
            nums[writeIndex] = nums[readIndex];
```

Această linie copiază elementul valid la poziția writeIndex. Să explicăm:

- `nums[writeIndex]` este poziția unde vrem să punem elementul valid
- `nums[readIndex]` este elementul valid pe care l-am găsit
- `=` înseamnă "copiază valoarea" - copiem elementul de la poziția readIndex la poziția writeIndex

Această operație "mută" elementul valid în locul potrivit, la începutul array-ului.

```java
            writeIndex++;
```

Această linie mută writeIndex înainte cu o poziție. După ce am plasat un element valid, writeIndex trebuie să indice următoarea poziție disponibilă pentru următorul element valid.

```java
    return writeIndex;
```

Această linie returnează valoarea writeIndex, care reprezintă numărul de elemente valide. Să explicăm de ce:

- WriteIndex indică poziția unde urmează să punem următorul element valid
- Dacă writeIndex este la poziția 2, înseamnă că am plasat elemente valide în pozițiile 0 și 1
- Deci writeIndex este exact numărul de elemente valide

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `nums = [3, 2, 2, 3]` și `val = 3`:

**Inițializare:**
- `writeIndex = 0`
- Array-ul: `[3, 2, 2, 3]`

**Iterația 1 (readIndex = 0, nums[0] = 3):**
- Verificăm: `nums[0] != 3` → `3 != 3` → `false`
- Este egal cu val, deci îl eliminăm - nu îl copiem
- `writeIndex` rămâne 0
- Array-ul: `[3, 2, 2, 3]` (neschimbat)

**Iterația 2 (readIndex = 1, nums[1] = 2):**
- Verificăm: `nums[1] != 3` → `2 != 3` → `true`
- Este diferit de val, deci este valid
- Copiem: `nums[writeIndex] = nums[readIndex]` → `nums[0] = 2`
- `writeIndex++` → `writeIndex = 1`
- Array-ul: `[2, 2, 2, 3]`

**Iterația 3 (readIndex = 2, nums[2] = 2):**
- Verificăm: `nums[2] != 3` → `2 != 3` → `true`
- Este diferit de val, deci este valid
- Copiem: `nums[writeIndex] = nums[readIndex]` → `nums[1] = 2`
- `writeIndex++` → `writeIndex = 2`
- Array-ul: `[2, 2, 2, 3]`

**Iterația 4 (readIndex = 3, nums[3] = 3):**
- Verificăm: `nums[3] != 3` → `3 != 3` → `false`
- Este egal cu val, deci îl eliminăm - nu îl copiem
- `writeIndex` rămâne 2
- Array-ul: `[2, 2, 2, 3]` (neschimbat)

**După toate iterațiile:**
- `writeIndex = 2`
- Primele 2 elemente sunt valide: `[2, 2]`
- Returnăm 2

**Rezultat:** Array-ul are elementele valide în primele 2 poziții: `[2, 2, ...]`, iar funcția returnează 2.

## Alt Exemplu

Să urmărim pentru `nums = [0, 1, 2, 2, 3, 0, 4, 2]` și `val = 2`:

**Inițializare:**
- `writeIndex = 0`
- Array-ul: `[0, 1, 2, 2, 3, 0, 4, 2]`

**Iterația 1 (readIndex = 0, nums[0] = 0):**
- `0 != 2` → `true` → valid
- `nums[0] = 0`, `writeIndex = 1`
- Array: `[0, 1, 2, 2, 3, 0, 4, 2]`

**Iterația 2 (readIndex = 1, nums[1] = 1):**
- `1 != 2` → `true` → valid
- `nums[1] = 1`, `writeIndex = 2`
- Array: `[0, 1, 2, 2, 3, 0, 4, 2]`

**Iterația 3 (readIndex = 2, nums[2] = 2):**
- `2 != 2` → `false` → eliminăm
- `writeIndex` rămâne 2

**Iterația 4 (readIndex = 3, nums[3] = 2):**
- `2 != 2` → `false` → eliminăm
- `writeIndex` rămâne 2

**Iterația 5 (readIndex = 4, nums[4] = 3):**
- `3 != 2` → `true` → valid
- `nums[2] = 3`, `writeIndex = 3`
- Array: `[0, 1, 3, 2, 3, 0, 4, 2]`

**Iterația 6 (readIndex = 5, nums[5] = 0):**
- `0 != 2` → `true` → valid
- `nums[3] = 0`, `writeIndex = 4`
- Array: `[0, 1, 3, 0, 3, 0, 4, 2]`

**Iterația 7 (readIndex = 6, nums[6] = 4):**
- `4 != 2` → `true` → valid
- `nums[4] = 4`, `writeIndex = 5`
- Array: `[0, 1, 3, 0, 4, 0, 4, 2]`

**Iterația 8 (readIndex = 7, nums[7] = 2):**
- `2 != 2` → `false` → eliminăm
- `writeIndex` rămâne 5

**Rezultat:** Primele 5 elemente sunt valide: `[0, 1, 3, 0, 4]`, returnăm 5.

## De Ce Este Această Soluție Eficientă?

1. **Parcurgem array-ul o singură dată**: Nu comparăm fiecare element cu toate celelalte, ci doar verificăm dacă este diferit de val.

2. **Modificăm array-ul direct**: Nu creăm un array nou, ci reorganizăm elementele în array-ul existent.

3. **Folosim spațiu constant**: Nu folosim spațiu suplimentar (doar două variabile pentru pointeri).

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de elemente din array. Parcurgem array-ul o singură dată.

- **Complexitatea spațiului**: O(1) - folosim doar două variabile (writeIndex și readIndex), nu creăm structuri de date suplimentare.

## Concluzie

Această soluție este elegantă și eficientă. Folosim doi pointeri pentru a identifica elementele valide și a le plasa la începutul array-ului, eliminând astfel toate aparițiile valorii date, fără spațiu suplimentar.
