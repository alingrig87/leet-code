# Soluție Detaliată - Binary Search

## Ce Ne Cere Problema?

Problema ne cere să găsim o anumită valoare (target) într-un array sortat. Trebuie să returnăm poziția (index-ul) valorii dacă există, sau -1 dacă nu există.

De exemplu, dacă avem array-ul `[-1, 0, 3, 5, 9, 12]` și căutăm `9`, trebuie să returnăm `4` (poziția unde se află 9). Dacă căutăm `2`, trebuie să returnăm `-1` (pentru că 2 nu există în array).

## Ce Este Binary Search?

Binary Search (căutare binară) este o tehnică foarte eficientă de căutare într-un array sortat. Funcționează prin împărțirea repetată a array-ului în jumătăți și eliminarea jumătății care cu siguranță nu conține valoarea căutată.

Gândiți-vă la cum căutați un cuvânt într-un dicționar. Nu începeți de la prima pagină și nu verificați fiecare cuvânt. În schimb, deschideți dicționarul la mijloc, verificați dacă cuvântul este înainte sau după, apoi eliminați jumătatea care nu vă interesează și repetați procesul.

## De Ce Funcționează Doar pe Array-uri Sortate?

Binary Search funcționează doar pe array-uri sortate pentru că ne permite să facem comparații și să eliminăm jumătăți din array. Dacă array-ul nu este sortat, nu putem ști dacă valoarea căutată este înainte sau după poziția curentă.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Avem doi pointeri - `left` (stânga) și `right` (dreapta) care definesc zona de căutare
2. Calculăm poziția de mijloc (`mid`) între `left` și `right`
3. Comparăm valoarea de la `mid` cu target-ul
4. Dacă sunt egale, am găsit target-ul - returnăm `mid`
5. Dacă target-ul este mai mare, eliminăm jumătatea stângă (mutăm `left` la `mid + 1`)
6. Dacă target-ul este mai mic, eliminăm jumătatea dreaptă (mutăm `right` la `mid - 1`)
7. Repetăm până găsim target-ul sau până când `left > right` (target-ul nu există)

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int search(int[] nums, int target) {
```

Această linie definește funcția `search`. Funcția primește:
- `nums` - array-ul sortat în care căutăm
- `target` - valoarea pe care o căutăm

Funcția returnează `int` - poziția target-ului dacă există, sau -1 dacă nu există.

```java
    if (nums == null || nums.length == 0) {
        return -1;
    }
```

Această verificare tratează cazul când array-ul este null sau gol. În acest caz, target-ul nu poate exista, deci returnăm -1.

```java
    int left = 0;
    int right = nums.length - 1;
```

Aceste linii inițializează pointerii care definesc zona de căutare. Să explicăm:

- `left = 0` - începutul zonei de căutare (primul element)
- `right = nums.length - 1` - sfârșitul zonei de căutare (ultimul element)

Inițial, căutăm în întregul array.

```java
    while (left <= right) {
```

Această buclă continuă cât timp mai avem o zonă validă de căutare. Să explicăm:

- `left <= right` înseamnă că mai avem cel puțin un element de verificat
- Când `left > right`, înseamnă că am epuizat toate posibilitățile și target-ul nu există

```java
        int mid = left + (right - left) / 2;
```

Această linie calculează poziția de mijloc. Să explicăm de ce folosim această formulă:

- `(right - left) / 2` calculează jumătatea distanței între `left` și `right`
- `left + (right - left) / 2` adună jumătatea la `left`, obținând poziția de mijloc

De exemplu, dacă `left = 2` și `right = 8`:
- `(8 - 2) / 2 = 3`
- `2 + 3 = 5`
- Deci `mid = 5` (mijlocul între 2 și 8)

De ce nu folosim `(left + right) / 2`? Pentru că dacă `left` și `right` sunt foarte mari, suma lor ar putea depăși limita unui număr întreg (overflow). Formula noastră evită această problemă.

```java
        if (nums[mid] == target) {
            return mid;
        }
```

Această condiție verifică dacă am găsit target-ul. Dacă valoarea de la poziția `mid` este egală cu target-ul, am găsit ceea ce căutăm, deci returnăm poziția `mid`.

```java
        else if (nums[mid] < target) {
            left = mid + 1;
        }
```

Această parte tratează cazul când valoarea de la `mid` este mai mică decât target-ul. Să explicăm:

- `nums[mid] < target` înseamnă că valoarea de la mijloc este mai mică decât target-ul
- Dacă array-ul este sortat și valoarea de la mijloc este mai mică, înseamnă că target-ul trebuie să fie în jumătatea dreaptă (după `mid`)
- `left = mid + 1` mută începutul zonei de căutare la poziția de după `mid`, eliminând astfel jumătatea stângă

De exemplu, dacă `nums[mid] = 5` și `target = 9`, știm că 9 este mai mare decât 5, deci trebuie să fie în dreapta. Eliminăm toate elementele de la stânga până la `mid` inclusiv.

```java
        else {
            right = mid - 1;
        }
```

Această parte tratează cazul când valoarea de la `mid` este mai mare decât target-ul. Să explicăm:

- Dacă valoarea de la mijloc este mai mare decât target-ul, înseamnă că target-ul trebuie să fie în jumătatea stângă (înainte de `mid`)
- `right = mid - 1` mută sfârșitul zonei de căutare la poziția de dinainte de `mid`, eliminând astfel jumătatea dreaptă

De exemplu, dacă `nums[mid] = 9` și `target = 5`, știm că 5 este mai mic decât 9, deci trebuie să fie în stânga. Eliminăm toate elementele de la `mid` inclusiv până la dreapta.

```java
    return -1;
```

Această linie se execută doar dacă am terminat bucla fără să găsim target-ul. În acest caz, target-ul nu există în array, deci returnăm -1.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `nums = [-1, 0, 3, 5, 9, 12]` și `target = 9`:

**Inițializare:**
- `left = 0`, `right = 5`
- Array-ul: `[-1, 0, 3, 5, 9, 12]`

**Iterația 1:**
- `mid = 0 + (5 - 0) / 2 = 2`
- `nums[2] = 3`
- `3 == 9` → `false`
- `3 < 9` → `true`, deci `left = 3`
- Zona de căutare: `[5, 9, 12]` (indicele 3-5)

**Iterația 2:**
- `mid = 3 + (5 - 3) / 2 = 4`
- `nums[4] = 9`
- `9 == 9` → `true`
- Returnăm `4`

**Rezultat:** `4` - am găsit 9 la poziția 4.

## Alt Exemplu - Target Nu Există

Să urmărim pentru `nums = [-1, 0, 3, 5, 9, 12]` și `target = 2`:

**Inițializare:**
- `left = 0`, `right = 5`

**Iterația 1:**
- `mid = 2`, `nums[2] = 3`
- `3 == 2` → `false`
- `3 < 2` → `false`, deci `right = 1`
- Zona de căutare: `[-1, 0]` (indicele 0-1)

**Iterația 2:**
- `mid = 0 + (1 - 0) / 2 = 0`
- `nums[0] = -1`
- `-1 == 2` → `false`
- `-1 < 2` → `true`, deci `left = 1`
- Zona de căutare: `[0]` (indicele 1)

**Iterația 3:**
- `mid = 1 + (1 - 1) / 2 = 1`
- `nums[1] = 0`
- `0 == 2` → `false`
- `0 < 2` → `true`, deci `left = 2`
- Zona de căutare: `left = 2`, `right = 1` → `left > right`

**După buclă:**
- `left > right`, deci target-ul nu există
- Returnăm `-1`

**Rezultat:** `-1` - 2 nu există în array.

## De Ce Este Această Soluție Eficientă?

1. **Eliminăm jumătate din array la fiecare iterație**: În loc să verificăm fiecare element, eliminăm jumătate din posibilități la fiecare pas.

2. **Complexitate logaritmică**: Dacă array-ul are n elemente, avem nevoie de cel mult log₂(n) iterații. De exemplu, pentru 1000 de elemente, avem nevoie de cel mult 10 iterații (2¹⁰ = 1024).

3. **Oprim imediat când găsim target-ul**: Dacă găsim target-ul, nu mai continuăm să căutăm.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(log n) - unde n este numărul de elemente. La fiecare iterație, eliminăm jumătate din array, deci avem nevoie de log₂(n) iterații.

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile pentru pointeri, nu creăm structuri de date suplimentare.

## Concluzie

Binary Search este o tehnică foarte eficientă pentru căutarea într-un array sortat. Prin eliminarea repetată a jumătăților din array, găsim rapid target-ul sau confirmăm că nu există, folosind doar O(log n) iterații.
