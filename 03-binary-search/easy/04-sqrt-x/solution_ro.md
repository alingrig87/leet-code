# Soluție Detaliată - Sqrt(x)

## Ce Ne Cere Problema?

Problema ne cere să găsim rădăcina pătrată a unui număr întreg nenegativ, rotunjită în jos (floor). De exemplu:
- `sqrt(4) = 2` (2 * 2 = 4)
- `sqrt(8) = 2` (2 * 2 = 4, 3 * 3 = 9, deci rădăcina pătrată rotunjită în jos este 2)
- `sqrt(9) = 3` (3 * 3 = 9)

## Ce Este Rădăcina Pătrată?

Rădăcina pătrată a unui număr x este numărul care, înmulțit cu el însuși, dă x. De exemplu, rădăcina pătrată a lui 9 este 3, pentru că 3 * 3 = 9.

## De Ce Folosim Binary Search?

În loc să încercăm toate numerele de la 1 la x (ceea ce ar fi O(x)), putem folosi binary search pentru a găsi rapid rădăcina pătrată (O(log x)).

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Folosim binary search în intervalul [0, x]
2. Calculăm pătratul numărului de mijloc
3. Dacă pătratul este egal cu x, am găsit rădăcina pătrată exactă
4. Dacă pătratul este mai mic decât x, încercăm numere mai mari
5. Dacă pătratul este mai mare decât x, încercăm numere mai mici
6. Ținem minte cel mai mare număr al cărui pătrat este <= x

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int mySqrt(int x) {
```

Această linie definește funcția `mySqrt`. Funcția primește:
- `x` - numărul pentru care căutăm rădăcina pătrată

Funcția returnează `int` - rădăcina pătrată rotunjită în jos.

```java
    if (x == 0 || x == 1) {
        return x;
    }
```

Această verificare tratează cazurile speciale. Să explicăm:

- `x == 0` - rădăcina pătrată a lui 0 este 0
- `x == 1` - rădăcina pătrată a lui 1 este 1

Returnăm direct x pentru aceste cazuri.

```java
    int left = 0;
    int right = x;
    int result = 0;
```

Aceste linii inițializează variabilele. Să explicăm:

- `left = 0` - începutul intervalului de căutare
- `right = x` - sfârșitul intervalului de căutare (rădăcina pătrată a lui x nu poate fi mai mare decât x)
- `result = 0` - va stoca cel mai mare număr al cărui pătrat este <= x

```java
    while (left <= right) {
```

Această buclă continuă cât timp mai avem un interval valid de căutare.

```java
        int mid = left + (right - left) / 2;
```

Această linie calculează numărul de mijloc. Folosim formula `left + (right - left) / 2` pentru a evita overflow.

```java
        long square = (long) mid * mid;
```

Această linie calculează pătratul numărului de mijloc. Să explicăm:

- `(long) mid` convertește `mid` la tipul `long` pentru a evita overflow
- `mid * mid` calculează pătratul
- `long square = ...` stochează rezultatul ca `long` pentru a evita overflow

De ce folosim `long`? Pentru că dacă `mid` este mare (de exemplu, aproape de x), `mid * mid` ar putea depăși limita unui `int`, ceea ce ar cauza overflow. Folosind `long`, evităm această problemă.

```java
        if (square == x) {
            return mid;
        }
```

Această condiție verifică dacă am găsit rădăcina pătrată exactă. Dacă pătratul lui `mid` este egal cu x, atunci `mid` este rădăcina pătrată exactă, deci returnăm `mid`.

```java
        else if (square < x) {
            result = mid;
            left = mid + 1;
        }
```

Această parte tratează cazul când pătratul este mai mic decât x. Să explicăm:

- `square < x` înseamnă că `mid * mid < x`
- În acest caz, `mid` este un candidat valid (pătratul său este mai mic decât x)
- `result = mid` actualizează rezultatul cu acest candidat valid
- `left = mid + 1` mută începutul intervalului la `mid + 1` pentru a încerca numere mai mari

De exemplu, dacă `mid = 2` și `x = 8`, atunci `2 * 2 = 4 < 8`, deci 2 este un candidat valid. Actualizăm `result = 2` și încercăm numere mai mari.

```java
        else {
            right = mid - 1;
        }
```

Această parte tratează cazul când pătratul este mai mare decât x. Să explicăm:

- Dacă `square > x`, înseamnă că `mid` este prea mare
- `right = mid - 1` mută sfârșitul intervalului la `mid - 1` pentru a încerca numere mai mici

De exemplu, dacă `mid = 5` și `x = 8`, atunci `5 * 5 = 25 > 8`, deci 5 este prea mare. Mutăm `right = 4` pentru a încerca numere mai mici.

```java
    return result;
```

Această linie returnează rezultatul. Când bucla se termină, `result` conține cel mai mare număr al cărui pătrat este <= x, care este exact rădăcina pătrată rotunjită în jos.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `x = 8`:

**Inițializare:**
- `left = 0`, `right = 8`, `result = 0`

**Iterația 1:**
- `mid = 0 + (8 - 0) / 2 = 4`
- `square = 4 * 4 = 16`
- `16 == 8`? `false`
- `16 < 8`? `false`, deci `16 > 8`
- `right = 4 - 1 = 3`
- Interval: 0-3

**Iterația 2:**
- `mid = 0 + (3 - 0) / 2 = 1`
- `square = 1 * 1 = 1`
- `1 == 8`? `false`
- `1 < 8`? `true`
- `result = 1`, `left = 2`
- Interval: 2-3

**Iterația 3:**
- `mid = 2 + (3 - 2) / 2 = 2`
- `square = 2 * 2 = 4`
- `4 == 8`? `false`
- `4 < 8`? `true`
- `result = 2`, `left = 3`
- Interval: 3-3

**Iterația 4:**
- `mid = 3 + (3 - 3) / 2 = 3`
- `square = 3 * 3 = 9`
- `9 == 8`? `false`
- `9 < 8`? `false`, deci `9 > 8`
- `right = 3 - 1 = 2`
- Interval: `left = 3`, `right = 2` → `left > right`, bucla se termină

**După buclă:**
- `result = 2`
- Returnăm `2`

**Rezultat:** `2` - rădăcina pătrată a lui 8 rotunjită în jos este 2 (2 * 2 = 4 <= 8, 3 * 3 = 9 > 8).

## Alt Exemplu - Pătrat Perfect

Să urmărim pentru `x = 16`:

**Inițializare:**
- `left = 0`, `right = 16`, `result = 0`

**Iterația 1:**
- `mid = 8`
- `square = 64`
- `64 > 16`, deci `right = 7`
- Interval: 0-7

**Iterația 2:**
- `mid = 3`
- `square = 9`
- `9 < 16`, deci `result = 3`, `left = 4`
- Interval: 4-7

**Iterația 3:**
- `mid = 5`
- `square = 25`
- `25 > 16`, deci `right = 4`
- Interval: 4-4

**Iterația 4:**
- `mid = 4`
- `square = 16`
- `16 == 16`? `true`!
- Returnăm `4`

**Rezultat:** `4` - rădăcina pătrată exactă a lui 16 este 4.

## De Ce Este Această Soluție Eficientă?

1. **O(log x) timp**: Folosim binary search, deci avem nevoie de cel mult log₂(x) iterații.

2. **O(1) spațiu**: Folosim doar câteva variabile.

3. **Evită overflow**: Folosim `long` pentru calculele de pătrat pentru a evita overflow.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(log x) - unde x este numărul pentru care căutăm rădăcina pătrată. Folosim binary search.

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile.

## Concluzie

Această soluție este elegantă și eficientă. Folosim binary search pentru a găsi rapid rădăcina pătrată, evitând overflow prin folosirea tipului `long` pentru calcule.
