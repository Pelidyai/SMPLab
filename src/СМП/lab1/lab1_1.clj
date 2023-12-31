(ns СМП.lab1.lab1_1)
(def global_symbols (vector "a" "b" "c"))

(defn add_symbol
  ([to_add symbols]
   (if (seq symbols)
     (if (= (first to_add) (first symbols))
       (add_symbol to_add (rest symbols))
       (cons (cons (first symbols) to_add) (add_symbol to_add (rest symbols))))
     )
   )
  )

(defn add_for_all
  [to_add_list, symbols]
  (if (seq to_add_list)
    (concat (add_symbol (first to_add_list) symbols) (add_for_all (rest to_add_list) symbols))
    )
  )


(defn combine
  ([length symbols_to_add]
    (combine length (list) symbols_to_add)
   )
  ([length to_combine symbols_to_add]
   (if (= (count (first to_combine)) length)
     to_combine
     (let [new_to_combine (if (= (count to_combine) 0)
                            (add_symbol to_combine symbols_to_add)
                            (add_for_all to_combine symbols_to_add)
                            )]
       (combine length new_to_combine symbols_to_add)
       )
     )
   )
  )

;(println (cons "c" (list "a" "b") ))
;(println (add_symbol (list "a" "b") global_symbols))
;(println (add_for_all (list (list "a" "b") (list "c" "d")) global_symbols))
(println (combine 2 global_symbols))
