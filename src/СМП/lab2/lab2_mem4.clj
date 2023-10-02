(ns СМП.lab2.lab2_mem4)
; FIX MEM

(def global_step 1)

(defn trap
  ([operator current]
   (trap operator current (+ current global_step))
   )
  ([operator current next]
   (* (/ (+ (operator current) (operator next)) 2) (- next current)))
  )

(defn fix [f] (fn g [& args] (apply f g args)))

(defn trap_parts_sum
  ([to_call operator up_to accumulator current]
   (if (>= current (- up_to global_step))
     (+ accumulator (trap operator current))
     (to_call operator up_to (+ accumulator (trap operator current)) (+ current global_step))
     )
   )
  )

(defn fixed_mem_trap_sum
  []
  (fix (memoize trap_parts_sum))
  )

(defn integral_mem
  ([operator]
   (fn [x] ((fixed_mem_trap_sum) operator x 0 0))
   )
  )

(defn target_func
  [x]
  (/ (* x x) 2)
  )

(defn ex
  []
  (let [integrated (integral_mem target_func)]
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
(def integrated (integral_mem target_func))
(println "second")
(println (integrated 50))
(time (integrated 41))
(time (integrated 56))
(time (integrated 61))
(time (integrated 66))
(time (integrated 71))
(time (integrated 76))
(time (integrated 81))
