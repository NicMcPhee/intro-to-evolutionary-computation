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

(defn add-priority
  [kid goal] [kid (distance-heuristic kid goal)])
(defn add-priority-map
  [kids goal] (map (fn [kid] (add-priority kid goal)) kids))

(defn reduce-frontier-distance
  [frontier kids goal]
  (into frontier (add-priority-map kids goal)))
"We need to turn kids into [kids priority] to pass into where we have kids in reduce-frontier-distance"

(def distance-search
  {:get-next-node (fn [pmap](first (first pmap)))
   :add-children reduce-frontier-distance
   })

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

(defn remove-previous-states
    [new-states frontier visited]
    (remove (cset/union (set frontier) (set visited)) new-states))

(defn search
  [{:keys [get-next-node add-children]}
   {:keys [goal make-children]}
   start-node max-calls]
  (loop [frontier (pm/priority-map start-node 10)
         came-from {start-node :start-node}
         num-calls 0]
    (println num-calls ": " frontier)
    (println came-from)
    (let [current-node (get-next-node frontier)]
      (println current-node)
      (cond
        (is-goal? current-node goal) (generate-path came-from current-node)
        (= num-calls max-calls) :max-calls-reached
        :else
        (let [kids (remove-previous-states
                    (make-children current-node)
                    frontier
                    (keys came-from))]
          (recur
           (add-children
            (dissoc frontier start-node)
            kids
            goal)
           (reduce (fn [cf child] (assoc cf child current-node)) came-from kids)
           (inc num-calls)))))))
