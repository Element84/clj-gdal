(ns clj-gdal.demo
  (:require [clj-gdal.core :as gdal]
            [clj-gdal.dataset :as dataset]
            [clj-gdal.band :as band]
            [nio.core :as nio]))

(gdal/init)
(def path   "test/data/scene/LT50460262009013PAC01_sr_band4.tif")
(def tiff   (gdal/open path))
(def band-1 (dataset/get-raster-band tiff 1))

;; these use the best Java type for the underlying raster's GDAL type
(def block  (band/raster-vec band-1 :xstart 0 :xstop 10 :ystart 0 :ystop 10))
(def blocks (band/raster-seq band-1 :xstep 500 :ystep 500))


