# Soluție Detaliată - Lemonade Change

## Ce Ne Cere Problema?

Problema ne cere să verificăm dacă putem oferi rest corect tuturor clienților. Limonada costă $5. Clienții plătesc cu bancnote de $5, $10 sau $20. Trebuie să oferim rest corect folosind bancnotele pe care le avem.

De exemplu, dacă avem `bills = [5, 5, 5, 10, 20]`:
- Client 1: plătește $5, nu trebuie rest
- Client 2: plătește $5, nu trebuie rest
- Client 3: plătește $5, nu trebuie rest
- Client 4: plătește $10, trebuie să dăm $5 rest (avem $5)
- Client 5: plătește $20, trebuie să dăm $15 rest (avem $10 + $5)
- Rezultat: `true` - putem oferi rest tuturor

## De Ce Preferăm $10 + $5 pentru Rest de $20?

Preferăm să dăm $10 + $5 în loc de trei bancnote de $5 pentru că bancnotele de $5 sunt mai versatile (sunt necesare pentru rest de $10 și $20). Păstrând mai multe bancnote de $5, maximizăm capacitatea noastră de a oferi rest în viitor.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Menținem contoare pentru bancnotele de $5 și $10 (nu avem nevoie să ținem minte $20, pentru că nu le dăm ca rest)
2. Pentru fiecare plată:
   - Dacă este $5, nu trebuie rest, doar adăugăm bancnota
   - Dacă este $10, trebuie să dăm $5 rest
   - Dacă este $20, preferăm să dăm $10 + $5, altfel trei bancnote de $5
3. Dacă nu putem oferi rest, returnăm `false`

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public boolean lemonadeChange(int[] bills) {
```

Această linie definește funcția `lemonadeChange`. Funcția primește:
- `bills` - array-ul cu bancnotele cu care plătesc clienții

Funcția returnează `boolean` - `true` dacă putem oferi rest tuturor, `false` dacă nu putem.

```java
    if (bills == null || bills.length == 0) {
        return true;
    }
```

Această verificare tratează cazul când nu există clienți. Dacă nu există clienți, putem oferi rest tuturor (vacuously true).

```java
    int fiveCount = 0;
    int tenCount = 0;
```

Aceste linii inițializează contoarele pentru bancnote. Să explicăm:

- `fiveCount` numără bancnotele de $5 pe care le avem
- `tenCount` numără bancnotele de $10 pe care le avem
- Nu avem nevoie să ținem minte bancnotele de $20, pentru că nu le dăm ca rest

```java
    for (int bill : bills) {
```

Această buclă parcurge fiecare plată. `bill` este variabila care stochează bancnota cu care plătește clientul curent.

```java
        if (bill == 5) {
            fiveCount++;
        }
```

Această parte tratează plata cu $5. Să explicăm:

- Dacă clientul plătește cu $5, nu trebuie să dăm rest (limonada costă $5)
- `fiveCount++` adaugă bancnota de $5 la colecția noastră

```java
        else if (bill == 10) {
            if (fiveCount > 0) {
                fiveCount--;
                tenCount++;
            } else {
                return false;
            }
        }
```

Această parte tratează plata cu $10. Să explicăm:

- Dacă clientul plătește cu $10, trebuie să dăm $5 rest
- `fiveCount > 0` verifică dacă avem bancnote de $5 disponibile
- Dacă da, `fiveCount--` dăm o bancnotă de $5 ca rest
- `tenCount++` adăugăm bancnota de $10 la colecția noastră
- Dacă nu avem bancnote de $5, `return false` (nu putem oferi rest)

```java
        else { // bill == 20
            if (tenCount > 0 && fiveCount > 0) {
                tenCount--;
                fiveCount--;
            } else if (fiveCount >= 3) {
                fiveCount -= 3;
            } else {
                return false;
            }
        }
```

Această parte tratează plata cu $20. Să explicăm:

- Dacă clientul plătește cu $20, trebuie să dăm $15 rest
- `tenCount > 0 && fiveCount > 0` verifică dacă avem $10 + $5 (preferat)
- Dacă da, `tenCount--` și `fiveCount--` dăm $10 + $5 ca rest
- `else if (fiveCount >= 3)` verifică dacă avem cel puțin 3 bancnote de $5
- Dacă da, `fiveCount -= 3` dăm trei bancnote de $5 ca rest
- Dacă nu avem niciuna dintre opțiuni, `return false` (nu putem oferi rest)

De ce preferăm $10 + $5? Pentru că bancnotele de $5 sunt mai utile (sunt necesare pentru rest de $10 și $20). Păstrând mai multe bancnote de $5, maximizăm capacitatea noastră de a oferi rest în viitor.

```java
    return true;
```

Această linie se execută doar dacă am procesat cu succes toți clienții. În acest caz, putem oferi rest tuturor, deci returnăm `true`.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `bills = [5, 5, 5, 10, 20]`:

**Inițializare:**
- `fiveCount = 0`, `tenCount = 0`

**Iterația 1 (bill = 5):**
- `fiveCount++` → `fiveCount = 1`
- `fiveCount = 1`, `tenCount = 0`

**Iterația 2 (bill = 5):**
- `fiveCount++` → `fiveCount = 2`
- `fiveCount = 2`, `tenCount = 0`

**Iterația 3 (bill = 5):**
- `fiveCount++` → `fiveCount = 3`
- `fiveCount = 3`, `tenCount = 0`

**Iterația 4 (bill = 10):**
- `fiveCount > 0`? `true`
- `fiveCount--` → `fiveCount = 2`
- `tenCount++` → `tenCount = 1`
- `fiveCount = 2`, `tenCount = 1`

**Iterația 5 (bill = 20):**
- `tenCount > 0 && fiveCount > 0`? `true`
- `tenCount--` → `tenCount = 0`
- `fiveCount--` → `fiveCount = 1`
- `fiveCount = 1`, `tenCount = 0`

**După toate iterațiile:**
- Am procesat cu succes toți clienții
- Returnăm `true`

**Rezultat:** `true` - putem oferi rest tuturor clienților.

## Alt Exemplu - Nu Putem Oferi Rest

Să urmărim pentru `bills = [5, 5, 10, 10, 20]`:

**Iterația 1:** `fiveCount = 1`
**Iterația 2:** `fiveCount = 2`
**Iterația 3:** `fiveCount = 1`, `tenCount = 1`
**Iterația 4:** `fiveCount = 0`, `tenCount = 2`
**Iterația 5:** `tenCount > 0 && fiveCount > 0`? `false`, `fiveCount >= 3`? `false`
- Returnăm `false`

**Rezultat:** `false` - nu putem oferi rest pentru ultimul client.

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Parcurgem array-ul o singură dată, făcând O(1) operații pentru fiecare plată.

2. **O(1) spațiu**: Folosim doar două contoare, nu creăm structuri de date suplimentare.

3. **Greedy optim**: Strategia de a prefera $10 + $5 pentru rest de $20 este optimă.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de clienți. Parcurgem array-ul o singură dată.

- **Complexitatea spațiului**: O(1) - folosim doar două contoare, nu creăm structuri de date suplimentare.

## Concluzie

Această soluție este simplă și eficientă. Menținem contoare pentru bancnotele de $5 și $10, procesăm fiecare plată și oferim rest folosind strategia greedy (preferăm $10 + $5 pentru rest de $20), returnând `false` imediat când nu putem oferi rest, verificând astfel dacă putem oferi rest tuturor clienților.
