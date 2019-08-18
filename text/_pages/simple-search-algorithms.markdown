---
layout: page
title:  "Simple search algorithms in Clojure"
categories: search clojure
---

In [the previous installment]({% link _pages/simple-states-in-clojure.markdown %}) we
looked at how we might represent (very) simple kinds of search states
in Clojure. In this installment we'll look
at how to actually search the "tree" implied by these states using two
important (if simple) search algorithms: Breadth-first and depth-first search.

## Representing states
