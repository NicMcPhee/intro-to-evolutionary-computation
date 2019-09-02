(def up [0 1])
(def down [0 -1])
(def left [-1 0])
(def right [1 0])
(def moves [up down left right])

(def start-position '(10 10))
(def start-state
  {:position start-position
   :depth 0
   :uuid (java.util.UUID/randomUUID)})

(defn rand-child [{:keys [depth position uuid]}]
  (vector
    (rand-nth
      (map
        (fn [move index]
          {:depth (inc depth)
           :position (map + move position)
           :parent-uuid uuid
           :uuid (java.util.UUID/randomUUID)})
        moves
        (range)))))

(defn all-children [{:keys [depth position uuid]}]
  (map
    (fn [move index]
      {:depth (inc depth)
       :position (map + move position)
       :parent-uuid uuid
       :uuid (java.util.UUID/randomUUID)})
    moves
    (range)))

(defn make-tree [depth]
  (apply
    concat
    (take depth
          (iterate #(mapcat rand-child %) [start-state]))))

(defn make-dot
  [{:keys [depth position uuid parent-uuid]}]
  (println (str "\"" uuid "\" [label=\"" (pr-str position) "\"];"))
  (when parent-uuid
    (println (str "\"" parent-uuid "\" -> \"" uuid "\""))))

; L0_10_10 [label="(10, 10)"];
; L0_10_10 -> "(10, 9)" [label="down"];
