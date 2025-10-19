import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@DisplayName("Shipment System Test Suite")
public class AbstractShipmentTest {
    
    private Goods smallGoods;
    private Goods mediumGoods;
    private Goods largeGoods;
    
    @BeforeEach
    void setUp() {
        smallGoods = new Goods(1000, 500);
        mediumGoods = new Goods(10000, 5000);
        largeGoods = new Goods(30000, 70000);
    }
    
    // ==================== Goods Tests ====================
    
    @Test
    @DisplayName("Goods - Create valid goods")
    void testGoodsCreation() {
        Goods goods = new Goods(100, 200);
        assertEquals(100, goods.getWeight());
        assertEquals(200, goods.getVolume());
    }
    
    @Test
    @DisplayName("Goods - Negative weight throws exception")
    void testGoodsNegativeWeight() {
        assertThrows(IllegalArgumentException.class, () -> new Goods(-1, 100));
    }
    
    @Test
    @DisplayName("Goods - Negative volume throws exception")
    void testGoodsNegativeVolume() {
        assertThrows(IllegalArgumentException.class, () -> new Goods(100, -1));
    }
    
    @Test
    @DisplayName("Goods - Equality test")
    void testGoodsEquality() {
        Goods goods1 = new Goods(100, 200);
        Goods goods2 = new Goods(100, 200);
        Goods goods3 = new Goods(100, 300);
        
        assertEquals(goods1, goods2);
        assertNotEquals(goods1, goods3);
    }
    
    // ==================== AirShipment Tests ====================
    
    @Test
    @DisplayName("AirShipment - Create with valid distance")
    void testAirShipmentCreation() {
        AirShipment shipment = new AirShipment(1000);
        assertEquals(0, shipment.getCurrentQuantity());
        assertEquals(80000, shipment.getQuantityLimit());
    }
    
    @Test
    @DisplayName("AirShipment - Add goods within limit")
    void testAirShipmentAddGoods() {
        AirShipment shipment = new AirShipment(1000);
        shipment.add(smallGoods);
        
        assertEquals(500, shipment.getCurrentQuantity());
        assertEquals(1, shipment.allGoods().size());
    }
    
    @Test
    @DisplayName("AirShipment - Add multiple goods")
    void testAirShipmentAddMultipleGoods() {
        AirShipment shipment = new AirShipment(1000);
        shipment.add(smallGoods);
        shipment.add(mediumGoods);
        
        assertEquals(5500, shipment.getCurrentQuantity());
        assertEquals(2, shipment.allGoods().size());
    }
    
    @Test
    @DisplayName("AirShipment - Exceed limit throws exception")
    void testAirShipmentExceedLimit() {
        AirShipment shipment = new AirShipment(1000);
        Goods tooLarge = new Goods(100000, 85000);
        
        assertThrows(IllegalStateException.class, () -> shipment.add(tooLarge));
    }
    
    @Test
    @DisplayName("AirShipment - Cost calculation")
    void testAirShipmentCost() {
        AirShipment shipment = new AirShipment(1000);
        shipment.add(smallGoods); // volume = 500
        
        // Cost = 10 * distance + currentQuantity * 4
        // Cost = 10 * 1000 + 500 * 4 = 10000 + 2000 = 12000
        assertEquals(12000.0, shipment.cost(), 0.001);
    }
    
    @Test
    @DisplayName("AirShipment - Cost with no goods")
    void testAirShipmentCostEmpty() {
        AirShipment shipment = new AirShipment(500);
        
        // Cost = 10 * 500 + 0 * 4 = 5000
        assertEquals(5000.0, shipment.cost(), 0.001);
    }
    
    @Test
    @DisplayName("AirShipment - List is unmodifiable")
    void testAirShipmentUnmodifiableList() {
        AirShipment shipment = new AirShipment(1000);
        shipment.add(smallGoods);
        
        List<Goods> goodsList = shipment.allGoods();
        assertThrows(UnsupportedOperationException.class, 
            () -> goodsList.add(mediumGoods));
    }
    
    // ==================== UrgentAirShipment Tests ====================
    
    @Test
    @DisplayName("UrgentAirShipment - Create with valid distance")
    void testUrgentAirShipmentCreation() {
        UrgentAirShipment shipment = new UrgentAirShipment(1000);
        assertEquals(0, shipment.getCurrentQuantity());
        assertEquals(80000, shipment.getQuantityLimit());
    }
    
    @Test
    @DisplayName("UrgentAirShipment - Inherits limit from AirShipment")
    void testUrgentAirShipmentInheritsLimit() {
        UrgentAirShipment urgentShipment = new UrgentAirShipment(1000);
        AirShipment airShipment = new AirShipment(1000);
        
        assertEquals(airShipment.getQuantityLimit(), urgentShipment.getQuantityLimit());
    }
    
    @Test
    @DisplayName("UrgentAirShipment - Cost is double of AirShipment")
    void testUrgentAirShipmentCost() {
        UrgentAirShipment urgentShipment = new UrgentAirShipment(1000);
        urgentShipment.add(smallGoods); // volume = 500
        
        // Cost = (10 * distance + currentQuantity * 4) * 2
        // Cost = (10 * 1000 + 500 * 4) * 2 = 12000 * 2 = 24000
        assertEquals(24000.0, urgentShipment.cost(), 0.001);
    }
    
    @Test
    @DisplayName("UrgentAirShipment - Exceeds limit throws exception")
    void testUrgentAirShipmentExceedLimit() {
        UrgentAirShipment shipment = new UrgentAirShipment(1000);
        Goods tooLarge = new Goods(100000, 85000);
        
        assertThrows(IllegalStateException.class, () -> shipment.add(tooLarge));
    }
    
    // ==================== RoadShipment Tests ====================
    
    @Test
    @DisplayName("RoadShipment - Create with valid distance")
    void testRoadShipmentCreation() {
        RoadShipment shipment = new RoadShipment(500);
        assertEquals(0, shipment.getCurrentQuantity());
        assertEquals(38000, shipment.getQuantityLimit());
    }
    
    @Test
    @DisplayName("RoadShipment - Add goods (uses weight)")
    void testRoadShipmentAddGoods() {
        RoadShipment shipment = new RoadShipment(500);
        shipment.add(smallGoods); // weight = 1000
        
        assertEquals(1000, shipment.getCurrentQuantity());
    }
    
    @Test
    @DisplayName("RoadShipment - Exceed weight limit")
    void testRoadShipmentExceedLimit() {
        RoadShipment shipment = new RoadShipment(500);
        Goods tooHeavy = new Goods(40000, 100);
        
        assertThrows(IllegalStateException.class, () -> shipment.add(tooHeavy));
    }
    
    @Test
    @DisplayName("RoadShipment - Cost calculation")
    void testRoadShipmentCost() {
        RoadShipment shipment = new RoadShipment(500);
        shipment.add(smallGoods); // weight = 1000
        
        // Cost = distance * currentQuantity * 4
        // Cost = 500 * 1000 * 4 = 2000000
        assertEquals(2000000.0, shipment.cost(), 0.001);
    }
    
    @Test
    @DisplayName("RoadShipment - Add multiple goods up to limit")
    void testRoadShipmentAddMultipleGoods() {
        RoadShipment shipment = new RoadShipment(500);
        shipment.add(smallGoods);  // 1000
        shipment.add(mediumGoods); // 10000
        shipment.add(new Goods(20000, 5000)); // 20000
        
        assertEquals(31000, shipment.getCurrentQuantity());
        
        // Should throw when trying to add more (would exceed 38000 limit)
        assertThrows(IllegalStateException.class, 
            () -> shipment.add(new Goods(8000, 100)));
    }
    
    // ==================== FluvialShipment Tests ====================
    
    @Test
    @DisplayName("FluvialShipment - Create with valid distance")
    void testFluvialShipmentCreation() {
        FluvialShipment shipment = new FluvialShipment(2000);
        assertEquals(0, shipment.getCurrentQuantity());
        assertEquals(300000, shipment.getQuantityLimit());
    }
    
    @Test
    @DisplayName("FluvialShipment - Add goods (uses weight)")
    void testFluvialShipmentAddGoods() {
        FluvialShipment shipment = new FluvialShipment(2000);
        shipment.add(smallGoods); // weight = 1000
        
        assertEquals(1000, shipment.getCurrentQuantity());
    }
    
    @Test
    @DisplayName("FluvialShipment - Exceed weight limit")
    void testFluvialShipmentExceedLimit() {
        FluvialShipment shipment = new FluvialShipment(2000);
        Goods tooHeavy = new Goods(350000, 100);
        
        assertThrows(IllegalStateException.class, () -> shipment.add(tooHeavy));
    }
    
    @Test
    @DisplayName("FluvialShipment - Cost calculation with sqrt")
    void testFluvialShipmentCost() {
        FluvialShipment shipment = new FluvialShipment(2000);
        shipment.add(new Goods(10000, 5000)); // weight = 10000
        
        // Cost = distance * sqrt(currentQuantity)
        // Cost = 2000 * sqrt(10000) = 2000 * 100 = 200000
        assertEquals(200000.0, shipment.cost(), 0.001);
    }
    
    @Test
    @DisplayName("FluvialShipment - Cost with no goods")
    void testFluvialShipmentCostEmpty() {
        FluvialShipment shipment = new FluvialShipment(2000);
        
        // Cost = 2000 * sqrt(0) = 0
        assertEquals(0.0, shipment.cost(), 0.001);
    }
    
    @Test
    @DisplayName("FluvialShipment - Large capacity advantage")
    void testFluvialShipmentLargeCapacity() {
        FluvialShipment shipment = new FluvialShipment(2000);
        
        // Can add much more than road or air
        for (int i = 0; i < 10; i++) {
            shipment.add(new Goods(25000, 1000));
        }
        
        assertEquals(250000, shipment.getCurrentQuantity());
        assertEquals(10, shipment.allGoods().size());
    }
    
    // ==================== Edge Cases and Integration Tests ====================
    
    @Test
    @DisplayName("Shipment - Negative distance throws exception")
    void testShipmentNegativeDistance() {
        assertThrows(IllegalArgumentException.class, () -> new AirShipment(-1));
        assertThrows(IllegalArgumentException.class, () -> new RoadShipment(-1));
        assertThrows(IllegalArgumentException.class, () -> new FluvialShipment(-1));
    }
    
    @Test
    @DisplayName("All shipments - Zero distance is valid")
    void testZeroDistance() {
        assertDoesNotThrow(() -> new AirShipment(0));
        assertDoesNotThrow(() -> new RoadShipment(0));
        assertDoesNotThrow(() -> new FluvialShipment(0));
    }
    
    @Test
    @DisplayName("Compare costs across shipment types")
    void testCompareCosts() {
        Goods testGoods = new Goods(5000, 3000);
        int distance = 1000;
        
        AirShipment air = new AirShipment(distance);
        air.add(testGoods);
        
        RoadShipment road = new RoadShipment(distance);
        road.add(testGoods);
        
        FluvialShipment fluvial = new FluvialShipment(distance);
        fluvial.add(testGoods);
        
        UrgentAirShipment urgentAir = new UrgentAirShipment(distance);
        urgentAir.add(testGoods);
        
        // Air: 10*1000 + 3000*4 = 22000
        assertEquals(22000.0, air.cost(), 0.001);
        
        // Road: 1000*5000*4 = 20000000
        assertEquals(20000000.0, road.cost(), 0.001);
        
        // Fluvial: 1000*sqrt(5000) ≈ 70710.678
        assertEquals(70710.678, fluvial.cost(), 0.001);
        
        // Urgent: 22000*2 = 44000
        assertEquals(44000.0, urgentAir.cost(), 0.001);
    }
    
    @Test
    @DisplayName("Boundary test - Add goods at exact limit")
    void testAddGoodsAtExactLimit() {
        AirShipment shipment = new AirShipment(1000);
        Goods exactLimit = new Goods(50000, 80000);
        
        assertDoesNotThrow(() -> shipment.add(exactLimit));
        assertEquals(80000, shipment.getCurrentQuantity());
    }
    
    @Test
    @DisplayName("Boundary test - Add goods one over limit")
    void testAddGoodsOneOverLimit() {
        AirShipment shipment = new AirShipment(1000);
        Goods overLimit = new Goods(50000, 80001);
        
        assertThrows(IllegalStateException.class, () -> shipment.add(overLimit));
    }
}
