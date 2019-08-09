(ns intro-to-ec.greedy)

(defn efficiency
  [item]
  (/ (:value item) (:weight item)))

(defn items-weight-value
  "prefix-weight-value computes the weight and value of sequence of items.
   This takes a sequence of `knapsack-items` and computes the total weight and
   total value of this solution, returning them as a map with keys
   `:total-weight` and `:total-value`."
  [knapsack-items]
  {:total-weight (reduce + (map :weight knapsack-items))
   :total-value  (reduce + (map :value  knapsack-items))})

(defn greedy-search
  [knapsack-instance]
  (let [sorted-items (sort-by efficiency #(compare %2 %1) (:items knapsack-instance))
        prefixes (map #(take % sorted-items) (range (inc (count sorted-items))))
        prefix-sums (map items-weight-value prefixes)]
    (last (take-while #(<= (:total-weight %) (:max-weight knapsack-instance)) prefix-sums))))

;;; A really good example of where greedy search doesn't work. Greedy gives
;;; us a solution with value 390, when there are 4 better solutions with
;;; values 420, 450, 480, and 540. The best (540) is optained by selecting
;;; items 0, 7, and 8. That gives us a weight of 50+60+70=180 (which is *very*
;;; near the maximum capacity, and a score of 150+180+210=540.

(def instance-bad-for-greedy
  {:max-weight 181.0,
   :items [{:weight 50.0, :value 150.0}
           {:weight 80.0, :value 240.0}
           {:weight 280.0, :value 420.0}
           {:weight 160.0, :value 240.0}
           {:weight 40.0, :value 60.0}
           {:weight 200.0, :value 300.0}
           {:weight 280.0, :value 420.0}
           {:weight 60.0, :value 180.0}
           {:weight 70.0, :value 210.0}
           {:weight 160.0, :value 240.0}]})
