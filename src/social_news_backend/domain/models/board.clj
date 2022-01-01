(ns social-news-backend.domain.models.board
  (:require [clojure.spec.alpha :as s]
            [social-news-backend.domain.models.user :refer [user?]]))

(defrecord ^:private Members [boardkeeper coboardkeeper members])
(defrecord ^:private Board [id name public? personal? members])
(defn board? [entity] (instance? Board entity))

(defn build-members
  ([boardkeeper]
   (build-members boardkeeper [] []))
  ([boardkeeper others]
   (build-members boardkeeper [] others))
  ([boardkeeper cokeeper others]
   {:pre [(s/valid? user? boardkeeper)
          (s/valid? true? (every? user? cokeeper))
          (s/valid? true? (every? user? others))]}
   (->Members boardkeeper cokeeper others)))

;; TODO: prevent overwrite members direct
;; TODO: create function to update members of board
(defn create-board
  "create a new Board as Entity"
  [{:keys [id name public? personal? boardkeeper]}]
  {:pre [(s/valid? nil? id)
         (s/valid? string? name)
         (s/valid? boolean? public?)
         (s/valid? boolean? personal?)
         (s/valid? user? boardkeeper)]}
  (->Board nil name public? personal? (build-members boardkeeper)))

(defn reconstitue-board [params]
  (map->Board params))
