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
```clojure
gdal.core=> (init)
gdal.core=> (open "LC80280302015112LGN00_B1.TIF")
#object[org.gdal.gdal.Dataset 0x484dd493 "org.gdal.gdal.Dataset@484dd493"]
```


## License [&#x219F;](#contents)

Copyright © 2015 Jonathan Morton, Duncan McGreggor

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
