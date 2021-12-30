(ns social-news-backend.domain.models.board)

(defrecord ^:private Members [boardkeeper coboardkeeper member])
(defrecord User [name])
(defn user? [param] (instance? User param))
(defrecord ^:private Board [id name public? personal? boardkeeper])
(defn board? [entity] (instance? Board entity))

(defn create-board 
  "create a new Board as Entity"
  [{:keys [id name public? personal? boardkeeper]}]
  {:pre [(nil? id)
         (boolean? public?)
         (boolean? public?)
         ;; (user? boardkeeper)
         (boolean? personal?)]}
  (->Board nil name public? personal? [boardkeeper]))

(defn reconstitue-board [params]
  (map->Board params))
