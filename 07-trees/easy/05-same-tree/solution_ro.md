# Soluție Detaliată - Same Tree

## Ce Ne Cere Problema?

Problema ne cere să verificăm dacă două arbori binari sunt identici - adică au aceeași structură și aceleași valori în aceleași poziții.

De exemplu:
- Arborii `[1, 2, 3]` și `[1, 2, 3]` sunt identici
- Arborii `[1, 2]` și `[1, null, 2]` nu sunt identici (structura diferă)

## Ce Înseamnă "Same Tree"?

Două arbori sunt identici dacă:
1. Au aceeași structură (aceleași noduri în aceleași poziții)
2. Fiecare nod are aceeași valoare în ambele arbori

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Verificăm cazurile de bază (ambele null, unul null, valori diferite)
2. Dacă nodurile curente sunt identice, verificăm recursiv subarborii stâng și drept
3. Arborii sunt identici doar dacă toate nodurile corespunzătoare sunt identice

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public boolean isSameTree(TreeNode p, TreeNode q) {
```

Această linie definește funcția `isSameTree`. Funcția primește:
- `p` - primul arbore
- `q` - al doilea arbore

Funcția returnează `boolean` - `true` dacă arborii sunt identici, `false` dacă nu sunt.

```java
    if (p == null && q == null) {
        return true;
    }
```

Această verificare tratează cazul când ambele noduri sunt null. Să explicăm:

- `p == null && q == null` verifică dacă ambele noduri sunt null
- Dacă da, înseamnă că am ajuns la sfârșitul ambelor arbori în același loc, deci sunt identici până aici
- Returnăm `true`

De exemplu, dacă ambele arbori au ajuns la un copil care nu există (null), înseamnă că structura este identică în acel punct.

```java
    if (p == null || q == null) {
        return false;
    }
```

Această verificare tratează cazul când doar unul dintre noduri este null. Să explicăm:

- `p == null || q == null` verifică dacă unul dintre noduri este null (dar nu ambele, pentru că am verificat deja cazul când ambele sunt null)
- Dacă unul este null și celălalt nu, înseamnă că structura diferă (un arbore are un nod unde celălalt nu are)
- Returnăm `false`

De exemplu, dacă `p` are un copil stâng, dar `q` nu are copil stâng (este null), arborii nu sunt identici.

```java
    if (p.val != q.val) {
        return false;
    }
```

Această verificare compară valorile nodurilor. Să explicăm:

- `p.val != q.val` verifică dacă valorile nodurilor sunt diferite
- Dacă da, nodurile nu sunt identice, deci arborii nu sunt identici
- Returnăm `false`

De exemplu, dacă `p.val = 1` și `q.val = 2`, nodurile au valori diferite, deci arborii nu sunt identici.

```java
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
```

Această linie verifică recursiv subarborii. Să explicăm:

- `isSameTree(p.left, q.left)` verifică dacă subarborii stâng sunt identici
- `isSameTree(p.right, q.right)` verifică dacă subarborii drept sunt identici
- `&&` înseamnă "și" - ambele trebuie să fie `true` pentru ca arborii să fie identici
- Returnăm rezultatul (true dacă ambele subarbori sunt identici, false altfel)

De exemplu, dacă subarborele stâng este identic (`true`) și subarborele drept este identic (`true`), atunci `true && true = true`, deci arborii sunt identici.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru arborii:
```
Arbore 1:     1          Arbore 2:     1
           /   \                    /   \
          2     3                  2     3
```

**Apelul 1 (p = nodul 1, q = nodul 1):**
- `p == null && q == null`? `false`
- `p == null || q == null`? `false`
- `p.val != q.val`? `1 != 1`? `false`, continuăm
- `isSameTree(p.left, q.left)` → apelează recursiv pentru subarborii stâng
- `isSameTree(p.right, q.right)` → apelează recursiv pentru subarborii drept

**Apelul 2 (p = nodul 2, q = nodul 2):**
- `p == null && q == null`? `false`
- `p == null || q == null`? `false`
- `p.val != q.val`? `2 != 2`? `false`, continuăm
- `isSameTree(p.left, q.left)` → `isSameTree(null, null)` → returnează `true`
- `isSameTree(p.right, q.right)` → `isSameTree(null, null)` → returnează `true`
- Returnează `true && true = true`

**Apelul 3 (p = nodul 3, q = nodul 3):**
- Similar cu apelul 2, returnează `true`

**Înapoi la apelul 1:**
- `isSameTree(p.left, q.left)` → `true`
- `isSameTree(p.right, q.right)` → `true`
- Returnează `true && true = true`

**Rezultat:** `true` - arborii sunt identici.

## Alt Exemplu - Nu Sunt Identici

Să urmărim pentru arborii:
```
Arbore 1:     1          Arbore 2:     1
           /   \                    /   \
          2     3                  2     4
```

**Apelul 1 (p = nodul 1, q = nodul 1):**
- `p.val == q.val`? `true`, continuăm
- `isSameTree(p.left, q.left)` → `true` (ambele sunt nodul 2)
- `isSameTree(p.right, q.right)` → apelează recursiv

**Apelul 2 (p = nodul 3, q = nodul 4):**
- `p.val != q.val`? `3 != 4`? `true`!
- Returnăm `false`

**Înapoi la apelul 1:**
- `isSameTree(p.right, q.right)` → `false`
- Returnează `true && false = false`

**Rezultat:** `false` - arborii nu sunt identici (valorile diferă).

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Vizităm fiecare nod exact o dată.

2. **O(h) spațiu**: Unde h este înălțimea arborelui. Spațiul este folosit pentru stiva de apeluri recursive.

3. **Early termination**: Dacă găsim o diferență, returnăm imediat `false`, fără să continuăm verificarea.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de noduri. Vizităm fiecare nod exact o dată.

- **Complexitatea spațiului**: O(h) - unde h este înălțimea arborelui. Spațiul este folosit pentru stiva de apeluri recursive.

## Concluzie

Această soluție este simplă și eficientă. Verificăm recursiv dacă nodurile corespunzătoare sunt identice, returnând `false` imediat când găsim o diferență, obținând astfel o verificare eficientă a identității arborilor.
