package taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> {
    val driverMap = this.trips.groupBy { it.driver.name }
    return this.allDrivers.filter { !driverMap.containsKey(it.name) }.toSet();
}

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> {
    return this.trips.flatMap { it.passengers }
        .groupBy { it.name }
        .filter { (_, value) -> value.size >= minTrips }
        .map { (key, _) -> Passenger(key) }
        .toSet()
}

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> {
    return this.trips.filter { it.driver.name == driver.name }
        .flatMap { it.passengers }
        .groupBy { it.name }
        .filter { (_, value) -> value.size >= 2 }
        .map { (key, _) -> Passenger(key) }
        .toSet()
}

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> {
    fun countDiscount(passenger: Passenger): Boolean {
        val hasDiscount = this.trips.count { it.discount != null && it.passengers.contains(passenger) }
        val noDiscount = this.trips.count { it.discount == null && it.passengers.contains(passenger) }
        return hasDiscount > noDiscount;
    }
    return this.allPassengers.filter { countDiscount(it) }.toSet()
}

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    if(trips.isEmpty()) return null;
    val map: Map.Entry<Int, List<Trip>> = this.trips.groupBy { it.duration/10 }.maxByOrNull { it.value.size } ?: return null
    return (map.key*10)..(map.key*10+9);
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    TODO()
}