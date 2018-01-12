package com.ramazeta.chordlib.models;

import com.ramazeta.chordlib.interfaces.Transposable;

/**
 * Created by rama on 13/01/18.
 */

public class Note implements Transposable<Note>, Comparable<Note> {
    private int pitch;

    private static final String[] NAMES = new String[] { "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B" };

    private Note(int pitch) {
        this.pitch = pitch;
    }

    public static Note lookup(String name) {
        int p = 0;
        for (String s : NAMES) {
            if (s.toLowerCase().equals(name.toLowerCase())) {
                return new Note(p);
            }
            p++;
        }
        return null;
    }

    private int getNormalizedPitch() {
        int i = pitch % NAMES.length;
        // Java uses the other modulo operator (not the one used by python), so
        // we have to handle negative values ourself
        while (i < 0) {
            i += NAMES.length;
        }
        return i;
    }

    public String getName() {
        int i = getNormalizedPitch();
        return NAMES[i];
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass().equals(getClass())) {
            // now we are on common ground
            Note other = (Note) o;
            return getNormalizedPitch() == other.getNormalizedPitch();
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return pitch;
    }

    public Note transpose(int steps) {
        return new Note(pitch + steps);
    }

    public int compareTo(Note n) {
        return new Integer(pitch).compareTo(n.pitch);
    }

    public int getHalfStepsTo(Note n) {
        return n.pitch - pitch;
    }

}

