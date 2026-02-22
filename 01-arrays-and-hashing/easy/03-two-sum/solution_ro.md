# Soluție Detaliată - Two Sum

## Ce Ne Cere Problema?

Problema ne cere să găsim două numere dintr-un array care, când sunt adunate, dau exact o anumită sumă (target). Trebuie să returnăm pozițiile (indicele) acestor două numere în array.

De exemplu, dacă avem array-ul `[2, 7, 11, 15]` și target-ul este `9`, trebuie să găsim două numere care adunate dau 9. În acest caz, `2 + 7 = 9`, deci returnăm pozițiile acestor numere, care sunt `[0, 1]` (2 este la poziția 0, 7 este la poziția 1).

## Ce Este un HashMap?

Un HashMap este ca un dicționar sau o agendă telefonică. În loc să căutăm prin toate numerele de fiecare dată, putem să "căutăm" direct după nume și să găsim imediat numărul de telefon asociat.

În cazul nostru, HashMap-ul va stoca perechi de tipul: număr → poziție. Astfel, când știm un număr, putem găsi imediat poziția lui în array.

## Cum Funcționează Soluția?

Ideea principală este următoarea: dacă căutăm două numere `a` și `b` astfel încât `a + b = target`, atunci dacă știm `a`, știm că `b = target - a`.

Strategia noastră:
1. Parcurgem array-ul o singură dată
2. Pentru fiecare număr pe care îl vedem, calculăm ce număr ne-ar trebui pentru a ajunge la target (complementul)
3. Verificăm dacă am mai văzut acest complement înainte (folosind HashMap-ul)
4. Dacă da, am găsit soluția - returnăm pozițiile
5. Dacă nu, adăugăm numărul curent în HashMap pentru a-l folosi mai târziu

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public int[] twoSum(int[] nums, int target) {
```

Această linie definește funcția `twoSum`. Funcția primește:
- `nums` - array-ul de numere în care căutăm
- `target` - suma pe care o căutăm

Funcția returnează `int[]` - un array cu două numere, care sunt pozițiile celor două numere care adunate dau target-ul.

```java
    if (nums == null || nums.length < 2) {
        return new int[0];
    }
```

Această verificare se asigură că array-ul există și are cel puțin 2 elemente. Să explicăm:

- `nums == null` verifică dacă array-ul nu există deloc
- `nums.length < 2` verifică dacă array-ul are mai puțin de 2 elemente
- `||` înseamnă "sau" - dacă oricare dintre condiții este adevărată
- `new int[0]` creează un array gol (fără elemente) - returnăm asta dacă nu putem găsi o soluție

De ce avem nevoie de cel puțin 2 elemente? Pentru că trebuie să găsim două numere, deci avem nevoie de cel puțin 2 elemente în array.

```java
    Map<Integer, Integer> map = new HashMap<>();
```

Această linie creează un HashMap gol. Să explicăm fiecare parte:

- `Map<Integer, Integer>` este tipul de date - un map (hartă) unde atât cheia cât și valoarea sunt numere întregi
- `map` este numele variabilei
- `new HashMap<>()` creează un HashMap nou și gol

În acest HashMap, vom stoca:
- **Cheia (Key)**: un număr pe care l-am văzut în array
- **Valoarea (Value)**: poziția (index-ul) unde l-am văzut

De exemplu, dacă am văzut numărul 7 la poziția 1, vom stoca în HashMap: `7 → 1`.

```java
    for (int i = 0; i < nums.length; i++) {
```

Această buclă parcurge array-ul de la început până la sfârșit. Să explicăm:

- `for` începe o buclă (repetare)
- `int i = 0` inițializează variabila `i` cu valoarea 0 - aceasta va fi poziția curentă în array
- `i < nums.length` este condiția - bucla continuă cât timp `i` este mai mic decât lungimea array-ului
- `i++` înseamnă "mărește `i` cu 1" după fiecare iterație

Această buclă va rula de atâtea ori câte elemente are array-ul. De exemplu, dacă array-ul are 4 elemente (indicele 0, 1, 2, 3), bucla va rula de 4 ori:
- Prima iterație: `i = 0`
- A doua iterație: `i = 1`
- A treia iterație: `i = 2`
- A patra iterație: `i = 3`

```java
        int complement = target - nums[i];
```

Această linie calculează "complementul" numărului curent. Să explicăm:

- `nums[i]` este numărul de la poziția `i` din array
- `target` este suma pe care o căutăm
- `complement = target - nums[i]` calculează ce număr ne-ar trebui pentru ca `nums[i] + complement = target`

De exemplu:
- Dacă `target = 9` și `nums[i] = 2`, atunci `complement = 9 - 2 = 7`
- Dacă `target = 9` și `nums[i] = 7`, atunci `complement = 9 - 7 = 2`

De ce calculăm complementul? Pentru că dacă știm un număr și știm suma dorită, putem calcula imediat ce număr ne mai trebuie.

```java
        if (map.containsKey(complement)) {
            return new int[]{map.get(complement), i};
        }
```

Această parte verifică dacă am mai văzut complementul înainte. Să explicăm pas cu pas:

- `map.containsKey(complement)` verifică dacă HashMap-ul conține complementul ca și cheie
- `containsKey()` este o metodă care returnează `true` dacă cheia există în map, sau `false` dacă nu există
- Dacă complementul există în map, înseamnă că l-am mai văzut la o poziție anterioară
- `map.get(complement)` returnează valoarea asociată cu complementul - adică poziția unde l-am văzut
- `new int[]{map.get(complement), i}` creează un array nou cu două elemente:
  - Primul element: poziția unde am văzut complementul (numărul care ne trebuie)
  - Al doilea element: poziția curentă `i` (numărul pe care îl verificăm acum)
- `return` oprește funcția imediat și returnează acest array

De ce returnăm imediat? Pentru că am găsit soluția! Am găsit două numere care adunate dau target-ul, deci nu mai trebuie să continuăm să căutăm.

```java
        map.put(nums[i], i);
```

Această linie adaugă numărul curent în HashMap. Să explicăm:

- `map.put(nums[i], i)` adaugă o intrare în HashMap
- `nums[i]` este cheia - numărul pe care l-am văzut
- `i` este valoarea - poziția unde l-am văzut

Această linie se execută doar dacă nu am găsit complementul (pentru că dacă l-am găsit, am returnat deja și am oprit funcția).

De ce adăugăm numărul în HashMap? Pentru ca mai târziu, când vom verifica alte numere, să putem verifica rapid dacă am mai văzut complementul lor.

```java
    return new int[0];
```

Această linie se execută doar dacă am terminat de parcurs toate numerele și nu am găsit o soluție. În acest caz, returnăm un array gol.

Conform enunțului problemei, există întotdeauna exact o soluție, deci această linie nu ar trebui să se execute niciodată. O includem pentru siguranță.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `nums = [2, 7, 11, 15]` și `target = 9`:

**Inițializare:**
- `map = {}` (HashMap gol)

**Iterația 1 (i = 0, nums[0] = 2):**
- Calculăm complement: `complement = 9 - 2 = 7`
- Verificăm: `map.containsKey(7)` → `false` (HashMap-ul este gol)
- Nu returnăm, continuăm
- Adăugăm: `map.put(2, 0)` → `map = {2 → 0}`

**Iterația 2 (i = 1, nums[1] = 7):**
- Calculăm complement: `complement = 9 - 7 = 2`
- Verificăm: `map.containsKey(2)` → `true` (HashMap-ul conține 2!)
- Returnăm: `new int[]{map.get(2), 1}` = `new int[]{0, 1}`
- Funcția se oprește aici

**Rezultat:** `[0, 1]` - numerele de la pozițiile 0 și 1 (2 și 7) adunate dau 9.

## Alt Exemplu

Să urmărim pentru `nums = [3, 2, 4]` și `target = 6`:

**Inițializare:**
- `map = {}`

**Iterația 1 (i = 0, nums[0] = 3):**
- Complement: `6 - 3 = 3`
- `map.containsKey(3)` → `false`
- `map.put(3, 0)` → `map = {3 → 0}`

**Iterația 2 (i = 1, nums[1] = 2):**
- Complement: `6 - 2 = 4`
- `map.containsKey(4)` → `false`
- `map.put(2, 1)` → `map = {3 → 0, 2 → 1}`

**Iterația 3 (i = 2, nums[2] = 4):**
- Complement: `6 - 4 = 2`
- `map.containsKey(2)` → `true` (am văzut 2 la poziția 1!)
- Returnăm: `new int[]{1, 2}`

**Rezultat:** `[1, 2]` - numerele de la pozițiile 1 și 2 (2 și 4) adunate dau 6.

## De Ce Este Această Soluție Eficientă?

1. **Parcurgem array-ul o singură dată**: Nu comparăm fiecare număr cu toate celelalte, ci doar verificăm dacă am mai văzut complementul.

2. **HashMap-ul este rapid**: Verificarea dacă un număr există în HashMap (`containsKey()`) și adăugarea unui număr (`put()`) sunt operații foarte rapide.

3. **Oprim imediat când găsim soluția**: Dacă găsim două numere care funcționează, nu mai continuăm să căutăm.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de elemente din array. Parcurgem array-ul o singură dată, iar fiecare operație pe HashMap este foarte rapidă.

- **Complexitatea spațiului**: O(n) - în cel mai rău caz, HashMap-ul va conține toate elementele din array (dacă soluția este la final).

## Concluzie

Această soluție este elegantă și eficientă. Folosim un HashMap pentru a ține minte numerele pe care le-am văzut și pozițiile lor. Pentru fiecare număr nou, calculăm complementul și verificăm dacă l-am mai văzut. Dacă da, am găsit soluția!
