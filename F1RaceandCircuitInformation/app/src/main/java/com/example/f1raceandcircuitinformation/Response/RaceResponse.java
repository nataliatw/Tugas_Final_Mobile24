package com.example.f1raceandcircuitinformation.Response;

import java.util.List;

public class RaceResponse {
    private MRData MRData;

    public MRData getMRData() {
        return MRData;
    }

    public void setMRData(MRData MRData) {
        this.MRData = MRData;
    }

    public class MRData {
        private RaceTable RaceTable;

        public RaceTable getRaceTable() {
            return RaceTable;
        }

        public void setRaceTable(RaceTable raceTable) {
            RaceTable = raceTable;
        }

        public class RaceTable {
            private List<Race> Races;

            public List<Race> getRaces() {
                return Races;
            }

            public void setRaces(List<Race> races) {
                Races = races;
            }
        }
    }
}
