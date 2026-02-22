# Soluție Detaliată - Sum of Left Leaves

## Ce Ne Cere Problema?

Problema ne cere să calculăm suma tuturor frunzelor stângi dintr-un arbore binar. O frunză stângă este un nod care este copilul stâng al părinților săi și nu are copii (este frunză).

De exemplu, dacă avem arborele:
```
     3
   /   \
  9     20
       /  \
      15   7
```

Frunzele stângi sunt: 9 și 15. Suma este 9 + 15 = 24.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Parcurgem arborele folosind DFS
2. Folosim un flag pentru a ține minte dacă nodul curent este copil stâng
3. Când găsim o frunză stângă (nod fără copii și este copil stâng), adăugăm valoarea sa la sumă
4. Continuăm recursiv pentru subarborii stâng și drept

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int sumOfLeftLeaves(TreeNode root) {
    return dfs(root, false);
}
```

Această linie definește funcția `sumOfLeftLeaves`. Funcția primește:
- `root` - rădăcina arborelui

Funcția returnează `int` - suma frunzelor stângi.

Funcția apelează `dfs(root, false)` pentru că rădăcina nu este copil stâng (nu are părinte).

```java
private int dfs(TreeNode node, boolean isLeft) {
```

Această linie definește funcția helper `dfs` care parcurge arborele recursiv. Funcția primește:
- `node` - nodul curent
- `isLeft` - flag care indică dacă nodul curent este copil stâng

```java
    if (node == null) {
        return 0;
    }
```

Această verificare tratează cazul când nodul este null. Dacă nodul este null, nu adaugă nimic la sumă, deci returnăm 0.

```java
    if (node.left == null && node.right == null && isLeft) {
        return node.val;
    }
```

Această condiție verifică dacă nodul este o frunză stângă. Să explicăm:

- `node.left == null && node.right == null` verifică dacă nodul este frunză (nu are copii)
- `isLeft` verifică dacă nodul este copil stâng
- Dacă ambele condiții sunt adevărate, nodul este o frunză stângă
- `return node.val` returnează valoarea frunzei stângi

De exemplu, dacă `node.val = 9`, `node.left = null`, `node.right = null` și `isLeft = true`, atunci returnăm 9.

```java
    return dfs(node.left, true) + dfs(node.right, false);
```

Această linie continuă parcurgerea recursivă pentru subarborii stâng și drept. Să explicăm:

- `dfs(node.left, true)` parcurge subarborele stâng, marcând că nodurile sunt copii stângi
- `dfs(node.right, false)` parcurge subarborele drept, marcând că nodurile sunt copii drepti
- `+` adună sumele returnate de ambele subarbori

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru arborele:
```
     3
   /   \
  9     20
       /  \
      15   7
```

**Apelul 1 (node = nodul 3, isLeft = false):**
- `node == null`? `false`, continuăm
- `node.left == null && node.right == null && isLeft`? `false`, continuăm
- `dfs(nodul 9, true)` → apelează recursiv
- `dfs(nodul 20, false)` → apelează recursiv

**Apelul 2 (node = nodul 9, isLeft = true):**
- `node == null`? `false`, continuăm
- `node.left == null && node.right == null && isLeft`? `true && true && true`? `true`!
- Returnăm `9`

**Apelul 3 (node = nodul 20, isLeft = false):**
- `node == null`? `false`, continuăm
- `node.left == null && node.right == null && isLeft`? `false`, continuăm
- `dfs(nodul 15, true)` → apelează recursiv
- `dfs(nodul 7, false)` → apelează recursiv

**Apelul 4 (node = nodul 15, isLeft = true):**
- `node == null`? `false`, continuăm
- `node.left == null && node.right == null && isLeft`? `true && true && true`? `true`!
- Returnăm `15`

**Apelul 5 (node = nodul 7, isLeft = false):**
- `node == null`? `false`, continuăm
- `node.left == null && node.right == null && isLeft`? `true && true && false`? `false`, continuăm
- `dfs(null, true)` → returnează `0`
- `dfs(null, false)` → returnează `0`
- Returnăm `0 + 0 = 0`

**Înapoi la apelul 3:**
- `dfs(nodul 15, true)` → `15`
- `dfs(nodul 7, false)` → `0`
- Returnăm `15 + 0 = 15`

**Înapoi la apelul 1:**
- `dfs(nodul 9, true)` → `9`
- `dfs(nodul 20, false)` → `15`
- Returnăm `9 + 15 = 24`

**Rezultat:** `24` - suma frunzelor stângi este 24 (9 + 15).

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Vizităm fiecare nod exact o dată.

2. **O(h) spațiu**: Unde h este înălțimea arborelui. Spațiul este folosit pentru stiva de apeluri recursive.

3. **Flag pentru copil stâng**: Folosim un flag pentru a identifica frunzele stângi, evitând verificări suplimentare.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de noduri. Vizităm fiecare nod exact o dată.

- **Complexitatea spațiului**: O(h) - unde h este înălțimea arborelui. Spațiul este folosit pentru stiva de apeluri recursive.

## Concluzie

Această soluție este simplă și eficientă. Folosim DFS cu un flag pentru a identifica frunzele stângi, adunând valorile lor la sumă, obținând astfel suma tuturor frunzelor stângi într-o singură parcurgere.
