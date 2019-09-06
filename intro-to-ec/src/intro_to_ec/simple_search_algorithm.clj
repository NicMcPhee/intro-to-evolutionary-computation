(ns
 intro-to-ec.simple-search-algorithm)

(defn get-next-node
  "Returns the next node to explore from the frontier.
   Assumes that the frontier is a sequence, and that
   the next node to explore is the first node in
   that sequence."
  [frontier]
  (first frontier))

(defn dfs-add-children
  "Adds the given child nodes to the frontier by
   appending them to the front of the frontier, so
   they will be explored *before* all other nodes
   in the frontier."
  [children frontier]
  (concat children frontier))

(defn bfs-add-children
  "Adds the given child nodes to the frontier by
   appending them to the end of the frontier, so
   they will be explored *after* all other nodes
   in the frontier."
  [children frontier]
  (concat frontier children))

(defn search
  "A generic implementation of a simple search algorithm that uses
   a sequence to hold its frontier of unexplored nodes. Specifying
   `get-next-node` and `add-children` can change the behavior of
   the search algorithm from, for example, breadth-first search to
   depth-first search. The arguments `goal?` and `make-children`
   define what problem is being explored."
  [max-calls get-next-node add-children goal? make-children frontier]
  (println frontier)
  (let [next-node (get-next-node frontier)]
    (if (goal? next-node)
      next-node
      (if (zero? max-calls)
        :max-calls-reached
        (search
         (dec max-calls)
         get-next-node
         add-children
         goal?
         make-children
         (add-children
          (make-children next-node)
          (remove #(= % next-node) frontier)))))))
