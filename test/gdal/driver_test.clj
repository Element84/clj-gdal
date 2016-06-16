(ns gdal.driver-test
  (:require [clojure.test :refer :all]
            [gdal.core :as gdal]
            [gdal.driver :as driver]))

(deftest create-test
  (testing "in memory raster"
    (let [mem (gdal/get-driver-by-name "MEM")
          dataset (driver/create mem "test" 128 128)]
      (is (some? dataset)))))
