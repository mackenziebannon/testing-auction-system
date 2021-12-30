import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.ArrayList;

class AuctionTest {
    /* add whitebox tests
    Auction()
    1. should not create a new auction (auctionready is false) if first lot number is invalid
    2. should not create a new auction (auctionready is false) if second lot number is invalid
    3. should not create a new auction (auctionready is false) if the auction name is null
    4. should not create a new auction (auctionready is false) if the auction name length is less than or equal to 0
    5. should not create a new auction (auctionready is false) if the minimum bid increment is invalid
    6. should create a new auction (auctionready is true) if all inputs are valid
     */
    @Test
    void testAuction_invalidFirstLot(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction invalidAuction = new Auction(lotList, allBidders, auctionName, -1, 2, 5);

        assertFalse(invalidAuction.auctionIsReady(), "auctionIsReady returned true on invalid auction");
    }

    @Test
    void testAuction_invalidSecondLot(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction invalidAuction = new Auction(lotList, allBidders, auctionName, 1, -2, 5);

        assertFalse(invalidAuction.auctionIsReady(), "auctionIsReady returned true on invalid auction");
    }

    @Test
    void testAuction_nullAuctionName(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        Auction invalidAuction = new Auction(lotList, allBidders, null, 1, 2, 5);

        assertFalse(invalidAuction.auctionIsReady(), "auctionIsReady returned true on invalid auction");
    }

    @Test
    void testAuction_invalidBidIncrease(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction invalidAuction = new Auction(lotList, allBidders, auctionName, 1, 2, -5);

        assertFalse(invalidAuction.auctionIsReady(), "auctionIsReady returned true on invalid auction");
    }

    @Test
    void testAuction_validAuction(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction validAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);

        assertTrue(validAuction.auctionIsReady(), "auctionIsReady returned false on a valid auction");
    }

    /* add whitebox tests
    openAuction()
    1. should return true (auction has been opened) if the auction was new
    2. should return false if the auction was already open
    3. should return false if the auction was closed
     */
    @Test
    void testOpenAuction_newAuction() {
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        assertTrue(newAuction.openAuction(), "openAuction did not return true after opening the new auction");
    }

    @Test
    void testOpenAuction_auctionAlreadyOpen(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();
        assertFalse(newAuction.openAuction(), "openAuction returned true even though auction was already open");
    }

    @Test
    void testOpenAuction_auctionClosed(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();
        newAuction.closeAuction();
        assertFalse(newAuction.openAuction(), "openAuction returned true even though auction was closed, therefore unable to reopen");
    }

    /* create whitebox tests
    closeAuction()
    1. should return true if open auction was successfuly closed
    2. should return false if auction was previously closed
    3. should return false if the auction is new (has not yet been opened)
     */
    @Test
    void testCloseAuction_successfullyClosed(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();
        assertTrue(newAuction.closeAuction(), "closeAuction returned false, should have closed and returned true");
    }

    @Test
    void testCloseAuction_previouslyClosed(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        newAuction.openAuction();
        newAuction.closeAuction();
        assertFalse(newAuction.closeAuction(), "closeAuction returned true even though auction was closed, therefore unable to reclose");
    }

    @Test
    void testCloseAuction_newAuction(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction newAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);
        assertFalse(newAuction.closeAuction(), "closeAuction closed a new auction");
    }

    /* create whitebox tests
    getMinIncrement()
    1. should return the minimum increment if the auction is valid
    2. should return 0 if the auction is invalid
     */
    @Test
    void testGetMinIncrement_validAuction(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction validAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);

        assertEquals(5, validAuction.getMinIncrement(), "getMinIncrement did not return the correct value");
    }

    @Test
    void testGetMinIncrement_invalidAuction(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction invalidAuction = new Auction(lotList, allBidders, auctionName, 1, 2, -2);

        assertEquals(0, invalidAuction.getMinIncrement(), "getMinIncrement did not return the correct value");
    }

    /* create whitebox tests
    auctionIsReady()
    1. should return true if lot number is greater than 0, the auction name is not null, and it's length is greater than 0, and the minimum bid increment is greater than 0
    2. should return false if the first lot number is less than 0
    3. should return false if the second lot number is less than 0
    4. should return false if the auction name is null
    5. should return false if the auction name length is less than 0
    6. should return false if the minimum bid increment is less than or equal to 0
     */
    @Test
    void testAuctionIsReady_validAuction(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction validAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);

        assertTrue(validAuction.auctionIsReady(), "valid auction is not ready");
    }

    @Test
    void testAuctionIsReady_invalidFirstLotNumber(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction invalidAuction = new Auction(lotList, allBidders, auctionName, -1, 2, 5);

        assertFalse(invalidAuction.auctionIsReady(), "invalid auction is ready");
    }

    @Test
    void testAuctionIsReady_invalidSecondLotNumber(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction invalidAuction = new Auction(lotList, allBidders, auctionName, 1, -2, 5);

        assertFalse(invalidAuction.auctionIsReady(), "invalid auction is ready");
    }

    @Test
    void testAuctionIsReady_nullAuctionName(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);


        Auction invalidAuction = new Auction(lotList, allBidders, null, 1, 2, 5);

        assertFalse(invalidAuction.auctionIsReady(), "invalid auction is ready");
    }

    @Test
    void testAuctionIsReady_invalidAuctionName(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "";

        Auction invalidAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);

        assertFalse(invalidAuction.auctionIsReady(), "invalid auction is ready");
    }

    @Test
    void testAuctionIsReady_invalidBidIncrement(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "auction1";

        Auction invalidAuction = new Auction(lotList, allBidders, auctionName, 1, 2, -2);

        assertFalse(invalidAuction.auctionIsReady(), "invalid auction is ready");
    }

    /* create whitebox tests
    getMinLot()
    1. should return 0 if the auction was invalid
    2. should return 0 if the starting lot was invalid
    2. should return minimum lot number if lots have been added
     */
    @Test
    void testGetMinLot_invalidAuction(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction invalidAuction = new Auction(lotList, allBidders, auctionName, 1, 2, -1);

        assertEquals(0, invalidAuction.getMinLot(), "getMinLot did not return the correct value");
    }

    @Test
    void testGetMinLot_invalidStartingLot(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction invalidAuction = new Auction(lotList, allBidders, auctionName, -1, 2, 5);

        assertEquals(0, invalidAuction.getMinLot(), "getMinLot did not return the correct value");
    }

    @Test
    void testGetMinLot_validAuction(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction validAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);

        assertEquals(1, validAuction.getMinLot(), "getMinLot did not return the correct value");
    }

    /* create whitebox tests
    getMaxLot()
    1. should return 0 if the auction is invalid
    2. should return 0 if the ending lot is invalid
    3. should return the ending lot if the auction is valid
     */
    @Test
    void testGetMaxLot_invalidAuction(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction invalidAuction = new Auction(lotList, allBidders, auctionName, 1, 2, -1);

        assertEquals(0, invalidAuction.getMaxLot(), "getMaxLot did not return the correct value");
    }

    @Test
    void testGetMaxLot_invalidMaxLotNumber(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction invalidAuction = new Auction(lotList, allBidders, auctionName, 1, -2, 2);

        assertEquals(0, invalidAuction.getMaxLot(), "getMaxLot did not return the correct value");
    }

    @Test
    void testGetMaxLot_validAuction(){
        HashMap<Integer, Lot> lotList = new HashMap<>();

        Bidder bidderOne = new Bidder(lotList, "Pearl", 4);
        Bidder bidderTwo = new Bidder(lotList, "Jake", 5);

        ArrayList<Bidder> allBidders = new ArrayList<>();

        allBidders.add(bidderOne);
        allBidders.add(bidderTwo);

        String auctionName = "Auction1";

        Auction validAuction = new Auction(lotList, allBidders, auctionName, 1, 2, 5);

        assertEquals(2, validAuction.getMaxLot(), "getMaxLot did not return the correct value");
    }
}