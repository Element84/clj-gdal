(defproject oubiwann/clj-gdal "0.2.0"
  :description "Clojure-idiomatic GDAL wrapper"
  :url "http://github.com/oubiwann/clj-gdal"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories [["geotoolkit" "http://maven.geotoolkit.org/"]
                 ["apache-snapshots" "http://repository.apache.org/snapshots"]
                 ["unidata" "http://artifacts.unidata.ucar.edu/content/repositories/unidata-releases"]
                 []]
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [org.gdal/gdal "1.11.2"]
                 ;; XXX note that Apache SIS doesn't yet support Albers Equal Area
                 ;;  * https://issues.apache.org/jira/browse/SIS-232
                 ;; when this is supported, we can re-enable Apache SIS
                 ;;[org.apache.sis.core/sis-referencing "0.6"]
                 [org.geotoolkit/geotk-referencing "3.21.1"]
                 [org.geotoolkit/geotk-epsg  "3.21.1"]
                 [org.apache.derby/derby "10.8.2.2"]
                 [nio "1.0.3"]]
  :repl-options {:init-ns gdal.dev})
