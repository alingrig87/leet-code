# Soluție Detaliată - Assign Cookies

## Ce Ne Cere Problema?

Problema ne cere să maximizăm numărul de copii mulțumiți prin atribuirea de prăjituri. Fiecare copil are un factor de lăcomie (greed factor), iar fiecare prăjitură are o dimensiune. Un copil este mulțumit dacă dimensiunea prăjiturii este >= factorul său de lăcomie.

De exemplu, dacă avem `g = [1, 2, 3]` (factorii de lăcomie) și `s = [1, 1]` (dimensiunile prăjiturilor):
- Putem atribui prăjitura de dimensiune 1 copilului cu factorul 1
- Putem atribui prăjitura de dimensiune 1 copilului cu factorul 2
- Nu putem mulțumi copilul cu factorul 3
- Rezultat: 2 copii mulțumiți

## De Ce Folosim Algoritm Greedy?

Algoritmul greedy este optim pentru această problemă. Strategia este să atribuim cea mai mică prăjitură care poate mulțumi un copil cu factorul de lăcomie cel mai mic. Astfel, lăsăm prăjiturile mai mari disponibile pentru copiii cu factori de lăcomie mai mari.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Sortăm copiii după factorul de lăcomie (crescător)
2. Sortăm prăjiturile după dimensiune (crescător)
3. Folosim doi pointeri pentru a potrivi cea mai mică prăjitură disponibilă cu cel mai mic copil nesatisfăcut
4. Dacă o prăjitură poate mulțumi un copil, o atribuim și mutăm ambele pointeri
5. Dacă o prăjitură este prea mică, trecem la următoarea prăjitură

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int findContentChildren(int[] g, int[] s) {
```

Această linie definește funcția `findContentChildren`. Funcția primește:
- `g` - array-ul cu factorii de lăcomie ai copiilor
- `s` - array-ul cu dimensiunile prăjiturilor

Funcția returnează `int` - numărul maxim de copii mulțumiți.

```java
    if (g == null || s == null || g.length == 0 || s.length == 0) {
        return 0;
    }
```

Această verificare tratează cazurile speciale. Dacă nu există copii sau prăjituri, nu putem mulțumi niciun copil, deci returnăm 0.

```java
    Arrays.sort(g);
    Arrays.sort(s);
```

Aceste linii sortează ambele array-uri. Să explicăm:

- `Arrays.sort(g)` sortează copiii după factorul de lăcomie (crescător)
- `Arrays.sort(s)` sortează prăjiturile după dimensiune (crescător)

Sortăm pentru a putea aplica strategia greedy: să atribuim cea mai mică prăjitură care poate mulțumi cel mai mic copil nesatisfăcut.

```java
    int childIndex = 0;
    int cookieIndex = 0;
    int contentCount = 0;
```

Aceste linii inițializează pointerii și contorul. Să explicăm:

- `childIndex` este pointer-ul pentru array-ul de copii
- `cookieIndex` este pointer-ul pentru array-ul de prăjituri
- `contentCount` numără câți copii sunt mulțumiți

```java
    while (childIndex < g.length && cookieIndex < s.length) {
```

Această buclă continuă cât timp mai avem copii și prăjituri de procesat.

```java
        if (s[cookieIndex] >= g[childIndex]) {
            contentCount++;
            childIndex++;
            cookieIndex++;
        }
```

Această condiție atribuie prăjitura copilului dacă poate fi mulțumit. Să explicăm:

- `s[cookieIndex] >= g[childIndex]` verifică dacă dimensiunea prăjiturii este >= factorul de lăcomie al copilului
- Dacă da, `contentCount++` incrementează numărul de copii mulțumiți
- `childIndex++` mută pointer-ul la următorul copil (copilul curent este mulțumit)
- `cookieIndex++` mută pointer-ul la următoarea prăjitură (prăjitura curentă este folosită)

De exemplu, dacă `s[cookieIndex] = 2` și `g[childIndex] = 1`:
- `2 >= 1`? `true`, atribuim prăjitura
- Copilul este mulțumit, trecem la următorul copil și următoarea prăjitură

```java
        else {
            cookieIndex++;
        }
```

Această parte trece la următoarea prăjitură dacă prăjitura curentă este prea mică. Să explicăm:

- `else` înseamnă că `s[cookieIndex] < g[childIndex]` (prăjitura este prea mică)
- `cookieIndex++` mută pointer-ul la următoarea prăjitură (poate este mai mare și poate mulțumi copilul curent sau unul viitor)

De exemplu, dacă `s[cookieIndex] = 1` și `g[childIndex] = 3`:
- `1 >= 3`? `false`, prăjitura este prea mică
- Trecem la următoarea prăjitură (poate este mai mare)

```java
    return contentCount;
```

Această linie returnează numărul de copii mulțumiți.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `g = [1, 2, 3]` și `s = [1, 1]`:

**Pasul 1: Sortăm**
- `g = [1, 2, 3]` (deja sortat)
- `s = [1, 1]` (deja sortat)

**Inițializare:**
- `childIndex = 0`, `cookieIndex = 0`, `contentCount = 0`

**Iterația 1:**
- `s[0] = 1`, `g[0] = 1`
- `1 >= 1`? `true`
- `contentCount = 1`, `childIndex = 1`, `cookieIndex = 1`

**Iterația 2:**
- `s[1] = 1`, `g[1] = 2`
- `1 >= 2`? `false`
- `cookieIndex = 2`
- `cookieIndex < s.length`? `false`, bucla se termină

**Rezultat:** `1` - un copil este mulțumit.

## Alt Exemplu

Să urmărim pentru `g = [1, 2]` și `s = [1, 2, 3]`:

**Iterația 1:**
- `s[0] = 1`, `g[0] = 1` → `1 >= 1`? `true`
- `contentCount = 1`, `childIndex = 1`, `cookieIndex = 1`

**Iterația 2:**
- `s[1] = 2`, `g[1] = 2` → `2 >= 2`? `true`
- `contentCount = 2`, `childIndex = 2`, `cookieIndex = 2`

**Rezultat:** `2` - toți copiii sunt mulțumiți.

## De Ce Este Această Soluție Eficientă?

1. **O(n log n + m log m) timp**: Sortarea este O(n log n) pentru copii și O(m log m) pentru prăjituri, iar potrivirea este O(n + m).

2. **O(1) spațiu**: Folosim doar câteva variabile, nu creăm structuri de date suplimentare.

3. **Greedy optim**: Strategia de a atribui cea mai mică prăjitură disponibilă este optimă.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n log n + m log m) - unde n este numărul de copii și m este numărul de prăjituri. Sortarea domină complexitatea.

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile (excludând sortarea care modifică array-urile originale).

## Concluzie

Această soluție este elegantă și eficientă. Sortăm copiii și prăjiturile, apoi folosim doi pointeri pentru a potrivi cea mai mică prăjitură disponibilă cu cel mai mic copil nesatisfăcut, maximizând astfel numărul de copii mulțumiți.
