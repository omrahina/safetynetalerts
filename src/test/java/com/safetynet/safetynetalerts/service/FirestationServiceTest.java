package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.FireStation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class FirestationServiceTest {

    @Autowired
    FirestationService firestationService;

    private List<FireStation> fireStations;

    @BeforeEach
    private void setUp(){
        FireStation fireStation1 = new FireStation("12 street", 1);
        FireStation fireStation2 = new FireStation("5 wall combo", 2);
        FireStation fireStation3 = new FireStation("29 15th St", 2);
        fireStations = new ArrayList<>();
        fireStations.add(fireStation1);
        fireStations.add(fireStation2);
        fireStations.add(fireStation3);

        firestationService.setFireStationList(fireStations);

    }

    @Test
    public void testList(){
        Iterable<FireStation> results = firestationService.list();

        assertThat(results).hasSize(3).extracting(FireStation::getAddress).contains("5 wall combo");
    }

    @Test
    public void testAddFirestation_success(){
        FireStation fireStation = new FireStation("834 Binoc Ave", 3);
        FireStation result = firestationService.addFirestation(fireStation);

        assertNotNull(result);
        assertThat(fireStations).hasSize(4).extracting(FireStation::getStation).containsExactly(1, 2, 2, 3);
    }

    @Test
    public void testAddFirestation_fail(){
        FireStation fireStation = new FireStation();
        FireStation result = firestationService.addFirestation(fireStation);

        assertNull(result);
        assertThat(fireStations).hasSize(3);
    }

    @Test
    public void testUpdateFirestation_success(){
        FireStation fireStation = new FireStation("5 wall combo", 4);
        FireStation result = firestationService.updateFirestation(fireStation);

        assertThat(fireStations).hasSize(3).extracting(FireStation::getStation).contains(4);
    }

    @Test
    public void testUpdateFirestation_fail(){
        FireStation fireStation = new FireStation("3 street combo", 4);
        FireStation result = firestationService.updateFirestation(fireStation);

        assertNull(result);
        assertThat(fireStations).hasSize(3).extracting(FireStation::getStation).doesNotContain(4);
    }
}
