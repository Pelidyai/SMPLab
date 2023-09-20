(ns СМП.lab1.lab1_2)

(def global_symbols (list "a" "b" "c"))

(defn add_symbol
  ([to_add symbols]
   (add_symbol to_add symbols (list))
   )
  ([to_add symbols collector]
   (if (<= (count symbols) 0)
     collector
     (if (= (first to_add) (first symbols))
       (recur to_add (rest symbols) collector)
       (let [new_collector (cons (cons (first symbols) to_add) collector)]
         (recur to_add (rest symbols) new_collector)))
     )
   )
  )

(defn add_for_all
  ([to_add_list, symbols]
   (add_for_all to_add_list symbols (list))
   )
  ([to_add_list, symbols, collector]
   (if (<= (count to_add_list) 0)
     collector
     (let [new_collector (concat collector (add_symbol (first to_add_list) symbols))]
       (recur (rest to_add_list) symbols new_collector))
     )
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
       (recur length new_to_combine symbols_to_add)
       )
     )
   )
  )


(println (combine 2 global_symbols))
;(println (add_symbol (list "a") global_symbols))
;(println (add_for_all (list (list "a") (list "b")) global_symbols))
