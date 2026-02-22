# Soluție Detaliată - Balanced Binary Tree

## Ce Ne Cere Problema?

Problema ne cere să verificăm dacă un arbore binar este "balanced" (echilibrat). Un arbore este balanced dacă pentru fiecare nod, diferența dintre înălțimile subarborilor stâng și drept este cel mult 1.

De exemplu:
- Arborele `[3, 9, 20, null, null, 15, 7]` este balanced
- Arborele `[1, 2, 2, 3, 3, null, null, 4, 4]` nu este balanced (diferența este mai mare decât 1)

## Ce Înseamnă "Balanced"?

Un arbore balanced înseamnă că nu este "dezechilibrat" - nu are ramuri mult mai lungi decât altele. Pentru fiecare nod, subarborii stâng și drept au înălțimi similare (diferența este cel mult 1).

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Calculăm înălțimea fiecărui subarbore recursiv
2. Dacă un subarbore nu este balanced, returnăm -1 (un "sentinel" care indică dezechilibru)
3. Dacă diferența dintre înălțimile subarborilor este > 1, returnăm -1
4. Dacă totul este balanced, returnăm înălțimea normală
5. La final, dacă rezultatul este -1, arborele nu este balanced

## Ce Este un Sentinel?

Un sentinel este o valoare specială care indică o situație anormală. În cazul nostru, -1 indică că arborele nu este balanced. Folosim -1 pentru că înălțimea unui arbore este întotdeauna >= 0, deci -1 nu poate fi confundat cu o înălțime validă.

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public boolean isBalanced(TreeNode root) {
    return height(root) != -1;
}
```

Această metodă verifică dacă arborele este balanced. Să explicăm:

- `height(root)` calculează înălțimea și returnează -1 dacă arborele nu este balanced
- `height(root) != -1` verifică dacă rezultatul nu este -1
- Dacă rezultatul nu este -1, înseamnă că arborele este balanced, deci returnăm `true`
- Dacă rezultatul este -1, înseamnă că arborele nu este balanced, deci returnăm `false`

```java
private int height(TreeNode node) {
```

Această metodă calculează înălțimea unui subarbore și verifică dacă este balanced. Returnează înălțimea dacă este balanced, sau -1 dacă nu este.

```java
    if (node == null) {
        return 0;
    }
```

Această verificare este cazul de bază. Dacă nodul este null, înălțimea este 0 (un arbore gol are înălțimea 0).

```java
    int leftHeight = height(node.left);
    if (leftHeight == -1) return -1;
```

Aceste linii calculează înălțimea subarborelui stâng și verifică dacă este balanced. Să explicăm:

- `height(node.left)` calculează înălțimea subarborelui stâng
- Dacă rezultatul este -1, înseamnă că subarborele stâng nu este balanced
- În acest caz, returnăm imediat -1 (nu mai continuăm calculul)

De ce returnăm imediat? Pentru că dacă un subarbore nu este balanced, întregul arbore nu este balanced, deci nu mai are sens să continuăm calculul.

```java
    int rightHeight = height(node.right);
    if (rightHeight == -1) return -1;
```

Aceste linii fac același lucru pentru subarborele drept. Dacă subarborele drept nu este balanced, returnăm imediat -1.

```java
    if (Math.abs(leftHeight - rightHeight) > 1) {
        return -1;
    }
```

Această condiție verifică dacă diferența dintre înălțimi este prea mare. Să explicăm:

- `Math.abs(leftHeight - rightHeight)` calculează valoarea absolută a diferenței dintre înălțimi
- `> 1` verifică dacă diferența este mai mare decât 1
- Dacă da, arborele nu este balanced, deci returnăm -1

De exemplu, dacă `leftHeight = 3` și `rightHeight = 1`, atunci `Math.abs(3 - 1) = 2 > 1`, deci returnăm -1.

```java
    return 1 + Math.max(leftHeight, rightHeight);
```

Această linie returnează înălțimea subarborelui dacă este balanced. Să explicăm:

- `Math.max(leftHeight, rightHeight)` returnează înălțimea celui mai înalt subarbore
- `1 + ...` adaugă 1 pentru nodul curent
- Returnăm înălțimea normală (nu -1), ceea ce indică că subarborele este balanced

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru arborele balanced:
```
     3
   /   \
  9     20
       /  \
      15   7
```

**Apelul 1 (node = nodul 3):**
- `leftHeight = height(nodul 9)` → apelează recursiv
- `rightHeight = height(nodul 20)` → apelează recursiv

**Apelul 2 (node = nodul 9):**
- `leftHeight = height(null) = 0`
- `rightHeight = height(null) = 0`
- `Math.abs(0 - 0) = 0 <= 1`? `true`, continuăm
- Returnează `1 + max(0, 0) = 1`

**Apelul 3 (node = nodul 20):**
- `leftHeight = height(nodul 15)` → returnează 1
- `rightHeight = height(nodul 7)` → returnează 1
- `Math.abs(1 - 1) = 0 <= 1`? `true`, continuăm
- Returnează `1 + max(1, 1) = 2`

**Înapoi la apelul 1:**
- `leftHeight = 1`
- `rightHeight = 2`
- `Math.abs(1 - 2) = 1 <= 1`? `true`, continuăm
- Returnează `1 + max(1, 2) = 3`

**Rezultat:** `3 != -1` → `true` - arborele este balanced.

## Alt Exemplu - Nu Este Balanced

Să urmărim pentru arborele:
```
     1
   /   \
  2     2
 / \
3   3
   / \
  4   4
```

**Apelul 1 (node = nodul 1):**
- `leftHeight = height(nodul 2)` → apelează recursiv
- `rightHeight = height(nodul 2)` → apelează recursiv

**Apelul 2 (node = nodul 2 din stânga):**
- `leftHeight = height(nodul 3)` → returnează 1
- `rightHeight = height(nodul 3 cu copii)` → apelează recursiv

**Apelul 3 (node = nodul 3 cu copii):**
- `leftHeight = height(nodul 4)` → returnează 1
- `rightHeight = height(nodul 4)` → returnează 1
- `Math.abs(1 - 1) = 0 <= 1`? `true`
- Returnează `2`

**Înapoi la apelul 2:**
- `leftHeight = 1`
- `rightHeight = 2`
- `Math.abs(1 - 2) = 1 <= 1`? `true`
- Returnează `3`

**Înapoi la apelul 1:**
- `leftHeight = 3`
- `rightHeight = 1` (nodul 2 din dreapta are doar un nivel)
- `Math.abs(3 - 1) = 2 > 1`? `true`!
- Returnează `-1`

**Rezultat:** `-1 != -1`? `false` → `false` - arborele nu este balanced.

## De Ce Este Această Soluție Eficientă?

1. **Early termination**: Dacă un subarbore nu este balanced, ne oprim imediat, fără să continuăm calculul.

2. **O(n) timp**: Vizităm fiecare nod cel mult o dată.

3. **O(h) spațiu**: Folosim stiva de apeluri recursive, care are înălțimea h.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de noduri. Vizităm fiecare nod exact o dată.

- **Complexitatea spațiului**: O(h) - unde h este înălțimea arborelui. Spațiul este folosit pentru stiva de apeluri recursive.

## Concluzie

Această soluție este elegantă și eficientă. Folosim -1 ca sentinel pentru a indica dezechilibru, permițând early termination și calculând înălțimea și verificând balanța într-o singură parcurgere.
