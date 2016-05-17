(def centos-lib-paths
  ["/usr/java/packages/lib/amd64"
   "/usr/lib64"
   "/lib64"
   "/lib"
   "/usr/lib"])

(def ubuntu-lib-paths
  ["/usr/java/packages/lib/amd64"
   "/usr/lib/x86_64-linux-gnu/jni"
   "/lib/x86_64-linux-gnu"
   "/usr/lib/x86_64-linux-gnu"
   "/usr/lib/jni"
   "/lib:/usr/lib"])

(def gdal-paths
  ["/usr/lib/java/gdal"])

(defn get-lib-path []
  (->> gdal-paths
       (into centos-lib-paths)
       (into ubuntu-lib-paths)
       (clojure.string/join ":")
       (str "-Djava.library.path=")))

(defproject element84/clj-gdal "0.3.4"
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
  :profiles {
    :dev {
      :jvm-opts [~(get-lib-path)]}})
