(ns СМП.lab4.operations.implication
  (:require [СМП.lab4.operations.inversion :refer :all])
  (:require [СМП.lab4.operations.disjunction :refer :all])
  (:require [СМП.lab4.operations.conjunction :refer :all])
  (:require [СМП.lab4.rules.rules-utils :refer :all])
  )


(defn implication [a b]
  (cons ::impl (list a b))
  )

(defn implication? [expr]
  (= ::impl (first expr))
  )

(defn implication-first-arg [expr]
  {:pre [(implication? expr)]}
  (first (rest expr))
  )

(defn implication-second-arg [expr]
  {:pre [(implication? expr)]}
  (second (rest expr))
  )

(defn implication-to-bool [expr]
  {:pre [(implication? expr)]}
  (if (and (single-to-bool (implication-first-arg expr)) (not (single-to-bool (implication-second-arg expr))))
    false
    true)
  )

(defn implication-to-DNF [expr]
  {:pre [(implication? expr)]}
  (disjunction ; a -> b = -a v b
    (list
      (single-to-DNF (inversion (implication-first-arg expr)))
      (single-to-DNF (implication-second-arg expr))
      )
    )
  )

