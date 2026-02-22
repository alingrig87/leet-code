# Soluție Detaliată - Diameter of Binary Tree

## Ce Ne Cere Problema?

Problema ne cere să găsim diametrul unui arbore binar. Diametrul este cel mai lung drum între oricare două noduri din arbore. Acest drum poate trece sau nu prin rădăcină.

De exemplu, dacă avem arborele:
```
     1
   /   \
  2     3
 / \
4   5
```

Diametrul este 3 (drumul 4 -> 2 -> 1 -> 3 sau 4 -> 2 -> 5, ambele au lungimea 3).

## Ce Este Diametrul unui Arbore?

Diametrul unui arbore este numărul de muchii (legături) de pe cel mai lung drum între oricare două noduri. De exemplu, dacă cel mai lung drum are 4 noduri, atunci are 3 muchii, deci diametrul este 3.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Pentru fiecare nod, calculăm înălțimea subarborelui stâng și a subarborelui drept
2. Diametrul care trece prin nodul curent este suma înălțimilor subarborilor
3. Actualizăm diametrul maxim dacă diametrul curent este mai mare
4. Returnăm înălțimea subarborelui (1 + maximul dintre înălțimile subarborilor)

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
class Solution {
    private int maxDiameter = 0;
```

Această linie declară o variabilă de instanță care va stoca diametrul maxim. Folosim `private` pentru a o ascunde și o inițializăm cu 0.

```java
    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return maxDiameter;
    }
```

Această metodă este punctul de intrare. Să explicăm:

- `height(root)` apelează metoda helper care calculează înălțimea și actualizează diametrul maxim
- `return maxDiameter` returnează diametrul maxim găsit

De ce apelăm `height()`? Pentru că în timp ce calculăm înălțimea fiecărui subarbore, putem calcula și diametrul care trece prin fiecare nod.

```java
    private int height(TreeNode node) {
```

Această metodă calculează înălțimea unui subarbore și actualizează diametrul maxim. Returnează înălțimea subarborelui.

```java
        if (node == null) {
            return 0;
        }
```

Această verificare este cazul de bază. Dacă nodul este null, înălțimea este 0 (nu există nod, deci nu adaugă la înălțime).

```java
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
```

Aceste linii calculează recursiv înălțimile subarborilor. Să explicăm:

- `height(node.left)` calculează înălțimea subarborelui stâng
- `height(node.right)` calculează înălțimea subarborelui drept

De exemplu, dacă subarborele stâng are înălțimea 2 și subarborele drept are înălțimea 1, atunci `leftHeight = 2` și `rightHeight = 1`.

```java
        int diameter = leftHeight + rightHeight;
        maxDiameter = Math.max(maxDiameter, diameter);
```

Aceste linii calculează și actualizează diametrul. Să explicăm:

- `diameter = leftHeight + rightHeight` calculează diametrul care trece prin nodul curent
- Diametrul este suma înălțimilor subarborilor pentru că drumul cel mai lung care trece prin nodul curent merge de la cel mai adânc nod din subarborele stâng la cel mai adânc nod din subarborele drept
- `maxDiameter = Math.max(maxDiameter, diameter)` actualizează diametrul maxim dacă diametrul curent este mai mare

De exemplu, dacă `leftHeight = 2` și `rightHeight = 1`, atunci `diameter = 2 + 1 = 3`. Dacă `maxDiameter` era 2, devine 3.

```java
        return 1 + Math.max(leftHeight, rightHeight);
```

Această linie returnează înălțimea subarborelui. Să explicăm:

- `Math.max(leftHeight, rightHeight)` returnează înălțimea celui mai înalt subarbore
- `1 + ...` adaugă 1 pentru nodul curent (nodul însuși adaugă un nivel la înălțime)
- `return ...` returnează înălțimea totală

De exemplu, dacă `leftHeight = 2` și `rightHeight = 1`, atunci înălțimea este `1 + max(2, 1) = 1 + 2 = 3`.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru arborele:
```
     1
   /   \
  2     3
 / \
4   5
```

**Apelul 1 (node = nodul 1):**
- `leftHeight = height(nodul 2)` → apelează recursiv
- `rightHeight = height(nodul 3)` → apelează recursiv

**Apelul 2 (node = nodul 2):**
- `leftHeight = height(nodul 4)` → apelează recursiv
- `rightHeight = height(nodul 5)` → apelează recursiv

**Apelul 3 (node = nodul 4):**
- `leftHeight = height(null) = 0`
- `rightHeight = height(null) = 0`
- `diameter = 0 + 0 = 0`
- `maxDiameter = max(0, 0) = 0`
- Returnează `1 + max(0, 0) = 1`

**Apelul 4 (node = nodul 5):**
- Similar cu nodul 4, returnează `1`

**Înapoi la apelul 2 (node = nodul 2):**
- `leftHeight = 1` (de la nodul 4)
- `rightHeight = 1` (de la nodul 5)
- `diameter = 1 + 1 = 2`
- `maxDiameter = max(0, 2) = 2`
- Returnează `1 + max(1, 1) = 2`

**Apelul 5 (node = nodul 3):**
- `leftHeight = height(null) = 0`
- `rightHeight = height(null) = 0`
- `diameter = 0 + 0 = 0`
- `maxDiameter = max(2, 0) = 2`
- Returnează `1 + max(0, 0) = 1`

**Înapoi la apelul 1 (node = nodul 1):**
- `leftHeight = 2` (de la nodul 2)
- `rightHeight = 1` (de la nodul 3)
- `diameter = 2 + 1 = 3`
- `maxDiameter = max(2, 3) = 3`
- Returnează `1 + max(2, 1) = 3`

**După toate apelurile:**
- `maxDiameter = 3`
- Returnăm `3`

**Rezultat:** `3` - diametrul este 3 (drumul 4 -> 2 -> 1 -> 3).

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Vizităm fiecare nod exact o dată.

2. **O(h) spațiu**: Unde h este înălțimea arborelui. Spațiul este folosit pentru stiva de apeluri recursive.

3. **Calculează diametrul în timpul calculării înălțimii**: Nu trebuie să parcurgem arborele de două ori - calculăm diametrul în timp ce calculăm înălțimea.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de noduri. Vizităm fiecare nod exact o dată.

- **Complexitatea spațiului**: O(h) - unde h este înălțimea arborelui. Spațiul este folosit pentru stiva de apeluri recursive. În cel mai rău caz (arbore liniar), O(n). În cel mai bun caz (arbore complet), O(log n).

## Concluzie

Această soluție este elegantă și eficientă. Calculăm diametrul în timp ce calculăm înălțimea fiecărui subarbore, actualizând diametrul maxim când găsim un drum mai lung, obținând astfel diametrul într-o singură parcurgere a arborelui.
