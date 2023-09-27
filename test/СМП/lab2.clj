(ns СМП.lab2
  (:require [clojure.test :refer :all])
  (:require [СМП.lab2.lab2]))


(defn target_func
  [x]
  (/ (* x x) 2)
  )

(def integrated (СМП.lab2.lab2/integral target_func))

(deftest test_steps_creation
  (is (= (count (СМП.lab2.lab2/get_steps 5)) 100))
  )

(deftest test_integral
  (is (< (abs (- 20833 (integrated 50))) 0.5))
  )
