---
layout: page
title:  "Implementing simple states in Clojure"
categories: search clojure
---

# {{ page.title }}

<!-- TOC depthFrom:1 depthTo:6 withLinks:1 updateOnSave:1 orderedList:0 -->

- [{{ page.title }}](#-pagetitle-)
	- [Representing states](#representing-states)
	- [Generating "child" states](#generating-child-states)
	- [Next: Breadth-first and depth-first search](#next-breadth-first-and-depth-first-search)

<!-- /TOC -->

In [the previous installment]({% link _pages/00-intro-to-search.markdown %}) we
looked at a (very) simple example of search. In this installment we'll look
at how to implement that in Clojure, and some of the most important (if
quite basic) search algorithms: Breadth-first and depth-first search.

## Representing states

Let's start with the Pat's state, which was an ordered pair $$(x, y)$$. We'll
represent that in Clojure as a vector `[x y]` containing the two values. Note
that a comma character (`,`) is considered whitespace in Clojure, which means
that we can write this as either `[x y]` or `[x, y]` – Clojure will read
them as the same thing.

![Microscope icon](/assets/Microscope_icon_32.png) We can see this treatment of `,` by actually evaluating expressions with
and without commas and seeing that we get the same results. Play with the
live example below!
{:.active-example}

```klipse
(= [2 3] [2, 3])
```

## Generating "child" states

For a given state `[x y]`, we're going to need to be able to generate the
four states reachable from the moves `up`, `down`, `left`, and `right`. Let's
start by defining four _move_ vectors that indicate the changes to
the $$x$$ and $$y$$ coordinates. We'll also gather them together into a
collection called `all-moves` for when we want to work with the full set
later.

```klipse
(def up    [ 0  1])
(def down  [ 0 -1])
(def left  [-1  0])
(def right [ 1  0])
(def all-moves [up down left right])
```

![Microscope icon](/assets/Microscope_icon_32.png) Now we can use
[the Clojure function `map`](https://clojuredocs.org/clojure.core/map) to
"apply" a move to any `[x y]` pair. Try it out below. Change `up` to any of
the other directions, and try changing `[10 10]` to other initial positions.
{:.active-example}

```klipse
(map + [10 10] up)
```

Let's turn that `map` call into a function do we don't have to keep
remembering how it works. (The `vec` call isn't strictly necessary. It
converts a _list_ `(3 5)` into a _vector_ `[3 5]`, which hopefully makes
it a little easier to distinguish between states and lists of states
later on.)

```klipse
(defn apply-move
  [position move]
  (vec (map + position move)))
```

We're also going to want to take a given state and apply all four moves to
it, generating the four resulting _child_ states.

```klipse
(defn generate-child-states
  [position]
  (map (partial apply-move position) all-moves))
```

![Microscope icon](/assets/Microscope_icon_32.png) Explore applying
`generate-child-states` to various initial positions to make sure you
understand what it's doing and how it works. You might want to look up
[the Clojure function `partial`](https://clojuredocs.org/clojure.core/partial)
and make sure you understand what it's doing here.
{:.active-example}

```klipse
(generate-child-states [10 10])
```

## Next: Breadth-first and depth-first search

Now that we have the code in place to essentially generate a "tree" of states,
in [the next installment]({% link _pages/02-simple-search-algorithms.markdown %}) we'll look at two important (but simple) search
algorithms: breadth-first and depth-first search.
