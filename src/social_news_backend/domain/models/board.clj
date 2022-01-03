(ns social-news-backend.domain.models.board
  (:require [clojure.spec.alpha :as s]))

(defrecord ^:private Members [boardkeeper cokeeper others])
(defrecord ^:private Board [id name public? personal? members])
(defn board? [entity] (instance? Board entity))

(defn build-members
  ([boardkeeper]
   (build-members boardkeeper [] []))
  ([boardkeeper others]
   (build-members boardkeeper [] others))
  ([boardkeeper cokeeper others]
   {:pre [(s/valid? :domain.board/boardkeeper boardkeeper)
          (s/valid? :domain.board/cokeeper cokeeper)
          (s/valid? :domain.board/others others)]}
   (->Members boardkeeper cokeeper others)))

(defn create-board
  "create a new Board as Entity"
  [name public? personal? boardkeeper]
  {:pre [(s/valid? :domain.board/name name)
         (s/valid? :domain.board/public? public?)
         (s/valid? :domain.board/personal? personal?)
         (s/valid? :domain.board/boardkeeper boardkeeper)]}
  (->Board nil name public? personal? (build-members boardkeeper)))

(defn reconstitue-board [params]
  (map->Board params))
