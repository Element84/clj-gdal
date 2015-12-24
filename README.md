# clj-gdal

*Clojure-idiomatic GDAL wrapper*

[![][clj-gdal-logo]][clj-gdal-logo-large]

[clj-gdal-logo]: resources/images/clj-gdal-LogoColor-x250.png
[clj-gdal-logo-large]: resources/images/clj-gdal-LogoColor-x1000.png


#### Contents

* [About](#about-)
* [Dependencies](#dependencies-)
* [Usage](#usage-)
* [License](#license-)


## About [&#x219F;](#contents)

[GDAL](http://www.gdal.org/) (Geospatial Data Abstraction Library) is a library
for reading and writing raster and vector geospatial data formats. GDAL
supports more than 120 data formats such as NOAA GTX, NASA ELAS, HF2/HFZ, ESRI
HDR, NetCDF, GeoTIFF, etc.

The clj-gdal project offers a Clojure wrapper around parts of the Java GDAL
library.

This version of the library was forked from the Element84 repo, here:

* https://github.com/Element84/clj-gdal

Key differences include:

* Support for UbuntuGIS versions of GDAL (1.x)
* Removal of ``clj-`` from namespace


## Dependencies [&#x219F;](#contents)

* Java 1.7 or higher (uses java.nio via the nio Clojure wrapper)
* Java GDAL 2.0.0 library (included)

The Java library downloaded from Maven still requires that you have GDAL 2.x
compiled on your system, as it references libraries which GDAL builds.


## Usage [&#x219F;](#contents)

Add clj-gdal a dependency to your lein project:

[![Clojars Project](http://clojars.org/oubiwann/clj-gdal/latest-version.svg)](http://clojars.org/oubiwann/clj-gdal)

Then start up the Clojure REPL:

```bash
$ lein repl
```

The following examples use the development convenience namespace ``gdal-dev``. This
namespace has the following requires already done for you:

```clojure
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
```


### Top-level GDAL Functions

```clojure
gdal.dev=> (gdal/init)
nil
gdal.dev=> (def data (gdal/open "LC80290302015263LGN00_B2.TIF"))
#'gdal.dev/tiff-file
gdal.dev=> (def tiff-data (gdal/open tiff-file))
#'gdal.dev/tiff-data
```


### Working with Dataset Objects

```clojure
gdal.dev=> (dataset/get-size tiff-data)
[7681 7811]
gdal.dev=> (dataset/count-bands tiff-data)
1
gdal.dev=> (def band-data (dataset/get-band tiff-data 1))
#'gdal.dev/band-data
```


### Working with Band Objects

```clojure
gdal.dev=> (band/get-checksum band-data)
60155

```


### Working with Projection Objects

Top-level data:

```clojure

```


Datum Functions:

```clojure
gdal.dev=> (proj/get-datum proj-data)
#object[org.apache.sis.referencing.datum.DefaultGeodeticDatum 0x659718b5 "GeodeticDatum[\"WGS_1984\",\n  Ellipsoid[\"WGS 84\", 6378137.0, 298.257223563],\n  Id[\"EPSG\", 6326, URI[\"urn:ogc:def:datum:EPSG::6326\"]]]"]
gdal.dev=> (proj/get-ellipsoid proj-data)
#object[org.apache.sis.referencing.datum.DefaultEllipsoid 0x1b798fdd "Ellipsoid[\"WGS 84\", 6378137.0, 298.257223563, Id[\"EPSG\", 7030, URI[\"urn:ogc:def:ellipsoid:EPSG::7030\"]]]"]
gdal.dev=> (proj/get-prime-meridian proj-data)
#object[org.apache.sis.referencing.datum.DefaultPrimeMeridian 0x4a616ee4 "PrimeMeridian[\"Greenwich\", 0.0]"]
gdal.dev=> (proj/get-bw-params proj-data)
#object["[Lorg.apache.sis.referencing.datum.BursaWolfParameters;" 0x5b736d8 "[Lorg.apache.sis.referencing.datum.BursaWolfParameters;@5b736d8"]

```

Coordinate Systems and Axes:

```clojure
gdal.dev=> (proj/get-coord-system proj-data)
#object[org.apache.sis.referencing.cs.DefaultCartesianCS 0xaeb1198 "CS[Cartesian, 2]"]
gdal.dev=> (proj/get-coord-name proj-data)
"Cartesian CS: East (m), North (m)."
gdal.dev=> (proj/get-coord-dim proj-data)
2
gdal.dev=> (proj/get-axes proj-data)
(#object[org.apache.sis.referencing.cs.DefaultCoordinateSystemAxis 0x1c2152ac "Axis[\"Easting (E)\", east, Unit[\"metre\", 1]]"] #object[org.apache.sis.referencing.cs.DefaultCoordinateSystemAxis 0x4fbaa8a1 "Axis[\"Northing (N)\", north, Unit[\"metre\", 1]]"])
gdal.dev=> (proj/get-axis proj-data 1)
#object[org.apache.sis.referencing.cs.DefaultCoordinateSystemAxis 0x4fbaa8a1 "Axis[\"Northing (N)\", north, Unit[\"metre\", 1]]"]
gdal.dev=> (proj/get-axis-name proj-data 1)
"Northing"
gdal.dev=> (proj/get-axis-unit proj-data 1)
"m"

```


## License [&#x219F;](#contents)

Copyright © 2015 Jonathan Morton, Duncan McGreggor

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
