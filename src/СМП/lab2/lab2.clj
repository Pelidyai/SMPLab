(ns СМП.lab2.lab2)


(def step 0.05)

(defn get_steps
  [up_to]
  (for [x (range (/ up_to step))] (* x step))
  )

(defn trap
  [func current next]
  (* (/ (+ (func current) (func next)) 2) (- next current))
  )

(defn integral
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

;(def v #(get_steps %))
;(println (v 1))

