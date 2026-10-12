package de.ibb.jpa;

import de.ibb.jpa.entity.Person;
import jakarta.persistence.*;

/**
 * EntityLifecycleDemo - Die 4 Zustände einer Entity.
 * 
 * Tag 8 des Kurses "Java Anwendungsentwicklung"
 * Java Fleet Systems Consulting - java-developer.online
 * 
 * Entity-Zustände:
 * 1. NEW (Transient) - Frisch erstellt, JPA kennt es nicht
 * 2. MANAGED - JPA trackt Änderungen
 * 3. DETACHED - War managed, jetzt nicht mehr
 * 4. REMOVED - Zum Löschen markiert
 * 
 * @author Franz-Martin (CTO, Java Fleet Systems Consulting)
 */
public class EntityLifecycleDemo {
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  🔄 Entity Lifecycle - Die 4 Zustände");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.ibb");
        EntityManager em = emf.createEntityManager();
        
        try {
            // ══════════════════════════════════════════════════════════════
            // 1. NEW (Transient)
            // ══════════════════════════════════════════════════════════════
            
            System.out.println("┌─────────────────────────────────────────────────────────┐");
            System.out.println("│  1️⃣  NEW (Transient)                                    │");
            System.out.println("└─────────────────────────────────────────────────────────┘");
            
            Person lisa = new Person("Lisa Schmidt", 34, "lisa@example.com");
            
            System.out.println("   Person erstellt: " + lisa);
            System.out.println("   ID: " + lisa.getId() + " (null - noch nicht in DB)");
            System.out.println("   contains(lisa): " + em.contains(lisa));
            System.out.println("   → JPA weiß NICHTS über dieses Objekt!");
            System.out.println();
            
            // ══════════════════════════════════════════════════════════════
            // 2. MANAGED
            // ══════════════════════════════════════════════════════════════
            
            System.out.println("┌─────────────────────────────────────────────────────────┐");
            System.out.println("│  2️⃣  MANAGED                                            │");
            System.out.println("└─────────────────────────────────────────────────────────┘");
            
            em.getTransaction().begin();
            em.persist(lisa);  // NEW → MANAGED
            
            System.out.println("   Nach persist(lisa):");
            System.out.println("   contains(lisa): " + em.contains(lisa));
            System.out.println("   → JPA trackt jetzt alle Änderungen!");
            System.out.println();
            
            // Änderungen werden automatisch getrackt
            lisa.setEmail("lisa.neu@example.com");
            System.out.println("   Email geändert (ohne explizites Update!)");
            
            em.getTransaction().commit();
            System.out.println("   Nach commit: ID = " + lisa.getId());
            System.out.println("   → INSERT und UPDATE wurden automatisch ausgeführt!");
            System.out.println();
            
            // ══════════════════════════════════════════════════════════════
            // 3. DETACHED
            // ══════════════════════════════════════════════════════════════
            
            System.out.println("┌─────────────────────────────────────────────────────────┐");
            System.out.println("│  3️⃣  DETACHED                                           │");
            System.out.println("└─────────────────────────────────────────────────────────┘");
            
            Long lisaId = lisa.getId();
            
            // detach() entfernt die Entity aus dem EntityManager
            em.detach(lisa);  // MANAGED → DETACHED
            
            System.out.println("   Nach detach(lisa):");
            System.out.println("   contains(lisa): " + em.contains(lisa));
            System.out.println("   → JPA trackt Änderungen NICHT mehr!");
            System.out.println();
            
            // Änderungen werden NICHT gespeichert!
            lisa.setAlter(99);
            System.out.println("   Alter auf 99 geändert (detached!)");
            
            em.getTransaction().begin();
            em.getTransaction().commit();
            
            // Prüfen - in der DB ist noch der alte Wert!
            Person ausDb = em.find(Person.class, lisaId);
            System.out.println("   In DB: Alter = " + ausDb.getAlter() + " (NICHT 99!)");
            System.out.println("   → Änderungen an detached Entities werden ignoriert!");
            System.out.println();
            
            // merge() macht detached wieder managed
            System.out.println("   merge() kann detached wieder managed machen:");
            em.getTransaction().begin();
            lisa.setAlter(35);
            Person merged = em.merge(lisa);  // DETACHED → MANAGED (neues Objekt!)
            em.getTransaction().commit();
            
            System.out.println("   Nach merge: Alter = " + merged.getAlter());
            System.out.println("   ⚠️ Achtung: merge() gibt ein NEUES Objekt zurück!");
            System.out.println("   lisa == merged: " + (lisa == merged));
            System.out.println();
            
            // ══════════════════════════════════════════════════════════════
            // 4. REMOVED
            // ══════════════════════════════════════════════════════════════
            
            System.out.println("┌─────────────────────────────────────────────────────────┐");
            System.out.println("│  4️⃣  REMOVED                                            │");
            System.out.println("└─────────────────────────────────────────────────────────┘");
            
            em.getTransaction().begin();
            
            // Zuerst die aktuelle Version laden
            Person zuLoeschen = em.find(Person.class, lisaId);
            System.out.println("   Vor remove: contains = " + em.contains(zuLoeschen));
            
            em.remove(zuLoeschen);  // MANAGED → REMOVED
            
            System.out.println("   Nach remove: contains = " + em.contains(zuLoeschen));
            System.out.println("   → Entity ist zum Löschen markiert");
            
            em.getTransaction().commit();
            System.out.println("   Nach commit: DELETE wurde ausgeführt!");
            
            Person nochDa = em.find(Person.class, lisaId);
            System.out.println("   Nochmal suchen: " + nochDa);
            
        } catch (RuntimeException ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
            emf.close();
        }
        
        System.out.println();
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  📊 Zusammenfassung:");
        System.out.println("     NEW      → persist() → MANAGED");
        System.out.println("     MANAGED  → detach()  → DETACHED");
        System.out.println("     DETACHED → merge()   → MANAGED");
        System.out.println("     MANAGED  → remove()  → REMOVED");
        System.out.println("═══════════════════════════════════════════════════════════");
    }
}
