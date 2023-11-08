(ns СМП.lab4.primitives.constants)

(defn constant [val]
  {:pre [(or (= val true) (= val false))]}
  (list ::const val)
  )

(defn constant? [expr]
  (= (first expr) ::const)
  )

(defn constant-value [expr]
  {:pre [(constant? expr)]}
  (second expr)
  )
