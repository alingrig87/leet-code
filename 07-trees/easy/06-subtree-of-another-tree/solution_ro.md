# Soluție Detaliată - Subtree of Another Tree

## Ce Ne Cere Problema?

Problema ne cere să verificăm dacă un arbore binar (`subRoot`) este un subarbore al unui alt arbore binar (`root`). Un subarbore este un arbore care există ca un nod și toți descendenții săi într-un alt arbore.

De exemplu, dacă avem:
```
root:      3          subRoot:   4
         / \                    / \
        4   5                  1   2
       / \
      1   2
```

Atunci `subRoot` este un subarbore al lui `root` (începând de la nodul 4).

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Parcurgem arborele `root` recursiv
2. Pentru fiecare nod, verificăm dacă subarborele care începe de la acel nod este identic cu `subRoot`
3. Dacă găsim un subarbore identic, returnăm `true`
4. Dacă nu găsim, continuăm căutarea în subarborii stâng și drept

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public boolean isSubtree(TreeNode root, TreeNode subRoot) {
```

Această linie definește funcția `isSubtree`. Funcția primește:
- `root` - arborele principal
- `subRoot` - arborele de verificat dacă este subarbore

Funcția returnează `boolean` - `true` dacă `subRoot` este subarbore al lui `root`, `false` dacă nu este.

```java
    if (root == null) {
        return false;
    }
```

Această verificare tratează cazul când `root` este null. Dacă `root` este null, nu poate conține `subRoot`, deci returnăm `false`.

```java
    if (isSameTree(root, subRoot)) {
        return true;
    }
```

Această condiție verifică dacă subarborele care începe de la `root` este identic cu `subRoot`. Dacă da, am găsit subarborele, deci returnăm `true`.

```java
    return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
```

Această linie continuă căutarea recursiv în subarborii stâng și drept. Să explicăm:

- `isSubtree(root.left, subRoot)` verifică dacă `subRoot` este subarbore al subarborelui stâng
- `isSubtree(root.right, subRoot)` verifică dacă `subRoot` este subarbore al subarborelui drept
- `||` înseamnă "sau" - returnăm `true` dacă oricare dintre subarbori conține `subRoot`

```java
private boolean isSameTree(TreeNode p, TreeNode q) {
```

Această linie definește funcția helper `isSameTree` care verifică dacă două arbori sunt identici. Această funcție este similară cu problema "Same Tree".

```java
    if (p == null && q == null) return true;
    if (p == null || q == null) return false;
    if (p.val != q.val) return false;
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
```

Aceste linii verifică dacă arborii sunt identici:
- Dacă ambele sunt null, sunt identici
- Dacă doar unul este null, nu sunt identici
- Dacă valorile diferă, nu sunt identici
- Altfel, verificăm recursiv subarborii

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru:
```
root:      3          subRoot:   4
         / \                    / \
        4   5                  1   2
       / \
      1   2
```

**Apelul 1 (root = nodul 3, subRoot = nodul 4):**
- `root == null`? `false`, continuăm
- `isSameTree(nodul 3, nodul 4)` → `3 != 4`? `true`, returnăm `false`
- `isSubtree(nodul 4, nodul 4)` → apelează recursiv pentru subarborele stâng
- `isSubtree(nodul 5, nodul 4)` → apelează recursiv pentru subarborele drept

**Apelul 2 (root = nodul 4, subRoot = nodul 4):**
- `root == null`? `false`, continuăm
- `isSameTree(nodul 4, nodul 4)` → apelează recursiv

**Apelul 3 (isSameTree pentru nodul 4):**
- `p.val = 4`, `q.val = 4`
- `4 != 4`? `false`, continuăm
- `isSameTree(nodul 1, nodul 1)` → apelează recursiv
- `isSameTree(nodul 2, nodul 2)` → apelează recursiv

**Apelul 4 (isSameTree pentru nodul 1):**
- `p.val = 1`, `q.val = 1`
- `1 != 1`? `false`, continuăm
- `isSameTree(null, null)` → returnează `true`
- `isSameTree(null, null)` → returnează `true`
- Returnează `true && true = true`

**Apelul 5 (isSameTree pentru nodul 2):**
- Similar cu nodul 1, returnează `true`

**Înapoi la apelul 3:**
- `isSameTree(nodul 1, nodul 1)` → `true`
- `isSameTree(nodul 2, nodul 2)` → `true`
- Returnează `true && true = true`

**Înapoi la apelul 2:**
- `isSameTree(nodul 4, nodul 4)` → `true`!
- Returnăm `true`

**Înapoi la apelul 1:**
- `isSubtree(nodul 4, nodul 4)` → `true`!
- Returnăm `true`

**Rezultat:** `true` - `subRoot` este subarbore al lui `root`.

## De Ce Este Această Soluție Eficientă?

1. **O(n * m) timp**: Unde n este numărul de noduri din `root` și m este numărul de noduri din `subRoot`. În cel mai rău caz, verificăm fiecare nod din `root` și comparăm cu `subRoot`.

2. **O(h) spațiu**: Unde h este înălțimea arborelui `root`. Spațiul este folosit pentru stiva de apeluri recursive.

3. **Early termination**: Dacă găsim un subarbore identic, returnăm imediat `true`, fără să continuăm căutarea.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n * m) - unde n este numărul de noduri din `root` și m este numărul de noduri din `subRoot`. În cel mai rău caz, verificăm fiecare nod din `root` și comparăm cu `subRoot`.

- **Complexitatea spațiului**: O(h) - unde h este înălțimea arborelui `root`. Spațiul este folosit pentru stiva de apeluri recursive.

## Concluzie

Această soluție este simplă și eficientă. Parcurgem arborele `root` recursiv și verificăm pentru fiecare nod dacă subarborele care începe de la acel nod este identic cu `subRoot`, returnând `true` imediat când găsim un subarbore identic.
