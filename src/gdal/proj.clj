;;;; This namespace projides wrappers for getting projection information
;;;; returned by GDAL. Since GDAL only gives this information as text (WKT
;;;; format), this namespace provides wrappers for object-access to
;;;; the projection data (using the Apache Spatial Information System
;;;; library).
;;;;
;;;; Note that this is for standard formats of WKT as found in Landsat 8 image
;;;; metadata. It is not meant to parse custom WKT with unsupported or
;;;; non-standard fields
;;;;
(ns gdal.proj
  (:require [gdal.core]
            [gdal.util :refer :all])
  (:import [org.gdal.gdal Dataset]
           [org.gdal.gdalconst gdalconst]
           [java.nio ByteBuffer]
           [java.text ParsePosition]
           [org.apache.sis.io.wkt WKTFormat]))

;;; Top-level

(defn get-name [proj]
  (-> proj
      (.getName)
      (.toString)))

(defn get-id-objs [proj]
  (.getIdentifiers proj))

(defn- -get-id [id-obj]
  {:code-space (obj->str (.getCodeSpace id-obj) "")
   :code (obj->str (.getCode id-obj) "")
   :authority (obj->str (.getAuthority id-obj) "")
   :version (obj->str (.getVersion id-obj) "")})

(defn get-ids [proj]
  (into [] (map -get-id (get-id-objs proj))))

(defn get-id [proj]
  (first (get-ids proj)))

;;; Datum

(defn get-datum [proj]
  (.getDatum proj))

(defn get-datum-name [proj]
  (-> (get-datum proj)
      (.getName)
      (.toString)))

(defn get-ellipsoid [proj]
  (-> (get-datum proj)
      (.getEllipsoid)))

(defn get-ellipsoid-name [proj]
  (-> (get-ellipsoid proj)
      (.getName)
      (.toString)))

(defn get-eccentricity [proj]
  (-> (get-ellipsoid proj)
      (.getEccentricity)))

(defn get-distance [proj lat1 long1 lat2 long2]
  (-> (get-ellipsoid proj)
      (.orthodromicDistance lat1 long1 lat2 long2)))

(defn get-prime-meridian [proj]
  (-> (get-datum proj)
      (.getPrimeMeridian)))

(defn get-bw-params [proj]
  (-> (get-datum proj)
      (.getBursaWolfParameters)))

;;; Coordinate system and axes

(defn get-coord-system [proj]
  (.getCoordinateSystem proj))

(defn get-coord-name [proj]
  (-> (get-coord-system proj)
      (.getName)
      (.toString)))

(defn get-coord-dim [proj]
  (-> (get-coord-system proj)
      (.getDimension)))

(defn get-axis [proj index]
  (-> (get-coord-system proj)
      (.getAxis index)))

(defn get-axis-name [proj index]
  (-> (get-axis proj index)
      (.getName)
      (.toString)))

(defn get-axis-unit [proj index]
  (-> (get-axis proj index)
      (.getUnit)
      (.toString)))

(defn get-axes [proj]
  (map #(get-axis proj %) (range (get-coord-dim proj))))

;;; Aliases

(def get-coordinate-system #'get-coord-system)
(def get-coord-system-name #'get-coord-name)
(def get-coord-system-dimension #'get-coord-dim)
(def get-bursa-wolf-parameters #'get-bw-params)
(def get-ellipsoid-eccentricity #'get-ellipsoid)
(def get-orthodromic-distance #'get-distance)
