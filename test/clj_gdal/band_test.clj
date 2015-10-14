(ns clj-gdal.band-test
  (:require [clojure.test :refer :all]
            [clj-gdal.band :refer :all]
            [clj-gdal.core :as gdal]
            [clj-gdal.dataset :as data]
            [nio.core :as nio])
  (:import [org.gdal.gdalconst gdalconst]))

;; BEWARE: IF YOU CHANGE THE TEST FILE THERE IS A GOOD CHANCE THE
;; THE TESTS WILL FAIL. These tests don't just ensure the definition
;; of functions, they check the properties and values contained in
;; the file too!

(deftest test-geotiff-handling
  (let [_ (gdal/init) ; fixture?
        path "test/data/espa/landsat_8_sr_band_4_san_francisco.tif"
        tiff (gdal/open path)
        band (data/get-raster-band tiff 1)]
    (testing "getting properties"
      ;; Exactly how GeoTIFF files are handled is explained in detail here:
      ;; http://www.gdal.org/frmt_gtiff.html
      (testing "width"
        (is (= 1000 (get-x-size band))))
      (testing "height"
        (is (= 1000 (get-y-size band))))
      ;; Block size is not simply the pixel width + 1 pixel high...
      ;; but the GDAL GeoTIFF does aren't exactly clear on how this
      ;; is calculated. This test exists to make sure the function
      ;; has been implemented.
      (testing "x block size"
        (is (= 1000 (get-block-x-size band))))
      (testing "y block size"
        (is (=    4 (get-block-y-size band))))
      ;; The GDAL type corresponds to a Java type and some related
      ;; kind of NIO buffer function.
      (testing "GDAL data type"
        (is (= gdalconst/GDT_Int16 (get-data-type band))))
      (testing "Java data type"
        (is (= java.lang.Short (get-java-type band))))
      (testing "Java NIO buffer type"
        (is (= nio/short-buffer))))
    (testing "Raster sequence (seq-ing many blocks as vectors)"
      (testing "implicit call"
        ;; The test image is 1000x1000 pixels. The natural block
        ;; size is 1000 pixels wide and 4 pixel high, so there
        ;; should be exactly 250 blocks.
        (let [blocks (raster-seq band)]
          (is (= 250 (count blocks)))))
      (testing "band with block size call"
        (let [blocks (raster-seq band :xstep 100 :ystep 100)]
          (is (= 100 (count blocks)))))
      (testing "explicit call"
        ;; The same 5000x5000 image is parsed in blocks of 1000x1000
        ;; so there should be five blocks along the x-axis and ten
        ;; blocks along the y-axis, yielding exactly 50 blocks.
        (let [blocks (raster-seq band :xstep 100 :ystep 50)]
          (is (= 200 (count blocks))))))
    (testing "Raster vector (getting one block as a vector)"
      (testing "explicit call for small area"
        (let [block (raster-vec band :xstart 10 :xstop 20 :ystart 10 :ystop 20)]
          (is (= 100 (count block))))))))
