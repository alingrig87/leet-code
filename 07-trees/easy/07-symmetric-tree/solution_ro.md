# Soluție Detaliată - Symmetric Tree

## Ce Ne Cere Problema?

Problema ne cere să verificăm dacă un arbore binar este simetric (oglindit față de centru). Un arbore este simetric dacă subarborele stâng este o oglindire a subarborelui drept.

De exemplu:
```
     1
   /   \
  2     2
 / \   / \
3   4 4   3
```
Acest arbore este simetric.

## Ce Înseamnă "Symmetric"?

Un arbore simetric înseamnă că subarborele stâng este o oglindire a subarborelui drept. Adică:
- Valoarea nodului stâng este egală cu valoarea nodului drept
- Subarborele stâng al nodului stâng este oglindirea subarborelui drept al nodului drept
- Subarborele drept al nodului stâng este oglindirea subarborelui stâng al nodului drept

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Verificăm dacă subarborele stâng și drept sunt oglindiri unul altuia
2. Pentru a verifica oglindirea, comparăm:
   - Nodul stâng al stângului cu nodul drept al dreptului
   - Nodul drept al stângului cu nodul stâng al dreptului
3. Dacă toate comparațiile sunt adevărate, arborele este simetric

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public boolean isSymmetric(TreeNode root) {
```

Această linie definește funcția `isSymmetric`. Funcția primește:
- `root` - rădăcina arborelui

Funcția returnează `boolean` - `true` dacă arborele este simetric, `false` dacă nu este.

```java
    if (root == null) {
        return true;
    }
```

Această verificare tratează cazul când arborele este gol. Un arbore gol este considerat simetric.

```java
    return isMirror(root.left, root.right);
```

Această linie verifică dacă subarborii stâng și drept sunt oglindiri. Dacă da, arborele este simetric.

```java
private boolean isMirror(TreeNode left, TreeNode right) {
```

Această linie definește funcția helper `isMirror` care verifică dacă două subarbori sunt oglindiri.

```java
    if (left == null && right == null) {
        return true;
    }
```

Această verificare tratează cazul când ambele noduri sunt null. Dacă ambele sunt null, sunt oglindiri (ambele nu există).

```java
    if (left == null || right == null) {
        return false;
    }
```

Această verificare tratează cazul când doar unul dintre noduri este null. Dacă doar unul este null, nu sunt oglindiri (unul există, celălalt nu).

```java
    if (left.val != right.val) {
        return false;
    }
```

Această verificare compară valorile nodurilor. Dacă valorile diferă, nodurile nu sunt oglindiri.

```java
    return isMirror(left.left, right.right) && 
           isMirror(left.right, right.left);
```

Această linie verifică recursiv oglindirea subarborilor. Să explicăm:

- `isMirror(left.left, right.right)` verifică dacă subarborele stâng al nodului stâng este oglindirea subarborelui drept al nodului drept
- `isMirror(left.right, right.left)` verifică dacă subarborele drept al nodului stâng este oglindirea subarborelui stâng al nodului drept
- `&&` înseamnă "și" - ambele trebuie să fie `true` pentru ca nodurile să fie oglindiri

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru arborele:
```
     1
   /   \
  2     2
 / \   / \
3   4 4   3
```

**Apelul 1 (isSymmetric pentru nodul 1):**
- `root == null`? `false`, continuăm
- `isMirror(nodul 2 stâng, nodul 2 drept)` → apelează recursiv

**Apelul 2 (isMirror pentru nodurile 2):**
- `left == null && right == null`? `false`
- `left == null || right == null`? `false`
- `left.val != right.val`? `2 != 2`? `false`, continuăm
- `isMirror(nodul 3, nodul 3)` → apelează recursiv
- `isMirror(nodul 4, nodul 4)` → apelează recursiv

**Apelul 3 (isMirror pentru nodurile 3):**
- `left == null && right == null`? `false`
- `left == null || right == null`? `false`
- `left.val != right.val`? `3 != 3`? `false`, continuăm
- `isMirror(null, null)` → returnează `true`
- `isMirror(null, null)` → returnează `true`
- Returnează `true && true = true`

**Apelul 4 (isMirror pentru nodurile 4):**
- Similar cu nodurile 3, returnează `true`

**Înapoi la apelul 2:**
- `isMirror(nodul 3, nodul 3)` → `true`
- `isMirror(nodul 4, nodul 4)` → `true`
- Returnează `true && true = true`

**Înapoi la apelul 1:**
- `isMirror(nodul 2 stâng, nodul 2 drept)` → `true`!
- Returnăm `true`

**Rezultat:** `true` - arborele este simetric.

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Vizităm fiecare nod exact o dată.

2. **O(h) spațiu**: Unde h este înălțimea arborelui. Spațiul este folosit pentru stiva de apeluri recursive.

3. **Early termination**: Dacă găsim o nepotrivire, returnăm imediat `false`, fără să continuăm verificarea.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de noduri. Vizităm fiecare nod exact o dată.

- **Complexitatea spațiului**: O(h) - unde h este înălțimea arborelui. Spațiul este folosit pentru stiva de apeluri recursive.

## Concluzie

Această soluție este elegantă și eficientă. Verificăm recursiv dacă subarborii stâng și drept sunt oglindiri, comparând nodurile în poziții oglindite, returnând `false` imediat când găsim o nepotrivire.
