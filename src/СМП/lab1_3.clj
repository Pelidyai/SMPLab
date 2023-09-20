(ns СМП.lab1_3)

(defn my-map
  [function roll]
  (let [vector_roll (cons (vector (function (first roll))) (rest roll))]
    (reduce
      (fn
        [collected new_to_calc]
        (conj
          collected
          (function new_to_calc)
          )
        ) vector_roll
      )
    )
  )


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
