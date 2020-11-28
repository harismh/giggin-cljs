(ns giggin.firebase.auth
  (:require ["firebase/app" :as firebase]
            [giggin.firebase.db :refer [db-save!]]
            [giggin.state :as state]))

(defn sign-in-with-google []
  (let [provider (firebase/auth.GoogleAuthProvider.)]
    (.signInWithPopup (firebase/auth) provider)))

(defn sign-out []
  (.signOut (firebase/auth)))

(defn on-auth-state-changed []
  (.onAuthStateChanged
   (firebase/auth)
   (fn [user]
     (if user
       (let [uid (.-uid user)
             profile {:display-name (.-displayName user)
                      :email (.-email user)
                      :photo-url (.-photoURL user)}]
         (do (reset! state/user profile)
             (db-save! ["users" uid "profile"]
                       (clj->js profile))))
       (reset! state/user nil)))))