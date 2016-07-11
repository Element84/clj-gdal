(ns gdal.dev
  (:require [clojure.pprint :as pp]
            [clojure.reflect :as reflect]
            [clojure.tools.logging :as log]
            [clojure.tools.namespace.repl :as repl]
            [gdal.band :as band]
            [gdal.dataset :as dataset]
            [gdal.driver :as driver]
            [gdal.proj :as proj]
            [gdal.util :as util]
            [gdal.core :as gdal])
  (:import [java.nio ByteBuffer]
           [org.gdal.gdalconst gdalconst]))

(def reload #'repl/refresh)

(defn show-members [java-object]
  (pp/print-table (:members (reflect/reflect java-object))))

(defn describe [it]
  {:type (class it)
   :driver (-> it dataset/get-driver driver/get-short-name)
   :band-count (dataset/count-bands it)
   :projection (dataset/get-projection-str it)
   :affine (dataset/get-geo-transform it)
   :xs (dataset/get-x-size it)
   :ys (dataset/get-y-size it)})

(defn copy [dataset driver-name file-name]
  (let [driver (gdal/get-driver-by-name driver-name)
        extension (-> driver driver/get-metadata :dmd-extension)]
    (driver/create-copy driver (str file-name "." extension) dataset)))

(comment
  "copy and compare"
  (let [src (gdal/open "test/data/sample.tif")
        dup (copy src "netCDF" "sample-meow")]
    (merge-with = (describe src) (describe dup))
    (dataset/get-file-list dup)
    (dataset/flush-cache dup)))

(comment
  "playing with drivers"
  (def drivers (gdal/get-drivers))
  (def names (sort (map driver/get-short-name drivers)))
  (def mem    (gdal/get-driver-by-name "MEM"))
  (def tiff   (gdal/get-driver-by-name "GTiff"))
  (def netcdf (gdal/get-driver-by-name "netCDF"))
  (def hdf4   (gdal/get-driver-by-name "hdf4"))
  (def hdf5   (gdal/get-driver-by-name "hdf5")))

;; in memory raster does not support subdataset
;;

(comment
  (driver/create? netcdf)
  (driver/get-metadata netcdf)
  (driver/create-datatypes tiff)
  (driver/copy? tiff)
  (driver/virtual-io? tiff)
  (driver/mime-type netcdf))
