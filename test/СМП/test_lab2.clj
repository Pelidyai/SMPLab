(ns СМП.test_lab2
  (:require [clojure.test :refer :all])
  (:require [СМП.lab2.lab2]))


(defn target_func
  [x]
  (/ (* x x) 2)
  )

(def integrated (СМП.lab2.lab2/integral target_func))

(deftest test_integral
  (is (< (abs (- 20833 (integrated 50))) 0.5))
  )


(time (integrated 50000))
(time (integrated 50000))
(time (integrated 50000))
(time (integrated 50000))
(time (integrated 50000))
(time (integrated 50000))
(time (integrated 50000))
(time (integrated 50000))
(time (integrated 50000))
(time (integrated 50000))
