# Introduction to clj-gdal

This library provides idiomatic Clojure support for GDAL. The project
version number will track the GDAL version number. Currently, only GDAL
1.11.2 is supported.


## Add Dependency

For Leiningen projects, updatet project.clj

[oubiwann/clj-gdal "0.0.1"]]

_This versioning scheme will probably change to reflect the version
of GDAL that is supported._


## Configure GDAL

To use this library you must compile GDAL's Java SWIG bindings and
set LD_LIBRARY_PATH to reference the directory that contains them.


### Get GDAL

The compilation and installation of GDAL on Linux, *BSD, and Mac can be a
bit tricky. This version of the Clojure library has only been tested
against GDAL 1.11.2 on Ubuntu from the UbuntuGIS reposotiory.
