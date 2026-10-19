package de.ibb.collections.todo.simplelist;

/**
 * Einfacher Test-Main für SimpleArrayList (als Alternative zu Unit-Tests).
 *
 * Führt einige grundlegende Operationen aus und gibt das Ergebnis auf der Konsole aus.
 */
public class SimpleListTestMain {

    public static void main(String[] args) {
        SimpleListTestMain tester = new SimpleListTestMain();
        tester.runAllTests();
    }

    private void runAllTests() {
        System.out.println("== SimpleArrayList – manuelle Tests ==");
        testAddAndSize();
        testGet();
        testRemove();
        testContains();
        System.out.println("== Tests abgeschlossen ==");
    }

    private void testAddAndSize() {
        SimpleList<String> list = new SimpleArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add("Item-" + i);
        }
        System.out.println("testAddAndSize: size == 15 ? " + (list.size() == 15));
    }

    private void testGet() {
        SimpleList<String> list = new SimpleArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        boolean ok = "A".equals(list.get(0)) && "C".equals(list.get(2));
        System.out.println("testGet: erste und dritte Position korrekt? " + ok);
        try {
            list.get(3);
            System.out.println("testGet: FEHLER – Erwartete IndexOutOfBoundsException wurde nicht geworfen.");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("testGet: IndexOutOfBoundsException wie erwartet geworfen.");
        }
    }

    private void testRemove() {
        SimpleList<String> list = new SimpleArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        String removed = list.remove(1);
        boolean ok = "B".equals(removed) && list.size() == 2 && "C".equals(list.get(1));
        System.out.println("testRemove: Element und Verschiebung korrekt? " + ok);
    }

    private void testContains() {
        SimpleList<String> list = new SimpleArrayList<>();
        list.add("X");
        list.add("Y");
        list.add(null);
        boolean ok = list.contains("X") && list.contains(null) && !list.contains("Z");
        System.out.println("testContains: enthält X und null, nicht Z? " + ok);
    }
}

