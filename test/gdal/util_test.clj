(ns gdal.util-test
  (:require [clojure.test :refer :all]
            [gdal.util :as util]))

(deftest test-gdal-type
  (testing "a short array (in more ways than one)"
    (let [xs (into-array Short/TYPE [42])]
      (is (= 3 (util/array->gdal-type xs))))))
