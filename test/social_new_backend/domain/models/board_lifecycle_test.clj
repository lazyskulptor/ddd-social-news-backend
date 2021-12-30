(ns social-new-backend.domain.models.board-lifecycle-test
  (:require
   [social-new-backend.domain.models.board :as b]
   [clojure.test :refer :all]))

(use-fixtures
  :once
  (fn [f]
    (def test-keeper (b/->User "Josh"))
      (f)))

(deftest test-create
  (is 
      (=
        [nil "main" true false [test-keeper]]
        (let [{:keys [id name public? personal? boardkeeper]}
              (b/create-board
              {:name "main", :public? true :personal? false :boardkeeper test-keeper})]
          [id name public? personal? boardkeeper]))
        ))

