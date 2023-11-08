(ns СМП.lab4.operations.disjunction
  (:require [СМП.lab4.rules.rules-utils :refer :all])
  )


(defn disjunction? [expr]
  (= ::disj (first expr))
  )

(defn disjunction-args [expr]
  {:pre [(disjunction? expr)]}
  (rest expr))


(defn disjunction [args]
  (cons ::disj (doall
                 (reduce
                   (fn [acc arg]
                     (if (disjunction? arg)
                       (concat acc (disjunction-args arg))
                       (conj acc arg))
                     )
                   '() args)
                 )
        )
  )

(defn disjunction-to-bool [expr]
  {:pre [(disjunction? expr)]}
  (boolean
    (some single-to-bool (disjunction-args expr))
    )
  )

(defn disjunction-to-DNF [expr]
  {:pre [(disjunction? expr)]}
  (disjunction
    (doall
      (map
        (fn [arg] (single-to-DNF arg))
        (disjunction-args expr)
        )
      )
    )
  )
