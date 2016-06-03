(ns gdal.core
  (:require [clojure.string :refer [split join]]))

(defn add-usr-path
  [& paths]
  (let [field (.getDeclaredField ClassLoader "usr_paths")]
    (try (.setAccessible field true)
         (let [original (vec (.get field nil))
               updated  (distinct (concat original paths))]
           (.set field nil (into-array updated)))
         (finally
           (.setAccessible field false)))))

(defn get-usr-path
  [& paths]
  (let [field (.getDeclaredField ClassLoader "usr_paths")]
    (try (.setAccessible field true)
         (vec (.get field nil))
         (finally
           (.setAccessible field false)))))


(def gdal-paths
  [;; CentOS yum install location
   "/usr/lib/java/gdal"
   ;; Ubuntu install location
   "/usr/lib/jni"])

;; The gdal paths must be added before importing the classes!
(apply add-usr-path gdal-paths)
(import org.gdal.gdal.gdal)

(defn init
  "Load all available GDAL drivers"
  []
  (gdal/AllRegister))

(defmulti open class)

(defmethod open java.lang.String [path]
  (gdal/Open path))

(defmethod open java.io.File [file]
  (gdal/Open (.getAbsolutePath file)))

(defn close
  "Frees native resources associated to dataset, closes file."
  [dataset]
  (.delete dataset))

(defmacro with-dataset
  [[binding path] & body]
  `(let [dataset# (open ~path)
         ~binding dataset#]
      (try
        (do ~@body)
        (finally
          (close dataset#)))))

(defn version-info
  "Get information about the current version of GDAL"
  [& request]
  (gdal/VersionInfo))

;;; Driver related functions

(defn get-driver
  "Get driver using a numeric index"
  [idx]
  (gdal/GetDriver idx))

(defn get-driver-by-name
  "Get driver using a string name"
  [name]
  (gdal/GetDriverByName name))

(defn get-driver-count
  "Get number of available drivers"
  []
  (gdal/GetDriverCount))

(defn get-drivers
  "Get all available drivers"
  []
  (let [ix (range 0 (get-driver-count))]
    (map get-driver ix)))
