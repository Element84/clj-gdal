(ns gdal.util)

(defn obj->str
  ([obj]
    (obj->str obj ""))
  ([obj default]
    (.toString (or obj default))))

(defn print-obj [obj]
  (println (obj->str obj)))
