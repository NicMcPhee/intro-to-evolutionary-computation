(ns intro-to-ec.cleaned-search
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

(defn search
  [{:keys [get-next-node add-children] :as search-algorithm}
   {:keys [goal? make-children] :as problem}
   frontier visited max-calls]
  (println frontier)
  (let [next-node (get-next-node frontier)]
    (if (goal? next-node)
      next-node
      (if (zero? max-calls)
        :max-calls-reached
        (search
         search-algorithm
         problem
         (add-children
          (remove-previous-states (make-children next-node) frontier visited)
          (rest frontier))
         (conj visited next-node)
         (dec max-calls))))))
