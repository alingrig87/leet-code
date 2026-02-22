# Soluție Detaliată - Contains Duplicate

## Ce Ne Cere Problema?

Problema ne cere să verificăm dacă într-un array (listă) de numere există cel puțin un număr care apare de două sau mai multe ori. Dacă există astfel de numere, returnăm `true` (adevărat). Dacă toate numerele sunt diferite între ele, returnăm `false` (fals).

## Ce Este un Array?

Un array este ca o cutie cu mai multe sertare, unde fiecare sertar poate conține o valoare. De exemplu, dacă avem array-ul `[1, 2, 3, 1]`, înseamnă că avem o cutie cu 4 sertare:
- Sertarul 0 conține numărul 1
- Sertarul 1 conține numărul 2
- Sertarul 2 conține numărul 3
- Sertarul 3 conține numărul 1

Observăm că numărul 1 apare de două ori (în sertarul 0 și în sertarul 3), deci avem duplicate.

## Ce Este un HashSet?

Un HashSet este ca o listă specială care nu permite duplicate. Dacă încerci să adaugi același element de două ori, HashSet îl va păstra doar o singură dată. Este perfect pentru a verifica dacă am mai văzut un număr înainte.

Gândiți-vă la un HashSet ca la o listă de nume la o petrecere. Dacă vine cineva și numele lui este deja pe listă, știi că persoana respectivă a mai fost la petrecere. Dacă numele nu este pe listă, îl adaugi și știi că este prima dată când vine.

## Cum Funcționează Soluția?

Soluția noastră este foarte simplă și inteligentă:

1. Creăm un HashSet gol (o listă goală de nume)
2. Parcurgem fiecare număr din array (verificăm fiecare persoană care vine la petrecere)
3. Pentru fiecare număr, verificăm dacă l-am mai văzut (dacă numele este deja pe listă)
4. Dacă l-am mai văzut, înseamnă că avem duplicate, deci returnăm `true`
5. Dacă nu l-am mai văzut, îl adăugăm pe listă și continuăm
6. Dacă am terminat de parcurs toate numerele și nu am găsit duplicate, returnăm `false`

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public boolean containsDuplicate(int[] nums) {
```

Această linie definește o funcție numită `containsDuplicate`. Funcția primește un parametru numit `nums`, care este array-ul nostru de numere. Funcția returnează un `boolean`, adică fie `true` (adevărat) fie `false` (fals).

Cuvântul `public` înseamnă că funcția poate fi folosită din orice altă parte a programului. Cuvântul `boolean` înseamnă că funcția va returna doar `true` sau `false`.

```java
    if (nums == null || nums.length <= 1) {
        return false;
    }
```

Această parte verifică dacă array-ul este gol sau are doar un singur element. Să explicăm fiecare parte:

- `nums == null` verifică dacă array-ul nu există deloc (este null, adică gol complet)
- `nums.length <= 1` verifică dacă array-ul are lungimea mai mică sau egală cu 1 (adică are 0 sau 1 element)
- `||` înseamnă "sau" - dacă oricare dintre cele două condiții este adevărată, atunci executăm ce este în acolade
- Dacă array-ul este gol sau are doar un element, nu poate avea duplicate, deci returnăm `false` imediat

De ce facem asta? Pentru că dacă avem 0 elemente sau 1 element, este imposibil să avem duplicate. Un duplicate înseamnă că același număr apare de cel puțin două ori, deci avem nevoie de cel puțin 2 elemente.

```java
    Set<Integer> seen = new HashSet<>();
```

Această linie creează un HashSet gol. Să explicăm fiecare parte:

- `Set<Integer>` este tipul de date - un set (mulțime) care conține numere întregi (Integer)
- `seen` este numele variabilei - am ales acest nume pentru că va stoca numerele pe care le-am "văzut" deja
- `new HashSet<>()` creează un HashSet nou și gol

HashSet-ul nostru va funcționa ca o listă de numere pe care le-am văzut deja. De fiecare dată când vedem un număr nou, îl vom adăuga în acest set.

```java
    for (int num : nums) {
```

Această linie începe o buclă care parcurge fiecare element din array-ul `nums`. Să explicăm:

- `for` este cuvântul cheie care începe o buclă (repetare)
- `int num` declară o variabilă numită `num` care va stoca fiecare număr pe măsură ce îl parcurgem
- `:` înseamnă "din" sau "pentru fiecare"
- `nums` este array-ul pe care îl parcurgem

Această buclă va rula de atâtea ori câte elemente are array-ul. De exemplu, dacă array-ul are 4 elemente `[1, 2, 3, 1]`, bucla va rula de 4 ori:
- Prima dată, `num` va fi 1
- A doua oară, `num` va fi 2
- A treia oară, `num` va fi 3
- A patra oară, `num` va fi 1

```java
        if (seen.contains(num)) {
            return true;
        }
```

Această parte verifică dacă numărul curent (`num`) există deja în HashSet-ul nostru (`seen`). Să explicăm:

- `seen.contains(num)` verifică dacă HashSet-ul `seen` conține deja numărul `num`
- `contains()` este o metodă (funcție) care returnează `true` dacă elementul există în set, sau `false` dacă nu există
- Dacă numărul există deja în set, înseamnă că l-am mai văzut o dată, deci avem un duplicate
- În acest caz, returnăm imediat `true` și oprim funcția (nu mai continuăm să verificăm restul numerelor)

De ce oprim funcția imediat? Pentru că am găsit deja ce căutăm - un duplicate. Nu mai este nevoie să verificăm restul numerelor, deci putem să ne oprim aici pentru eficiență.

```java
        seen.add(num);
```

Această linie adaugă numărul curent în HashSet-ul nostru. Să explicăm:

- `seen.add(num)` adaugă numărul `num` în HashSet-ul `seen`
- `add()` este o metodă care adaugă un element în set
- Dacă elementul există deja, `add()` nu îl va adăuga din nou (HashSet-ul nu permite duplicate)

Această linie se execută doar dacă numărul nu exista deja în set (pentru că dacă ar fi existat, am fi returnat `true` mai sus și am fi oprit funcția).

După ce adăugăm numărul, bucla continuă cu următorul număr din array.

```java
    return false;
```

Această linie se execută doar dacă am terminat de parcurs toate numerele din array și nu am găsit niciun duplicate. În acest caz, returnăm `false` pentru a indica că nu există duplicate.

## Exemplu Pas cu Pas

Să urmărim cum funcționează soluția pentru array-ul `[1, 2, 3, 1]`:

**Inițializare:**
- `seen = []` (HashSet gol)

**Iterația 1 (num = 1):**
- Verificăm: `seen.contains(1)` → `false` (HashSet-ul este gol, deci nu conține 1)
- Nu returnăm `true`, continuăm
- Adăugăm: `seen.add(1)` → `seen = [1]`

**Iterația 2 (num = 2):**
- Verificăm: `seen.contains(2)` → `false` (HashSet-ul conține doar 1, nu conține 2)
- Nu returnăm `true`, continuăm
- Adăugăm: `seen.add(2)` → `seen = [1, 2]`

**Iterația 3 (num = 3):**
- Verificăm: `seen.contains(3)` → `false` (HashSet-ul conține 1 și 2, nu conține 3)
- Nu returnăm `true`, continuăm
- Adăugăm: `seen.add(3)` → `seen = [1, 2, 3]`

**Iterația 4 (num = 1):**
- Verificăm: `seen.contains(1)` → `true` (HashSet-ul conține deja 1!)
- Returnăm imediat `true` și oprim funcția

**Rezultat:** `true` (am găsit duplicate)

## Alt Exemplu - Fără Duplicate

Să urmărim pentru array-ul `[1, 2, 3, 4]`:

**Inițializare:**
- `seen = []`

**Iterația 1 (num = 1):**
- `seen.contains(1)` → `false`
- `seen.add(1)` → `seen = [1]`

**Iterația 2 (num = 2):**
- `seen.contains(2)` → `false`
- `seen.add(2)` → `seen = [1, 2]`

**Iterația 3 (num = 3):**
- `seen.contains(3)` → `false`
- `seen.add(3)` → `seen = [1, 2, 3]`

**Iterația 4 (num = 4):**
- `seen.contains(4)` → `false`
- `seen.add(4)` → `seen = [1, 2, 3, 4]`

**După toate iterațiile:**
- Am terminat de parcurs toate numerele
- Nu am returnat `true` în nicio iterație
- Returnăm `false`

**Rezultat:** `false` (nu există duplicate)

## De Ce Este Această Soluție Eficientă?

Această soluție este foarte eficientă din mai multe motive:

1. **Parcurgem array-ul o singură dată**: Nu trebuie să comparăm fiecare număr cu toate celelalte numere, ci doar să verificăm dacă l-am mai văzut.

2. **HashSet-ul este rapid**: Verificarea dacă un număr există în HashSet (`contains()`) și adăugarea unui număr (`add()`) sunt operații foarte rapide, aproape instantanee, indiferent de câte elemente sunt deja în set.

3. **Oprim imediat când găsim duplicate**: Dacă găsim un duplicate, nu mai continuăm să verificăm restul numerelor, ceea ce economisește timp.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de elemente din array. Parcurgem array-ul o singură dată, iar fiecare operație pe HashSet este foarte rapidă.

- **Complexitatea spațiului**: O(n) - în cel mai rău caz, toate numerele sunt diferite, deci HashSet-ul va conține toate cele n elemente.

## Concluzie

Această soluție este elegantă, eficientă și ușor de înțeles. Folosim un HashSet pentru a ține minte numerele pe care le-am văzut deja, iar de fiecare dată când întâlnim un număr, verificăm dacă l-am mai văzut. Dacă da, avem duplicate. Dacă nu, îl adăugăm în set și continuăm.
