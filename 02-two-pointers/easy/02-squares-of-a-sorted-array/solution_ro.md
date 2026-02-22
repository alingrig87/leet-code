# Soluție Detaliată - Squares of a Sorted Array

## Ce Ne Cere Problema?

Problema ne cere să ridicăm la pătrat fiecare număr dintr-un array sortat și să returnăm un array sortat cu aceste pătrate. De exemplu, dacă avem `[-4, -1, 0, 3, 10]`, după ridicarea la pătrat obținem `[16, 1, 0, 9, 100]`, iar sortat devine `[0, 1, 9, 16, 100]`.

## De Ce Este Important Că Array-ul Este Sortat?

Când un array este sortat, numerele negative sunt la stânga și numerele pozitive sunt la dreapta. Când ridicăm la pătrat:
- Numerele negative devin pozitive (de exemplu, -4 devine 16)
- Numerele pozitive rămân pozitive, dar cresc (de exemplu, 3 devine 9)

Cel mai mare pătrat poate fi fie de la cel mai negativ număr (la stânga), fie de la cel mai pozitiv număr (la dreapta). De exemplu, -4² = 16 și 10² = 100, deci 100 este cel mai mare.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Folosim doi pointeri - unul de la început (stânga) și unul de la sfârșit (dreapta)
2. Calculăm pătratele numerelor de la ambele capete
3. Comparăm pătratele și punem pătratul mai mare la sfârșitul rezultatului
4. Mutăm pointer-ul corespunzător și continuăm
5. Repetăm până când am procesat toate numerele

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int[] sortedSquares(int[] nums) {
```

Această linie definește funcția `sortedSquares`. Funcția primește:
- `nums` - array-ul sortat de numere (poate conține negative)

Funcția returnează `int[]` - un array cu pătratele numerelor, sortat.

```java
    if (nums == null || nums.length == 0) {
        return new int[0];
    }
```

Această verificare tratează cazul când array-ul este null sau gol. În acest caz, returnăm un array gol.

```java
    int n = nums.length;
    int[] result = new int[n];
```

Aceste linii creează array-ul rezultat. Să explicăm:

- `n = nums.length` salvează lungimea array-ului într-o variabilă pentru ușurință
- `int[] result = new int[n]` creează un array nou cu `n` elemente, toate inițializate cu 0

Acest array va conține pătratele sortate.

```java
    int left = 0;
    int right = n - 1;
    int resultIndex = n - 1;
```

Aceste linii inițializează cei trei pointeri. Să explicăm:

- `left = 0` - pointer pentru începutul array-ului (stânga)
- `right = n - 1` - pointer pentru sfârșitul array-ului (dreapta)
- `resultIndex = n - 1` - pointer pentru sfârșitul array-ului rezultat

De ce începem de la sfârșitul rezultatului? Pentru că vrem să punem mai întâi pătratele mai mari (care sunt la capetele array-ului original), apoi cele mai mici (care sunt în mijloc).

```java
    while (left <= right) {
```

Această buclă continuă cât timp pointerii nu s-au întâlnit. Când `left > right`, am procesat toate numerele.

```java
        int leftSquare = nums[left] * nums[left];
        int rightSquare = nums[right] * nums[right];
```

Aceste linii calculează pătratele numerelor de la ambele capete. Să explicăm:

- `nums[left] * nums[left]` calculează pătratul numărului de la poziția `left`
- `nums[right] * nums[right]` calculează pătratul numărului de la poziția `right`

De exemplu, dacă `nums[left] = -4`, atunci `leftSquare = (-4) * (-4) = 16`.

```java
        if (leftSquare > rightSquare) {
            result[resultIndex] = leftSquare;
            left++;
        } else {
            result[resultIndex] = rightSquare;
            right--;
        }
```

Această parte compară pătratele și plasează pătratul mai mare la sfârșitul rezultatului. Să explicăm:

- `if (leftSquare > rightSquare)` verifică dacă pătratul de la stânga este mai mare
- Dacă da, punem `leftSquare` la poziția `resultIndex` și mutăm pointer-ul `left` la dreapta (`left++`)
- Dacă nu (pătratul de la dreapta este mai mare sau egal), punem `rightSquare` la poziția `resultIndex` și mutăm pointer-ul `right` la stânga (`right--`)

De ce punem pătratul mai mare? Pentru că mergem de la sfârșitul rezultatului, deci vrem să punem mai întâi pătratele mai mari.

```java
        resultIndex--;
```

Această linie mută `resultIndex` cu o poziție la stânga (către început). După ce am plasat un pătrat, trecem la următoarea poziție disponibilă.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `nums = [-4, -1, 0, 3, 10]`:

**Inițializare:**
- `left = 0`, `right = 4`, `resultIndex = 4`
- `result = [0, 0, 0, 0, 0]`

**Iterația 1:**
- `leftSquare = (-4)² = 16`
- `rightSquare = 10² = 100`
- `16 > 100` → `false`, deci `rightSquare` este mai mare
- `result[4] = 100`
- `right = 3`, `resultIndex = 3`
- `result = [0, 0, 0, 0, 100]`

**Iterația 2:**
- `leftSquare = (-4)² = 16`
- `rightSquare = 3² = 9`
- `16 > 9` → `true`, deci `leftSquare` este mai mare
- `result[3] = 16`
- `left = 1`, `resultIndex = 2`
- `result = [0, 0, 0, 16, 100]`

**Iterația 3:**
- `leftSquare = (-1)² = 1`
- `rightSquare = 3² = 9`
- `1 > 9` → `false`, deci `rightSquare` este mai mare
- `result[2] = 9`
- `right = 2`, `resultIndex = 1`
- `result = [0, 0, 9, 16, 100]`

**Iterația 4:**
- `leftSquare = (-1)² = 1`
- `rightSquare = 0² = 0`
- `1 > 0` → `true`, deci `leftSquare` este mai mare
- `result[1] = 1`
- `left = 2`, `resultIndex = 0`
- `result = [0, 1, 9, 16, 100]`

**Iterația 5:**
- `leftSquare = 0² = 0`
- `rightSquare = 0² = 0`
- `0 > 0` → `false`, deci sunt egale (sau rightSquare este considerat)
- `result[0] = 0`
- `right = 1`, `resultIndex = -1`
- `result = [0, 1, 9, 16, 100]`

**După buclă:**
- `left = 2`, `right = 1`, deci `left > right` - bucla se termină

**Rezultat:** `[0, 1, 9, 16, 100]` - pătratele sunt sortate.

## De Ce Este Această Soluție Eficientă?

1. **Parcurgem array-ul o singură dată**: Nu sortăm după ce calculăm pătratele, ci le plasăm direct în ordinea corectă.

2. **Folosim proprietatea array-ului sortat**: Știm că cele mai mari pătrate sunt la capete, deci le procesăm primul.

3. **O(n) timp**: Parcurgem array-ul o singură dată, comparând și plasând elementele.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de elemente. Parcurgem array-ul o singură dată.

- **Complexitatea spațiului**: O(n) - creăm un array nou pentru rezultat.

## Concluzie

Această soluție este elegantă și eficientă. Folosim doi pointeri pentru a identifica pătratele mai mari (care sunt la capetele array-ului sortat) și le plasăm la sfârșitul rezultatului, obținând astfel un array sortat fără a sorta explicit.
