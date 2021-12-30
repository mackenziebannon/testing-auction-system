import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.ArrayList;

class BidderTest {

    /* add whitebox test
       Bidder()
       1. should not create a new bidder if bidderId < 0
       2. should not create a new bidder if bidderName == null
       3. should not create a new bidder if bidderName.length() == 0
       4. should create a new bidder if bidderId is greater than 0, the bidderName is not null,
          and bidderName is not an empty string
          note: this is assuming that someone would not input the name as a series of spaces.
     */
    @Test
    void testBidder_negativeBidderId(){
        HashMap<Integer, Lot> lotList = new HashMap<>();
        Bidder negativeIdBidder = new Bidder(lotList, "Jessie", -2);
        assertFalse(negativeIdBidder.bidderIsReady(), "Invalid bidder appears ready.");
    }

    @Test
    void testBidder_nullBidderName(){
        HashMap<Integer, Lot> lotList = new HashMap<>();
        Bidder nullNameBidder = new Bidder(lotList, null, 1);
        assertFalse(nullNameBidder.bidderIsReady(), "Invalid bidder appears ready.");
    }

    @Test
    void testBidder_emptyNameString(){
        HashMap<Integer, Lot> lotList = new HashMap<>();
        Bidder emptyNameBidder = new Bidder(lotList, "", 1);
        assertFalse(emptyNameBidder.bidderIsReady(), "Invalid bidder appears ready." );
    }

    @Test
    void testBidder_validBidder() {
        HashMap<Integer, Lot> lotList = new HashMap<>();
        Bidder validBidder = new Bidder(lotList, "Charles", 2);
        assertTrue(validBidder.bidderIsReady(), "valid bidder appears unready." );
    }

    /* add whitebox tests
       getBidderId()
       1. should return bidderNumber
    */
    @Test
    void testGetBidderId() {
        HashMap<Integer, Lot> lotList = new HashMap<>();
        Bidder newBidder = new Bidder(lotList, "Fred", 1);
        assertEquals(1, newBidder.getBidderId(), "method getBidderId did not return the correct Id");
    }

    /* add whitebox tests
       String feesOwed()
       1. should return bidderName, won, cost if hashmap is empty
       2. should return won = 1, cost = the current bid of the lot if there is one lot
          that was won by the bidder
       3. should return bidderName, won = 0, cost = 0, if the bidder bid on one lot total but did not win it
       4. should return bidderName, won = 0, cost = 0 if the bidder is winning but the auction is still open
       5. should return bidderName, won = 0, cost = 0 if the bidder is losing and the auction is still open
       6. should return bidderName, won = 2, cost = total of both winning bids if bidder wins two lots where the auctions have both closed
     */
    @Test
    void testFeesOwed_emptyHashMap() {
        HashMap<Integer, Lot> lotList = new HashMap<>();
        Bidder noLotsBidder = new Bidder(lotList, "Jamie", 2);
        assertEquals("Jamie\t0\t0\n", noLotsBidder.feesOwed(), "method feesOwed did not return bidder's name");
    }

    /*
       2. should return winningBidder, won = 1, cost = the current bid of the lot if there is one lot that was won by the bidder
     */
    @Test
    void testFeesOwed_oneLotWon() {
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder winningBidder = new Bidder(lotList, "Pearl", 4);
        Bidder losingBidder = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<Bidder>();

        allBidders.add(winningBidder);
        allBidders.add(losingBidder);

        String auctionName = "Auction1";

        // initializing auction so that winningBidder should be the winner of the lot
        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 1, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        newLot.placeBid(5 ,4);
        newLot.placeBid(10, 5);
        newLot.placeBid(15, 4);
        newAuction.closeAuction();

        assertEquals("Pearl\t1\t15\n", winningBidder.feesOwed(), "feesOwed did not return the correct string");
    }

    /*
       3. should return losingBidder, won = 0, cost = 0, if the bidder bid on one lot total but did not win it
     */
    @Test
    void testFeesOwed_bidderDidNotWin() {
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder winningBidder = new Bidder(lotList, "Pearl", 4);
        Bidder losingBidder = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<Bidder>();

        allBidders.add(winningBidder);
        allBidders.add(losingBidder);

        String auctionName = "Auction1";

        // initializing auction so that winningBidder should be the winner of the lot
        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 1, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        newLot.placeBid(5 ,4);
        newLot.placeBid(10, 5);
        newLot.placeBid(15, 4);
        newAuction.closeAuction();

        assertEquals("Jake\t0\t0\n", losingBidder.feesOwed(), "feesOwed did not return the correct string");
    }

    /*
       4. should return winningBidder, won = 0, cost = 0 if the bidder is winning but the auction is still open
     */
    @Test
    void testFeesOwed_bidderWinningAuctionOpen() {
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder winningBidder = new Bidder(lotList, "Pearl", 4);
        Bidder losingBidder = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<Bidder>();

        allBidders.add(winningBidder);
        allBidders.add(losingBidder);

        String auctionName = "Auction1";

        // initializing auction so that winningBidder should be the winner of the lot
        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 1, 5);
        newAuction.openAuction();
        Lot newLot = new Lot(newAuction, allBidders, 1);
        newLot.placeBid(5 ,4);
        newLot.placeBid(10, 5);
        newLot.placeBid(15, 4);

        assertEquals("Pearl\t0\t0\n", winningBidder.feesOwed(), "feesOwed did not return the correct string");
    }

    /*
       5. should return losingBidder, won = 0, cost = 0 if the bidder is losing and the auction is still open
     */
    @Test
    void testFeesOwed_bidderLosingAuctionOpen() {
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder winningBidder = new Bidder(lotList, "Pearl", 4);
        Bidder losingBidder = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<Bidder>();

        allBidders.add(winningBidder);
        allBidders.add(losingBidder);

        String auctionName = "Auction1";

        // initializing auction so that winningBidder should be the winner of the lot
        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 1, 5);
        newAuction.openAuction();
        Lot newLot = new Lot(newAuction, allBidders, 1);
        newLot.placeBid(5 ,4);
        newLot.placeBid(10, 5);
        newLot.placeBid(15, 4);

        assertEquals("Jake\t0\t0\n", losingBidder.feesOwed(), "feesOwed did not return the correct string");
    }
    /*
        6. should return bidderName, won = 2, cost = total of both winning bids if bidder wins two lots
     */
    @Test
    void testFeesOwed_bidderWinsTwice() {
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder winningBidder = new Bidder(lotList, "Pearl", 4);
        Bidder losingBidder = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<Bidder>();

        allBidders.add(winningBidder);
        allBidders.add(losingBidder);

        String firstAuctionName = "Auction1";
        String secondAuctionName = "Auction2";

        // initializing auction so that winningBidder should be the winner of the lot
        Auction firstAuction = new Auction(lotList, allBidders, firstAuctionName, 1, 1, 5);
        firstAuction.openAuction();

        Lot firstLot = new Lot(firstAuction, allBidders, 1);
        firstLot.placeBid(5 ,4);
        firstLot.placeBid(10, 5);
        firstLot.placeBid(15, 4);
        firstAuction.closeAuction();

        Auction secondAuction = new Auction(lotList, allBidders, secondAuctionName, 2, 2, 20);
        secondAuction.openAuction();

        Lot secondLot = new Lot(firstAuction, allBidders, 2);
        secondLot.placeBid(20 ,4);
        secondLot.placeBid(40, 5);
        secondLot.placeBid(60, 4);
        secondAuction.closeAuction();

        assertEquals("Pearl\t2\t75\n", winningBidder.feesOwed(), "feesOwed did not return the correct string");
    }
    /*
    example test
    */
    @Test
    void testBidderIsReady_invalid() {
        HashMap<Integer, Lot> lotList = new HashMap<>();

        // Ask to create an invalid bidder and see if the system will proceed

        Bidder badBidder = new Bidder(lotList, null, 5);
        assertFalse( badBidder.bidderIsReady(), "invalid bidder appears ready" );
    }


}