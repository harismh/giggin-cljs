(ns giggin.core
  (:require [reagent.core :as r]
            [giggin.components.header :refer [header]]
            [giggin.components.gigs :refer [gigs]]
            [giggin.components.orders :refer [orders]]
            [giggin.components.footer :refer [footer]]
            [giggin.firebase.init :refer [firebase-init]]
            [giggin.firebase.db :refer [db-subscribe]]))

(defn app []
  [:div.container
   [header]
   [gigs]
   [orders]
   [footer]])

(defn ^:dev/after-load render-app []
  (r/render
   [app]
   (.getElementById js/document "app")))

(defn ^:export main []
  (render-app)
  (firebase-init)
  (db-subscribe ["gigs"]))
