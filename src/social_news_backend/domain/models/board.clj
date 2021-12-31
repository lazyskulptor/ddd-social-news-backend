(ns social-news-backend.domain.models.board
  (:require [clojure.spec.alpha :as s]
            [social-news-backend.domain.models.user :refer [user?]]))

(defrecord ^:private Members [boardkeeper coboardkeeper member])
(defrecord ^:private Board [id name public? personal? boardkeeper])
(defn board? [entity] (instance? Board entity))

(defn create-board 
  "create a new Board as Entity"
  [{:keys [id name public? personal? boardkeeper]}]
  {:pre [(s/valid? nil? id)
         (s/valid? string? name)
         (s/valid? boolean? public?)
         (s/valid? boolean? personal?)
         (s/valid? user? boardkeeper)]}
  (->Board nil name public? personal? [boardkeeper]))

(defn reconstitue-board [params]
  (map->Board params))
