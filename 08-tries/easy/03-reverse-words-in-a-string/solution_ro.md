# Soluție Detaliată - Reverse Words in a String

## Ce Ne Cere Problema?

Problema ne cere să inversăm ordinea cuvintelor dintr-un string, păstrând un singur spațiu între cuvinte și eliminând spațiile de la început și sfârșit. De exemplu, dacă avem `"  hello world  "`, după inversare ar trebui să obținem `"world hello"`.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Eliminăm spațiile de la început și sfârșit (trim)
2. Despărțim string-ul în cuvinte folosind spațiile ca delimitatori
3. Inversăm ordinea cuvintelor
4. Unim cuvintele cu un singur spațiu între ele

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public String reverseWords(String s) {
```

Această linie definește funcția `reverseWords`. Funcția primește:
- `s` - string-ul de procesat

Funcția returnează `String` - string-ul cu cuvintele inversate.

```java
    String[] words = s.trim().split("\\s+");
```

Această linie elimină spațiile și despărțește string-ul în cuvinte. Să explicăm pas cu pas:

- `s.trim()` elimină spațiile de la început și sfârșitul string-ului
- `split("\\s+")` despărțește string-ul în cuvinte folosind unul sau mai multe spații ca delimitatori
- `\\s+` este o expresie regulată care înseamnă "unul sau mai multe caractere de spațiu"
- `String[] words` stochează array-ul de cuvinte rezultat

De exemplu, dacă `s = "  hello   world  "`:
- `s.trim()` → `"hello   world"`
- `split("\\s+")` → `["hello", "world"]`

```java
    Collections.reverse(Arrays.asList(words));
```

Această linie inversează ordinea cuvintelor. Să explicăm:

- `Arrays.asList(words)` convertește array-ul într-o listă
- `Collections.reverse(...)` inversează ordinea elementelor din listă
- Lista este modificată direct (nu returnăm o listă nouă)

De exemplu, dacă `words = ["hello", "world"]`:
- După `Collections.reverse(...)`, `words = ["world", "hello"]`

```java
    return String.join(" ", words);
```

Această linie unește cuvintele cu un singur spațiu. Să explicăm:

- `String.join(" ", words)` unește elementele din array-ul `words` folosind " " (un singur spațiu) ca separator
- Returnează string-ul rezultat

De exemplu, dacă `words = ["world", "hello"]`:
- `String.join(" ", words)` → `"world hello"`

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `s = "  the sky is blue  "`:

**Pasul 1: Trim**
- `s.trim()` → `"the sky is blue"`

**Pasul 2: Split**
- `split("\\s+")` → `["the", "sky", "is", "blue"]`

**Pasul 3: Reverse**
- `Collections.reverse(...)` → `["blue", "is", "sky", "the"]`

**Pasul 4: Join**
- `String.join(" ", words)` → `"blue is sky the"`

**Rezultat:** `"blue is sky the"` - cuvintele sunt inversate.

## Alt Exemplu - Multe Spații

Să urmărim pentru `s = "  hello   world  "`:

**Pasul 1: Trim**
- `s.trim()` → `"hello   world"`

**Pasul 2: Split**
- `split("\\s+")` → `["hello", "world"]` (spațiile multiple sunt ignorate)

**Pasul 3: Reverse**
- `Collections.reverse(...)` → `["world", "hello"]`

**Pasul 4: Join**
- `String.join(" ", words)` → `"world hello"`

**Rezultat:** `"world hello"` - cuvintele sunt inversate, cu un singur spațiu între ele.

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Parcurgem string-ul o singură dată pentru split și join.

2. **O(n) spațiu**: Folosim un array pentru a stoca cuvintele.

3. **Simplă și clară**: Folosim metode built-in pentru a simplifica codul.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este lungimea string-ului. Parcurgem string-ul pentru split și join.

- **Complexitatea spațiului**: O(n) - folosim un array pentru a stoca cuvintele.

## Concluzie

Această soluție este simplă și eficientă. Folosim metode built-in pentru a elimina spațiile, despărți string-ul în cuvinte, inversa ordinea și uni cuvintele, obținând astfel string-ul cu cuvintele inversate.
