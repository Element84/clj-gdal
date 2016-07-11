(ns gdal.libpath)

(def gdal-paths
  [;; CentOS yum install location
   "/usr/lib/java/gdal"
   ;; Ubuntu install location
   "/usr/lib/jni"])

(defn add-usr-path
  ""
  [& paths]
  (let [field (.getDeclaredField ClassLoader "usr_paths")]
    (try (.setAccessible field true)
         (let [original (vec (.get field nil))
               updated  (distinct (concat original paths))]
           (.set field nil (into-array updated)))
         (finally
           (.setAccessible field false)))))

(defn get-usr-path
  ""
  [& paths]
  (let [field (.getDeclaredField ClassLoader "usr_paths")]
    (try (.setAccessible field true)
         (vec (.get field nil))
         (finally
           (.setAccessible field false)))))

(defn amend
  ""
  []
  (apply add-usr-path gdal-paths))
