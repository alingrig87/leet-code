# Soluție Detaliată - Search Insert Position

## Ce Ne Cere Problema?

Problema ne cere să găsim poziția unde ar trebui să inserăm un număr (target) într-un array sortat, astfel încât array-ul să rămână sortat. Dacă numărul există deja în array, returnăm poziția lui. Dacă nu există, returnăm poziția unde ar trebui să fie inserat.

De exemplu, dacă avem array-ul `[1, 3, 5, 6]` și target-ul este `5`, returnăm `2` (poziția unde se află 5). Dacă target-ul este `2`, returnăm `1` (poziția unde ar trebui să fie inserat 2 pentru ca array-ul să rămână sortat: `[1, 2, 3, 5, 6]`).

## Ce Este Poziția de Inserare?

Poziția de inserare este prima poziție unde elementul este mai mare sau egal cu target-ul. De exemplu, în `[1, 3, 5, 6]`, dacă target-ul este `2`, prima poziție unde elementul (3) este >= 2 este poziția 1.

## De Ce Folosim Binary Search?

Array-ul este sortat, deci putem folosi binary search pentru a găsi rapid poziția de inserare. În loc să parcurgem array-ul liniar (O(n)), putem folosi binary search (O(log n)).

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Folosim binary search pentru a găsi target-ul sau poziția de inserare
2. Dacă găsim target-ul, returnăm poziția lui
3. Dacă nu găsim target-ul, returnăm poziția unde `left` indică (prima poziție unde elementul este >= target)

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int searchInsert(int[] nums, int target) {
```

Această linie definește funcția `searchInsert`. Funcția primește:
- `nums` - array-ul sortat
- `target` - numărul de inserat sau căutat

Funcția returnează `int` - poziția unde ar trebui să fie inserat target-ul.

```java
    int left = 0;
    int right = nums.length - 1;
```

Aceste linii inițializează pointerii. Să explicăm:

- `left = 0` - începutul zonei de căutare (primul element)
- `right = nums.length - 1` - sfârșitul zonei de căutare (ultimul element)

```java
    while (left <= right) {
```

Această buclă continuă cât timp mai avem o zonă validă de căutare. Folosim `<=` pentru că vrem să verificăm și cazul când `left == right`.

```java
        int mid = left + (right - left) / 2;
```

Această linie calculează poziția de mijloc. Folosim formula `left + (right - left) / 2` pentru a evita overflow.

```java
        if (nums[mid] == target) {
            return mid;
        }
```

Această condiție verifică dacă am găsit target-ul. Dacă elementul de la poziția `mid` este egal cu target-ul, am găsit exact ceea ce căutăm, deci returnăm poziția `mid`.

```java
        else if (nums[mid] < target) {
            left = mid + 1;
        }
```

Această parte tratează cazul când elementul de la `mid` este mai mic decât target-ul. Să explicăm:

- `nums[mid] < target` înseamnă că target-ul este mai mare decât elementul de la mijloc
- Dacă array-ul este sortat și elementul de la mijloc este mai mic, target-ul trebuie să fie în jumătatea dreaptă
- `left = mid + 1` mută începutul zonei la poziția de după `mid`

De exemplu, dacă `nums[mid] = 3` și `target = 5`, știm că 5 este mai mare decât 3, deci trebuie să fie în dreapta. Mutăm `left = mid + 1`.

```java
        else {
            right = mid - 1;
        }
```

Această parte tratează cazul când elementul de la `mid` este mai mare decât target-ul. Să explicăm:

- Dacă elementul de la mijloc este mai mare, target-ul trebuie să fie în jumătatea stângă
- `right = mid - 1` mută sfârșitul zonei la poziția de dinainte de `mid`

De exemplu, dacă `nums[mid] = 7` și `target = 5`, știm că 5 este mai mic decât 7, deci trebuie să fie în stânga. Mutăm `right = mid - 1`.

```java
    return left;
```

Această linie returnează poziția de inserare. Să explicăm de ce:

- Când bucla se termină, `left` indică prima poziție unde elementul este >= target
- Dacă target-ul există, `left` indică poziția lui (pentru că am returnat `mid` când l-am găsit)
- Dacă target-ul nu există, `left` indică poziția unde ar trebui să fie inserat

De exemplu, dacă avem `[1, 3, 5, 6]` și target-ul este `2`:
- După binary search, `left` va indica poziția 1 (unde este 3, care este primul element >= 2)
- Returnăm 1, care este poziția corectă de inserare

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `nums = [1, 3, 5, 6]` și `target = 5`:

**Inițializare:**
- `left = 0`, `right = 3`

**Iterația 1:**
- `mid = 0 + (3 - 0) / 2 = 1`
- `nums[1] = 3`
- `3 == 5`? `false`
- `3 < 5`? `true`, deci `left = 2`
- Zona de căutare: indicii 2-3

**Iterația 2:**
- `mid = 2 + (3 - 2) / 2 = 2`
- `nums[2] = 5`
- `5 == 5`? `true`!
- Returnăm `2`

**Rezultat:** `2` - target-ul 5 se află la poziția 2.

## Alt Exemplu - Target Nu Există

Să urmărim pentru `nums = [1, 3, 5, 6]` și `target = 2`:

**Inițializare:**
- `left = 0`, `right = 3`

**Iterația 1:**
- `mid = 1`
- `nums[1] = 3`
- `3 == 2`? `false`
- `3 < 2`? `false`, deci `right = 0`
- Zona de căutare: indicii 0-0

**Iterația 2:**
- `mid = 0`
- `nums[0] = 1`
- `1 == 2`? `false`
- `1 < 2`? `true`, deci `left = 1`
- Zona de căutare: `left = 1`, `right = 0` → `left > right`, bucla se termină

**După buclă:**
- `left = 1`
- Returnăm `1`

**Rezultat:** `1` - target-ul 2 ar trebui să fie inserat la poziția 1 (între 1 și 3).

## De Ce Este Această Soluție Eficientă?

1. **O(log n) timp**: Folosim binary search, deci avem nevoie de cel mult log₂(n) iterații.

2. **O(1) spațiu**: Folosim doar câteva variabile pentru pointeri.

3. **Găsește și poziția de inserare**: Chiar dacă target-ul nu există, găsim corect poziția unde ar trebui să fie inserat.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(log n) - unde n este numărul de elemente. Folosim binary search.

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile pentru pointeri.

## Concluzie

Această soluție este elegantă și eficientă. Folosim binary search pentru a găsi rapid target-ul sau poziția de inserare, obținând o complexitate de O(log n) în loc de O(n).
