(ns gdal.dataset
  (:require [gdal.core]
            [gdal.util])
  (:import [org.gdal.gdal Dataset]
           [org.gdal.gdalconst gdalconst]
           [java.nio ByteBuffer]
           [java.text ParsePosition]
           [org.apache.sis.io.wkt WKTFormat]
           [gdal.util])
  (:refer-clojure :exclude [read]))

(defn add-band
  "Add a band to a dataset"
  ([dataset]
   (.AddBand dataset))
  ([dataset index & options]
   (.AddBand dataset index)))

(defn build-overviews
  "Build raster overview(s)."
  [dataset]
  (gdal.util/not-yet))

(defn commit-transaction
  ""
  [dataset]
  (gdal.util/not-yet))

(defn create-layer
  ""
  [dataset]
  (gdal.util/not-yet))

(defn create-mask-band
  ""
  [dataset]
  (gdal.util/not-yet))

(defn delete
  ""
  [dataset]
  (.delete dataset))

(defn create-mask-band
  ""
  [dataset]
  (gdal.util/not-yet))

(defn delete-layer
  ""
  [dataset]
  (gdal.util/not-yet))

(defn execute-sql
  ""
  [dataset]
  (gdal.util/not-yet))

(defn flush-cache
  "Write dataset to file"
  [dataset]
  (.FlushCache dataset))

(defn get-driver
  ""
  [dataset]
  (.GetDriver dataset))

(defn get-file-list
  ""
  [dataset]
  (.GetFileList dataset))

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

(defn get-raster-band
  "Get the nth raster band, starts with index of 1 in keeping with GDAL conventions"
  [dataset band-id]
  (.GetRasterBand dataset band-id))

(defn get-raster-count
  "Get the number of raster bands in the dataset"
  [dataset]
  (.GetRasterCount dataset))

(defn get-raster-x-size
  "Raster width in pixels"
  [dataset]
  (.GetRasterXSize dataset))

(defn get-raster-y-size
  "Raster height in pixels"
  [dataset]
  (.GetRasterYSize dataset))

(defn get-size
  ""
  [dataset]
  [(get-raster-x-size dataset) (get-raster-y-size dataset)])

(defn get-style-table
  ""
  [dataset]
  (.GetStyleTable dataset))

(defn read-raster
  "Read a region of image data from multiple bands"
  [dataset xoff yoff xsize ysize & opts]
  (gdal.util/not-yet))

(defn read-raster-direct
  "Read a region of image data from multiple bands"
  [dataset xoff yoff xsize ysize & opts]
  (gdal.util/not-yet))

(defn release-result-set
  ""
  [dataset layer]
  (gdal.util/not-yet))

(defn rollback-transaction
  ""
  [dataset]
  (gdal.util/not-yet))

(defn set-gcps
  ""
  [dataset gcp-seq gcp-projection]
  (gdal.util/not-yet))

(defn set-geo-transform
  ""
  [dataset affine]
  (.SetGeoTransform dataset (into-array Double/TYPE affine)))

(defn set-projection-str
  ""
  [dataset wkt]
  (.SetProjection dataset wkt))

(defn set-style-table
  ""
  [dataset style-table]
  (.SetStyleTable dataset style-table))

(defn start-transaction
  ""
  [dataset]
  (gdal.util/not-yet))

(defn test-capability
  ""
  [dataset capability]
  (.TestCapability dataset capability))

(defn write-raster
  ""
  [dataset xoff yoff xsize ysize & opts]
  (gdal.util/not-yet))

(defn write-raster-direct
  ""
  [dataset xoff yoff xsize ysize & opts]
  (gdal.util/not-yet))


;;; Aliases

(def get-proj #'get-projection)
(def count-bands #'get-raster-count)
(def count-gcps #'get-gcp-count)
(def read #'read-raster)

(def get-band #'get-raster-band)
(def get-band-count #'get-raster-count)
(def get-x-size #'get-raster-x-size)
(def get-y-size #'get-raster-y-size)
