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
    (def test-param {:id nil, :name test-board-name, :public? true,
                     :personal? false, :boardkeeper test-keeper})
    (f)))

(deftest test-create
  (is
   (= true
      (b/board? (b/create-board test-param))))
  (is
   (=
    (assoc (dissoc test-param :boardkeeper)
           :members (b/build-members (:boardkeeper test-param)))
    (into {} (b/create-board test-param))))

  (is
   (thrown? AssertionError
            (b/create-board
             {:id 1 :name test-board-name, :public? true :personal? false :boardkeeper test-keeper}))))
