(ns СМП.lab2.lab2)


(def step 0.05)


(defn target_func
  [x]
  (/ (* x x) 2)
  )
(defn get_steps
  [up_to]
  (for [x (range (/ up_to step))] (* x step))
  )

(defn trap
  [func current next]
  (* (/ (+ (func current) (func next)) 2) (- next current))
  )

(defn func
  [operator]
  #(reduce
     (fn [collected current] (+ collected current))
     0
     (flatten
       (let [collector (vector)]
         (for [input (get_steps %)]
           (cons (trap operator input (+ input step)) collector)
           )
         )
       )
     )
  )

(def integrated (func #(target_func %)))
(println
  (integrated 50)
  )
(println
  (integrated 40)
  )
(println
  (integrated 30)
  )



;(def v #(get_steps %))
;(println (v 1))

