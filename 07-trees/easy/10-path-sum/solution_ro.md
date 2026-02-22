# Soluție Detaliată - Path Sum

## Ce Ne Cere Problema?

Problema ne cere să verificăm dacă există un drum de la rădăcină la o frunză a cărui sumă este egală cu o valoare dată (targetSum).

De exemplu, dacă avem arborele:
```
      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
```

Și `targetSum = 22`, există un drum: 5 -> 4 -> 11 -> 2 (suma = 22).

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Parcurgem arborele folosind DFS
2. Scădem valoarea nodului curent din targetSum
3. Când ajungem la o frunză, verificăm dacă targetSum rămas este 0
4. Dacă da, am găsit un drum cu suma dorită
5. Continuăm recursiv pentru subarborii stâng și drept

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public boolean hasPathSum(TreeNode root, int targetSum) {
```

Această linie definește funcția `hasPathSum`. Funcția primește:
- `root` - rădăcina arborelui
- `targetSum` - suma dorită

Funcția returnează `boolean` - `true` dacă există un drum cu suma dorită, `false` dacă nu există.

```java
    if (root == null) {
        return false;
    }
```

Această verificare tratează cazul când arborele este gol. Dacă arborele este gol, nu există drumuri, deci returnăm `false`.

```java
    targetSum -= root.val;
```

Această linie scade valoarea nodului curent din targetSum. Aceasta reprezintă faptul că am "consumat" valoarea nodului în drumul curent.

```java
    if (root.left == null && root.right == null) {
        return targetSum == 0;
    }
```

Această condiție verifică dacă nodul este o frunză. Să explicăm:

- `root.left == null && root.right == null` verifică dacă nodul este frunză (nu are copii)
- Dacă da, am ajuns la sfârșitul unui drum
- `targetSum == 0` verifică dacă suma rămasă este 0 (am găsit un drum cu suma dorită)
- Returnăm rezultatul acestei verificări

De exemplu, dacă `targetSum = 2` și `root.val = 2`, atunci după `targetSum -= 2`, `targetSum = 0`, deci `targetSum == 0` este `true`, returnăm `true`.

```java
    return hasPathSum(root.left, targetSum) || 
           hasPathSum(root.right, targetSum);
```

Această linie continuă parcurgerea recursivă pentru subarborii stâng și drept. Să explicăm:

- `hasPathSum(root.left, targetSum)` verifică dacă există un drum în subarborele stâng cu suma rămasă
- `hasPathSum(root.right, targetSum)` verifică dacă există un drum în subarborele drept cu suma rămasă
- `||` înseamnă "sau" - returnăm `true` dacă oricare dintre subarbori conține un drum cu suma dorită

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru arborele:
```
      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
```

Și `targetSum = 22`:

**Apelul 1 (root = nodul 5, targetSum = 22):**
- `root == null`? `false`, continuăm
- `targetSum -= 5` → `targetSum = 17`
- `root.left == null && root.right == null`? `false`, continuăm
- `hasPathSum(nodul 4, 17)` → apelează recursiv
- `hasPathSum(nodul 8, 17)` → apelează recursiv

**Apelul 2 (root = nodul 4, targetSum = 17):**
- `targetSum -= 4` → `targetSum = 13`
- `root.left == null && root.right == null`? `false`, continuăm
- `hasPathSum(nodul 11, 13)` → apelează recursiv

**Apelul 3 (root = nodul 11, targetSum = 13):**
- `targetSum -= 11` → `targetSum = 2`
- `root.left == null && root.right == null`? `false`, continuăm
- `hasPathSum(nodul 7, 2)` → apelează recursiv
- `hasPathSum(nodul 2, 2)` → apelează recursiv

**Apelul 4 (root = nodul 7, targetSum = 2):**
- `targetSum -= 7` → `targetSum = -5`
- `root.left == null && root.right == null`? `true`!
- `targetSum == 0`? `-5 == 0`? `false`
- Returnăm `false`

**Apelul 5 (root = nodul 2, targetSum = 2):**
- `targetSum -= 2` → `targetSum = 0`
- `root.left == null && root.right == null`? `true`!
- `targetSum == 0`? `0 == 0`? `true`!
- Returnăm `true`

**Înapoi la apelul 3:**
- `hasPathSum(nodul 7, 2)` → `false`
- `hasPathSum(nodul 2, 2)` → `true`!
- Returnăm `true || false = true`

**Înapoi la apelul 2:**
- `hasPathSum(nodul 11, 13)` → `true`!
- Returnăm `true`

**Înapoi la apelul 1:**
- `hasPathSum(nodul 4, 17)` → `true`!
- Returnăm `true`

**Rezultat:** `true` - există un drum cu suma 22 (5 -> 4 -> 11 -> 2).

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Vizităm fiecare nod cel mult o dată.

2. **O(h) spațiu**: Unde h este înălțimea arborelui. Spațiul este folosit pentru stiva de apeluri recursive.

3. **Early termination**: Dacă găsim un drum cu suma dorită, returnăm imediat `true`, fără să continuăm căutarea.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de noduri. Vizităm fiecare nod cel mult o dată.

- **Complexitatea spațiului**: O(h) - unde h este înălțimea arborelui. Spațiul este folosit pentru stiva de apeluri recursive.

## Concluzie

Această soluție este simplă și eficientă. Folosim DFS pentru a parcurge arborele, scăzând valoarea nodului curent din targetSum și verificând când ajungem la o frunză dacă suma rămasă este 0, returnând `true` imediat când găsim un drum cu suma dorită.
