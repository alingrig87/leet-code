# Soluție Detaliată - Merge Sorted Array

## Ce Ne Cere Problema?

Problema ne cere să combinăm (merge) două array-uri sortate într-unul singur, păstrând ordinea sortată. Primul array (`nums1`) are lungimea `m + n`, unde primele `m` elemente sunt valide, iar restul de `n` poziții sunt goale (sunt 0 sau valori neimportante). Al doilea array (`nums2`) are `n` elemente. Trebuie să combinăm array-urile direct în `nums1`, fără să creăm un array nou.

De exemplu, dacă avem `nums1 = [1, 2, 3, 0, 0, 0]` (m=3), `nums2 = [2, 5, 6]` (n=3), după combinare ar trebui să obținem `nums1 = [1, 2, 2, 3, 5, 6]`.

## De Ce Mergem de la Sfârșit?

Mergem de la sfârșit pentru a utiliza spațiul gol de la sfârșitul lui `nums1`. Dacă am merge de la început, am putea suprascrie elementele valide din `nums1` înainte de a le procesa.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Folosim trei pointeri - `i` pentru ultimul element valid din `nums1`, `j` pentru ultimul element din `nums2`, și `k` pentru ultima poziție din `nums1`
2. Comparăm elementele de la sfârșit și le plasăm în ordine descrescătoare
3. Continuăm până când am procesat toate elementele din ambele array-uri
4. Dacă mai rămân elemente din `nums2`, le copiem în `nums1`

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public void merge(int[] nums1, int m, int[] nums2, int n) {
```

Această linie definește funcția `merge`. Funcția primește:
- `nums1` - primul array sortat cu spațiu suplimentar
- `m` - numărul de elemente valide din `nums1`
- `nums2` - al doilea array sortat
- `n` - numărul de elemente din `nums2`

Funcția nu returnează nimic (`void`) - modifică `nums1` direct.

```java
    if (n == 0) {
        return;
    }
```

Această verificare tratează cazul când `nums2` este gol. Dacă nu avem elemente de adăugat, `nums1` este deja rezultatul final.

```java
    if (m == 0) {
        System.arraycopy(nums2, 0, nums1, 0, n);
        return;
    }
```

Această verificare tratează cazul când `nums1` nu are elemente valide. În acest caz, copiem toate elementele din `nums2` în `nums1`.

```java
    int i = m - 1;
    int j = n - 1;
    int k = m + n - 1;
```

Aceste linii inițializează cei trei pointeri. Să explicăm:

- `i = m - 1` - pointer-ul pentru ultimul element valid din `nums1`
- `j = n - 1` - pointer-ul pentru ultimul element din `nums2`
- `k = m + n - 1` - pointer-ul pentru ultima poziție din `nums1` (unde vom plasa elementele combinate)

```java
    while (i >= 0 && j >= 0) {
```

Această buclă continuă cât timp mai avem elemente în ambele array-uri. Când unul dintre array-uri se termină, bucla se oprește.

```java
        if (nums1[i] > nums2[j]) {
            nums1[k--] = nums1[i--];
        } else {
            nums1[k--] = nums2[j--];
        }
```

Această parte compară elementele și le plasează în ordine descrescătoare. Să explicăm:

- `nums1[i] > nums2[j]` compară ultimul element valid din `nums1` cu ultimul element din `nums2`
- Dacă `nums1[i]` este mai mare:
  - `nums1[k--] = nums1[i--]` plasează `nums1[i]` la poziția `k` și decrementează ambele pointeri
- Dacă `nums2[j]` este mai mare sau egal:
  - `nums1[k--] = nums2[j--]` plasează `nums2[j]` la poziția `k` și decrementează ambele pointeri

De ce funcționează? Pentru că mergem de la sfârșit, deci pozițiile `k` sunt întotdeauna >= `i`, ceea ce înseamnă că nu suprascriem elemente neprocesate din `nums1`.

```java
    while (j >= 0) {
        nums1[k--] = nums2[j--];
    }
```

Această buclă copiază elementele rămase din `nums2`. Dacă `nums2` mai are elemente după ce `nums1` s-a terminat, le copiem în `nums1`.

De ce nu avem nevoie de o buclă pentru `nums1`? Pentru că dacă `nums1` mai are elemente, ele sunt deja în pozițiile corecte (nu le-am mutat).

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `nums1 = [1, 2, 3, 0, 0, 0]` (m=3), `nums2 = [2, 5, 6]` (n=3):

**Inițializare:**
- `i = 2` (ultimul element valid din nums1: 3)
- `j = 2` (ultimul element din nums2: 6)
- `k = 5` (ultima poziție din nums1)

**Iterația 1:**
- `nums1[2] = 3`, `nums2[2] = 6`
- `3 > 6`? `false`
- `nums1[5] = 6`, `k = 4`, `j = 1`
- `nums1 = [1, 2, 3, 0, 0, 6]`

**Iterația 2:**
- `nums1[2] = 3`, `nums2[1] = 5`
- `3 > 5`? `false`
- `nums1[4] = 5`, `k = 3`, `j = 0`
- `nums1 = [1, 2, 3, 0, 5, 6]`

**Iterația 3:**
- `nums1[2] = 3`, `nums2[0] = 2`
- `3 > 2`? `true`
- `nums1[3] = 3`, `k = 2`, `i = 1`
- `nums1 = [1, 2, 3, 3, 5, 6]`

**Iterația 4:**
- `nums1[1] = 2`, `nums2[0] = 2`
- `2 > 2`? `false`
- `nums1[2] = 2`, `k = 1`, `j = -1`
- `nums1 = [1, 2, 2, 3, 5, 6]`

**După buclă:**
- `j = -1`, deci `nums2` s-a terminat
- `i = 1`, dar elementele rămase din `nums1` sunt deja în pozițiile corecte

**Rezultat:** `nums1 = [1, 2, 2, 3, 5, 6]` - array-urile sunt combinate.

## De Ce Este Această Soluție Eficientă?

1. **O(m + n) timp**: Parcurgem fiecare element o singură dată.

2. **O(1) spațiu**: Folosim doar câteva variabile pentru pointeri, nu creăm array-uri noi.

3. **In-place**: Modificăm `nums1` direct, utilizând spațiul gol de la sfârșit.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(m + n) - unde m este numărul de elemente valide din `nums1` și n este numărul de elemente din `nums2`. Parcurgem fiecare element o singură dată.

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile pentru pointeri, nu creăm structuri de date suplimentare.

## Concluzie

Această soluție este elegantă și eficientă. Mergem de la sfârșit pentru a utiliza spațiul gol de la sfârșitul lui `nums1`, comparând elementele și plasându-le în ordine descrescătoare, obținând astfel array-urile combinate într-o singură parcurgere și fără spațiu suplimentar.
