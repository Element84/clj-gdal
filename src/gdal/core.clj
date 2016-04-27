(ns gdal.core
  (:import [org.gdal.gdal gdal]))

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
