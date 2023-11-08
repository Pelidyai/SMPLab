(ns СМП.lab4.operations.inversion
  (:require [СМП.lab4.rules.rules-utils :refer :all])
  (:require [СМП.lab4.operations.disjunction :refer :all])
  (:require [СМП.lab4.operations.conjunction :refer :all])
  )


(defn inversion? [expr]
  (= ::invert (first expr))
  )

(defn inversion-arg [expr]
  {:pre [(inversion? expr)]}
  (second expr))

(declare inversion)

(defn inversion-disjunction-create
  [expr]
  {:pre [(disjunction? expr)]}
  (conjunction
    (doall
      (map
        (fn [arg] (inversion arg))
        (disjunction-args expr)
        )
      )
    )
  )

(defn inversion-conjunction-create
  [expr]
  {:pre [(conjunction? expr)]}
  (disjunction
    (doall
      (map
        (fn [arg] (inversion arg))
        (conjunction-args expr)
        )
      )
    )
  )

(defn inversion-inversion-create
  [expr]
  (inversion-arg expr)
  )

(defn inversion-simple-create
  [expr]
  (list ::invert expr)
  )

(def inversion-creation-rules
  (list
    [inversion? inversion-inversion-create]
    [disjunction? inversion-disjunction-create]
    [conjunction? inversion-conjunction-create]
    [(fn [arg] true) inversion-simple-create]
    )
  )

(defn inversion [expr]
  ((get-transform
     inversion-creation-rules
     expr) expr)
  )


(defn inversion-to-bool [expr]
  {:pre [(inversion? expr)]}
  (not
    (single-to-bool (inversion-arg expr))
    )
  )

; region inversion to DNF rules
(defn simple-inversion-to-DNF
  [expr]
  {:pre [(inversion? expr)]}
  (inversion (single-to-DNF (inversion-arg expr)))
  )


(defn inversion-disjunction-to-DNF
  [expr]
  {:pre [(and (inversion? expr) (disjunction? (inversion-arg expr)))]}
  (conjunction
    (doall
      (map
        (fn [arg] (inversion arg))
        (disjunction-args (inversion-arg expr))
        )
      )
    )
  )

(defn inversion-conjunction-to-DNF
  [expr]
  {:pre [(and (inversion? expr) (conjunction? (inversion-arg expr)))]}
  (disjunction
    (doall
      (map
        (fn [arg] (inversion arg))
        (conjunction-args (inversion-arg expr))
        )
      )
    )
  )

(def inversion-to-DNF-rules
  (list
    [disjunction? inversion-disjunction-to-DNF]
    [conjunction? inversion-conjunction-to-DNF]
    [(fn [arg] true) simple-inversion-to-DNF]
    )
  )
; endregion

(defn inversion-to-DNF [expr]
  {:pre [(inversion? expr)]}
  ((get-transform
     inversion-to-DNF-rules
     (inversion-arg expr)) expr)
  )