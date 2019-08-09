(ns intro-to-ec.exhaustive-search
  (:require [clojure.math.combinatorics :as combo]
            [intro-to-ec.knapsack :as ks]))

(defn exhaustive-search
  [evaluator knapsack-instance]
  (let [num-items (count (:items knapsack-instance))
        all-possible-solutions (combo/selections [0 1] num-items)]
    (map #(ks/evaluate-solution evaluator knapsack-instance %) all-possible-solutions)))
