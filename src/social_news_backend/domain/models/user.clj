(ns social-news-backend.domain.models.user
  (:require [clojure.spec.alpha :as s]))


(defrecord ^:private User [id name])
(defn user? [param] (instance? User param))

(defn create-user
  "create a new Board as Entity"
  [{:keys [id name]}]
  {:pre [(s/valid? nil? id)
         (s/valid? string? name)]}
  (->User nil name))
