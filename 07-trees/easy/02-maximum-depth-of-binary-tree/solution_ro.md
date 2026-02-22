# Soluție Detaliată - Maximum Depth of Binary Tree

## Ce Ne Cere Problema?

Problema ne cere să găsim adâncimea maximă (înălțimea) a unui arbore binar. Adâncimea unui arbore este numărul de noduri de la rădăcină până la cel mai îndepărtat nod frunză (nod fără copii).

De exemplu, dacă avem arborele:
```
     3
   /   \
  9     20
       /  \
      15   7
```

Adâncimea este 3 (rădăcina este la nivelul 1, nodurile 9 și 20 sunt la nivelul 2, nodurile 15 și 7 sunt la nivelul 3).

## Ce Este Adâncimea unui Arbore?

Adâncimea (sau înălțimea) unui arbore este numărul de nivele din arbore, sau numărul de noduri de pe cel mai lung drum de la rădăcină la o frunză.

Gândiți-vă la adâncime ca la numărul de etaje ale unei clădiri - rădăcina este la parter (nivelul 1), copiii sunt la etajul 1 (nivelul 2), și așa mai departe.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Folosim recursivitate pentru a parcurge arborele
2. Pentru fiecare nod, calculăm adâncimea subarborelui stâng și a subarborelui drept
3. Adâncimea nodului curent este 1 (nodul însuși) plus maximul dintre adâncimile subarborilor
4. Cazul de bază: dacă nodul este null, adâncimea este 0

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int maxDepth(TreeNode root) {
```

Această linie definește funcția `maxDepth`. Funcția primește:
- `root` - rădăcina arborelui

Funcția returnează `int` - adâncimea maximă a arborelui.

```java
    if (root == null) {
        return 0;
    }
```

Această verificare este cazul de bază al recursivității. Să explicăm:

- `root == null` verifică dacă nodul curent este null (nu există)
- Dacă nodul este null, adâncimea este 0 (nu există nod, deci nu adaugă la adâncime)

De ce este important? Pentru că recursivitatea trebuie să se oprească undeva. Când ajungem la un nod null (un copil care nu există), ne oprim și returnăm 0.

```java
    int leftDepth = maxDepth(root.left);
    int rightDepth = maxDepth(root.right);
```

Aceste linii calculează recursiv adâncimile subarborilor. Să explicăm:

- `maxDepth(root.left)` apelează funcția pentru copilul stâng al nodului curent
- Funcția va calcula adâncimea întregului subarbore stâng și va returna rezultatul
- `int leftDepth = ...` stochează adâncimea subarborelui stâng
- Similar pentru `maxDepth(root.right)` - calculează adâncimea subarborelui drept

De exemplu, dacă subarborele stâng are adâncimea 2 și subarborele drept are adâncimea 3, atunci `leftDepth = 2` și `rightDepth = 3`.

```java
    return 1 + Math.max(leftDepth, rightDepth);
```

Această linie calculează adâncimea nodului curent. Să explicăm:

- `Math.max(leftDepth, rightDepth)` returnează maximul dintre adâncimile subarborilor
- `1 + ...` adaugă 1 pentru nodul curent (nodul însuși adaugă un nivel la adâncime)
- `return ...` returnează adâncimea totală

De ce adăugăm 1? Pentru că nodul curent este un nivel în plus față de subarborii săi. De exemplu, dacă subarborele stâng are adâncimea 2 și subarborele drept are adâncimea 3, atunci adâncimea nodului curent este 1 + max(2, 3) = 1 + 3 = 4.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru arborele:
```
     3
   /   \
  9     20
       /  \
      15   7
```

**Apelul 1 (root = nodul 3):**
- `root == null`? `false`, continuăm
- `leftDepth = maxDepth(nodul 9)` → apelează recursiv
- `rightDepth = maxDepth(nodul 20)` → apelează recursiv

**Apelul 2 (root = nodul 9):**
- `root == null`? `false`, continuăm
- `leftDepth = maxDepth(null)` → returnează `0`
- `rightDepth = maxDepth(null)` → returnează `0`
- `return 1 + max(0, 0) = 1 + 0 = 1`
- Returnează `1`

**Apelul 3 (root = nodul 20):**
- `root == null`? `false`, continuăm
- `leftDepth = maxDepth(nodul 15)` → apelează recursiv
- `rightDepth = maxDepth(nodul 7)` → apelează recursiv

**Apelul 4 (root = nodul 15):**
- `root == null`? `false`, continuăm
- `leftDepth = maxDepth(null)` → returnează `0`
- `rightDepth = maxDepth(null)` → returnează `0`
- `return 1 + max(0, 0) = 1`
- Returnează `1`

**Apelul 5 (root = nodul 7):**
- Similar cu nodul 15, returnează `1`

**Înapoi la apelul 3 (root = nodul 20):**
- `leftDepth = 1` (de la nodul 15)
- `rightDepth = 1` (de la nodul 7)
- `return 1 + max(1, 1) = 1 + 1 = 2`
- Returnează `2`

**Înapoi la apelul 1 (root = nodul 3):**
- `leftDepth = 1` (de la nodul 9)
- `rightDepth = 2` (de la nodul 20)
- `return 1 + max(1, 2) = 1 + 2 = 3`
- Returnează `3`

**Rezultat:** `3` - adâncimea maximă este 3.

## Alt Exemplu - Arbore Liniar

Să urmărim pentru arborele liniar `1 -> 2 -> 3 -> null`:

**Apelul 1 (root = nodul 1):**
- `leftDepth = maxDepth(null)` → `0`
- `rightDepth = maxDepth(nodul 2)` → apelează recursiv

**Apelul 2 (root = nodul 2):**
- `leftDepth = maxDepth(null)` → `0`
- `rightDepth = maxDepth(nodul 3)` → apelează recursiv

**Apelul 3 (root = nodul 3):**
- `leftDepth = maxDepth(null)` → `0`
- `rightDepth = maxDepth(null)` → `0`
- `return 1 + max(0, 0) = 1`

**Înapoi la apelul 2:**
- `rightDepth = 1`
- `return 1 + max(0, 1) = 2`

**Înapoi la apelul 1:**
- `rightDepth = 2`
- `return 1 + max(0, 2) = 3`

**Rezultat:** `3` - adâncimea este 3.

## De Ce Este Această Soluție Eficientă?

1. **Parcurgem fiecare nod o singură dată**: Vizităm fiecare nod exact o dată.

2. **O(n) timp**: Unde n este numărul de noduri. Parcurgem toate nodurile o singură dată.

3. **O(h) spațiu**: Unde h este înălțimea arborelui. Spațiul este folosit pentru stiva de apeluri recursive.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de noduri. Vizităm fiecare nod exact o dată.

- **Complexitatea spațiului**: O(h) - unde h este înălțimea arborelui. Spațiul este folosit pentru stiva de apeluri recursive. În cel mai rău caz (arbore liniar), O(n). În cel mai bun caz (arbore complet), O(log n).

## Concluzie

Această soluție este elegantă și eficientă. Folosim recursivitate pentru a calcula adâncimea subarborilor și apoi calculăm adâncimea nodului curent ca fiind 1 plus maximul dintre adâncimile subarborilor.
