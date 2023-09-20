(ns СМП.lab1.lab1_3)

(defn my-map
  [function roll]
  (let [roll_with_first_vector (cons (vector (function (first roll))) (rest roll))]
    (reduce
      (fn
        [collected new_to_calc]
        (conj
          collected
          (function new_to_calc)
          )
        ) roll_with_first_vector
      )
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
  (let [roll_with_first_vector (cons
                      (apply-predicate predicate (first roll))
                      (rest roll))]
    (reduce
      (fn
        [collected new_to_calc]
        (concat
          collected
          (apply-predicate predicate new_to_calc)
          )
        ) roll_with_first_vector
      )
    )
  )


(println "core map:")
(println (map (fn [value] (* 10 value)) '(1 2 3)))
(println (map (fn [string] (concat "concated:" string)) '("a" "b" "c")))


(println "my map:")
(println (my-map (fn [value] (* 10 value)) '(1 2 3)))
(println (my-map (fn [string] (concat "concated:" string)) '("a" "b" "c")))

;(print (cons (vector (first (list 1 2 3))) (list 2 3) ))
;(println (reduce (fn [value roll]
;                 (cons
;                   (print (concat "value" (str value)))
;                   (println (concat "roll" (str roll)))
;                   )
;                 ) '(1 2 3)))
;(print (reduce (fn [value roll]
;                 (conj
;                   value
;                   roll
;                   )
;                 ) (list (vector 1) 2 3) ))

(println "core filter:")
(println (filter (fn [value] (> 5 value)) '(1 10 2 20 3)))
(println (filter (fn [string] (clojure.string/includes? string "a")) '("abc" "bcd" "cab")))


(println "my filter:")
(println (my-filter (fn [value] (> 5 value)) '(1 10 2 20 3)))
(println (my-filter (fn [string] (clojure.string/includes? string "a")) '("abc" "bcd" "cab")))


;(println (concat '[1 2 3] '(1)))
