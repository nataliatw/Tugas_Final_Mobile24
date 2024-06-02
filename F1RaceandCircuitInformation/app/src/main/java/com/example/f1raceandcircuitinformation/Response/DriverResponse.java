package com.example.f1raceandcircuitinformation.Response;

import com.example.f1raceandcircuitinformation.Response.Driver;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DriverResponse {
    private MRData MRData;

    public MRData getMRData() {
        return MRData;
    }

    public void setMRData(MRData MRData) {
        this.MRData = MRData;
    }

    public class MRData {
        private DriverTable DriverTable;

        public DriverTable getDriverTable() {
            return DriverTable;
        }

        public void setDriverTable(DriverTable driverTable) {
            DriverTable = driverTable;
        }

        public class DriverTable {
            private List<Driver> Drivers;

            public List<Driver> getDrivers() {
                return Drivers;
            }

            public void setDrivers(List<Driver> drivers) {
                Drivers = drivers;
            }
        }
    }
}

