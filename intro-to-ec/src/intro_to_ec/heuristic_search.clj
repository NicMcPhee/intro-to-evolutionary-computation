(ns intro-to-ec.heuristic-search
  (:require [clojure.set :as cset])
  (:require [clojure.data.priority-map :as pm])
  (:require [intro-to-ec.search-with-solutions :as sws]))

"Bad legacy code ;)"
(def depth-first-search
  {:get-next-node first
    :add-children concat})

"Heuristic Search"
(defn distance-heuristic
  [node goal]
  (+ (Math/abs (- (first node) (first goal))) (Math/abs (- (second node) (second goal)))))

(defn add-heuristic-priority
  [kid goal] [kid (distance-heuristic kid goal)])
(defn add-heuristic-priority-map
  [kids goal] (map (fn [kid] (add-heuristic-priority kid goal)) kids))

(defn reduce-frontier-distance
  [frontier kids goal nothing]
  (into frontier (add-heuristic-priority-map kids goal)))

(def distance-search
  {:get-next-node (fn [pmap](first (first pmap)))
   :add-children reduce-frontier-distance
   })

"A*"

(defn a*-heuristic
  [node goal steps]
  (+ steps (Math/abs (- (first node) (first goal))) (Math/abs (- (second node) (second goal)))))

(defn add-a*-priority
    [kid goal steps] [kid (a*-heuristic kid goal steps)])
(defn add-a*-priority-map
  [kids goal steps] (map (fn [kid] (add-a*-priority kid goal steps)) kids))

(defn reduce-frontier-distance-a*
  [frontier kids goal steps]
  (into frontier (add-a*-priority-map kids goal steps)))

"TODO: change the 1 to the number of steps when you pass it in"
(def a*-search
  {:get-next-node (fn [pmap](first (first pmap)))
   :add-children reduce-frontier-distance-a*
  })

(defn is-goal?
   [location goal]
   (= goal location))

(defn generate-path
    [came-from node]
    (if (= :start-node (get came-from node))
      [node]
      (conj (generate-path came-from (get came-from node)) node)))

(defn get-path-length
  [came-from node len]
  (if (= :start-node (get came-from node))
    1
    (+ 1 (get-path-length came-from (get came-from node) len))))

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
        (empty? frontier) :no-solutions
        (is-goal? current-node goal) (generate-path came-from current-node)
        (= num-calls max-calls) :max-calls-reached
        :else
        (let [kids (remove-previous-states
                    (make-children current-node)
                    frontier
                    (keys came-from))]
          (recur
           (add-children
            (dissoc frontier current-node)
            kids
            goal
            (get-path-length came-from current-node 0))
           (reduce (fn [cf child] (assoc cf child current-node)) came-from kids)
           (inc num-calls)))))))
