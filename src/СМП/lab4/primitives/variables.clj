(ns СМП.lab4.primitives.variables)

(defn variable [name]
  {:pre [(symbol? name)]}
  (list ::var name)
  )

(defn variable? [expr]
  (= (first expr) ::var)
  )

(defn variable-name [v]
  {:pre [(variable? v)]}
  (second v)
  )

(defn same-variables? [v1 v2]
  (and
    (variable? v1)
    (variable? v2)
    (= (variable-name v1)
       (variable-name v2))))

(defn variable-to-bool [v]
  {:pre [(variable? v)]}
  (boolean (eval (second v)))
  )
