(ns gdal.band
  (:require [gdal.core]
            [gdal.util]
            [nio.core :as nio])
  (:import [java.nio ByteBuffer]
           [org.gdal.gdalconst gdalconst]))

(defn checksum
  "Compute checksum for whole image"
  ([band] (.Checksum band))
  ([band x y xs ys] (.Checksum band x y xs ys)))

(defn compute-band-stats
  "Compute mean and standard deviation values"
  ([band] (gdal.util/not-yet))
  ([band samplestep] (gdal.util/not-yet)))

(defn compute-raster-min-max
  "Compute min/max values for a band"
  ([band] (gdal.util/not-yet))
  ([band approx-ok] (gdal.util/not-yet)))

(defn compute-statistics
  "Compute image statistics"
  ([band approx-ok & opts] (gdal.util/not-yet)))

(defn create-mask-band
  "Add a mask band to the current band"
  [band flags]
  (gdal.util/not-yet))

(defn fill
  "Fill band with a constant value"
  ([band real-fill & opts] (gdal.util/not-yet)))

(defn flush-cache
  "Flush raster data cache"
  [band]
  (.FlushCache band))

(defn get-band
  "Fetch the band number"
  [band]
  (.GetBand band))

(defn get-block-x-size
  "Fetch the natural block width of this band"
  [band]
  (.GetBlockXSize band))

(defn get-block-y-size
  "Fetch the natural block heigh of this band"
  [band]
  (.GetBlockYSize band))

(defn get-block-size
  "Fetch the natural block size of this band"
  [band]
  ;; this side-steps working with the actual GDAL function
  ;; because creating and reading array buffer arguments is
  ;; not cool.
  [(get-block-x-size band) (get-block-y-size band)])

(defn get-category-names
  "Fetch the list of category names for this raster"
  [band]
  (.GetCategoryNames band))

; shall we use a symbol instead of an int constant??
(defn get-color-interpretation
  "How should this band be interpreted as color?"
  [band]
  ; I wonder if a symbol ought to be returned.
  (.GetColorInterpretation band))

(defn get-color-table
  "Fetch the color table associated with band"
  [band]
  (.GetColorTable band))

(defn get-dataset
  "Get the dataset to which the band belongs"
  [band]
  (.GetDataset band))

(defn get-data-type
  "Return GDAL data type of the band"
  [band]
  (.getDataType band))

(def java-type->gdal-type
  {java.lang.Byte     gdalconst/GDT_Byte
   java.lang.Short    gdalconst/GDT_Int16
   java.lang.Integer  gdalconst/GDT_Int32
   java.lang.Float    gdalconst/GDT_Float32})

(defn get-gdal-type
  "Get the GDAL type needed to convert rasters to a Java type"
  [java-type] (java-type->gdal-type java-type))

(def gdal-type->java-type
  {gdalconst/GDT_Byte     java.lang.Byte
   gdalconst/GDT_Int16    java.lang.Short
   gdalconst/GDT_Int32    java.lang.Integer
   gdalconst/GDT_Float32  java.lang.Float})

(defn get-java-type
  "Get the best Java type for the given GDAL band"
  [band]
  (let [gdal-type (get-data-type band)]
    (gdal-type->java-type gdal-type)))

(def java-type->buffer-type
  {java.lang.Byte     nio/byte-buffer
   java.lang.Short    nio/short-buffer
   java.lang.Integer  nio/int-buffer
   java.lang.Float    nio/float-buffer})

(defn get-buffer-type
  "Get the nio buffer converter for the Java type"
  [java-type] (java-type->buffer-type java-type))

(defn get-data-type-size
  "Number of bytes per pixel"
  [band]
  (let [gdal-type (get-data-type band)
        byte-size (get-in gdal-type->java-type [gdal-type :size])]
    (/ byte-size 8)))

; unsigned integers are too big for the signed java types...
; but they require special handling to make fit.
; gdalconst/GDT_UInt16  nio/int-buffer ; wrong
; gdalconst/GDT_UInt32  nio/long-buffer ; wrong

; explain what this does -- comment repeats function name
(defn get-default-histogram
  "Fetch the default raster histogram"
  ;; XXX handle overloaded functions with opts
  ([band min max & opts] (gdal.util/not-yet)))

(defn get-default-rat
  "Fetch the default raster attribute table"
  [band]
  (.GetDefaultRAT band))

(defn get-histogram
  "Compute raster histogram"
  ;; XXX handle overloaded functions with opts
  [band & opts]
  (.GetHistogram band))

(defn get-mask-band
  "Return the mask band associated with band"
  [band]
  (.GetMaskBand band))

(defn get-mask-flags
  "Return the status flags of the mask band associated with the band"
  [band]
  (.GetMaskFlags band))

(defn get-maximum
  "Fetch maximum value for band"
  [band]
  (let [result (make-array java.lang.Double 1)
        safely #(cond % (short %))]
    (.GetMaximum band result)
    (-> result first safely)))

(defn get-minimum
  "Fetch minimum value for band"
  [band]
  (let [result (make-array java.lang.Double 1)
        safely #(cond % (short %))]
    (.GetMinimum band result)
    (-> result first safely)))

(defn get-no-data-value
  "Fetch the no data value for this band"
  [band]
  (let [result (make-array java.lang.Double 1)
        safely #(cond % (short %))]
    (.GetNoDataValue band result)
    (-> result first safely)))

(defn get-offset
  "Fetch the raster value offset"
  [band i]
  (let [result (make-array java.lang.Double 1)
        safely #(cond % (short %))]
    (.GetOffset band result)
    (-> result first safely)))

(defn get-overview
  "Fetch overview raster band"
  [band index]
  (.GetOverview index))

(defn get-overview-count
  "Number of overview layers available"
  [band]
  (.GetOverviewCount band))

(defn get-raster-category-names
  ""
  [band]
  (.GetRasterCategoryNames band))

(defn get-raster-color-interpretation
  ""
  [band]
  (.GetRasterColorInterpretation band))

(defn get-raster-color-table
  ""
  [band]
  (.GetRasterColorTable band))

(defn get-raster-data-type
  ""
  [band]
  (.GetRasterDataType band))

(defn get-scale
  "Fetch the raster value scale"
  [band]
  (let [result (make-array java.lang.Double 1)
        safely #(cond % (short %))]
    (.GetScale band result)
    (-> result first safely)))

(defn get-statistics
  "Fetch image statistics"
  [band approx-ok force]
  (gdal.util/not-yet))

(defn get-unit-type
  "Fetch raster unit type"
  [band]
  (.GetUnitType band))

(defn get-byte-count
  "Count the number of bytes used by java-type"
  [java-type]
  (/ (eval `(. ~java-type SIZE)) 8))

(defn get-x-size
  "Fetch number of pixels along x axis"
  [band]
  (.GetXSize band))

(defn get-y-size
  "Fetch number of pixels along y axis"
  [band]
  (.GetYSize band))

(defn get-size
  "Fetch number of pixels along x and y axis"
  [band]
  [(get-x-size band) (get-y-size band)])

(defn has-arbitrary-overviews
  "Check for arbitrary overviews"
  [band]
  (.HasArbitraryOverviews band))

(defn read-block-direct
  "Read a block of image data efficiently"
  [band xoff yoff buffer]
  (.ReadBlock_Direct band xoff yoff buffer))

(defn read-raster-direct
  "Read a region of image data"
  ([band xoff yoff xsize ysize]
   (let [buf-type (get-data-type band)]
     (.ReadRaster_Direct band xoff yoff xsize ysize buf-type)))
  ([band xoff yoff xsize ysize buf-xsize buf-ysize buf-type buffer]
   (.ReadRaster_Direct band xoff yoff xsize ysize buf-xsize buf-ysize buf-type buffer)))

(defn raster-seq
  "Read an entire raster in blocks without converting buffer to array.

  This function is for power users that intend to use byte buffer contents
  directly. It avoids the overhead raster-seq incurs when it converts the
  buffer contents into a vector."
  [band & {:keys [xstart ystart xstop ystop xstep ystep java-type]
            :or   {xstart    0
                   ystart    0
                   xstop     (get-x-size band)
                   ystop     (get-y-size band)
                   xstep     (get-block-x-size band)
                   ystep     (get-block-y-size band)
            :as args}}]
   (let [reader #(read-raster-direct band %1 %2 xstep ystep)]
     (for [x (range xstart xstop xstep)
           y (range xstart ystop ystep)]
       {:x x :y y :data (reader x y)})))

(defn read-raster
  ""
  [band x y xs ys array & opts]
  ;; XXX handle overloaded function calls with opts
  (.ReadRaster band x y xs ys array))

(defn set-category-names
  "Set the category names for this band"
  [band names]
  (.GetCategoryNames band names))

(defn set-color-interpretation
  "Set color interpretation of band"
  [band interpretation]
  (.SetColorInterpretation band interpretation))

(defn set-color-table
  "Set color table associated with band"
  [band color-table]
  (gdal.util/not-yet))

(defn set-default-histogram
  "Set default histogram"
  [band min max histogram]
  (gdal.util/not-yet))

(defn set-default-rat
  "Set raster color table"
  [band table]
  (gdal.util/not-yet))

(defn set-no-data-value
  "Set the no data value for this band"
  [band no-data]
  (gdal.util/not-yet))

(defn set-offset
  "Set scaling offset"
  [band offset]
  (gdal.util/not-yet))

(defn set-scale
  "Set scaling ratio"
  [band scale]
  (gdal.util/not-yet))

(defn set-statistics
  "Set statistics on band"
  [band min max mean stddev]
  (gdal.util/not-yet))

(defn set-unit-type
  "Set unit type"
  [band unit-type]
  (gdal.util/not-yet))

(defn write-block-direct
  "Write a block of image data efficiently"
  [band xoff yoff data]
  (gdal.util/not-yet))

(defn write-raster-direct
  "Write a region of image data for this band. Buffer must be a direct allocated byte buffer."
  [band xoff yoff xsize ysize buffer]
  (let [gdal-type (gdal.util/type-map buffer)]
    (.WriteRaster_Direct band xoff yoff xsize ysize buffer)))

(defn write-raster
  "Write a region of image data for this band"
  [band xoff yoff xsize ysize data]
  ;; use a function to map java array type to
  ;; underlying gdal type...
  (let [gdal-type (gdal.util/array->gdal-type data)]
    (.WriteRaster band xoff yoff xsize ysize gdal-type data)))
