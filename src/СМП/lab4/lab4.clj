(ns СМП.lab4.lab4
  (:require [СМП.lab4.primitives.constants :refer :all])
  (:require [СМП.lab4.primitives.variables :refer :all])
  (:require [СМП.lab4.operations.inversion :refer :all])
  (:require [СМП.lab4.operations.disjunction :refer :all])
  (:require [СМП.lab4.operations.conjunction :refer :all])
  (:require [СМП.lab4.operations.implication :refer :all])
  (:require [СМП.lab4.rules.rules-utils :refer :all])
  (:require [СМП.lab4.rules.rules-implementation :refer :all])
  )

(def a false)

; region inversion
(println "inversion")
(println (inversion-to-bool (inversion (variable 'a))))
(println (inversion-to-bool (inversion (constant true))))
(println (inversion (inversion (constant true))))
(println (inversion (disjunction (list (constant true) (variable 'a)))))
(println (inversion (conjunction (list (constant true) (variable 'a)))))
;endregion

;region disjunction
(println "\ndisjunction")
(println (disjunction-to-bool (disjunction (list (constant true) (variable 'a)))))
(println (disjunction-to-bool (disjunction (list (constant false) (constant false)))))
(println (disjunction-to-bool (disjunction (list (constant false) (constant false) (constant true)))))

(println (multiply-dis-dis (disjunction (list (variable 'a) (variable 'b))) (disjunction (list (variable 'c) (variable 'd)))))
;(:СМП.lab4.operations.disjunction/disj
;  (:СМП.lab4.operations.conjunction/conj (:СМП.lab4.primitives.variables/var a) (:СМП.lab4.primitives.variables/var d))
;  (:СМП.lab4.operations.conjunction/conj (:СМП.lab4.primitives.variables/var b) (:СМП.lab4.primitives.variables/var d))
;  (:СМП.lab4.operations.conjunction/conj (:СМП.lab4.primitives.variables/var a) (:СМП.lab4.primitives.variables/var c))
;  (:СМП.lab4.operations.conjunction/conj (:СМП.lab4.primitives.variables/var b) (:СМП.lab4.primitives.variables/var c))
;  )


(println (multiply-disjunctions (list (disjunction (list (variable 'a) (variable 'b))) (disjunction (list (variable 'c) (variable 'd))) (disjunction (list (variable 'e) (variable 'f))))))

;(:СМП.lab4.operations.disjunction/disj
;  (:СМП.lab4.operations.conjunction/conj (:СМП.lab4.primitives.variables/var f) (:СМП.lab4.primitives.variables/var b) (:СМП.lab4.primitives.variables/var c))
;  (:СМП.lab4.operations.conjunction/conj (:СМП.lab4.primitives.variables/var f) (:СМП.lab4.primitives.variables/var a) (:СМП.lab4.primitives.variables/var c))
;  (:СМП.lab4.operations.conjunction/conj (:СМП.lab4.primitives.variables/var f) (:СМП.lab4.primitives.variables/var b) (:СМП.lab4.primitives.variables/var d))
;  (:СМП.lab4.operations.conjunction/conj (:СМП.lab4.primitives.variables/var f) (:СМП.lab4.primitives.variables/var a) (:СМП.lab4.primitives.variables/var d))
;  (:СМП.lab4.operations.conjunction/conj (:СМП.lab4.primitives.variables/var e) (:СМП.lab4.primitives.variables/var b) (:СМП.lab4.primitives.variables/var c))
;  (:СМП.lab4.operations.conjunction/conj (:СМП.lab4.primitives.variables/var e) (:СМП.lab4.primitives.variables/var a) (:СМП.lab4.primitives.variables/var c))
;  (:СМП.lab4.operations.conjunction/conj (:СМП.lab4.primitives.variables/var e) (:СМП.lab4.primitives.variables/var b) (:СМП.lab4.primitives.variables/var d))
;  (:СМП.lab4.operations.conjunction/conj (:СМП.lab4.primitives.variables/var e) (:СМП.lab4.primitives.variables/var a) (:СМП.lab4.primitives.variables/var d))
;  )

;endregion

;region conjunction
(println "\nconjunction")
(println (conjunction-to-bool (conjunction (list (constant true) (variable 'a)))))
(println (conjunction-to-bool (conjunction (list (constant false) (constant false)))))
(println (conjunction-to-bool (conjunction (list (constant true) (constant true)))))
(println (conjunction-to-bool (conjunction (list (constant true) (constant false) (constant true)))))
(println (conjunction (list (conjunction (list (constant true) (constant false) (constant true))) (conjunction (list (constant true) (constant false) (constant true))))))

;(:СМП.lab4.operations.conjunction/conj
;  (:СМП.lab4.primitives.constants/const true)
;  (:СМП.lab4.primitives.constants/const false)
;  (:СМП.lab4.primitives.constants/const true)
;  (:СМП.lab4.primitives.constants/const true)
;  (:СМП.lab4.primitives.constants/const false)
;  (:СМП.lab4.primitives.constants/const true)
;  )
(println (multiply-dis-con (disjunction (list (variable 'a) (variable 'b))) (conjunction (list (constant true) (constant false) (constant true)))))
;(:СМП.lab4.operations.disjunction/disj
;  (:СМП.lab4.operations.conjunction/conj (:СМП.lab4.primitives.constants/const true) (:СМП.lab4.primitives.constants/const false) (:СМП.lab4.primitives.constants/const true) (:СМП.lab4.primitives.variables/var a))
;  (:СМП.lab4.operations.conjunction/conj (:СМП.lab4.primitives.constants/const true) (:СМП.lab4.primitives.constants/const false) (:СМП.lab4.primitives.constants/const true) (:СМП.lab4.primitives.variables/var b))
;  )

;endregion

;region implication
(println "\nimplication")
(println (implication-to-bool (implication (constant true) (variable 'a))))
(println (implication-to-bool (implication (constant false) (constant false))))
(println (implication-to-bool (implication (constant true) (constant true))))
(println (implication-to-bool (implication (constant false) (constant true))))
;endregion


(def test-expression (inversion (disjunction
                                  (list
                                    (implication (variable 'X) (variable 'Y))
                                    (inversion (implication (variable 'Y) (variable 'Z)))
                                    )
                                  )))

;(def test-expression (conjunction
;                       (list
;                         (variable 'X)
;                         (disjunction (list (variable 'Y) (variable 'Z)))
;                         )
;                       ))


;(println test-expression)


(defn DNF
  [expr]
  (let [intermediate-dnf (single-to-DNF expr)]
    (if (conjunction? intermediate-dnf)
      (try-to-be-disjunction intermediate-dnf)
      intermediate-dnf)
    )
  )

(println (DNF test-expression))

;(:СМП.lab4.operations.disjunction/disj
;  (:СМП.lab4.operations.conjunction/conj
;    (:СМП.lab4.primitives.variables/var X)
;    (:СМП.lab4.operations.inversion/invert (:СМП.lab4.primitives.variables/var Y))
;    (:СМП.lab4.operations.inversion/invert (:СМП.lab4.primitives.variables/var Y))
;    )
;  (:СМП.lab4.operations.conjunction/conj (:СМП.lab4.primitives.variables/var X)
;    (:СМП.lab4.operations.inversion/invert (:СМП.lab4.primitives.variables/var Y))
;    (:СМП.lab4.primitives.variables/var Z))
;  )