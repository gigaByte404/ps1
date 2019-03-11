/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.time.Instant;
import java.util.Arrays;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-18T11:00:00Z");
    private static final Instant d4 = Instant.parse("2016-02-15T11:00:00Z");
    private static final Instant d5 = Instant.parse("2016-02-16T11:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "hamaad", "expected @Nadir and @Inshal as followers", d3);
    private static final Tweet tweet4 = new Tweet(3, "inshal", "@lol", d4);
    private static final Tweet tweet5 = new Tweet(3, "gorgeyesmile", "tweet5", d5);

	
	
	
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty_1() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    
    @Test
    public void testGuessFollowsGraph_2() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet3, tweet5));
 
        assertTrue(followsGraph.containsKey("hamaad"));
    }
    
    
    //checking either user is present in the map for corresponding to the searched user/not.
    @Test
    public void testGuessFollowsGraph_3() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet3, tweet5));
 
        assertFalse(followsGraph.containsKey("hamaadlatif"));
    }

    //This test case is checking either the the person has mentioned user as his follower or not.
    @Test
    public void testGuessFollowsGraph_4() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet3, tweet5));
//        System.out.println(followsGraph.get("hamaad"));
        assertTrue(followsGraph.get("hamaad").contains("@nadir"));
    }


  //This test case is checking total followers of mentioned user
    @Test
    public void testGuessFollowsGraph_5() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet3, tweet5));
//        System.out.println(followsGraph);
        assertEquals(2,followsGraph.get("hamaad").size());
    }

    

    
    @Test
    public void testInfluencersEmpty_1() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }

    @Test
    public void testInfluencers_2() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet3, tweet4, tweet5));
        List<String> influencersExpected = SocialNetwork.influencers(followsGraph);
        List<String> influencersActual = new ArrayList<String>();
        influencersActual.add("hamaad");
        influencersActual.add("inshal");
        influencersActual.add("gorgeyesmile");
        assertEquals("Expected Output",influencersActual,influencersExpected);
    
    }

    //Checking list size that either as expected or not
    @Test
    public void testInfluencers_3() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet3, tweet4, tweet5));
        List<String> influencersExpected = SocialNetwork.influencers(followsGraph);
        assertEquals(3,influencersExpected.size());
    
    }



    //Checking list's 2nd element as either its expected or not
    @Test
    public void testInfluencers_4() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet3, tweet4, tweet5));
        List<String> influencersExpected = SocialNetwork.influencers(followsGraph);
        assertTrue(influencersExpected.get(1).equals("inshal"));
    }
    
    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
