(ns gdal.driver-test
  (:require [clojure.test :refer :all]
            [gdal.core :as gdal]
            [gdal.driver :as driver]))

(deftest create-test
  (testing "in memory raster"
    (let [mem (gdal/get-driver-by-name "MEM")
          dataset (driver/create mem "test" 128 128)]
      (is (some? dataset)))))

(deftest register-test
  (testing "register a driver"
    (comment "I don't know how to test this..."))

(deftest deregister-test
  (testing "deregister a driver"
    (comment "I don't know how to test this...")))
