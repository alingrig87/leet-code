# Soluție Detaliată - Reverse Linked List

## Ce Ne Cere Problema?

Problema ne cere să inversăm o listă înlănțuită (linked list). Adică, dacă lista este `1 -> 2 -> 3 -> 4 -> 5`, după inversare ar trebui să fie `5 -> 4 -> 3 -> 2 -> 1`.

## Ce Este o Listă Înlănțuită?

O listă înlănțuită este o structură de date formată din noduri, unde fiecare nod conține o valoare și un pointer către următorul nod. Primul nod se numește "head" (cap), iar ultimul nod are pointer-ul setat la `null` (indicând sfârșitul listei).

De exemplu, lista `1 -> 2 -> 3` este formată din:
- Nodul 1: valoare = 1, next = nodul 2
- Nodul 2: valoare = 2, next = nodul 3
- Nodul 3: valoare = 3, next = null

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Folosim trei pointeri: `prev` (anterior), `curr` (curent), `next` (următor)
2. Parcurgem lista și inversăm legăturile între noduri
3. Pentru fiecare nod, schimbăm pointer-ul `next` să pointeze către nodul anterior
4. Mutăm pointerii înainte și repetăm

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public ListNode reverseList(ListNode head) {
```

Această linie definește funcția `reverseList`. Funcția primește:
- `head` - primul nod al listei (capul listei)

Funcția returnează `ListNode` - noul cap al listei inversate.

```java
    if (head == null || head.next == null) {
        return head;
    }
```

Această verificare tratează cazurile speciale. Să explicăm:

- `head == null` - lista este goală, nu avem ce inversa, returnăm null
- `head.next == null` - lista are un singur nod, nu avem ce inversa, returnăm același nod

```java
    ListNode prev = null;
    ListNode curr = head;
    ListNode next = null;
```

Aceste linii inițializează cei trei pointeri. Să explicăm:

- `prev = null` - pointer pentru nodul anterior, inițial null (primul nod nu are anterior)
- `curr = head` - pointer pentru nodul curent, începe cu primul nod
- `next = null` - pointer pentru nodul următor, va fi setat în buclă

```java
    while (curr != null) {
```

Această buclă continuă cât timp mai avem noduri de procesat. Când `curr` devine `null`, am terminat de procesat toate nodurile.

```java
        next = curr.next;
```

Această linie salvează referința către următorul nod înainte de a inversa legătura. Să explicăm de ce este important:

- `curr.next` este pointer-ul către următorul nod
- Dacă inversăm legătura imediat, vom pierde referința către următorul nod
- Prin salvarea lui în `next`, putem continua să parcurgem lista după inversare

De exemplu, dacă avem `1 -> 2 -> 3` și `curr` este la nodul 1:
- `next = curr.next` salvează referința către nodul 2
- Dacă nu am salva, când inversăm legătura, am pierde nodul 2

```java
        curr.next = prev;
```

Această linie inversează legătura. Să explicăm:

- `curr.next` este pointer-ul nodului curent către următorul nod
- `prev` este nodul anterior
- `curr.next = prev` schimbă pointer-ul să pointeze către nodul anterior în loc de următorul

De exemplu, dacă avem `1 -> 2` și `curr` este la nodul 2:
- `curr.next = prev` schimbă legătura astfel încât nodul 2 să pointeze către nodul 1
- Rezultatul: `1 <- 2` (legătura este inversată)

```java
        prev = curr;
        curr = next;
```

Aceste linii mută pointerii înainte. Să explicăm:

- `prev = curr` - nodul anterior devine nodul curent (pentru următoarea iterație)
- `curr = next` - nodul curent devine nodul următor (continuăm parcurgerea)

După aceste operații, pointerii sunt pregătiți pentru următoarea iterație.

```java
    return prev;
```

Această linie returnează noul cap al listei. Să explicăm de ce:

- După ce am terminat bucla, `curr` este `null` (am terminat de procesat toate nodurile)
- `prev` este ultimul nod procesat, care este acum primul nod al listei inversate (noul cap)

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru lista `1 -> 2 -> 3 -> null`:

**Inițializare:**
- `prev = null`
- `curr = nodul 1`
- `next = null`
- Lista: `1 -> 2 -> 3 -> null`

**Iterația 1 (curr = nodul 1):**
- `next = curr.next` → `next = nodul 2`
- `curr.next = prev` → `nodul 1.next = null`
- `prev = curr` → `prev = nodul 1`
- `curr = next` → `curr = nodul 2`
- Lista: `null <- 1    2 -> 3 -> null`

**Iterația 2 (curr = nodul 2):**
- `next = curr.next` → `next = nodul 3`
- `curr.next = prev` → `nodul 2.next = nodul 1`
- `prev = curr` → `prev = nodul 2`
- `curr = next` → `curr = nodul 3`
- Lista: `null <- 1 <- 2    3 -> null`

**Iterația 3 (curr = nodul 3):**
- `next = curr.next` → `next = null`
- `curr.next = prev` → `nodul 3.next = nodul 2`
- `prev = curr` → `prev = nodul 3`
- `curr = next` → `curr = null`
- Lista: `null <- 1 <- 2 <- 3`

**După buclă:**
- `curr = null`, deci bucla se termină
- `prev = nodul 3` (noul cap)
- Returnăm `nodul 3`

**Rezultat:** Lista inversată: `3 -> 2 -> 1 -> null`

## De Ce Este Această Soluție Eficientă?

1. **Parcurgem lista o singură dată**: Inversăm legăturile în timp ce parcurgem lista, fără a o parcurge de mai multe ori.

2. **O(1) spațiu suplimentar**: Folosim doar câteva pointeri, nu creăm noduri noi sau structuri de date suplimentare.

3. **Modificăm lista direct**: Nu creăm o listă nouă, ci inversăm legăturile în lista existentă.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de noduri. Parcurgem lista o singură dată.

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile pentru pointeri, nu creăm structuri de date suplimentare.

## Concluzie

Această soluție este elegantă și eficientă. Folosim trei pointeri pentru a inversa legăturile între noduri în timp ce parcurgem lista, obținând lista inversată într-o singură parcurgere și fără spațiu suplimentar.
