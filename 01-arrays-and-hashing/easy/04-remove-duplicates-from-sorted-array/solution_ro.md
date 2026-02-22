# Soluție Detaliată - Remove Duplicates from Sorted Array

## Ce Ne Cere Problema?

Problema ne cere să eliminăm duplicatele dintr-un array sortat (ordonat), dar să facem asta direct în array-ul dat, fără să creăm un array nou. Trebuie să returnăm lungimea noului array după eliminarea duplicatelor.

De exemplu, dacă avem array-ul `[1, 1, 2, 3, 3, 4]`, după eliminarea duplicatelor ar trebui să avem `[1, 2, 3, 4]` în primele 4 poziții, și să returnăm 4 (lungimea noului array).

## Ce Înseamnă "In-Place"?

"In-place" înseamnă că modificăm array-ul dat direct, fără să creăm un array nou. Este ca și cum ai reorganiza cărțile dintr-o bibliotecă direct pe raft, fără să le muți într-o altă bibliotecă.

## De Ce Este Important Că Array-ul Este Sortat?

Când un array este sortat (ordonat), toate duplicatele sunt unul lângă altul. De exemplu, în `[1, 1, 2, 3, 3, 4]`, duplicatele sunt consecutive:
- Primul 1 și al doilea 1 sunt unul lângă altul
- Primul 3 și al doilea 3 sunt unul lângă altul

Această proprietate ne permite să folosim o tehnică numită "two pointers" (doi pointeri) pentru a rezolva problema eficient.

## Ce Sunt Pointerii?

În contextul acestei probleme, "pointer" înseamnă o variabilă care ține minte o poziție (index) în array. Avem două pointeri:
- **Slow pointer** (pointer lent): ține minte poziția unde urmează să punem următorul element unic
- **Fast pointer** (pointer rapid): parcurge array-ul și verifică fiecare element

Gândiți-vă la ei ca la doi oameni care lucrează împreună:
- Fast pointer este ca un inspector care verifică fiecare element
- Slow pointer este ca un arhivar care pune doar elementele unice în locul potrivit

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Primul element este întotdeauna unic, deci îl păstrăm
2. Folosim slow pointer pentru a ține minte unde urmează să punem următorul element unic
3. Folosim fast pointer pentru a parcurge array-ul
4. Când fast pointer găsește un element diferit de cel anterior, înseamnă că este un element nou (unic)
5. Punem acest element nou la poziția slow pointer și mutăm slow pointer înainte
6. Continuăm până când fast pointer parcurge tot array-ul

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int removeDuplicates(int[] nums) {
```

Această linie definește funcția `removeDuplicates`. Funcția primește:
- `nums` - array-ul sortat din care eliminăm duplicatele

Funcția returnează `int` - un număr care reprezintă lungimea noului array după eliminarea duplicatelor.

```java
    if (nums == null || nums.length == 0) {
        return 0;
    }
```

Această verificare se asigură că array-ul există și nu este gol. Să explicăm:

- `nums == null` verifică dacă array-ul nu există deloc
- `nums.length == 0` verifică dacă array-ul este gol (are 0 elemente)
- `||` înseamnă "sau" - dacă oricare dintre condiții este adevărată
- Dacă array-ul este null sau gol, returnăm 0 (nu există elemente unice)

```java
    if (nums.length == 1) {
        return 1;
    }
```

Această verificare tratează cazul special când array-ul are doar un element. Dacă are doar un element, acesta este automat unic (nu poate fi duplicat), deci returnăm 1.

```java
    int slow = 1;
```

Această linie inițializează slow pointer-ul la poziția 1. Să explicăm de ce:

- Primul element (la poziția 0) este întotdeauna unic, deci îl păstrăm
- Slow pointer începe de la 1 pentru că următorul element unic va fi plasat la poziția 1 (sau mai departe)

Slow pointer indică poziția unde urmează să punem următorul element unic.

```java
    for (int fast = 1; fast < nums.length; fast++) {
```

Această buclă inițializează fast pointer-ul și îl mută prin array. Să explicăm:

- `int fast = 1` inițializează fast pointer-ul la poziția 1 (începem de la al doilea element)
- `fast < nums.length` este condiția - bucla continuă cât timp fast pointer-ul este în limitele array-ului
- `fast++` mărește fast pointer-ul cu 1 după fiecare iterație

Fast pointer parcurge array-ul de la poziția 1 până la sfârșit, verificând fiecare element.

```java
        if (nums[fast] != nums[fast - 1]) {
```

Această condiție verifică dacă elementul curent este diferit de elementul anterior. Să explicăm:

- `nums[fast]` este elementul de la poziția curentă (unde este fast pointer-ul)
- `nums[fast - 1]` este elementul de la poziția anterioară (cu o poziție înainte)
- `!=` înseamnă "diferit de" sau "nu este egal cu"

De ce comparăm cu elementul anterior? Pentru că array-ul este sortat, deci dacă un element este diferit de cel anterior, înseamnă că este un element nou (unic). Dacă ar fi duplicat, ar fi identic cu cel anterior.

```java
            nums[slow] = nums[fast];
```

Această linie copiază elementul unic la poziția slow pointer-ului. Să explicăm:

- `nums[slow]` este poziția unde vrem să punem elementul unic
- `nums[fast]` este elementul unic pe care l-am găsit
- `=` înseamnă "copiază valoarea" - copiem elementul de la poziția fast la poziția slow

Această operație "mută" elementul unic în locul potrivit, păstrând ordinea elementelor unice.

```java
            slow++;
```

Această linie mută slow pointer-ul înainte cu o poziție. După ce am plasat un element unic, slow pointer-ul trebuie să indice următoarea poziție disponibilă pentru următorul element unic.

```java
    return slow;
```

Această linie returnează valoarea slow pointer-ului, care reprezintă numărul de elemente unice. Să explicăm de ce:

- Slow pointer indică poziția unde urmează să punem următorul element unic
- Dacă slow pointer este la poziția 4, înseamnă că am plasat elemente unice în pozițiile 0, 1, 2, 3
- Deci slow pointer este exact numărul de elemente unice

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `nums = [1, 1, 2, 3, 3, 4]`:

**Inițializare:**
- `slow = 1` (primul element, la poziția 0, este deja unic)
- Array-ul: `[1, 1, 2, 3, 3, 4]`

**Iterația 1 (fast = 1, nums[1] = 1):**
- Comparăm: `nums[1] != nums[0]` → `1 != 1` → `false`
- Nu este diferit, deci este duplicat - îl sărim
- `slow` rămâne 1
- Array-ul: `[1, 1, 2, 3, 3, 4]` (neschimbat)

**Iterația 2 (fast = 2, nums[2] = 2):**
- Comparăm: `nums[2] != nums[1]` → `2 != 1` → `true`
- Este diferit! Este un element nou
- Copiem: `nums[slow] = nums[fast]` → `nums[1] = 2`
- `slow++` → `slow = 2`
- Array-ul: `[1, 2, 2, 3, 3, 4]`

**Iterația 3 (fast = 3, nums[3] = 3):**
- Comparăm: `nums[3] != nums[2]` → `3 != 2` → `true`
- Este diferit! Este un element nou
- Copiem: `nums[slow] = nums[fast]` → `nums[2] = 3`
- `slow++` → `slow = 3`
- Array-ul: `[1, 2, 3, 3, 3, 4]`

**Iterația 4 (fast = 4, nums[4] = 3):**
- Comparăm: `nums[4] != nums[3]` → `3 != 3` → `false`
- Nu este diferit, deci este duplicat - îl sărim
- `slow` rămâne 3
- Array-ul: `[1, 2, 3, 3, 3, 4]` (neschimbat)

**Iterația 5 (fast = 5, nums[5] = 4):**
- Comparăm: `nums[5] != nums[4]` → `4 != 3` → `true`
- Este diferit! Este un element nou
- Copiem: `nums[slow] = nums[fast]` → `nums[3] = 4`
- `slow++` → `slow = 4`
- Array-ul: `[1, 2, 3, 4, 3, 4]`

**După toate iterațiile:**
- `slow = 4`
- Primele 4 elemente sunt unice: `[1, 2, 3, 4]`
- Returnăm 4

**Rezultat:** Array-ul are elementele unice în primele 4 poziții: `[1, 2, 3, 4, ...]`, iar funcția returnează 4.

## De Ce Este Această Soluție Eficientă?

1. **Parcurgem array-ul o singură dată**: Nu comparăm fiecare element cu toate celelalte, ci doar cu elementul anterior.

2. **Modificăm array-ul direct**: Nu creăm un array nou, ci reorganizăm elementele în array-ul existent.

3. **Folosim spațiu constant**: Nu folosim spațiu suplimentar (doar două variabile pentru pointeri).

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de elemente din array. Parcurgem array-ul o singură dată.

- **Complexitatea spațiului**: O(1) - folosim doar două variabile (slow și fast), nu creăm structuri de date suplimentare.

## Concluzie

Această soluție este elegantă și eficientă. Folosim doi pointeri pentru a identifica elementele unice și a le plasa în locul potrivit, păstrând ordinea și modificând array-ul direct, fără spațiu suplimentar.
