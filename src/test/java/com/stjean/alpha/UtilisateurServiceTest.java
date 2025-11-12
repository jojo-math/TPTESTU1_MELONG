package com.stjean.alpha;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.*;
import java.util.List;

import static org.junit.Assert.*;

public class UtilisateurServiceTest {

    UtilisateurService service;

    @BeforeEach
    void setUp() {
        service = new UtilisateurService();
        UtilisateurService.users.clear(); // isolation FIRST
    }

    @Test
    public void ajouter_refuse_email_invalide() {
        var u = new Utilisateur(1,"Alice",22,"bad-email","", "Yaoundé", 1000);
        assertThrows(UtilisateurService.EmailInvalidException.class, () -> service.ajouter(u));
    }

    @Test
    public void ajouter_ok_lister_afficher() {
        var u = new Utilisateur(1,"Bob",25,"bob@mail.com","", "Douala", 5000);
        service.ajouter(u);
        List<Utilisateur> all = service.lister();
        assertEquals(1, all.size());
        assertTrue(service.afficher(1).isPresent());
    }

    @Test
    public void supprimer_inexistant_lance_exception() {
        assertThrows(UtilisateurService.SuppressionInvalidException.class, () -> service.supprimer(999));
    }

    @Test
    public void analyseSoldeGeneral_positif_ok() {
        service.ajouter(new Utilisateur(1,"A",20,"a@x.com","", "Bafoussam", 1000));
        service.ajouter(new Utilisateur(2,"B",21,"b@x.com","", "Garoua", 2000));
        assertEquals(3000, service.analyseSoldeGeneral());
    }

    @Test
    public void analyseSoldeGeneral_negatif_declenche_exception() {
        service.ajouter(new Utilisateur(1,"A",20,"a@x.com","", "Bamenda", -50));
        assertThrows(UtilisateurService.NegativeGeneralBalanceException.class, () -> service.analyseSoldeGeneral());
    }

    @Test
    public void utilisateurLePlusRiche_ok() {
        service.ajouter(new Utilisateur(1,"A",20,"a@x.com","", "Kribi", 100));
        service.ajouter(new Utilisateur(2,"B",20,"b@x.com","", "Limbe", 500));
        var richest = service.utilisateurLePlusRiche();
        assertTrue(richest.isPresent());
        assertEquals(2, richest.get().getId());
    }

}
