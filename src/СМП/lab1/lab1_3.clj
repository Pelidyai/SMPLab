(ns СМП.lab1.lab1_3)

(defn my-map
  [function roll]
  (reduce
    (fn
      [collected new_to_calc]
      (conj
        collected
        (function new_to_calc)
        )
      ) (vector) roll
    )
  )


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


(println "core map:")
(println (map (fn [value] (* 10 value)) '()))
(println (map (fn [value] (* 10 value)) '(1 2 3)))
(println (map (fn [string] (concat "concated:" string)) '("a" "b" "c")))


(println "my map:")
(println (my-map (fn [value] (* 10 value)) '()))

(println (my-map (fn [value] (* 10 value)) '(1 2 3)))
(println (my-map (fn [string] (concat "concated:" string)) '("a" "b" "c")))


(println "core filter:")
(println (filter (fn [value] (> 5 value)) '(1 10 2 20 3)))
(println (filter (fn [string] (clojure.string/includes? string "a")) '("abc" "bcd" "cab")))


(println "my filter:")
(println (my-filter (fn [value] (> 5 value)) '(1 10 2 20 3)))
(println (my-filter (fn [string] (clojure.string/includes? string "a")) '("abc" "bcd" "cab")))

