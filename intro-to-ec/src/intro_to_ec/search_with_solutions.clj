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

(defn search
  [{:keys [get-next-node add-children]}
   {:keys [goal? make-children]}
   start-node max-calls]
 (loop [frontier [start-node]
        visited #{}
        num-calls 0]
  (println num-calls ": " frontier)
  (let [next-node (get-next-node frontier)]
    (cond
      (goal? next-node) next-node
      (= num-calls max-calls) :max-calls-reached
      :else
      (recur
       (add-children
        (remove-previous-states (make-children next-node) frontier visited)
        (rest frontier))
       (conj visited next-node)
       (inc num-calls))))))
