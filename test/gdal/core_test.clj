(ns gdal.core-test
  (:require [clojure.test :refer :all]
            [gdal.core :as gdal]))

(deftest test-drivers
  ;; Drivers are loaded during GDAL initailization. Before calling
  ;; init there are no available driveres. However, this test only
  ;; makes sense if initialization must be invoked explicitly.
  (testing "Initialize GDAL"
    (let [_ (gdal/init)]
      (is (< 0 (gdal/get-driver-count)))))

  ;; GDAL has a few built in drivers, a geotiff driver is one of them.
  (testing "Loading a driver by name"
    (let [driver (gdal/get-driver-by-name "GTiff")]
      (not (= driver nil))))

  ;; I don't know if it makes sense to make a guess at the name of the
  ;; 0th driver, so the assertion is pretty vague.
  (testing "Loading a driver by index"
    (let [driver (gdal/get-driver 0)]
      (not (= driver nil)))))

(deftest test-open
  (testing "Opening a geotiff"
    (let [_ (gdal/init) ; fixture?
          path "test/data/espa/landsat_8_sr_band_4_san_francisco.tif"
          tiff (gdal/open path)]
      (not (= tiff nil)))))

