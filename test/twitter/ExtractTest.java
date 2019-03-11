/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

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
    private static final Tweet tweet3 = new Tweet(3, "hamaad", "expected @Nadir", d3);
    private static final Tweet tweet4 = new Tweet(3, "inshal", "@sub_pagal_hain", d4);
    private static final Tweet tweet5 = new Tweet(3, "gorgeyesmile", "tweet5", d5);
    
    
    
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
//        assertEquals("", actual);
    }
    

    @Test
    public void testGetTimespan_2() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5));
        
        assertEquals("expected start", d4, timespan.getStart());
        assertEquals("expected end", d3, timespan.getEnd());
//        assertEquals("", actual);
    }

    
  
    // Here d3 has  minimum time span
    //  Here d5 has maximum time span   
    @Test
    public void testGetTimespan_3() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet3, tweet5));
        
        assertEquals("expected start", d5, timespan.getStart());
        assertEquals("expected end", d3, timespan.getEnd());
//        assertEquals("", actual);
    }

    
    
    @Test
    public void testGetMentionedUsersNoMention_1() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }

    
    
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet3));
        assertTrue(mentionedUsers.contains("Nadir"));
    }



}
