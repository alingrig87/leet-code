# Soluție Detaliată - Valid Anagram

## Ce Ne Cere Problema?

Problema ne cere să verificăm dacă două cuvinte (string-uri) sunt anagrame. Un anagram înseamnă că un cuvânt poate fi format prin rearanjarea literelor celuilalt cuvânt. De exemplu, "listen" și "silent" sunt anagrame - ambele conțin exact aceleași litere, doar că sunt aranjate diferit.

## Ce Este un String?

Un string (șir de caractere) este o secvență de litere, cifre sau alte simboluri. De exemplu, "abc" este un string format din trei caractere: 'a', 'b' și 'c'. În Java, un string este ca o listă de caractere care nu se poate modifica după ce este creat.

## Ce Înseamnă Să Fie Anagrame?

Două cuvinte sunt anagrame dacă:
1. Au exact aceeași lungime
2. Conțin exact aceleași litere
3. Fiecare literă apare de același număr de ori în ambele cuvinte

De exemplu:
- "listen" și "silent" - ambele au 6 litere, ambele conțin: l, i, s, t, e, n (fiecare o dată)
- "abc" și "cba" - ambele au 3 litere, ambele conțin: a, b, c (fiecare o dată)
- "aab" și "abb" - NU sunt anagrame, deși au aceeași lungime, prima are două 'a' și un 'b', a doua are un 'a' și două 'b'

## Cum Funcționează Soluția?

Soluția noastră numără de câte ori apare fiecare literă în primul cuvânt, apoi verifică dacă al doilea cuvânt are exact aceleași litere cu aceleași frecvențe.

Strategia este:
1. Dacă cuvintele au lungimi diferite, nu pot fi anagrame (returnăm `false` imediat)
2. Numărăm de câte ori apare fiecare literă în primul cuvânt
3. Pentru fiecare literă din al doilea cuvânt, scădem din numărătoare
4. Dacă toate numărătorile ajung la zero, cuvintele sunt anagrame
5. Dacă vreun numărător devine negativ, al doilea cuvânt are mai multe litere decât primul, deci nu sunt anagrame

## Ce Este un Array de Frecvențe?

Un array de frecvențe este un array unde fiecare poziție reprezintă o literă și valoarea din acea poziție reprezintă de câte ori am văzut acea literă.

Pentru literele mici din alfabetul englez (a-z), avem 26 de litere. Putem crea un array cu 26 de poziții:
- Poziția 0 reprezintă litera 'a'
- Poziția 1 reprezintă litera 'b'
- Poziția 2 reprezintă litera 'c'
- ...
- Poziția 25 reprezintă litera 'z'

Cum știm care poziție corespunde cărei litere? Folosim faptul că în Java, caracterele sunt reprezentate ca numere. Litera 'a' are valoarea 97, 'b' are 98, etc. Dacă scădem 'a' dintr-o literă, obținem poziția ei în array:
- 'a' - 'a' = 0
- 'b' - 'a' = 1
- 'c' - 'a' = 2
- ...
- 'z' - 'a' = 25

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public boolean isAnagram(String s, String t) {
```

Această linie definește o funcție numită `isAnagram`. Funcția primește două parametri:
- `s` - primul string (cuvânt)
- `t` - al doilea string (cuvânt)

Funcția returnează `boolean` - adică `true` dacă sunt anagrame, sau `false` dacă nu sunt.

```java
    if (s.length() != t.length()) {
        return false;
    }
```

Această verificare este foarte importantă. Verificăm dacă cele două cuvinte au lungimi diferite.

- `s.length()` returnează numărul de caractere din primul string
- `t.length()` returnează numărul de caractere din al doilea string
- `!=` înseamnă "diferit de" sau "nu este egal cu"
- Dacă lungimile sunt diferite, cuvintele nu pot fi anagrame, deci returnăm `false` imediat

De ce facem asta? Pentru că dacă un cuvânt are mai multe litere decât celălalt, nu pot conține exact aceleași litere. De exemplu, "abc" (3 litere) și "abcd" (4 litere) nu pot fi anagrame.

```java
    int[] freq = new int[26];
```

Această linie creează un array cu 26 de poziții, toate inițializate cu 0. Să explicăm:

- `int[]` înseamnă că creăm un array de numere întregi
- `freq` este numele variabilei - am ales acest nume pentru că va stoca frecvențele (de câte ori apare fiecare literă)
- `new int[26]` creează un array nou cu 26 de poziții, toate setate la 0

Array-ul nostru va arăta astfel inițial:
```
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
```

Fiecare poziție reprezintă o literă din alfabet.

```java
    for (char c : s.toCharArray()) {
        freq[c - 'a']++;
    }
```

Această buclă parcurge fiecare caracter din primul string și numără de câte ori apare fiecare literă. Să explicăm pas cu pas:

- `s.toCharArray()` convertește string-ul `s` într-un array de caractere. De exemplu, dacă `s = "listen"`, obținem `['l', 'i', 's', 't', 'e', 'n']`
- `for (char c : ...)` este o buclă care parcurge fiecare caracter din array
- `c` este variabila care stochează caracterul curent
- `freq[c - 'a']` calculează poziția în array pentru litera curentă:
  - Dacă `c = 'a'`, atunci `'a' - 'a' = 0`, deci accesăm `freq[0]`
  - Dacă `c = 'b'`, atunci `'b' - 'a' = 1`, deci accesăm `freq[1]`
  - Dacă `c = 'l'`, atunci `'l' - 'a' = 11`, deci accesăm `freq[11]`
- `++` înseamnă "mărește cu 1" - adică incrementăm numărătorul pentru acea literă

Exemplu pentru `s = "listen"`:
- 'l' → `freq[11]++` → `freq[11]` devine 1
- 'i' → `freq[8]++` → `freq[8]` devine 1
- 's' → `freq[18]++` → `freq[18]` devine 1
- 't' → `freq[19]++` → `freq[19]` devine 1
- 'e' → `freq[4]++` → `freq[4]` devine 1
- 'n' → `freq[13]++` → `freq[13]` devine 1

După această buclă, array-ul `freq` arată astfel (doar pozițiile cu valori nenule):
- `freq[4] = 1` (e apare 1 dată)
- `freq[8] = 1` (i apare 1 dată)
- `freq[11] = 1` (l apare 1 dată)
- `freq[13] = 1` (n apare 1 dată)
- `freq[18] = 1` (s apare 1 dată)
- `freq[19] = 1` (t apare 1 dată)

```java
    for (char c : t.toCharArray()) {
        freq[c - 'a']--;
        
        if (freq[c - 'a'] < 0) {
            return false;
        }
    }
```

Această buclă parcurge fiecare caracter din al doilea string și scade din numărătoare. Să explicăm:

- `t.toCharArray()` convertește al doilea string într-un array de caractere
- `for (char c : ...)` parcurge fiecare caracter
- `freq[c - 'a']--` scade 1 din numărătorul pentru litera curentă:
  - `--` înseamnă "scade cu 1" - adică decrementăm numărătorul
- `if (freq[c - 'a'] < 0)` verifică dacă numărătorul a devenit negativ

De ce verificăm dacă devine negativ? Dacă numărătorul devine negativ, înseamnă că în al doilea string am văzut o literă de mai multe ori decât în primul string. De exemplu, dacă primul string are un 'a' și al doilea are două 'a', când scădem a doua oară, numărătorul devine -1, ceea ce înseamnă că nu sunt anagrame.

Exemplu pentru `t = "silent"`:
- 's' → `freq[18]--` → `freq[18]` devine 0 (era 1, scădem 1)
- 'i' → `freq[8]--` → `freq[8]` devine 0
- 'l' → `freq[11]--` → `freq[11]` devine 0
- 'e' → `freq[4]--` → `freq[4]` devine 0
- 'n' → `freq[13]--` → `freq[13]` devine 0
- 't' → `freq[19]--` → `freq[19]` devine 0

Toate numărătoarele au ajuns la 0, și niciuna nu a devenit negativă, deci cuvintele sunt anagrame!

```java
    return true;
```

Dacă am ajuns aici, înseamnă că:
1. Cuvintele au aceeași lungime
2. Am parcurs toate literele din al doilea string
3. Niciun numărător nu a devenit negativ
4. Toate numărătoarele sunt 0 (sau ar trebui să fie)

În acest caz, returnăm `true` pentru a indica că cuvintele sunt anagrame.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `s = "listen"` și `t = "silent"`:

**Pasul 1: Verificare lungime**
- `s.length() = 6`
- `t.length() = 6`
- Lungimile sunt egale, continuăm

**Pasul 2: Inițializare**
- `freq = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]`

**Pasul 3: Numărăm literele din "listen"**
- 'l' → `freq[11] = 1`
- 'i' → `freq[8] = 1`
- 's' → `freq[18] = 1`
- 't' → `freq[19] = 1`
- 'e' → `freq[4] = 1`
- 'n' → `freq[13] = 1`

**Pasul 4: Scădem literele din "silent"**
- 's' → `freq[18] = 0` (era 1, scădem 1)
- 'i' → `freq[8] = 0`
- 'l' → `freq[11] = 0`
- 'e' → `freq[4] = 0`
- 'n' → `freq[13] = 0`
- 't' → `freq[19] = 0`

**Pasul 5: Rezultat**
- Toate numărătoarele sunt 0
- Niciuna nu a devenit negativă
- Returnăm `true`

## Alt Exemplu - Nu Sunt Anagrame

Să urmărim pentru `s = "rat"` și `t = "car"`:

**Pasul 1: Verificare lungime**
- Ambele au 3 litere, continuăm

**Pasul 2: Numărăm "rat"**
- 'r' → `freq[17] = 1`
- 'a' → `freq[0] = 1`
- 't' → `freq[19] = 1`

**Pasul 3: Scădem "car"**
- 'c' → `freq[2]--` → `freq[2] = -1` (era 0, scădem 1)
- `freq[2] < 0` → returnăm `false` imediat!

De ce? Pentru că 'c' nu apare în "rat", deci când încercăm să scădem, numărătorul devine negativ, ceea ce înseamnă că "car" are o literă pe care "rat" nu o are.

## De Ce Este Această Soluție Eficientă?

1. **Verificăm lungimea primul lucru**: Dacă lungimile diferă, nu mai continuăm, economisind timp.

2. **Folosim un array fix**: Array-ul are întotdeauna 26 de poziții, indiferent de lungimea cuvintelor, deci folosim spațiu constant.

3. **Oprim imediat când găsim o problemă**: Dacă un numărător devine negativ, știm imediat că nu sunt anagrame și ne oprim.

4. **Parcurgem fiecare string o singură dată**: Nu comparăm fiecare literă cu fiecare altă literă, ci doar numărăm și verificăm.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este lungimea string-urilor. Parcurgem fiecare string o singură dată.

- **Complexitatea spațiului**: O(1) - folosim un array fix de 26 de poziții, indiferent de lungimea string-urilor.

## Concluzie

Această soluție este elegantă și eficientă. Folosim un array de frecvențe pentru a număra literele din primul string, apoi scădem pentru al doilea string. Dacă toate numărătoarele ajung la zero și niciuna nu devine negativă, string-urile sunt anagrame.
