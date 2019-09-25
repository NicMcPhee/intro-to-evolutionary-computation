(ns intro-to-ec.n-puzzle)

; This essentially defines a type, `State`, that is a map
; with at least two keys, `:board` and `:blank-position`.
(defrecord State [board blank-position])

(defn legal? [board-size n]
  (and (<= 0 n) (< n board-size)))

(defn swap [board old-pos new-pos]
  "Swap the tile in `old-pos` with that in `new-pos`."
  (let [old-value (get-in board old-pos)
        new-value (get-in board new-pos)]
    (assoc-in (assoc-in board old-pos new-value)
              new-pos old-value)))

(defn children [state]
  "Generate the collection of child states that are reachable
   from the given state. This is what would result from moving
   the 'blank' space up, down, left, and right."
  (let [x (first (:blank-position state))
        y (second (:blank-position state))
        board-size (count (:board state))]
    (for [[dx dy] [[-1, 0] [1, 0], [0, -1], [0, 1]]
          :let [new-x (+ x dx)
                new-y (+ y dy)]
          :when (legal? board-size new-x)
          :when (legal? board-size new-y)]
      (->State (swap (:board state) [x y] [new-x new-y])
               [new-x new-y]))))

(defn state->vec [state]
  (flatten (:board state)))

(defn num-wrong [goal-state current-state]
  "A simple heuristic that counts the number of incorrectly
   placed tiles."
  (count (filter identity
                 (map not=
                      (state->vec goal-state)
                      (state->vec current-state)))))

(defn zero-or-same? [[x y]]
  (or (= x y)
      (zero? x)
      (zero? y)))

(defn num-non-blank-wrong [goal-state current-state]
  "A simple heuristic that counts the number of incorrectly
   placed non-blank tiles."
  (count (remove zero-or-same?
                 (map (fn [x y] [x y])
                      (state->vec goal-state)
                      (state->vec current-state)))))
