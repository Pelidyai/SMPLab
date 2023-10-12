(ns СМП.lab3.lab3-2
  (:require [СМП.lab3.lab3-1])
  )


(defn split-to-batches
  ([step coll]
   (let [step-drop (partial drop step) step-take (partial take step)]
     (->> (iterate step-drop coll)
          (map step-take)
          )
     )
   )
  )

(defn hard-predicate
  [value]
  (Thread/sleep 10)
  (> 5 value)
  )

(defn lazy-filter-seq
  [predicate step]
  partition
  (let [sequence (iterate (fn [value] (if (= value 10) 2 10)) 10)
        part-filter (partial СМП.lab3.lab3-1/parallel-filter predicate step)]
    (->> sequence
          (split-to-batches (* step 8))
          (map (fn [value] (part-filter value)))
          )
    )
  )

(defn filtered-wrapper
  [predicate step]
  (let [sequence (lazy-filter-seq predicate step)]
    #(doall (->> sequence
                 (flatten)
                 (take %)
                 ))
    )
  )

(defn ex
  []
  (let [filtered-seq (filtered-wrapper hard-predicate 4)]
    (time (filtered-seq 10))
    (time (filtered-seq 200))
    (time (filtered-seq 100))
    (time (filtered-seq 70))
    (time (filtered-seq 10))
    (time (filtered-seq 10))
    (time (filtered-seq 10))
    )
  )
(ex)

;(println (split-to-batches 3 '(1 2 3 4 5 6 7 8)))
