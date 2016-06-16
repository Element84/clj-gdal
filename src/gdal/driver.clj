(ns gdal.driver
  (:require [gdal.core])
  (:import [org.gdal.gdal Driver]))

(defn register
  "Register a driver for use"
  [driver]
  nil)

(defn deregister
  "Deregister the driver"
  [driver]
  nil)

(defn get-help-topic
  "URL to the help describing this driver"
  [driver]
  nil)

(defn get-long-name
  "Full name of a driver"
  [driver]
  nil)

(defn get-short-name
  "Succint name of a driver"
  [driver]
  nil)

;; dataset manipulation

(defn create
  "Create a new dataset"
  [driver name xsize ysize]
  (.Create driver name xsize ysize))

(defn create-copy
  "Create a copy of a dataset"
  [name dest]
  nil)

(defn delete
  "Delete named dataset"
  [name]
  nil)

(defn rename
  "Change the name of a dataset"
  [new-name old-name]
  nil)

(defn copy-files
  ""
  [new-name old-name])
