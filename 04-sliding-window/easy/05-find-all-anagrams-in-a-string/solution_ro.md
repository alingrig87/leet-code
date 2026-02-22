# Soluție Detaliată - Find All Anagrams in a String

## Ce Ne Cere Problema?

Problema ne cere să găsim toate pozițiile de început ale anagramelor unui string `p` într-un string `s`. Un anagram este un string care conține aceleași caractere cu aceeași frecvență, dar în altă ordine.

De exemplu, dacă avem `s = "cbaebabacd"` și `p = "abc"`, anagramele lui "abc" sunt "abc", "bca", "cab", etc. În `s`, găsim anagramele la pozițiile 0 ("cba") și 6 ("bac").

## Ce Este un Anagram?

Un anagram este un string care conține exact aceleași caractere cu aceeași frecvență ca un alt string, dar în altă ordine. De exemplu, "abc" și "bca" sunt anagrame.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Calculăm frecvența caracterelor din `p`
2. Folosim sliding window de lungime `p.length()` pentru a parcurge `s`
3. Calculăm frecvența caracterelor din fereastra curentă
4. Dacă frecvențele se potrivesc, fereastra este un anagram
5. Actualizăm fereastra eliminând caracterul de la stânga și adăugând caracterul de la dreapta

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public List<Integer> findAnagrams(String s, String p) {
```

Această linie definește funcția `findAnagrams`. Funcția primește:
- `s` - string-ul în care căutăm
- `p` - string-ul pentru care căutăm anagrame

Funcția returnează `List<Integer>` - lista cu pozițiile de început ale anagramelor.

```java
    List<Integer> result = new ArrayList<>();
```

Această linie creează lista rezultatelor. Aici vom stoca pozițiile de început ale anagramelor găsite.

```java
    if (s.length() < p.length()) {
        return result;
    }
```

Această verificare tratează cazul când `s` este mai scurt decât `p`. Dacă `s` este mai scurt, nu pot exista anagrame, deci returnăm lista goală.

```java
    int[] pFreq = new int[26];
    for (char c : p.toCharArray()) {
        pFreq[c - 'a']++;
    }
```

Aceste linii calculează frecvența caracterelor din `p`. Să explicăm:

- `int[] pFreq = new int[26]` creează un array pentru a stoca frecvența fiecărei litere (a-z)
- `c - 'a'` convertește caracterul la un index (0 pentru 'a', 1 pentru 'b', etc.)
- `pFreq[c - 'a']++` incrementează frecvența caracterului

De exemplu, dacă `p = "abc"`:
- `pFreq[0] = 1` (pentru 'a')
- `pFreq[1] = 1` (pentru 'b')
- `pFreq[2] = 1` (pentru 'c')

```java
    int[] windowFreq = new int[26];
```

Această linie creează array-ul pentru frecvența caracterelor din fereastra curentă.

```java
    for (int i = 0; i < p.length(); i++) {
        windowFreq[s.charAt(i) - 'a']++;
    }
```

Aceste linii inițializează fereastra cu primele `p.length()` caractere din `s`. Calculăm frecvența caracterelor din prima fereastră.

```java
    if (Arrays.equals(pFreq, windowFreq)) {
        result.add(0);
    }
```

Această condiție verifică dacă prima fereastră este un anagram. Dacă frecvențele se potrivesc, adăugăm poziția 0 în rezultat.

```java
    for (int i = p.length(); i < s.length(); i++) {
```

Această buclă "alunecă" fereastra prin restul string-ului `s`. `i` este poziția noului caracter care intră în fereastră.

```java
        windowFreq[s.charAt(i - p.length()) - 'a']--;
```

Această linie elimină caracterul care iese din fereastră. Să explicăm:

- `i - p.length()` este poziția caracterului care iese din fereastră
- `s.charAt(i - p.length())` este caracterul care iese
- `windowFreq[...]--` decrementează frecvența acestui caracter

```java
        windowFreq[s.charAt(i) - 'a']++;
```

Această linie adaugă caracterul care intră în fereastră. `s.charAt(i)` este noul caracter, iar `windowFreq[...]++` incrementează frecvența sa.

```java
        if (Arrays.equals(pFreq, windowFreq)) {
            result.add(i - p.length() + 1);
        }
```

Această condiție verifică dacă fereastra curentă este un anagram. Dacă frecvențele se potrivesc, adăugăm poziția de început a ferestrei (`i - p.length() + 1`) în rezultat.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `s = "cbaebabacd"` și `p = "abc"`:

**Inițializare:**
- `pFreq = [1, 1, 1, 0, ...]` (a=1, b=1, c=1)
- `windowFreq = [0, 0, 0, ...]`

**Inițializare fereastră (primele 3 caractere):**
- `s[0] = 'c'` → `windowFreq[2]++` → `windowFreq[2] = 1`
- `s[1] = 'b'` → `windowFreq[1]++` → `windowFreq[1] = 1`
- `s[2] = 'a'` → `windowFreq[0]++` → `windowFreq[0] = 1`
- `windowFreq = [1, 1, 1, 0, ...]`
- `Arrays.equals(pFreq, windowFreq)`? `true`!
- `result.add(0)`

**Iterația 1 (i = 3):**
- Eliminăm `s[0] = 'c'` → `windowFreq[2]--` → `windowFreq[2] = 0`
- Adăugăm `s[3] = 'e'` → `windowFreq[4]++` → `windowFreq[4] = 1`
- `windowFreq = [1, 1, 0, 0, 1, ...]`
- `Arrays.equals(pFreq, windowFreq)`? `false`

**Iterația 2 (i = 4):**
- Eliminăm `s[1] = 'b'` → `windowFreq[1]--` → `windowFreq[1] = 0`
- Adăugăm `s[4] = 'b'` → `windowFreq[1]++` → `windowFreq[1] = 1`
- `windowFreq = [1, 1, 0, 0, 1, ...]`
- `Arrays.equals(pFreq, windowFreq)`? `false`

**Iterația 3 (i = 5):**
- Eliminăm `s[2] = 'a'` → `windowFreq[0]--` → `windowFreq[0] = 0`
- Adăugăm `s[5] = 'a'` → `windowFreq[0]++` → `windowFreq[0] = 1`
- `windowFreq = [1, 1, 0, 0, 1, ...]`
- `Arrays.equals(pFreq, windowFreq)`? `false`

**Iterația 4 (i = 6):**
- Eliminăm `s[3] = 'e'` → `windowFreq[4]--` → `windowFreq[4] = 0`
- Adăugăm `s[6] = 'b'` → `windowFreq[1]++` → `windowFreq[1] = 2`
- `windowFreq = [1, 2, 0, ...]`
- `Arrays.equals(pFreq, windowFreq)`? `false`

**Iterația 5 (i = 7):**
- Eliminăm `s[4] = 'b'` → `windowFreq[1]--` → `windowFreq[1] = 1`
- Adăugăm `s[7] = 'a'` → `windowFreq[0]++` → `windowFreq[0] = 2`
- `windowFreq = [2, 1, 0, ...]`
- `Arrays.equals(pFreq, windowFreq)`? `false`

**Iterația 6 (i = 8):**
- Eliminăm `s[5] = 'a'` → `windowFreq[0]--` → `windowFreq[0] = 1`
- Adăugăm `s[8] = 'c'` → `windowFreq[2]++` → `windowFreq[2] = 1`
- `windowFreq = [1, 1, 1, 0, ...]`
- `Arrays.equals(pFreq, windowFreq)`? `true`!
- `result.add(6)` (poziția de început: 8 - 3 + 1 = 6)

**Rezultat:** `[0, 6]` - anagramele se găsesc la pozițiile 0 și 6.

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Parcurgem string-ul `s` o singură dată, făcând O(1) operații pentru fiecare caracter.

2. **O(1) spațiu**: Array-urile de frecvență au dimensiune fixă (26), deci spațiul este constant.

3. **Sliding window eficient**: Actualizăm frecvența eliminând un caracter și adăugând altul, fără să recalculăm întreaga fereastră.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este lungimea string-ului `s`. Parcurgem string-ul o singură dată.

- **Complexitatea spațiului**: O(1) - array-urile de frecvență au dimensiune fixă (26), deci spațiul este constant.

## Concluzie

Această soluție este elegantă și eficientă. Folosim sliding window cu array-uri de frecvență pentru a găsi rapid anagramele, actualizând frecvența eficient când fereastra "alunecă" prin string, obținând astfel toate pozițiile de început ale anagramelor într-o singură parcurgere.
