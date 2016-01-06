(ns clj-gdal.dataset
  (:import [org.gdal.gdal Dataset]
           [org.gdal.gdalconst gdalconst]
           [java.nio ByteBuffer]))


(defn get-geo-transform
  "Get the affine transformation coefficients of the dataset.

  These are used for transforming between pixel/line (P,L) raster space,
  and projection coordinates (Xp,Yp) space.

    Xp = geoTransformArray[0] + P*geoTransformArray[1] + L*geoTransformArray[2];
    Yp = geoTransformArray[3] + P*geoTransformArray[4] + L*geoTransformArray[5];

  Ultimately, you shouldn't need to use these to retrieve raster data using
  gdal-clj.
  "
  [dataset]
  (vec (.GetGeoTransform dataset)))

(defn get-projection
  "Get the projection definition WKT string for dataset"
  [dataset]
  (.GetProjection dataset))

(defn get-raster-count
  "Get the number of raster bands in the dataset"
  [dataset]
  (.GetRasterCount dataset))

(defn get-raster-x-size
  "Raster width in pixels"
  [dataset]
  (.GetRasterXSize dataset))

(defn get-raster-y-size
  "Raster height in pixels"
  [dataset]
  (.GetRasterYSize dataset))

(defn get-raster-band
  "Get the nth raster band, starts with index of 1 in keeping with GDAL conventions"
  [dataset n]
  (.GetRasterBand dataset n))

(defn read-raster
  "Read a region of image data from multiple bands"
  [dataset xoff yoff xsize ysize & bands]
  nil)
