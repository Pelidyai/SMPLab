(ns СМП.lab3.lab3-1)

(defn apply-predicate
  [predicate value]
  (if (predicate value)
    (vector value)
    (vector)
    )
  )

(defn my-filter
  [predicate roll]
  (reduce
    (fn
      [collected new_to_calc]
      (concat
        collected
        (apply-predicate predicate new_to_calc)
        )
      ) (vector) roll
    )
  )




(defn split-to-batches
  ([step coll]
   (split-to-batches step coll (list))
   )

  ([step coll collector]
   (if
     (seq coll)
     (let [inner-collector (cons (take step coll) collector)]
       (recur step (drop step coll) inner-collector)
       )
     collector
     )
   )
  )


(defn hard-predicate
  [value]
  (Thread/sleep 10)
  (> 5 value)
  )


(def collection
  (->> (iterate inc 0)
       (take 100000)
       )
  )

(defn parallel-filter
  [predicate roll step]
  (->> roll
       (split-to-batches step)
       (map #(future (my-filter predicate %)))
       (doall)
       (map deref)
       (flatten)
       )
  )

(println "lib filter:")
(time (filter hard-predicate collection))

(println "\nparallel filter (step - 5):")
(println (time (parallel-filter hard-predicate collection 5)))

(println "\nparallel filter (step - 20):")
(println (time (parallel-filter hard-predicate collection 20)))

(println "\nparallel filter (step - 2):")
(println (time (parallel-filter hard-predicate collection 2)))

(println "\nnon-parallel filter:")
(time (my-filter hard-predicate collection))


