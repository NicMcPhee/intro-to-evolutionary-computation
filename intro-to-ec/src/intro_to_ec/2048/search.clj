(ns intro-to-ec.2048.search
  (:require [intro-to-ec.2048.game :as game]))


(defn has-2048?
  [board]
  (not (= (.indexOf board 2048) -1)))

(defn score
  [board]
  (apply + board))

(defn count-zeros
  [board]
  (count (filter zero? board)))

"Stolen"
(defn remove-visited
  [new-states frontier visited]
  (remove (cset/union (set frontier) (set visited)) new-states))

(defn generate-path
  [came-from node]
  (if (= :start-node (get came-from node))
    [node]
    (conj (generate-path came-from (get came-from node)) node)))

(defn search
  [{:keys [get-next add-children]}
   {:keys [goal? make-children]}
   start-state max-calls]
  (loop [frontier [start-state]
         came-from {start-state :start}
         num-calls 0]
    (println num-calls ": " frontier)
    (println came-from)
    (let [current (get-next frontier)]
      (cond
        (goal? current) (generate-path came-from current)
        (= num-calls max-calls) :max-calls-reached
        :else
        (let [kids (remove-visited (make-children current) frontier (keys came-from))]
          (recur
           (add-children kids (rest frontier))
           (reduce (fn [cf child] (assoc cf child current)) came-from kids)
           (inc num-calls)
           ))))))