(ns giggin.api
  (:require [ajax.core :refer [GET]]
            [giggin.state :as state]))

(defn index-by
  [key collection]
  (->> collection
       (map (juxt key identity) collection)
       (into {})))

(defn handler [response]
  (reset! state/gigs (index-by :id response)))

(defn error-handler [{:keys [status status-text]}]
  (.log js/console (str "Something went wrong: " status " " status-text)))

(def ^:const
  api-url "https://gist.githubusercontent.com/harismh/292e9ec28d7b44273cb73e6fc4549df8/raw/f976e72d4f4bcee2b894728823450695602ec3d2/giggin_db.json")

(defn seed-gigs []
  (GET api-url {:handler handler
                :error-handler error-handler
                :response-format :json
                :keywords? true}))
