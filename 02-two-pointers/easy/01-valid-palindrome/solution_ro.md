# Soluție Detaliată - Valid Palindrome

## Ce Ne Cere Problema?

Problema ne cere să verificăm dacă un string (șir de caractere) este un palindrom. Un palindrom este un cuvânt sau frază care se citește la fel de la stânga la dreapta și de la dreapta la stânga, după ce eliminăm caracterele care nu sunt litere sau cifre și convertim toate literele la litere mici.

De exemplu:
- "A man, a plan, a canal: Panama" devine "amanaplanacanalpanama" după curățare, care este un palindrom.
- "race a car" devine "raceacar", care nu este un palindrom.

## Ce Este un Palindrom?

Un palindrom este un cuvânt sau frază care rămâne aceeași când o citești invers. De exemplu:
- "aba" citit invers este tot "aba"
- "racecar" citit invers este tot "racecar"
- "A man a plan a canal Panama" (după curățare) este un palindrom

## Ce Înseamnă "Alphanumeric"?

"Alphanumeric" înseamnă caractere care sunt fie litere (a-z, A-Z) fie cifre (0-9). Caracterele precum spații, virgule, două puncte, etc. nu sunt alphanumerice și trebuie ignorate.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Folosim doi pointeri - unul de la început (stânga) și unul de la sfârșit (dreapta)
2. Mutăm pointerii până găsim caractere alphanumerice
3. Comparăm caracterele (convertite la litere mici)
4. Dacă nu se potrivesc, returnăm `false`
5. Dacă se potrivesc, mutăm pointerii către centru și continuăm
6. Dacă pointerii se întâlnesc, returnăm `true`

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public boolean isPalindrome(String s) {
```

Această linie definește funcția `isPalindrome`. Funcția primește:
- `s` - string-ul de verificat

Funcția returnează `boolean` - `true` dacă este palindrom, `false` dacă nu este.

```java
    if (s == null || s.length() == 0) {
        return true;
    }
```

Această verificare tratează cazul când string-ul este null sau gol. Un string gol este considerat palindrom (se citește la fel în ambele direcții - nu există nimic de citit).

```java
    int left = 0;
    int right = s.length() - 1;
```

Aceste linii inițializează cei doi pointeri. Să explicăm:

- `left = 0` - pointer-ul de la stânga, începe de la primul caracter (poziția 0)
- `right = s.length() - 1` - pointer-ul de la dreapta, începe de la ultimul caracter

De exemplu, dacă string-ul are 10 caractere (indicele 0-9), atunci `right = 9`.

```java
    while (left < right) {
```

Această buclă continuă cât timp pointerii nu s-au întâlnit. Să explicăm:

- `left < right` înseamnă că pointerii nu s-au întâlnit încă
- Când `left >= right`, înseamnă că am comparat toate caracterele necesare

```java
        while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
            left++;
        }
```

Această buclă mută pointer-ul de la stânga până găsește un caracter alphanumeric. Să explicăm:

- `Character.isLetterOrDigit(s.charAt(left))` verifică dacă caracterul de la poziția `left` este o literă sau o cifră
- `!` înseamnă "nu" - deci verificăm dacă caracterul NU este alphanumeric
- Dacă caracterul nu este alphanumeric, `left++` mută pointer-ul cu o poziție la dreapta
- Bucla continuă până găsim un caracter alphanumeric sau până când pointerii se întâlnesc

De exemplu, dacă avem "A, B", pointer-ul `left` va sări peste 'A' (este alphanumeric, deci oprește), dar dacă avem ", A", pointer-ul va sări peste virgula și spațiul până ajunge la 'A'.

```java
        while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
            right--;
        }
```

Această buclă mută pointer-ul de la dreapta până găsește un caracter alphanumeric. Funcționează similar cu bucla anterioară, dar mută pointer-ul `right` către stânga (`right--`).

```java
        char leftChar = Character.toLowerCase(s.charAt(left));
        char rightChar = Character.toLowerCase(s.charAt(right));
```

Aceste linii extrag caracterele de la ambele pointeri și le convertesc la litere mici. Să explicăm:

- `s.charAt(left)` extrage caracterul de la poziția `left`
- `Character.toLowerCase()` convertește caracterul la literă mică
- Facem asta pentru a compara 'A' cu 'a' ca fiind același caracter

De exemplu, 'A' devine 'a', 'B' devine 'b', '1' rămâne '1' (cifrele nu se schimbă).

```java
        if (leftChar != rightChar) {
            return false;
        }
```

Această condiție verifică dacă caracterele nu se potrivesc. Dacă nu se potrivesc, string-ul nu este palindrom, deci returnăm `false` imediat.

```java
        left++;
        right--;
```

Aceste linii mută ambele pointeri către centru. După ce am comparat caracterele și se potrivesc, mutăm pointerii pentru a compara următoarele caractere.

```java
    return true;
```

Această linie se execută doar dacă am terminat de comparat toate caracterele și toate s-au potrivit. În acest caz, string-ul este palindrom, deci returnăm `true`.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `s = "A man, a plan, a canal: Panama"`:

**Inițializare:**
- `left = 0`, `right = 29` (lungimea string-ului minus 1)

**Iterația 1:**
- `left` la 'A' (alphanumeric) → `leftChar = 'a'`
- `right` la 'a' (alphanumeric) → `rightChar = 'a'`
- Comparăm: `'a' == 'a'` → `true` → continuăm
- `left = 1`, `right = 28`

**Iterația 2:**
- `left` la ' ' (spațiu, nu este alphanumeric) → sărim peste → `left = 2` (la 'm')
- `right` la 'm' (alphanumeric) → `rightChar = 'm'`
- `leftChar = 'm'`, `rightChar = 'm'`
- Comparăm: `'m' == 'm'` → `true` → continuăm
- `left = 3`, `right = 27`

Continuăm astfel până când pointerii se întâlnesc. Dacă toate caracterele se potrivesc, returnăm `true`.

## Alt Exemplu - Nu Este Palindrom

Să urmărim pentru `s = "race a car"`:

**După curățare:** "raceacar"

**Comparări:**
- 'r' == 'r' → continuă
- 'a' == 'a' → continuă
- 'c' == 'c' → continuă
- 'e' == 'a' → `false` → returnăm `false`

**Rezultat:** `false` - nu este palindrom.

## De Ce Este Această Soluție Eficientă?

1. **Parcurgem string-ul o singură dată**: Nu creăm un string nou curățat, ci sărim peste caracterele non-alphanumerice direct.

2. **Oprim imediat când găsim o nepotrivire**: Dacă două caractere nu se potrivesc, nu mai continuăm să verificăm.

3. **Folosim spațiu constant**: Nu creăm structuri de date suplimentare, ci doar folosim doi pointeri.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este lungimea string-ului. Parcurgem string-ul o singură dată.

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile pentru pointeri, nu creăm structuri de date suplimentare.

## Concluzie

Această soluție este elegantă și eficientă. Folosim doi pointeri pentru a compara caracterele de la ambele capete, sărind peste caracterele non-alphanumerice și convertind la litere mici pentru comparație.
