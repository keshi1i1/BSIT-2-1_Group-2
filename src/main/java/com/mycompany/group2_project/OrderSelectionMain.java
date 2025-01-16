/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.group2_project;

/**
 *
 * @author Alfred Gualberto
 */

/*
[GUI OUTPUT: USER INPUT]
- OrderSelection (choices of foods, orders   of the customer) 
- name (customer's name)
- address (customer's address for delivery status purposes)

[DATABASE]
- OrderSelection, name, address 
- orderId (random generated numbers, [ex. 109091029])
- customerId (random generated numbers, must not be the same as orderId, [ex. 263])
- restaurantId (NOT SURE here, probably given Id for different branch of the restaurant from different locations)
 */
public class OrderSelectionMain {

    public static void main(String[] args) {
        new OrderSelection();

    }
}
