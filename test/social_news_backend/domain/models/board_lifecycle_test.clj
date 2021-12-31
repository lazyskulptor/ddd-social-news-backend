(ns social-news-backend.domain.models.board-lifecycle-test
  (:require
   [social-news-backend.domain.models.board :as b]
   [social-news-backend.domain.models.user :as u]
   [clojure.test :refer :all]))

(use-fixtures
  :once
  (fn [f]
    (def test-keeper (u/create-user {:name "Josh"}))
    (def test-board-name "main board")
    (f)))

(deftest test-create
  (is
   (=
    [nil test-board-name true false [test-keeper]]
    (let [{:keys [id name public? personal? boardkeeper]}
          (b/create-board
           {:name test-board-name, :public? true :personal? false :boardkeeper test-keeper})]
      [id name public? personal? boardkeeper])))

  (is
   (thrown? AssertionError
    (b/create-board
     {:id 1 :name test-board-name, :public? true :personal? false :boardkeeper test-keeper})))
  )
