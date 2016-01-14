(ns gdal.dataset
  (:import [org.gdal.gdal Dataset]
           [org.gdal.gdalconst gdalconst]
           [java.nio ByteBuffer]
           [java.text ParsePosition]
           ;; XXX note that Apache SIS doesn't yet support Albers Equal Area
           ;;  * https://issues.apache.org/jira/browse/SIS-232
           ;; when this is supported, we can re-enable Apache SIS
           ;;[org.apache.sis.io.wkt WKTFormat]
           [org.geotoolkit.io.wkt WKTFormat]
           [org.opengis.referencing.crs CoordinateReferenceSystem])
  (:refer-clojure :exclude [read]))

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
  ;; XXX This is for Apache SIS
  ;; (let [wkt (new WKTFormat locale timezone)
  ;;       parse-index (new ParsePosition start-index)]
  ;;   (.parse wkt (get-projection-str dataset) parse-index))
  (let [wkt (new WKTFormat)
        parse-index 0]
    (.parse wkt (get-projection-str dataset) parse-index CoordinateReferenceSystem))
  )

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

;;; Aliases

(def get-proj #'get-projection)
(def count-bands #'get-band-count)
(def count-gcps #'get-gcp-count)
(def read-raster #'read)
(def get-raster-x-size #'get-x-size)
(def get-raster-y-size #'get-y-size)
