package com.example.diego.quake.data;

import java.util.List;

public class Quake {

    /**
     * type : FeatureCollection
     * metadata : {"generated":1537887787000,"url":"https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_day.geojson","title":"USGS Magnitude 4.5+ Earthquakes, Past Day","status":200,"api":"1.5.8","count":6}
     * features : [{"type":"Feature","properties":{"mag":4.8,"place":"215km ENE of Port Mathurin, Mauritius","time":1537867258440,"updated":1537868555040,"tz":240,"url":"https://earthquake.usgs.gov/earthquakes/eventpage/us2000hjne","detail":"https://earthquake.usgs.gov/earthquakes/feed/v1.0/detail/us2000hjne.geojson","felt":null,"cdi":null,"mmi":null,"alert":null,"status":"reviewed","tsunami":0,"sig":354,"net":"us","code":"2000hjne","ids":",us2000hjne,","sources":",us,","types":",geoserve,origin,phase-data,","nst":null,"dmin":2.009,"rms":0.71,"gap":52,"magType":"mb","type":"earthquake","title":"M 4.8 - 215km ENE of Port Mathurin, Mauritius"},"geometry":{"type":"Point","coordinates":[65.1637,-18.6694,10]},"id":"us2000hjne"},{"type":"Feature","properties":{"mag":5.1,"place":"292km SSW of Severo-Kuril'sk, Russia","time":1537865619920,"updated":1537866894040,"tz":600,"url":"https://earthquake.usgs.gov/earthquakes/eventpage/us2000hjmx","detail":"https://earthquake.usgs.gov/earthquakes/feed/v1.0/detail/us2000hjmx.geojson","felt":null,"cdi":null,"mmi":null,"alert":null,"status":"reviewed","tsunami":0,"sig":400,"net":"us","code":"2000hjmx","ids":",us2000hjmx,","sources":",us,","types":",geoserve,origin,phase-data,","nst":null,"dmin":5.249,"rms":0.71,"gap":131,"magType":"mb","type":"earthquake","title":"M 5.1 - 292km SSW of Severo-Kuril'sk, Russia"},"geometry":{"type":"Point","coordinates":[155.0126,48.1512,35]},"id":"us2000hjmx"},{"type":"Feature","properties":{"mag":4.8,"place":"38km NNE of San Isidro, Philippines","time":1537841795850,"updated":1537842780040,"tz":480,"url":"https://earthquake.usgs.gov/earthquakes/eventpage/us2000hjkk","detail":"https://earthquake.usgs.gov/earthquakes/feed/v1.0/detail/us2000hjkk.geojson","felt":null,"cdi":null,"mmi":null,"alert":null,"status":"reviewed","tsunami":0,"sig":354,"net":"us","code":"2000hjkk","ids":",us2000hjkk,","sources":",us,","types":",geoserve,origin,phase-data,","nst":null,"dmin":3.305,"rms":0.86,"gap":142,"magType":"mb","type":"earthquake","title":"M 4.8 - 38km NNE of San Isidro, Philippines"},"geometry":{"type":"Point","coordinates":[126.225,10.3338,132.86]},"id":"us2000hjkk"},{"type":"Feature","properties":{"mag":4.8,"place":"South of Tonga","time":1537833029720,"updated":1537834646040,"tz":-720,"url":"https://earthquake.usgs.gov/earthquakes/eventpage/us2000hjk4","detail":"https://earthquake.usgs.gov/earthquakes/feed/v1.0/detail/us2000hjk4.geojson","felt":null,"cdi":null,"mmi":null,"alert":null,"status":"reviewed","tsunami":0,"sig":354,"net":"us","code":"2000hjk4","ids":",us2000hjk4,","sources":",us,","types":",geoserve,origin,phase-data,","nst":null,"dmin":7.349,"rms":1.15,"gap":77,"magType":"mb","type":"earthquake","title":"M 4.8 - South of Tonga"},"geometry":{"type":"Point","coordinates":[-175.4491,-24.3626,10]},"id":"us2000hjk4"},{"type":"Feature","properties":{"mag":4.5,"place":"15km NNE of Bekasi, Indonesia","time":1537811621030,"updated":1537816342208,"tz":420,"url":"https://earthquake.usgs.gov/earthquakes/eventpage/us2000hjdb","detail":"https://earthquake.usgs.gov/earthquakes/feed/v1.0/detail/us2000hjdb.geojson","felt":1,"cdi":2,"mmi":null,"alert":null,"status":"reviewed","tsunami":0,"sig":312,"net":"us","code":"2000hjdb","ids":",us2000hjdb,","sources":",us,","types":",dyfi,geoserve,origin,phase-data,","nst":null,"dmin":3.527,"rms":1.38,"gap":96,"magType":"mb","type":"earthquake","title":"M 4.5 - 15km NNE of Bekasi, Indonesia"},"geometry":{"type":"Point","coordinates":[107.0202,-6.0966,234.9]},"id":"us2000hjdb"},{"type":"Feature","properties":{"mag":5.4,"place":"183km SSE of Bristol Island, South Sandwich Islands","time":1537809190610,"updated":1537816478485,"tz":-120,"url":"https://earthquake.usgs.gov/earthquakes/eventpage/us2000hjd3","detail":"https://earthquake.usgs.gov/earthquakes/feed/v1.0/detail/us2000hjd3.geojson","felt":null,"cdi":null,"mmi":2.51,"alert":"green","status":"reviewed","tsunami":0,"sig":449,"net":"us","code":"2000hjd3","ids":",us2000hjd3,","sources":",us,","types":",geoserve,losspager,origin,phase-data,shakemap,","nst":null,"dmin":8.638,"rms":1.12,"gap":73,"magType":"mb","type":"earthquake","title":"M 5.4 - 183km SSE of Bristol Island, South Sandwich Islands"},"geometry":{"type":"Point","coordinates":[-25.5862,-60.6106,10]},"id":"us2000hjd3"}]
     * bbox : [-175.4491,-60.6106,10,155.0126,48.1512,234.9]
     */

    private String type;
    private MetadataBean metadata;
    private List<FeaturesBean> features;
    private List<Double> bbox;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MetadataBean getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataBean metadata) {
        this.metadata = metadata;
    }

    public List<FeaturesBean> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeaturesBean> features) {
        this.features = features;
    }

    public List<Double> getBbox() {
        return bbox;
    }

    public void setBbox(List<Double> bbox) {
        this.bbox = bbox;
    }

    public static class MetadataBean {
        /**
         * generated : 1537887787000
         * url : https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_day.geojson
         * title : USGS Magnitude 4.5+ Earthquakes, Past Day
         * status : 200
         * api : 1.5.8
         * count : 6
         */

        private long generated;
        private String url;
        private String title;
        private int status;
        private String api;
        private int count;

        public long getGenerated() {
            return generated;
        }

        public void setGenerated(long generated) {
            this.generated = generated;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getApi() {
            return api;
        }

        public void setApi(String api) {
            this.api = api;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    public static class FeaturesBean {
        /**
         * type : Feature
         * properties : {"mag":4.8,"place":"215km ENE of Port Mathurin, Mauritius","time":1537867258440,"updated":1537868555040,"tz":240,"url":"https://earthquake.usgs.gov/earthquakes/eventpage/us2000hjne","detail":"https://earthquake.usgs.gov/earthquakes/feed/v1.0/detail/us2000hjne.geojson","felt":null,"cdi":null,"mmi":null,"alert":null,"status":"reviewed","tsunami":0,"sig":354,"net":"us","code":"2000hjne","ids":",us2000hjne,","sources":",us,","types":",geoserve,origin,phase-data,","nst":null,"dmin":2.009,"rms":0.71,"gap":52,"magType":"mb","type":"earthquake","title":"M 4.8 - 215km ENE of Port Mathurin, Mauritius"}
         * geometry : {"type":"Point","coordinates":[65.1637,-18.6694,10]}
         * id : us2000hjne
         */

        private String type;
        private PropertiesBean properties;
        private GeometryBean geometry;
        private String id;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public PropertiesBean getProperties() {
            return properties;
        }

        public void setProperties(PropertiesBean properties) {
            this.properties = properties;
        }

        public GeometryBean getGeometry() {
            return geometry;
        }

        public void setGeometry(GeometryBean geometry) {
            this.geometry = geometry;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class PropertiesBean {
            /**
             * mag : 4.8
             * place : 215km ENE of Port Mathurin, Mauritius
             * time : 1537867258440
             * updated : 1537868555040
             * tz : 240
             * url : https://earthquake.usgs.gov/earthquakes/eventpage/us2000hjne
             * detail : https://earthquake.usgs.gov/earthquakes/feed/v1.0/detail/us2000hjne.geojson
             * felt : null
             * cdi : null
             * mmi : null
             * alert : null
             * status : reviewed
             * tsunami : 0
             * sig : 354
             * net : us
             * code : 2000hjne
             * ids : ,us2000hjne,
             * sources : ,us,
             * types : ,geoserve,origin,phase-data,
             * nst : null
             * dmin : 2.009
             * rms : 0.71
             * gap : 52
             * magType : mb
             * type : earthquake
             * title : M 4.8 - 215km ENE of Port Mathurin, Mauritius
             */

            private double mag;
            private String place;
            private long time;
            private long updated;
            private int tz;
            private String url;
            private String detail;
            private Object felt;
            private Object cdi;
            private Object mmi;
            private Object alert;
            private String status;
            private int tsunami;
            private int sig;
            private String net;
            private String code;
            private String ids;
            private String sources;
            private String types;
            private Object nst;
            private double dmin;
            private double rms;
            private double gap;
            private String magType;
            private String type;
            private String title;

            public double getMag() {
                return mag;
            }

            public void setMag(double mag) {
                this.mag = mag;
            }

            public String getPlace() {
                return place;
            }

            public void setPlace(String place) {
                this.place = place;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public long getUpdated() {
                return updated;
            }

            public void setUpdated(long updated) {
                this.updated = updated;
            }

            public int getTz() {
                return tz;
            }

            public void setTz(int tz) {
                this.tz = tz;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public Object getFelt() {
                return felt;
            }

            public void setFelt(Object felt) {
                this.felt = felt;
            }

            public Object getCdi() {
                return cdi;
            }

            public void setCdi(Object cdi) {
                this.cdi = cdi;
            }

            public Object getMmi() {
                return mmi;
            }

            public void setMmi(Object mmi) {
                this.mmi = mmi;
            }

            public Object getAlert() {
                return alert;
            }

            public void setAlert(Object alert) {
                this.alert = alert;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getTsunami() {
                return tsunami;
            }

            public void setTsunami(int tsunami) {
                this.tsunami = tsunami;
            }

            public int getSig() {
                return sig;
            }

            public void setSig(int sig) {
                this.sig = sig;
            }

            public String getNet() {
                return net;
            }

            public void setNet(String net) {
                this.net = net;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getIds() {
                return ids;
            }

            public void setIds(String ids) {
                this.ids = ids;
            }

            public String getSources() {
                return sources;
            }

            public void setSources(String sources) {
                this.sources = sources;
            }

            public String getTypes() {
                return types;
            }

            public void setTypes(String types) {
                this.types = types;
            }

            public Object getNst() {
                return nst;
            }

            public void setNst(Object nst) {
                this.nst = nst;
            }

            public double getDmin() {
                return dmin;
            }

            public void setDmin(double dmin) {
                this.dmin = dmin;
            }

            public double getRms() {
                return rms;
            }

            public void setRms(double rms) {
                this.rms = rms;
            }

            public double getGap() {
                return gap;
            }

            public void setGap(int gap) {
                this.gap = gap;
            }

            public String getMagType() {
                return magType;
            }

            public void setMagType(String magType) {
                this.magType = magType;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class GeometryBean {
            /**
             * type : Point
             * coordinates : [65.1637,-18.6694,10]
             */

            private String type;
            private List<Double> coordinates;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<Double> getCoordinates() {
                return coordinates;
            }

            public void setCoordinates(List<Double> coordinates) {
                this.coordinates = coordinates;
            }
        }
    }
}
