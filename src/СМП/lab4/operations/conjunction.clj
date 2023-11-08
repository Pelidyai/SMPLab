(ns СМП.lab4.operations.conjunction
  (:require [СМП.lab4.rules.rules-utils :refer :all])
  (:require [СМП.lab4.operations.disjunction :refer :all])
  )


(defn conjunction? [expr]
  (= ::conj (first expr))
  )


(defn conjunction-args [expr]
  {:pre [(conjunction? expr)]}
  (rest expr))


(defn conjunction [args]
  (cons ::conj (doall
                 (reduce
                   (fn [acc arg]
                     (if (conjunction? arg)
                       (concat acc (conjunction-args arg))
                       (conj acc arg))
                     )
                   '() args)
                 )
        )
  )


(defn conjunction-to-bool [expr]
  {:pre [(conjunction? expr)]}
  (every? single-to-bool (conjunction-args expr))
  )

(defn conjunction-to-DNF [expr]
  {:pre [(conjunction? expr)]}
  (conjunction
    (doall
      (map
        (fn [arg] (single-to-DNF arg))
        (conjunction-args expr)
        )
      )
    )
  )

(defn one-arg-is-disjunction [expr]
  {:pre [(conjunction? expr)]}
  (some disjunction? (conjunction-args expr))
  )

(defn multiply-dis-dis
  [first second]
  {:pre [(and (disjunction? first) (disjunction? second))]}
  (disjunction (doall
                 (reduce (fn [acc arg]
                           (concat (doall
                                     (map (fn [arg2] ((eval 'conjunction) (list arg arg2)))
                                          (disjunction-args first))
                                     ) acc)
                           )
                         '() (disjunction-args second))
                 )
               )
  )

(defn multiply-disjunctions
  [disjunctions]
  (reduce
    (fn [acc dis]
      (multiply-dis-dis acc dis)
      )
    disjunctions)
  )

(defn multiply-dis-con
  [dis con]
  {:pre [(and (disjunction? dis) (conjunction? con))]}
  (disjunction
    (map (fn [arg] (conjunction (cons arg (conjunction-args con))))
         (disjunction-args dis)
         )
    )
  )

(defn get-only-dis
  [expr]
  {:pre [(conjunction? expr)]}
  (filter disjunction? (conjunction-args expr))
  )

(defn get-not-dis
  [expr]
  {:pre [(conjunction? expr)]}
  (filter (fn [arg] (not (disjunction? arg))) (conjunction-args expr))
  )

(defn try-to-be-disjunction [expr]
  {:pre [(conjunction? expr)]}
  (if (one-arg-is-disjunction expr)
    (let [disjunctions (get-only-dis expr)
          not-disjunctions (get-not-dis expr)
          intermediate-dis (multiply-disjunctions disjunctions)]
      (multiply-dis-con intermediate-dis (conjunction not-disjunctions))
      )
    expr)
  )
