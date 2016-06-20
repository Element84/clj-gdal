(ns gdal.driver
  (:require [gdal.core])
  (:import [org.gdal.gdal Driver]))

(defn copy-files
  "Not explained in GDAL Java bindings"
  [driver new-name old-name]
  ;; XXX understand and explain intended use.
  (.CopyFiles driver new-name old-name))

(defn create
  "Create a new dataset"
  ([driver name xsize ysize]
   (.Create driver name xsize ysize))
  ([driver name xsize ysize bands]
   (.Create driver name xsize ysize bands))
  ([driver name xsize ysize bands etype]
   (.Create driver name xsize ysize bands etype)))

(defn create-copy
  "Create a copy of a dataset"
  [driver name src-dataset]
  (.CreateCopy driver name src-dataset))

(defn deregister
  "Deregister the driver"
  [driver]
  (.Deregister driver))

(defn get-help-topic
  "URL to the help describing this driver"
  [driver]
  (.getHelpTopic driver))

(defn get-long-name
  "Full name of a driver"
  [driver]
  (.getLongName driver))

(defn get-short-name
  "Succint name of a driver"
  [driver]
  (.getShortName driver))

(defn ->keyword-kebab-case [text]
  (-> text
      (clojure.string/lower-case)
      (clojure.string/replace #"_" "-")
      (keyword)))

(defn get-metadata
  [driver]
  (into {}
        (for [[k v] (.GetMetadata_Dict driver)]
          [(->keyword-kebab-case k) v])))

(defn delete
  "Delete named dataset"
  [driver dataset-name]
  (.Delete driver dataset-name))

(defn register
  "Register a driver for use"
  [driver]
  (.Register driver))

(defn rename
  "Change the name of a dataset"
  [driver new-name old-name]
  (.Rename driver new-name old-name))

;; metadata related functions

(defn copy?
  [driver]
  (-> driver get-metadata :dcap-createcopy (= "YES")))

(defn create?
  [driver]
  (-> driver get-metadata :dcap-create (= "YES")))

(defn create-datatypes
  [driver]
  (-> driver
      get-metadata
      :dmd-creationdatatypes
      (clojure.string/split #" ")))

(defn file-ext
  [driver]
  (-> driver get-metadata :dmd-extension))

(defn mime-type
  [driver]
  (-> driver get-metadata :dmd-mimetype))

(defn virtual-io?
  [driver]
  (-> driver get-metadata :dcap-virtualio (= "YES")))
