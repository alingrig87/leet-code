# Soluție Detaliată - Merge Sorted Array

## Ce Ne Cere Problema?

Problema ne cere să combinăm (merge) două array-uri sortate într-unul singur, păstrând ordinea sortată. Array-ul rezultat trebuie să fie sortat. Trebuie să facem asta direct în primul array, care are deja spațiu suficient la sfârșit.

De exemplu, dacă avem `nums1 = [1, 2, 3, 0, 0, 0]` (cu 3 elemente valide și 3 poziții goale) și `nums2 = [2, 5, 6]`, după combinare ar trebui să avem `nums1 = [1, 2, 2, 3, 5, 6]`.

## De Ce Este Important Să Începem de la Sfârșit?

Dacă am începe să combinăm de la început (de la stânga), am putea suprascrie elemente din `nums1` înainte să le folosim. De exemplu, dacă am pune un element din `nums2` la poziția 0, am pierde elementul care era deja acolo.

Prin combinarea de la sfârșit (de la dreapta), folosim mai întâi spațiile goale, apoi elementele din `nums1` care au fost deja procesate, deci nu le pierdem.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Avem trei pointeri:
   - Unul pentru ultimul element valid din `nums1`
   - Unul pentru ultimul element din `nums2`
   - Unul pentru ultima poziție din `nums1` (unde punem rezultatul)
2. Comparăm elementele de la sfârșitul ambelor array-uri
3. Punem elementul mai mare la sfârșitul rezultatului
4. Mutăm pointer-ul corespunzător înapoi
5. Continuăm până când am procesat toate elementele

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public void merge(int[] nums1, int m, int[] nums2, int n) {
```

Această linie definește funcția `merge`. Funcția primește:
- `nums1` - primul array sortat, cu spațiu suficient la sfârșit
- `m` - numărul de elemente valide din `nums1`
- `nums2` - al doilea array sortat
- `n` - numărul de elemente din `nums2`

Funcția nu returnează nimic - modifică `nums1` direct.

```java
    if (n == 0) {
        return;
    }
```

Această verificare tratează cazul când `nums2` este gol. Dacă `nums2` este gol, nu avem ce combina, deci `nums1` rămâne neschimbat și returnăm imediat.

```java
    if (m == 0) {
        System.arraycopy(nums2, 0, nums1, 0, n);
        return;
    }
```

Această verificare tratează cazul când `nums1` nu are elemente valide. În acest caz, copiem pur și simplu toate elementele din `nums2` în `nums1`.

`System.arraycopy(nums2, 0, nums1, 0, n)` copiază `n` elemente din `nums2` (începând de la poziția 0) în `nums1` (începând de la poziția 0).

```java
    int i = m - 1;
    int j = n - 1;
    int k = m + n - 1;
```

Aceste linii inițializează cei trei pointeri. Să explicăm fiecare:

- `i = m - 1` - pointer pentru ultimul element valid din `nums1`. De exemplu, dacă `m = 3`, atunci `i = 2` (ultimul element valid este la poziția 2).
- `j = n - 1` - pointer pentru ultimul element din `nums2`. De exemplu, dacă `n = 3`, atunci `j = 2`.
- `k = m + n - 1` - pointer pentru ultima poziție din `nums1` (unde va fi rezultatul final). De exemplu, dacă `m = 3` și `n = 3`, atunci `k = 5` (ultima poziție este 5).

```java
    while (i >= 0 && j >= 0) {
```

Această buclă continuă cât timp ambele array-uri mai au elemente de procesat. Să explicăm:

- `i >= 0` înseamnă că mai avem elemente în `nums1` de procesat
- `j >= 0` înseamnă că mai avem elemente în `nums2` de procesat
- `&&` înseamnă "și" - bucla continuă doar dacă ambele condiții sunt adevărate

```java
        if (nums1[i] > nums2[j]) {
            nums1[k] = nums1[i];
            i--;
        } else {
            nums1[k] = nums2[j];
            j--;
        }
        k--;
```

Această parte compară elementele și le plasează în ordine. Să explicăm pas cu pas:

- `if (nums1[i] > nums2[j])` compară elementul curent din `nums1` cu elementul curent din `nums2`
- Dacă elementul din `nums1` este mai mare:
  - `nums1[k] = nums1[i]` - punem elementul din `nums1` la poziția `k`
  - `i--` - mutăm pointer-ul `i` înapoi cu o poziție (către stânga)
- Dacă elementul din `nums2` este mai mare sau egal:
  - `nums1[k] = nums2[j]` - punem elementul din `nums2` la poziția `k`
  - `j--` - mutăm pointer-ul `j` înapoi cu o poziție
- `k--` - mutăm pointer-ul `k` înapoi cu o poziție (către următoarea poziție disponibilă)

De ce punem elementul mai mare? Pentru că mergem de la sfârșit, deci vrem să punem mai întâi elementele mai mari, apoi cele mai mici.

```java
    while (j >= 0) {
        nums1[k] = nums2[j];
        j--;
        k--;
    }
```

Această buclă copiază elementele rămase din `nums2` (dacă există). Să explicăm:

- Dacă după bucla principală, `j >= 0`, înseamnă că mai avem elemente în `nums2` care nu au fost procesate
- Aceste elemente sunt mai mici decât toate elementele din `nums1` care au rămas
- Le copiem în `nums1` la pozițiile corespunzătoare

De ce nu avem nevoie de o buclă similară pentru `nums1`? Pentru că dacă `nums1` mai are elemente, acestea sunt deja în locul potrivit (la începutul array-ului), deci nu trebuie să le mutăm.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `nums1 = [1, 2, 3, 0, 0, 0]`, `m = 3`, `nums2 = [2, 5, 6]`, `n = 3`:

**Inițializare:**
- `i = 2` (ultimul element valid din nums1: nums1[2] = 3)
- `j = 2` (ultimul element din nums2: nums2[2] = 6)
- `k = 5` (ultima poziție din nums1)
- Array-ul nums1: `[1, 2, 3, 0, 0, 0]`

**Iterația 1:**
- Comparăm: `nums1[2] = 3` și `nums2[2] = 6`
- `3 > 6` → `false`, deci `nums2[2]` este mai mare
- Punem: `nums1[5] = 6`
- `j--` → `j = 1`, `k--` → `k = 4`
- Array-ul: `[1, 2, 3, 0, 0, 6]`

**Iterația 2:**
- Comparăm: `nums1[2] = 3` și `nums2[1] = 5`
- `3 > 5` → `false`, deci `nums2[1]` este mai mare
- Punem: `nums1[4] = 5`
- `j--` → `j = 0`, `k--` → `k = 3`
- Array-ul: `[1, 2, 3, 0, 5, 6]`

**Iterația 3:**
- Comparăm: `nums1[2] = 3` și `nums2[0] = 2`
- `3 > 2` → `true`, deci `nums1[2]` este mai mare
- Punem: `nums1[3] = 3`
- `i--` → `i = 1`, `k--` → `k = 2`
- Array-ul: `[1, 2, 3, 3, 5, 6]`

**Iterația 4:**
- Comparăm: `nums1[1] = 2` și `nums2[0] = 2`
- `2 > 2` → `false`, deci sunt egale (sau nums2[0] este considerat)
- Punem: `nums1[2] = 2`
- `j--` → `j = -1`, `k--` → `k = 1`
- Array-ul: `[1, 2, 2, 3, 5, 6]`

**După bucla principală:**
- `j = -1`, deci nu mai avem elemente în `nums2`
- `i = 1`, deci mai avem elemente în `nums1`, dar acestea sunt deja în locul potrivit
- Nu mai avem nevoie de bucla pentru `j`

**Rezultat:** `nums1 = [1, 2, 2, 3, 5, 6]` - array-urile sunt combinate și sortate.

## Alt Exemplu - Când nums2 Are Elemente Rămase

Să urmărim pentru `nums1 = [4, 5, 6, 0, 0, 0]`, `m = 3`, `nums2 = [1, 2, 3]`, `n = 3`:

**Inițializare:**
- `i = 2`, `j = 2`, `k = 5`
- Array-ul: `[4, 5, 6, 0, 0, 0]`

**Iterația 1:**
- `6 > 3` → `true`
- `nums1[5] = 6`, `i = 1`, `k = 4`
- Array: `[4, 5, 6, 0, 0, 6]`

**Iterația 2:**
- `5 > 3` → `true`
- `nums1[4] = 5`, `i = 0`, `k = 3`
- Array: `[4, 5, 6, 0, 5, 6]`

**Iterația 3:**
- `4 > 3` → `true`
- `nums1[3] = 4`, `i = -1`, `k = 2`
- Array: `[4, 5, 6, 4, 5, 6]`

**După bucla principală:**
- `i = -1`, deci nu mai avem elemente în `nums1`
- `j = 2`, deci mai avem elemente în `nums2`

**Bucla pentru j:**
- `j = 2`: `nums1[2] = 3`, `j = 1`, `k = 1`
- `j = 1`: `nums1[1] = 2`, `j = 0`, `k = 0`
- `j = 0`: `nums1[0] = 1`, `j = -1`, `k = -1`

**Rezultat:** `nums1 = [1, 2, 3, 4, 5, 6]`

## De Ce Este Această Soluție Eficientă?

1. **Parcurgem fiecare array o singură dată**: Nu comparăm fiecare element cu toate celelalte, ci doar comparăm elementele curente și le plasăm în ordine.

2. **Folosim spațiul existent**: Nu creăm un array nou, ci folosim spațiul deja disponibil în `nums1`.

3. **Oprim când un array se termină**: Dacă un array se termină, elementele rămase din celălalt sunt deja în locul potrivit sau le copiem direct.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(m + n) - unde m este numărul de elemente din `nums1` și n este numărul de elemente din `nums2`. Parcurgem fiecare element o singură dată.

- **Complexitatea spațiului**: O(1) - nu folosim spațiu suplimentar (doar câteva variabile pentru pointeri).

## Concluzie

Această soluție este elegantă și eficientă. Combinăm array-urile de la sfârșit, folosind spațiul disponibil în `nums1`, ceea ce ne permite să nu suprascriem elemente înainte să le folosim.
