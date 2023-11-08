(ns СМП.lab4.rules.rules-implementation
  (:require [СМП.lab4.primitives.constants :refer :all])
  (:require [СМП.lab4.primitives.variables :refer :all])
  (:require [СМП.lab4.operations.inversion :refer :all])
  (:require [СМП.lab4.operations.disjunction :refer :all])
  (:require [СМП.lab4.operations.conjunction :refer :all])
  (:require [СМП.lab4.operations.implication :refer :all])
  )

;(def args-get-rules
;  (list
;    [constant? constant-value]
;    [variable? variable-name]
;    [inversion? inversion-arg]
;    [disjunction? disjunction-args]
;    [conjunction? conjunction-args]
;    [implication? implication-args]
;    )
;  )

(defn no-convert-to-DNF
  [expr]
  expr
  )

(def to-DNF-rules
  (list
    [implication? implication-to-DNF]
    [inversion? inversion-to-DNF]
    [disjunction? disjunction-to-DNF]
    [conjunction? conjunction-to-DNF]
    [(fn [arg] true) no-convert-to-DNF]
    )
  )

(def to-boolean-rules
  (list
    [constant? constant-value]
    [variable? variable-to-bool]
    [inversion? inversion-to-bool]
    [disjunction? disjunction-to-bool]
    [conjunction? conjunction-to-bool]
    [implication? implication-to-bool]
    )
  )
