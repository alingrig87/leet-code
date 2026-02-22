# Soluție Detaliată - Palindrome Linked List

## Ce Ne Cere Problema?

Problema ne cere să verificăm dacă o listă înlănțuită este un palindrom. O listă este palindrom dacă se citește la fel de la început la sfârșit și de la sfârșit la început.

De exemplu:
- `1 -> 2 -> 2 -> 1` - este palindrom (se citește la fel în ambele direcții)
- `1 -> 2 -> 3` - nu este palindrom

## De Ce Este Dificilă Această Problemă?

Într-o listă înlănțuită, nu putem accesa direct elementele de la sfârșit (nu putem merge "înapoi"). Trebuie să găsim o modalitate de a compara primul element cu ultimul, al doilea cu penultimul, etc., fără să putem accesa direct elementele de la sfârșit.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Găsim mijlocul listei folosind algoritmul slow/fast pointer
2. Inversăm a doua jumătate a listei
3. Comparăm prima jumătate cu a doua jumătate inversată
4. Dacă toate elementele se potrivesc, lista este palindrom

## Ce Este Algoritmul Slow/Fast Pointer?

Algoritmul slow/fast pointer folosește doi pointeri care se mișcă cu viteze diferite:
- **Slow pointer**: se mișcă cu 1 pas la fiecare iterație
- **Fast pointer**: se mișcă cu 2 pași la fiecare iterație

Când fast pointer ajunge la sfârșit, slow pointer este exact la mijloc.

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public boolean isPalindrome(ListNode head) {
```

Această linie definește funcția `isPalindrome`. Funcția primește:
- `head` - primul nod al listei

Funcția returnează `boolean` - `true` dacă lista este palindrom, `false` dacă nu este.

```java
    if (head == null || head.next == null) {
        return true;
    }
```

Această verificare tratează cazurile speciale. O listă goală sau cu un singur nod este considerată palindrom.

```java
    ListNode slow = head;
    ListNode fast = head;
```

Aceste linii inițializează cei doi pointeri. Amândoi încep de la începutul listei, dar se vor mișca cu viteze diferite.

```java
    while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
```

Această buclă găsește mijlocul listei. Să explicăm:

- `fast.next != null && fast.next.next != null` verifică dacă fast pointer poate să facă încă 2 pași
- `slow = slow.next` mută slow pointer cu 1 pas
- `fast = fast.next.next` mută fast pointer cu 2 pași

Când bucla se termină, slow pointer este la mijlocul listei (sau la nodul de dinainte de mijloc, dacă lista are număr par de noduri).

De exemplu, pentru lista `1 -> 2 -> 2 -> 1`:
- Inițial: `slow = nodul 1`, `fast = nodul 1`
- Iterația 1: `slow = nodul 2`, `fast = nodul 2` (al doilea 2)
- Iterația 2: `fast.next.next = null`, bucla se termină
- `slow` este la primul nod 2 (mijlocul listei)

```java
    ListNode secondHalf = reverseList(slow.next);
    slow.next = null;
```

Aceste linii inversează a doua jumătate a listei. Să explicăm:

- `slow.next` este începutul celei de-a doua jumătăți
- `reverseList(slow.next)` inversează a doua jumătate și returnează noul cap
- `slow.next = null` "taie" legătura între cele două jumătăți, separând lista în două

De exemplu, dacă lista este `1 -> 2 -> 2 -> 1` și `slow` este la primul 2:
- `slow.next` este al doilea 2 (începutul celei de-a doua jumătăți)
- După inversare: `1 -> 2` și `1 -> 2` (a doua jumătate inversată)
- `slow.next = null` separă lista: `1 -> 2` și `1 -> 2`

```java
    ListNode firstHalf = head;
    while (secondHalf != null) {
        if (firstHalf.val != secondHalf.val) {
            return false;
        }
        firstHalf = firstHalf.next;
        secondHalf = secondHalf.next;
    }
```

Această buclă compară cele două jumătăți. Să explicăm:

- `ListNode firstHalf = head` inițializează pointer-ul pentru prima jumătate
- `while (secondHalf != null)` continuă cât timp mai avem noduri în a doua jumătate
- `firstHalf.val != secondHalf.val` compară valorile nodurilor
- Dacă nu se potrivesc, lista nu este palindrom, deci returnăm `false`
- `firstHalf = firstHalf.next` și `secondHalf = secondHalf.next` mută ambele pointeri înainte

De exemplu, pentru `1 -> 2` și `1 -> 2`:
- Iterația 1: `1 == 1`? `true`, continuăm
- Iterația 2: `2 == 2`? `true`, continuăm
- `secondHalf = null`, bucla se termină

```java
    return true;
```

Această linie se execută doar dacă toate elementele s-au potrivit. În acest caz, lista este palindrom, deci returnăm `true`.

```java
private ListNode reverseList(ListNode head) {
```

Această linie definește funcția helper `reverseList` care inversează o listă. Funcționează similar cu problema "Reverse Linked List".

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru lista `1 -> 2 -> 2 -> 1`:

**Pasul 1: Găsim mijlocul**
- `slow = nodul 1`, `fast = nodul 1`
- Iterația 1: `slow = nodul 2` (primul), `fast = nodul 2` (al doilea)
- Iterația 2: `fast.next.next = null`, bucla se termină
- `slow` este la primul nod 2

**Pasul 2: Inversăm a doua jumătate**
- `slow.next` este al doilea nod 2
- Inversăm: `1 -> 2` (a doua jumătate devine `1 -> 2`)
- `slow.next = null` separă lista: `1 -> 2` și `1 -> 2`

**Pasul 3: Comparăm**
- `firstHalf = nodul 1`, `secondHalf = nodul 1` (din a doua jumătate inversată)
- `1 == 1`? `true`, continuăm
- `firstHalf = nodul 2`, `secondHalf = nodul 2`
- `2 == 2`? `true`, continuăm
- `secondHalf = null`, bucla se termină

**Rezultat:** `true` - lista este palindrom.

## Alt Exemplu - Nu Este Palindrom

Să urmărim pentru lista `1 -> 2 -> 3`:

**Pasul 1: Găsim mijlocul**
- `slow = nodul 2` (după iterații)

**Pasul 2: Inversăm a doua jumătate**
- A doua jumătate: `3` (după inversare rămâne `3`)

**Pasul 3: Comparăm**
- `firstHalf = nodul 1`, `secondHalf = nodul 3`
- `1 == 3`? `false`!
- Returnăm `false`

**Rezultat:** `false` - lista nu este palindrom.

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Parcurgem lista de două ori (o dată pentru a găsi mijlocul, o dată pentru inversare și comparare), dar fiecare parcurgere este O(n), deci total O(n).

2. **O(1) spațiu**: Nu creăm noduri noi, ci doar reorganizăm legăturile existente.

3. **Modifică lista temporar**: Inversăm a doua jumătate temporar pentru comparație, apoi o putem inversa din nou dacă este necesar (problema nu cere să păstrăm lista neschimbată).

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de noduri. Parcurgem lista de câteva ori, dar fiecare parcurgere este O(n).

- **Complexitatea spațiului**: O(1) - folosim doar câteva pointeri, nu creăm structuri de date suplimentare.

## Concluzie

Această soluție este elegantă și eficientă. Găsim mijlocul listei, inversăm a doua jumătate și comparăm cu prima jumătate, verificând astfel dacă lista este palindrom fără a folosi spațiu suplimentar.
