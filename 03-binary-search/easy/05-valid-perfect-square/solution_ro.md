# Soluție Detaliată - Valid Perfect Square

## Ce Ne Cere Problema?

Problema ne cere să verificăm dacă un număr întreg pozitiv este un pătrat perfect. Un pătrat perfect este un număr care poate fi exprimat ca pătratul unui număr întreg. De exemplu:
- `16` este pătrat perfect (4 * 4 = 16)
- `14` nu este pătrat perfect (nu există un număr întreg al cărui pătrat să fie 14)

## Ce Este un Pătrat Perfect?

Un pătrat perfect este un număr care este rezultatul înmulțirii unui număr întreg cu el însuși. De exemplu, 4 * 4 = 16, deci 16 este pătrat perfect.

## De Ce Folosim Binary Search?

În loc să încercăm toate numerele de la 1 la num (ceea ce ar fi O(num)), putem folosi binary search pentru a găsi rapid rădăcina pătrată (O(log num)).

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Folosim binary search în intervalul [1, num]
2. Calculăm pătratul numărului de mijloc
3. Dacă pătratul este egal cu num, numărul este pătrat perfect
4. Dacă pătratul este mai mic decât num, încercăm numere mai mari
5. Dacă pătratul este mai mare decât num, încercăm numere mai mici
6. Dacă nu găsim un pătrat egal cu num, numărul nu este pătrat perfect

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public boolean isPerfectSquare(int num) {
```

Această linie definește funcția `isPerfectSquare`. Funcția primește:
- `num` - numărul de verificat

Funcția returnează `boolean` - `true` dacă numărul este pătrat perfect, `false` dacă nu este.

```java
    if (num == 1) {
        return true;
    }
```

Această verificare tratează cazul special. 1 este pătrat perfect (1 * 1 = 1), deci returnăm `true` direct.

```java
    long left = 1;
    long right = num;
```

Aceste linii inițializează intervalul de căutare. Folosim `long` pentru a evita overflow când calculăm pătratul.

```java
    while (left <= right) {
```

Această buclă continuă cât timp mai avem un interval valid de căutare.

```java
        long mid = left + (right - left) / 2;
```

Această linie calculează numărul de mijloc. Folosim formula `left + (right - left) / 2` pentru a evita overflow.

```java
        long square = mid * mid;
```

Această linie calculează pătratul numărului de mijloc. Folosim `long` pentru a evita overflow.

```java
        if (square == num) {
            return true;
        }
```

Această condiție verifică dacă am găsit pătratul perfect. Dacă pătratul lui `mid` este egal cu `num`, atunci `mid` este rădăcina pătrată a lui `num`, deci `num` este pătrat perfect, returnăm `true`.

```java
        else if (square < num) {
            left = mid + 1;
        }
```

Această parte tratează cazul când pătratul este mai mic decât num. Să explicăm:

- `square < num` înseamnă că `mid * mid < num`
- În acest caz, `mid` este prea mic, deci trebuie să încercăm numere mai mari
- `left = mid + 1` mută începutul intervalului la `mid + 1`

```java
        else {
            right = mid - 1;
        }
```

Această parte tratează cazul când pătratul este mai mare decât num. Să explicăm:

- Dacă `square > num`, înseamnă că `mid` este prea mare
- `right = mid - 1` mută sfârșitul intervalului la `mid - 1`

```java
    return false;
```

Această linie se execută doar dacă nu am găsit un pătrat egal cu num. În acest caz, numărul nu este pătrat perfect, deci returnăm `false`.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `num = 16`:

**Inițializare:**
- `left = 1`, `right = 16`

**Iterația 1:**
- `mid = 1 + (16 - 1) / 2 = 8`
- `square = 8 * 8 = 64`
- `64 == 16`? `false`
- `64 > 16`? `true`, deci `right = 7`
- Interval: 1-7

**Iterația 2:**
- `mid = 1 + (7 - 1) / 2 = 4`
- `square = 4 * 4 = 16`
- `16 == 16`? `true`!
- Returnăm `true`

**Rezultat:** `true` - 16 este pătrat perfect.

## Alt Exemplu - Nu Este Pătrat Perfect

Să urmărim pentru `num = 14`:

**Inițializare:**
- `left = 1`, `right = 14`

**Iterația 1:**
- `mid = 7`
- `square = 49`
- `49 > 14`, deci `right = 6`
- Interval: 1-6

**Iterația 2:**
- `mid = 3`
- `square = 9`
- `9 < 14`, deci `left = 4`
- Interval: 4-6

**Iterația 3:**
- `mid = 5`
- `square = 25`
- `25 > 14`, deci `right = 4`
- Interval: 4-4

**Iterația 4:**
- `mid = 4`
- `square = 16`
- `16 > 14`, deci `right = 3`
- Interval: `left = 4`, `right = 3` → `left > right`, bucla se termină

**După buclă:**
- Nu am găsit un pătrat egal cu 14
- Returnăm `false`

**Rezultat:** `false` - 14 nu este pătrat perfect.

## De Ce Este Această Soluție Eficientă?

1. **O(log num) timp**: Folosim binary search, deci avem nevoie de cel mult log₂(num) iterații.

2. **O(1) spațiu**: Folosim doar câteva variabile.

3. **Evită overflow**: Folosim `long` pentru calculele de pătrat pentru a evita overflow.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(log num) - unde num este numărul de verificat. Folosim binary search.

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile.

## Concluzie

Această soluție este elegantă și eficientă. Folosim binary search pentru a găsi rapid rădăcina pătrată, verificând dacă pătratul este egal cu numărul dat, evitând overflow prin folosirea tipului `long` pentru calcule.
