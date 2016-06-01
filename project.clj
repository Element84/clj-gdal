(defproject clj-gdal "0.3.5-SNAPSHOT"
  :description "Clojure-idiomatic GDAL wrapper"
  :url "http://github.com/Element84/clj-gdal"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories [["geotoolkit" "http://maven.geotoolkit.org/"]]
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [org.gdal/gdal "1.11.2"]
                 [org.apache.sis.core/sis-referencing "0.6"]
                 [nio "1.0.3"]]
  :repl-options {:init-ns gdal.dev}
  :profiles {:dev {}})
