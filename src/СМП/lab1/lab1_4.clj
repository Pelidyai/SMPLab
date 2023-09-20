(ns СМП.lab1.lab1_4)

(def global_symbols (list "a" "b" "c"))

(defn add-symbol
  [to_add symbol]
  (clojure.string/join (list symbol to_add))
  )

(defn add-all-symbols
  [to_add symbols]
  (let
    [inner-add (fn [symbol] (add-symbol to_add symbol))]
    (flatten (map inner-add symbols))
    )
  )

(defn filter-symbols
  [to_add symbols]
  (filter (fn [symbol] (not= symbol (str (first to_add)))) symbols)
  )

(defn add-all-filtered-symbols
  [to_add symbols]
  (let
    [inner-filter (fn
                    [inner_to_add]
                    (add-all-symbols inner_to_add (filter-symbols inner_to_add symbols))
                    )
     ]
    (flatten (map inner-filter to_add))
    )
  )

(defn combine
  [length symbols]
  (reduce (fn
            [collected new_symbols]
            (add-all-filtered-symbols collected new_symbols))
          (take length (repeat symbols))
          )
  )

(println (combine 2 global_symbols))
;(println (add-all-filtered-symbols global_symbols global_symbols))
;(println (filter-symbols "ab" global_symbols))
;(println (let [var (add-all-symbols "ab" global_symbols)] var))
;(println (filter-symbols "ab" global_symbols))
;(println (combine 2 global_symbols))
