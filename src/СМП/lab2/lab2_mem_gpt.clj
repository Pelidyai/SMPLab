(ns СМП.lab2.lab2-mem-gpt)


(defn trapezoidal-integration [f a b n]
  (let [dx (/ (- b a) n)
        mid-sum (fn [k] (f (+ a (* k dx))))]
    (loop [i 1
           sum (f a)]
      (if (< i n)
        (recur (inc i) (+ sum (* 2 (mid-sum i))))
        (+ sum (f b) (* 2 (mid-sum n)) (* dx 0.5))))))

(defn trapezoidal-integration_mem [f a b n]
  (let [dx (/ (- b a) n)
        mid-sum (memoize (fn [k] (f (+ a (* k dx)))))]
    (loop [i 1
           sum (f a)]
      (if (< i n)
        (recur (inc i) (+ sum (* 2 (mid-sum i))))
        (+ sum (f b) (* 2 (mid-sum n)) (* dx 0.5))))))

(defn target_func
  [x]
  (/ (* x x) 2)
  )
(def n 100)

(println "non-mem:")

(time (trapezoidal-integration target_func 0 40 n))
(time (trapezoidal-integration target_func 0 55 n))
(time (trapezoidal-integration target_func 0 60 n))
(time (trapezoidal-integration target_func 0 65 n))
(time (trapezoidal-integration target_func 0 70 n))
(time (trapezoidal-integration target_func 0 75 n))
(time (trapezoidal-integration target_func 0 80 n))


(println "mem:")
(time (trapezoidal-integration_mem target_func 0 40 n))
(time (trapezoidal-integration_mem target_func 0 55 n))
(time (trapezoidal-integration_mem target_func 0 60 n))
(time (trapezoidal-integration_mem target_func 0 65 n))
(time (trapezoidal-integration_mem target_func 0 70 n))
(time (trapezoidal-integration_mem target_func 0 75 n))
(time (trapezoidal-integration_mem target_func 0 80 n))


