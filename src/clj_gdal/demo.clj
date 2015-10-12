(ns clj-gdal.demo
  (:require [clj-gdal.core :as gdal]
            [clj-gdal.dataset :as dataset]
            [clj-gdal.band :as band]
            [nio.core :as nio]))

(gdal/init)
(def path "test/data/LC80470272013111-SC20150612185259/LC80470272013111LGN01_sr_band4.tif")
(def tiff (gdal/open path))
(def b1 (dataset/get-raster-band tiff 1))
(def bu (band/allocate-block-buffer b1))
(band/read-block-direct b1 0 0 bu)
(def a1 (nio/buffer-to-array bu))
;(def data (/get-raster-data band 4990 4990 10 10))

(map #(. data getShort %) (range 1 100 2))

(gdal/get-projection tiff)

(gdal/get-geo-transform tiff)

;; out of place

(defn get-raster-data
  "Retrieve data from a band in a byte buffer"
  [band x y x_size y_size]
  (let [gdal_type (. band GetRasterDataType)
        buffer    (. band ReadRaster_Direct x y x_size y_size gdal_type)]
    buffer))
