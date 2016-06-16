(ns gdal.core
  (:require [clojure.string :refer [split join]]
            [gdal.libpath]))

;; This reduces the amount of configuration required
;; by developers by addin directories to the load path
;; that contain the GDAL JNI libraries. Without this,
;; one would likely need to set LD_LIBRARY_PATH when
;; invoking something that uses clj-gdal (yikes).
(try
  (gdal.libpath/amend)
  (catch RuntimeException e
    (binding [*out* *err*]
      (println (str "Could not update paths to native libraries. "
                    "You may need to set LD_LIBRARY_PATH to the "
                    "directory containing libgdaljni.so"))))
  (finally
      (import org.gdal.gdal.gdal)))

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
