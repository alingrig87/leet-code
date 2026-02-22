# Soluție Detaliată - Isomorphic Strings

## Ce Ne Cere Problema?

Problema ne cere să verificăm dacă două string-uri sunt "izomorfe" (isomorphic). Două string-uri sunt izomorfe dacă putem înlocui fiecare caracter din primul string cu un caracter din al doilea string astfel încât să obținem al doilea string, și invers.

De exemplu:
- `"egg"` și `"add"` sunt izomorfe: e→a, g→d
- `"foo"` și `"bar"` nu sunt izomorfe: f→b, o→a, dar o apare din nou și ar trebui să fie 'r', dar 'a' != 'r'

## Ce Înseamnă "Isomorphic"?

Două string-uri sunt izomorfe dacă există o corespondență unu-la-unu între caracterele lor. Fiecare caracter din primul string corespunde exact unui caracter din al doilea string, și invers.

## De Ce Avem Nevoie de Două Hash Maps?

Avem nevoie de două hash maps pentru a verifica mapping-ul în ambele direcții:
- `sToT`: mapează caracterele din `s` la caracterele din `t`
- `tToS`: mapează caracterele din `t` la caracterele din `s`

De ce? Pentru că trebuie să verificăm că:
1. Fiecare caracter din `s` mapează la exact un caracter din `t` (nu la mai multe)
2. Fiecare caracter din `t` mapează la exact un caracter din `s` (nu la mai multe)

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Verificăm dacă string-urile au aceeași lungime
2. Pentru fiecare poziție, verificăm mapping-ul din `s` în `t` și invers
3. Dacă găsim un conflict (un caracter mapează la două caractere diferite), string-urile nu sunt izomorfe
4. Dacă toate mapping-urile sunt consistente, string-urile sunt izomorfe

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public boolean isIsomorphic(String s, String t) {
```

Această linie definește funcția `isIsomorphic`. Funcția primește:
- `s` - primul string
- `t` - al doilea string

Funcția returnează `boolean` - `true` dacă string-urile sunt izomorfe, `false` dacă nu sunt.

```java
    if (s.length() != t.length()) {
        return false;
    }
```

Această verificare tratează cazul când string-urile au lungimi diferite. Dacă au lungimi diferite, nu pot fi izomorfe (nu putem mapa fiecare caracter din unul la un caracter din celălalt).

```java
    Map<Character, Character> sToT = new HashMap<>();
    Map<Character, Character> tToS = new HashMap<>();
```

Aceste linii creează cele două hash maps. Să explicăm:

- `sToT` mapează caracterele din `s` la caracterele din `t`
- `tToS` mapează caracterele din `t` la caracterele din `s`

```java
    for (int i = 0; i < s.length(); i++) {
```

Această buclă parcurge fiecare poziție din string-uri. `i` este poziția curentă.

```java
        char sChar = s.charAt(i);
        char tChar = t.charAt(i);
```

Aceste linii extrag caracterele de la poziția curentă. Să explicăm:

- `sChar` este caracterul de la poziția `i` din `s`
- `tChar` este caracterul de la poziția `i` din `t`

```java
        if (sToT.containsKey(sChar)) {
            if (sToT.get(sChar) != tChar) {
                return false;
            }
        } else {
            sToT.put(sChar, tChar);
        }
```

Această parte verifică mapping-ul din `s` în `t`. Să explicăm:

- `sToT.containsKey(sChar)` verifică dacă caracterul `sChar` a fost deja mapat
- Dacă da, `sToT.get(sChar)` obține caracterul la care este mapat
- `sToT.get(sChar) != tChar` verifică dacă mapping-ul existent este diferit de mapping-ul curent
- Dacă da, avem un conflict (același caracter din `s` mapează la două caractere diferite din `t`), deci returnăm `false`
- Dacă `sChar` nu a fost mapat înainte, `sToT.put(sChar, tChar)` creează noul mapping

De exemplu, dacă `s = "egg"` și `t = "add"`:
- `i = 0`: `sChar = 'e'`, `tChar = 'a'` → `sToT.put('e', 'a')`
- `i = 1`: `sChar = 'g'`, `tChar = 'd'` → `sToT.put('g', 'd')`
- `i = 2`: `sChar = 'g'`, `tChar = 'd'` → `sToT.containsKey('g')`? `true`, `sToT.get('g') = 'd' == 'd'`? `true`, continuăm

```java
        if (tToS.containsKey(tChar)) {
            if (tToS.get(tChar) != sChar) {
                return false;
            }
        } else {
            tToS.put(tChar, sChar);
        }
```

Această parte verifică mapping-ul din `t` în `s`. Funcționează similar cu partea anterioară, dar verifică inversul: dacă un caracter din `t` mapează la două caractere diferite din `s`.

De exemplu, dacă `s = "foo"` și `t = "bar"`:
- `i = 0`: `sChar = 'f'`, `tChar = 'b'` → `sToT.put('f', 'b')`, `tToS.put('b', 'f')`
- `i = 1`: `sChar = 'o'`, `tChar = 'a'` → `sToT.put('o', 'a')`, `tToS.put('a', 'o')`
- `i = 2`: `sChar = 'o'`, `tChar = 'r'` → `sToT.containsKey('o')`? `true`, `sToT.get('o') = 'a' != 'r'`? `true`!
- Returnăm `false`

```java
    return true;
```

Această linie se execută doar dacă toate mapping-urile sunt consistente. În acest caz, string-urile sunt izomorfe, deci returnăm `true`.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `s = "egg"` și `t = "add"`:

**Inițializare:**
- `sToT = {}`, `tToS = {}`

**Iterația 1 (i = 0):**
- `sChar = 'e'`, `tChar = 'a'`
- `sToT.containsKey('e')`? `false` → `sToT.put('e', 'a')`
- `tToS.containsKey('a')`? `false` → `tToS.put('a', 'e')`
- `sToT = {'e': 'a'}`, `tToS = {'a': 'e'}`

**Iterația 2 (i = 1):**
- `sChar = 'g'`, `tChar = 'd'`
- `sToT.containsKey('g')`? `false` → `sToT.put('g', 'd')`
- `tToS.containsKey('d')`? `false` → `tToS.put('d', 'g')`
- `sToT = {'e': 'a', 'g': 'd'}`, `tToS = {'a': 'e', 'd': 'g'}`

**Iterația 3 (i = 2):**
- `sChar = 'g'`, `tChar = 'd'`
- `sToT.containsKey('g')`? `true` → `sToT.get('g') = 'd' == 'd'`? `true`, continuăm
- `tToS.containsKey('d')`? `true` → `tToS.get('d') = 'g' == 'g'`? `true`, continuăm

**După toate iterațiile:**
- Toate mapping-urile sunt consistente
- Returnăm `true`

**Rezultat:** `true` - string-urile sunt izomorfe.

## Alt Exemplu - Nu Sunt Izomorfe

Să urmărim pentru `s = "foo"` și `t = "bar"`:

**Iterația 1 (i = 0):**
- `sChar = 'f'`, `tChar = 'b'` → `sToT.put('f', 'b')`, `tToS.put('b', 'f')`

**Iterația 2 (i = 1):**
- `sChar = 'o'`, `tChar = 'a'` → `sToT.put('o', 'a')`, `tToS.put('a', 'o')`

**Iterația 3 (i = 2):**
- `sChar = 'o'`, `tChar = 'r'`
- `sToT.containsKey('o')`? `true` → `sToT.get('o') = 'a' != 'r'`? `true`!
- Returnăm `false`

**Rezultat:** `false` - string-urile nu sunt izomorfe (caracterul 'o' mapează la 'a' și 'r').

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Parcurgem string-urile o singură dată, făcând O(1) operații pentru fiecare caracter.

2. **O(1) spațiu**: Hash maps-urile conțin cel mult 256 de intrări (pentru toate caracterele ASCII), deci spațiul este constant.

3. **Verificare bidirecțională**: Verificăm mapping-ul în ambele direcții pentru a asigura consistența.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este lungimea string-urilor. Parcurgem string-urile o singură dată.

- **Complexitatea spațiului**: O(1) - hash maps-urile conțin cel mult 256 de intrări (pentru caracterele ASCII), deci spațiul este constant.

## Concluzie

Această soluție este elegantă și eficientă. Folosim două hash maps pentru a verifica mapping-ul în ambele direcții, asigurându-ne că fiecare caracter mapează la exact un caracter și că nu există conflicte, verificând astfel dacă string-urile sunt izomorfe.
