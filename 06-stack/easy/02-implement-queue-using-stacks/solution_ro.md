# Soluție Detaliată - Implement Queue using Stacks

## Ce Ne Cere Problema?

Problema ne cere să implementăm o coadă (queue) folosind doar stive (stacks). O coadă funcționează pe principiul FIFO (First In, First Out) - primul element adăugat este primul element scos. O stivă funcționează pe principiul LIFO (Last In, First Out) - ultimul element adăugat este primul element scos.

## Ce Este o Coadă (Queue)?

O coadă este ca o coadă la magazin - primul venit este primul servit. Elementele sunt adăugate la sfârșit (enqueue) și eliminate de la început (dequeue).

## Ce Este o Stivă (Stack)?

O stivă este ca o stivă de farfurii - ultima farfurie pusă este prima scosă. Elementele sunt adăugate și eliminate de la același capăt (top).

## Cum Putem Simula o Coadă cu Două Stive?

Ideea principală este să folosim două stive:
- **Input stack**: stocăm elementele când le adăugăm (push)
- **Output stack**: folosim pentru a scoate elementele în ordinea corectă (pop/peek)

Când trebuie să scoatem un element, transferăm toate elementele din input stack în output stack. Acest lucru inversează ordinea, transformând LIFO în FIFO.

## Cum Funcționează Soluția?

Strategia noastră este următoarea:
1. Când adăugăm un element (push), îl punem în input stack
2. Când vrem să vedem primul element (peek) sau să îl scoatem (pop), verificăm dacă output stack este gol
3. Dacă output stack este gol, transferăm toate elementele din input stack în output stack
4. Scoatem sau vedem elementul din output stack

## Explicație Pas cu Pas a Codului

Să analizăm codul linie cu linie:

```java
class MyQueue {
    private Stack<Integer> input;
    private Stack<Integer> output;
```

Aceste linii definesc clasa `MyQueue` și cele două stive. Să explicăm:

- `private Stack<Integer> input` - stiva de intrare, unde adăugăm elementele noi
- `private Stack<Integer> output` - stiva de ieșire, de unde scoatem elementele

Folosim `private` pentru a ascunde detaliile de implementare (encapsulare).

```java
    public MyQueue() {
        input = new Stack<>();
        output = new Stack<>();
    }
```

Acesta este constructorul clasei. Inițializează ambele stive ca fiind goale.

```java
    public void push(int x) {
        input.push(x);
    }
```

Această metodă adaugă un element în coadă. Să explicăm:

- `input.push(x)` adaugă elementul `x` în stiva de intrare

De ce adăugăm în input stack? Pentru că vrem să păstrăm elementele în ordinea în care au fost adăugate. Când transferăm din input în output, ordinea se inversează, ceea ce ne dă comportamentul FIFO.

```java
    public int pop() {
        if (output.isEmpty()) {
            transfer();
        }
        return output.pop();
    }
```

Această metodă scoate primul element din coadă. Să explicăm pas cu pas:

- `if (output.isEmpty())` verifică dacă stiva de ieșire este goală
- Dacă este goală, `transfer()` transferă toate elementele din input stack în output stack
- `return output.pop()` scoate și returnează elementul din vârful output stack-ului

De ce verificăm dacă output stack este gol? Pentru că dacă output stack nu este gol, elementele sunt deja în ordinea corectă (FIFO), deci putem să le scoatem direct. Dacă este gol, trebuie să transferăm elementele din input pentru a le pune în ordinea corectă.

```java
    public int peek() {
        if (output.isEmpty()) {
            transfer();
        }
        return output.peek();
    }
```

Această metodă returnează primul element fără să îl scoată. Funcționează similar cu `pop()`, dar folosește `peek()` în loc de `pop()` pentru a vedea elementul fără să îl scoată.

```java
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }
```

Această metodă verifică dacă coada este goală. Coada este goală dacă ambele stive sunt goale.

```java
    private void transfer() {
        while (!input.isEmpty()) {
            output.push(input.pop());
        }
    }
```

Această metodă transferă toate elementele din input stack în output stack. Să explicăm:

- `while (!input.isEmpty())` continuă cât timp input stack nu este gol
- `output.push(input.pop())` scoate un element din input stack și îl pune în output stack

De ce funcționează? Să urmărim un exemplu:
- Input stack: `[1, 2, 3]` (1 este în vârf)
- Transferăm:
  - `input.pop()` → 1, `output.push(1)` → output: `[1]`
  - `input.pop()` → 2, `output.push(2)` → output: `[1, 2]`
  - `input.pop()` → 3, `output.push(3)` → output: `[1, 2, 3]` (3 este în vârf)
- Output stack: `[1, 2, 3]` (1 este în vârf, deci va fi scos primul)

Ordinea s-a inversat! Elementele care au fost adăugate primele (1, apoi 2, apoi 3) sunt acum în output stack astfel încât 1 este primul care va fi scos, ceea ce este exact comportamentul FIFO al unei cozi.

## Exemplu Pas cu Pas Complet

Să urmărim pas cu pas pentru operațiile: push(1), push(2), push(3), pop(), peek(), pop():

**Inițializare:**
- `input = []`, `output = []`

**push(1):**
- `input.push(1)` → `input = [1]`
- `output = []`

**push(2):**
- `input.push(2)` → `input = [1, 2]` (2 este în vârf)
- `output = []`

**push(3):**
- `input.push(3)` → `input = [1, 2, 3]` (3 este în vârf)
- `output = []`

**pop():**
- `output.isEmpty()`? `true`, deci `transfer()`
- Transferăm: `input = []`, `output = [1, 2, 3]` (1 este în vârf)
- `output.pop()` → returnează 1
- `output = [2, 3]`

**peek():**
- `output.isEmpty()`? `false` (output nu este gol)
- `output.peek()` → returnează 2 (fără să îl scoată)
- `output = [2, 3]` (neschimbat)

**pop():**
- `output.isEmpty()`? `false`
- `output.pop()` → returnează 2
- `output = [3]`

## De Ce Este Această Soluție Eficientă?

1. **Amortizat O(1)**: Deși transferul este O(n), fiecare element este transferat cel mult o dată, deci costul este "amortizat" peste toate operațiile.

2. **Leneș (Lazy)**: Transferăm elementele doar când este necesar (când output stack este gol), nu la fiecare operație.

3. **Simplă**: Folosim doar două stive și o metodă de transfer simplă.

## Complexitatea Timpului și Spațiului

- **Complexitatea timpului**: 
  - `push()`: O(1) - adăugăm în input stack
  - `pop()`: O(1) amortizat - transferăm doar când output este gol
  - `peek()`: O(1) amortizat - similar cu pop
  - `empty()`: O(1) - verificare simplă

- **Complexitatea spațiului**: O(n) - unde n este numărul de elemente. Stocăm elementele în cele două stive.

## Concluzie

Această soluție este elegantă și eficientă. Folosim două stive pentru a simula comportamentul unei cozi, transferând elementele doar când este necesar, obținând astfel o complexitate amortizată O(1) pentru operațiile principale.
