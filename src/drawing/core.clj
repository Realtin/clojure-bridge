(ns drawing.core
  (:require [quil.core :as q]))

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :rgb)
  (q/stroke 255 0 0))

(def width (q/screen-width))
(def height (q/screen-height))

(defn abs [z]
  (if (neg? z)
    (- z)
    z))

(defn set-color [mx my]
  (q/stroke (abs (- mx 255))
            (abs (- my 255))
            (abs (/ (+ mx my) 2)))
  (q/fill (abs (- mx 255))
            (abs (- my 255))
            (abs (/ (+ mx my) 2))))

;(defn rectangle [edge xpos ypos]
;  (cond 
;    (= edge 00) [(- xpos 10) ypos]
;    (= edge 01) [(+ xpos 10) ypos]
;    (= edge 10) [xpos (- ypos 10)]
;    (= edge 11) [xpos (+ ypos 10)]
;    :else [0 0]))
  
(defn size-rect [x y size]
   (q/rect (- x (/ size 2)) (- y (/ size 2)) size size ))

(defn perspective-rec []
  (let [x (q/mouse-x)
        y (q/mouse-y)
        size (/ y 2)]
  (q/line 0 0  (- x (/ size 2)) (- y (/ size 2)))
  (q/line width 0 (+ x (/ size 2)) (- y (/ size 2)))
  (q/line 0 height (- x (/ size 2)) (+ y (/ size 2)))
  (q/line width height (+ x (/ size 2)) (+ y (/ size 2)))
  (size-rect x y size)))

(defn draw []
  (q/background 0)
  (set-color (q/mouse-x) (q/mouse-y))
  (perspective-rec))



(q/defsketch hello-lines
  :title "Rainbow-lines"
  :size :fullscreen
  :features [:present]
  :setup setup
  :draw draw )