---
layout: page
title:  "The basic ideas of search"
categories: search
---

# {{ page.title }}

## A simple example of search

<!-- TOC depthFrom:1 depthTo:6 withLinks:1 updateOnSave:1 orderedList:0 -->

- [{{ page.title }}](#-pagetitle-)
	- [A simple example of search](#a-simple-example-of-search)
	- [The size of search trees](#the-size-of-search-trees)
	- [Repetition and search trees](#repetition-and-search-trees)
	- [Next: Implementing these ideas in Clojure](#next-implementing-these-ideas-in-clojure)

<!-- /TOC -->

Let us first consider an _extremely_ (& deliberately) simple example. Imagine
a little robot, Pat, on an integer grid at position (10, 10) who needs to get to
(0, 0).

![Integer grid with robot at (10, 10) and goal at (0, 0).](/assets/simple_search_grid.svg){:width="75%"}

Our friend Pat can only go left-right and up-down. They can't cut across
diagonals – imagine it's a city street map and they can't wander through
random office buildings.

How might we frame this as a search problem? Let us first represent Pat's
_state_, which for our purposes is just Pat's position on the grid as an
ordered pair $$(x, y)$$. <input type="checkbox" id="cb1"/><label for="cb1"><sup class="note-marker">\*</sup></label><span>Pat's state could be a pretty complex thing, including the state of their batteries,
the urgency of the current goal, etc., but we're going to ignore all that for
now.</span> Thus in our example Pat's _initial state_ is (10, 10) and
their _goal state_ is (0, 0). At each time step Pat can make one of four moves:

   * `up`, which takes the state $$(x, y)$$ to $$(x, y+1)$$
   * `down`, which takes the state $$(x, y)$$ to $$(x, y-1)$$
   * `left`, which takes the state $$(x, y)$$ to $$(x-1, y)$$
   * `right`, which takes the state $$(x, y)$$ to $$(x+1, y)$$

A _solution_ for this problem is then a sequence of moves that takes Pat from
(10, 10) to (0, 0). As is typically the case, there are many possible solutions.
Some are in some sense "optimal"; here the solution that goes `left` 10 times
and then `down` 10 times is one of many examples of a _shortest_ solution.

The idea then is to _search_ for a solution (a sequence of moves) that gets
Pat from their start state to their goal state. A common way of visualizing
this is as a _search tree_, where each _node_ represents a state of the system
(Pat's position in our case) and its _children_ represent the states reachable
from the _parent_ state.

The diagram below illustrates Pat starting at (10, 10) and exploring a few
paths:

![A subset of the first few levels of a search tree.](/assets/simple_search_tree.svg)

At the first step, Pat has four options:

   * `up`, which goes to (10, 11)
   * `down`, which goes to (10, 9)
   * `left`, which goes to (9, 10)
   * `right`, which goes to (11, 10)

From each of those new positions, Pat again has
four options, one of which (`left`) leads to (9, 9); to keep the size of the
diagram manageable we've omitted the other three states. From (9, 9) there are
again four options, as illustrated in the figure.

## The size of search trees

In theory Pat could find a path to (0, 0) by simply expanding this tree until
they find the desired node. Even in this very simple example, however, this
quickly becomes infeasible as the number of states that need to be explored
grows rapidly as one expands down the tree. Here, for example, the number of
nodes at level $$N+1$$ is four times the number of nodes at level $$N$$ because
each node has four children. Thus the number of nodes at level $$N$$ is $$4^N$$,
and expanding the tree down the 20 levels necessary for a _minimal_ solution
leads to a level with $$4^{20} = 1,099,511,627,776 \approx 10^{12}$$ nodes!
And that's just for this very simple example; more realistic search spaces
tend to be be _much_ larger; [the number of legal chess configurations is
estimated to be in the neighborhood of $$10^{45}$$](https://math.stackexchange.com/questions/1406919/how-many-legal-states-of-chess-exists). The numbers for the game
of go [are much larger still](https://senseis.xmp.net/?NumberOfPossibleGoGames):

> Statistics from real games between professional players show that it is
> extremely rare for a game to last more than 400 moves. If, on average, there are
> 100 legal moves in every position, then the possible number of games of length
> 400 or less is in the order of $$10^{800}$$, corresponding to around
> $$10^{720}$$ possible games for every atom in the known universe. -- [senseis.xmp.net](https://senseis.xmp.net/?NumberOfPossibleGoGames)

Yikes!

## Repetition and search trees

The search tree above also illustrates an annoying property of most search
trees: there is often repetition, and frequently lots of it. In our diagram
there are two instances of (10, 9) shown, and in fact there are many more
duplicates if we expand the nodes below the first instance of (10, 9) on
the level with depth 1. As we expand farther and farther down we'll find more and
more duplicates; every level with an even depth, for example, will have an
increasing number of copies of (10, 10) that result from sequences that "undo"
all their work such as `up-down-down-up` and `left-down-right-up`. As we'll
see later, we'll probably want our search techniques to avoid
repeatedly processing duplicate states.

There can, however, be times when we want to
revisit a state. If the first time we visited a state it was very
"expensive" to get there (e.g., required many moves), we might have then
decided we didn't
want to further explore that state. If we later find a "cheaper" way to reach
that state, then our interest in it might increase and we might
want to (re)explore the paths from that state.

## Next: Implementing these ideas in Clojure

In [the next installment]({{ site.baseurl }}{% link _pages/01-simple-states-in-clojure.markdown %}) we'll
look at how to represent and manipulate these
simple states in the programming language Clojure, before moving on to various
algorithms for searching "trees" of such states.
