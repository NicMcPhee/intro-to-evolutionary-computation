(ns intro-to-ec.heuristic-search
  (:require [clojure.set :as cset])
  (:require [clojure.data.priority-map :as pm])
  (:require [intro-to-ec.search-with-solutions :as sws]))


(def depth-first-search
  {:get-next-node first
    :add-children concat})

(defn distance-heuristic
  [node goal]
  (+ (Math/abs (- (first node) (first goal))) (Math/abs (- (second node) (second goal)))))

(defn add-heuristic
  [frontier goal kids]
  (reduce (fn [kid frontier goal] (assoc frontier kid (distance-heuristic kid goal)) frontier goal kids)))

(defn is-goal?
   [location goal]
   (= goal location))

   (defn generate-path
     [came-from node]
     (if (= :start-node (get came-from node))
       [node]
       (conj (generate-path came-from (get came-from node)) node)))

   (defn remove-previous-states
     [new-states frontier visited]
     (remove (cset/union (set frontier) (set visited)) new-states))

(defn search
  [{:keys [get-next-node add-children]}
   {:keys [goal make-children]}
   start-node max-calls]
  (loop [frontier [start-node]
         came-from {start-node :start-node}
         num-calls 0]
    (println num-calls ": " frontier)
    (println came-from)
    (let [current-node (get-next-node frontier)]
      (cond
        (is-goal? current-node goal) (generate-path came-from current-node)
        (= num-calls max-calls) :max-calls-reached
        :else
        (let [kids (remove-previous-states
                    (make-children current-node) frontier (keys came-from))]
          (recur
           (add-children
            (rest frontier)
            goal
            kids)
           (reduce (fn [cf child] (assoc cf child current-node)) came-from kids)
           (inc num-calls)))))))
