(ns intro-to-ec.puzzle-2048)

(def blank-board [0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0])

(defn print-board [board]
  (println (take 4 board))
  (println (take 4 (subvec board 4)))
  (println (take 4 (subvec board 8)))
  (println (take 4 (subvec board 12))))

(def blank-game {:score 0
                 :board blank-board})

(defn comp-turn
  [board]
  (let [spawn-place (rand-int 16)]
    (if (= (get board spawn-place) 0)
      (assoc board spawn-place 2)
      (comp-turn board))))

(defn player-turn
  [game move]
  "do stuff")

"doesn't work yet, needs recursion"
(defn move-up
  [game]
  (for [i (range 4)]
    (loop [board (:board game)
           j 0
           last-index -4
           last-value 0]
      (println board)
      (println (+ i j))
      (let [value (get (:board game) (+ i j))
            index (+ i j)]
        (cond
          (> j 12)
          board
          (zero? value)
          (recur board (+ 4 j) last-index last-value)
          :else
          (if (= last-value value)
            (recur (assoc (assoc board last-index (* 2 last-value)) index 0) (+ 4 j) last-index 0)
            (recur (assoc (assoc board (+ 4 last-index) value) index 0) (+ 4 j) last-index value)))))))
