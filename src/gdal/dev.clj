(ns gdal.dev
  (:require [clojure.pprint :as pp]
            [clojure.reflect :as reflect]
            [clojure.tools.logging :as log]
            [clojure.tools.namespace.repl :as repl]
            [gdal.core :as gdal]
            [gdal.band :as band]
            [gdal.dataset :as dataset])
  (:import [java.nio ByteBuffer]
           [org.gdal.gdalconst gdalconst]))

(def reload #'repl/refresh)

(defn show-members [java-object]
  (pp/print-table (:members (reflect/reflect java-object))))
