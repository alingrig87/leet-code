# Soluție Detaliată - Remove Duplicates from Sorted List

## Ce Ne Cere Problema?

Problema ne cere să eliminăm duplicatele dintr-o listă înlănțuită sortată, păstrând doar o singură apariție a fiecărei valori. De exemplu, dacă avem `1 -> 1 -> 2 -> 3 -> 3`, după eliminarea duplicatelor ar trebui să obținem `1 -> 2 -> 3`.

## De Ce Este Important Că Lista Este Sortată?

Când lista este sortată, toate duplicatele sunt unul lângă altul. De exemplu, în `1 -> 1 -> 2 -> 3 -> 3`, duplicatele sunt consecutive:
- Primul 1 și al doilea 1 sunt unul lângă altul
- Primul 3 și al doilea 3 sunt unul lângă altul

Această proprietate ne permite să verificăm doar dacă nodul curent este egal cu următorul nod, fără să trebuiască să verificăm toate nodurile anterioare.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Parcurgem lista cu un pointer
2. Pentru fiecare nod, verificăm dacă valoarea sa este egală cu valoarea următorului nod
3. Dacă sunt egale, "sărim" peste următorul nod (legăm nodul curent la nodul de după următorul)
4. Dacă nu sunt egale, mutăm pointer-ul la următorul nod
5. Continuăm până când am parcurs toată lista

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public ListNode deleteDuplicates(ListNode head) {
```

Această linie definește funcția `deleteDuplicates`. Funcția primește:
- `head` - primul nod al listei sortate

Funcția returnează `ListNode` - capul listei după eliminarea duplicatelor.

```java
    if (head == null || head.next == null) {
        return head;
    }
```

Această verificare tratează cazurile speciale. Să explicăm:

- `head == null` - lista este goală, nu avem ce elimina, returnăm null
- `head.next == null` - lista are un singur nod, nu poate avea duplicate, returnăm același nod

```java
    ListNode current = head;
```

Această linie inițializează pointer-ul curent. `current` va parcurge lista și va elimina duplicatele.

```java
    while (current != null && current.next != null) {
```

Această buclă continuă cât timp mai avem noduri de procesat. Să explicăm:

- `current != null` - nodul curent există
- `current.next != null` - următorul nod există (putem compara cu el)
- `&&` înseamnă "și" - bucla continuă doar dacă ambele condiții sunt adevărate

De ce verificăm `current.next != null`? Pentru că comparăm `current.val` cu `current.next.val`, deci avem nevoie ca `current.next` să existe.

```java
        if (current.val == current.next.val) {
            current.next = current.next.next;
        }
```

Această parte elimină duplicatele. Să explicăm pas cu pas:

- `current.val == current.next.val` compară valoarea nodului curent cu valoarea următorului nod
- Dacă sunt egale, înseamnă că avem duplicate consecutive
- `current.next = current.next.next` "sare" peste următorul nod (nodul duplicat)

De exemplu, dacă avem `1 -> 1 -> 2` și `current` este la primul nod 1:
- `current.val = 1`, `current.next.val = 1`
- `1 == 1`? `true`, deci eliminăm al doilea nod 1
- `current.next = current.next.next` leagă primul nod 1 direct la nodul 2
- Rezultat: `1 -> 2`

De ce nu mutăm `current`? Pentru că după eliminare, trebuie să verificăm din nou dacă nodul curent este egal cu noul următor nod (care ar putea fi tot un duplicat). De exemplu, dacă avem `1 -> 1 -> 1 -> 2`, după prima eliminare avem `1 -> 1 -> 2`, și trebuie să verificăm din nou dacă primul 1 este egal cu al doilea 1.

```java
        else {
            current = current.next;
        }
```

Această parte mută pointer-ul când nu avem duplicate. Să explicăm:

- `else` înseamnă că `current.val != current.next.val` (nu sunt duplicate)
- `current = current.next` mută pointer-ul la următorul nod

De ce mutăm pointer-ul? Pentru că nodul curent nu are duplicate, deci putem trece la următorul nod pentru a continua verificarea.

```java
    return head;
```

Această linie returnează capul listei. Deoarece eliminăm duplicatele prin modificarea legăturilor, capul listei rămâne același (primul nod nu este niciodată eliminat, deoarece verificăm duplicatele înainte de el).

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru lista `1 -> 1 -> 2 -> 3 -> 3`:

**Inițializare:**
- `current = nodul 1` (primul)
- Lista: `1 -> 1 -> 2 -> 3 -> 3`

**Iterația 1:**
- `current.val = 1`, `current.next.val = 1`
- `1 == 1`? `true`
- `current.next = current.next.next` → eliminăm al doilea nod 1
- `current` rămâne la primul nod 1
- Lista: `1 -> 2 -> 3 -> 3`

**Iterația 2:**
- `current.val = 1`, `current.next.val = 2`
- `1 == 2`? `false`
- `current = current.next` → `current = nodul 2`
- Lista: `1 -> 2 -> 3 -> 3`

**Iterația 3:**
- `current.val = 2`, `current.next.val = 3`
- `2 == 3`? `false`
- `current = current.next` → `current = nodul 3` (primul)
- Lista: `1 -> 2 -> 3 -> 3`

**Iterația 4:**
- `current.val = 3`, `current.next.val = 3`
- `3 == 3`? `true`
- `current.next = current.next.next` → eliminăm al doilea nod 3
- `current` rămâne la primul nod 3
- Lista: `1 -> 2 -> 3`

**După buclă:**
- `current.next = null`, deci bucla se termină
- Returnăm `head` (nodul 1)

**Rezultat:** `1 -> 2 -> 3` - duplicatele au fost eliminate.

## Alt Exemplu - Toate Sunt Duplicate

Să urmărim pentru lista `1 -> 1 -> 1`:

**Inițializare:**
- `current = nodul 1` (primul)

**Iterația 1:**
- `1 == 1`? `true`
- Eliminăm al doilea nod 1
- Lista: `1 -> 1`
- `current` rămâne la primul nod 1

**Iterația 2:**
- `1 == 1`? `true`
- Eliminăm al doilea nod 1
- Lista: `1`
- `current.next = null`, bucla se termină

**Rezultat:** `1` - toate duplicatele au fost eliminate, rămâne doar un nod.

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Parcurgem lista o singură dată, făcând O(1) operații pentru fiecare nod.

2. **O(1) spațiu**: Folosim doar un pointer, nu creăm noduri noi sau structuri de date suplimentare.

3. **Modifică lista direct**: Eliminăm duplicatele prin modificarea legăturilor, fără a crea o listă nouă.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de noduri. Parcurgem lista o singură dată.

- **Complexitatea spațiului**: O(1) - folosim doar un pointer, nu creăm structuri de date suplimentare.

## Concluzie

Această soluție este simplă și eficientă. Folosim un singur pointer pentru a parcurge lista și eliminăm duplicatele consecutive prin modificarea legăturilor, obținând astfel o listă fără duplicate într-o singură parcurgere.
