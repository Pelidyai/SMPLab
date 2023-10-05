(ns СМП.lab2.lab2_seq)

(def global_step 0.5)

(defn trap
  ([func current]
   (trap func current (+ current global_step))
   )
  ([func current next]
   (* (/ (+ (func current) (func next)) 2) (- next current)))
  )

(defn trap_parts_sum
  ([operator up_to step]
   (trap_parts_sum operator up_to 0 0 step)
   )
  ([operator up_to accumulator current step]
   (if (>= current (- up_to step))
     (+ accumulator (trap operator current))
     (recur operator up_to (+ accumulator (trap operator current)) (+ current step)  step)
     )
   )
  )

(defn partial_sums
  [operator step]
  (let [sequence (iterate (fn [x] (+ x step)) 0)]
    (->> sequence
          (map (fn [x] (trap_parts_sum operator x step)))
          )
    )
  )

(defn integral
  [operator step]
  (let [mapped_seq (partial_sums operator step)]
    #(nth mapped_seq (int (/ % step)))
    )
  )



(defn target_func
  [x]
  (/ (* x x) 2)
  )
(defn ex
  []
  (let [integrated (integral target_func global_step)]
    (repeat 10 (integrated 10))
    (time (integrated 40))
    (time (integrated 40))
    (time (integrated 55))
    (time (integrated 60))
    (time (integrated 65))
    (time (integrated 70))
    (time (integrated 175))
    (time (integrated 80))
    (time (integrated 40))
    (time (integrated 30))
    )
  )


(ex)
(def integrated (integral target_func global_step))
(println "second")
(println (integrated 50))
(time (integrated 41))
(time (integrated 49))
(time (integrated 61))
(time (integrated 66))
(time (integrated 71))
(time (integrated 76))
(time (integrated 81))


