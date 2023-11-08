(ns СМП.lab4.rules.rules-utils)

(defn get-transform
  [rules, arg]
  (some
    (fn [rule]
      (if ((first rule) arg)
        (second rule)
        false)
      )
    rules)
  )

(defn single-to-bool
  [arg]
  ((get-transform
     (eval 'to-boolean-rules)
     arg) arg)
  )

(defn single-to-DNF
  [expr]
  ((get-transform
     (eval 'to-DNF-rules)
     expr) expr)
  )