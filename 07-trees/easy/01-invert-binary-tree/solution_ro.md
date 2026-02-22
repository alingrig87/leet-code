# Soluție Detaliată - Invert Binary Tree

## Ce Ne Cere Problema?

Problema ne cere să inversăm (oglindim) un arbore binar. Inversarea înseamnă să schimbăm copiii stâng și drept ai fiecărui nod. De exemplu, dacă avem un arbore:
```
     4
   /   \
  2     7
 / \   / \
1   3 6   9
```

După inversare, ar trebui să obținem:
```
     4
   /   \
  7     2
 / \   / \
9   6 3   1
```

## Ce Este un Arbore Binar?

Un arbore binar este o structură de date formată din noduri, unde fiecare nod poate avea cel mult doi copii: un copil stâng și un copil drept. Primul nod se numește "root" (rădăcină).

Gândiți-vă la un arbore binar ca la un arbore genealogic, dar unde fiecare persoană poate avea cel mult doi copii.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Folosim recursivitate pentru a parcurge arborele
2. Pentru fiecare nod, inversăm copiii stâng și drept
3. Aplicăm recursiv inversarea pe subarborii stâng și drept
4. Returnăm nodul curent (care acum are copiii inversați)

## Ce Este Recursivitatea?

Recursivitatea este o tehnică unde o funcție se apelează pe ea însăși. În cazul nostru, funcția `invertTree` se apelează pentru copiii stâng și drept ai nodului curent.

Gândiți-vă la recursivitate ca la păpușile rusești - fiecare păpușă conține o păpușă mai mică, care conține o păpușă și mai mică, și așa mai departe.

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public TreeNode invertTree(TreeNode root) {
```

Această linie definește funcția `invertTree`. Funcția primește:
- `root` - rădăcina arborelui

Funcția returnează `TreeNode` - rădăcina arborelui inversat.

```java
    if (root == null) {
        return null;
    }
```

Această verificare este cazul de bază al recursivității. Să explicăm:

- `root == null` verifică dacă nodul curent este null (nu există)
- Dacă nodul este null, nu avem ce inversa, deci returnăm `null`

De ce este important? Pentru că recursivitatea trebuie să se oprească undeva. Când ajungem la un nod null (un copil care nu există), ne oprim și returnăm.

```java
    TreeNode left = invertTree(root.left);
    TreeNode right = invertTree(root.right);
```

Aceste linii inversează recursiv subarborii. Să explicăm pas cu pas:

- `invertTree(root.left)` apelează funcția pentru copilul stâng al nodului curent
- Funcția va inversa întregul subarbore stâng și va returna noua rădăcină a subarborelui inversat
- `TreeNode left = ...` stochează rezultatul (subarborele stâng inversat)
- Similar pentru `invertTree(root.right)` - inversează subarborele drept

De ce facem asta înainte de a schimba copiii? Pentru că vrem să inversăm întâi subarborii, apoi să schimbăm copiii nodului curent.

```java
    root.left = right;
    root.right = left;
```

Aceste linii schimbă copiii nodului curent. Să explicăm:

- `root.left = right` setează copilul stâng al nodului curent să fie subarborele drept inversat
- `root.right = left` setează copilul drept al nodului curent să fie subarborele stâng inversat

De ce schimbăm astfel? Pentru că vrem să inversăm arborele - copilul stâng devine copilul drept și invers.

```java
    return root;
```

Această linie returnează nodul curent (care acum are copiii inversați). După ce am inversat subarborii și am schimbat copiii, returnăm nodul curent.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru arborele:
```
     4
   /   \
  2     7
 / \   / \
1   3 6   9
```

**Apelul 1 (root = nodul 4):**
- `root == null`? `false`, continuăm
- `left = invertTree(nodul 2)` → apelează recursiv pentru subarborele stâng
- `right = invertTree(nodul 7)` → apelează recursiv pentru subarborele drept

**Apelul 2 (root = nodul 2):**
- `root == null`? `false`, continuăm
- `left = invertTree(nodul 1)` → apelează recursiv
- `right = invertTree(nodul 3)` → apelează recursiv

**Apelul 3 (root = nodul 1):**
- `root == null`? `false`, continuăm
- `left = invertTree(null)` → returnează `null`
- `right = invertTree(null)` → returnează `null`
- `root.left = null`, `root.right = null` (nodul 1 nu are copii, rămâne neschimbat)
- Returnează `nodul 1`

**Apelul 4 (root = nodul 3):**
- Similar cu nodul 1, returnează `nodul 3`

**Înapoi la apelul 2 (root = nodul 2):**
- `left = nodul 1` (inversat, dar fără copii)
- `right = nodul 3` (inversat, dar fără copii)
- `root.left = nodul 3` (schimbăm copiii)
- `root.right = nodul 1`
- Returnează `nodul 2` (acum cu copiii schimbați)

**Apelul 5 (root = nodul 7):**
- Similar cu nodul 2, inversează subarborii și schimbă copiii
- Returnează `nodul 7` (cu copiii schimbați)

**Înapoi la apelul 1 (root = nodul 4):**
- `left = nodul 2` (inversat)
- `right = nodul 7` (inversat)
- `root.left = nodul 7` (schimbăm copiii)
- `root.right = nodul 2`
- Returnează `nodul 4` (cu copiii schimbați)

**Rezultat:** Arborele inversat:
```
     4
   /   \
  7     2
 / \   / \
9   6 3   1
```

## De Ce Este Această Soluție Eficientă?

1. **Parcurgem fiecare nod o singură dată**: Vizităm fiecare nod exact o dată.

2. **O(n) timp**: Unde n este numărul de noduri. Parcurgem toate nodurile o singură dată.

3. **O(h) spațiu**: Unde h este înălțimea arborelui. Spațiul este folosit pentru stiva de apeluri recursive. În cel mai rău caz (arbore liniar), h = n. În cel mai bun caz (arbore complet), h = log(n).

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de noduri. Vizităm fiecare nod exact o dată.

- **Complexitatea spațiului**: O(h) - unde h este înălțimea arborelui. Spațiul este folosit pentru stiva de apeluri recursive. În cel mai rău caz (arbore liniar), O(n). În cel mai bun caz (arbore complet), O(log n).

## Concluzie

Această soluție este elegantă și eficientă. Folosim recursivitate pentru a inversa subarborii și apoi schimbăm copiii nodului curent, obținând astfel arborele inversat într-o singură parcurgere.
