(ns intro-to-ec.puzzle-2048)

(def blank-board [1 0 0 0 1 0 0 0 1 0 0 0 0 0 0 0])

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
  (loop [board (:board game)
         i 0]
    (if (< i 4)
      (recur
       (loop [board board
              j 0
              last-index (- i 4)
              last-value 0]
         (println board)
         (println (+ (* 4 j) i))
         (println last-index)
         (if (> j 4)
           board
           (let [value (get board (+ (* 4 i) j))
                 index (+ (* 4 i) j)]
             (cond
               (> j 4)
               board
               (zero? value)
               (recur board (+ 1 j) last-index last-value)
               :else
               (if (= last-value value)
                 (recur (assoc (assoc board index 0) last-index (* 2 last-value)) (+ 1 j) last-index 0)
                 (recur (assoc (assoc board index 0) (+ 4 last-index) value) (+ 1 j) (+ 4 last-index) value))))))
       (+ i 1)) board)))

"
(defn move-up
  [game]
  (let [col1 (move-col-up game 0)
        col2 (move-col-up game 1)
        col3 (move-col-up game 2)
        col4 (move-col-up game 3)])
  (assoc []))"
