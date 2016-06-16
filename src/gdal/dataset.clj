(ns gdal.dataset
  (:require [gdal.core])
  (:import [org.gdal.gdal Dataset]
           [org.gdal.gdalconst gdalconst]
           [java.nio ByteBuffer]
           [java.text ParsePosition]
           [org.apache.sis.io.wkt WKTFormat])
  (:refer-clojure :exclude [read]))

(defn add-band
  ""
  ([dataset]
   (.AddBand dataset))
  ([dataset index]
   (.AddBand dataset index)))

(defn get-geo-transform
  "Get the affine transformation coefficients of the dataset.

  These are used for transforming between pixel/line (P,L) raster space,
  and projection coordinates (Xp,Yp) space.

    Xp = geoTransformArray[0] + P*geoTransformArray[1] + L*geoTransformArray[2];
    Yp = geoTransformArray[3] + P*geoTransformArray[4] + L*geoTransformArray[5];

  Ultimately, you shouldn't need to use these to retrieve raster data using
  clj-gdal."
  [dataset]
  (vec (.GetGeoTransform dataset)))

(defn get-projection-str
  "Get the projection definition WKT string for dataset"
  [dataset]
  (.GetProjection dataset))

(defn get-projection
  "Get a parsed WKT object for the dataset's projection definition"
  [dataset & {:keys [locale timezone start-index]
              :or {locale nil timezone nil start-index 0}}]
  (let [wkt (new WKTFormat locale timezone)
        parse-index (new ParsePosition start-index)]
    (.parse wkt (get-projection-str dataset) parse-index)))

(defn get-band-count
  "Get the number of raster bands in the dataset"
  [dataset]
  (.GetRasterCount dataset))

(defn get-gcp-count
  "Get number of GCPs"
  [dataset]
  (.GetGCPCount dataset))

(defn get-gcp-projection
  "Get output projection for GCPs"
  [dataset]
  (.GetGCPProjection dataset))

(defn get-gcps
  "Fetch GCPs"
  [dataset]
    (.GetGCPs dataset))

(defn get-x-size
  "Raster width in pixels"
  [dataset]
  (.GetRasterXSize dataset))

(defn get-y-size
  "Raster height in pixels"
  [dataset]
  (.GetRasterYSize dataset))

(defn get-size [data]
  [(get-x-size data) (get-y-size data)])

(defn get-band
  "Get the nth raster band, starts with index of 1 in keeping with GDAL conventions"
  [dataset n]
  (.GetRasterBand dataset n))

(defn read
  "Read a region of image data from multiple bands"
  [dataset xoff yoff xsize ysize & bands]
  nil)

(defn set-geo-transform
  ""
  [dataset affine]
  (.SetGeoTransform dataset (into-array Double/TYPE affine)))

(defn set-projection-str
  ""
  [dataset wkt]
  (.SetProjection dataset wkt))

;;; Aliases

(def get-proj #'get-projection)
(def count-bands #'get-band-count)
(def count-gcps #'get-gcp-count)
(def read-raster #'read)
(def get-raster-x-size #'get-x-size)
(def get-raster-y-size #'get-y-size)
