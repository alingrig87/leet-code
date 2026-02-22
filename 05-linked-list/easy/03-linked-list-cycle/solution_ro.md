# Soluție Detaliată - Linked List Cycle

## Ce Ne Cere Problema?

Problema ne cere să verificăm dacă o listă înlănțuită are un ciclu (buclă). Un ciclu înseamnă că există un nod în listă care pointează către un nod anterior, creând astfel o buclă infinită.

De exemplu:
- `1 -> 2 -> 3 -> 4 -> 2` (nodul 4 pointează înapoi la nodul 2) - are ciclu
- `1 -> 2 -> 3 -> 4 -> null` - nu are ciclu

## Ce Este un Ciclu într-o Listă Înlănțuită?

Un ciclu este când un nod pointează către un nod anterior din listă, creând o buclă. În loc să se termine cu `null`, lista se întoarce și formează un cerc.

Gândiți-vă la o pistă de alergare circulară - dacă alergați pe ea, veți reveni întotdeauna la același punct. La fel funcționează un ciclu într-o listă.

## Ce Este Algoritmul Floyd (Tortoise and Hare)?

Algoritmul Floyd (cunoscut și ca "Tortoise and Hare" - Țestoasa și Iepurele) folosește doi pointeri care se mișcă cu viteze diferite:
- **Slow pointer** (Țestoasa): se mișcă cu 1 pas la fiecare iterație
- **Fast pointer** (Iepurele): se mișcă cu 2 pași la fiecare iterație

Dacă există un ciclu, pointer-ul rapid va ajunge din urmă pe pointer-ul lent și îi va face "ture" în jurul ciclului până când se întâlnesc.

## De Ce Funcționează Algoritmul?

Dacă există un ciclu, pointer-ul rapid va intra în ciclu înaintea pointer-ului lent. Odată în ciclu, pointer-ul rapid se va apropia constant de pointer-ul lent (pentru că se mișcă mai repede), și într-un număr finit de pași se vor întâlni.

Dacă nu există ciclu, pointer-ul rapid va ajunge la `null` înainte ca pointer-ul lent să ajungă la sfârșit.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Inițializăm doi pointeri - `slow` și `fast` - amândoi la începutul listei
2. Mutăm `slow` cu 1 pas și `fast` cu 2 pași la fiecare iterație
3. Dacă `slow` și `fast` se întâlnesc (sunt același nod), există un ciclu
4. Dacă `fast` ajunge la `null`, nu există ciclu

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public boolean hasCycle(ListNode head) {
```

Această linie definește funcția `hasCycle`. Funcția primește:
- `head` - primul nod al listei

Funcția returnează `boolean` - `true` dacă există ciclu, `false` dacă nu există.

```java
    if (head == null || head.next == null) {
        return false;
    }
```

Această verificare tratează cazurile speciale. Să explicăm:

- `head == null` - lista este goală, nu poate avea ciclu
- `head.next == null` - lista are un singur nod, care pointează la `null`, deci nu poate avea ciclu

În ambele cazuri, returnăm `false` (nu există ciclu).

```java
    ListNode slow = head;
    ListNode fast = head;
```

Aceste linii inițializează cei doi pointeri. Să explicăm:

- `slow = head` - pointer-ul lent începe de la primul nod
- `fast = head` - pointer-ul rapid începe de la primul nod

Amândoi încep de la același loc, dar se vor mișca cu viteze diferite.

```java
    while (fast != null && fast.next != null) {
```

Această buclă continuă cât timp pointer-ul rapid mai poate să se miște. Să explicăm:

- `fast != null` - pointer-ul rapid nu a ajuns la sfârșitul listei
- `fast.next != null` - pointer-ul rapid poate să facă încă un pas (pentru că face 2 pași, avem nevoie de `fast.next` să existe)
- `&&` înseamnă "și" - bucla continuă doar dacă ambele condiții sunt adevărate

De ce verificăm `fast.next`? Pentru că `fast` se mișcă cu 2 pași (`fast.next.next`), deci trebuie să verificăm că `fast.next` există înainte de a accesa `fast.next.next`.

```java
        slow = slow.next;
        fast = fast.next.next;
```

Aceste linii mută pointerii cu viteze diferite. Să explicăm:

- `slow = slow.next` mută pointer-ul lent cu 1 pas (la următorul nod)
- `fast = fast.next.next` mută pointer-ul rapid cu 2 pași (sare peste un nod)

De exemplu, dacă `slow` este la nodul 1, după `slow = slow.next`, `slow` este la nodul 2.
Dacă `fast` este la nodul 1, după `fast = fast.next.next`, `fast` este la nodul 3 (sare peste nodul 2).

```java
        if (slow == fast) {
            return true;
        }
```

Această condiție verifică dacă pointerii s-au întâlnit. Să explicăm:

- `slow == fast` compară dacă amândoi pointerii pointează către același nod
- Dacă da, înseamnă că pointer-ul rapid a ajuns din urmă pe pointer-ul lent, ceea ce poate apărea doar dacă există un ciclu
- În acest caz, returnăm `true` (există ciclu)

De ce dacă se întâlnesc există ciclu? Pentru că dacă nu ar exista ciclu, pointer-ul rapid ar ajunge la `null` înainte ca pointer-ul lent să ajungă la sfârșit, deci nu s-ar putea întâlni.

```java
    return false;
```

Această linie se execută doar dacă bucla s-a terminat fără ca pointerii să se întâlnească. În acest caz, pointer-ul rapid a ajuns la `null`, ceea ce înseamnă că nu există ciclu, deci returnăm `false`.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru lista `1 -> 2 -> 3 -> 4 -> 2` (nodul 4 pointează înapoi la nodul 2):

**Inițializare:**
- `slow = nodul 1`
- `fast = nodul 1`

**Iterația 1:**
- `slow = slow.next` → `slow = nodul 2`
- `fast = fast.next.next` → `fast = nodul 3`
- `slow == fast`? `nodul 2 != nodul 3` → `false`, continuăm

**Iterația 2:**
- `slow = slow.next` → `slow = nodul 3`
- `fast = fast.next.next` → `fast = nodul 2` (nodul 3.next.next = nodul 2, pentru că nodul 4 pointează înapoi la nodul 2)
- `slow == fast`? `nodul 3 != nodul 2` → `false`, continuăm

**Iterația 3:**
- `slow = slow.next` → `slow = nodul 4`
- `fast = fast.next.next` → `fast = nodul 4` (nodul 2.next.next = nodul 4)
- `slow == fast`? `nodul 4 == nodul 4` → `true`!
- Returnăm `true`

**Rezultat:** `true` - există ciclu.

## Alt Exemplu - Fără Ciclu

Să urmărim pentru lista `1 -> 2 -> 3 -> 4 -> null`:

**Inițializare:**
- `slow = nodul 1`
- `fast = nodul 1`

**Iterația 1:**
- `slow = nodul 2`
- `fast = nodul 3`
- `slow != fast`, continuăm

**Iterația 2:**
- `slow = nodul 3`
- `fast = nodul 4.next.next` → `fast = null` (nodul 4.next = null, deci null.next nu există, dar verificăm `fast.next != null` înainte)
- De fapt, verificăm `fast.next != null` înainte, deci bucla se termină când `fast = nodul 4` și `fast.next = null`

**După buclă:**
- `fast.next = null`, deci bucla se termină
- Pointerii nu s-au întâlnit
- Returnăm `false`

**Rezultat:** `false` - nu există ciclu.

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: În cel mai rău caz, pointer-ul lent parcurge lista o dată înainte ca pointerii să se întâlnească.

2. **O(1) spațiu**: Folosim doar doi pointeri, nu creăm structuri de date suplimentare (precum un HashSet pentru a ține minte nodurile vizitate).

3. **Elegantă**: Nu necesită să modificăm lista sau să folosim spațiu suplimentar.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de noduri. În cel mai rău caz, pointer-ul lent parcurge lista o dată.

- **Complexitatea spațiului**: O(1) - folosim doar doi pointeri, nu creăm structuri de date suplimentare.

## Concluzie

Algoritmul Floyd (Tortoise and Hare) este o soluție elegantă și eficientă pentru detectarea ciclurilor. Folosind doi pointeri care se mișcă cu viteze diferite, detectăm rapid dacă există un ciclu fără a folosi spațiu suplimentar.
