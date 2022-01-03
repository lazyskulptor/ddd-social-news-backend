(ns social-news-backend.domain.specs.board)
  (:require [clojure.spec.alpha :as s]
            [social-news-backend.domain.models.user :refer [user?]]))

(s/def :domain.board/id (s/nilable string?))
(s/def :domain.board/boardkeeper user?)
(s/def :domain.board/cokeeper (s/coll-of user?))
(s/def :domain.board/others (s/coll-of user?))
(s/def :domain.board/member user?)
(s/def :domain.board/name string?)
(s/def :domain.board/public? boolean?)
(s/def :domain.board/personal? boolean?)
(s/def :domain.board/members
  (s/keys :req-un [:domain.board/boardkeeper :domain.board/cokeeper :domain.board/others]))
(s/def :domain.board/board
  (s/keys :req-un [:domain.board/id :domain.board/name :domain.board/public? :domain.board/personal? :domain.board/members]))
