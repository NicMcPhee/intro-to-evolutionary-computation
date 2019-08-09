(ns intro-to-ec.knapsack)

;; A knapsack problem specification is a map with two entries: `:max-weight`
;; and `:items`. The `:items` entry will be a sequence of maps, each map
;; containing a `:weight` and a `:value` key.

(def sample-knapsack-problem
  "A simple instance of a knapsack problem.
   This has 10 items for potential inclusion. The first on is own is the global
   optima, filling the sack and having value 7. The next best local optima is
   is the last 6 items, which together fill the sack and have a total value of
   6. Replacing any two of those items with one of the weight 2 items will
   slightly reduce the value, leading to another local optima."
  {:max-weight 6
   :items [{:weight 6, :value 7}
           {:weight 2, :value 1.9}
           {:weight 2, :value 1.9}
           {:weight 2, :value 1.9}
           {:weight 1, :value 1}
           {:weight 1, :value 1}
           {:weight 1, :value 1}
           {:weight 1, :value 1}
           {:weight 1, :value 1}
           {:weight 1, :value 1}]})

(defn strongly-correlated-item
  [data-range]
  (let [weight (inc (rand-int data-range))
        value (+ weight (/ data-range 10))]
    {:weight weight, :value value}))

(defn normalize-item
  [multiplier-limit item]
  {:weight (Math/ceil (/ (* 2 (:weight item)) multiplier-limit))
   :value (Math/ceil (/ (* 2 (:value item)) multiplier-limit))})

(defn make-spanner-set
  [spanner-set-size multiplier-limit]
  (map
   (partial normalize-item multiplier-limit)
   (repeatedly spanner-set-size #(strongly-correlated-item 1000))))

(defn scale-span-item
  [multiplier-limit span-item]
  (let [multiplier (inc (rand-int multiplier-limit))]
    {:weight (* multiplier (:weight span-item))
     :value (* multiplier (:value span-item))}))

(defn span
  [spanner-set-size multiplier-limit num-items]
  (let [spanner-set (make-spanner-set spanner-set-size multiplier-limit)]
    (repeatedly num-items #(scale-span-item multiplier-limit (rand-nth spanner-set)))))

(def strongly-correlated-span-instance
  (let [items (span 2 10 10)
        capacity (Math/floor (* (rand) (reduce + (map :weight items))))]
    {:max-weight capacity
     :items items}))

(defn knapsack-weight-value
  "knapsack-weight-value computes the weight and value of a solution.
   This takes a sequence of `knapsack-items` and `proposed-solution`
   as a vector of 0's and 1's, and computes the total weight and
   total value of this solution, returning them as a map with keys
   `:total-weight` and `:total-value`. Note that this does *not* worry
   about the `:max-weight` for this problem instance; that will be
   handled elsewhere."
  [knapsack-items proposed-solution]
  (assoc {:choices proposed-solution}
         :total-weight (reduce + (map * (map :weight knapsack-items) proposed-solution))
         :total-value  (reduce + (map * (map :value  knapsack-items) proposed-solution))))

(defn simple-knapsack-evaluator
  "simple-knapsack-evaluator computes the score of a proposed solution, giving overloaded solutions a score of zero.
   This takes a `knapsack-instance` and a `proposed-solution` and returns the
   score of this proposed solution. If all the selected items fit in the
   knapsack, then the score is the value of those items. If the selected items
   don't fit, then the score is zero."
  [knapsack-instance proposed-solution]
  (let [{total-weight :total-weight, total-value :total-value}
        (knapsack-weight-value (:items knapsack-instance) proposed-solution)]
    (if (> total-weight (:max-weight knapsack-instance))
      0
      total-value)))

(defn evaluate-solution
  [evaluator knapsack-instance proposed-solution]
  (assoc {:choices proposed-solution}
         :score (evaluator knapsack-instance proposed-solution)))
