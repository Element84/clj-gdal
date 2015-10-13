(ns clj-gdal.demo
  (:require [clj-gdal.core :as gdal]
            [clj-gdal.dataset :as dataset]
            [clj-gdal.band :as band]
            [nio.core :as nio]))

(gdal/init)
(def path   "test/data/LC80470272013111-SC20150612185259/LC80470272013111LGN01_sr_band4.tif")
(def tiff   (gdal/open path))
(def band-1 (dataset/get-raster-band tiff 1))

;; these use the best Java type for the underlying raster's GDAL type
(def buffer-a (band/allocate-block-buffer band-1 10 10))
(def block-a  (band/raster-vec band-1 4900 4900 10 10 buffer-a))
(def blocks-a (band/raster-seq band-1))

;; these use whatever java type 
(def buffer-b (band/allocate-block-buffer band-1 10 10 java.lang.Integer))
(def block-b  (band/raster-vec band-1 4900 4900 10 10 buffer-b java.lang.Integer))
(def blocks-b (band/raster-seq band-1 java.lang.Integer))

