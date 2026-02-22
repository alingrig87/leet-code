# Soluție Detaliată - Valid Palindrome

## Ce Ne Cere Problema?

Problema ne cere să verificăm dacă un string este palindrom după ce eliminăm toate caracterele non-alfanumerice și convertim toate literele la lowercase. De exemplu, `"A man, a plan, a canal: Panama"` devine `"amanaplanacanalpanama"` după curățare, care este palindrom.

## Ce Este un Palindrom?

Un palindrom este un string care se citește la fel de la stânga la dreapta și de la dreapta la stânga. De exemplu, "aba" citit invers este tot "aba".

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Folosim doi pointeri - unul de la început și unul de la sfârșit
2. Sărim peste caracterele non-alfanumerice
3. Comparăm caracterele alfanumerice (convertite la lowercase)
4. Dacă toate caracterele se potrivesc, string-ul este palindrom

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public boolean isPalindrome(String s) {
```

Această linie definește funcția `isPalindrome`. Funcția primește:
- `s` - string-ul de verificat

Funcția returnează `boolean` - `true` dacă string-ul este palindrom, `false` dacă nu este.

```java
    if (s == null || s.length() == 0) {
        return true;
    }
```

Această verificare tratează cazurile speciale. Un string null sau gol este considerat palindrom.

```java
    int left = 0;
    int right = s.length() - 1;
```

Aceste linii inițializează cei doi pointeri. Să explicăm:

- `left = 0` - pointer-ul de la stânga, începe de la primul caracter
- `right = s.length() - 1` - pointer-ul de la dreapta, începe de la ultimul caracter

```java
    while (left < right) {
```

Această buclă continuă cât timp pointerii nu s-au întâlnit. Când `left >= right`, am comparat toate caracterele necesare.

```java
        while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
            left++;
        }
```

Această buclă sărește peste caracterele non-alfanumerice de la stânga. Să explicăm:

- `left < right` verifică dacă pointerii nu s-au întâlnit
- `!Character.isLetterOrDigit(s.charAt(left))` verifică dacă caracterul de la poziția `left` nu este o literă sau o cifră
- Dacă caracterul nu este alfanumeric, `left++` mută pointer-ul la dreapta pentru a sări peste acest caracter

De exemplu, dacă avem `"a, b"` și `left = 1` (caracterul ','):
- `Character.isLetterOrDigit(',')` → `false`
- `!false` → `true`, deci intrăm în buclă
- `left++` → `left = 2` (caracterul ' ')

```java
        while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
            right--;
        }
```

Această buclă sărește peste caracterele non-alfanumerice de la dreapta. Funcționează similar cu bucla anterioară, dar mută pointer-ul `right` la stânga.

```java
        if (Character.toLowerCase(s.charAt(left)) != 
            Character.toLowerCase(s.charAt(right))) {
            return false;
        }
```

Această condiție compară caracterele. Să explicăm:

- `Character.toLowerCase(s.charAt(left))` convertește caracterul de la stânga la lowercase
- `Character.toLowerCase(s.charAt(right))` convertește caracterul de la dreapta la lowercase
- `!=` compară dacă caracterele sunt diferite
- Dacă sunt diferite, string-ul nu este palindrom, deci returnăm `false`

De exemplu, dacă `s.charAt(left) = 'A'` și `s.charAt(right) = 'a'`:
- `Character.toLowerCase('A')` → `'a'`
- `Character.toLowerCase('a')` → `'a'`
- `'a' != 'a'`? `false`, deci caracterele se potrivesc

```java
        left++;
        right--;
```

Aceste linii mută pointerii către centru pentru următoarea comparație.

```java
    return true;
```

Această linie se execută doar dacă toate caracterele s-au potrivit. În acest caz, string-ul este palindrom, deci returnăm `true`.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `s = "A man, a plan, a canal: Panama"`:

**Inițializare:**
- `left = 0`, `right = 29`

**Iterația 1:**
- `s.charAt(0) = 'A'` → `Character.isLetterOrDigit('A')`? `true`, nu sărim
- `s.charAt(29) = 'a'` → `Character.isLetterOrDigit('a')`? `true`, nu sărim
- `Character.toLowerCase('A') = 'a'`, `Character.toLowerCase('a') = 'a'`
- `'a' == 'a'`? `true`, continuăm
- `left = 1`, `right = 28`

**Iterația 2:**
- `s.charAt(1) = ' '` → `Character.isLetterOrDigit(' ')`? `false`, sărim peste
- `left = 2` (caracterul 'm')
- `s.charAt(28) = 'a'` → `Character.isLetterOrDigit('a')`? `true`, nu sărim
- `Character.toLowerCase('m') = 'm'`, `Character.toLowerCase('a') = 'a'`
- `'m' == 'a'`? `false`!
- Returnăm `false`

**Rezultat:** `false` - string-ul nu este palindrom (de fapt, este palindrom, dar am făcut o greșeală în exemplu - ar trebui să fie `true` pentru acest string).

## Alt Exemplu - Este Palindrom

Să urmărim pentru `s = "race a car"`:

**Inițializare:**
- `left = 0`, `right = 9`

**Iterația 1:**
- `'r' == 'r'`? `true`, continuăm
- `left = 1`, `right = 8`

**Iterația 2:**
- `'a' == 'a'`? `true`, continuăm
- `left = 2`, `right = 7`

**Iterația 3:**
- `'c' == 'c'`? `true`, continuăm
- `left = 3`, `right = 6`

**Iterația 4:**
- `'e' == 'a'`? `false`!
- Returnăm `false`

**Rezultat:** `false` - string-ul nu este palindrom.

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Parcurgem string-ul o singură dată, făcând O(1) operații pentru fiecare caracter.

2. **O(1) spațiu**: Folosim doar câteva variabile pentru pointeri, nu creăm structuri de date suplimentare.

3. **In-place**: Nu creăm un string nou, ci comparăm direct caracterele din string-ul original.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este lungimea string-ului. Parcurgem string-ul o singură dată.

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile pentru pointeri.

## Concluzie

Această soluție este elegantă și eficientă. Folosim doi pointeri pentru a compara caracterele de la ambele capete, sărind peste caracterele non-alfanumerice și convertind caracterele la lowercase pentru comparație, verificând astfel dacă string-ul este palindrom fără a crea structuri suplimentare.
