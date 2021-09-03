import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE


    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        assertEquals(restaurant, service.findRestaurantByName("Amelie's cafe"));
        //WRITE UNIT TEST CASE HERE
    }
    
    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        assertThrows(restaurantNotFoundException.class,()->service.findRestaurantByName("Pantry d'or"));
        //WRITE UNIT TEST CASE HERE
    }

    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    
    
   //>>>>>>>>>>>>>>>>>>>>>>SUCCESS TEST CASE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
     @Test
    public void total_cost_success()  throws restaurantNotFoundException{
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",150);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Chicken Soup", 350);

        // user selected item
        ArrayList<Item> userselectedItems = new  ArrayList<Item>();
        Item item1 = new Item("Sweet corn soup",150);
        Item item2 = new Item("Chicken Soup", 350);
        userselectedItems.add(item1);
        userselectedItems.add(item2);
        // total cost should be 500 (150+350)
        assertEquals(restaurant.getTotalCost(userselectedItems), 500);
        //WRITE UNIT TEST CASE HERE
    }
    
 //<<<<<<<<<<<<<<<<<<<<SUCCESS TEST CASE>>>>>>>>>>>>>>>>>>>>>>>>>>
    
 //>>>>>>>>>>>>>>>>>>>>>>FAILED TEST CASE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void total_cost_failed() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",150);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Chicken Soup", 350);

        // user selected item
        ArrayList<Item> userselectedItems = new  ArrayList<Item>();
        Item item1 = new Item("Sweet corn soup",150);
        userselectedItems.add(item1);
        // total cost is 150, not equal to 500
        assertNotEquals(restaurant.getTotalCost(userselectedItems), 500);
        //WRITE UNIT TEST CASE HERE
    }
    
   //<<<<<<<<<<<<<<<<<<<<FAILED TEST CASE>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}
