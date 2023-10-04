(ns СМП.lab2.lab2)
; NON-MEM

(def step 0.5)

(defn trap
  ([func current]
   (trap func current (+ current step))
   )
  ([func current next]
   (* (/ (+ (func current) (func next)) 2) (- next current)))
  )

(defn trap_parts_sum
  ([operator up_to]
   (trap_parts_sum operator up_to 0 0)
   )
  ([operator up_to accumulator current]
   (if (>= current (- up_to step))
     (+ accumulator (trap operator current))
     (recur operator up_to (+ accumulator (trap operator current)) (+ current step))
     )
   )
  )

(defn integral
  [operator]
  (fn [x] (trap_parts_sum operator x))
  )


(defn target_func
  [x]
  (/ (* x x) 2)
  )
(defn ex
  []
  (let [integrated (integral target_func)]
    (time (integrated 40))
    (time (integrated 55))
    (time (integrated 60))
    (time (integrated 65))
    (time (integrated 70))
    (time (integrated 75))
    (time (integrated 80))
    )
  )


(ex)
(def integrated (integral target_func))
(println "second")
(println (integrated 50))
(time (integrated 41))
(time (integrated 56))
(time (integrated 61))
(time (integrated 66))
(time (integrated 71))
(time (integrated 76))
(time (integrated 81))


