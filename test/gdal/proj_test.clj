(ns gdal.proj-test
  (:require [clojure.test :refer :all]
            [nio.core :as nio]
            [gdal.band :as band]
            [gdal.core :as gdal]
            [gdal.dataset :as dataset]
            [gdal.proj :as proj])
  (:import [org.gdal.gdalconst gdalconst]))

;;; Fixture to to wrap all tests in the namespace, called just once

(defn setup-once []
  (gdal/init))

(defn teardown-once [])

(defn fixture-once [test-fn]
  (setup-once)
  (test-fn)
  (teardown-once))

(use-fixtures :once fixture-once)

;;; Fixture to to wrap each test in the namespace, called for every test

(def test-data-path "test/data/l8/LC80290302015263LGN00_B2.TIF")
(def ^:dynamic *proj*)

(defn setup-each [test-fn]
  (let [proj-data (-> test-data-path
                      (gdal/open)
                      (dataset/get-projection))]
    (with-redefs [*proj* proj-data]
      (test-fn))))

(defn teardown-each [])

(defn fixture-each [test-fn]
  (setup-each test-fn)
  (teardown-each))

(use-fixtures :each fixture-each)

;;; Tests

(deftest test-top-level-funcs
  (testing "name"
    ;; Exactly how GeoTIFF files are handled is explained in detail here:
    ;; http://www.gdal.org/frmt_gtiff.html
    (is (= "WGS 84 / UTM zone 14N" (proj/get-name *proj*))))
  (testing "ids"
    (is (= [{:code-space "EPSG", :code "32614", :authority "IdentifierSpace[“EPSG”]", :version ""}]
           (proj/get-ids *proj*)))
    (is (= {:code-space "EPSG", :code "32614", :authority "IdentifierSpace[“EPSG”]", :version ""}
           (proj/get-id *proj*)))))

(deftest test-datum-funcs
  (testing "name"
    (is (= "WGS_1984" (proj/get-datum-name *proj*))))
  (testing "ellipsoid"
    (is (= "WGS 84" (proj/get-ellipsoid-name *proj*)))
    (is (= 0.08181919084262128   (proj/get-eccentricity *proj*))))
  ; (testing "distance"
  ;   (is (= "" (get-distance *proj* lat1 long1 lat2 long2))))
  (testing "meridian"
    (is (= "Greenwich" (-> *proj*
                           (proj/get-prime-meridian)
                           (.getName)
                           (.toString)))))
  ; (testing "bursa-wolf"
  ;   )
  )

(deftest test-coordinate-system-funcs
  (testing "coords"
    (is (= "Cartesian CS: East (m), North (m)."
           (proj/get-coord-name *proj*)))
    (is (= 2 (proj/get-coord-dim *proj*))))
  (testing "axes"
    (is (= 2 (count (proj/get-axes *proj*))))
    (is (= "Northing" (proj/get-axis-name *proj* 1)))
    (is (= "m" (proj/get-axis-unit *proj* 1)))
    (is (= "Easting" (proj/get-axis-name *proj* 0)))
    (is (= "m" (proj/get-axis-unit *proj* 0)))))
