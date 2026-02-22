# Soluție Detaliată - Reverse String

## Ce Ne Cere Problema?

Problema ne cere să inversăm un array de caractere direct în array-ul dat, fără să creăm un array nou. De exemplu, dacă avem `['h', 'e', 'l', 'l', 'o']`, după inversare ar trebui să avem `['o', 'l', 'l', 'e', 'h']`.

## Ce Înseamnă "In-Place"?

"In-place" înseamnă că modificăm array-ul dat direct, fără să creăm un array nou. Este ca și cum ai inversa ordinea cărților dintr-un pachet direct în același pachet, fără să le muți într-un alt pachet.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Folosim doi pointeri - unul de la început (stânga) și unul de la sfârșit (dreapta)
2. Schimbăm (swap) caracterele de la ambele capete
3. Mutăm pointerii către centru
4. Repetăm până când pointerii se întâlnesc

## Ce Este Swap (Schimb)?

Swap înseamnă să schimbi valorile a două variabile. De exemplu, dacă avem `a = 'h'` și `b = 'o'`, după swap avem `a = 'o'` și `b = 'h'`.

Pentru a face swap, avem nevoie de o variabilă temporară:
1. Salvează valoarea lui `a` într-o variabilă temporară
2. Copiază valoarea lui `b` în `a`
3. Copiază valoarea temporară (valoarea veche a lui `a`) în `b`

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public void reverseString(char[] s) {
```

Această linie definește funcția `reverseString`. Funcția primește:
- `s` - array-ul de caractere de inversat

Funcția nu returnează nimic (`void`) - modifică array-ul direct.

```java
    if (s == null || s.length <= 1) {
        return;
    }
```

Această verificare tratează cazurile speciale. Dacă array-ul este null, gol sau are un singur element, nu avem ce inversa, deci returnăm imediat.

```java
    int left = 0;
    int right = s.length - 1;
```

Aceste linii inițializează cei doi pointeri. Să explicăm:

- `left = 0` - pointer-ul de la stânga, începe de la primul caracter (poziția 0)
- `right = s.length - 1` - pointer-ul de la dreapta, începe de la ultimul caracter

De exemplu, dacă array-ul are 5 elemente (indicele 0-4), atunci `right = 4`.

```java
    while (left < right) {
```

Această buclă continuă cât timp pointerii nu s-au întâlnit. Să explicăm:

- `left < right` înseamnă că pointerii nu s-au întâlnit încă
- Când `left >= right`, am terminat de inversat (sau am ajuns la mijloc)

De ce folosim `<` și nu `<=`? Pentru că când `left == right`, am ajuns la mijlocul array-ului (sau la un singur element rămas), care nu trebuie schimbat cu el însuși.

```java
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
```

Aceste linii fac swap între caracterele de la ambele capete. Să explicăm pas cu pas:

- `char temp = s[left]` salvează caracterul de la poziția `left` într-o variabilă temporară
- `s[left] = s[right]` copiază caracterul de la poziția `right` la poziția `left`
- `s[right] = temp` copiază caracterul salvat (caracterul vechi de la `left`) la poziția `right`

De exemplu, dacă `s = ['h', 'e', 'l', 'l', 'o']`, `left = 0` și `right = 4`:
- `temp = 'h'` (salvăm 'h')
- `s[0] = 'o'` (copiem 'o' la poziția 0)
- `s[4] = 'h'` (copiem 'h' la poziția 4)
- Rezultat: `['o', 'e', 'l', 'l', 'h']`

```java
        left++;
        right--;
```

Aceste linii mută pointerii către centru. Să explicăm:

- `left++` mărește `left` cu 1, mutând pointer-ul de la stânga cu o poziție la dreapta
- `right--` scade `right` cu 1, mutând pointer-ul de la dreapta cu o poziție la stânga

După aceste operații, pointerii sunt mai aproape de centru și sunt pregătiți pentru următoarea iterație.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `s = ['h', 'e', 'l', 'l', 'o']`:

**Inițializare:**
- `left = 0`, `right = 4`
- Array-ul: `['h', 'e', 'l', 'l', 'o']`

**Iterația 1:**
- Swap: `s[0]` ('h') cu `s[4]` ('o')
- `temp = 'h'`, `s[0] = 'o'`, `s[4] = 'h'`
- `left = 1`, `right = 3`
- Array-ul: `['o', 'e', 'l', 'l', 'h']`

**Iterația 2:**
- Swap: `s[1]` ('e') cu `s[3]` ('l')
- `temp = 'e'`, `s[1] = 'l'`, `s[3] = 'e'`
- `left = 2`, `right = 2`
- Array-ul: `['o', 'l', 'l', 'e', 'h']`

**După buclă:**
- `left = 2`, `right = 2`
- `left < right`? `false`, bucla se termină
- Caracterul de la mijloc (poziția 2, 'l') rămâne neschimbat (corect, pentru că este la mijloc)

**Rezultat:** `['o', 'l', 'l', 'e', 'h']` - array-ul este inversat.

## Alt Exemplu - Număr Par de Elemente

Să urmărim pentru `s = ['H', 'a', 'n', 'n', 'a', 'h']`:

**Inițializare:**
- `left = 0`, `right = 5`

**Iterația 1:**
- Swap `s[0]` ('H') cu `s[5]` ('h')
- Array: `['h', 'a', 'n', 'n', 'a', 'H']`
- `left = 1`, `right = 4`

**Iterația 2:**
- Swap `s[1]` ('a') cu `s[4]` ('a')
- Array: `['h', 'a', 'n', 'n', 'a', 'H']` (neschimbat, caracterele sunt identice)
- `left = 2`, `right = 3`

**Iterația 3:**
- Swap `s[2]` ('n') cu `s[3]` ('n')
- Array: `['h', 'a', 'n', 'n', 'a', 'H']` (neschimbat)
- `left = 3`, `right = 2`
- `left < right`? `false`, bucla se termină

**Rezultat:** `['h', 'a', 'n', 'n', 'a', 'H']` - array-ul este inversat.

## De Ce Este Această Soluție Eficientă?

1. **Parcurgem array-ul o singură dată**: Facem doar n/2 swap-uri (unde n este numărul de elemente).

2. **O(1) spațiu suplimentar**: Folosim doar câteva variabile (pointeri și variabila temporară pentru swap).

3. **Modificăm array-ul direct**: Nu creăm un array nou, ci inversăm caracterele direct în array-ul existent.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de caractere. Facem n/2 swap-uri, deci O(n).

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile, nu creăm structuri de date suplimentare.

## Concluzie

Această soluție este elegantă și eficientă. Folosim doi pointeri pentru a schimba caracterele de la ambele capete, inversând astfel array-ul într-o singură parcurgere și fără spațiu suplimentar.
