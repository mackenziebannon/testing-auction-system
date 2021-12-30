import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.ArrayList;

class LotTest {
    /* add whitebox tests
       Lot()
       1. should not initialize new lot if the lot number is 0 or less and there has not been an auction created
       2. should not initialize new lot if there has not been an auction created
       3. should not initialize new lot if the lot number is 0 or less
       4. should initialize new lot if the lot number is greater than 0 and there is an auction initialized
        */

    /*
       1. should not initialize new lot if the lot number is 0 or less and there has not been an auction created
     */
    @Test
    void testLot_NullAuctionInvalidLotNumber(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        Lot newLot = new Lot(null, allBidders, -10);
        lotList.put(1, newLot);

        // doing this because there is no method in Lot.java to check whether a lot is valid/invalid (i.e. a lotIsTrue or lotIsReady)
        assertNull(newLot, "invalid lot was created");
    }

    /*
       2. should not initialize new lot if there has not been an auction created
     */
    @Test
    void testLot_invalidLotNullAuction(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        Lot newLot = new Lot(null, allBidders, -10);
        lotList.put(1, newLot);

        assertNull(newLot, "invalid lot was created");
    }

    /*
        3. should not initialize new lot if the lot number is 0 or less
     */
    @Test
    void testLot_invalidLotNumber(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";
        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, -10);
        lotList.put(1, newLot);

        assertNull(newLot, "invalid lot was created");
    }

    /*
        4. should initialize new lot if the lot number is greater than 0 and there is an auction initialized
     */
    @Test
    void testLot_validLot() {
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        assertNotNull(newLot, "valid lot was not created");
    }

    /* add whitebox tests
       int currentBid()
       1. should return 0 if there have been no bids
       2. should return top bid if there have been multiple bids, all valid
       3. should return 0 if there was one bid placed by an invalid bidder
       4. should return 0 if there was one invalid bid placed by a valid bidder
       5. should return highest valid bid placed if the "highest" bid placed was placed by an invalid bidder
       6. should return highest valid bid placed if the "highest" bid placed did not use the set minimum increment for the auction
     */
    /*
       1. should return 0 if there have been no bids
     */
    @Test
    void testCurrentBid_noBids(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        assertEquals(0, newLot.currentBid(), "currentBid did not return the correct value");
    }
    /*
    2. should return top bid if there have been multiple bids, all valid
     */
    @Test
    void testCurrentBid_getTopBidAllInputsValid(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        newLot.placeBid(5 ,4);
        newLot.placeBid(10, 5);
        newLot.placeBid(15, 4);
        assertEquals(15, newLot.currentBid(), "currentBid did not return the correct value");
    }
    /*
        3. should return 0 if there was one bid placed by an invalid bidder
     */
    @Test
    void testCurrentBid_bidMadeByInvalidBidder(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder invalidBidder = new Bidder(lotList, "Pearl", -1);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(invalidBidder);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        newLot.placeBid(5 ,-1);
        assertEquals(0, newLot.currentBid(), "currentBid did not return the correct value");
    }
    /*
        4. should return 0 if there was one invalid bid placed by a valid bidder
        Error found: returns an invalid bid.
     */
    @Test
    void testCurrentBid_invalidBidByValidBidder() {
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder invalidBidder = new Bidder(lotList, "Pearl", 1);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(invalidBidder);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 1, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        newLot.placeBid(2 ,1);
        assertEquals(0, newLot.currentBid(), "currentBid did not return the correct value");
    }
    /*
       5. should return highest valid bid placed if the "highest" bid placed was placed by an invalid bidder
     */
    @Test
    void testCurrentBid_highestBidMadeByInvalidBidder() {
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", -1);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        newLot.placeBid(5 ,-1);
        newLot.placeBid(10, 5);
        newLot.placeBid(15, -1);
        assertEquals(10, newLot.currentBid(), "currentBid did not return the correct value");
    }
    /*
    6. should return highest valid bid placed if the "highest" bid placed did not use the set minimum increment for the auction
    error found: bids that are invalidly incremented are still counted as a highest bid and thus the current bid through the current bid method.
     */
    @Test
    void testCurrentBid_highestBidInvalidlyIncremented() {
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        newLot.placeBid(5 ,4);
        newLot.placeBid(10, 5);
        newLot.placeBid(12, 4);
        assertEquals(10, newLot.currentBid(), "currentBid did not return the correct value");
    }

    /* create whitebox tests
       winningBidder()
       1. should return 0 if there have been no bids
       2. should return top bidder's Id if there have been multiple bids, all valid
       3. should return 0 if there was one bid placed by an invalid bidder
       4. should return 0 if there was one invalid bid placed by a valid bidder
       5. should return highest valid bidder's Id if the "highest" bid placed was placed by an invalid bidder
       6. should return highest valid bidder's Id if the "highest" bid placed did not use the set minimum increment for the auction
     */
    @Test
    void testWinningBidder_noBidsPlaced(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        assertEquals(0, newLot.winningBidder(), "winningBidder did not return the correct value");
    }

    @Test
    void testWinningBidder_allBidsValid(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        newLot.placeBid(5 ,4);
        newLot.placeBid(10, 5);
        newLot.placeBid(15, 4);
        assertEquals(4, newLot.winningBidder(), "winningBidder did not return the correct value");
    }

    @Test
    void testWinningBidder_validBidByInvalidBidder() {
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder invalidBidder = new Bidder(lotList, "Pearl", -1);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(invalidBidder);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        newLot.placeBid(5 ,-1);
        assertEquals(0, newLot.winningBidder(), "winningBidder did not return the correct value");
    }

    @Test
    void testWinningBidder_invalidBidByValidBidder() {
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder invalidBidder = new Bidder(lotList, "Pearl", 1);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(invalidBidder);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 1, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        newLot.placeBid(2 ,1);
        assertEquals(0, newLot.winningBidder(), "winningBidder did not return the correct value");
    }

    @Test
    void testWinningBidder_highestBidByInvalidBidder(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", -1);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        newLot.placeBid(5 ,-1);
        newLot.placeBid(10, 5);
        newLot.placeBid(15, -1);
        assertEquals(5, newLot.winningBidder(), "winningBidder did not return the correct value");
    }

    /*
        error found: bids that are invalidly incremented are still counted as a highest bid and thus the bidder wins through the winningbidder method.
     */
    @Test
    void testWinningBidder_highestBidInvalid() {
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        newLot.placeBid(5 ,4);
        newLot.placeBid(10, 5);
        newLot.placeBid(12, 4);
        assertEquals(5, newLot.winningBidder(), "winningBidder did not return the correct value");
    }

    /* create whitebox tests
       winningBidString()
       1. should return string with lot number, topBid = 0, and winningBidder = 0 if there have been no bids
       2. should return string containing winning bid info if there have been multiple bids, all valid
       3. should return string containing lot number, topBid = 0, and winningBidder = 0 if there was one bid placed by an invalid bidder
       4. should return string containing lot number, topBid = 0, and winningBidder = 0 if there was one invalid bid placed by a valid bidder
       5. should return string representing winning bid's info if the "highest" bid placed was placed by an invalid bidder
       6. should return string representing winning bid's info if the "highest" bid placed did not use the set minimum increment for the auction
     */
    @Test
    void testWinningBidString_noBidsPlaced(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        assertEquals("1\t0\t0\n", newLot.winningBidString(), "winningBidString did not return the correct value");
    }

    @Test
    void testWinningBidString_allBidsValid(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        newLot.placeBid(5 ,4);
        newLot.placeBid(10, 5);
        newLot.placeBid(15, 4);
        assertEquals("1\t15\t4\n", newLot.winningBidString(), "winningBidString did not return the correct value");
    }
    @Test
    void testWinningBidString_validBidByInvalidBidder(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder invalidBidder = new Bidder(lotList, "Pearl", -1);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(invalidBidder);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        newLot.placeBid(5 ,-1);
        assertEquals("1\t0\t0\n", newLot.winningBidString(), "winningBidString did not return the correct value");
    }
    @Test
    void testWinningBidString_invalidBidByValidBidder(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder invalidBidder = new Bidder(lotList, "Pearl", 4);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(invalidBidder);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        newLot.placeBid(2 ,4);
        assertEquals("1\t0\t0\n", newLot.winningBidString(), "winningBidString did not return the correct value");
    }
    @Test
    void testWinningBidString_highestBidByInvalidBidder(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", -1);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        newLot.placeBid(5 ,-1);
        newLot.placeBid(10, 5);
        newLot.placeBid(15, -1);
        assertEquals("1\t10\t5\n", newLot.winningBidString(), "winningBidString did not return the correct value");
    }
    @Test
    void testWinningBidString_highestBidInvalid(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        newLot.placeBid(5 ,4);
        newLot.placeBid(10, 5);
        newLot.placeBid(12, 4);
        assertEquals("1\t10\t5\n", newLot.winningBidString(), "winningBidString did not return the correct value");
    }

    /* create whitebox tests
        placeBid()
        1. should return LotNotAccepting if bidder is not valid -> logically, this should return bidNotAcceptable, however code states LotNotAccepting
        2. should return BidNotAcceptable if the bidder is valid but the bid is less than 0
        3. should return BidAcceptableNotWinning if the bid is less than the current highest valid bid
        4. should return BidWinning but not increment bid if the bidder is valid and the bidder is already winning bidder and their bid is greater than the current top bid
        5. should return BidWinning if the bidder was not the highest bidder previously, and their bid is greater than the previous winning bid
     */
    @Test
    void testPlaceBid_bidderNotValid(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder invalidBidder = new Bidder(lotList, "Pearl", -1);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(invalidBidder);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        int bidPlaced = newLot.placeBid(5 ,-1);
        assertEquals(0, bidPlaced, "placeBid did not return the correct value");
    }

    @Test
    void testPlaceBid_invalidBidValidBidder() {
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder invalidBidder = new Bidder(lotList, "Pearl", 4);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(invalidBidder);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        int bidPlaced = newLot.placeBid(2 ,4);
        assertEquals(1, bidPlaced, "placeBid did not return the correct value");
    }

    @Test
    void testPlaceBid_bidLessThanWinningBid(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        newLot.placeBid(5 ,4);
        newLot.placeBid(10, 5);
        newLot.placeBid(15, 4);
        int bidPlacedForTest = newLot.placeBid(10, 5);
        assertEquals(2, bidPlacedForTest, "placeBid did not return the correct value");
    }

    @Test
    void testPlaceBid_bidPlacedByCurrentWinner(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        newLot.placeBid(5 ,4);
        newLot.placeBid(10, 5);
        newLot.placeBid(15, 4);
        int bidPlacedForTest = newLot.placeBid(20, 4);
        assertEquals(3, bidPlacedForTest, "placeBid did not return the correct value");
    }

    @Test
    void testPlaceBid_newHighestBidder(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();

        Lot newLot = new Lot(newAuction, allBidders, 1);
        lotList.put(1, newLot);

        newLot.placeBid(5 ,4);
        newLot.placeBid(10, 5);
        int bidPlacedForTest = newLot.placeBid(15, 4);
        assertEquals(3, bidPlacedForTest, "placeBid did not return the correct value");
    }
}