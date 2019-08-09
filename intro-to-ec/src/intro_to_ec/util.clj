(ns intro-to-ec.util
  (:require [clojure.spec.alpha :as s]))

(s/def ::score nat-int?)
(s/def ::search-result (s/keys :req [::score]))
(s/def ::search-results (s/coll-of ::search-result))

(s/def ::summary-entry (s/spec (s/cat :score ::score :count int?)))
(s/def ::summary (s/and (s/* ::summary-entry)
                        #(= (sort (map first %))
                            (map first %))))

(defn summarize-results
  "Summarize a sequence of search results.
   `summarize-results` takes a sequence of `search-results`, each of which
   has a `:score` field, and returns a sorted list of scores paired with
   how many of the search results had that score."
  [search-results]
  (->> search-results
       (group-by ::score)
       (map #(vector (first %) (count (second %))))
       (sort-by first)))

(s/fdef summarize-results
  :args (s/cat :search-results ::search-results)
  :ret ::summary
  ; Here we check two things: First, that the sum of the counts equals
  ; the number of items in the initial list of results; and second that
  ; the list of scores in the results matches the distinct values in the
  ;  list of scores in the input. 
  :fn (s/and
        #(= (count (-> % :args :search-results))
            (reduce + (map :count (:ret %))))
        #(= (distinct (sort (->> % :args :search-results (map ::score))))
            (map :score (:ret %)))))
