# Soluție Detaliată - Merge Two Sorted Lists

## Ce Ne Cere Problema?

Problema ne cere să combinăm (merge) două liste înlănțuite sortate într-una singură, păstrând ordinea sortată. De exemplu, dacă avem `list1 = 1 -> 2 -> 4` și `list2 = 1 -> 3 -> 4`, după combinare ar trebui să obținem `1 -> 1 -> 2 -> 3 -> 4 -> 4`.

## Ce Este o Listă Înlănțuită Sortată?

O listă înlănțuită sortată este o listă unde valorile nodurilor sunt în ordine crescătoare. De exemplu, `1 -> 2 -> 4` este sortată pentru că 1 < 2 < 4.

## Ce Este un Nod Dummy?

Un nod dummy (fictiv) este un nod temporar pe care îl creăm pentru a simplifica codul. Nu conține o valoare importantă, dar ne ajută să construim lista rezultată mai ușor. La final, returnăm `dummy.next`, care este primul nod real al listei rezultate.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Creăm un nod dummy pentru a începe construirea listei rezultate
2. Comparăm valorile nodurilor curente din ambele liste
3. Legăm nodul cu valoarea mai mică la lista rezultată
4. Mutăm pointer-ul din lista corespunzătoare
5. Continuăm până când una dintre liste se termină
6. Legăm restul nodurilor din lista care nu s-a terminat

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
```

Această linie definește funcția `mergeTwoLists`. Funcția primește:
- `list1` - prima listă sortată
- `list2` - a doua listă sortată

Funcția returnează `ListNode` - capul listei combinate.

```java
    if (list1 == null) return list2;
    if (list2 == null) return list1;
```

Aceste verificări tratează cazurile când una dintre liste este goală. Să explicăm:

- Dacă `list1` este null (goală), returnăm `list2` (lista combinată este doar `list2`)
- Dacă `list2` este null (goală), returnăm `list1` (lista combinată este doar `list1`)

De ce facem asta? Pentru că dacă o listă este goală, nu avem ce combina - lista combinată este pur și simplu cealaltă listă.

```java
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;
```

Aceste linii creează nodul dummy și pointer-ul curent. Să explicăm:

- `ListNode dummy = new ListNode(0)` creează un nod nou cu valoarea 0 (valoarea nu este importantă)
- `ListNode current = dummy` inițializează pointer-ul `current` să pointeze către nodul dummy

`current` va fi pointer-ul care construiește lista rezultată. Începem de la dummy și adăugăm noduri după el.

```java
    while (list1 != null && list2 != null) {
```

Această buclă continuă cât timp ambele liste mai au noduri. Să explicăm:

- `list1 != null` înseamnă că mai avem noduri în prima listă
- `list2 != null` înseamnă că mai avem noduri în a doua listă
- `&&` înseamnă "și" - bucla continuă doar dacă ambele condiții sunt adevărate

Când una dintre liste se termină (devine null), bucla se oprește.

```java
        if (list1.val <= list2.val) {
            current.next = list1;
            list1 = list1.next;
        } else {
            current.next = list2;
            list2 = list2.next;
        }
```

Această parte compară valorile și leagă nodul mai mic. Să explicăm pas cu pas:

- `list1.val <= list2.val` compară valoarea nodului curent din `list1` cu valoarea nodului curent din `list2`
- Dacă valoarea din `list1` este mai mică sau egală:
  - `current.next = list1` leagă nodul din `list1` la lista rezultată
  - `list1 = list1.next` mută pointer-ul `list1` la următorul nod
- Dacă valoarea din `list2` este mai mică:
  - `current.next = list2` leagă nodul din `list2` la lista rezultată
  - `list2 = list2.next` mută pointer-ul `list2` la următorul nod

De ce comparăm și legăm nodul mai mic? Pentru că vrem să păstrăm ordinea sortată - nodurile mai mici trebuie să fie înaintea celor mai mari.

```java
        current = current.next;
```

Această linie mută pointer-ul `current` la nodul tocmai adăugat. După ce am legat un nod la lista rezultată, `current` trebuie să pointeze către acel nod pentru a putea adăuga următorul nod după el.

```java
    if (list1 != null) {
        current.next = list1;
    } else {
        current.next = list2;
    }
```

Aceste linii leagă restul nodurilor din lista care nu s-a terminat. Să explicăm:

- După ce bucla principală se termină, una dintre liste s-a terminat (este null)
- Cealaltă listă mai poate avea noduri
- `if (list1 != null)` verifică dacă `list1` mai are noduri
- Dacă da, leagă toate nodurile rămase din `list1` la lista rezultată
- Dacă nu (adică `list2` mai are noduri), leagă toate nodurile rămase din `list2`

De ce facem asta? Pentru că nodurile rămase sunt deja sortate și mai mari decât toate nodurile pe care le-am procesat, deci le putem lega direct la sfârșit.

```java
    return dummy.next;
```

Această linie returnează primul nod real al listei rezultate. Să explicăm:

- `dummy` este nodul fictiv pe care l-am creat
- `dummy.next` este primul nod real pe care l-am adăugat (primul nod al listei rezultate)
- Returnăm `dummy.next` pentru a returna lista combinată

De ce nu returnăm `dummy`? Pentru că `dummy` este doar un nod temporar cu valoarea 0, care nu face parte din lista reală. `dummy.next` este primul nod real.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `list1 = 1 -> 2 -> 4` și `list2 = 1 -> 3 -> 4`:

**Inițializare:**
- `dummy = nodul 0`
- `current = dummy`
- `list1 = nodul 1 (val=1)`
- `list2 = nodul 1 (val=1)`

**Iterația 1:**
- Comparăm: `1 <= 1` → `true`
- `current.next = list1` → `dummy.next = nodul 1 (val=1)`
- `list1 = list1.next` → `list1 = nodul 2 (val=2)`
- `current = current.next` → `current = nodul 1 (val=1)`
- Lista rezultată: `0 -> 1`

**Iterația 2:**
- Comparăm: `2 <= 1` → `false`
- `current.next = list2` → `nodul 1.next = nodul 1 (val=1) din list2`
- `list2 = list2.next` → `list2 = nodul 3 (val=3)`
- `current = current.next` → `current = nodul 1 (val=1) din list2`
- Lista rezultată: `0 -> 1 -> 1`

**Iterația 3:**
- Comparăm: `2 <= 3` → `true`
- `current.next = list1` → `nodul 1.next = nodul 2 (val=2)`
- `list1 = list1.next` → `list1 = nodul 4 (val=4)`
- `current = current.next` → `current = nodul 2 (val=2)`
- Lista rezultată: `0 -> 1 -> 1 -> 2`

**Iterația 4:**
- Comparăm: `4 <= 3` → `false`
- `current.next = list2` → `nodul 2.next = nodul 3 (val=3)`
- `list2 = list2.next` → `list2 = nodul 4 (val=4)`
- `current = current.next` → `current = nodul 3 (val=3)`
- Lista rezultată: `0 -> 1 -> 1 -> 2 -> 3`

**Iterația 5:**
- Comparăm: `4 <= 4` → `true`
- `current.next = list1` → `nodul 3.next = nodul 4 (val=4)`
- `list1 = list1.next` → `list1 = null`
- `current = current.next` → `current = nodul 4 (val=4)`
- Lista rezultată: `0 -> 1 -> 1 -> 2 -> 3 -> 4`

**După buclă:**
- `list1 = null`, deci bucla se termină
- `list2 = nodul 4 (val=4)` mai are noduri
- `current.next = list2` → `nodul 4.next = nodul 4 (val=4) din list2`
- Lista rezultată: `0 -> 1 -> 1 -> 2 -> 3 -> 4 -> 4`

**Returnăm:**
- `dummy.next = nodul 1 (val=1)` (primul nod real)
- Lista finală: `1 -> 1 -> 2 -> 3 -> 4 -> 4`

## De Ce Este Această Soluție Eficientă?

1. **Parcurgem fiecare listă o singură dată**: Nu comparăm fiecare nod cu toate celelalte, ci doar comparăm nodurile curente și le legăm în ordine.

2. **O(1) spațiu suplimentar**: Folosim doar câteva pointeri și un nod dummy, nu creăm noduri noi (doar le reorganizăm).

3. **Simplificare cu dummy**: Nodul dummy elimină nevoia de a trata separat cazul când lista rezultată este goală.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n + m) - unde n este numărul de noduri din `list1` și m este numărul de noduri din `list2`. Parcurgem fiecare nod o singură dată.

- **Complexitatea spațiului**: O(1) - folosim doar câteva pointeri și un nod dummy, nu creăm structuri de date suplimentare (nodurile există deja, doar le reorganizăm).

## Concluzie

Această soluție este elegantă și eficientă. Folosim un nod dummy pentru a simplifica construirea listei rezultate, comparăm nodurile curente din ambele liste și le legăm în ordine, obținând o listă sortată combinată.
