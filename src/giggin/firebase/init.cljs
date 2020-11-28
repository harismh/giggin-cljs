(ns giggin.firebase.init
  (:require ["firebase/app" :as firebase]
            ["firebase/database"]
            ["firebase/auth"]
            [giggin.firebase.auth :refer [on-auth-state-changed]]))

(defn firebase-init
  []
  (if (zero? (alength firebase/apps)) ; if initialized
    (firebase/initializeApp
     #js {:apiKey "FILL IN"
          :authDomain "FILL IN"
          :databaseURL "FILL IN"
          :projectId "FILL IN"
          :appId "FILL IN"})
    (firebase/app))
  (on-auth-state-changed))