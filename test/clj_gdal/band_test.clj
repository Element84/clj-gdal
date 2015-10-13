(ns clj-gdal.band-test
  (:require [clojure.test :refer :all]
            [clj-gdal.band :refer :all]
            [clj-gdal.core :as gdal]
            [clj-gdal.dataset :as data]
            [nio.core :as nio])
  (:import [org.gdal.gdalconst gdalconst]))

(deftest test-geotiff-handling
  (let [_ (gdal/init) ; fixture?
        path "test/data/scene/LT50460262009013PAC01_sr_band4.tif"
        tiff (gdal/open path)
        band (data/get-raster-band tiff 1)]
    (testing "getting properties"      
      (testing "width"
        (is (= 5000 (get-x-size band))))
      (testing "height"
        (is (= 5000 (get-y-size band))))
      (testing "x block size"
        (is (= 5000 (get-block-x-size band))))
      (testing "y block size"
        (is (= 1 (get-block-y-size band))))
      (testing "GDAL data type"
        (is (= gdalconst/GDT_Int16 (get-data-type band))))
      (testing "Java data type"
        (is (= java.lang.Short (get-java-type band))))
      (testing "Java NIO buffer type"
        (is (= nio/short-buffer))))
    (testing "Raster sequence (seq-ing many blocks as vectors)"
      (testing "implicit call"
        ;; The test image is 5000x5000 pixels. The natural block
        ;; size is 5000 pixels wide and 1 pixel high, so there
        ;; should be exactly 5000 blocks.
        (let [blocks (raster-seq band)]
          (is (= 5000 (count blocks)))))
      (testing "band with block size call"
        (let [blocks (raster-seq band :xstep 1000 :ystep 1000)]
          (is (= 25 (count blocks)))))
      (testing "explicit call"
        ;; The same 5000x5000 image is parsed in blocks of 1000x1000
        ;; so there should be five blocks along the x-axis and ten
        ;; blocks along the y-axis, yielding exactly 50 blocks.
        (let [blocks (raster-seq band :xstep 1000 :ystep 500)]
          (is (= 50 (count blocks))))))
    (testing "Raster vector (getting one block as a vector)"
      (testing "implicit call")
      (testing "explicit call"))))
