# Soluție Detaliată - Maximum Average Subarray I

## Ce Ne Cere Problema?

Problema ne cere să găsim media maximă a unui subarray (subșir) de lungime k dintr-un array. De exemplu, dacă avem array-ul `[1, 12, -5, -6, 50, 3]` și `k = 4`, trebuie să găsim subarray-ul de lungime 4 cu media maximă.

În acest caz, subarray-urile de lungime 4 sunt:
- `[1, 12, -5, -6]` - suma = 2, media = 2/4 = 0.5
- `[12, -5, -6, 50]` - suma = 51, media = 51/4 = 12.75
- `[-5, -6, 50, 3]` - suma = 42, media = 42/4 = 10.5

Media maximă este 12.75.

## Ce Este un Subarray?

Un subarray este o secvență continuă de elemente dintr-un array. De exemplu, dacă avem `[1, 2, 3, 4]`, atunci `[2, 3]` este un subarray, dar `[1, 3]` nu este (nu este continuu).

## Ce Este Sliding Window (Fereastră Glisantă)?

Sliding Window este o tehnică unde menținem o "fereastră" de dimensiune fixă care "alunecă" prin array. În loc să calculăm suma pentru fiecare subarray de la zero, actualizăm suma existentă prin eliminarea elementului care iese din fereastră și adăugarea elementului care intră.

Gândiți-vă la o fereastră care se mișcă pe un perete - când fereastra se mișcă, vedem o nouă parte a peretelui, dar păstrăm o parte din ceea ce am văzut deja.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Calculăm suma primelor k elemente (fereastra inițială)
2. Această sumă devine suma maximă inițială
3. "Alunecăm" fereastra: eliminăm primul element și adăugăm următorul element
4. Actualizăm suma maximă dacă suma curentă este mai mare
5. Repetăm până când am procesat toate subarray-urile de lungime k

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
public double findMaxAverage(int[] nums, int k) {
```

Această linie definește funcția `findMaxAverage`. Funcția primește:
- `nums` - array-ul de numere
- `k` - lungimea subarray-ului

Funcția returnează `double` - media maximă.

```java
    int sum = 0;
    for (int i = 0; i < k; i++) {
        sum += nums[i];
    }
```

Aceste linii calculează suma primelor k elemente. Să explicăm:

- `int sum = 0` inițializează suma la 0
- `for (int i = 0; i < k; i++)` parcurge primele k elemente (de la 0 la k-1)
- `sum += nums[i]` adună fiecare element la sumă

De exemplu, dacă `nums = [1, 12, -5, -6, 50, 3]` și `k = 4`:
- `i = 0`: `sum = 0 + 1 = 1`
- `i = 1`: `sum = 1 + 12 = 13`
- `i = 2`: `sum = 13 + (-5) = 8`
- `i = 3`: `sum = 8 + (-6) = 2`
- După buclă: `sum = 2` (suma primelor 4 elemente)

```java
    int maxSum = sum;
```

Această linie inițializează suma maximă cu suma primelor k elemente. Aceasta devine suma maximă inițială, pe care o vom actualiza dacă găsim o sumă mai mare.

```java
    for (int i = k; i < nums.length; i++) {
```

Această buclă "alunecă" fereastra prin restul array-ului. Să explicăm:

- `int i = k` începe de la poziția k (primul element care nu este în fereastra inițială)
- `i < nums.length` continuă până la sfârșitul array-ului
- `i++` mărește `i` cu 1 la fiecare iterație

La fiecare iterație, `i` este poziția noului element care intră în fereastră.

```java
        sum = sum - nums[i - k] + nums[i];
```

Această linie actualizează suma prin "alunecarea" ferestrei. Să explicăm pas cu pas:

- `nums[i - k]` este elementul care iese din fereastră (primul element al ferestrei anterioare)
- `nums[i]` este elementul care intră în fereastră (noul element)
- `sum - nums[i - k]` elimină elementul care iese din sumă
- `+ nums[i]` adaugă noul element la sumă

De exemplu, dacă fereastra inițială este `[1, 12, -5, -6]` (suma = 2) și `i = 4`:
- Elementul care iese: `nums[4 - 4] = nums[0] = 1`
- Elementul care intră: `nums[4] = 50`
- Noua sumă: `2 - 1 + 50 = 51`
- Noua fereastră: `[12, -5, -6, 50]` (suma = 51)

De ce funcționează? Pentru că în loc să calculăm suma de la zero pentru fiecare subarray, actualizăm suma existentă prin eliminarea elementului care iese și adăugarea elementului care intră.

```java
        maxSum = Math.max(maxSum, sum);
```

Această linie actualizează suma maximă. Să explicăm:

- `Math.max(maxSum, sum)` compară suma maximă curentă cu suma curentă și returnează cea mai mare
- `maxSum = ...` actualizează suma maximă dacă suma curentă este mai mare

De exemplu, dacă `maxSum = 2` și `sum = 51`, atunci `maxSum` devine 51.

```java
    return (double) maxSum / k;
```

Această linie calculează și returnează media maximă. Să explicăm:

- `(double) maxSum` convertește suma maximă la tipul `double` pentru a obține o împărțire precisă
- `/ k` împarte suma la lungimea subarray-ului pentru a obține media
- `return ...` returnează media maximă

De exemplu, dacă `maxSum = 51` și `k = 4`, atunci media este `51.0 / 4 = 12.75`.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru `nums = [1, 12, -5, -6, 50, 3]` și `k = 4`:

**Pasul 1: Calculăm suma inițială**
- `sum = 1 + 12 + (-5) + (-6) = 2`
- `maxSum = 2`
- Fereastra: `[1, 12, -5, -6]`

**Pasul 2: Alunecăm fereastra (i = 4)**
- Element care iese: `nums[0] = 1`
- Element care intră: `nums[4] = 50`
- `sum = 2 - 1 + 50 = 51`
- `maxSum = max(2, 51) = 51`
- Fereastra: `[12, -5, -6, 50]`

**Pasul 3: Alunecăm fereastra (i = 5)**
- Element care iese: `nums[1] = 12`
- Element care intră: `nums[5] = 3`
- `sum = 51 - 12 + 3 = 42`
- `maxSum = max(51, 42) = 51`
- Fereastra: `[-5, -6, 50, 3]`

**După toate iterațiile:**
- `maxSum = 51`
- Media: `51.0 / 4 = 12.75`
- Returnăm `12.75`

**Rezultat:** `12.75` - media maximă este 12.75 (subarray-ul `[12, -5, -6, 50]`).

## De Ce Este Această Soluție Eficientă?

1. **O(n) timp**: Parcurgem array-ul o singură dată. Pentru fiecare element (după primele k), facem O(1) operații.

2. **O(1) spațiu**: Folosim doar câteva variabile, nu creăm structuri de date suplimentare.

3. **Evită recalcularea**: În loc să calculăm suma pentru fiecare subarray de la zero (ceea ce ar fi O(n*k)), actualizăm suma existentă (O(1) per subarray).

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: O(n) - unde n este numărul de elemente. Parcurgem array-ul o singură dată, făcând O(1) operații pentru fiecare element.

- **Complexitatea spațiului**: O(1) - folosim doar câteva variabile, nu creăm structuri de date suplimentare.

## Concluzie

Această soluție este elegantă și eficientă. Folosim tehnica sliding window pentru a menține suma unui subarray de lungime fixă, actualizând-o eficient când fereastra "alunecă" prin array, obținând astfel o complexitate de O(n) în loc de O(n*k).
