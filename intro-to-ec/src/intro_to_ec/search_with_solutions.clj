(ns intro-to-ec.search-with-solutions
  (:require [clojure.set :as cset]))

(defn remove-previous-states
  [new-states frontier visited]
  (cset/difference
   ; The function `set` takes a collection and returns a set with those items
   (set new-states)
   (cset/union (set frontier) (set visited))))

(def depth-first-search
  {:get-next-node first
   :add-children concat})

(def breadth-first-search
  {:get-next-node first
   :add-children #(concat %2 %1)})

(def random-search
  {:get-next-node rand-nth
   :add-children concat})

(defn path-to
  [came-from node]
  (loop [current node
         path '()]
    (let [parent (get came-from current)]
      (if (= parent :start-node)
        (conj path current)
        (recur parent (conj path current))))))

(defn search
  [{:keys [get-next-node add-children]}
   {:keys [goal? make-children]}
   start-node max-calls]
  (loop [frontier [start-node]
         visited #{}
         came-from {start-node :start-node}
         num-calls 0]
    (println num-calls ": " frontier)
    (println came-from)
    (let [next-node (get-next-node frontier)]
      (cond
        (goal? next-node) came-from ; (path-to visited next-node)
        (= num-calls max-calls) :max-calls-reached
        :else
        (let [kids (make-children next-node)]
          (recur
           (add-children
            (remove-previous-states kids frontier visited)
            (rest frontier))
           (conj visited next-node)
           (merge
            (reduce (fn [cf child] (assoc cf child next-node)) {} kids)
            came-from)
           (inc num-calls)))))))
