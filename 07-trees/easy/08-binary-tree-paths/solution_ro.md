# Soluție Detaliată - Binary Tree Paths

## Ce Ne Cere Problema?

Problema ne cere să găsim toate drumurile de la rădăcină la frunze într-un arbore binar. Fiecare drum trebuie să fie reprezentat ca un string cu nodurile separate de "->".

De exemplu, dacă avem arborele:
```
     1
   /   \
  2     3
   \
    5
```

Drumurile sunt: `["1->2->5", "1->3"]`.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Parcurgem arborele folosind DFS (Depth-First Search)
2. Construim drumul pe măsură ce parcurgem arborele
3. Când ajungem la o frunză (nod fără copii), adăugăm drumul complet în rezultat
4. Continuăm recursiv pentru subarborii stâng și drept

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public List<String> binaryTreePaths(TreeNode root) {
```

Această linie definește funcția `binaryTreePaths`. Funcția primește:
- `root` - rădăcina arborelui

Funcția returnează `List<String>` - lista cu toate drumurile de la rădăcină la frunze.

```java
    List<String> result = new ArrayList<>();
    if (root == null) {
        return result;
    }
```

Aceste linii inițializează lista rezultatelor și tratează cazul când arborele este gol. Dacă arborele este gol, nu există drumuri, deci returnăm lista goală.

```java
    dfs(root, "", result);
    return result;
```

Aceste linii apelează funcția helper `dfs` pentru a găsi toate drumurile și returnează rezultatul.

```java
private void dfs(TreeNode node, String path, List<String> result) {
```

Această linie definește funcția helper `dfs` care parcurge arborele recursiv. Funcția primește:
- `node` - nodul curent
- `path` - drumul construit până acum
- `result` - lista rezultatelor

```java
    String currentPath = path.isEmpty() ? String.valueOf(node.val) 
                                       : path + "->" + node.val;
```

Această linie construiește drumul curent. Să explicăm:

- `path.isEmpty()` verifică dacă drumul este gol (suntem la rădăcină)
- Dacă da, `String.valueOf(node.val)` convertește valoarea nodului la string și o folosește ca drum
- Dacă nu, `path + "->" + node.val` adaugă "->" și valoarea nodului la drumul existent

De exemplu, dacă `path = "1"` și `node.val = 2`, atunci `currentPath = "1->2"`.

```java
    if (node.left == null && node.right == null) {
        result.add(currentPath);
        return;
    }
```

Această condiție verifică dacă nodul este o frunză. Să explicăm:

- `node.left == null && node.right == null` verifică dacă nodul nu are copii
- Dacă da, nodul este o frunză, deci am găsit un drum complet
- `result.add(currentPath)` adaugă drumul în rezultat
- `return` se oprește din parcurgerea acestui subarbore

```java
    if (node.left != null) {
        dfs(node.left, currentPath, result);
    }
    if (node.right != null) {
        dfs(node.right, currentPath, result);
    }
```

Aceste linii continuă parcurgerea recursivă pentru subarborii stâng și drept. Să explicăm:

- `if (node.left != null)` verifică dacă există subarbore stâng
- Dacă da, `dfs(node.left, currentPath, result)` continuă parcurgerea cu subarborele stâng, folosind drumul curent
- Similar pentru subarborele drept

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru arborele:
```
     1
   /   \
  2     3
   \
    5
```

**Apelul 1 (node = nodul 1, path = ""):**
- `path.isEmpty()`? `true`, deci `currentPath = "1"`
- `node.left == null && node.right == null`? `false`, continuăm
- `dfs(nodul 2, "1", result)` → apelează recursiv
- `dfs(nodul 3, "1", result)` → apelează recursiv

**Apelul 2 (node = nodul 2, path = "1"):**
- `currentPath = "1->2"`
- `node.left == null && node.right == null`? `false` (are copil drept), continuăm
- `dfs(nodul 5, "1->2", result)` → apelează recursiv

**Apelul 3 (node = nodul 5, path = "1->2"):**
- `currentPath = "1->2->5"`
- `node.left == null && node.right == null`? `true`!
- `result.add("1->2->5")`
- Returnăm

**Înapoi la apelul 1:**
- `dfs(nodul 3, "1", result)` → apelează recursiv

**Apelul 4 (node = nodul 3, path = "1"):**
- `currentPath = "1->3"`
- `node.left == null && node.right == null`? `true`!
- `result.add("1->3")`
- Returnăm

**După toate apelurile:**
- `result = ["1->2->5", "1->3"]`
- Returnăm `result`

**Rezultat:** `["1->2->5", "1->3"]` - toate drumurile de la rădăcină la frunze.

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Vizităm fiecare nod exact o dată.

2. **O(h) spațiu**: Unde h este înălțimea arborelui. Spațiul este folosit pentru stiva de apeluri recursive și pentru string-urile drumurilor.

3. **Construim drumurile incremental**: Adăugăm nodul curent la drum pe măsură ce parcurgem arborele, evitând recalcularea.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de noduri. Vizităm fiecare nod exact o dată.

- **Complexitatea spațiului**: O(h) - unde h este înălțimea arborelui. Spațiul este folosit pentru stiva de apeluri recursive și pentru string-urile drumurilor.

## Concluzie

Această soluție este simplă și eficientă. Folosim DFS pentru a parcurge arborele și construim drumurile incremental, adăugând drumurile complete în rezultat când ajungem la frunze, obținând astfel toate drumurile de la rădăcină la frunze într-o singură parcurgere.
