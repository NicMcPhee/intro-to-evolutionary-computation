(ns intro-to-ec.game-2048)

(def blank-board [1 0 0 0 1 0 0 0 1 0 0 0 0 0 0 0])

(defn print-board [board]
  (println (take 4 board))
  (println (take 4 (subvec board 4)))
  (println (take 4 (subvec board 8)))
  (println (take 4 (subvec board 12))))

(def blank-game {:score 0
                 :board blank-board})

(defn process-seq
  ([xs] (process-seq (first xs) (rest xs)))
  ([last xs]
   (println "---")
   (println last)
   (println xs)
   (cond
     (empty? xs)
     (if (> last 0)
       [last]
       [])
     (= 0 (first xs))
     (process-seq last (rest xs))
     (= last (first xs))
     (cons (* 2 last) (process-seq -1 (rest xs)))
     (> last 0)
     (cons last (process-seq (first xs) (rest xs)))
     :else
     (process-seq (first xs) (rest xs)))))