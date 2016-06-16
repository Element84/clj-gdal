(ns gdal.util
  (:import [org.gdal.gdalconst gdalconst]
           [java.nio ByteBuffer ShortBuffer IntBuffer FloatBuffer DoubleBuffer HeapByteBuffer DirectByteBuffer]))

(defn obj->str
  ([obj]
    (obj->str obj ""))
  ([obj default]
    (.toString (or obj default))))

(defn print-obj [obj]
  (println (obj->str obj)))

(def type-map {;; Primitive Types
               Byte/TYPE         gdalconst/GDT_Byte
               Short/TYPE        gdalconst/GDT_Int16
               Integer/TYPE      gdalconst/GDT_Int32
               Float/TYPE        gdalconst/GDT_Float32
               Double/TYPE       gdalconst/GDT_Float64
               ;; Buffer Types
               ByteBuffer        gdalconst/GDT_Byte
               ShortBuffer       gdalconst/GDT_Int16
               IntBuffer         gdalconst/GDT_Int32
               FloatBuffer       gdalconst/GDT_Float32
               DoubleBuffer      gdalconst/GDT_Float64
               ;; java.nio Buffers
               HeapByteBuffer    gdalconst/GDT_Byte
               DirectByteBuffer  gdalconst/GDT_Byte})

(defn array->gdal-type
  ""
  [array]
  (-> array
      (.getClass)
      (.getComponentType)
      type-map))

(defn not-yet
  ""
  []
  (throw (UnsupportedOperationException. "this function is not yet supported")))
