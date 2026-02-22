# Soluție Detaliată - First Bad Version

## Ce Ne Cere Problema?

Problema ne cere să găsim prima versiune "rea" dintr-o serie de versiuni, folosind cât mai puține apeluri la o funcție care verifică dacă o versiune este "rea". Versiunile sunt numerotate de la 1 la n, și toate versiunile de la o anumită versiune înainte sunt "rele", iar toate versiunile înainte sunt "bune".

De exemplu, dacă avem versiunile 1, 2, 3, 4, 5 și versiunea 4 este prima versiune rea, atunci:
- Versiunile 1, 2, 3 sunt bune
- Versiunile 4, 5 sunt rele

Trebuie să găsim versiunea 4 folosind cât mai puține verificări.

## Ce Este Binary Search?

Binary Search (căutare binară) este o tehnică foarte eficientă de căutare într-o secvență sortată. Funcționează prin împărțirea repetată a secvenței în jumătăți și eliminarea jumătății care cu siguranță nu conține valoarea căutată.

În cazul nostru, versiunile sunt "sortate" în sensul că toate versiunile bune sunt la stânga și toate versiunile rele sunt la dreapta. Putem folosi binary search pentru a găsi punctul de tranziție.

## De Ce Folosim Binary Search?

Dacă am verifica fiecare versiune de la 1 la n, am face n apeluri în cel mai rău caz. Cu binary search, facem doar log₂(n) apeluri, ceea ce este mult mai eficient.

De exemplu, pentru 1000 de versiuni:
- Verificare liniară: până la 1000 apeluri
- Binary search: cel mult 10 apeluri (2¹⁰ = 1024)

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Avem doi pointeri - `left` (stânga) și `right` (dreapta) care definesc zona de căutare
2. Calculăm versiunea de mijloc (`mid`) între `left` și `right`
3. Verificăm dacă `mid` este o versiune rea
4. Dacă da, prima versiune rea este la `mid` sau în stânga, deci mutăm `right` la `mid`
5. Dacă nu, prima versiune rea este în dreapta, deci mutăm `left` la `mid + 1`
6. Repetăm până când `left == right` (am găsit prima versiune rea)

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int firstBadVersion(int n) {
```

Această linie definește funcția `firstBadVersion`. Funcția primește:
- `n` - numărul total de versiuni

Funcția returnează `int` - numărul primei versiuni rele.

```java
    int left = 1;
    int right = n;
```

Aceste linii inițializează pointerii care definesc zona de căutare. Să explicăm:

- `left = 1` - începutul zonei de căutare (prima versiune)
- `right = n` - sfârșitul zonei de căutare (ultima versiune)

Inițial, căutăm în toate versiunile de la 1 la n.

```java
    while (left < right) {
```

Această buclă continuă cât timp mai avem o zonă validă de căutare. Să explicăm:

- `left < right` înseamnă că mai avem cel puțin o versiune de verificat
- Când `left == right`, am găsit prima versiune rea

De ce folosim `left < right` în loc de `left <= right`? Pentru că vrem să găsim prima versiune rea, nu doar să verificăm dacă există. Când `left == right`, am găsit exact prima versiune rea.

```java
        int mid = left + (right - left) / 2;
```

Această linie calculează versiunea de mijloc. Să explicăm:

- `(right - left) / 2` calculează jumătatea distanței între `left` și `right`
- `left + (right - left) / 2` adună jumătatea la `left`, obținând versiunea de mijloc

De exemplu, dacă `left = 1` și `right = 10`:
- `(10 - 1) / 2 = 4`
- `1 + 4 = 5`
- Deci `mid = 5` (mijlocul între 1 și 10)

De ce nu folosim `(left + right) / 2`? Pentru că dacă `left` și `right` sunt foarte mari, suma lor ar putea depăși limita unui număr întreg (overflow). Formula noastră evită această problemă.

```java
        if (isBadVersion(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
```

Această parte verifică versiunea de mijloc și ajustează zona de căutare. Să explicăm:

- `isBadVersion(mid)` verifică dacă versiunea `mid` este rea
- Dacă da (`isBadVersion(mid)` returnează `true`):
  - `right = mid` mută sfârșitul zonei la `mid` (prima versiune rea este la `mid` sau în stânga)
  - Nu scădem 1 pentru că `mid` ar putea fi prima versiune rea
- Dacă nu (`isBadVersion(mid)` returnează `false`):
  - `left = mid + 1` mută începutul zonei la `mid + 1` (prima versiune rea este în dreapta)
  - Adăugăm 1 pentru că știm că `mid` este bună, deci prima versiune rea este după `mid`

De exemplu, dacă `mid = 5` și `isBadVersion(5)` returnează `true`, înseamnă că versiunea 5 este rea, deci prima versiune rea este la 5 sau înainte. Mutăm `right = 5` pentru a continua căutarea în zona 1-5.

Dacă `isBadVersion(5)` returnează `false`, înseamnă că versiunea 5 este bună, deci prima versiune rea este după 5. Mutăm `left = 6` pentru a continua căutarea în zona 6-10.

```java
    return left;
```

Această linie returnează prima versiune rea. Să explicăm:

- Când bucla se termină, `left == right` și ambele pointează către prima versiune rea
- Returnăm `left` (sau `right`, sunt egale)

De ce `left` este prima versiune rea? Pentru că algoritmul nostru asigură că `left` este întotdeauna prima poziție unde ar putea fi prima versiune rea. Când `left == right`, am găsit exact acea poziție.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `n = 5` și prima versiune rea este 4:

**Inițializare:**
- `left = 1`, `right = 5`

**Iterația 1:**
- `mid = 1 + (5 - 1) / 2 = 3`
- `isBadVersion(3)` → `false` (versiunea 3 este bună)
- `left = 3 + 1 = 4`
- Zona de căutare: versiunile 4-5

**Iterația 2:**
- `mid = 4 + (5 - 4) / 2 = 4`
- `isBadVersion(4)` → `true` (versiunea 4 este rea)
- `right = 4`
- Zona de căutare: versiunile 4-4

**După buclă:**
- `left = 4`, `right = 4`
- `left == right`, deci bucla se termină
- Returnăm `4`

**Rezultat:** `4` - prima versiune rea este 4.

## Alt Exemplu

Să urmărim pentru `n = 10` și prima versiune rea este 7:

**Inițializare:**
- `left = 1`, `right = 10`

**Iterația 1:**
- `mid = 1 + (10 - 1) / 2 = 5`
- `isBadVersion(5)` → `false`
- `left = 6`
- Zona: 6-10

**Iterația 2:**
- `mid = 6 + (10 - 6) / 2 = 8`
- `isBadVersion(8)` → `true`
- `right = 8`
- Zona: 6-8

**Iterația 3:**
- `mid = 6 + (8 - 6) / 2 = 7`
- `isBadVersion(7)` → `true`
- `right = 7`
- Zona: 6-7

**Iterația 4:**
- `mid = 6 + (7 - 6) / 2 = 6`
- `isBadVersion(6)` → `false`
- `left = 7`
- Zona: 7-7

**După buclă:**
- `left = 7`, `right = 7`
- Returnăm `7`

**Rezultat:** `7` - prima versiune rea este 7.

## De Ce Este Această Soluție Eficientă?

1. **Eliminăm jumătate din versiuni la fiecare iterație**: În loc să verificăm fiecare versiune, eliminăm jumătate din posibilități la fiecare pas.

2. **Complexitate logaritmică**: Dacă avem n versiuni, avem nevoie de cel mult log₂(n) apeluri la `isBadVersion`.

3. **Minimizăm apelurile**: Folosim exact numărul minim de apeluri necesare.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(log n) - unde n este numărul de versiuni. La fiecare iterație, eliminăm jumătate din versiuni, deci avem nevoie de log₂(n) iterații.

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile pentru pointeri, nu creăm structuri de date suplimentare.

## Concluzie

Binary Search este o tehnică foarte eficientă pentru găsirea primei versiuni rele. Prin eliminarea repetată a jumătăților din zona de căutare, găsim rapid prima versiune rea folosind doar O(log n) apeluri la `isBadVersion`.
