# Soluție Detaliată - Plus One

## Ce Ne Cere Problema?

Problema ne cere să adunăm 1 la un număr reprezentat ca un array de cifre. De exemplu, dacă avem array-ul `[1, 2, 3]`, acesta reprezintă numărul 123. Când adunăm 1, obținem 124, deci trebuie să returnăm `[1, 2, 4]`.

Problema devine interesantă când avem cifre care sunt 9. De exemplu, dacă avem `[9, 9]` (reprezentând 99) și adunăm 1, obținem 100, deci trebuie să returnăm `[1, 0, 0]` - un array mai mare cu un element în plus.

## Ce Este un Număr Reprezentat ca Array?

Un număr poate fi reprezentat ca un array de cifre, unde fiecare element al array-ului este o cifră. De exemplu:
- `[1, 2, 3]` reprezintă numărul 123
- `[9, 9]` reprezintă numărul 99
- `[1, 0, 0]` reprezintă numărul 100

Prima cifră (la stânga) este cea mai semnificativă (cea mai importantă), iar ultima cifră (la dreapta) este cea mai puțin semnificativă.

## Ce Este "Carry" (Transport)?

Când adunăm 1 la o cifră, dacă rezultatul este 10 sau mai mare, trebuie să "transportăm" 1 la cifra următoare (la stânga). Acest proces se numește "carry" (transport).

De exemplu:
- Dacă avem 9 și adunăm 1, obținem 10. Scriem 0 și transportăm 1 la cifra următoare.
- Dacă avem 19 și adunăm 1, obținem 20. Cifra unităților devine 0, iar cifra zecilor crește cu 1.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Începem de la ultima cifră (cea mai puțin semnificativă, la dreapta)
2. Adunăm 1 la cifra curentă
3. Dacă rezultatul este mai mic decât 10, am terminat - returnăm array-ul
4. Dacă rezultatul este 10, setăm cifra la 0 și continuăm cu cifra următoare (la stânga)
5. Dacă toate cifrele erau 9, trebuie să creăm un array nou cu 1 la început și 0-uri după

## De Ce Începem de la Dreapta?

În matematică, când adunăm numere, începem de la cifra unităților (dreapta) și mergem spre stânga. Același principiu se aplică și aici. Dacă am începe de la stânga, am putea modifica cifre care ar putea fi afectate de transportul de la cifrele din dreapta.

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int[] plusOne(int[] digits) {
```

Această linie definește funcția `plusOne`. Funcția primește:
- `digits` - array-ul de cifre care reprezintă numărul

Funcția returnează `int[]` - un array de cifre care reprezintă numărul + 1.

```java
    if (digits == null || digits.length == 0) {
        return new int[]{1};
    }
```

Această verificare tratează cazul special când array-ul este null sau gol. În acest caz, returnăm un array cu un singur element: `[1]`, care reprezintă numărul 1.

```java
    for (int i = digits.length - 1; i >= 0; i--) {
```

Această buclă parcurge array-ul de la sfârșit la început (de la dreapta la stânga). Să explicăm:

- `int i = digits.length - 1` inițializează `i` cu poziția ultimului element (ultima cifră)
- `i >= 0` este condiția - bucla continuă cât timp `i` este mai mare sau egal cu 0
- `i--` scade `i` cu 1 după fiecare iterație, deci mergem de la dreapta la stânga

De exemplu, dacă array-ul are 3 elemente (indicele 0, 1, 2), bucla va rula de 3 ori:
- Prima iterație: `i = 2` (ultima cifră)
- A doua iterație: `i = 1` (cifra din mijloc)
- A treia iterație: `i = 0` (prima cifră)

```java
        digits[i]++;
```

Această linie adună 1 la cifra curentă. Să explicăm:

- `digits[i]` este cifra de la poziția `i`
- `++` înseamnă "mărește cu 1" - adunăm 1 la cifra curentă

De exemplu, dacă `digits[i] = 5`, după `digits[i]++`, `digits[i]` devine 6.

```java
        if (digits[i] < 10) {
            return digits;
        }
```

Această condiție verifică dacă cifra este mai mică decât 10 după adunare. Să explicăm:

- `digits[i] < 10` verifică dacă cifra este mai mică decât 10
- Dacă da, înseamnă că nu avem transport (carry) - cifra este între 0 și 9
- În acest caz, returnăm imediat array-ul, deoarece nu mai trebuie să modificăm alte cifre

De ce returnăm imediat? Pentru că dacă o cifră este mai mică decât 10 după adunare, nu avem transport, deci cifrele din stânga rămân neschimbate.

```java
        digits[i] = 0;
```

Această linie setăm cifra la 0. Să explicăm când se execută:

- Această linie se execută doar dacă `digits[i] >= 10` (dacă nu am returnat mai sus)
- Dacă cifra este 10 sau mai mare după adunare, înseamnă că avem transport
- Setăm cifra la 0 și continuăm cu cifra următoare (la stânga), care va primi transportul de 1

De exemplu, dacă `digits[i] = 9` și adunăm 1, obținem 10. Setăm `digits[i] = 0` și continuăm cu cifra următoare, care va primi 1 (transport).

```java
    int[] result = new int[digits.length + 1];
    result[0] = 1;
    return result;
```

Aceste linii se execută doar dacă am terminat de parcurs toate cifrele și toate erau 9. Să explicăm:

- `new int[digits.length + 1]` creează un array nou cu un element în plus față de array-ul original
- `result[0] = 1` setează prima cifră la 1
- Toate celelalte cifre sunt automat 0 (Java inițializează array-urile cu 0)

De ce avem nevoie de un array nou? Pentru că dacă toate cifrele erau 9, adunând 1 obținem un număr cu o cifră în plus. De exemplu:
- `[9, 9]` (99) + 1 = `[1, 0, 0]` (100)
- `[9]` (9) + 1 = `[1, 0]` (10)

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `digits = [1, 2, 3]`:

**Inițializare:**
- Array-ul: `[1, 2, 3]` (reprezintă 123)

**Iterația 1 (i = 2, digits[2] = 3):**
- Adunăm 1: `digits[2]++` → `digits[2] = 4`
- Verificăm: `4 < 10` → `true`
- Returnăm imediat: `[1, 2, 4]`

**Rezultat:** `[1, 2, 4]` (reprezintă 124)

## Alt Exemplu - Cu Transport

Să urmărim pentru `digits = [1, 9, 9]`:

**Inițializare:**
- Array-ul: `[1, 9, 9]` (reprezintă 199)

**Iterația 1 (i = 2, digits[2] = 9):**
- Adunăm 1: `digits[2]++` → `digits[2] = 10`
- Verificăm: `10 < 10` → `false`
- Setăm: `digits[2] = 0`
- Array-ul: `[1, 9, 0]`
- Continuăm cu următoarea cifră

**Iterația 2 (i = 1, digits[1] = 9):**
- Adunăm 1: `digits[1]++` → `digits[1] = 10`
- Verificăm: `10 < 10` → `false`
- Setăm: `digits[1] = 0`
- Array-ul: `[1, 0, 0]`
- Continuăm cu următoarea cifră

**Iterația 3 (i = 0, digits[0] = 1):**
- Adunăm 1: `digits[0]++` → `digits[0] = 2`
- Verificăm: `2 < 10` → `true`
- Returnăm: `[2, 0, 0]`

**Rezultat:** `[2, 0, 0]` (reprezintă 200)

## Exemplu - Toate Cifrele Sunt 9

Să urmărim pentru `digits = [9, 9]`:

**Inițializare:**
- Array-ul: `[9, 9]` (reprezintă 99)

**Iterația 1 (i = 1, digits[1] = 9):**
- Adunăm 1: `digits[1]++` → `digits[1] = 10`
- Verificăm: `10 < 10` → `false`
- Setăm: `digits[1] = 0`
- Array-ul: `[9, 0]`
- Continuăm

**Iterația 2 (i = 0, digits[0] = 9):**
- Adunăm 1: `digits[0]++` → `digits[0] = 10`
- Verificăm: `10 < 10` → `false`
- Setăm: `digits[0] = 0`
- Array-ul: `[0, 0]`
- Continuăm (dar i devine -1, bucla se termină)

**După buclă:**
- Toate cifrele erau 9 și au devenit 0
- Creăm array nou: `result = new int[3]` → `[0, 0, 0]`
- Setăm prima cifră: `result[0] = 1`
- Array-ul: `[1, 0, 0]`

**Rezultat:** `[1, 0, 0]` (reprezintă 100)

## De Ce Este Această Soluție Eficientă?

1. **Parcurgem array-ul o singură dată**: În cel mai bun caz, adunăm 1 la ultima cifră și returnăm imediat, fără să modificăm alte cifre.

2. **Oprim imediat când nu mai avem transport**: Dacă o cifră este mai mică decât 10 după adunare, returnăm imediat, economisind timp.

3. **Modificăm array-ul direct când este posibil**: Doar când toate cifrele sunt 9 creăm un array nou.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: 
  - Cel mai bun caz: O(1) - dacă ultima cifră nu este 9, returnăm imediat
  - Cel mai rău caz: O(n) - dacă toate cifrele sunt 9, parcurgem tot array-ul
  - Cazul mediu: O(1) - de obicei, doar ultimele câteva cifre sunt 9

- **Complexitatea spațiului**: 
  - Cel mai bun caz: O(1) - modificăm array-ul existent
  - Cel mai rău caz: O(n) - când toate cifrele sunt 9, creăm un array nou cu un element în plus

## Concluzie

Această soluție este elegantă și eficientă. Procesăm cifrele de la dreapta la stânga, adunând 1 și gestionând transportul. Dacă toate cifrele erau 9, creăm un array nou cu 1 la început și 0-uri după.
