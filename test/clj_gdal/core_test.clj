(ns clj-gdal.core-test
  (:require [clojure.test :refer :all]
            [clj-gdal.core :refer :all]))

(deftest test-drivers
  ;; Drivers are loaded during GDAL initailization. Before calling
  ;; init there are no available driveres. However, this test only
  ;; makes sense if initialization must be invoked explicitly.
  (testing "Initialize GDAL"
    (let [_ (init)]
      (is (< 0 (get-driver-count)))))
  ;; GDAL has a few built in drivers, a geotiff driver is one of them.
  (testing "Loading a driver by name"
    (let [driver (get-driver-by-name "GTiff")]
      (not (= driver nil))))
  ;; I don't know if it makes sense to make a guess at the name of the
  ;; 0th driver, so the assertion is pretty vague.
  (testing "Loading a driver by index"
    (let [driver (get-driver 0)]
      (not (= driver nil)))))

(deftest test-open
  (testing "Opening a geotiff"
    (let [_    (init) ; fixture?
          path "test/data/scene/LT50460262009013PAC01_sr_band4.tif"
          tiff (open path)]
      (not (= tiff nil)))))

