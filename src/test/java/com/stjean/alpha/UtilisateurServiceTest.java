package com.stjean.alpha;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.*;
import java.util.List;

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
        Assert.assertThrows(UtilisateurService.EmailInvalidException.class, () -> service.ajouter(u));
    }

    @Test
    public void ajouter_ok_lister_afficher() {
        var u = new Utilisateur(1,"Bob",25,"bob@mail.com","", "Douala", 5000);
        service.ajouter(u);
        List<Utilisateur> all = service.lister();
        Assert.assertEquals(1, all.size());
        Assert.assertTrue(service.afficher(1).isPresent());
    }

    @Test
    public void supprimer_inexistant_lance_exception() {
        Assert.assertThrows(UtilisateurService.SuppressionInvalidException.class, () -> service.supprimer(999));
    }
}
