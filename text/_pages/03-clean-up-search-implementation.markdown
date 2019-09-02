---
layout: page
title:  "Cleaning up the search implementation"
categories: search clojure
---

# {{ page.title }}

In [the previous installment]({% link _pages/02-simple-search-algorithms.markdown %}) we
developed an working initial implementation of our simple search algorithms in
Clojure. Our code can be tidied up in several ways, and the logic of the search
algorithms can also be improved.

## Avoiding duplicate exploration

The first concern is the the fact that we end up with the same state in the
`open` list multiple times, which obviously isn't great. There are in fact
two separate issues. One is the problem of multiple copies of a node in the
`open` list; this we can solve by "simply" being careful to only add new nodes
to the `open` list if they're not already there.
<input type="checkbox" id="cb1"/><label for="cb1"><sup class="note-marker">\*</sup></label><span>
A "simple" solution would be to change `open` from a sequence to a Clojure
`set`, which would ensure that there would be no duplicates. The problem,
though, would be that sets are inherently not ordered sequences, and our
current approach depends crucially on `open` being a sequence whose order
represents the order in which nodes should be explored.
</span>

The other problem is that we could explore state $$s$$ at one point, and then
later have $$s$$ appear again as the child of some other state. Since we've
already explored $$s$$, we don't want to explore it again. That means we have
to in some sense "remember" what states we've explored so we don't explore a
state twice. The standard approach to that is to also have a `closed` collection
which holds all the states that we've already explored. Here I will use a
Clojure set, since we don't care about the order in which nodes were added to
`closed`.

To add those to our code involves two changes. The first is to remove
everything from the set of children that already exist in `open`. For now
we'll do this by converting the collection of children to a set, and the
`open` list to a set, and then using the built-in `clojure.set/difference`
function to remove the duplicates.<input type="checkbox" id="cb2"/><label for="cb2"><sup class="note-marker">\*</sup></label><span>
All this generation and re-generation of sets isn't terribly efficient, but
we'll live with this for now.
</span>

```klipse
(defn remove-previous-states
  [new-states open-states closed-states]
  (clojure.set/difference
    ; The function `set` takes a collection and returns a set with those items
    (set new-states)
    (clojure.set/union (set open-states) (set closed-states))))
```

![Microscope icon](/assets/Microscope_icon_32.png)
Explore these set functions below. Note that the order of items in a set is
somewhat arbitrary and never guaranteed.
{:.active-example}

```klipse
(clojure.set/union #{3 2 0} #{2 4 1 6})
```

```klipse
(clojure.set/difference #{2 4 1 6} #{4 5 2 0})
```

Note that the arguments to
`remove-previous-states` can be any collection, and can in fact have different
types. What we'll get back, though, will always be a set.
{:.active-example}

```klipse
(remove-previous-states [5 8 9 8 7] '(3 5 7) [9 2])
```

Now that we've made sure that we don't add previously seen states to
`open-states`, we now need to sort out *closed* nodes. This involves
adding Yet Another Argument (we'll help clean that up later) for the
`closed` set and add the `next-node` to that along the way.

```clojure
(defn search
  [get-next-node add-children goal? children open-nodes closed-nodes]
  (println open-nodes)
  (let [next-node (get-next-node open-nodes)]
    (if (goal? next-node)
      next-node
      (search
        get-next-node
        add-children
        goal?
        children
        (add-children
          (remove-previous-states (children next-node) open-nodes closed-nodes)
          open-nodes)
        ; Add `next-node` to the set of `closed-nodes`
        (conj closed-nodes next-node)))))
```

The function `conj` above is a
general mechanism to add ("conjoin") a new element to a collection. It attempts
to do what "makes sense" given the type of the collection, e.g., only add an
element to a set if it's not already present. It also has its arguments in
what many find a counterintuitive order; this often turns out to be more
convenient when using `conj` as a higher-order function.

```klipse
(conj #{5 8 9} 9)
```

![Microscope icon](/assets/Microscope_icon_32.png)
Change the new element from `9` to `11`. Collections can have mixed types, so
you could also change `9` to a string like `"Morris"` or a symbol like
`:Minnesota`.
{:.active-example}

Also experiment with changing the collection in that call from a set to a
vector (using square brackets like `[5 8 9]`) or a list (using parentheses
and a quote to prevent it from being evaluated like a function call, like
`'(5 8 9)`). `conj` has
[a perhaps surprising difference in behavior](https://stackoverflow.com/a/17910712)
on vectors and lists.
{:.active-example}

## So many arguments!

What we have now works, but we have an awful lot of arguments (6). Worse, they
logically break into three groups:

* `get-next-node` and `add-children` specify the behavior of the particular
  search algorithm we want `search` to use.
* `goal?` and `children` specify the features of the problem space we're
  searching.
* `open-nodes` and `closed-nodes` are "bookkeeping" arguments needed by
  `search`

Unfortunately, there's nothing in this blob of arguments that
makes this organization clear. We've put related arguments together in the
sequence, but there's nothing that separates them.

Clearly not good.

If we were in an object-oriented world, a reasonable response here would be to
create new classes that would collect together the related bits of logic in
a named type. In Clojure, that idea is typically implemented as a *map*,
which gives us named tags for the different functions.

### Encapsulating problems

As an example, we could define `sample-problem` as a map containing two
fields:

```clojure
(define sample-problem
  {:goal? …
   :children …})
```

Here we use keywords (e.g., `:goal?` and `:children`) as the *keys* in our map;
this is very common, but not required. Pretty much any values can be used as
keys; Clojure is just able to do lookup on keywords particularly efficiently,
which makes them a nice choice. The values can also be pretty much
anything, including functions, which is really useful in this case since it's
functions we want.

Borrowing from [our previous work]({% link _pages/02-simple-search-algorithms.markdown %}),
here's a definition of our `sample-problem`:

```klipse
(defn origin-goal?
  [[x y]]
  (and (zero? x) (zero? y)))

(def up    [ 0  1])
(def down  [ 0 -1])
(def left  [-1  0])
(def right [ 1  0])

(def all-moves [up down left right])

(defn apply-move
  [position move]
  (vec (map + position move)))

(defn legal-coordinate
  [x]
  (and (>= x 0) (<= x 10)))

(defn legal-state
  [position]
  (every? legal-coordinate position))

(defn grid-children
  [position]
  (filter legal-state (map (partial apply-move position) all-moves)))

(def sample-problem
  {:goal? origin-goal?
   :children grid-children})
```

Now we can extract and apply the relevant functions from `sample-problem`:

```klipse
((:goal? sample-problem) [0 0])
```

![Microscope icon](/assets/Microscope_icon_32.png)
Change `[0 0]` to a different position. Change `:goal?` to `:children`, and
then play with the position argument there as well. (Remember that we limit
child coordinates to be between 0 and 10.)
{:.active-example}

### Encapsulating search algorithms

[Previously]({% link _pages/02-simple-search-algorithms.markdown %}) we'd
developed the following definitions for depth-first and breadth-first search:

```clojure
; Same for both search algorithms
(defn get-next-node
  [open-nodes]
  (first open-nodes))

(defn dfs-add-children
  [children open-nodes]
  (concat children open-nodes))

(defn bfs-add-children
  [children open-nodes]
  (concat open-nodes children))
```

We can, however, take this opportunity to simplify these definitions. Note
that `get-next-node` is really just `first`, and `dfs-add-children` is just
`concat`. The only one of these functions that actually "does" anything is
`bfs-add-children`, and all it does is reverse the order of the arguments
before calling `concat`.

So we could simplify the previous definitions to:

```clojure
(def get-next-node first)
(def dfs-add-children concat)
(def bfs-add-children #(concat %2 %1))
```

The question, then, is whether it's actually *worth* it to introduce these
names here. Normally I'd be a big fan introducing names to improve readability
and maintanability. Here, though, these will be inside maps that will
themselves have descriptive names, so it's not clear that they add much, so
I'm going to skip those names and go straight to the maps:

```klipse
(def depth-first-search
  {:get-next-node first
   :add-children concat})

(def breadth-first-search
  {:get-next-node first
   :add-children #(concat %2 %1)})
```

![Microscope icon](/assets/Microscope_icon_32.png)
Experiment with both of these to make sure you understand how the maps will
work. Swap `depth-first-search` and `breadth-first-search` and see what does
and doesn't change. Note also the expressions like
`(:get-next-node depth-first-search)` return a function, which we can then
call with the same syntax (i.e., `(f x)`) of other function calls, where
instead of a function name `f` we have the expression.
{:.active-example}

```klipse
((:get-next-node depth-first-search) [5 8 9])
```

```klipse
((:add-children breadth-first-search) [5 8 9] [6 3 2 0])
```

---

## The new version of `search`

Given all that, we can put together the new version of `search`. We could
receive the arguments in the "traditional" way and extract the values by
keys as follows:

```clojure
(defn search
  [search-algorithm problem open-nodes closed-nodes]
  (let [next-node ((:get-next-node search-algorithm) open-nodes)]
    (if ((:goal? problem) next-node)
      …
      )))
```

We can, however, simplify that by using Clojure's *destructuring* tools. These
allow us to extract and name the components of the maps right away in the
declaration of the argument list. Clojure allows us to write things like:

```klipse
(defn f [{:keys [this that] :as entire-map}]
  (println "The value of :this was" this " and the value of :that was" that)
  (println "The whole map is" entire-map))

(f {:this "Hello!" :that 5})
```

Here `[{:keys [this that]}]` is saying that `f` will receive a map as an
argument (the curly braces say that) and the map will have at least two keys,
`:this` and `:that`. Clojure will then extract the values associated with those
keys, and then assign them to parameter names `this` and `that` (without the
colons because they're not keywords). We can then refer to those in the body
of the function just as if they'd been passed in as separate arguments.

Note also that this only extracts the keys `:this` and `:that` from the map that is
passed in, but in no way *limits* the map to only having those keys. This
allows us to add other elements to our maps which will then be ignored here
since they're not needed.

The `:all` is an optional keyword that allows us to provide a name for the
whole map if we want/need that. We'll need that in `search` because we have
to pass the entire search algorithm and problem maps back around in the
recursive calls.

We can now use destructing to extract the relevant components from our
`search-algorithm` and `problem` arguments above:

```klipse
(defn search
  [{:keys [get-next-node add-children] :as search-algorithm}
   {:keys [goal? children] :as problem}
   open-nodes closed-nodes max-calls]
  (println open-nodes)
  (let [next-node (get-next-node open-nodes)]
    (if (or (goal? next-node) (zero? max-calls))
      next-node
      (search
        search-algorithm
        problem
        (add-children
          (remove-previous-states (children next-node) open-nodes closed-nodes)
          (rest open-nodes))
        (conj closed-nodes next-node)
        (dec max-calls)))))
```

## Yay – it works!

This has made our search tool far more usable. In our previous version, for
example, depth-first search was completely unusable because we would frequently
just end up alternating back and forth between adjacent states, drifting off
into unproductive infinite loops. Now the fact that we avoid duplication will
make things *much* better:

```klipse
(search depth-first-search sample-problem [[3 3]] #{} 20)
```

Our search now terminates, and the length of the `open` list never grows to
insane lengths because we never have duplicates there. Note that depth-first
search *would* go off into an infinite search if we didn't limit the
coordinates. Since our depth-first implementation applies the `up` move first,
if there weren't any bounds on our space we would simply go up forever from our
starting point, only ever finding the goal if it happened to be right above
our starting point.

![Microscope icon](/assets/Microscope_icon_32.png)
It's still quite easy to give it problems it can't solve, typically by moving
the starting location a little farther away from the goal location.  
{:.active-example}
