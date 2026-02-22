# Soluție Detaliată - Longest Common Prefix

## Ce Ne Cere Problema?

Problema ne cere să găsim cel mai lung prefix comun (subșir de la început) între toate string-urile dintr-un array. De exemplu, dacă avem `["flower", "flow", "flight"]`, cel mai lung prefix comun este "fl".

## Ce Este un Prefix?

Un prefix este un subșir care începe de la începutul string-ului. De exemplu, prefixele lui "flower" sunt: "f", "fl", "flo", "flow", "flowe", "flower".

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Folosim primul string ca referință
2. Pentru fiecare poziție din primul string, verificăm dacă toate celelalte string-uri au același caracter la aceeași poziție
3. Dacă găsim o nepotrivire sau un string este prea scurt, returnăm prefixul până la poziția anterioară
4. Dacă toate string-urile au caracterele identice pentru toate pozițiile primului string, returnăm primul string complet

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public String longestCommonPrefix(String[] strs) {
```

Această linie definește funcția `longestCommonPrefix`. Funcția primește:
- `strs` - array-ul de string-uri

Funcția returnează `String` - cel mai lung prefix comun.

```java
    if (strs == null || strs.length == 0) {
        return "";
    }
```

Această verificare tratează cazurile speciale. Dacă array-ul este null sau gol, nu există prefix comun, deci returnăm string-ul gol "".

```java
    for (int i = 0; i < strs[0].length(); i++) {
```

Această buclă parcurge fiecare caracter din primul string. `i` este poziția caracterului curent. Folosim primul string ca referință pentru că prefixul comun trebuie să fie comun tuturor string-urilor, deci trebuie să fie un prefix al primului string.

```java
        char c = strs[0].charAt(i);
```

Această linie extrage caracterul de la poziția `i` din primul string. Acest caracter va fi comparat cu caracterele de la aceeași poziție din celelalte string-uri.

```java
        for (int j = 1; j < strs.length; j++) {
```

Această buclă parcurge toate celelalte string-uri (de la al doilea încolo). `j` este indicele string-ului curent din array.

```java
            if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                return strs[0].substring(0, i);
            }
```

Această condiție verifică dacă string-ul curent nu are același caracter. Să explicăm:

- `i >= strs[j].length()` verifică dacă string-ul curent este prea scurt (nu are caracter la poziția `i`)
- `strs[j].charAt(i) != c` verifică dacă caracterul de la poziția `i` din string-ul curent este diferit de caracterul din primul string
- `||` înseamnă "sau" - dacă oricare dintre condiții este adevărată, string-ul curent nu face parte din prefixul comun
- `return strs[0].substring(0, i)` returnează prefixul comun până la poziția anterioară (excludând poziția `i`)

De exemplu, dacă `strs[0] = "flower"` și `i = 2` (caracterul 'o'), iar `strs[1] = "flow"` (care are doar 4 caractere, deci `i = 2` este valid, dar să zicem că la `i = 4` nu mai există), atunci `strs[0].substring(0, 4)` returnează "flow".

```java
    return strs[0];
```

Această linie se execută doar dacă toate string-urile au caracterele identice pentru toate pozițiile primului string. În acest caz, primul string întreg este prefixul comun, deci îl returnăm.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `strs = ["flower", "flow", "flight"]`:

**Inițializare:**
- Primul string: "flower" (lungime 6)

**Iterația 1 (i = 0, caracterul 'f'):**
- `c = 'f'`
- Verificăm `strs[1] = "flow"`: `strs[1].charAt(0) = 'f' == 'f'`? `true`
- Verificăm `strs[2] = "flight"`: `strs[2].charAt(0) = 'f' == 'f'`? `true`
- Toate string-urile au 'f' la poziția 0, continuăm

**Iterația 2 (i = 1, caracterul 'l'):**
- `c = 'l'`
- Verificăm `strs[1]`: `strs[1].charAt(1) = 'l' == 'l'`? `true`
- Verificăm `strs[2]`: `strs[2].charAt(1) = 'l' == 'l'`? `true`
- Toate string-urile au 'l' la poziția 1, continuăm

**Iterația 3 (i = 2, caracterul 'o'):**
- `c = 'o'`
- Verificăm `strs[1]`: `strs[1].charAt(2) = 'o' == 'o'`? `true`
- Verificăm `strs[2]`: `strs[2].charAt(2) = 'i' == 'o'`? `false`!
- Returnăm `strs[0].substring(0, 2) = "fl"`

**Rezultat:** `"fl"` - cel mai lung prefix comun este "fl".

## Alt Exemplu - Primul String Este Prefixul Complet

Să urmărim pentru `strs = ["ab", "abc", "abcd"]`:

**Iterația 1 (i = 0, 'a'):**
- Toate string-urile au 'a' la poziția 0, continuăm

**Iterația 2 (i = 1, 'b'):**
- Toate string-urile au 'b' la poziția 1, continuăm

**După toate iterațiile:**
- Primul string "ab" are doar 2 caractere, deci am terminat
- Toate string-urile au caracterele identice pentru toate pozițiile primului string
- Returnăm `strs[0] = "ab"`

**Rezultat:** `"ab"` - primul string întreg este prefixul comun.

## De Ce Este Această Soluție Eficientă?

1. **O(S) timp**: Unde S este suma tuturor caracterelor din toate string-urile. În cel mai rău caz, comparăm fiecare caracter o dată.

2. **O(1) spațiu**: Folosim doar câteva variabile, nu creăm structuri de date suplimentare (excludând string-ul rezultat).

3. **Early termination**: Ne oprim imediat când găsim o nepotrivire, fără să continuăm verificarea.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(S) - unde S este suma tuturor caracterelor. În cel mai rău caz, comparăm fiecare caracter o dată.

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile (excludând string-ul rezultat care este returnat).

## Concluzie

Această soluție este simplă și eficientă. Folosim primul string ca referință și verificăm vertical (poziție cu poziție) dacă toate string-urile au caracterele identice, returnând prefixul comun imediat când găsim o nepotrivire.
