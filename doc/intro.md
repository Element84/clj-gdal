# Introduction to clj-gdal

This library provides idiomatic Clojure support for GDAL. The project
version number will track the GDAL version number. Currently, only GDAL
2.0.1 is supported.


## Add Dependency

For Leiningen projects, updatet project.clj

[jmorton/clj-gdal "0.0.1-SNAPSHOT"]]

_This versioning scheme will probably change to reflect the version
of GDAL that is supported._


## Configure GDAL

To use this library you must compile GDAL's Java SWIG bindings and
set LD_LIBRARY_PATH to reference the directory that contains them.

### Get GDAL and compile it

```
curl -O http://download.osgeo.org/gdal/2.0.1/gdal-2.0.1.tar.gz
tar -xzf gdal-2.0.1.tar.gz
cd gdal-2.0.1
./configure --with-java=yes
make
```

### Build SWIG Bindings

This step requires editing swig/java/java.opt to refer to your
JAVA_HOME directory. This might be `/usr/lib/jvm/default-java` but
it may also be different on your system.

```
cd swig/java
# EDIT java.opt JAVA_HOME
make
```

### Export LD_LIBRARY_PATH

Now you must set LD_LIBRARY_PATH to refer to the shared objects
created by previous `make`

```
mkdir -p ~/opts/lib
cp lib*.so ~/opts/lib
EXPORT LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$HOME/opts/lib"
```
