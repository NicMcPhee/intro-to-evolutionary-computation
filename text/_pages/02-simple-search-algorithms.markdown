---
layout: page
title:  "Simple search algorithms in Clojure"
categories: search clojure
---

# {{ page.title }}

In [the previous installment]({% link _pages/01-simple-states-in-clojure.markdown %}) we
looked at how we might represent (very) simple kinds of search states
in Clojure. In this installment we'll look
at how to actually search the "tree" implied by these states using two
important (if simple) search algorithms: Breadth-first and depth-first search.

## A general approach to search

A number of search algorithms, including both
breadth-first and depth-first search, have a shared structure. They have
an _open_ collection of nodes that still need to be explored, which
initially just contains the root node of the search tree (i.e., the
starting point). Then the search process involves selecting a node `next-node`
from `open`. We then check to see if `next-node` is a goal state; if it is
then we're done! If not, then we compute the _child_ nodes and add them
back into `open` to be (possibly) explored in future iterations.

All that might look roughly like this in Clojure:
<input type="checkbox" id="cb1"/><label for="cb1"><sup class="note-marker">\*</sup></label><span>
There's actually a problem with this approach. Since we might in realistic
settings search *many* nodes (millions), this recursive solution could run
out of memory in Clojure. The problem here isn't the recursion, because a
common run-time optimization called *tail recursion* would totally fix the
problem by having each recursive call happen in the same stack frame.
Unfortunately, the Java Virtual Machine (JVM) doesn't support tail
recursion, so Clojure (which was designed to run on the JVM) also doesn't
support tail recursion. (Scheme and Racket both do.) This means that if we
wanted this to be able to search many, many nodes, we would need to use
Clojure's `loop-recur` construct, which allows us to write recursions in a
way that allows them to be converted to essentially `while` loops which won't
run out of memory. We might return to this later.
</span>

```clojure
(defn search
  [open-nodes]
  (let [next-node (get-next-node open-nodes)]
    (if (goal? next-node)
      next-node
      (search
        (add-children
          (children next-node)
          (remove #(= % next-node) open-nodes))))))
```

This assumes the existence of several functions, some of which are problem
dependent, and some of which effectively define the search algorithm.

We'll start with the two that define the behavior of the search algorithm,
and don't depend on the details of the problem:

* `get-next-node`, which determines which, of the possibly many nodes in
  `open-nodes` is the node we're going to explore next.
* `add-children`, which determines how we are going to add the child nodes
  into `open` before recursing back and continuing the search.

The various search algorithms then differ largely on a single questions: How do
we decide which node to remove from `open` (i.e., `get-next-node`) and how to
put the new children into `open` (i.e., `add-children`). It turns out that this
is related to the question of which type of collection we use
for `open`, which could be a sequence, or a set, or any of a number
of other data structures. It's quite straightforward, for example, to
use a sequence for `open` and just always take the first node in the
list as `next-node`. If we do that, it turns out that the big question
becomes how to add the `children` into `open`?

Given that `open` is a sequence (an ordered list), there's essentially two
simple ways to add the children: Append them to the front `open`, or append
them to the end of `open`. It turns out that these two opens give us
depth-first search (append to the front) and breadth-first search (append to
the end).

## Depth-first search

If we append children to the front of `open` we get depth-first search, where
we explore the children (and all their descendants) of a node before
exploring any of the siblings and their descendants. Consider, for example, the
numbering on this graph, which shows the order in which the nodes would be
explored using depth-first search:

![Depth-first numbering of tree nodes.](https://upload.wikimedia.org/wikipedia/commons/1/1f/Depth-first-tree.svg)

Here the `open` list changes as follows:

```clojure
       [1]              ; initially just the start node
    -> [2, 7, 8]        ; the children of node 1
    -> [3, 6, 7, 8]     ; added the children of 2 to the *front*
    -> [4, 5, 6, 7, 8]  ; add the children of 3
    -> [5, 6, 7, 8]     ; 4 has no children
    -> [6, 7, 8]        ; 5 has no children
    -> [7, 8]           ; 6 has no children
    -> [8]              ; 8 has no children
    -> [9, 12]          ; the children of 8
    -> [10, 11, 12]     ; add the children of 9
    -> [11, 12]         ; 10 has no children
    -> [12]             ; 11 has no children
    -> []               ; 12 has no children
    ; we've explored the entire tree!
```

## Breadth-first search

If we append children to the end of `open` we get breadth-first search, where
we explore the siblings of a node before moving down to the children.
Consider, for example, the
numbering on this graph, which shows the order in which the nodes would be
explored using depth-first search:

![Breadth-first numbering of tree nodes.](https://upload.wikimedia.org/wikipedia/commons/3/33/Breadth-first-tree.svg)

Here the `open` list changes as follows:

```clojure
       [1]                ; initially just the start node
    -> [2, 3, 4]          ; the children of node 1
    -> [3, 4, 5, 6]       ; added the children of 2 to the *end*
    -> [4, 5, 6]          ; 3 has no children
    -> [5, 6, 7, 8]       ; add the children of 4
    -> [6, 7, 8, 9, 10]   ; add the children of 5
    -> [7, 8, 9, 10]      ; 6 has no children
    -> [8, 9, 10, 11, 12] ; add the children of 7
    -> [9, 10, 11, 12]    ; 8 has no children
    -> [10, 11, 12]       ; 9 has no children
    -> [11, 12]           ; 10 has no children
    -> [12]               ; 11 has no children
    -> []                 ; 12 has no children
    ; we've explored the entire tree!
```

## Actually implementing search

This means we can easily implement `get-next-node` and `add-children` for
depth- and breadth-first search. They both
just use `first` to get the first item from `open`:

```klipse
(defn get-next-node
  [open-nodes]
  (first open-nodes))
```

Where they differ is in `add-children`. In both cases we can use the `concat`
function to join together the lists of nodes, and its just a matter of who
goes first and who goes last.

```klipse
(defn dfs-add-children
  [children open-nodes]
  (concat children open-nodes))

(defn bfs-add-children
  [children open-nodes]
  (concat open-nodes children))
```

We're almost there, but we need the functions `goal?` and `children` for at
least a test problem to try things out.
Using [the state work for our simple example from before]({% link _pages/01-simple-states-in-clojure.markdown %})
we can define `goal?` and `children`. The one change we'll make to what was
done before is we're going to limit ourselves so that both `x` and `y` are
between 0 and 3, inclusive on both ends. So we'll modify `all-moves` to
filter out any states that violate those constraints.

```klipse
(defn goal?
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
  (and (>= x 0) (<= x 3)))

(defn legal-state
  [position]
  (every? legal-coordinate position))

(defn children
  [position]
  (filter legal-state (map (partial apply-move position) all-moves)))
```

Now we have all these pieces, there's the question of how they are made
available to the `search` function so that we can specify "at runtime" things
like, for example, the details of the problem being solved, and which search
algorithm we're actually using. The typical
approach in functional programming is to take advantage of the fact that we
can pass functions in as arguments. So we'll pass in

- `get-next-node`
- `add-children`
- `goal?`
- `children`

as arguments; in the current setup we also have to pass them
through in the recursive calls as well. So we can see what nodes are being
explored, I've added a `println` that prints each value of `open-nodes` as
the search progresses.

```klipse
(defn search
  [get-next-node add-children goal? children open-nodes]
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
          (children next-node)
          (remove #(= % next-node) open-nodes))))))
```

Now let's see how it works! We can start with bread-first search:

```klipse
(search get-next-node bfs-add-children goal? children [[1 1]])
```

Huzzah!

![Microscope icon](/assets/Microscope_icon_32.png) Feel free to
play around some, but very very careful not to have the start position be
very far from `[0 0]`. Even "short" distances like `[3 3]` quickly generate
*many* nodes. In our very simple example problem, for example, each state has
four child states, which means the number of states at level $$n+1$$ will be
4 times the number of states at level $$n$$, so the total number of nodes in
$$n$$ levels is $$O(n^4)$$.
{:.active-example}

Also, definitely *don't* try to replace breadth-first search with
depth-first search (i.e., replace `bfs-add-children` with
`dfs-add-children`) – you'll end up with an infinite loop because you'll
go from `[1 1]` to `[1 2]` to `[1 3]` to `[1 4]`, etc. That will never find
the solution, and you'll lock up your browser tab or window completely.
We'll fix that problem down the road.
{:.active-example}

So this works, but there are issues, both in the structure of the code and in
the logic of the search itself. if you look at the output above, for example,
you'll notice that we end up "repeating ourselves" rather a lot. There are,
for example, numerous instances of `[1 1]` in our output, with as many as
four copies of `[1 1]` in `open-states` a couple of times.

In [the next installment]({% link _pages/03-clean-up-search-implementation.markdown %})
we'll clean some of that up.
