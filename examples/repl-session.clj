(gdal/init)
(def data-dir "/home/oubiwann/Documents/Landsat/Downloads")
(def scene-id "LC80290302015263LGN00")
(def tiff-file (format "%s/%s/%s_B2.TIF" data-dir scene-id scene-id))
(def tiff-data (gdal/open tiff-file))

(dataset/get-size tiff-data)
(dataset/count-bands tiff-data)

(def band-data (dataset/get-band tiff-data 1))
(band/get-checksum band-data)
