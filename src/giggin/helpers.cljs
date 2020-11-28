(ns giggin.helpers)

(defn format-price
  [price]
  (str " $" price))

(defn get-event-target
  [event]
  (-> event .-target .-value))

(defn get-event-checked
  [event]
  (-> event .-target .-checked))