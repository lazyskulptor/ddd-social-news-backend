(ns social-news-backend.domain.models.board-lifecycle-test
  (:require
   [social-news-backend.domain.models.board :as b]
   [social-news-backend.domain.models.user :as u]
   [clojure.test :refer :all])
  (:import [social_news_backend.domain.models.board Board]))

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
      (instance? Board (apply b/create-board (vals (dissoc test-param :id))))))
  (is
   (= true
      (b/board? (apply b/create-board (vals (dissoc test-param :id))))))
  (is
   (=
    (assoc (dissoc test-param :boardkeeper)
           :members (b/build-members (:boardkeeper test-param)))
    (into {} (apply b/create-board (vals (dissoc test-param :id))))))

  (is
   (thrown? AssertionError
            (b/create-board test-board-name "true" false test-keeper))))
