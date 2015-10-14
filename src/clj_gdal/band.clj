(ns clj-gdal.band
  (:require [nio.core :as nio])
  (:import [java.nio ByteBuffer]
           [org.gdal.gdalconst gdalconst]))


(defn checksum
  "Compute checksum for whole image"
  ([band] nil)
  ([band xoff yoff xsize ysize] nil))

(defn compute-band-stats
  "Compute mean and standard deviation values"
  ([band] nil)
  ([band samplestep] nil))

(defn compute-raster-min-max
  "Compute min/max values for a band"
  ([band] nil)
  ([band approx-ok] nil))

(defn compute-statistics
  "Compute image statistics"
  ([band approx-ok] nil)
  ([band approx-ok min max] nil)
  ([band approx-ok min max mean stddev] nil)
  ([band approx-ok min max mean stddev callback]))

(defn create-mask-band
  "Add a mask band to the current band"
  [band flags]
  nil)

(defn fill
  "Fill band with a constant value"
  ([band real-fill] nil)
  ([band real-file imag-fill] nil))

(defn flush-cache
  "Flush raster data cache"
  [band]
  nil)

(defn get-band
  "Fetch the band number"
  [band]
  (. band GetBand))

(defn get-block-size
  "Fetch the natural block size of this band"
  [band]
  nil)

(defn get-block-x-size
  "Fetch the natural block width of this band"
  [band]
  (. band GetBlockXSize))

(defn get-block-y-size
  "Fetch the natural block heigh of this band"
  [band]
  (. band GetBlockYSize))

(defn get-category-names
  "Fetch the list of category names for this raster"
  [band]
  nil)

(defn set-category-names
  "Set the category names for this band"
  [band names])

; shall we use a symbol instead of an int constant??
(defn get-color-interpretation
  "How should this band be interpreted as color?"
  [band]
  nil)

(defn set-color-interpretation
  "Set color interpretation of band"
  [band interpretation]
  nil)

(defn get-color-table
  "Fetch the color table associated with band"
  [band]
  nil)

(defn set-color-table
  "Set color table associated with band"
  [band color-table]
  nil)

(defn get-dataset
  "Get the dataset to which the band belongs"
  [band]
  nil)

(defn get-data-type
  "Return GDAL data type of the band"
  [band]
  (. band getDataType))

(def java_type->gdal_type
  {java.lang.Byte     gdalconst/GDT_Byte
   java.lang.Short    gdalconst/GDT_Int16
   java.lang.Integer  gdalconst/GDT_Int32
   java.lang.Float    gdalconst/GDT_Float32})

(defn get-gdal-type
  "Get the GDAL type needed to convert rasters to a Java type"
  [java-type] (java_type->gdal_type java-type))

(def gdal_type->java_type
  {gdalconst/GDT_Byte     java.lang.Byte
   gdalconst/GDT_Int16    java.lang.Short    
   gdalconst/GDT_Int32    java.lang.Integer  
   gdalconst/GDT_Float32  java.lang.Float})

(defn get-java-type
  "Get the best Java type for the given GDAL band"
  [band]
  (let [gdal-type (get-data-type band)]
    (gdal_type->java_type gdal-type)))

(def java_type->buffer_type
  {java.lang.Byte     nio/byte-buffer
   java.lang.Short    nio/short-buffer
   java.lang.Integer  nio/int-buffer
   java.lang.Float    nio/float-buffer})

(defn get-buffer-type
  "Get the nio buffer converter for the Java type"
  [java-type] (java_type->buffer_type java-type))

(defn get-data-type-size
  "Number of bytes per pixel"
  [band]
  (let [gdal_type (get-data-type band)
        byte_size (get-in gdal_type->java_type [gdal_type :size])]
    (/ byte_size 8)))

; unsigned integers are too big for the signed java types...
; but they require special handling to make fit.
; gdalconst/GDT_UInt16  nio/int-buffer ; wrong
; gdalconst/GDT_UInt32  nio/long-buffer ; wrong

; explain what this does -- comment repeats function name
(defn get-default-histogram
  "Fetch the default raster histogram"
  ([band min max] nil)
  ([band min max force] nil)
  ([band min max force callback] nil))

(defn set-default-histogram
  "Set default histogram"
  [band min max histogram]
  nil)

(defn get-default-rat
  "Fetch the default raster attribute table"
  [band]
  nil)

(defn set-default-rat
  "Set raster color table"
  [band table])

(defn get-histogram
  "Compute raster histogram"
  [band min max]
  nil)

(defn get-mask-band
  "Return the mask band associated with band"
  [band]
  nil)

(defn get-mask-flags
  "Return the status flags of the mask band associated with the band"
  [band]
  nil)

(defn get-maximum
  "Fetch maximum value for band"
  [band]
  nil)

(defn get-minimum
  "Fetch minimum value for band"
  [band]
  nil)

(defn get-no-data-value
  "Fetch the no data value for this band"
  [band]
  nil)

(defn set-no-data-value
  "Set the no data value for this band"
  [band no-data]
  nil)

(defn get-offset
  "Fetch the raster value offset"
  [band]
  nil)

(defn set-offset
  "Set scaling offset"
  [band offset]
  nil)

(defn get-overview
  "Fetch overview raster band"
  [band index]
  nil)

(defn get-overview-count
  "Number of overview layers available"
  [band]
  nil)

; get-raster-* and set-raster-* functions, are they redundant?

(defn get-scale
  "Fetch the raster value scale"
  [band]
  nil)

(defn set-scale
  "Set scaling ratio"
  [band scale]
  nil)

(defn get-statistics
  "Fetch image statistics"
  [band approx-ok force]
  nil)

(defn set-statistics
  "Set statistics on band"
  [band min max mean stddev]
  nil)

(defn get-unit-type
  "Fetch raster unit type"
  [band]
  nil)

(defn set-unit-type
  "Set unit type"
  [band unit-type]
  nil)

(defn get-byte-count
  "Count the number of bytes used by java-type"
  [java-type]
  (/ (eval `(. ~java-type SIZE)) 8))

(defn get-x-size
  "Fetch number of pixels along x axis"
  [band]
  (. band GetXSize))

(defn get-y-size
  "Fetch number of pixels along y axis"
  [band]
  (. band GetYSize))

(defn has-arbitrary-overviews
  "Check for arbitrary overviews"
  [band]
  nil)

(defn read-block-direct
  "Read a block of image data efficiently"
  [band xoff yoff buffer]
  (. band ReadBlock_Direct xoff yoff buffer)
  buffer)

(defn read-raster-direct
  "Read a region of image data"
  [band xoff yoff xsize ysize xbsize ybsize btype buffer]
  (. band ReadRaster_Direct xoff yoff xsize ysize xbsize ybsize btype buffer)
  buffer)

(defn read-raster
  "Read a region of image data"
  [band xoff yoff xsize ysize]
  nil)

(defn allocate-block-buffer
  "Create a buffer big enough to hold data in raster"
  ([band]
   (let [xblock (get-block-x-size band)
         yblock(get-block-y-size band)
         java-type  (get-java-type band)
         byte-count (get-byte-count java-type)]
     (allocate-block-buffer band xblock yblock byte-count)))
  ([band xblock yblock]
   (let [byte-count (-> band get-java-type get-byte-count)]
     (allocate-block-buffer band xblock yblock byte-count)))
  ([band xblock yblock byte-count] ;; xblock/yblock
   (let [buffer (ByteBuffer/allocateDirect (* xblock yblock byte-count))]
     (. buffer order (java.nio.ByteOrder/nativeOrder))
     buffer)))

(defn raster-seq
  "Read entire raster in blocks"
  ([band & {:keys [xstart ystart xstop ystop xstep ystep java-type]
            :or   {xstart    0
                   ystart    0
                   xstop     (get-x-size band)
                   ystop     (get-y-size band)
                   xstep     (get-block-x-size band)
                   ystep     (get-block-y-size band)
                   java-type (get-java-type band)
            :as args}}]
   (let [gdal-type   (get-gdal-type java-type)
         buffer-type (get-buffer-type java-type)
         byte-count  (get-byte-count java-type)
         buffer      (allocate-block-buffer band xstep ystep byte-count)
         reader      #(read-raster-direct band %1 %2 xstep ystep xstep ystep gdal-type buffer)]
     (for [x (range xstart xstop xstep)
           y (range xstart ystop ystep)]
       (-> (reader x y) buffer-type nio/buffer-to-array vec)))))

(defn clear-buffer
  "Set buffer values to zero" ; this could be a mistake... zero isn't always fill!
  [buffer]
  (let [size (. buffer capacity)]
    (map #(. buffer put % 0) (range size))))

(defn raster-vec
  "Read partial raster into single block"
  [band & {:keys [xstart xstop ystart ystop xblock yblock java-type byte-count buffer]
            :or   {xstart 0
                   ystart 0
                   xstop (get-x-size band)
                   ystop (get-y-size band)
                   xblock (- xstop xstart)
                   yblock (- ystop ystart)
                   java-type (get-java-type band)
                   byte-count (get-byte-count java-type)
                   buffer (allocate-block-buffer band xblock yblock byte-count)
            :as args}}]
   (let [gdal-type (get-gdal-type java-type)
         buffer-type (get-buffer-type java-type)]
     (-> (read-raster-direct band xstart ystart xblock yblock xblock yblock gdal-type buffer)
           buffer-type
           nio/buffer-to-array
           vec)))

(defn write-block-direct
  "Write a block of image data efficiently"
  [band xoff yoff data]
  nil)

(defn write-raster-direct
  "Write a region of image data for this band"
  [band xoff yoff xsize ysize buffer-gdal-type buffer]
  nil)

(defn write-raster
  "Write a region of image data for this band"
  [band xoff yoff xsize ysize data]
  nil)
