(defproject oubiwann/clj-gdal "0.2.0"
  :description "Clojure-idiomatic GDAL wrapper"
  :url "http://github.com/oubiwann/clj-gdal"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.gdal/gdal "1.11.2"]
                 [nio "1.0.3"]]
  :repl-options {:init-ns gdal.core})
