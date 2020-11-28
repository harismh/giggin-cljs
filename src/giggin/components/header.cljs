(ns giggin.components.header
  (:require [giggin.firebase.auth :refer [sign-in-with-google sign-out]]
            [giggin.state :as state]))

(defn header
  []
  [:header
   [:img.logo {:src "img/giggin-logo.svg" :alt "Giggin Logo"}]
   (if @state/user
     [:button.btn.btn--link.float--right.tooltip
      {:data-tooltip "Sign out"
       :on-click #(sign-out)}
      [:figure.avatar
       [:img {:src (:photo-url @state/user) :alt "User photo"}]]]
     [:button.btn.btn-link.float--right
      {:on-click #(sign-in-with-google)} "Login"])])
