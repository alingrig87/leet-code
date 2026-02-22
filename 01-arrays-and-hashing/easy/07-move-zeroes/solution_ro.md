# Soluție Detaliată - Move Zeroes

## Ce Ne Cere Problema?

Problema ne cere să mutăm toate zerourile dintr-un array la sfârșit, păstrând ordinea elementelor care nu sunt zero. De exemplu, dacă avem `[0, 1, 0, 3, 12]`, după mutarea zerourilor ar trebui să avem `[1, 3, 12, 0, 0]`.

## Ce Înseamnă "Păstrând Ordinea"?

"Păstrând ordinea" înseamnă că elementele care nu sunt zero trebuie să rămână în aceeași ordine relativă. De exemplu, dacă în array-ul original avem `[1, 3, 12]` în această ordine, după mutarea zerourilor, acestea trebuie să rămână `[1, 3, 12]`, nu `[3, 1, 12]` sau altă ordine.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Folosim un pointer (writeIndex) care ține minte unde urmează să punem următorul element care nu este zero
2. Parcurgem array-ul cu un alt pointer (readIndex)
3. Pentru fiecare element care nu este zero, îl mutăm la poziția writeIndex
4. Elementele care sunt zero rămân în locul lor sau sunt mutate automat la sfârșit prin procesul de mutare a elementelor non-zero

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public void moveZeroes(int[] nums) {
```

Această linie definește funcția `moveZeroes`. Funcția primește:
- `nums` - array-ul în care mutăm zerourile

Funcția nu returnează nimic (`void`) - modifică array-ul direct.

```java
    if (nums == null || nums.length <= 1) {
        return;
    }
```

Această verificare tratează cazurile speciale. Dacă array-ul este null, gol, sau are doar un element, nu avem ce muta, deci returnăm imediat.

```java
    int writeIndex = 0;
```

Această linie inițializează writeIndex la 0. WriteIndex este pointer-ul care indică poziția unde urmează să punem următorul element care nu este zero.

Gândiți-vă la writeIndex ca la un arhivar care pune doar obiectele valide (non-zero) într-o cutie, începând de la începutul cutiei.

```java
    for (int readIndex = 0; readIndex < nums.length; readIndex++) {
```

Această buclă parcurge array-ul de la început până la sfârșit. ReadIndex este pointer-ul care verifică fiecare element.

```java
        if (nums[readIndex] != 0) {
```

Această condiție verifică dacă elementul curent nu este zero. Dacă elementul nu este zero, vrem să îl mutăm la început (la poziția writeIndex).

```java
            int temp = nums[writeIndex];
            nums[writeIndex] = nums[readIndex];
            nums[readIndex] = temp;
```

Aceste linii fac un "swap" (schimb) între elementul de la poziția writeIndex și elementul de la poziția readIndex. Să explicăm pas cu pas:

- `int temp = nums[writeIndex]` salvează valoarea de la poziția writeIndex într-o variabilă temporară
- `nums[writeIndex] = nums[readIndex]` copiază elementul non-zero la poziția writeIndex
- `nums[readIndex] = temp` pune valoarea veche (care poate fi zero sau alt număr) la poziția readIndex

De ce facem swap? Pentru că vrem să mutăm elementul non-zero la început, iar elementul care era la început (care poate fi zero) îl mutăm la poziția unde era elementul non-zero.

```java
            writeIndex++;
```

Această linie mută writeIndex înainte cu o poziție. După ce am plasat un element non-zero, writeIndex trebuie să indice următoarea poziție disponibilă pentru următorul element non-zero.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `nums = [0, 1, 0, 3, 12]`:

**Inițializare:**
- `writeIndex = 0`
- Array-ul: `[0, 1, 0, 3, 12]`

**Iterația 1 (readIndex = 0, nums[0] = 0):**
- Verificăm: `0 != 0` → `false`
- Este zero, deci îl sărim
- `writeIndex` rămâne 0
- Array-ul: `[0, 1, 0, 3, 12]` (neschimbat)

**Iterația 2 (readIndex = 1, nums[1] = 1):**
- Verificăm: `1 != 0` → `true`
- Nu este zero, deci îl mutăm
- Swap: `temp = nums[0] = 0`, `nums[0] = 1`, `nums[1] = 0`
- `writeIndex++` → `writeIndex = 1`
- Array-ul: `[1, 0, 0, 3, 12]`

**Iterația 3 (readIndex = 2, nums[2] = 0):**
- Verificăm: `0 != 0` → `false`
- Este zero, deci îl sărim
- `writeIndex` rămâne 1
- Array-ul: `[1, 0, 0, 3, 12]` (neschimbat)

**Iterația 4 (readIndex = 3, nums[3] = 3):**
- Verificăm: `3 != 0` → `true`
- Nu este zero, deci îl mutăm
- Swap: `temp = nums[1] = 0`, `nums[1] = 3`, `nums[3] = 0`
- `writeIndex++` → `writeIndex = 2`
- Array-ul: `[1, 3, 0, 0, 12]`

**Iterația 5 (readIndex = 4, nums[4] = 12):**
- Verificăm: `12 != 0` → `true`
- Nu este zero, deci îl mutăm
- Swap: `temp = nums[2] = 0`, `nums[2] = 12`, `nums[4] = 0`
- `writeIndex++` → `writeIndex = 3`
- Array-ul: `[1, 3, 12, 0, 0]`

**După toate iterațiile:**
- Array-ul: `[1, 3, 12, 0, 0]`
- Toate zerourile sunt la sfârșit
- Elementele non-zero sunt la început, în ordinea corectă

**Rezultat:** `[1, 3, 12, 0, 0]` - toate zerourile sunt mutate la sfârșit.

## De Ce Este Această Soluție Eficientă?

1. **Parcurgem array-ul o singură dată**: Nu mutăm zerourile explicit, ci mutăm elementele non-zero la început, ceea ce lasă automat zerourile la sfârșit.

2. **Modificăm array-ul direct**: Nu creăm un array nou, ci reorganizăm elementele în array-ul existent.

3. **Folosim spațiu constant**: Nu folosim spațiu suplimentar (doar două variabile pentru pointeri și o variabilă temporară pentru swap).

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de elemente din array. Parcurgem array-ul o singură dată.

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile, nu creăm structuri de date suplimentare.

## Concluzie

Această soluție este elegantă și eficientă. Folosim doi pointeri pentru a muta elementele non-zero la început, ceea ce lasă automat zerourile la sfârșit, păstrând ordinea elementelor non-zero.
