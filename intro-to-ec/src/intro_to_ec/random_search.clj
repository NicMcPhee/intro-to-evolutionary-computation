(ns intro-to-ec.random-search
  (:require [intro-to-ec.knapsack :as ks]))

(defn random-solution
  [length]
  (repeatedly length #(rand-int 2)))

(defn random-search
  [knapsack-instance evaluator number-of-trials]
  (let [num-items (count (:items knapsack-instance))
        random-solutions (repeatedly number-of-trials #(random-solution num-items))
        scored-solutions (map (partial ks/evaluate-solution evaluator knapsack-instance) random-solutions)]
    (apply max-key :score scored-solutions)))
