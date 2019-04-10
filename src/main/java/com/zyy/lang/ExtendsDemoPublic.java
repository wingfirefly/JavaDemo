package com.zyy.lang;

public class ExtendsDemoPublic {
}

interface ExtendsDemoPublicA {

    interface Entry {
        String getKey();
    }
}

class ExtendsDemoPublicB implements ExtendsDemoPublicA {

    class Node implements /* ExtendsDemoPublicA.*/Entry {
        @Override
        public String getKey() {
            return null;
        }
    }
}

class ExtendsDemoPublicC {

    interface Entry {
        String getKey();
    }
}

class ExtendsDemoPublicD extends ExtendsDemoPublicC {

    public class Node implements ExtendsDemoPublicC.Entry {
        @Override
        public String getKey() {
            return null;
        }
    }
}