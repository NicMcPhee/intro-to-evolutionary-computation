(ns
 intro-to-ec.grid-search-problem)

;; The possible moves in this lattice world. Each
;; move is represented by a vector indicating the
;; change in both x and y coordinates associated
;; with this move.
(def up    [0  1])
(def down  [0 -1])
(def left  [-1  0])
(def right [1  0])
(def all-moves [up down left right])

(defn apply-move
  "Apply a move to a given position, yielding the new position"
  [position move]
  (vec (map + position move)))

(defn legal-coordinate
  "Limit our search to the space where both coordinates
   are in the range [0, 10]."
  [x]
  (and (>= x 0) (<= x 10)))

(defn legal-state
  "Return true if both coordinates are legal."
  [position]
  (every? legal-coordinate position))

(defn make-children
  "Generate a list of all the possible child states
   reachable from the given initial position."
  [position]
  (map (partial apply-move position) all-moves))

(defn goal?
  "A goal checking function that assumes the target
   position is the origin, i.e., [0 0]."
  [position]
  (= position [0 0]))
