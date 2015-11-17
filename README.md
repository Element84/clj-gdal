# clj-gdal

[![][clj-gdal-logo]][clj-gdal-logo-large]

[clj-gdal-logo]: resources/images/clj-gdal-LogoColor-x250.png
[clj-gdal-logo-large]: resources/images/clj-gdal-LogoColor-x1000.png


*Clojure support for GDAL*

##### Contents

* [About](#about-)
* [Dependencies](#dependencies-)
* [Getting Started](#getting-started-)
* [License](#license-)


## About [&#x219F;](#contents)

[GDAL](http://www.gdal.org/) (Geospatial Data Abstraction Library) is a library
for reading and writing raster and vector geospatial data formats. GDAL
supports more than 120 data formats such as NOAA GTX, NASA ELAS, HF2/HFZ, ESRI
HDR, NetCDF, GeoTIFF, etc.

The clj-gdal project offers a Clojure wrapper around parts of the Java GDAL
library.


## Dependencies [&#x219F;](#contents)

Currently, only GDAL 2.0.1 support is provided.


## Getting Started [&#x219F;](#contents)

You must compile GDAL 2.0.1 Java SWIG bindings and add the generated libraries to `LD_LIBRARY_PATH`

Then add clj-gdal a dependency to your lein project:

[![Clojars Project](http://clojars.org/clj-gdal/latest-version.svg)](http://clojars.org/clj-gdal)


## License [&#x219F;](#contents)

Copyright © 2015 Jonathan Morton

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
